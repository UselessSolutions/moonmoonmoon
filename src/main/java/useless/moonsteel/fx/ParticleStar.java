package useless.moonsteel.fx;

import net.minecraft.client.render.particle.ParticleFlame;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.world.World;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class ParticleStar extends ParticleFlame {
	private static final IconCoordinate star = TextureRegistry.getTexture(MOD_ID + ":item/particle_star");
	public float sizeO;
	public ParticleStar(final World world, final double x, final double y, final double z, final double xd, final double yd, final double zd) {
		super(world, x, y, z, xd, yd, zd, Type.ORANGE);
		this.tex = star;
		this.size *= 2.25f;
		this.sizeO = this.size;
	}

	// TODO figure out what do to with this
	public void render(final Tessellator t, final float partialTick, final double xOff, final double yOff, final double zOff, final float xa, final float ya, final float za, final float xa2, final float za2) {
//		final float s = ((float)this.age + partialTick) / (float)this.lifetime;
//		this.size = this.sizeO * (1.0f - s * s * 0.5f);
//		if (this.tex != null) {
//			final float u0 = (float)this.tex.getIconUMin();
//			final float u2 = (float)this.tex.getIconUMax();
//			final float v0 = (float)this.tex.getIconVMin();
//			final float v2 = (float)this.tex.getIconVMax();
//			final float r = 0.1F * this.size;
//			final float x = (float)(this.xo + (this.x - this.xo) * (double)partialTick - xOff);
//			final float y = (float)(this.yo + (this.y - this.yo) * (double)partialTick - yOff);
//			final float z = (float)(this.zo + (this.z - this.zo) * (double)partialTick - zOff);
//			float br = 1.0F;
//			if (LightmapHelper.isLightmapEnabled()) {
//				t.setLightmapCoord(this.getLightmapCoord(partialTick));
//			} else {
//				br = this.getBrightness(partialTick);
//			}
//
//			t.setColorOpaque_F(this.rCol * br, this.gCol * br, this.bCol * br);
//			t.addVertexWithUV(x - xa * r - xa2 * r, y - ya * r, z - za * r - za2 * r, u2, v2);
//			t.addVertexWithUV(x - xa * r + xa2 * r, y + ya * r, z - za * r + za2 * r, u2, v0);
//			t.addVertexWithUV(x + xa * r + xa2 * r, y + ya * r, z + za * r + za2 * r, u0, v0);
//			t.addVertexWithUV(x + xa * r - xa2 * r, y - ya * r, z + za * r - za2 * r, u0, v2);
//		}
	}

	@Override
	public void tick() {
		super.tick();
		this.tex = star;
	}
	@Override
	public int getParticleTexture() {
		return 2;
	}
}
