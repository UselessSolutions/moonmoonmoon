package useless.moonsteel;

import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.item.tool.*;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import useless.moonsteel.item.ItemConnectedStar;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelItems {
    public static ToolMaterial moonSteelTool = new ToolMaterial().setDurability(1536).setEfficiency(7.0f, 14.0f).setMiningLevel(3).setDamage(2);
    public static ArmorMaterial moonSteelArmor = ArmorHelper.createArmorMaterial(MOD_ID, "moonsteel", 800, 51f, 45f, 45f, 100f);

    public static Item INGOT_MOONSTEEL;
    public static Item INGOT_MOONSTEEL_CRUDE;

    public static Item TOOL_PICKAXE_MOONSTEEL;
    public static Item TOOL_AXE_MOONSTEEL;
    public static Item TOOL_SHOVEL_MOONSTEEL;
    public static Item TOOL_HOE_MOONSTEEL;
    public static Item TOOL_SWORD_MOONSTEEL;

    public static Item ARMOR_HELMET_MOONSTEEL;
    public static Item ARMOR_CHESTPLATE_MOONSTEEL;
    public static Item ARMOR_LEGGINGS_MOONSTEEL;
    public static Item ARMOR_BOOTS_MOONSTEEL;

    public static Item STAR_FALLEN;
    public static Item STAR_CONNECTED;

    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            hasInit = true;
            initializeItems();
        }
        MoonSteel.starZombieSword = MoonSteelItems.TOOL_SWORD_MOONSTEEL.getDefaultStack();
    }

    public static void initializeItems() {


        INGOT_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new Item("ingot.moonsteel", MOD_ID + ":item/ingot_moonsteel", MoonSteel.itemId++));
        INGOT_MOONSTEEL_CRUDE = new ItemBuilder(MOD_ID)
            .build(new Item("crude.moonsteel", MOD_ID + ":item/ingot_moonsteel_crude", MoonSteel.itemId++));
        TOOL_PICKAXE_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemToolPickaxe("tool.pickaxe.moonsteel", MOD_ID + ":item/tool_pickaxe_moonsteel", MoonSteel.itemId++, moonSteelTool));
        TOOL_AXE_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemToolAxe("tool.axe.moonsteel", MOD_ID + ":item/tool_axe_moonsteel", MoonSteel.itemId++, moonSteelTool));
        TOOL_SHOVEL_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemToolShovel("tool.shovel.moonsteel", MOD_ID + ":item/tool_shovel_moonsteel", MoonSteel.itemId++, moonSteelTool));
        TOOL_HOE_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemToolHoe("tool.hoe.moonsteel", MOD_ID + ":item/tool_hoe_moonsteel", MoonSteel.itemId++, moonSteelTool));
        TOOL_SWORD_MOONSTEEL = new ItemBuilder(MOD_ID)
            .setTags(ItemTags.PREVENT_CREATIVE_MINING)
            .build(new ItemToolSword("tool.sword.moonsteel", MOD_ID + ":item/tool_sword_moonsteel", MoonSteel.itemId++, moonSteelTool));
        ARMOR_HELMET_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemArmor("helmet.moonsteel", MOD_ID + ":item/armor_helmet_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_HEAD));
        ARMOR_CHESTPLATE_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemArmor("chestplate.moonsteel", MOD_ID + ":item/armor_chestplate_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_CHEST));
        ARMOR_LEGGINGS_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemArmor("leggings.moonsteel", MOD_ID + ":item/armor_leggings_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_LEGS));
        ARMOR_BOOTS_MOONSTEEL = new ItemBuilder(MOD_ID)
            .build(new ItemArmor("boots.moonsteel", MOD_ID + ":item/armor_boots_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_BOOTS));
        STAR_FALLEN = new ItemBuilder(MOD_ID)
            .build(new Item("star.fallen", MOD_ID + ":item/star_fallen", MoonSteel.itemId++));
        STAR_CONNECTED = new ItemBuilder(MOD_ID)
            .setStackSize(1)
            .build(new ItemConnectedStar("star.connected", MOD_ID + ":item/star_connected", MoonSteel.itemId++));

    }
}
