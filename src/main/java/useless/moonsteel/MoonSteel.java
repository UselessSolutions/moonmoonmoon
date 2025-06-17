package useless.moonsteel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.entity.particle.ParticleDispatcher;
import net.minecraft.client.sound.SoundRepository;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundTypes;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tosutosu.betterwithbackpacks.ModItems;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import useless.moonsteel.block.TileEntityStellarRewinder;
import useless.moonsteel.fx.ParticleMagicSmoke;
import useless.moonsteel.fx.ParticleStar;

import java.util.Properties;


public class MoonSteel implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "moonsteel";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean backpackPresent = FabricLoader.getInstance().isModLoaded("betterwithbackpacks");
	public static int blockId;
	public static int itemId;
	public static int GUI_ID;
	public static int FORTUNE_AMOUNT;
	public static int LOOTING_AMOUNT;
	public static int STAR_SPAWN_RATE;
	static {
		final Properties prop = new Properties();
		prop.setProperty("starting_block_id","6700");
		prop.setProperty("starting_item_id","21400");
		prop.setProperty("gui_backpack_id","20");
		prop.setProperty("fortune_amount","3");
		prop.setProperty("looting_amount","3");
		prop.setProperty("star_spawn_rate","7500");
		final ConfigHandler config = new ConfigHandler(MOD_ID,prop);

		blockId = config.getInt("starting_block_id");
		itemId = config.getInt("starting_item_id");
		GUI_ID = config.getInt("gui_backpack_id");
		FORTUNE_AMOUNT = config.getInt("fortune_amount");
		LOOTING_AMOUNT = config.getInt("looting_amount");
		STAR_SPAWN_RATE=config.getInt("star_spawn_rate");

		config.updateConfig();
	}

	public static ItemStack starZombieSword;
	public static boolean forceChunkLoads = false;
    @Override
    public void onInitialize() {
        LOGGER.info("MoonSteel initialized.");
    }

	@Override
	public void beforeGameStart() {
		SoundTypes.loadSoundsJson(MOD_ID);
		EntityHelper.createTileEntity(TileEntityStellarRewinder.class, NamespaceID.getPermanent(MOD_ID, "moonsteel$stellar_rewinder"), "moonsteel$stellar_rewinder");
		MoonSteelBlocks.init();
		MoonSteelItems.init();
		if (backpackPresent){
			CreativeHelper.setParent(MoonSteelItems.BACKPACK_COSMIC.getDefaultStack(), ModItems.diamondBackpack.getDefaultStack());
		}
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void beforeClientStart() {
		SoundRepository.registerNamespace(MOD_ID);
	}

	@Override
	public void afterClientStart() {
		ParticleDispatcher.getInstance().addDispatch("moonsteel$star", (world, x, y, z, motionX, motionY, motionZ, data) -> new ParticleStar(world, x, y, z, motionX, motionY, motionX));
		ParticleDispatcher.getInstance().addDispatch("moonsteel$magic_smoke", (world, x, y, z, motionX, motionY, motionZ, data) -> new ParticleMagicSmoke(world, x, y, z, motionX, motionY, motionX));
	}

	public static boolean isStarTime(final World world){
		if (world.worldType.hasCeiling()) return false;
		if (world.isDaytime()) return false;
		return world.getWorldTime() % 2000 <= 200;
	}
}
