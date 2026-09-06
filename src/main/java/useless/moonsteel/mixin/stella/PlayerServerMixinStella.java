package useless.moonsteel.mixin.stella;


import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.handler.PacketHandlerServer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerServer.class)
public class PlayerServerMixinStella extends PlayerMixinStella {
	@Shadow
	public PacketHandlerServer playerNetServerHandler;

	public PlayerServerMixinStella(@NotNull World world) {
		super(world);
	}

	@Override
	public void moonsteel$teleport(final double x, final double y, final double z) {
		this.playerNetServerHandler.teleport(x, y, z);
	}
}
