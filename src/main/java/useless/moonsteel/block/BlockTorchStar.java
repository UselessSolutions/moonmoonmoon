package useless.moonsteel.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTorch;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BlockTorchStar extends BlockLogicTorch {

	public static final String SMOKE = "moonsteel$magic_smoke";
	public static final String STAR = "moonsteel$star";

	public BlockTorchStar(final Block<?> block) {
		super(block);
	}
	@Override
	public void animationTick(final World world, final @NotNull TilePosc tilePos, final @NotNull Random rand) {
		final double xPos = tilePos.x() + 0.5;
		final double yPos = tilePos.y() + 0.575;
		final double zPos = tilePos.z() + 0.5;
		final double d3 = 0.22;
		final double d4 = 0.27;
		double py = yPos + d3;
		final int side = world.getBlockData(tilePos) & MASK_DIRECTION;
		switch (side) {
			case SIDE_WEST -> {
				double px = xPos - d4;
				world.spawnParticle(SMOKE, px, py, zPos, 0.0F, 0.0F, 0.0F, 0, false);
				world.spawnParticle(STAR, px, py, zPos, 0.0F, 0.0F, 0.0F, 0, false);
			}
			case SIDE_EAST -> {
				double px = xPos + d4;
				world.spawnParticle(SMOKE, px, py, zPos, 0.0F, 0.0F, 0.0F, 0, false);
				world.spawnParticle(STAR, px, py, zPos, 0.0F, 0.0F, 0.0F, 0, false);
			}
			case SIDE_SOUTH -> {
				double pz = zPos + d4;
				world.spawnParticle(SMOKE, xPos, py, pz, 0.0F, 0.0F, 0.0F, 0, false);
				world.spawnParticle(STAR, xPos, py, pz, 0.0F, 0.0F, 0.0F, 0, false);
			}
			case SIDE_BOTTOM -> {
				world.spawnParticle(SMOKE, xPos, yPos, zPos, 0.0F, 0.0F, 0.0F, 0, false);
				world.spawnParticle(STAR, xPos, yPos, zPos, 0.0F, 0.0F, 0.0F, 0, false);
			}
			default -> { // north side is default
				double pz = zPos - d4;
				world.spawnParticle(SMOKE, xPos, py, pz, 0.0F, 0.0F, 0.0F, 0, false);
				world.spawnParticle(STAR, xPos, py, pz, 0.0F, 0.0F, 0.0F, 0, false);
			}
		}
	}
}
