package useless.moonsteel.mixin.armor;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import useless.moonsteel.MoonSteelItems;
import useless.moonsteel.interfaces.IMoonGrav;

@Mixin(value = Player.class, remap = false)
public class PlayerMixinEquipment implements IMoonGrav {
	@Shadow
	public ContainerInventory inventory;

	@Override
	public double moonsteel$getGravScalar() {
		double scalar = 0d;
		if (inventory.armorItemInSlot(HumanArmorShape.HEAD) != null 	&& inventory.armorItemInSlot(HumanArmorShape.HEAD).getItem() 	== MoonSteelItems.ARMOR_HELMET_MOONSTEEL) scalar += 0.167d;
		if (inventory.armorItemInSlot(HumanArmorShape.CHEST) != null 	&& inventory.armorItemInSlot(HumanArmorShape.CHEST).getItem() 	== MoonSteelItems.ARMOR_CHESTPLATE_MOONSTEEL) scalar += 0.334d;
		if (inventory.armorItemInSlot(HumanArmorShape.LEGS) != null 	&& inventory.armorItemInSlot(HumanArmorShape.LEGS).getItem() 	== MoonSteelItems.ARMOR_LEGGINGS_MOONSTEEL) scalar += 0.167d;
		if (inventory.armorItemInSlot(HumanArmorShape.BOOTS) != null 	&& inventory.armorItemInSlot(HumanArmorShape.BOOTS).getItem() 	== MoonSteelItems.ARMOR_BOOTS_MOONSTEEL) scalar += 0.334d;
		return (1/(scalar + 1));
	}
}
