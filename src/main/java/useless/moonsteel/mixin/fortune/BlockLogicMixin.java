package useless.moonsteel.mixin.fortune;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemTool;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.MoonSteelBlocks;
import useless.moonsteel.MoonSteelItems;

@Mixin(value = BlockLogic.class,remap = false)
public abstract class BlockLogicMixin {

	@Shadow
	@Final
	@NotNull
	public Block<?> block;

	@Shadow
	public abstract void dropWithCause(World world, EnumDropCause cause, TilePosc tilePosc, int meta, TileEntity tileEntity, Player player);

	@Inject(method = "onHarvest", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/BlockLogic;dropWithCause(Lnet/minecraft/core/world/World;Lnet/minecraft/core/enums/EnumDropCause;Lnet/minecraft/core/world/pos/TilePosc;ILnet/minecraft/core/block/entity/TileEntity;Lnet/minecraft/core/entity/player/Player;)V"))
	private void multiplyHarvest(World world, Player entityplayer, TilePosc tilePos, int data, TileEntity tileEntity, CallbackInfo ci){
		final ItemStack heldItemStack = entityplayer.inventory.getCurrentItem();
		if (heldItemStack != null && heldItemStack.getItem() instanceof ItemTool itemTool && itemTool.getMaterial() == MoonSteelItems.MOON_STEEL_TOOL && MoonSteelBlocks.canBeFortuned(this.block)){
			for (int i = 0; i < world.rand.nextInt(MoonSteel.FORTUNE_AMOUNT); i++) {
				dropWithCause(world, EnumDropCause.PROPER_TOOL, tilePos, data, tileEntity, entityplayer);
			}
		}
	}
}
