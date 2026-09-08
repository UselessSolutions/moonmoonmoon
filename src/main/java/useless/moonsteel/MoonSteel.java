package useless.moonsteel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityDispatcher;
import net.minecraft.core.item.IItemConvertible;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.sound.SoundTypes;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryRegistry;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.dependency.Key;
import useless.moonsteel.block.TileEntityStellarRewinder;

import java.util.Properties;
import java.util.function.Supplier;

import static useless.moonsteel.MoonSteelBlocks.*;
import static useless.moonsteel.MoonSteelItems.*;


public class MoonSteel implements ModInitializer {
    public static final String MOD_ID = HalpLibe.registerMod("moonsteel");
	public static final Key KEY = Key.of(MOD_ID);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String SMOKE = "moonsteel$magic_smoke";
	public static final String STAR = "moonsteel$star";
	public static boolean backpackPresent = FabricLoader.getInstance().isModLoaded("betterwithbackpacks");
	public static int blockId;
	public static int itemId;
	public static int GUI_ID;
	public static int FORTUNE_AMOUNT;
	public static int LOOTING_AMOUNT;
	public static int STAR_SPAWN_RATE;
	public static ItemStack starZombieSword;
	public static boolean forceChunkLoads = false;

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

    @Override
    public void onInitialize() {
        LOGGER.info("MoonSteel initialized.");
		CommonEvents.BEFORE_GAME_START.listen(KEY, this::beforeGameStart);
		CommonEvents.RECIPES_READY.listen(KEY, MoonSteelRecipes::onRecipesReady);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(KEY, MoonSteelRecipes::initNamespaces);
    }

	public void beforeGameStart() {
		SoundTypes.loadSoundsJson(MOD_ID);
		TileEntityDispatcher.addMapping(
			TileEntityStellarRewinder.class,
			NamespaceID.fromPool(MOD_ID, "moonsteel$stellar_rewinder")
		);
		MoonSteelBlocks.init();
		MoonSteelItems.init();
		// blocks
		CreativeInventoryRegistry.INSTANCE.register(BLOCK_MOONSTEEL, place(() -> Blocks.BLOCK_OLIVINE));
		CreativeInventoryRegistry.INSTANCE.register(TORCH_STAR, place(() -> Blocks.TORCH_COAL));
		CreativeInventoryRegistry.INSTANCE.register(STELLAR_REWINDER, place());
		CreativeInventoryRegistry.INSTANCE.register(STAR_LAMP, place(() -> Blocks.TORCH_COAL));
		// items
		CreativeInventoryRegistry.INSTANCE.register(INGOT_MOONSTEEL_CRUDE, place(() -> Items.INGOT_STEEL_CRUDE));
		CreativeInventoryRegistry.INSTANCE.register(INGOT_MOONSTEEL, place(() -> Items.INGOT_STEEL_CRUDE));
		CreativeInventoryRegistry.INSTANCE.register(STAR_FALLEN, place(() -> Items.INGOT_STEEL_CRUDE));
		CreativeInventoryRegistry.INSTANCE.register(STAR_CONNECTED, place(() -> Items.INGOT_STEEL_CRUDE));
		CreativeInventoryRegistry.INSTANCE.register(TOOL_SHOVEL_MOONSTEEL, place(() -> Items.TOOL_SWORD_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(TOOL_PICKAXE_MOONSTEEL, place(() -> Items.TOOL_SWORD_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(TOOL_AXE_MOONSTEEL, place(() -> Items.TOOL_SWORD_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(TOOL_HOE_MOONSTEEL, place(() -> Items.TOOL_SWORD_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(TOOL_SWORD_MOONSTEEL, place(() -> Items.TOOL_SWORD_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(ARMOR_HELMET_MOONSTEEL, place(() -> Items.ARMOR_WOLF_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(ARMOR_CHESTPLATE_MOONSTEEL, place(() -> Items.ARMOR_WOLF_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(ARMOR_LEGGINGS_MOONSTEEL, place(() -> Items.ARMOR_WOLF_STEEL));
		CreativeInventoryRegistry.INSTANCE.register(ARMOR_BOOTS_MOONSTEEL, place(() -> Items.ARMOR_WOLF_STEEL));
		if (backpackPresent){
			CreativeInventoryRegistry.INSTANCE.register(BACKPACK_COSMIC, place(() -> Items.ARMOR_WOLF_STEEL));
		}
	}

	private static CreativeInventoryPlacement.@NotNull After place(Supplier<IItemConvertible> iItemConvertibleSupplier) {
		return new CreativeInventoryPlacement.After(iItemConvertibleSupplier);
	}

	private static CreativeInventoryPlacement.@NotNull Category place() {
		return new CreativeInventoryPlacement.Category(CreativeInventoryCategory.WORKBENCHES);
	}

	public static boolean isStarTime(final World world){
		if (world.getWorldType().hasCeiling()) return false;
		if (world.isDaytime()) return false;
		return world.getWorldTime() % 2000 <= 200;
	}
}
