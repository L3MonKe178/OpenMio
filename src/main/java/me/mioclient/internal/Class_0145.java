package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_1200;
import me.mioclient.enum_.Class_1290;
import me.mioclient.module.abstract_.AbstractModule_41;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Class_0145 implements Class_1309, Class_0937 {
   public final Class_0031 field_417 = new Class_0031(Float.intBitsToFloat(1082130432));
   public final Class_0746 field_418;
   public int field_419;
   public int field_420;
   public boolean field_421 = false;

   public Class_0145(Class_0746 var1, int var2) {
      super();
      this.field_418 = var1;
      this.field_419 = var2;
   }

   public Class_0746 method_167() {
      return this.field_418;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.method_5(var3, var5)) {
         if (!this.field_421 && this.method_852().field_2865.getValue() && !this.method_2(Class_1290.SILENT)) {
            Class_0337 var7 = Hub.field_2606.method_2(this.method_852().field_2866.getValue());
            if (var7 != null) {
               var7.method_230(this.method_852().field_2867.getValue());
            }
         }

         this.field_421 = true;
         Hub.method_755().method_998();
         Class_1117.field_3460 = Class_1200.POINTER;
      } else {
         this.field_421 = false;
      }

      if (this.method_852().field_2875.getValue()) {
         this.field_417.method_3(this.method_5(var3, var5) ? Float.intBitsToFloat(1065353216) : 0.0F);
      } else {
         this.field_417.method_3(0.0F);
      }

      Class_0937.super.method_2(var1, var2, var3, var5);
   }

   @Override
   public void method_26(int var1) {
      this.field_419 = var1;
   }

   @Override
   public int method_168() {
      return this.field_419;
   }

   public float method_169() {
      return this.field_417.method_45();
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (this.method_5(var1, var3) && !this.method_2(Class_1290.SILENT)) {
         if (var5 == 0 && this.method_171()) {
            if (this.method_852().field_2868.getValue()) {
               Class_0337 var7 = Hub.field_2606.method_2(this.method_852().field_2869.getValue());
               if (var7 != null) {
                  var7.method_230(this.method_852().field_2870.getValue());
               }
            }
         } else if (var5 == 1 && this.method_852().field_2871.getValue()) {
            Class_0337 var6 = Hub.field_2606.method_2(this.method_852().field_2872.getValue());
            if (var6 != null) {
               var6.method_230(this.method_852().field_2873.getValue());
            }
         }
      }
   }

   @Override
   public boolean method_5(double var1, double var3) {
      return var1 > (double)this.field_418.getX()
         && var1 < (double)(this.field_418.getX() + this.field_418.method_216())
         && var3 > (double)(this.field_418.getY() + this.field_419)
         && var3 < (double)(this.field_418.getY() + this.method_66() + this.field_419);
   }

   public int method_170() {
      return this.method_2(Class_1290.PADDING_SHIFT) ? 2 : 1;
   }

   public boolean method_171() {
      return this instanceof Class_0050 var1 ? !(var1.method_65() instanceof AbstractModule_41) : true;
   }

   public void method_2(Class_1290... var1) {
      for (Class_1290 var5 : var1) {
         this.field_420 = this.field_420 | var5.method_1154();
      }
   }

   public boolean method_2(Class_1290 var1) {
      return (this.field_420 & var1.method_1154()) == var1.method_1154();
   }
}
