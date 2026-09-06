package useless.moonsteel.item;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.pos.ChunkPos;
import net.minecraft.core.world.pos.ChunkTilePos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.block.TileEntityStellarRewinder;
import useless.moonsteel.interfaces.ITeleporter;

public class ItemConnectedStar extends Item {
	public ItemConnectedStar(String name, String namespaceId, int id) {
		super(name, namespaceId, id);
	}

	@Override
	public @Nullable ItemStack onUse(@NotNull ItemStack itemstack, @NotNull World world, @NotNull Player entityplayer) {
		if (itemstack.getData().getBoolean("moonsteel$has_location")){
			int destX = itemstack.getData().getInteger("moonsteel$x");
			int destY = itemstack.getData().getInteger("moonsteel$y");
			int destZ = itemstack.getData().getInteger("moonsteel$z");
			int dim = itemstack.getData().getInteger("moonsteel$dimension");
			if (dim != world.dimension.id) {
				entityplayer.sendMessageTranslated("moonsteel.teleport.fail.dimension");
				return itemstack;
			}
			int cost = MathHelper.floor(entityplayer.distanceTo(destX, destY, destZ));
			if (entityplayer.score < cost) {
				entityplayer.sendMessageTranslated("moonsteel.teleport.fail.score");
				return itemstack;
			}
			MoonSteel.forceChunkLoads = true;
			Chunk chunk = world.getChunkProvider().provideChunk(new ChunkPos(destX, destZ), true);
			MoonSteel.forceChunkLoads = false;
			TileEntity te = chunk.getTileEntity(new ChunkTilePos(destX &0xF, destY, destZ &0xF));
			if (te instanceof TileEntityStellarRewinder && ((TileEntityStellarRewinder) te).canTeleport(itemstack)){
				entityplayer.score -= cost;
				Side side = ((TileEntityStellarRewinder) te).side();
				((ITeleporter) entityplayer).moonsteel$teleport(destX + side.offsetX() + 0.5f, destY + side.offsetY(), destZ + side.offsetZ() + 0.5f);
				((TileEntityStellarRewinder) te).setInUse(false);
			} else if (!world.isClientSide) {
				entityplayer.sendMessageTranslated("moonsteel.teleport.fail.missing");
			}
			itemstack.getData().putBoolean("moonsteel$has_location", false);
		}
		return itemstack;
	}
}
