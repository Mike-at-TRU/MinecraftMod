package net.mclebrun.minecraftmod.datagen;

import net.mclebrun.minecraftmod.MinecraftMod;
import net.mclebrun.minecraftmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeBlockTagsProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {


    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MinecraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.MAGIC_BLOCK.get())
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get())
                .add(ModBlocks.BISMUTH_ORE.get())

        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get());
        //.add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());


        tag(BlockTags.FENCES)
                .add(ModBlocks.BISMUTH_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BISMUTH_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.BISMUTH_WALL.get());


        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());

        tag(Tags.Blocks.NEEDS_GOLD_TOOL);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(Tags.Blocks.NEEDS_GOLD_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL);

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .remove(BlockTags.NEEDS_STONE_TOOL);

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .remove(Tags.Blocks.NEEDS_GOLD_TOOL);

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .remove(BlockTags.NEEDS_IRON_TOOL);
        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(Tags.Blocks.NEEDS_NETHERITE_TOOL);

    }

}
