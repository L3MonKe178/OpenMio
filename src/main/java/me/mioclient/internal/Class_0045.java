package me.mioclient.internal;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Class_0045 extends Class_0145 {
   public final Class_0519 field_89;
   public final String field_90;
   public Runnable field_91;

   public Class_0045(int var1, Class_0519 var2, String var3, Runnable var4) {
      super(var2.method_167(), var1);
      this.field_89 = var2;
      this.field_90 = var3;
      this.field_91 = var4;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (!this.field_89.method_911()) {
         super.method_2(var1, var2, var3, var5);
         Class_1016.field_3143
            .method_9(
               var1,
               this.getName(),
               (float)this.field_418.getX() + this.method_59(),
               (float)(this.field_418.getY() + this.field_419) + this.method_60() + this.method_850() - this.method_169(),
               this.method_852().field_2876.getValue()
            );
      }
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (!this.field_89.method_911()) {
         super.method_2(var1, var3, var5);
         if (this.method_5(var1, var3) && var5 == 0) {
            this.field_91.run();
         }
      }
   }

   @Override
   public boolean method_5(double var1, double var3) {
      return var1 > (double)((float)this.field_418.getX() + this.method_59())
         && var1 < (double)((float)this.field_418.getX() + this.method_59() + Class_1016.field_3143.method_221(this.getName()))
         && var3 > (double)((float)(this.field_418.getY() + this.field_419) + this.method_60())
         && var3
            < (double)(
               (float)(this.field_418.getY() + this.field_419)
                  + this.method_60()
                  + (float)Class_1016.field_3143.method_66()
                  + this.method_850() * Float.intBitsToFloat(1073741824)
            );
   }

   public abstract float method_59();

   public abstract float method_60();

   public String getName() {
      return this.field_90;
   }
}
