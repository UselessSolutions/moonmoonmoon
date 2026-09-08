package useless.moonsteel;

import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.sound.BlockSounds;
import useless.moonsteel.block.BlockLogicStarLamp;
import useless.moonsteel.block.BlockStellarRewinder;
import useless.moonsteel.block.BlockTorchStar;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelBlocks {

	private MoonSteelBlocks(){}

	private static String formatTranslationKey(String key) {
		return String.format("%s.%s", MOD_ID, key);
	}

	private static String formatName(String name) {
		return String.format("%s:block/%s", MOD_ID, name);
	}

	public static final Block<?> BLOCK_MOONSTEEL =
		Blocks.register(
			formatTranslationKey("block"),
			formatName("block_moonsteel"),
			MoonSteel.blockId++,
			b -> new BlockLogic(b, Materials.METAL)
		)
		.withHardness(5f)
		.withBlastResistance(2000f)
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block<?> TORCH_STAR =
		Blocks.register(
			formatTranslationKey("torch.star"),
			formatName("torch_star"),
			MoonSteel.blockId++,
			BlockTorchStar::new
		)
		.withLightEmission(15);

	public static final Block<?> STELLAR_REWINDER =
		Blocks.register(
			formatTranslationKey("stellar.rewinder"),
			formatName("stellar_rewinder"),
			MoonSteel.blockId++,
			b -> new BlockStellarRewinder(b, Materials.METAL)
		)
		.withHardness(3.5f)
		.withTags(BlockTags.MINEABLE_BY_PICKAXE);

	public static final Block<?> STAR_LAMP =
		Blocks.register(
			formatTranslationKey("star.lamp"),
			formatName("star_lamp"),
			MoonSteel.blockId++,
			b -> new BlockLogicStarLamp(b, Materials.METAL)
		)
			.withSound(BlockSounds.GLASS)
			.withHardness(0.5F)
			.withLightEmission(15)
			.withTags(new Tag[]{BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE});

	public static final Tag<Block<?>> FORCE_FORTUNE = Tag.of("moonsteel$force_enable_fortune");
	public static final Tag<Block<?>> FORCE_NO_FORTUNE = Tag.of("moonsteel$force_disable_fortune");

	public static boolean canBeFortuned(Block<?> block) {
		if (block.hasTag(FORCE_FORTUNE)) return true;
		if (block.hasTag(FORCE_NO_FORTUNE)) return false;
		if (Block.hasLogicClass(block, BlockLogicLeavesBase.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreCoal.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreDiamond.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreGold.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreIron.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreLapis.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreNetherCoal.class)) return true;
		if (Block.hasLogicClass(block, BlockLogicOreRedstone.class)) return true;
		return Block.hasLogicClass(block, BlockLogicTallGrass.class);
	}

	public static void init() {
		/* no need */
	}
}
