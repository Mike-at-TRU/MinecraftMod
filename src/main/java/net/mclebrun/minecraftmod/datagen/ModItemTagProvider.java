package net.mclebrun.minecraftmod.datagen;

import net.mclebrun.minecraftmod.MinecraftMod;
import net.mclebrun.minecraftmod.item.ModItems;
import net.mclebrun.minecraftmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeFluidTagsProvider;
import net.neoforged.neoforge.common.data.internal.NeoForgeItemTagsProvider;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MinecraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.BISMUTH.get())
                .add(ModItems.RAW_BISMUTH.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

        tag(ItemTags.SWORDS)
                .add(ModItems.BISMUTH_SWORD.get());

        tag(ItemTags.AXES)
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.BISMUTH_PICKAXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.BISMUTH_HOE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.BISMUTH_SHOVEL.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.BISMUTH_HELMET.get())
                .add(ModItems.BISMUTH_CHESTPLATE.get())
                .add(ModItems.BISMUTH_LEGGINGS.get())
                .add(ModItems.BISMUTH_BOOTS.get());

        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.BISMUTH.get());

        tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.MICHAEL_SMITHING_TEMPLATE.get());

    }


}
