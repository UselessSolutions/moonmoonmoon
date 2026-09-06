package useless.moonsteel.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.world.chunk.provider.ChunkProviderStatic;
import net.minecraft.core.world.pos.ChunkPosc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import useless.moonsteel.MoonSteel;

@Mixin(value = ChunkProviderStatic.class, remap = false)
public class ChunkProviderStaticMixin {

	@WrapMethod(method = "canChunkExist")
	private boolean overrideChunk(ChunkPosc chunkPos, Operation<Boolean> original){
		return MoonSteel.forceChunkLoads;
	}
}
