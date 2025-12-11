package useless.moonsteel.compat.backpacks.mixin.backpack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import useless.moonsteel.compat.backpacks.GuiStarBackpack;

@Environment(EnvType.CLIENT)
@Mixin(value = PlayerLocal.class, remap = false)
public class PlayerLocalMixin extends PlayerMixin {
    @Shadow
    protected Minecraft mc;
    @Unique
    public PlayerLocal thisAs = (PlayerLocal) (Object) this;

    public PlayerLocalMixin(@Nullable World world) {
        super(world);
    }

    @Override
    public void moonsteel$displayGuiStarBackpack() {
        this.mc.displayScreen(new GuiStarBackpack(this.thisAs));
    }
}
