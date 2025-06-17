package useless.moonsteel.mixin.entity;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.moonsteel.MoonSteelItems;
import useless.moonsteel.interfaces.IFallenStar;

@Mixin(value = EntityItem.class, remap = false)
public abstract class EntityItemMixin extends Entity implements IFallenStar {
	@Shadow
	public ItemStack item;
	@Unique
	public boolean despawnInDay = false;

	public EntityItemMixin(final World world) {
		super(world);
	}
	@Inject(method = "<init>(Lnet/minecraft/core/world/World;DDDLnet/minecraft/core/item/ItemStack;)V", at = @At("TAIL"))
	private void constuct1(final World world, final double d, final double d1, final double d2, final ItemStack itemstack, final CallbackInfo ci){
		if (itemstack.getItem() == MoonSteelItems.STAR_FALLEN){
			this.viewScale = 30;
			if (this.y > world.getWorldType().getMaxY()){
				world.playSoundEffect(null, SoundCategory.WEATHER_SOUNDS, (float) this.x, world.findTopSolidBlock((int) this.x, (int) this.z) + 10, (float) this.z,  "moonsteel:starspawn", 5, 1f + this.random.nextFloat() * 0.1f);
			}
		}
	}
	@Inject(method = "tick()V", at = @At("TAIL"))
	private void tick(final CallbackInfo ci){
		if (this.despawnInDay && this.world.isDaytime()){
			this.remove();
		}
	}

	@Override
	public void moonsteel$setDaylightSensitive(final boolean flag) {
		this.despawnInDay = flag;
	}
	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void saveData(final CompoundTag tag, final CallbackInfo ci){
		tag.putBoolean("moonsteel$daydespawn", this.despawnInDay);
	}
	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void loadData(final CompoundTag tag, final CallbackInfo ci){
		this.despawnInDay = tag.getBoolean("moonsteel$daydespawn");
		if (this.item.getItem() == MoonSteelItems.STAR_FALLEN){
			this.viewScale = 5;
		}
	}
}
