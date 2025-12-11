package useless.moonsteel.compat.backpacks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.PacketHandlerClient;
import net.minecraft.core.net.packet.PacketContainerOpen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.MoonSteel;
import useless.moonsteel.compat.backpacks.interfaces.IStarBackpack;

@Environment(EnvType.CLIENT)
@Mixin(value = PacketHandlerClient.class, remap = false)
public abstract class PacketHandlerClientMixin {
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method = "handleOpenWindow", at = @At("TAIL"))
    public void inject(final PacketContainerOpen packetContainerOpen, final CallbackInfo ci) {
        if (packetContainerOpen.inventoryType == MoonSteel.GUI_ID) {
            ((IStarBackpack) (this.mc.thePlayer)).moonsteel$displayGuiStarBackpack();
            this.mc.thePlayer.craftingInventory.containerId = packetContainerOpen.windowId;
        }
    }
}
