package net.mclebrun.minecraftmod.datagen;

import net.mclebrun.minecraftmod.block.ModBlocks;
import net.mclebrun.minecraftmod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());
        add(ModBlocks.BISMUTH_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.BISMUTH_BLOCK.get(), ModItems.RAW_BISMUTH.get(), 1f, 1f));
        add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.BISMUTH_DEEPSLATE_ORE.get(), ModItems.RAW_BISMUTH.get(), 2f, 5f));

        dropSelf(ModBlocks.BISMUTH_STAIRS.get());
        add(ModBlocks.BISMUTH_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BISMUTH_SLAB.get()));

        dropSelf(ModBlocks.BISMUTH_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BISMUTH_BUTTON.get());

        dropSelf(ModBlocks.BISMUTH_FENCE.get());
        dropSelf(ModBlocks.BISMUTH_FENCE_GATE.get());
        dropSelf(ModBlocks.BISMUTH_WALL.get());
        dropSelf(ModBlocks.BISMUTH_TRAP_DOOR.get());

        add(ModBlocks.BISMUTH_DOOR.get(),
                block -> createDoorTable(ModBlocks.BISMUTH_DOOR.get()));
    }

    @Override
    //because this is all the blocks all will need a loot table
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item itemToBeDroppedWithoutSilkTouch, float minimumNumberOfItemsToBeDropped, float maximumNumberOfItemsToBeDropped) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(itemToBeDroppedWithoutSilkTouch)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minimumNumberOfItemsToBeDropped, maximumNumberOfItemsToBeDropped)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
}
