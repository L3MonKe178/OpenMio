package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0988;
import me.mioclient.setting.Setting;
import net.minecraft.client.util.math.MatrixStack;

public abstract class Class_1015<T> extends Class_0145 {
   public final Class_0988 field_3137;
   public final Setting<T> field_3138;
   public final Class_0055 field_3139 = new Class_0055();
   public boolean field_3140;
   public long field_3141;
   public boolean field_3142;

   public Class_1015(Class_0746 var1, Setting<T> var2) {
      this(var1, () -> false, var2);
   }

   public Class_1015(Class_0746 var1, Class_0988 var2, Setting<T> var3) {
      super(var1, 0);
      this.field_3137 = var2;
      this.field_3138 = var3;
   }

   @Override
   public int method_66() {
      return !this.field_3138.method_176() ? 0 : this.method_851();
   }

   public Class_0988 method_909() {
      return this.field_3137;
   }

   public Setting<T> method_910() {
      return this.field_3138;
   }

   public boolean method_911() {
      boolean var1 = !this.field_3138.method_176() || this.field_3137.isClosed();
      if (var1) {
         this.field_3138.field_111 = false;
      }

      return var1;
   }

   public void method_2(MatrixStack var1, String var2, Runnable var3) {
      var1.push();
      this.method_468(Class_1016.field_3143.method_221(var2));
      float var4 = -this.field_3139.method_45();
      boolean var5 = var4 != 0.0F && !(this instanceof Class_0414);
      if (var5) {
         Class_0050 var6 = (Class_0050)this.field_3137;
         Class_0745.method_474();
         Class_0041.method_9(
            this.field_418.getX() + this.method_170(),
            this.field_418.getY() + var6.method_168(),
            this.field_418.method_216() - this.method_170(),
            var6.method_66()
         );
      }

      var1.translate(var4, 0.0F, 0.0F);
      var3.run();
      if (var5) {
         Class_0745.method_474();
         Class_0041.method_57();
      }

      var1.pop();
   }

   public void method_468(float var1) {
      if (var1 <= (float)(this.field_418.method_216() - 4)) {
         this.field_3139.method_36(0.0F);
      } else {
         if (this.field_421) {
            if (!this.field_3140) {
               this.field_3141 = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() > this.field_3141 + 750L) {
               this.field_3139.method_2(var1 - (float)(this.field_418.method_216() - 4), 500L);
            }
         } else {
            this.field_3139.method_2(0.0F, 500L);
         }

         this.field_3140 = this.field_421;
      }
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (!this.method_911()) {
         super.method_2(var1, var3, var5);
      }
   }

   public Class_0406 method_912() {
      return Hub.method_755();
   }
}
