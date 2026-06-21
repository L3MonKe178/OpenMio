package me.mioclient.internal;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import me.mioclient.module.abstract_.AbstractModule_26;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class Class_0723 extends Class_0158 {
   public final List<? extends Class_0121> field_2298;

   public Class_0723(AbstractModule_26 var1, Class_0121 var2) {
      this(var1, Collections.singletonList(var2));
   }

   public Class_0723(AbstractModule_26 var1, List<? extends Class_0121> var2) {
      super(var1);
      this.field_2298 = var2;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      float var7 = this.method_194((float)this.method_66());

      for (Class_0121 var9 : this.field_2298) {
         var2.push();
         float var10 = var9.method_143();
         var10 = (float)MathHelper.clamp(
            Double.longBitsToDouble(4607182418800017408L)
               - Math.pow((double)(Float.intBitsToFloat(1065353216) - var10), Double.longBitsToDouble(4616189618054758400L)),
            0.0,
            Double.longBitsToDouble(4607182418800017408L)
         );
         Color var11 = this.method_2(var7, var9);
         float var12 = var9.field_360.method_45();
         float var13 = var12 * (Float.intBitsToFloat(1065353216) - var10);
         var2.translate(this.method_176(var12) - var13 * (float)this.method_195(), var7, 0.0F);
         var9.method_2(var1, 0.0F, 0.0F, var11);
         if (var10 != 0.0F) {
            var7 += ((float)this.method_66() * var10 + Float.intBitsToFloat(1065353216)) * (float)this.method_196();
         }

         var2.pop();
      }
   }

   @Override
   public float[] method_31() {
      float var1 = 0.0F;
      float var2 = 0.0F;

      for (Class_0121 var4 : this.field_2298) {
         var4.method_142();
         if (var4.method_143() != 0.0F) {
            float var5 = var4.method_143();
            var5 = (float)MathHelper.clamp(
               Double.longBitsToDouble(4607182418800017408L)
                  - Math.pow((double)(Float.intBitsToFloat(1065353216) - var5), Double.longBitsToDouble(4616189618054758400L)),
               0.0,
               Double.longBitsToDouble(4607182418800017408L)
            );
            float var6 = Class_1016.field_3143.method_221(var4.method_15().getString());
            var1 += (float)this.method_66() * var5 + Float.intBitsToFloat(1065353216);
            if (var6 > var2) {
               var2 = var6;
            }
         }
      }

      if (!field_460 && var2 == 0.0F) {
         var2 = Float.intBitsToFloat(1112014848);
         var1 = (float)this.method_66();
      }

      return new float[]{var2, var1};
   }

   public List<? extends Class_0121> method_694() {
      return this.field_2298;
   }

   public Color method_2(float var1, Class_0121 var2) {
      return this.field_461.method_9(var1);
   }
}
