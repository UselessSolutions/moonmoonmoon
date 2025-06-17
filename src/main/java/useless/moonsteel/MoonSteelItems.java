package useless.moonsteel;

import net.minecraft.core.item.IArmorItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.item.tool.ItemToolAxe;
import net.minecraft.core.item.tool.ItemToolHoe;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.item.tool.ItemToolShovel;
import net.minecraft.core.item.tool.ItemToolSword;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import useless.moonsteel.item.ItemConnectedStar;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelItems {
	public static ToolMaterial moonSteelTool = new ToolMaterial().setDurability(1536).setEfficiency(7.0f, 14.0f).setMiningLevel(3).setDamage(2);
	public static Item INGOT_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new Item("ingot.moonsteel", MOD_ID + ":item/ingot_moonsteel", MoonSteel.itemId++));
	public static Item INGOT_MOONSTEEL_CRUDE = new ItemBuilder(MOD_ID)
		.build(new Item("crude.moonsteel", MOD_ID + ":item/ingot_moonsteel_crude", MoonSteel.itemId++));
	public static Item TOOL_PICKAXE_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemToolPickaxe("tool.pickaxe.moonsteel", MOD_ID + ":item/tool_pickaxe_moonsteel", MoonSteel.itemId++, moonSteelTool));
	public static Item TOOL_AXE_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemToolAxe("tool.axe.moonsteel", MOD_ID + ":item/tool_axe_moonsteel", MoonSteel.itemId++, moonSteelTool));
	public static Item TOOL_SHOVEL_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemToolShovel("tool.shovel.moonsteel", MOD_ID + ":item/tool_shovel_moonsteel", MoonSteel.itemId++, moonSteelTool));
	public static Item TOOL_HOE_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemToolHoe("tool.hoe.moonsteel", MOD_ID + ":item/tool_hoe_moonsteel", MoonSteel.itemId++, moonSteelTool));
	public static Item TOOL_SWORD_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemToolSword("tool.sword.moonsteel", MOD_ID + ":item/tool_sword_moonsteel", MoonSteel.itemId++, moonSteelTool));
	public static ArmorMaterial moonSteelArmor = ArmorHelper.createArmorMaterial(MOD_ID, "moonsteel", 800, 51f, 45f, 45f, 100f);
	public static Item ARMOR_HELMET_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemArmor("helmet.moonsteel", MOD_ID + ":item/armor_helmet_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_HEAD));
	public static Item ARMOR_CHESTPLATE_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemArmor("chestplate.moonsteel", MOD_ID + ":item/armor_chestplate_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_CHEST));
	public static Item ARMOR_LEGGINGS_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemArmor("leggings.moonsteel", MOD_ID + ":item/armor_leggings_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_LEGS));
	public static Item ARMOR_BOOTS_MOONSTEEL = new ItemBuilder(MOD_ID)
		.build(new ItemArmor("boots.moonsteel", MOD_ID + ":item/armor_boots_moonsteel", MoonSteel.itemId++, moonSteelArmor, IArmorItem.PIECE_BOOTS));
	public static Item STAR_FALLEN = new ItemBuilder(MOD_ID)
		.build(new Item("star.fallen", MOD_ID + ":item/star_fallen", MoonSteel.itemId++));
	public static Item STAR_CONNECTED = new ItemBuilder(MOD_ID)
		.setStackSize(1)
		.build(new ItemConnectedStar("star.connected", MOD_ID + ":item/star_connected", MoonSteel.itemId++));
	public static Item BACKPACK_COSMIC;

	public static void init() {
		MoonSteel.LOGGER.info("Backpacks present: {}", MoonSteel.backpackPresent);
		if (MoonSteel.backpackPresent){
			BACKPACK_COSMIC = new ItemBuilder(MOD_ID)
				.setStackSize(1)
				.build( BackpackProxy.proxyBackpack("backpack.cosmic", MOD_ID + ":item/backpack_cosmic", MoonSteel.itemId++));
		} else {
			BACKPACK_COSMIC = new ItemBuilder(MOD_ID)
				.setStackSize(1)
				.setTags(ItemTags.NOT_IN_CREATIVE_MENU)
				.build(new Item("backpack.cosmic.missing", MOD_ID + ":item/backpack_cosmic", MoonSteel.itemId++));
		}
		MoonSteel.starZombieSword = MoonSteelItems.TOOL_SWORD_MOONSTEEL.getDefaultStack();
	}
}
