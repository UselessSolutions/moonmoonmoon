package useless.moonsteel.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.TextureManager;
import net.minecraft.client.render.entity.MobRendererZombieArmored;
import net.minecraft.core.entity.monster.MobZombieArmored;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import useless.moonsteel.interfaces.IStarZombie;

@Mixin(value = MobRendererZombieArmored.class, remap = false)
public class MobRenderZombieArmoredMixin {

	@Inject(method = "getAndSetupModelForLayer(Lnet/minecraft/core/entity/monster/MobZombieArmored;FFI)Lorg/useless/dragonfly/models/entity/StaticEntityModel;",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/MobRendererZombieArmored;bindTexture(Ljava/lang/String;)V", shift = At.Shift.AFTER))
	private void starZombie(
		MobZombieArmored zombie,
		float brightness,
		float partialTick,
		int renderPass,
		CallbackInfoReturnable<StaticEntityModel> cir
	){
		if (((IStarZombie)zombie).moonsteel$isStarZombie()){
			String texturePath = "/assets/moonsteel/textures/armor/moonsteel_" + (renderPass != 2 ? 1 : 2) + ".png";
			TextureManager textureManager = Minecraft.getMinecraft().textureManager;
			textureManager.bindTexture(textureManager.loadTexture(texturePath));

		}
	}
}
