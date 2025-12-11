package useless.moonsteel.compat.backpacks;

import net.minecraft.core.item.Item;

public class BackpackProxy {
    public static Item proxyBackpack(final String name, final String namespaceId, final int id) {
        return new ItemStarBackpack(name, namespaceId, id);
    }
}
