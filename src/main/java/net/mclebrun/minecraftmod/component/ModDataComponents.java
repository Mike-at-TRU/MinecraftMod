package net.mclebrun.minecraftmod.component;

import net.mclebrun.minecraftmod.MinecraftMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MinecraftMod.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
            builder -> builder.persistent(BlockPos.CODEC)); //the second type needs to have a codec


    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register((eventBus));
    }
}
