package useless.moonsteel.mixin.starfallen;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.type.WorldType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.MoonSteelItems;
import useless.moonsteel.interfaces.IFallenStar;

import java.util.Random;

@Mixin(value = World.class, remap = false)
public abstract class WorldMixinStarFall {

	@Shadow
	protected int updateLCG;

	@Shadow
	public Random rand;

	@Shadow
	public abstract EntityItem dropItem(int x, int y, int z, ItemStack itemStack);

	@Shadow
	public abstract @NotNull WorldType getWorldType();

	@Inject(method = "updateBlocksAndPlayCaveSounds()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;getChunkFromChunkCoords(II)Lnet/minecraft/core/world/chunk/Chunk;", shift = At.Shift.AFTER))
	private void makeTheStarsFall(
		final CallbackInfo ci,
		@Local(name = "chunkBlockX") int chunkBlockX,
		@Local(name = "chunkBlockZ") int chunkBlockZ
	){
		if (!MoonSteel.isStarTime((World) (Object)this)) return;
		if (this.rand.nextInt(MoonSteel.STAR_SPAWN_RATE) == 0){
			this.updateLCG = this.updateLCG * 3 + 1013904223;
			final int randVal = this.updateLCG >> 2;
			final int blockX = chunkBlockX + (randVal & 0xF);
			final int blockZ = chunkBlockZ + (randVal / 256 & 0xF);
			((IFallenStar)dropItem(blockX, this.getWorldType().getMaxY((WorldSource) this) + 32, blockZ, MoonSteelItems.STAR_FALLEN.getDefaultStack()))
				.moonsteel$setDaylightSensitive(true);
		}
	}
}
