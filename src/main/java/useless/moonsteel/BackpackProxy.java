package useless.moonsteel;

import net.minecraft.core.item.Item;
import useless.moonsteel.item.ItemStarBackpack;

public class BackpackProxy {
    public static Item proxyBackpack(final String name, final String namespaceId, final int id) {
        return new ItemStarBackpack(name, namespaceId, id);
    }
}
