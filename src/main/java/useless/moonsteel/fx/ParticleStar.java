package useless.moonsteel.fx;

import net.minecraft.client.render.particle.ParticleFlame;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tessellator.TessellatorParticle;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class ParticleStar extends ParticleFlame {
	private static final IconCoordinate star = TextureRegistry.getTexture(MOD_ID + ":item/particle_star");
	private float sizeO;

	public ParticleStar(final World world, final double x, final double y, final double z, final double xd, final double yd, final double zd) {
		super(world, x, y, z, xd, yd, zd, Type.ORANGE);
		this.tex = star;
		this.size *= 2.25f;
		this.sizeO = this.size;
	}


	@Override
	public void render(@NotNull TessellatorParticle tessellatorParticle, float partialTick) {
		final float s = (this.age + partialTick) / (float)this.lifetime;
		this.size = this.sizeO * (1.0f - s * s * 0.5f);
		super.render(tessellatorParticle, partialTick);
	}


	@Override
	public void tick() {
		super.tick();
		this.tex = star;
	}
//	@Override
//	public int getParticleTexture() {
//		return 2;
//	}
}
