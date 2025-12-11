package useless.moonsteel.compat.backpacks;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import useless.moonsteel.compat.backpacks.interfaces.IStarBackpack;

public class ItemStarBackpack extends ItemBackpack {
    public ItemStarBackpack(String name, String namespaceId, int id) {
        super(name, namespaceId, id, StarBackpackInventory.starBackpackSize);
    }

    @Override
    public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
        if (!world.isClientSide && BetterWithBackpacks.ENABLE_BACKPACKS) {
            ((IStarBackpack) entityplayer).moonsteel$displayGuiStarBackpack();
        }

        return itemstack;
    }
}
