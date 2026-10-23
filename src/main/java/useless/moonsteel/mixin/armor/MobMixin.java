package useless.moonsteel.mixin.armor;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemToolSword;
import net.minecraft.core.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.item.MoonSteelItems;
import useless.moonsteel.interfaces.IMoonGrav;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.mixin.accessor.ItemToolSwordAccessor;

@Mixin(value = Mob.class, remap = false)
public abstract class MobMixin extends Entity {
	@Shadow
	protected abstract void dropDeathItems();

	protected MobMixin(final World world) {
		super(world);
	}

	@WrapOperation(method = "moveEntityWithHeading(FF)V",
		at = @At(value = "FIELD", target = "Lnet/minecraft/core/entity/Mob;yd:D", opcode = Opcodes.PUTFIELD))
	private void entityGravity(Mob entity, double yd, Operation<Void> original) {
		if (entity instanceof IMoonGrav iMoonGrav) {
			final double offset = -(yd - entity.yd);
			final double scalar = iMoonGrav.moonsteel$getGravScalar();
			if ((0.019 < offset && offset < 0.021) || (0.079 < offset && offset < 0.081)) { // gravity step in water or air
				original.call(entity, entity.yd - offset * scalar);
				return;
			}
			if (-0.251 < yd && yd < -0.249) { // ladder clamp
				original.call(entity, yd * scalar);
				return;
			}
			original.call(entity, yd);
			return;
		}
		original.call(entity, yd);
	}


	@ModifyVariable(method = "causeFallDamage(F)V", at = @At(value = "STORE"), ordinal = 0)
	private int changeFallDamage(final int i){
		if (this instanceof IMoonGrav){
			return (int)((i * ((IMoonGrav) this).moonsteel$getGravScalar()) - (3/((IMoonGrav) this).moonsteel$getGravScalar()) + 3);
		}
		return i;
	}

	@Inject(method = "onDeath(Lnet/minecraft/core/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/Mob;dropDeathItems()V"))
	private void multiplyDrop(final Entity entity, final CallbackInfo ci){
		if (entity instanceof Player player){
			final ItemStack heldStack = player.getHeldItem();
			if (heldStack != null && heldStack.getItem() instanceof ItemToolSword && ((ItemToolSwordAccessor) heldStack.getItem()).getMaterial() == MoonSteelItems.MOON_STEEL_TOOL){
				for (int i = 0; i < this.random.nextInt(MoonSteel.LOOTING_AMOUNT); i++) {
					dropDeathItems();
				}
			}
		}
	}
}
