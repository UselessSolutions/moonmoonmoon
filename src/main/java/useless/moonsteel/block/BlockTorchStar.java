package useless.moonsteel.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTorch;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockTorchStar extends BlockLogicTorch {
    public BlockTorchStar(final Block<?> block) {
        super(block);
    }

    @Override
    public void animationTick(final World world, final int x, final int y, final int z, final Random rand) {
        final double xPos = x + 0.5;
        final double yPos = y + 0.575;
        final double zPos = z + 0.5;
        final double d3 = 0.22;
        final double d4 = 0.27;
        final int side = world.getBlockMetadata(x, y, z) & MASK_DIRECTION;
        switch (side) {
            case SIDE_WEST:
                world.spawnParticle("moonsteel$magic_smoke", xPos - d4, yPos + d3, zPos, 0.0F, 0.0F, 0.0F, 0);
                world.spawnParticle("moonsteel$star", xPos - d4, yPos + d3, zPos, 0.0F, 0.0F, 0.0F, 0);
                break;
            case SIDE_EAST:
                world.spawnParticle("moonsteel$magic_smoke", xPos + d4, yPos + d3, zPos, 0.0F, 0.0F, 0.0F, 0);
                world.spawnParticle("moonsteel$star", xPos + d4, yPos + d3, zPos, 0.0F, 0.0F, 0.0F, 0);
                break;
            case SIDE_NORTH:
                world.spawnParticle("moonsteel$magic_smoke", xPos, yPos + d3, zPos - d4, 0.0F, 0.0F, 0.0F, 0);
                world.spawnParticle("moonsteel$star", xPos, yPos + d3, zPos - d4, 0.0F, 0.0F, 0.0F, 0);
                break;
            case SIDE_SOUTH:
                world.spawnParticle("moonsteel$magic_smoke", xPos, yPos + d3, zPos + d4, 0.0F, 0.0F, 0.0F, 0);
                world.spawnParticle("moonsteel$star", xPos, yPos + d3, zPos + d4, 0.0F, 0.0F, 0.0F, 0);
                break;
            case SIDE_BOTTOM:
                world.spawnParticle("moonsteel$magic_smoke", xPos, yPos, zPos, 0.0F, 0.0F, 0.0F, 0);
                world.spawnParticle("moonsteel$star", xPos, yPos, zPos, 0.0F, 0.0F, 0.0F, 0);
        }
    }
}
