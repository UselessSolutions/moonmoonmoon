package useless.moonsteel.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.moonsteel.interfaces.IStarBackpack;

@Mixin(value = PlayerList.class, remap = false)
public class PlayerListMixin {
    @Inject(method = "recreatePlayerEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/container/ContainerInventory;transferAllContents(Lnet/minecraft/core/player/inventory/container/ContainerInventory;)V", shift = At.Shift.AFTER))
    public void keepBackpackMP(final PlayerServer previousPlayer, final int i, final CallbackInfoReturnable<PlayerServer> cir, @Local(name = "newPlayer") final PlayerServer newPlayer) {
        ((IStarBackpack) newPlayer).moonsteel$setStarBackpackInventory(((IStarBackpack) previousPlayer).moonsteel$getStarBackpackInventory());
    }
}
