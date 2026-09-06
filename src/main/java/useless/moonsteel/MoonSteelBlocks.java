package useless.moonsteel;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicLeavesBase;
import net.minecraft.core.block.BlockLogicOreCoal;
import net.minecraft.core.block.BlockLogicOreDiamond;
import net.minecraft.core.block.BlockLogicOreGold;
import net.minecraft.core.block.BlockLogicOreIron;
import net.minecraft.core.block.BlockLogicOreLapis;
import net.minecraft.core.block.BlockLogicOreNetherCoal;
import net.minecraft.core.block.BlockLogicOreRedstone;
import net.minecraft.core.block.BlockLogicTallGrass;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import turniplabs.halplibe.helper.BlockBuilder;
import useless.moonsteel.block.BlockStellarRewinder;
import useless.moonsteel.block.BlockTorchStar;

import static useless.moonsteel.MoonSteel.MOD_ID;

public class MoonSteelBlocks {
	public static Block<?> BLOCK_MOONSTEEL = new BlockBuilder(MOD_ID)
		.setHardness(5f)
		.setResistance(2000f)
		.addTags(BlockTags.MINEABLE_BY_PICKAXE)
		.build("block", "block_moonsteel", MoonSteel.blockId++, b -> new BlockLogic(b, Materials.METAL));

	// TODO make sure this is always is set with notify
	public static Block<?> TORCH_STAR = new BlockBuilder(MOD_ID)
		.setLuminance(15)
		.build("torch.star", "torch_star", MoonSteel.blockId++, BlockTorchStar::new);
	//		.withDisabledNeighborNotifyOnMetadataChange(); // need to be set with notify

	// TODO figure out what withImmovableFlag was
	public static Block<?> STELLAR_REWINDER = new BlockBuilder(MOD_ID)
		.setHardness(3.5f)
		.addTags(BlockTags.MINEABLE_BY_PICKAXE)
		.build("stellar.rewinder", "stellar_rewinder", MoonSteel.blockId++, b -> new BlockStellarRewinder(b, Materials.METAL));
//		.withImmovableFlagSet();


	public static Tag<Block<?>> FORCE_FORTUNE = Tag.of("moonsteel$force_enable_fortune");
	public static Tag<Block<?>> FORCE_NO_FORTUNE = Tag.of("moonsteel$force_disable_fortune");

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
		if (Block.hasLogicClass(block, BlockLogicTallGrass.class)) return true;
		return false;
	}

	public static void init() {

	}
}
