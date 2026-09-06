package useless.moonsteel.backpack;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventorySorter;
import net.minecraft.core.player.inventory.container.Container;
import org.jetbrains.annotations.NotNull;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import useless.moonsteel.MoonSteelItems;

public class StarBackpackInventory implements Container {
	public static final int STAR_BACKPACK_SIZE = 18;
	private ItemStack[] backpackItemStacks;
	private Player player;

	public StarBackpackInventory(Player player) {
		this.player = player;
		this.backpackItemStacks = new ItemStack[STAR_BACKPACK_SIZE];
	}

	@Override
	public int getContainerSize() {
		return STAR_BACKPACK_SIZE;
	}

	@Override
	public ItemStack getItem(int i) {
		return this.backpackItemStacks[i];
	}

	@Override
	public ItemStack removeItem(int i, int j) {
		if (this.backpackItemStacks[i] != null) {
			if (this.backpackItemStacks[i].stackSize <= j) {
				ItemStack itemstack = this.backpackItemStacks[i];
				this.backpackItemStacks[i] = null;
				return itemstack;
			} else {
				ItemStack itemstack1 = this.backpackItemStacks[i].splitStack(j);
				if (this.backpackItemStacks[i].stackSize <= 0) {
					this.backpackItemStacks[i] = null;
				}

				return itemstack1;
			}
		} else {
			return null;
		}
	}

	@Override
	public void setItem(int i, ItemStack itemStack) {
		this.backpackItemStacks[i] = itemStack;
		if (itemStack != null && itemStack.stackSize > this.getMaxStackSize()) {
			itemStack.stackSize = this.getMaxStackSize();
		}
	}

	@Override
	public @NotNull String getNameTranslationKey() {
		return "moonsteel.container.backpack.star.name";
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public void setChanged() {
		/* no need */
	}

	public void readFromNBT(ListTag tagList) {
		this.backpackItemStacks = new ItemStack[this.getContainerSize()];

		for(int i = 0; i < tagList.tagCount(); ++i) {
			CompoundTag nbttagcompound1 = (CompoundTag)tagList.tagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.backpackItemStacks.length) {
				this.backpackItemStacks[byte0] = ItemStack.readItemStackFromNbt(nbttagcompound1);
			}
		}
	}

	public ListTag writeToNBT(ListTag tagList) {
		for(int i = 0; i < this.backpackItemStacks.length; ++i) {
			if (this.backpackItemStacks[i] != null) {
				CompoundTag nbttagcompound1 = new CompoundTag();
				nbttagcompound1.putByte("Slot", (byte)i);
				this.backpackItemStacks[i].writeToNBT(nbttagcompound1);
				tagList.addTag(nbttagcompound1);
			}
		}

		return tagList;
	}

	@Override
	public boolean stillValid(@NotNull Player entityPlayer) {
		if (!BetterWithBackpacks.ENABLE_BACKPACKS) {
			return false;
		} else if (entityPlayer.getHeldItem() == null) {
			return false;
		} else {
			ItemStack heldItem = entityPlayer.getHeldItem();
			return heldItem.getItem() == MoonSteelItems.BACKPACK_COSMIC;
		}
	}

	@Override
	public void sort() {
		InventorySorter.sortInventory(this.backpackItemStacks);
	}

	@Override
	public boolean locked(int slot) {
		return Container.super.locked(slot);
	}

}
