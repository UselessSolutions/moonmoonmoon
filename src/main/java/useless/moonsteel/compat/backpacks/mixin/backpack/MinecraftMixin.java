package useless.moonsteel.compat.backpacks.mixin.backpack;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.compat.backpacks.interfaces.IStarBackpack;

@Environment(EnvType.CLIENT)
@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
    @Shadow
    public PlayerLocal thePlayer;

    @Inject(method = "respawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/player/PlayerLocal;setGamemode(Lnet/minecraft/core/player/gamemode/Gamemode;)V", shift = At.Shift.AFTER))
    public void keepBackpack(boolean flag, int i, CallbackInfo ci, @Local Player previousPlayer) {
        ((IStarBackpack) thePlayer).moonsteel$setStarBackpackInventory(((IStarBackpack) previousPlayer).moonsteel$getStarBackpackInventory());
    }
}
