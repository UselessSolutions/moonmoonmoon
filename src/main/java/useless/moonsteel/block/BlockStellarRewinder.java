package useless.moonsteel.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import useless.moonsteel.MoonSteelItems;

public class BlockStellarRewinder extends BlockLogicRotatable {
	//Uses BlockTileEntityRotatable for its rotation properties not because its a tileEntity
	public BlockStellarRewinder(Block<?> block, Material material) {
		super(block, material);
		block.withEntity(TileEntityStellarRewinder::new);
	}

	@Override
	public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
		ItemStack heldItem = player.getHeldItem();
		TileEntityStellarRewinder rewinder = (TileEntityStellarRewinder) world.getTileEntity(tilePos);
		if (heldItem != null && heldItem.getItem() == MoonSteelItems.STAR_CONNECTED && rewinder != null){
			rewinder.linkStar(heldItem);
			return true;
		}
		return false;
	}
}
