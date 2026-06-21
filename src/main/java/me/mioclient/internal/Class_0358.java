package me.mioclient.internal;

import net.minecraft.block.BedBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;

public final class Class_0358 {
   public Class_0358() {
      super();
      throw new AssertionError();
   }

   public static boolean method_29(Item var0) {
      if (var0 instanceof BlockItem var1 && var1.getBlock() instanceof ShulkerBoxBlock) {
         return true;
      }

      return false;
   }

   public static boolean method_4(Item var0) {
      if (var0 instanceof BlockItem var1 && var1.getBlock() instanceof BedBlock) {
         return true;
      }

      return false;
   }

   public static boolean method_2(ItemStack var0, RegistryEntry<StatusEffect> var1) {
      for (StatusEffectInstance var3 : Class_0356.method_78(var0).getEffects()) {
         if (var3.getEffectType() == var1) {
            return true;
         }
      }

      return false;
   }
}
