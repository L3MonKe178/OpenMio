package me.mioclient.internal;

import java.awt.Color;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0735 extends Class_0045 {
   public Class_0735(int var1, Class_0519 var2) {
      super(var1, var2, "Paste", () -> {
         try {
            Color var1x = Class_1081.method_209(field_4219.keyboard.getClipboard().trim());
            var2.method_31(var1x);
            var2.method_910().method_78(var1x);
         } catch (Exception var2x) {
            var2.field_1642.reset();
            return;
         }

         var2.field_1643.reset();
      });
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (!this.field_89.method_911()) {
         Class_0745.method_4(
            var2,
            (float)this.field_418.getX() + (float)this.field_418.method_216() / Float.intBitsToFloat(1073741824) + Float.intBitsToFloat(1056964608),
            (float)((int)((float)(this.field_418.getY() + this.field_419) + this.method_60())),
            (float)(this.field_418.getX() + this.field_418.method_216()) - Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getY() + this.field_419) + this.method_60() + (float)this.method_66() - Float.intBitsToFloat(1056964608),
            this.method_852().field_2879.getValue()
         );
         super.method_2(var1, var2, var3, var5);
      }
   }

   @Override
   public float method_59() {
      return (float)this.method_167().method_216()
         - (float)this.method_167().method_216() / Float.intBitsToFloat(1082130432)
         - FontRenderer.field_3143.method_221(this.getName())
         + FontRenderer.field_3143.method_221(this.getName()) * Float.intBitsToFloat(1056964608);
   }

   @Override
   public float method_60() {
      return 0.0F;
   }

   @Override
   public String getName() {
      if (!this.field_89.field_1642.method_9(1000L)) {
         return "Invalid";
      } else {
         return !this.field_89.field_1643.method_9(1000L) ? "Pasted" : super.getName();
      }
   }
}
