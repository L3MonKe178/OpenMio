package me.mioclient.internal;

import java.awt.Color;
import java.util.function.Supplier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0906 extends Class_0145 {
   public final Supplier<String> field_2840;
   public boolean field_2841;

   public Class_0906(Class_0746 var1, Supplier<String> var2) {
      super(var1, 0);
      this.field_2840 = var2;
   }

   public Class_0906(Class_0746 var1, String var2) {
      super(var1, 0);
      this.field_2840 = () -> var2;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      String[] var7 = this.field_2840.get().split("\n");

      for (int var8 = 0; var8 < var7.length; var8++) {
         float var9 = this.field_2841 && this.method_5(var3, var5) ? Float.intBitsToFloat(1065353216) : 0.0F;
         FontRenderer.field_3143
            .method_9(
               var1,
               var7[var8],
               (float)(this.field_418.getX() + this.method_852().field_2853.getValue()),
               (float)(this.field_418.getY() + this.field_419)
                  + this.method_850()
                  + (float)(var8 * FontRenderer.field_3143.method_66())
                  + Float.intBitsToFloat(1065353216)
                  - var9,
               Color.white
            );
      }

      super.method_2(var1, var2, var3, var5);
   }

   @Override
   public int method_66() {
      return super.method_66() + FontRenderer.field_3143.method_66() * (this.field_2840.get().split("\n").length - 1) + 1;
   }

   public void method_460(boolean var1) {
      this.field_2841 = var1;
   }
}
