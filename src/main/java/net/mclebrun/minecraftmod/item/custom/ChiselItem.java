package net.mclebrun.minecraftmod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StonecutterBlock;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.CRACKED_DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.NETHERRACK, Blocks.NETHER_BRICKS
            );

    private boolean didWork = false;

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if (!level.isClientSide()) {
            if (CHISEL_MAP.containsKey(clickedBlock)) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
                didWork = true;

            } else if (CHISEL_MAP.containsValue(clickedBlock)) {

                level.setBlockAndUpdate(context.getClickedPos(), getKeyByValue(CHISEL_MAP, clickedBlock).defaultBlockState());

            }

            if (didWork) {
                didWork = false;
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;


    }

    private Block getKeyByValue(Map<Block, Block> map, Block clickedBlock) {
        for (Map.Entry<Block, Block> entry : map.entrySet()) {
            if (Objects.equals(clickedBlock, entry.getValue())) {
                didWork = true;
                return entry.getKey();
            }
        }
        return clickedBlock;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.michaelsamazingminecraftmod.chisel.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.michaelsamazingminecraftmod.chisel"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
