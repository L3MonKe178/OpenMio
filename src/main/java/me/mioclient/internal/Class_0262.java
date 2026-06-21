package me.mioclient.internal;

import me.mioclient.api.Class_0988;
import me.mioclient.enum_.Class_1200;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0262 extends Class_1015<Class_0211> {
   @SuppressWarnings({"unchecked", "rawtypes"})
   public Class_0262(Class_0746 var1, Class_0988 var2, Setting<?> var3) {
      super(var1, var2, (Setting) var3);
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (!this.method_911() && this.method_5(var1, var3) && var5 == 0) {
         field_4219.setScreen(new Class_0918(this.field_3138, this.method_912()));
      }
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (!this.method_911()) {
         super.method_2(var1, var2, var3, var5);
         if (this.method_5(var3, var5)) {
            Class_1117.field_3460 = Class_1200.POINTER;
         }

         Class_0745.method_4(
            var2,
            (float)(this.field_418.getX() + this.method_170()),
            (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(1056964608),
            (float)(this.field_418.getX() + this.field_418.method_216() - 1),
            (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1056964608),
            this.method_852().field_2879.getValue()
         );
         String var7 = new TextBuilder().method_2(this.field_3138.getValue().getName()).method_2(this.field_3138.getName()).method_9("\u0001: \u0001");
         this.method_2(
            var2,
            var7,
            () -> FontRenderer.field_3143
                  .method_9(
                     var1,
                     var7,
                     (float)(this.field_418.getX() + 4),
                     (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419,
                     this.method_852().field_2876.getValue()
                  )
         );
      }
   }
}
