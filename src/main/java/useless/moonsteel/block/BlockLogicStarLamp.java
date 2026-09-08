package useless.moonsteel.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.support.FullSupport;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.ISupportable;
import net.minecraft.core.block.support.PartialSupport;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

public class BlockLogicStarLamp extends BlockLogic implements ISupportable {

	public static final float MIN_LENGTH = 0.0F;
	public static final float MAX_LENGTH = 1.0F;
	public static final float THINNESS = 2.0f / 16.0f;
	public static final float HEIGHT = 5.0f /16.0F;
	public static final float START = 5.5F / 16.0F;

	public BlockLogicStarLamp(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
		this.setBlockBounds(MAX_LENGTH - THINNESS, START, MIN_LENGTH, MAX_LENGTH, START + HEIGHT, MAX_LENGTH);
	}

	@Override
	public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		int meta = world.getBlockData(tilePos);
		side = side.opposite();
		if (!this.isSupported(world, tilePos, side)) {
			side = this.getDefaultSide(world, tilePos);
		}

		if (side != Side.NONE) {
			world.setBlockData(tilePos, setSide(meta, side));
		} else {
			this.dropWithCause(world, EnumDropCause.WORLD, tilePos, meta, null, null);
			world.setBlockTypeNotify(tilePos, Blocks.AIR);
		}
	}

	@Override
	public @NotNull AABBdc getBoundsFromState(@NotNull WorldSource source, @NotNull TilePosc tilePos) {
		int meta = source.getBlockData(tilePos);
		AABBd aabbd;
		switch (sideFromMeta(meta)) {
			case BOTTOM -> aabbd = 	new AABBd(MIN_LENGTH, MIN_LENGTH, START, MAX_LENGTH, THINNESS, START + HEIGHT);
			case TOP -> aabbd = 	new AABBd(MIN_LENGTH, MAX_LENGTH - THINNESS, START, MAX_LENGTH, MAX_LENGTH, START + HEIGHT);
			case NORTH -> aabbd = 	new AABBd(MIN_LENGTH, START, MIN_LENGTH, MAX_LENGTH, START + HEIGHT, THINNESS);
			case SOUTH -> aabbd = 	new AABBd(MIN_LENGTH, START, MAX_LENGTH - THINNESS, MAX_LENGTH, START + HEIGHT, MAX_LENGTH);
			case WEST -> aabbd = 	new AABBd(MIN_LENGTH, START, MIN_LENGTH, THINNESS, START + HEIGHT, MAX_LENGTH);
			default -> aabbd = 		new AABBd(MAX_LENGTH - THINNESS, START, MIN_LENGTH, MAX_LENGTH, START + HEIGHT, MAX_LENGTH);
		}
		return aabbd;
	}

	@Override
	public void onNeighborChanged(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Block<?> block) {
		if (!this.isSupported(world, tilePos, sideFromMeta(world.getBlockData(tilePos)))) {
			this.dropWithCause(world, EnumDropCause.WORLD, tilePos, world.getBlockData(tilePos), null, null);
			world.setBlockTypeNotify(tilePos, Blocks.AIR);
		}

	}

	public static int setSide(int meta, @NotNull Side side) {
		return meta & -15 | side.id << 1;
	}

	public static @NotNull Side sideFromMeta(int meta) {
		return Side.fromId((meta & 14) >> 1);
	}

	private @NotNull Side getDefaultSide(@NotNull World world, @NotNull TilePosc tilePos) {
		for (int i = 0; i < Side.sides.length; ++i) {
			Side side = Side.sides[i];
			if (this.isSupported(world, tilePos, side)) {
				return side;
			}
		}

		return Side.NONE;
	}

	@Override
	public int getPistonPushReaction(@NotNull World world, @NotNull TilePosc tilePos) {
		return 1;
	}

	@Override
	public boolean canPlaceAt(@NotNull World world, @NotNull TilePosc tilePos) {
		return this.isSupported(world, tilePos, Side.BOTTOM)
			|| this.isSupported(world, tilePos, Side.TOP)
			|| this.isSupported(world, tilePos, Side.NORTH)
			|| this.isSupported(world, tilePos, Side.SOUTH)
			|| this.isSupported(world, tilePos, Side.WEST)
			|| this.isSupported(world, tilePos, Side.EAST);
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public boolean isCubeShaped() {
		return false;
	}


	@Override
	public @NotNull ISupport getSupport(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side) {
		return PartialSupport.INSTANCE;
	}

	@Override
	public @NotNull ISupport getSupportConstraint(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side) {
		return PartialSupport.INSTANCE.center();
	}

}
