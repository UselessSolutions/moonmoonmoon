package useless.moonsteel.block;

import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicPressurePlate;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.data.block.BlockModelData;

public class BlockModelStarLamp<T extends BlockLogic> extends BlockModelGeneric<T> {

	public BlockModelStarLamp(@NotNull Block<T> block, @NotNull BlockModelData staticModel) {
		super(block, staticModel);
	}

	@Override
	public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		Side direction = BlockLogicPressurePlate.sideFromMeta(worldSource.getBlockData(tilePos));
		boolean var10000;
		switch (direction) {
			// int rotX, int rotY, int rotZ, double xOff, double yOff, double zOff,
			case BOTTOM -> 	var10000 = this.getModel(worldSource, tilePos)
				.renderAttached(this, tessellator, worldSource, tilePos, 1, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			case TOP -> 	var10000 = this.getModel(worldSource, tilePos)
				.renderAttached(this, tessellator, worldSource, tilePos, 3, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			case NORTH -> 	var10000 = this.getModel(worldSource, tilePos)
				.renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			case SOUTH -> 	var10000 = this.getModel(worldSource, tilePos)
				.renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			case WEST -> 	var10000 = this.getModel(worldSource, tilePos)
				.renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
			default -> 		var10000 = this.getModel(worldSource, tilePos)
				.renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
		}

		return var10000;
	}

}
