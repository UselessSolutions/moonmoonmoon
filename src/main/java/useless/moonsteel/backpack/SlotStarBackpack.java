package useless.moonsteel.backpack;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.player.inventory.slot.Slot;
import tosutosu.betterwithbackpacks.item.ItemBackpack;

public class SlotStarBackpack extends Slot {
	public SlotStarBackpack(Container inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	public boolean mayPlace(ItemStack itemstack) {
		return itemstack != null && !(itemstack.getItem() instanceof ItemBackpack) && !this.isLocked();
	}

}
