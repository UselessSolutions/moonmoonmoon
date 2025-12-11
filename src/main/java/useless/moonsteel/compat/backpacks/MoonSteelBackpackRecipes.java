package useless.moonsteel.compat.backpacks;

import tosutosu.betterwithbackpacks.ModItems;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;
import useless.moonsteel.MoonSteelItems;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelBackpackRecipes implements RecipeEntrypoint {
    @Override
    public void onRecipesReady() {
        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MMM",
                "MSM",
                "MMM")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .addInput('S', ModItems.goldBackpack)
            .create("cosmic_backpack", MoonSteelBackpackItems.BACKPACK_COSMIC.getDefaultStack());
    }

    @Override
    public void initNamespaces() {
        RecipeBuilder.initNameSpace(MOD_ID);
    }
}
