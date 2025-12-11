package useless.moonsteel.fx;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.entity.particle.ParticleSmoke;
import net.minecraft.client.render.LightmapHelper;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;

import static useless.moonsteel.MoonSteel.MOD_ID;

@Environment(EnvType.CLIENT)
public class ParticleMagicSmoke extends ParticleSmoke {
    private static final IconCoordinate smoke = TextureRegistry.getTexture(MOD_ID + ":item/particle_magicsmoke");
    public float sizeO;

    public ParticleMagicSmoke(World world, double x, double y, double z, double xa, double ya, double za) {
        super(world, x, y, z, xa, ya, za);
        this.rCol = 1;
        this.gCol = 1;
        this.bCol = 1;
        this.tex = smoke;
        this.lifetime = (int) (5.0 / (random.nextInt() * 0.9 + 0.2));
        size *= 1.5f;
        sizeO = size;
    }

    @Override
    public void render(Tessellator t, float partialTick, double xOff, double yOff, double zOff, float xa, float ya, float za, float xa2, float za2) {
        float f6 = (this.age + partialTick) / this.lifetime * 32.0F;
        if (f6 < 0.0f) {
            f6 = 0.0f;
        }
        if (f6 > 1.0f) {
            f6 = 1.0f;
        }
        this.size = this.sizeO * f6;
        if (this.tex != null) {
            final float u0 = (float) this.tex.getIconUMin();
            final float u2 = (float) this.tex.getIconUMax();
            final float v0 = (float) this.tex.getIconVMin();
            final float v2 = (float) this.tex.getIconVMax();
            final float r = 0.1F * this.size;
            final float x = (float) (this.xo + (this.x - this.xo) * partialTick - xOff);
            final float y = (float) (this.yo + (this.y - this.yo) * partialTick - yOff);
            final float z = (float) (this.zo + (this.z - this.zo) * partialTick - zOff);
            float br = 1.0F;
            if (LightmapHelper.isLightmapEnabled()) {
                t.setLightmapCoord(this.getLightmapCoord(partialTick));
            } else {
                br = this.getBrightness(partialTick);
            }

            t.setColorOpaque_F(this.rCol * br, this.gCol * br, this.bCol * br);
            t.addVertexWithUV(x - xa * r - xa2 * r, y - ya * r, z - za * r - za2 * r, u2, v2);
            t.addVertexWithUV(x - xa * r + xa2 * r, y + ya * r, z - za * r + za2 * r, u2, v0);
            t.addVertexWithUV(x + xa * r + xa2 * r, y + ya * r, z + za * r + za2 * r, u0, v0);
            t.addVertexWithUV(x + xa * r - xa2 * r, y - ya * r, z + za * r - za2 * r, u0, v2);
        }
    }

    @Override
    public float getBrightness(float partialTick) {
        float decay = MathHelper.clamp((this.age + partialTick) / this.lifetime, 0.0f, 1.0f);
        return super.getBrightness(partialTick) * decay + (1.0f - decay);
    }

    @Override
    public int getParticleTexture() {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();
        this.tex = smoke;
    }
}
