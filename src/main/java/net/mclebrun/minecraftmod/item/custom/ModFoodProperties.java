package net.mclebrun.minecraftmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.neoforged.neoforge.capabilities.Capabilities;

public class ModFoodProperties {
    public static final FoodProperties RADISH = new FoodProperties.Builder().nutrition(3)
            .saturationModifier(.25f).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), .1f)
            .alwaysEdible().build();
}
