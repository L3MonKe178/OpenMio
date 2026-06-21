package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0274;
import me.mioclient.module.client.HUDModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class Class_0535 implements MioAPI, Class_0937 {
   public static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   public final Class_0587<Class_0158> field_1683 = new Class_0587<>();
   public final Class_0274 field_464;

   public Class_0535(Class_0274 var1) {
      super();
      this.field_464 = var1;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      for (int var7 = 0; var7 < this.field_1683.size(); var7++) {
         Class_0158 var8 = this.field_1683.get(var7);
         var8.method_189();
         boolean var9 = var8.method_176() || var8.method_200() > 0.0F;
         float[] var10 = var8.method_31();
         if (!var9) {
            var10 = new float[]{0.0F, 0.0F};
         }

         var8.method_39(var10);
         if (this.field_464 != Class_0274.NONE) {
            var8.method_2(this.method_2(var10[0], this.field_464), true);
            if (var7 == 0) {
               var8.method_9(this.method_9(var10[1], this.field_464), true);
            } else if (this.field_464 == Class_0274.BOTTOM_LEFT || this.field_464 == Class_0274.BOTTOM_RIGHT) {
               var8.method_9(this.field_1683.get(var7 - 1).method_60() - var10[1], true);
            }
         }

         if (var9) {
            RenderSystem.setShaderColor(Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), var8.method_200());
            var8.method_2(var1, var2, var3, var5);
            RenderSystem.setShaderColor(
               Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
            );
         }

         if (var7 < this.field_1683.size() - 1 && this.field_464 != Class_0274.NONE && this.method_9(this.field_464) == 1) {
            this.field_1683.get(var7 + 1).method_9(var8.method_60() + var10[1], true);
         }
      }
   }

   public Class_0274 method_3() {
      return this.field_464;
   }

   public Class_0587<Class_0158> method_566() {
      return this.field_1683;
   }

   public int method_9(Class_0274 var1) {
      return switch (var1) {
         case TOP_LEFT, TOP_RIGHT, TOP_CENTER, NONE -> 1;
         case BOTTOM_LEFT, BOTTOM_RIGHT -> -1;
      };
   }

   public float method_2(float var1, Class_0274 var2) {
      return switch (var2) {
         case TOP_LEFT, BOTTOM_LEFT -> (float)hud.method_341();
         case TOP_RIGHT, BOTTOM_RIGHT -> (float)field_4219.getWindow().getScaledWidth() - var1 - (float)hud.method_341();
         case TOP_CENTER -> (float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824) - var1 / Float.intBitsToFloat(1073741824);
         case NONE -> throw new IllegalArgumentException("dumb");
      };
   }

   public float method_9(float var1, Class_0274 var2) {
      return switch (var2) {
         case TOP_LEFT, TOP_CENTER -> (float)hud.method_342();
         case TOP_RIGHT -> hud.method_339() + (float)hud.method_342();
         case NONE -> throw new IllegalArgumentException("dumb");
         case BOTTOM_LEFT, BOTTOM_RIGHT -> (float)field_4219.getWindow().getScaledHeight() - var1 - this.method_567();
      };
   }

   public float method_567() {
      return MathHelper.clamp((float)hud.method_342() + hud.method_338(), 0.0F, Float.intBitsToFloat(1096810496));
   }
}
