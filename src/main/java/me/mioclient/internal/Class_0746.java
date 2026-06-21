package me.mioclient.internal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Nameable;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_0945;
import me.mioclient.api.MioAPI;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0746 implements Nameable, Class_0937, Class_0945<Class_0937, List<Class_0937>> {
   public final ArrayList<Class_0937> field_2378;
   public final String field_2379;
   public final int field_1656;
   public int field_2380 = 14;
   public int field_2381;
   public int field_2382;
   public boolean field_111;
   public int field_1322;
   public int field_1321;
   public boolean field_2383;
   public int field_1657;
   public Class_0585 field_2384;

   public Class_0746(String var1) {
      super();
      this.field_2378 = new ArrayList<>();
      this.field_2380 = 14;
      this.field_2384 = new Class_0585(() -> this.method_852().field_2854.getValue() * Float.intBitsToFloat(1073741824), true);
      this.field_2379 = var1;
      this.field_1656 = 92;
      this.field_2381 = 0;
      this.field_111 = true;
      this.field_1322 = 5;
      this.field_1321 = 5;
      this.field_2383 = false;
      this.field_2384.method_36(true);
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      Class_0745.method_4(
         var1.getMatrices(),
         (float)this.field_1321,
         (float)this.field_1322,
         (float)(this.field_1321 + this.method_216()),
         (float)(this.field_1322 + 14),
         UIModule.field_2843.field_2879.getValue()
      );
      FontRenderer.field_3143
         .method_9(
            var1,
            this.field_2379,
            (float)(this.field_1321 + 2),
            (float)this.field_1322 + Float.intBitsToFloat(1088421888) - (float)FontRenderer.field_3143.method_66() / Float.intBitsToFloat(1073741824),
            Color.white
         );
      this.field_2384.method_3(this.field_111);
      float var7 = this.field_2384.method_45();
      if (var7 != Float.intBitsToFloat(1065353216) && this.field_1657 != 0) {
         Class_0745.method_474();
         Class_0041.method_9(
            this.field_1321 - 1, this.field_1322 - 1, this.method_216() + 2, (int)(Float.intBitsToFloat(1096810496) + (float)this.field_1657 * var7) + 3
         );
      }

      if (var7 > 0.0F && !this.field_2378.isEmpty()) {
         Class_0745.method_4(
            var1.getMatrices(),
            (float)this.field_1321,
            (float)(this.field_1322 + 14),
            (float)(this.field_1321 + this.method_216()),
            (float)(this.field_1322 + this.field_1657) + Float.intBitsToFloat(1056964608),
            UIModule.field_2843.field_2880.getValue()
         );
         this.field_2378.forEach(var7x -> {
            if (this.method_2(var7x)) {
               var7x.method_4(var3, var5);
               var7x.method_2(var1, var2, var3, var5);
            }
         });
         if (UIModule.field_2843.field_2861.getValue()) {
            Class_0745.method_4(
               var1.getMatrices(),
               (float)this.field_1321 + UIModule.field_2843.field_2863.getValue(),
               (float)(this.field_1322 + this.field_1657) + Float.intBitsToFloat(1056964608),
               (float)(this.field_1321 + this.method_216()),
               (float)(this.field_1322 + this.field_1657) + Float.intBitsToFloat(1056964608) + UIModule.field_2843.field_2863.getValue(),
               UIModule.field_2843.field_2862.getValue()
            );
            Class_0745.method_4(
               var1.getMatrices(),
               (float)(this.field_1321 + this.method_216()),
               (float)this.field_1322 + UIModule.field_2843.field_2863.getValue(),
               (float)(this.field_1321 + this.method_216()) + UIModule.field_2843.field_2863.getValue(),
               (float)(this.field_1322 + this.field_1657) + Float.intBitsToFloat(1056964608) + UIModule.field_2843.field_2863.getValue(),
               UIModule.field_2843.field_2862.getValue()
            );
         }
      }

      if (UIModule.field_2843.field_2858.getValue()) {
         Class_0745.method_29(
            var1.getMatrices(),
            (float)(this.field_1321 - 1),
            (float)(this.field_1322 - 1),
            (float)(this.field_1321 + this.method_216()),
            (float)this.field_1322 + (var7 == 0.0F ? Float.intBitsToFloat(1096810496) : (float)this.field_1657 + Float.intBitsToFloat(1056964608)),
            UIModule.field_2843.field_2879.getValue()
         );
      }

      if (var7 != Float.intBitsToFloat(1065353216) && this.field_1657 != 0) {
         Class_0745.method_474();
         Class_0041.method_57();
      }

      int var8 = 14 + 1;

      for (Class_0937 var10 : this.field_2378) {
         if (!this.method_2(var10)) {
            var10.method_26(0);
         } else {
            var10.method_26(var8);
            var8 += var10.method_66();
         }
      }

      this.field_1657 = var7 == 0.0F ? 15 : var8;
   }

   @Override
   public void method_2(double var1, double var3, int var5) {
      if (this.method_78(var1, var3) && var5 == 1) {
         this.method_80(!this.field_111);
      }

      if (this.field_111) {
         for (Class_0937 var7 : this.field_2378) {
            var7.method_2(var1, var3, var5);
         }
      }
   }

   @Override
   public void method_9(double var1, double var3, int var5) {
      if (this.field_111) {
         for (Class_0937 var7 : this.field_2378) {
            var7.method_9(var1, var3, var5);
         }
      }
   }

   @Override
   public void method_7(double var1, double var3, double var5) {
      if (this.field_111) {
         for (Class_0937 var8 : this.field_2378) {
            var8.method_7(var1, var3, var5);
         }
      }
   }

   @Override
   public void method_68(int var1) {
      if (this.field_111) {
         for (Class_0937 var3 : this.field_2378) {
            var3.method_68(var1);
         }
      }
   }

   @Override
   public void method_9(char var1) {
      if (this.field_111) {
         for (Class_0937 var3 : this.field_2378) {
            var3.method_9(var1);
         }
      }
   }

   @Override
   public void init() {
      if (this.field_111) {
         for (Class_0937 var2 : this.field_2378) {
            var2.init();
         }
      }
   }

   public ArrayList<Class_0937> method_714() {
      return this.field_2378;
   }

   public void method_157(boolean var1) {
      if (var1 && !Hub.method_755().method_1000()) {
         Hub.method_755().method_294(true);
         this.field_2383 = true;
      } else {
         if (Hub.method_755().method_1000()) {
            Hub.method_755().method_294(false);
         }

         this.field_2383 = false;
      }
   }

   public boolean method_2(Class_0937 var1) {
      return true;
   }

   public boolean method_194() {
      return this.field_111 && this.field_2384.field_55 == Float.intBitsToFloat(1073741824);
   }

   public float method_715() {
      return this.field_2384.method_605() - Float.intBitsToFloat(1065353216);
   }

   public void method_80(boolean var1) {
      this.field_111 = var1;
   }

   public void method_142() {
      int var1 = 14 + 1;

      for (Class_0937 var3 : this.field_2378) {
         var3.method_26(var1);
         var1 += var3.method_66();
      }

      this.field_1657 = var1;
   }

   public int getX() {
      return this.field_1321;
   }

   public void setX(int var1) {
      this.field_1321 = var1;
   }

   public int getY() {
      return this.field_1322;
   }

   public void setY(int var1) {
      this.field_1322 = var1;
   }

   public int method_216() {
      return this.field_1656 + UIModule.field_2843.field_2852.getValue();
   }

   @Override
   public int method_66() {
      return this.field_1657;
   }

   public void method_22(int var1, int var2) {
      if (this.field_2383) {
         this.setX(Math.max(0, Math.min(MioAPI.field_4219.getWindow().getScaledWidth() - this.method_216(), var1 - this.field_2381)));
         this.setY(Math.max(0, Math.min(MioAPI.field_4219.getWindow().getScaledHeight() - this.method_66(), var2 - this.field_2382)));
      }
   }

   public boolean method_78(double var1, double var3) {
      return var1 >= (double)this.field_1321
         && var1 <= (double)(this.field_1321 + this.method_216())
         && var3 >= (double)this.field_1322
         && var3 <= (double)(this.field_1322 + 14);
   }

   @Override
   public boolean method_5(double var1, double var3) {
      return var1 >= (double)this.field_1321
         && var1 <= (double)(this.field_1321 + this.method_216())
         && var3 >= (double)this.field_1322
         && var3 <= (double)(this.field_1322 + this.field_1657);
   }

   public boolean method_192() {
      return this.field_2383;
   }

   @Override
   public String getName() {
      return this.field_2379;
   }

   public List<Class_0937> getRegistry() {
      return this.field_2378;
   }

   public boolean method_9(Class_0937 var1) {
      return this.getRegistry().add(var1);
   }

   @Override public boolean register(Class_0937 var1)   { return method_9(var1); }
   @Override public boolean unregister(Class_0937 var1) { return this.getRegistry().remove(var1); }

   public boolean method_5(Class_0937 var1) {
      return this.getRegistry().add(var1);
   }
}
