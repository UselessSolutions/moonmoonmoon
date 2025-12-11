package useless.moonsteel;

import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.data.tag.Tag;
import silveon22.deep.block.ore.*;
import turniplabs.halplibe.helper.BlockBuilder;
import useless.moonsteel.block.BlockStellarRewinder;
import useless.moonsteel.block.BlockTorchStar;

import static useless.moonsteel.MoonSteel.MOD_ID;
import static useless.moonsteel.compat.deep.MoonSteelDeepCompatibility.IS_DEEP_LOADED;

public class MoonSteelBlocks {
    public static Block<?> BLOCK_MOONSTEEL = new BlockBuilder(MOD_ID)
        .setHardness(5f)
        .setResistance(2000f)
        .addTags(BlockTags.MINEABLE_BY_PICKAXE)
        .build("block", "block_moonsteel", MoonSteel.blockId++, b -> new BlockLogic(b, Material.metal));
    public static Block<?> TORCH_STAR = new BlockBuilder(MOD_ID)
        .setLuminance(15)
        .build("torch.star", "torch_star", MoonSteel.blockId++, BlockTorchStar::new)
        .withDisabledNeighborNotifyOnMetadataChange();
    public static Block<?> STELLAR_REWINDER = new BlockBuilder(MOD_ID)
        .setHardness(3.5f)
        .addTags(BlockTags.MINEABLE_BY_PICKAXE)
        .build("stellar.rewinder", "stellar_rewinder", MoonSteel.blockId++, b -> new BlockStellarRewinder(b, Material.metal))
        .withImmovableFlagSet();


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
        if (IS_DEEP_LOADED) {
            if (Block.hasLogicClass(block, BlockLogicAmethystOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicBismuthOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicLeadOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicMagnetOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicRhodoniteOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicSilverOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicTopazOre.class)) return true;
            if (Block.hasLogicClass(block, BlockLogicUraniumOre.class)) return true;
        }
        return Block.hasLogicClass(block, BlockLogicTallGrass.class);
    }

    public static void init() {

    }
}
