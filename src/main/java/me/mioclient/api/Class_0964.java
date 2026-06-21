package me.mioclient.api;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0482;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.module.render.ChamsModule;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface Class_0964 extends Class_1309 {
   AbstractModule_21 field_2972 = Hub.field_2595.method_78(AbstractModule_21.class);
   Color field_2973 = new Color(0, 0, 0, 0);

   default void method_2(ChamsModule var1, Entity var2, MatrixStack var3) {
      Color[] var4 = this.method_2(var1);
      if (var1.field_222.getValue()) {
         float var5 = var1.method_99(var2);
         var4 = new Color[]{
            Class_1081.method_9(var4[0], (int)(var5 * (float)var4[0].getAlpha())), Class_1081.method_9(var4[1], (int)(var5 * (float)var4[1].getAlpha()))
         };
      }

      Class_0482.method_2(var4[0], var4[1]);
      if (var1.field_247.getValue() && var2 instanceof PlayerEntity var6) {
         if (Hub.field_2603.method_30(var6)) {
            this.method_2(var1, var4, field_2972.field_2130.getValue());
         }

         if (Hub.field_2603.method_16(var6)) {
            this.method_2(var1, var4, field_2972.field_2131.getValue());
         }
      }

      Class_0482.method_16(var1.field_225.getValue());
      if (var2 instanceof PlayerEntity var7 && var1.method_103()) {
         var7.limbAnimator.pos = 0.0F;
         var7.limbAnimator.speed = 0.0F;
         var7.limbAnimator.prevSpeed = 0.0F;
      }

      Class_0482.method_2(var3, var2);
   }

   default void method_2(ChamsModule var1, Color[] var2, Color var3) {
      Class_0482.method_2(Class_1081.method_9(var3, var2[0].getAlpha()), Class_1081.method_9(var3, var2[1].getAlpha()));
   }

   default Color[] method_2(ChamsModule var1) {
      return new Color[]{var1.field_249.getValue(), var1.field_248.getValue()};
   }

   default boolean method_670() {
      return false;
   }
}
