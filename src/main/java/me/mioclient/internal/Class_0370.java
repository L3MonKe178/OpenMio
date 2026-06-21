package me.mioclient.internal;

import java.awt.Color;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Class_0370 extends Class_0145 {
   public final int field_1200;
   public final int field_1201;
   public final Class_0242 field_1202 = new Class_0242();

   public Class_0370(Class_0746 var1, int var2, int var3, int var4) {
      super(var1, var2);
      this.field_1200 = var3;
      this.field_1201 = var4;
   }

   public Class_0370(Class_0746 var1, int var2) {
      this(var1, var2, 22, 22);
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);
      if (this.field_1202.method_9((long)this.method_412())) {
         this.method_411();
         this.field_1202.reset();
      }
   }

   public abstract void method_411();

   public abstract int method_412();

   public void method_2(MatrixStack var1, int var2, int var3, Color var4) {
      Class_0745.method_4(
         var1,
         (float)(this.method_167().getX() + var2 * this.method_413()),
         (float)(this.method_167().getY() + this.field_419 + var3 * this.method_414()),
         (float)(this.method_167().getX() + var2 * this.method_413() + this.method_413()),
         (float)(this.method_167().getY() + this.field_419 + var3 * this.method_414() + this.method_414()),
         var4
      );
   }

   public int method_413() {
      return this.method_167().method_216() / this.field_1200;
   }

   public int method_414() {
      return this.method_66() / this.field_1201;
   }
}
