package useless.moonsteel.block;

import net.minecraft.client.render.block.model.BlockModelHorizontalRotation;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import useless.moonsteel.MoonSteel;

public class BlockModelStellarRewinder<T extends BlockLogic> extends BlockModelHorizontalRotation<T> {
	private static IconCoordinate icon_front = TextureRegistry.getTexture(MoonSteel.MOD_ID + ":block/stellarrewinder_front");
	private static IconCoordinate icon_front_active = TextureRegistry.getTexture(MoonSteel.MOD_ID + ":block/stellarrewinder_front_active");
	public BlockModelStellarRewinder(Block block) {
		super(block);
	}
	@Override
	public IconCoordinate getBlockTexture(@NotNull WorldSource blockAccess, @NotNull TilePosc tilePosc, @NotNull Side side) {
		IconCoordinate iconCoordinate = super.getBlockTexture(blockAccess, tilePosc, side);
		TileEntityStellarRewinder rewinder = (TileEntityStellarRewinder) blockAccess.getTileEntity(tilePosc);
		if (rewinder != null && rewinder.inUse() && iconCoordinate == icon_front){
			iconCoordinate = icon_front_active;
		}
		return iconCoordinate;
	}
}
