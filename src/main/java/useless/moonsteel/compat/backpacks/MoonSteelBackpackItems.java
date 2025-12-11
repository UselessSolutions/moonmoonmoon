package useless.moonsteel.compat.backpacks;

import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;
import useless.moonsteel.MoonSteel;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelBackpackItems {
    public static Item BACKPACK_COSMIC;

    private static boolean hasInit = false;

    public static void init() {
        if (!hasInit) {
            hasInit = true;
            initializeItems();
        }
    }

    public static void initializeItems() {
        BACKPACK_COSMIC = new ItemBuilder(MOD_ID)
            .setStackSize(1)
            .build(BackpackProxy.proxyBackpack("backpack.cosmic", MOD_ID + ":item/backpack_cosmic", MoonSteel.itemId++));
    }
}
