package useless.moonsteel.item;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.block.TileEntityStellarRewinder;
import useless.moonsteel.interfaces.ITeleporter;

public class ItemConnectedStar extends Item {
    public ItemConnectedStar(String name, String namespaceId, int id) {
        super(name, namespaceId, id);
    }

    @Override
    public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
        if (itemstack.getData().getBoolean("moonsteel$has_location")) {
            int destX = itemstack.getData().getInteger("moonsteel$x");
            int destY = itemstack.getData().getInteger("moonsteel$y");
            int destZ = itemstack.getData().getInteger("moonsteel$z");
            int dim = itemstack.getData().getInteger("moonsteel$dimension");
            if (dim != world.dimension.id) {
                entityplayer.sendTranslatedChatMessage("moonsteel.teleport.fail.dimension");
                return itemstack;
            }
            int cost = MathHelper.floor(entityplayer.distanceTo(destX, destY, destZ));
            if (entityplayer.score < cost) {
                entityplayer.sendTranslatedChatMessage("moonsteel.teleport.fail.score");
                return itemstack;
            }
            MoonSteel.forceChunkLoads = true;
            Chunk chunk = world.getChunkProvider().provideChunk(destX >> 4, destZ >> 4);
            MoonSteel.forceChunkLoads = false;
            TileEntity te = chunk.getTileEntity(destX & 0xF, destY, destZ & 0xF);
            if (te instanceof TileEntityStellarRewinder && ((TileEntityStellarRewinder) te).canTeleport(itemstack)) {
                entityplayer.score -= cost;
                Side side = ((TileEntityStellarRewinder) te).side;
                ((ITeleporter) entityplayer).moonsteel$teleport(destX + side.getOffsetX() + 0.5f, destY + side.getOffsetY(), destZ + side.getOffsetZ() + 0.5f);
                ((TileEntityStellarRewinder) te).setInUse(false);
            } else if (!world.isClientSide) {
                entityplayer.sendTranslatedChatMessage("moonsteel.teleport.fail.missing");
            }
            itemstack.getData().putBoolean("moonsteel$has_location", false);
        }
        return itemstack;
    }
}
