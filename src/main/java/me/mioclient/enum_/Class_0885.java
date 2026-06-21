package me.mioclient.enum_;

import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0756;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

public enum Class_0885 implements Class_0013 {
   NONE("None", var0 -> false),
   BREACH("Breach", var0 -> Class_0756.method_9(Enchantments.BREACH, var0)),
   DENSITY("Density", var0 -> Class_0756.method_9(Enchantments.DENSITY, var0) && Class_1309.field_4219.player.fallDistance > 3.0F),
   SMART(
      "Smart",
      var0 -> !(Class_1309.field_4219.player.fallDistance > 3.0F) && Hub.field_2615.method_1161() > 1
            ? Class_0756.method_9(Enchantments.BREACH, var0)
            : Class_0756.method_9(Enchantments.DENSITY, var0)
   );

   public final Predicate<ItemStack> field_2804;
   public final String field_2805;

    Class_0885(String var3, Predicate<ItemStack> var4) {
      this.field_2804 = var4;
      this.field_2805 = var3;
   }

   @Override
   public String getName() {
      return this.field_2805;
   }

   public boolean method_16(ItemStack var1) {
      return this.field_2804.test(var1);
   }
}
