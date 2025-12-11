package useless.moonsteel.compat.backpacks;

import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;
import useless.moonsteel.compat.backpacks.interfaces.IStarBackpack;

import java.util.Collections;
import java.util.List;

public class ContainerStarBackpack extends MenuAbstract {
    public StarBackpackInventory backpackInventory;

    public ContainerStarBackpack(final Player player) {
        this.backpackInventory = ((IStarBackpack) player).moonsteel$getStarBackpackInventory();
        final int slotsNum = this.backpackInventory.getContainerSize();
        final int rows = (int) Math.ceil(slotsNum / 9.0);

        for (int i = 0; i < rows; ++i) {
            int width = 9;
            if (i == rows - 1) {
                width = slotsNum - 9 * i;
            }

            for (int k = 0; k < width; ++k) {
                this.addSlot(new SlotStarBackpack(this.backpackInventory, k + i * 9, 8 + k * 18, 18 + 18 * i));
            }
        }

        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(player.inventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(player.inventory, j, 8 + j * 18, 142));
        }
    }

    @Override
    public List<Integer> getMoveSlots(final InventoryAction inventoryAction, final Slot slot, final int i, final Player entityPlayer) {
        final int chestSize = this.backpackInventory.getContainerSize();
        if (slot.index >= 0 && slot.index < chestSize) {
            return this.getSlots(0, chestSize, false);
        } else {
            if (inventoryAction == InventoryAction.MOVE_ALL) {
                if (slot.index >= chestSize && slot.index < chestSize + 27) {
                    return this.getSlots(chestSize, 27, false);
                }

                if (slot.index >= chestSize + 27 && slot.index < chestSize + 36) {
                    return this.getSlots(chestSize + 27, 9, false);
                }
            } else if (slot.index >= chestSize && slot.index < chestSize + 36) {
                return this.getSlots(chestSize, 36, false);
            }

            return Collections.emptyList();
        }
    }

    @Override
    public List<Integer> getTargetSlots(final InventoryAction inventoryAction, final Slot slot, final int i, final Player entityPlayer) {
        final int chestSize = this.backpackInventory.getContainerSize();
        return slot.index < chestSize ? this.getSlots(chestSize, 36, true) : this.getSlots(0, chestSize, false);
    }

    @Override
    public boolean stillValid(final Player entityPlayer) {
        return this.backpackInventory.stillValid(entityPlayer);
    }

}
