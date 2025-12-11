package useless.moonsteel.compat.backpacks.mixin.backpack;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.compat.backpacks.StarBackpackInventory;
import useless.moonsteel.compat.backpacks.interfaces.IStarBackpack;
import useless.moonsteel.interfaces.ITeleporter;

@Mixin(value = Player.class, remap = false)
public abstract class PlayerMixin extends Mob implements IStarBackpack, ITeleporter {
    @Unique
    public StarBackpackInventory starBackpackInventory;

    protected PlayerMixin(@Nullable final World world) {
        super(world);
    }

    @Inject(method = "<init>(Lnet/minecraft/core/world/World;)V", at = @At("TAIL"))
    private void createBackpack(final World world, final CallbackInfo ci) {
        this.starBackpackInventory = new StarBackpackInventory((Player) (Object) this);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addData(final CompoundTag tag, final CallbackInfo ci) {
        tag.put("moonsteel$InventoryStardust", this.starBackpackInventory.writeToNBT(new ListTag()));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void loadData(final CompoundTag tag, final CallbackInfo ci) {
        this.starBackpackInventory.readFromNBT(tag.getList("moonsteel$InventoryStardust"));
    }

    @Override
    public StarBackpackInventory moonsteel$getStarBackpackInventory() {
        return this.starBackpackInventory;
    }

    @Override
    public void moonsteel$setStarBackpackInventory(final StarBackpackInventory backpackInventory) {
        this.starBackpackInventory = backpackInventory;
    }

    @Override
    public void moonsteel$displayGuiStarBackpack() {

    }

    @Override
    public void moonsteel$teleport(final double x, final double y, final double z) {
        setPos(x, y + this.bbHeight, z);
    }
}
