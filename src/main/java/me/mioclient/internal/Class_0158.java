package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0274;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.client.FontsModule;
import me.mioclient.record.Class_0920;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public abstract class Class_0158 implements Class_1309, Class_0937 {
   public static final Color field_459 = new Color(0, 0, 0, 120);
   public static boolean field_460 = false;
   public final AbstractModule_26 field_461;
   public final Class_0055 field_462 = new Class_0055();
   public final Class_0055 field_463 = new Class_0055();
   public Class_0274 field_464 = Class_0274.NONE;
   public float field_465 = Float.intBitsToFloat(1056964608);
   public float field_466 = Float.intBitsToFloat(1056964608);
   public int field_467;
   public int field_468;
   public float[] field_469;
   public boolean field_470;
   public long field_471;

   public Class_0158(AbstractModule_26 var1) {
      super();
      this.field_461 = var1;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      float[] var7 = this.method_193();
      if (this.method_5(var3, var5) && !this.field_470) {
         if (this.field_471 == -1L) {
            this.field_471 = System.currentTimeMillis();
         }

         if (this.field_471 + 300L <= System.currentTimeMillis()) {
            float var8 = this.method_195() == 1
               ? this.method_59() + var7[0] + Float.intBitsToFloat(1073741824)
               : this.method_59() - Float.intBitsToFloat(1073741824) - Class_1016.field_3143.method_221(this.field_461.getName());
            Class_0617.field_1951.method_2(this.field_461.getName(), var8, this.method_60(), this.method_195());
         }
      } else {
         this.field_471 = -1L;
      }

      if (!field_460) {
         float var13 = this.method_59();
         float var9 = this.method_60();
         float var10 = RenderSystem.getShaderColor()[3];
         float var11 = Hub.method_756().method_937() * var10 * (float)field_459.getAlpha() / Float.intBitsToFloat(1132396544);
         Class_0745.method_9(
            var2,
            var13,
            var9,
            var13 + Math.max(var7[0], Float.intBitsToFloat(1082130432)),
            var9 + Math.max(var7[1], Float.intBitsToFloat(1082130432)),
            Class_1081.method_9(field_459, var11)
         );
         float var12 = this.field_462.method_45();
         if (var12 > 0.0F) {
            Class_0745.method_29(
               var2,
               var13 - Float.intBitsToFloat(1065353216),
               var9 - Float.intBitsToFloat(1065353216),
               var13 + Math.max(var7[0], Float.intBitsToFloat(1082130432)),
               var9 + Math.max(var7[1], Float.intBitsToFloat(1082130432)),
               Class_1081.method_9(Class_1081.method_959(), (int)(var12 * Float.intBitsToFloat(1132396544)))
            );
         }
      }
   }

   @Override
   public boolean method_5(double var1, double var3) {
      float[] var5 = this.method_193();
      return var1 >= (double)this.method_59()
         && var1 <= (double)(this.method_59() + Math.max(var5[0], Float.intBitsToFloat(1082130432)))
         && var3 >= (double)this.method_60()
         && var3 <= (double)(this.method_60() + Math.max(var5[1], Float.intBitsToFloat(1082130432)));
   }

   public void method_189() {
      this.field_463.method_2(this.method_176() ? Float.intBitsToFloat(1065353216) : 0.0F, 250L);
   }

   public abstract float[] method_31();

   public float method_59() {
      return Math.min(this.field_465 * (float)field_4219.getWindow().getScaledWidth(), (float)field_4219.getWindow().getScaledWidth() - this.method_193()[0]);
   }

   public float method_190() {
      return this.field_465;
   }

   public void method_2(float var1, boolean var2) {
      if (var2) {
         this.field_465 = var1 / (float)field_4219.getWindow().getScaledWidth();
      } else {
         this.field_465 = MathHelper.clamp(var1, 0.0F, Float.intBitsToFloat(1065353216));
      }
   }

   public float method_60() {
      return Math.min(this.field_466 * (float)field_4219.getWindow().getScaledHeight(), (float)field_4219.getWindow().getScaledHeight() - this.method_193()[1]);
   }

   public float method_191() {
      return this.field_466;
   }

   public void method_9(float var1, boolean var2) {
      if (var2) {
         this.field_466 = var1 / (float)field_4219.getWindow().getScaledHeight();
      } else {
         this.field_466 = MathHelper.clamp(var1, 0.0F, Float.intBitsToFloat(1065353216));
      }
   }

   @Override
   public int method_66() {
      int var1 = Class_1016.field_3143.method_66();
      if (FontsModule.field_364.isToggled()) {
         var1--;
      }

      return var1;
   }

   public void method_6(boolean var1) {
      if (this.field_470 != var1) {
         this.field_462.method_2(var1 ? Float.intBitsToFloat(1065353216) : 0.0F, 250L);
         this.field_470 = var1;
      }
   }

   public boolean method_192() {
      return this.field_470;
   }

   public float[] method_193() {
      return this.field_469;
   }

   public void method_39(float[] var1) {
      this.field_469 = var1;
   }

   public Class_0274 method_3() {
      return this.field_464;
   }

   public void method_2(Class_0274 var1) {
      this.field_464 = var1;
   }

   public boolean method_176() {
      return this.field_461.isToggled();
   }

   public float method_176(float var1) {
      if (this.field_464 == Class_0274.TOP_CENTER) {
         return this.method_59() + this.field_469[0] / Float.intBitsToFloat(1073741824) - var1 / Float.intBitsToFloat(1073741824);
      } else {
         return this.method_195() == -1 ? this.method_59() + this.field_469[0] - var1 : this.method_59();
      }
   }

   public float method_194(float var1) {
      return this.method_196() == -1 ? this.method_60() + this.field_469[1] - var1 : this.method_60();
   }

   public int method_195() {
      if (this.field_467 == 0) {
         this.method_197();
      }

      return this.field_467;
   }

   public int method_196() {
      if (this.field_468 == 0) {
         this.method_197();
      }

      return this.field_468;
   }

   public void method_197() {
      this.field_467 = this.method_59() + this.field_469[0] / Float.intBitsToFloat(1073741824)
            > (float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824)
         ? -1
         : 1;
      this.field_468 = this.method_60() + this.field_469[1] / Float.intBitsToFloat(1073741824)
            > (float)field_4219.getWindow().getScaledHeight() / Float.intBitsToFloat(1073741824)
         ? -1
         : 1;
   }

   public Class_0920 method_198() {
      return Class_0920.method_9(
         this.field_465 * (float)field_4219.getWindow().getScaledWidth(),
         this.field_466 * (float)field_4219.getWindow().getScaledHeight(),
         this.field_469[0],
         this.field_469[1]
      );
   }

   public AbstractModule_26 method_199() {
      return this.field_461;
   }

   public float method_200() {
      return this.field_463.method_45();
   }
}
