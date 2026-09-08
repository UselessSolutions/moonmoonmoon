package useless.moonsteel.block;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;

public class BlockModelStellarRewinder<T extends BlockLogic> extends BlockModelGeneric<T> {
	public final StaticBlockModel active;


	public BlockModelStellarRewinder(Block<T> block, String baseKey) {
		super(block, BlockModelDispatcher.loadDataModel(baseKey));
		this.active = BlockModelDispatcher.loadDataModel(baseKey + "_active").asModel();
	}

	@Override
	public @NotNull StaticBlockModel getModel(@NotNull WorldSource source, @NotNull TilePosc tilePosc) {
		TileEntity tileEntity = source.getTileEntity(tilePosc);
		if (tileEntity instanceof TileEntityStellarRewinder rewinder && rewinder.inUse()) {
			return this.active;
		}
		return super.getModel(source, tilePosc);
	}


	@Override
	public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		Direction direction = BlockLogicRotatable.getDirectionFromMeta(worldSource.getBlockData(tilePos));
		switch (direction) {
			case UP -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 1, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case DOWN -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, -1, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case NORTH -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case WEST -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			case EAST -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
			default -> {
				return this.getModel(worldSource, tilePos)
					.renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			}
		}
	}
}
