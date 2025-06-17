package useless.moonsteel.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import useless.moonsteel.MoonSteelItems;

public class BlockStellarRewinder extends BlockLogicRotatable {
	//Uses BlockTileEntityRotatable for its rotation properties not because its a tileEntity
	public BlockStellarRewinder(Block<?> block, Material material) {
		super(block, material);
		block.withEntity(TileEntityStellarRewinder::new);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		ItemStack heldItem = player.getHeldItem();
		TileEntityStellarRewinder rewinder = (TileEntityStellarRewinder) world.getTileEntity(x,y,z);
		if (heldItem != null && heldItem.getItem() == MoonSteelItems.STAR_CONNECTED){
			rewinder.linkStar(heldItem);
			return true;
		}
		return false;
	}
}
