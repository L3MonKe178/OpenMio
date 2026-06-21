package me.mioclient.internal;

import me.mioclient.api.Class_1240;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Class_0959 extends Class_0045 implements Class_1240 {
   public boolean field_2971;

   public Class_0959(int var1, Class_0519 var2, String var3, Runnable var4) {
      super(var1, var2, var3, var4);
      this.field_91 = () -> {
         var4.run();
         this.field_2971 = !this.field_2971;
      };
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (!this.field_89.method_911()) {
         Class_1016.field_3143
            .method_9(
               var1,
               this.field_90,
               (float)this.field_418.getX() + this.method_59(),
               (float)(this.field_418.getY() + this.field_419) + this.method_60() + this.method_850() - this.method_169(),
               this.field_2971 ? this.method_852().field_2879.getValue() : this.method_852().field_2876.getValue()
            );
      }
   }

   @Override
   public boolean isToggled() {
      return this.field_2971;
   }

   @Override
   public void enable() {
   }

   @Override
   public void disable() {
   }
}
