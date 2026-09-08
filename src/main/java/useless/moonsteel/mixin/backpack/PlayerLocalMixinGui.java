package useless.moonsteel.mixin.backpack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import useless.moonsteel.backpack.GuiStarBackpack;

@Mixin(value = PlayerLocal.class, remap = false)
public class PlayerLocalMixinGui extends PlayerMixin {
	@Shadow
	protected Minecraft mc;
	@Unique
	public PlayerLocal thisAs = (PlayerLocal) (Object)this;

	public PlayerLocalMixinGui(@Nullable World world) {
		super(world);
	}

	@Override
	public void moonsteel$displayGuiStarBackpack() {
		this.mc.displayScreen(new GuiStarBackpack(this.thisAs));
	}
}
