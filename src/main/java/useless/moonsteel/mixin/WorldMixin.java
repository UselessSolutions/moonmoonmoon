package useless.moonsteel.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.chunk.ChunkCoordinate;
import net.minecraft.core.world.type.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.MoonSteelItems;
import useless.moonsteel.interfaces.IFallenStar;

import java.util.Iterator;
import java.util.Random;

@Mixin(value = World.class, remap = false)
public abstract class WorldMixin {
	@Shadow
	public abstract Chunk getChunkFromChunkCoords(int x, int z);

	@Shadow
	public WorldType worldType;

	@Shadow
	protected int updateLCG;

	@Shadow
	public abstract EntityItem dropItem(int x, int y, int z, ItemStack itemStack);

	@Shadow
	public Random rand;
	@Inject(method = "updateBlocksAndPlayCaveSounds()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;getChunkFromChunkCoords(II)Lnet/minecraft/core/world/chunk/Chunk;", shift = At.Shift.AFTER))
	private void makeTheStarsFall(final CallbackInfo ci, @Local(name = "coordinate") final ChunkCoordinate coordinate){
		if (!MoonSteel.isStarTime((World) (Object)this)) return;
		final Chunk chunk = this.getChunkFromChunkCoords(coordinate.x, coordinate.z);
		if (this.rand.nextInt(MoonSteel.STAR_SPAWN_RATE) == 0){
			this.updateLCG = this.updateLCG * 3 + 1013904223;
			final int randVal = this.updateLCG >> 2;
			final int blockX = chunk.xPosition * 16 + (randVal & 0xF);
			final int blockZ = chunk.zPosition * 16 + (randVal / 256 & 0xF);

			((IFallenStar)dropItem(blockX, this.worldType.getMaxY() + 32, blockZ, MoonSteelItems.STAR_FALLEN.getDefaultStack())).moonsteel$setDaylightSensitive(true);
		}
	}
}
