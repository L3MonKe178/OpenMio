package me.mioclient.internal;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import me.mioclient.api.Class_1309;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;

public final class Class_0756 implements Class_1309 {
   public Class_0756() {
      super();
      throw new IllegalArgumentException("Illegal constructor call");
   }

   public static int method_2(RegistryKey<Enchantment> var0, ItemStack var1) {
      if (var1.isEmpty()) {
         return 0;
      } else {
         for (Entry var3 : var1.getEnchantments().getEnchantmentEntries()) {
            if (((RegistryEntry)var3.getKey()).matchesKey(var0)) {
               return var3.getIntValue();
            }
         }

         return 0;
      }
   }

   public static int method_2(RegistryKey<Enchantment> var0, EquipmentSlot var1, LivingEntity var2) {
      return method_2(var0, var2.getEquippedStack(var1));
   }

   public static int method_2(RegistryKey<Enchantment> var0, EquipmentSlot var1) {
      return method_2(var0, var1, field_4219.player);
   }

   public static boolean method_9(RegistryKey<Enchantment> var0, ItemStack var1) {
      return method_2(var0, var1) > 0;
   }

   public static boolean method_9(RegistryKey<Enchantment> var0, EquipmentSlot var1, LivingEntity var2) {
      return method_2(var0, var1, var2) > 0;
   }

   public static boolean method_9(RegistryKey<Enchantment> var0, EquipmentSlot var1) {
      return method_2(var0, var1) > 0;
   }

   public static Map<RegistryKey<Enchantment>, Integer> method_7(ItemStack var0) {
      if (var0.isEmpty()) {
         return Collections.emptyMap();
      } else {
         HashMap var1 = new HashMap();

         for (Entry var3 : var0.getEnchantments().getEnchantmentEntries()) {
            Optional var4 = ((RegistryEntry)var3.getKey()).getKey();
            if (!var4.isEmpty()) {
               var1.put((RegistryKey)var4.get(), var3.getIntValue());
            }
         }

         return var1;
      }
   }

   public static RegistryEntry<Enchantment> method_2(RegistryKey<Enchantment> var0) {
      return (RegistryEntry<Enchantment>)field_4219.world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(var0).orElse(null);
   }
}
