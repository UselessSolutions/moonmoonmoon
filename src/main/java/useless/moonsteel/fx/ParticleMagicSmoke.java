package useless.moonsteel.fx;

import net.minecraft.client.render.particle.ParticleSmoke;
import net.minecraft.client.render.tessellator.TessellatorParticle;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class ParticleMagicSmoke extends ParticleSmoke {
	private static final IconCoordinate smoke = TextureRegistry.getTexture(MOD_ID + ":item/particle_magicsmoke");
	private float sizeO;

	public ParticleMagicSmoke(World world, double x, double y, double z, double xa, double ya, double za) {
		super(world, x, y, z, xa, ya, za, 1.0f, 0); // no scale unsure about data
		this.rCol = 1;
		this.gCol = 1;
		this.bCol = 1;
		this.tex = smoke;
		this.lifetime = (int) (5.0 / (Math.random() * 0.9 + 0.2));
		this.size *= 1.5f;
		this.sizeO = size;
	}

	@Override
	public void render(@NotNull TessellatorParticle tessellatorParticle, float partialTick) {
		float f6 = ((float) this.age + partialTick) / (float) this.lifetime * 32.0f;
		if (f6 < 0.0f) {
			f6 = 0.0f;
		}
		if (f6 > 1.0f) {
			f6 = 1.0f;
		}
		this.size = this.sizeO * f6;
		super.render(tessellatorParticle, partialTick);
	}

	@Override
	public float getBrightness(float partialTick) {
		float decay = MathHelper.clamp((this.age + partialTick) / this.lifetime, 0.0f, 1.0f);
		return super.getBrightness(partialTick) * decay + (1.0f - decay);
	}

//	@Override
//	public int getParticleTexture() {
//		return 2;
//	}

	@Override
	public void tick() {
		super.tick();
		this.tex = smoke;
	}
}
