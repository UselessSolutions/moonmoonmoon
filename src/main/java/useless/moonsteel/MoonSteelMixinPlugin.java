package useless.moonsteel;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MoonSteelMixinPlugin implements IMixinConfigPlugin {
	// TODO make Intelij happy
	private static final Supplier<Boolean> BACKPACKS = () -> FabricLoader.getInstance().isModLoaded("betterwithbackpacks");

	@Override
	public void onLoad(String mixinPackage) {
		/* not need */
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (mixinClassName.startsWith("useless.moonsteel.mixin.backpack")) return BACKPACKS.get();
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		/* not need */
	}

	@Override
	public List<String> getMixins() {
		return Collections.emptyList();
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		/* not need */
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		/* not need */
	}
}
