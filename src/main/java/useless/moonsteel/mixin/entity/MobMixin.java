package useless.moonsteel.mixin.entity;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemToolSword;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.MoonSteelItems;
import useless.moonsteel.interfaces.IMoonGrav;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.mixin.accessor.ItemToolSwordAccessor;

@Mixin(value = Mob.class, remap = false)
public abstract class MobMixin extends Entity {
	@Shadow
	protected abstract void dropDeathItems();

	public MobMixin(final World world) {
		super(world);
	}

	@Unique
	private double moonsteel$scalar() {
		return (this instanceof IMoonGrav) ? ((IMoonGrav) this).moonsteel$getGravScalar() : 1.0;
	}

	// Air + skating gravity: yd -= 0.08
	@ModifyConstant(method = "moveEntityWithHeading(FF)V", constant = @Constant(doubleValue = 0.08))
	private double gravity(double original) {
		return original * moonsteel$scalar();
	}

	// Water + lava sinking: yd -= 0.02
	@ModifyConstant(method = "moveEntityWithHeading(FF)V", constant = @Constant(doubleValue = 0.02))
	private double liquidSink(double original) {
		return original * moonsteel$scalar();
	}

	// Ladder descent clamp: yd < -0.25 / yd = -0.25
	@ModifyConstant(method = "moveEntityWithHeading(FF)V", constant = @Constant(doubleValue = -0.25))
	private double ladderClamp(double original) {
		return original * moonsteel$scalar();
	}


	@ModifyVariable(method = "causeFallDamage(F)V", at = @At(value = "STORE"), ordinal = 0)
	private int changeFallDamage(final int i) {
		if (this instanceof IMoonGrav) {
			return (int) ((i * ((IMoonGrav) this).moonsteel$getGravScalar()) - (3 / ((IMoonGrav) this).moonsteel$getGravScalar()) + 3);
		}
		return i;
	}

	@Inject(method = "onDeath(Lnet/minecraft/core/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/Mob;dropDeathItems()V"))
	private void multiplyDrop(final Entity entity, final CallbackInfo ci) {
		if (entity instanceof Player) {
			final ItemStack heldStack = ((Player) entity).getHeldItem();
			if (heldStack != null && heldStack.getItem() instanceof ItemToolSword && ((ItemToolSwordAccessor) heldStack.getItem()).getMaterial() == MoonSteelItems.moonSteelTool) {
				for (int i = 0; i < this.random.nextInt(MoonSteel.LOOTING_AMOUNT); i++) {
					dropDeathItems();
				}
			}
		}
	}
}
