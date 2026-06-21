package me.mioclient.enum_;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0396;
import me.mioclient.module.combat.AutoCrystalModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;

public enum Class_0247 implements Class_1309 {
   NONE(false),
   POP(true),
   KILL(true);

   public static AutoCrystalModule field_219 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public final boolean field_696;

    Class_0247(boolean var3) {
      this.field_696 = var3;
   }

   public static Class_0247 method_2(LivingEntity var0, double var1) {
      boolean var3 = var1 >= (double)Class_0396.method_2((Entity)var0);
      boolean var4 = var0.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING) || var0.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING);
      if (var3) {
         return var4 ? POP : KILL;
      } else {
         return NONE;
      }
   }
}
