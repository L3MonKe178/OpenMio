package me.mioclient.internal;

import java.util.function.Supplier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_1266 extends Class_0906 {
   public final Runnable field_3980;

   public Class_1266(Class_0746 var1, String var2, Runnable var3) {
      this(var1, () -> var2, var3);
   }

   public Class_1266(Class_0746 var1, Supplier<String> var2, Runnable var3) {
      super(var1, var2);
      this.field_3980 = var3;
      this.method_460(true);
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.method_949()) {
         Class_0745.method_4(
            var2,
            (float)(this.field_418.getX() + 1),
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getX() + this.field_418.method_216() - 1),
            (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1056964608),
            this.method_852().field_2879.getValue()
         );
      }

      super.method_2(var1, var2, var3, var5);
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      super.method_2(var1, var3, var5);
      if (this.method_5(var1, var3) && var5 == 0) {
         this.field_3980.run();
      }
   }

   public boolean method_949() {
      return true;
   }
}
