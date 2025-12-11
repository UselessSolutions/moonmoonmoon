package useless.moonsteel.compat.deep;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class MoonSteelDeepCompatibility implements PreLaunchEntrypoint {

    public static boolean IS_DEEP_LOADED = false;

    @Override
    public void onPreLaunch() {
        FabricLoader loader = FabricLoader.getInstance();
        IS_DEEP_LOADED = loader.isModLoaded("deep");
    }
}
