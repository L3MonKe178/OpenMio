package me.mioclient.internal;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0513 extends Class_0045 {
   public Class_0513(int var1, Class_0519 var2) {
      super(var1, var2, "Copy", () -> {
         field_4219.keyboard.setClipboard(Class_1081.method_2(var2.method_152(), true));
         var2.field_1644.reset();
      });
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (!this.field_89.method_911()) {
         Class_0745.method_4(
            var2,
            (float)(this.field_418.getX() + 2),
            (float)((int)((float)(this.field_418.getY() + this.field_419) + this.method_60())),
            (float)this.field_418.getX() + (float)this.field_418.method_216() / Float.intBitsToFloat(1073741824) - Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getY() + this.field_419) + this.method_60() + (float)this.method_66() - Float.intBitsToFloat(1056964608),
            this.method_852().field_2879.getValue()
         );
         super.method_2(var1, var2, var3, var5);
      }
   }

   @Override
   public float method_59() {
      return (float)this.method_167().method_216() / Float.intBitsToFloat(1082130432)
         - FontRenderer.field_3143.method_221(this.getName()) / Float.intBitsToFloat(1073741824);
   }

   @Override
   public float method_60() {
      return 0.0F;
   }

   @Override
   public String getName() {
      return !this.field_89.field_1644.method_9(1000L) ? "Copied" : super.getName();
   }
}
