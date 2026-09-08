package useless.moonsteel.mixin.accessor;

import net.minecraft.client.render.particle.ParticleFlame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ParticleFlame.class)
public interface ParticleFlameAccessor {

	@Mutable
	@Accessor
	void setOSize(float oSize);
}
