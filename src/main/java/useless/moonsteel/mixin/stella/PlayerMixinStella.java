package useless.moonsteel.mixin.stella;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import useless.moonsteel.interfaces.ITeleporter;

@Mixin(Player.class)
public class PlayerMixinStella extends Mob implements ITeleporter {

	public PlayerMixinStella(@NotNull World world) {
		super(world);
	}

	@Override
	public void moonsteel$teleport(final double x, final double y, final double z) {
		setPos(x, y + this.bbHeight, z);
	}

}
