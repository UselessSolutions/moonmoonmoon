package useless.moonsteel.mixin.backpack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import useless.moonsteel.backpack.GuiStarBackpack;
import useless.moonsteel.interfaces.IStarBackpack;
import useless.moonsteel.interfaces.ITeleporter;

@Mixin(value = PlayerLocal.class, remap = false)
public class PlayerLocalMixin extends PlayerMixin implements ITeleporter {
	@Shadow
	protected Minecraft mc;
	@Unique
	public PlayerLocal thisAs = (PlayerLocal) (Object)this;

	public PlayerLocalMixin(@Nullable World world) {
		super(world);
	}

	@Override
	public void moonsteel$displayGuiStarBackpack() {
		this.mc.displayScreen(new GuiStarBackpack(this.thisAs));
	}


	@Override
	public void moonsteel$teleport(final double x, final double y, final double z) {
		setPos(x, y + this.bbHeight, z);
	}
}
