package useless.moonsteel;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.block.model.generic.BlockModelGenericTorch;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.Side;
import org.jetbrains.annotations.NotNull;
import useless.moonsteel.block.BlockModelStellarRewinder;
import useless.moonsteel.item.ItemModelConnectStar;

import static net.minecraft.client.render.item.model.ItemModelDispatcher.*;
import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelModels {


	public void initBlockModels(final BlockModelDispatcher dispatcher) {
		final Side[] S_TB = {Side.TOP, Side.BOTTOM};
		final Side[] S_SIDES = {Side.NORTH, Side.EAST, Side.SOUTH, Side.WEST};
		//TODO replace with BlockModelGeneric
		dispatcher.addDispatch(new BlockModelStandard<>(MoonSteelBlocks.BLOCK_MOONSTEEL));
//			.setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/moonsteel_block_side", S_SIDES)
//			.setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/moonsteel_block_top", Side.TOP)
//			.setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/moonsteel_block_bottom", Side.BOTTOM));

		// TODO especialy for this, replace the key
		dispatcher.addDispatch(new BlockModelGenericTorch<>(MoonSteelBlocks.TORCH_STAR, ""));
//			.setAllTextures(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/startorch"));

		// TODO relace this by a generic model
		dispatcher.addDispatch(new BlockModelStellarRewinder<>(MoonSteelBlocks.STELLAR_REWINDER));
//			.setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/stellarrewinder_side", Side.SOUTH, Side.WEST, Side.EAST)
//			.setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/stellarrewinder_front", Side.NORTH)
//			.setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/stellarrewinder_top", S_TB));
	}


	public void initItemModels(final ItemModelDispatcher dispatcher) {
		dispatcher.addDispatch(makeModel(MoonSteelItems.INGOT_MOONSTEEL, "moonsteel_ingot"));
		dispatcher.addDispatch(makeModel(MoonSteelItems.INGOT_MOONSTEEL_CRUDE, "moonsteel_crude"));
		dispatcher.addDispatch(makeHoldModel(MoonSteelItems.TOOL_PICKAXE_MOONSTEEL, "moonsteel_pickaxe"));
		dispatcher.addDispatch(makeHoldModel(MoonSteelItems.TOOL_AXE_MOONSTEEL, "moonsteel_axe"));
		dispatcher.addDispatch(makeHoldModel(MoonSteelItems.TOOL_SHOVEL_MOONSTEEL, "moonsteel_shovel"));
		dispatcher.addDispatch(makeHoldModel(MoonSteelItems.TOOL_HOE_MOONSTEEL, "moonsteel_hoe"));
		dispatcher.addDispatch(makeHoldModel(MoonSteelItems.TOOL_SWORD_MOONSTEEL, "moonsteel_sword"));
		dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_HELMET_MOONSTEEL, "moonsteel_helmet"));
		dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_CHESTPLATE_MOONSTEEL, "moonsteel_chestplate"));
		dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_LEGGINGS_MOONSTEEL, "moonsteel_leggings"));
		dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_BOOTS_MOONSTEEL, "moonsteel_boots"));
		dispatcher.addDispatch(makeModel(MoonSteelItems.STAR_FALLEN, "fallen_star").setFullBright());
		dispatcher.addDispatch(setIcon(new ItemModelConnectStar(MoonSteelItems.STAR_CONNECTED, null), MOD_ID + ":item/connected_star_off").setFullBright());
		dispatcher.addDispatch(makeModel(MoonSteelItems.BACKPACK_COSMIC, "starpack"));
	}


	public static @NotNull ItemModelStandard makeModel(@NotNull final Item item, @NotNull final String textureValue) {
		return setIcon(new ItemModelStandard(item, true), NamespaceID.fromPool(MOD_ID, "item/" + textureValue));
	}

	public static @NotNull ItemModelStandard makeHoldModel(@NotNull final Item item, @NotNull final String textureValue) {
		return setIcon(new ItemModelStandard(item, true), NamespaceID.fromPool(MOD_ID, "item/" + textureValue))
			.setDisplayPos("firstperson_righthand", HANDHELD_FIRST_PERSON_RIGHT_HAND)
			.setDisplayPos("firstperson_lefthand", HANDHELD_FIRST_PERSON_LEFT_HAND)
			.setDisplayPos("thirdperson_righthand", HANDHELD_THIRD_PERSON_RIGHT_HAND)
			.setDisplayPos("thirdperson_lefthand", HANDHELD_THIRD_PERSON_LEFT_HAND);
	}

	public static <T extends ItemModelStandard> @NotNull T setIcon(@NotNull final T model, @NotNull final String texture) {
		model.icon = TextureRegistry.getTexture(texture);
		return model;
	}

	public static <T extends ItemModelStandard> @NotNull T setIcon(@NotNull final T model, @NotNull final NamespaceID texture) {
		model.icon = TextureRegistry.getTexture(texture);
		return model;
	}
}
