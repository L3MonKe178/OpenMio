package me.mioclient.internal;

import me.mioclient.enum_.Class_1290;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.ColorSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0781 extends Class_1031 {
   public Class_0781(Class_0519 var1) {
      super(var1.method_167(), var1, new BooleanSetting("", ((ColorSetting)var1.method_910()).method_132()));
      this.field_3138.method_9(() -> ((ColorSetting)var1.method_910()).method_4(this.field_3138.getValue()));
      this.method_2(new Class_1290[]{Class_1290.PADDING_SHIFT});
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      String var7 = "Rainbow";
      this.method_2(
         var2,
         var7,
         () -> Class_1016.field_3143
               .method_9(
                  var1,
                  var7,
                  (float)this.field_418.getX()
                     + (float)this.method_167().method_216() * Float.intBitsToFloat(1056964608)
                     - Class_1016.field_3143.method_221(var7) * Float.intBitsToFloat(1056964608),
                  (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                  this.method_852().field_2876.getValue()
               )
      );
   }
}
