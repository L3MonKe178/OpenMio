package me.mioclient.internal;

import me.mioclient.Hub;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0458 extends Class_0906 {
   public final Class_0211 field_1463;
   public final Class_0918 field_1464;

   public Class_0458(Class_0918 var1, Class_0746 var2, Class_0211 var3) {
      super(var2, var3.getName());
      this.field_1464 = var1;
      this.field_1463 = var3;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.field_1464.field_2897.getValue().equals(this.field_1463)) {
         RenderUtil.field_2672
            .method_9(
               var2,
               (float)(this.field_418.getX() + 1),
               (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
               (float)(this.field_418.getX() + this.field_418.method_216() - 1),
               (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1056964608),
               this.method_852().field_2879.getValue()
            );
      } else {
         RenderUtil.field_2672
            .method_9(
               var2,
               (float)(this.field_418.getX() + 1),
               (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
               (float)(this.field_418.getX() + this.field_418.method_216() - 1),
               (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1056964608),
               this.method_852().field_2881.getValue()
            );
      }

      super.method_2(var1, var2, var3, var5);
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (this.field_421) {
         if (var5 == 0) {
            this.field_1464.field_2897.method_78(this.field_1463);
            this.field_1464.method_257();
         } else if (var5 == 1) {
            Hub.field_2606.method_2(this.field_1463, Float.intBitsToFloat(1065353216));
         }
      }
   }
}
