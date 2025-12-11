package useless.moonsteel;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.crafting.LookupFuelFurnaceBlast;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import tosutosu.betterwithbackpacks.ModItems;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelRecipes implements RecipeEntrypoint {
    @Override
    public void onRecipesReady() {
        LookupFuelFurnace.instance.addFuelEntry(MoonSteelItems.STAR_FALLEN.id, 9600);
        LookupFuelFurnaceBlast.instance.addFuelEntry(MoonSteelItems.STAR_FALLEN.id, 9600);

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                " F ",
                "FSF",
                " F ")
            .addInput('F', MoonSteelItems.STAR_FALLEN)
            .addInput('S', Items.INGOT_STEEL_CRUDE)
            .create("raw_moonsteel", MoonSteelItems.INGOT_MOONSTEEL_CRUDE.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "M",
                "M",
                "S")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .addInput('S', Items.STICK)
            .create("moonsteel_sword", MoonSteelItems.TOOL_SWORD_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MMM",
                " S ",
                " S ")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .addInput('S', Items.STICK)
            .create("moonsteel_pickaxe", MoonSteelItems.TOOL_PICKAXE_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MM",
                "MS",
                " S")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .addInput('S', Items.STICK)
            .create("moonsteel_axe", MoonSteelItems.TOOL_AXE_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "M",
                "S",
                "S")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .addInput('S', Items.STICK)
            .create("moonsteel_shovel", MoonSteelItems.TOOL_SHOVEL_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MM",
                " S",
                " S")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .addInput('S', Items.STICK)
            .create("moonsteel_hoe", MoonSteelItems.TOOL_HOE_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MMM",
                "M M",
                "   ")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .create("moonsteel_helmet", MoonSteelItems.ARMOR_HELMET_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "M M",
                "MMM",
                "MMM")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .create("moonsteel_chestplate", MoonSteelItems.ARMOR_CHESTPLATE_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MMM",
                "M M",
                "M M")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .create("moonsteel_leggings", MoonSteelItems.ARMOR_LEGGINGS_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "M M",
                "M M",
                "   ")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .create("moonsteel_boots", MoonSteelItems.ARMOR_BOOTS_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "MMM",
                "MMM",
                "MMM")
            .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
            .create("block_of_moonsteel", MoonSteelBlocks.BLOCK_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "F",
                "S")
            .addInput('F', MoonSteelItems.STAR_FALLEN)
            .addInput('S', Items.STICK)
            .create("stardust_torches", new ItemStack(MoonSteelBlocks.TORCH_STAR, 8));

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "DLD",
                "LFL",
                "DLD")
            .addInput('F', MoonSteelItems.STAR_FALLEN)
            .addInput('L', Items.DYE, DyeColor.BLUE.itemMeta)
            .addInput('D', Items.DIAMOND)
            .create("connected_star", MoonSteelItems.STAR_CONNECTED.getDefaultStack());

        RecipeBuilder.Shaped(MOD_ID)
            .setShape(
                "SSS",
                "ICI",
                "SSS")
            .addInput('S', Items.INGOT_STEEL)
            .addInput('I', Items.INGOT_IRON)
            .addInput('C', MoonSteelItems.STAR_CONNECTED)
            .create("stellar_rewinder", MoonSteelBlocks.STELLAR_REWINDER.getDefaultStack());

        if (MoonSteel.backpackPresent) {
            RecipeBuilder.Shaped(MOD_ID)
                .setShape(
                    "MMM",
                    "MSM",
                    "MMM")
                .addInput('M', MoonSteelItems.INGOT_MOONSTEEL)
                .addInput('S', ModItems.goldBackpack)
                .create("cosmic_backpack", MoonSteelItems.BACKPACK_COSMIC.getDefaultStack());
        }

        RecipeBuilder.BlastFurnace(MOD_ID)
            .setInput(MoonSteelItems.INGOT_MOONSTEEL_CRUDE)
            .create("moonsteel", MoonSteelItems.INGOT_MOONSTEEL.getDefaultStack());

        RecipeBuilder.Trommel(MOD_ID)
            .setInput(MoonSteelItems.STAR_FALLEN)
            .addEntry(new WeightedRandomLootObject(Items.COAL.getDefaultStack(), 1, 3), 14)
            .addEntry(new WeightedRandomLootObject(Items.ORE_RAW_IRON.getDefaultStack(), 1), 5)
            .addEntry(new WeightedRandomLootObject(Items.ORE_RAW_GOLD.getDefaultStack(), 1), 1)
            .create("fallenstar");
    }

    @Override
    public void initNamespaces() {
        RecipeBuilder.initNameSpace(MOD_ID);
    }
}
