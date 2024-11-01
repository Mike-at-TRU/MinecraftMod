package net.mclebrun.minecraftmod.datagen;

import net.mclebrun.minecraftmod.MinecraftMod;
import net.mclebrun.minecraftmod.block.ModBlocks;
import net.mclebrun.minecraftmod.block.custom.BismuthLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.FenceBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MinecraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BISMUTH_BLOCK);
        blockWithItem(ModBlocks.BISMUTH_ORE);
        blockWithItem(ModBlocks.BISMUTH_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.MAGIC_BLOCK);

        stairsBlock(ModBlocks.BISMUTH_STAIRS.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        slabBlock(ModBlocks.BISMUTH_SLAB.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        buttonBlock(ModBlocks.BISMUTH_BUTTON.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        pressurePlateBlock(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        fenceBlock(ModBlocks.BISMUTH_FENCE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        fenceGateBlock(ModBlocks.BISMUTH_FENCE_GATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        wallBlock(ModBlocks.BISMUTH_WALL.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));


        doorBlockWithRenderType(ModBlocks.BISMUTH_DOOR.get(), modLoc("block/bismuth_door_bottom"), modLoc("block/bismuth_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.BISMUTH_TRAP_DOOR.get(), modLoc("block/bismuth_trapdoor"), true, "cutout");

        blockItem(ModBlocks.BISMUTH_STAIRS);
        blockItem(ModBlocks.BISMUTH_SLAB);
        blockItem(ModBlocks.BISMUTH_PRESSURE_PLATE);
        blockItem(ModBlocks.BISMUTH_FENCE_GATE);
        blockItem(ModBlocks.BISMUTH_TRAP_DOOR, "_bottom");
        customOnOffBlock(ModBlocks.BISMUTH_LAMP);


    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }


    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("michaelsawesometestmod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("michaelsawesometestmod:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void customOnOffBlock(DeferredBlock<?> block) {
        String appendexOne = "_on";
        String appendexTwo = "_off";
        getVariantBuilder(block.get()).forAllStates(state -> {
            if (state.getValue(BismuthLampBlock.IS_ON)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll(block.getId().getPath() + appendexOne,
                        ResourceLocation.fromNamespaceAndPath(MinecraftMod.MOD_ID, "block/" + block.getId().getPath() + appendexOne)))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll(block.getId().getPath() + appendexTwo,
                        ResourceLocation.fromNamespaceAndPath(MinecraftMod.MOD_ID, "block/" + block.getId().getPath() + appendexTwo)))};
            }

        });
        simpleBlockItem(block.get(), models().cubeAll(block.getId().getPath() + appendexOne,
                ResourceLocation.fromNamespaceAndPath(MinecraftMod.MOD_ID, "block/" + block.getId().getPath() + appendexOne))

        );
    }
}
