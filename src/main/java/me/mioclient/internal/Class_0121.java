package me.mioclient.internal;

import java.awt.Color;
import java.util.function.Supplier;
import me.mioclient.Hub;
import me.mioclient.module.client.HUDModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class Class_0121 {
   public static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   public final Supplier<Text> field_357;
   public final Supplier<Boolean> field_358;
   public float field_359;
   public final Class_0031 field_360 = new Class_0031(Float.intBitsToFloat(1073741824), true);

   public Class_0121(Supplier<Text> var1, Supplier<Boolean> var2) {
      super();
      this.field_357 = var1;
      this.field_358 = var2;
   }

   public void method_2(DrawContext var1, float var2, float var3, Color var4) {
      float var5 = Class_1016.field_3143.method_221(this.method_15().getString());
      if (hud.field_956.getValue()) {
         this.field_360.method_3(var5);
      } else {
         this.field_360.method_36(var5);
      }

      if (this.field_359 > 0.0F) {
         Color var6 = Class_1081.method_9(
            var4,
            (int)(
               (double)var4.getAlpha()
                  * MathHelper.clamp(
                     (double)(this.field_359 * Float.intBitsToFloat(1073741824)),
                     Double.longBitsToDouble(4591870180066957722L),
                     Double.longBitsToDouble(4607182418800017408L)
                  )
            )
         );
         Class_1016.field_3143.method_9(var1, this.method_15(), var2, var3, var6);
      }
   }

   public void method_142() {
      float var1 = Float.intBitsToFloat(1065353216) / (float)(Hub.field_2601.method_814() >> 2);
      if (this.isDrawn()) {
         this.field_359 += var1;
      } else {
         this.field_359 -= var1;
      }

      this.field_359 = MathHelper.clamp(this.field_359, 0.0F, Float.intBitsToFloat(1065353216));
   }

   public float method_143() {
      return this.field_359;
   }

   public boolean isDrawn() {
      return this.field_358.get();
   }

   public Text method_15() {
      return this.field_357.get();
   }
}
