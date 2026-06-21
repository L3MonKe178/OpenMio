package me.mioclient.internal;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;

public class Class_0356 {
   public static final Potion field_1158 = new Potion(new StatusEffectInstance[0]);

   public Class_0356() {
      super();
   }

   public static Potion method_78(ItemStack var0) {
      PotionContentsComponent var1 = (PotionContentsComponent)var0.get(DataComponentTypes.POTION_CONTENTS);
      if (var1 == null) {
         return field_1158;
      } else {
         RegistryEntry var2 = (RegistryEntry)var1.potion().orElse(null);
         return var2 == null ? field_1158 : (Potion)var2.value();
      }
   }
}
