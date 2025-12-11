package useless.moonsteel.mixin;

import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererZombieArmored;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.entity.monster.MobZombieArmored;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.moonsteel.interfaces.IStarZombie;

@Mixin(value = MobRendererZombieArmored.class, remap = false)
public class MobRenderZombieArmoredMixin extends MobRendererBiped<MobZombieArmored> {
    public MobRenderZombieArmoredMixin(final ModelBiped model, final float shadowSize) {
        super(model, shadowSize);
    }

    @Inject(method = "prepareArmor(Lnet/minecraft/core/entity/monster/MobZombieArmored;IF)Z",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/MobRendererZombieArmored;bindTexture(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void starZombie(final MobZombieArmored zombie, final int renderPass, final float partialTick, final CallbackInfoReturnable<Boolean> cir) {
        if (((IStarZombie) zombie).moonsteel$isStarZombie()) {
            this.bindTexture("/assets/moonsteel/textures/armor/moonsteel_" + (renderPass != 2 ? 1 : 2) + ".png");
        }
    }
}
