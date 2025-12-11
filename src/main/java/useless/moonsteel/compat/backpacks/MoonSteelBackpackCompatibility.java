package useless.moonsteel.compat.backpacks;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import org.spongepowered.asm.mixin.Mixins;
import tosutosu.betterwithbackpacks.ModItems;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.ModelEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class MoonSteelBackpackCompatibility implements PreLaunchEntrypoint, GameStartEntrypoint, ModelEntrypoint, RecipeEntrypoint {

    public static boolean IS_BACKPACK_LOADED = false;

    public static ModelEntrypoint modelEntryPointDelegate;
    public static RecipeEntrypoint recipeEntrypointDelegate;


    @Override
    public void onPreLaunch() {
        FabricLoader loader = FabricLoader.getInstance();

        IS_BACKPACK_LOADED = loader.isModLoaded("betterwithbackpacks");

        if (IS_BACKPACK_LOADED) {
            Mixins.addConfiguration("compat/backpack/backpack.mixins.json");

            try {
                modelEntryPointDelegate = (ModelEntrypoint) Class
                    .forName("useless.moonsteel.compat.backpacks.MoonSteelBackpackModels")
                    .getConstructor()
                    .newInstance();

                recipeEntrypointDelegate = (RecipeEntrypoint) Class
                    .forName("useless.moonsteel.compat.backpacks.MoonSteelBackpackRecipes")
                    .getConstructor()
                    .newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void callInit(String classPath, String methodName) {
        try {
            Class.forName(classPath).getMethod(methodName).invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeGameStart() {
        if (IS_BACKPACK_LOADED) {
            callInit("useless.moonsteel.compat.backpacks.MoonSteelBackpackItems", "init");
            CreativeHelper.setParent(MoonSteelBackpackItems.BACKPACK_COSMIC.getDefaultStack(), ModItems.diamondBackpack.getDefaultStack());
        }
    }

    @Override
    public void afterGameStart() {
    }

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {
        if (IS_BACKPACK_LOADED) modelEntryPointDelegate.initBlockColors(dispatcher);
    }

    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {
        if (IS_BACKPACK_LOADED) modelEntryPointDelegate.initBlockModels(dispatcher);
    }

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        if (IS_BACKPACK_LOADED) modelEntryPointDelegate.initItemModels(dispatcher);
    }

    @Override
    public void initEntityModels(EntityRenderDispatcher dispatcher) {
        if (IS_BACKPACK_LOADED) modelEntryPointDelegate.initEntityModels(dispatcher);
    }

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {
        if (IS_BACKPACK_LOADED) modelEntryPointDelegate.initTileEntityModels(dispatcher);
    }

    @Override
    public void onRecipesReady() {
        if (IS_BACKPACK_LOADED) recipeEntrypointDelegate.onRecipesReady();
    }

    @Override
    public void initNamespaces() {
        if (IS_BACKPACK_LOADED) recipeEntrypointDelegate.initNamespaces();
    }
}
