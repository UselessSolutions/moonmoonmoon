package useless.moonsteel.mixin.backpack;

import net.minecraft.core.net.packet.PacketContainerOpen;
import net.minecraft.server.entity.player.PlayerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import useless.moonsteel.ContainerStarBackpack;
import useless.moonsteel.MoonSteel;

@Mixin(value = PlayerServer.class, remap = false)
public abstract class PlayerServerMixin extends PlayerMixin {
	@Shadow
	protected abstract void getNextWindowId();

	@Shadow
	private int currentWindowId;
	@Unique
	public PlayerServer thisAs = (PlayerServer) (Object)this;

	@Override
	public void moonsteel$displayGuiStarBackpack() {
		this.getNextWindowId();
		ContainerStarBackpack backpack = new ContainerStarBackpack(thisAs);
		this.thisAs
			.playerNetServerHandler
			.sendPacket(
				new PacketContainerOpen(this.currentWindowId, MoonSteel.GUI_ID, "moonsteel$StarBackpack", backpack.backpackInventory.getContainerSize())
			);
		this.thisAs.craftingInventory = backpack;
		this.thisAs.craftingInventory.containerId = this.currentWindowId;
		this.thisAs.craftingInventory.addSlotListener(this.thisAs);
	}
}
