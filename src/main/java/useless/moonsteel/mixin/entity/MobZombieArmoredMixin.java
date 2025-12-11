package useless.moonsteel.mixin.entity;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.monster.MobZombie;
import net.minecraft.core.entity.monster.MobZombieArmored;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.MoonSteelItems;
import useless.moonsteel.interfaces.IMoonGrav;
import useless.moonsteel.interfaces.IStarZombie;

@Mixin(value = MobZombieArmored.class, remap = false)
public abstract class MobZombieArmoredMixin extends MobZombie implements IMoonGrav, IStarZombie {
    @Unique
    private static final int DATA_FLAG_STARTIME = 20;

    @Shadow
    public abstract int[] getArmorBreakPoints();

    @Shadow
    public abstract boolean isHoldingSword();

    protected MobZombieArmoredMixin(final World world) {
        super(world);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void defineSynchedData(final CallbackInfo ci) {
        this.entityData.define(DATA_FLAG_STARTIME, (byte) 0, Byte.class);
    }

    @Inject(method = "spawnInit", at = @At("TAIL"))
    public void sInit(final CallbackInfo ci) {
        if (MoonSteel.isStarTime(this.world)) {
            this.entityData.set(DATA_FLAG_STARTIME, (byte) 1);
        }
    }

    @Override
    public double moonsteel$getGravScalar() {
        if (moonsteel$isStarZombie()) {
            double scalar = 1d;
            final int[] breakPoints = this.getArmorBreakPoints();
            for (int i = 0; i < 4; ++i) {
                if (this.getHealth() > breakPoints[i]) scalar -= 0.125d;
            }
            return scalar;
        }
        return 1d;
    }

    @Override
    public boolean moonsteel$isStarZombie() {
        return this.entityData.getByte(DATA_FLAG_STARTIME) == 1;
    }

    @Inject(method = "getHeldItem()Lnet/minecraft/core/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void sword(final CallbackInfoReturnable<ItemStack> cir) {
        if (isHoldingSword() && moonsteel$isStarZombie()) {
            cir.setReturnValue(MoonSteel.starZombieSword);
        }
    }

    @Redirect(method = "hurt(Lnet/minecraft/core/entity/Entity;ILnet/minecraft/core/util/helper/DamageType;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/monster/MobZombieArmored;dropItem(Lnet/minecraft/core/item/ItemStack;F)Lnet/minecraft/core/entity/EntityItem;"))
    private EntityItem spawnRedirect(final MobZombieArmored instance, final ItemStack stack, final float v) {
        if (moonsteel$isStarZombie()) {
            return dropItem(MoonSteelItems.STAR_FALLEN.getDefaultStack(), v);
        }
        return dropItem(stack, v);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(final CompoundTag tag, final CallbackInfo ci) {
        tag.putByte("moonsteel$starzombie", this.entityData.getByte(DATA_FLAG_STARTIME));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(final CompoundTag tag, final CallbackInfo ci) {
        this.entityData.set(DATA_FLAG_STARTIME, tag.getByte("moonsteel$starzombie"));
    }


}
