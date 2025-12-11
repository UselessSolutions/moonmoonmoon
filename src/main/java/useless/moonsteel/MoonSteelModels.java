package useless.moonsteel;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.block.model.BlockModelTorch;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.Side;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.util.ModelEntrypoint;
import useless.moonsteel.block.BlockModelStellarRewinder;
import useless.moonsteel.item.ItemModelConnectStar;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelModels implements ModelEntrypoint {
    @Override
    public void initBlockModels(final BlockModelDispatcher dispatcher) {
        final Side[] S_TB = {Side.TOP, Side.BOTTOM};
        final Side[] S_SIDES = {Side.NORTH, Side.EAST, Side.SOUTH, Side.WEST};

        dispatcher.addDispatch(new BlockModelStandard<>(MoonSteelBlocks.BLOCK_MOONSTEEL)
            .setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/moonsteel_block_side", S_SIDES)
            .setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/moonsteel_block_top", Side.TOP)
            .setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/moonsteel_block_bottom", Side.BOTTOM));

        dispatcher.addDispatch(new BlockModelTorch<>(MoonSteelBlocks.TORCH_STAR)
            .setAllTextures(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/startorch"));

        dispatcher.addDispatch(new BlockModelStellarRewinder<>(MoonSteelBlocks.STELLAR_REWINDER)
            .setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/stellarrewinder_side", Side.SOUTH, Side.WEST, Side.EAST)
            .setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/stellarrewinder_front", Side.NORTH)
            .setTex(BlockModelStandard.BLOCK_TEXTURES, MOD_ID + ":block/stellarrewinder_top", S_TB));
    }

    @Override
    public void initItemModels(final ItemModelDispatcher dispatcher) {
        dispatcher.addDispatch(makeModel(MoonSteelItems.INGOT_MOONSTEEL, "moonsteel_ingot"));
        dispatcher.addDispatch(makeModel(MoonSteelItems.INGOT_MOONSTEEL_CRUDE, "moonsteel_crude"));
        dispatcher.addDispatch(makeModel(MoonSteelItems.TOOL_PICKAXE_MOONSTEEL, "moonsteel_pickaxe").setFull3D());
        dispatcher.addDispatch(makeModel(MoonSteelItems.TOOL_AXE_MOONSTEEL, "moonsteel_axe").setFull3D());
        dispatcher.addDispatch(makeModel(MoonSteelItems.TOOL_SHOVEL_MOONSTEEL, "moonsteel_shovel").setFull3D());
        dispatcher.addDispatch(makeModel(MoonSteelItems.TOOL_HOE_MOONSTEEL, "moonsteel_hoe").setFull3D());
        dispatcher.addDispatch(makeModel(MoonSteelItems.TOOL_SWORD_MOONSTEEL, "moonsteel_sword").setFull3D());
        dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_HELMET_MOONSTEEL, "moonsteel_helmet"));
        dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_CHESTPLATE_MOONSTEEL, "moonsteel_chestplate"));
        dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_LEGGINGS_MOONSTEEL, "moonsteel_leggings"));
        dispatcher.addDispatch(makeModel(MoonSteelItems.ARMOR_BOOTS_MOONSTEEL, "moonsteel_boots"));
        dispatcher.addDispatch(makeModel(MoonSteelItems.STAR_FALLEN, "fallen_star").setFullBright());
        dispatcher.addDispatch(setIcon(new ItemModelConnectStar(MoonSteelItems.STAR_CONNECTED, null), MOD_ID + ":item/connected_star_off").setFullBright());
        dispatcher.addDispatch(makeModel(MoonSteelItems.BACKPACK_COSMIC, "starpack"));
    }

    public static @NotNull ItemModelStandard makeModel(@NotNull final Item item, @NotNull final String textureValue) {
        return setIcon(new ItemModelStandard(item, null), NamespaceID.getTemp(MOD_ID, "item/" + textureValue));
    }

    public static <T extends ItemModelStandard> @NotNull T setIcon(@NotNull final T model, @NotNull final String texture) {
        model.icon = TextureRegistry.getTexture(texture);
        return model;
    }

    public static <T extends ItemModelStandard> @NotNull T setIcon(@NotNull final T model, @NotNull final NamespaceID texture) {
        model.icon = TextureRegistry.getTexture(texture);
        return model;
    }

    @Override
    public void initEntityModels(final EntityRenderDispatcher dispatcher) {

    }

    @Override
    public void initTileEntityModels(final TileEntityRenderDispatcher dispatcher) {

    }

    @Override
    public void initBlockColors(final BlockColorDispatcher dispatcher) {

    }
}
