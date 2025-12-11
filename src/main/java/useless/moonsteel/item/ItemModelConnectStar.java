package useless.moonsteel.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import useless.moonsteel.MoonSteel;

@Environment(EnvType.CLIENT)
public class ItemModelConnectStar extends ItemModelStandard {
    private static final IconCoordinate star_connected = TextureRegistry.getTexture(MoonSteel.MOD_ID + ":item/connected_star");

    public ItemModelConnectStar(final Item item, final String namespace) {
        super(item, namespace);
    }

    @NotNull
    @Override
    public IconCoordinate getIcon(@Nullable final Entity entity, final ItemStack itemStack) {
        if (itemStack.getData().getBoolean("moonsteel$has_location")) {
            return star_connected;
        }
        return super.getIcon(entity, itemStack);
    }
}
