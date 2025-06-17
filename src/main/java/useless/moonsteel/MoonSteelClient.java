package useless.moonsteel;

import net.minecraft.client.entity.particle.ParticleDispatcher;
import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.sound.SoundRepository;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import useless.moonsteel.fx.ParticleMagicSmoke;
import useless.moonsteel.fx.ParticleStar;

import java.io.IOException;
import java.net.URISyntaxException;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelClient implements ClientStartEntrypoint {
	@Override
	public void beforeClientStart() {
		for (final AtlasStitcher stitcher : TextureRegistry.stitcherMap.values()) {
			try {
				TextureRegistry.initializeAllFiles(MOD_ID, stitcher, true);
			} catch (URISyntaxException | IOException e) {
				MoonSteel.LOGGER.error("Failed to initialize texture files!", e);
			}
		}
		SoundRepository.registerNamespace(MOD_ID);
	}

	@Override
	public void afterClientStart() {
		ParticleDispatcher.getInstance().addDispatch("moonsteel$star", (world, x, y, z, motionX, motionY, motionZ, data) -> new ParticleStar(world, x, y, z, motionX, motionY, motionX));
		ParticleDispatcher.getInstance().addDispatch("moonsteel$magic_smoke", (world, x, y, z, motionX, motionY, motionZ, data) -> new ParticleMagicSmoke(world, x, y, z, motionX, motionY, motionX));
	}
}
