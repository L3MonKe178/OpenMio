package me.mioclient.internal;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import me.mioclient.enum_.Class_0417;
import me.mioclient.module.render.ViewModelModule;
import me.mioclient.record.Class_1375;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;

public class Class_0217 extends Class_1117 {
   public static final Color field_603 = new Color(255, 255, 255, 100);
   public final ViewModelModule viewmodel;
   public final LinkedList<Class_1375> field_604 = new LinkedList<>();
   public final Class_0242 field_605 = new Class_0242();
   public Class_0417 field_606;
   public Class_1375 field_607;
   public double field_608;
   public double field_609;
   public boolean field_610;

   public Class_0217(ViewModelModule var1) {
      super();
      this.viewmodel = var1;
      this.field_607 = Class_1375.method_2(var1);
      this.field_604.add(this.field_607);
   }

   @Override
   public void method_2(DrawContext var1, int var2, int var3, float var4) {
      Class_1375 var5 = this.field_607;
      if (this.method_252() != Class_0417.NONE) {
         float var6 = (float)Class_0930.method_2((this.field_608 - (double)var2) * Double.longBitsToDouble(4572414629547868160L), 2)
            * (float)(this.field_610 ? -1 : 1);
         float var7 = (float)Class_0930.method_2((this.field_609 - (double)var3) * Double.longBitsToDouble(4572414629547868160L), 2);
         if (Class_0018.method_24()) {
            if (Math.abs(var6) > Math.abs(var7)) {
               var7 = 0.0F;
            } else {
               var6 = 0.0F;
            }
         }

         if (this.method_252() == Class_0417.TRANSLATE) {
            if (Class_0018.method_23()) {
               if (this.field_610) {
                  this.viewmodel.field_1923.method_78(var5.method_1198() + var6);
                  this.viewmodel.field_1924.method_78(var5.method_1199() + var7);
               } else {
                  this.viewmodel.field_1913.method_78(var5.method_1195() + var6);
                  this.viewmodel.field_1914.method_78(var5.method_1196() + var7);
               }
            } else {
               this.viewmodel.field_1923.method_78(var5.method_1198() + var6);
               this.viewmodel.field_1924.method_78(var5.method_1199() + var7);
               this.viewmodel.field_1913.method_78(var5.method_1195() + var6);
               this.viewmodel.field_1914.method_78(var5.method_1196() + var7);
            }
         } else if (this.method_252() == Class_0417.SCALE) {
            if (Class_0018.method_24()) {
               var6 = Math.abs(Math.max(var6, var7)) * Math.signum(var6);
               var7 = Math.abs(Math.max(var6, var7)) * Math.signum(var7);
            }

            var6 = -var6;
            if (Class_0018.method_23()) {
               if (this.field_610) {
                  this.viewmodel.field_1926.method_78(var5.method_1204() + var6);
                  this.viewmodel.field_1927.method_78(var5.method_1205() + var7);
               } else {
                  this.viewmodel.field_1916.method_78(var5.method_1201() + var6);
                  this.viewmodel.field_1917.method_78(var5.method_1202() + var7);
               }
            } else {
               this.viewmodel.field_1926.method_78(var5.method_1204() + var6);
               this.viewmodel.field_1927.method_78(var5.method_1205() + var7);
               this.viewmodel.field_1916.method_78(var5.method_1201() + var6);
               this.viewmodel.field_1917.method_78(var5.method_1202() + var7);
            }
         } else if (this.method_252() == Class_0417.ROTATE) {
            var6 /= Float.intBitsToFloat(1000593162);
            var7 /= Float.intBitsToFloat(1000593162);
            if (Class_0018.method_23()) {
               if (this.field_610) {
                  this.viewmodel.field_1929.method_78(Class_0930.method_4(var5.method_1210() + var7));
                  this.viewmodel.field_1930.method_78(Class_0930.method_4(var5.method_1211() + var6));
               } else {
                  this.viewmodel.field_1919.method_78(Class_0930.method_4(var5.method_1207() + var7));
                  this.viewmodel.field_1920.method_78(Class_0930.method_4(var5.method_1208() + var6));
               }
            } else {
               this.viewmodel.field_1929.method_78(Class_0930.method_4(var5.method_1210() + var7));
               this.viewmodel.field_1930.method_78(Class_0930.method_4(var5.method_1211() + var6));
               this.viewmodel.field_1919.method_78(Class_0930.method_4(var5.method_1207() + var7));
               this.viewmodel.field_1920.method_78(Class_0930.method_4(var5.method_1208() + var6));
            }
         }
      }
   }

   @Override
   public void method_5(double var1, double var3, int var5) {
      this.method_2(this.method_253(var5));
      if (this.method_252() != Class_0417.NONE) {
         this.field_607 = Class_1375.method_2(this.viewmodel);
         this.field_604.add(this.field_607);
         this.field_610 = var1 * Class_1067.method_78(this.field_1096)
            < (double)((float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824));
         this.field_608 = var1;
         this.field_609 = var3;
      }
   }

   @Override
   public void method_7(double var1, double var3, int var5) {
      this.method_2(Class_0417.NONE);
   }

   @Override
   public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      if (verticalAmount > Double.longBitsToDouble(-4616189618054758400L) && verticalAmount < Double.longBitsToDouble(4607182418800017408L)) {
         return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
      } else {
         boolean var9 = mouseX * Class_1067.method_78(this.field_1096)
            < (double)((float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824));
         float var10 = (float)(verticalAmount * Double.longBitsToDouble(-4631501856680443904L));
         if (this.field_605.method_9(100L)) {
            this.field_604.add(Class_1375.method_2(this.viewmodel));
         }

         this.field_605.reset();
         if (Class_0018.method_24()) {
            float var11 = Class_0930.method_4(this.viewmodel.field_1931.getValue() + (float)verticalAmount * Float.intBitsToFloat(1092616192));
            float var12 = Class_0930.method_4(this.viewmodel.field_1921.getValue() + (float)verticalAmount * Float.intBitsToFloat(1092616192));
            if (Class_0018.method_23()) {
               if (var9) {
                  this.viewmodel.field_1931.method_78(var11);
               } else {
                  this.viewmodel.field_1921.method_78(var12);
               }
            } else {
               this.viewmodel.field_1931.method_78(var11);
               this.viewmodel.field_1921.method_78(var12);
            }

            return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
         } else if (Class_0018.method_25()) {
            this.method_2(this.viewmodel.field_1918, this.viewmodel.field_1928, var9, var10);
            return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
         } else {
            this.method_2(this.viewmodel.field_1915, this.viewmodel.field_1925, var9, var10);
            return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
         }
      }
   }

   @Override
   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (Class_0018.method_23() && keyCode == 90 && !this.field_604.isEmpty()) {
         this.field_604.getLast().method_9(this.viewmodel);
         if (this.field_604.size() != 1) {
            this.field_604.removeLast();
         }
      }

      if (keyCode == 261) {
         this.field_607 = Class_1375.method_1194();
         this.field_607.method_9(this.viewmodel);
         this.field_604.add(this.field_607);
      }

      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   @Override
   public void method_38(DrawContext var1) {
      float var2 = (float)var1.getScaledWindowWidth();
      float var3 = (float)var1.getScaledWindowHeight();
      Class_0838.field_2672
         .method_9(
            var1.getMatrices(),
            var2 / Float.intBitsToFloat(1073741824) - Float.intBitsToFloat(1065353216),
            0.0F,
            var2 / Float.intBitsToFloat(1073741824) + Float.intBitsToFloat(1065353216),
            Float.intBitsToFloat(1111490560),
            field_603
         );
      List<String> var4 = List.of(
         "Left Click - Rotate",
         "Right Click - Move",
         "Middle Click - Scale",
         "Mouse Scroll - Move by Z",
         "Mouse Scroll + Shift - Rotate by Z",
         "Mouse Scroll + Alt - Scale by Z",
         "Hold Ctrl - Modify one",
         "Hold Shift - Lock axis",
         "Ctrl + Z - Undo",
         "Delete - Reset to defaults"
      );
      int var5 = 2;

      for (String var7 : var4) {
         float var8 = Class_1016.field_3143.method_221(var7);
         Class_1016.field_3143
            .method_9(var1, var7, (var2 - var8) / Float.intBitsToFloat(1073741824), (float)(50 + var5), Class_1081.method_9(Color.white, 180));
         var5 += Class_1016.field_3143.method_66() + 1;
      }

      Class_0838.field_2672
         .method_9(
            var1.getMatrices(),
            var2 / Float.intBitsToFloat(1073741824) - Float.intBitsToFloat(1065353216),
            (float)(var5 + 52),
            var2 / Float.intBitsToFloat(1073741824) + Float.intBitsToFloat(1065353216),
            var3,
            field_603
         );
   }

   public Class_0417 method_252() {
      return this.field_606;
   }

   public Class_0417 method_253(int var1) {
      return switch (var1) {
         case 0 -> Class_0417.ROTATE;
         case 1 -> Class_0417.TRANSLATE;
         case 2 -> Class_0417.SCALE;
         default -> Class_0417.NONE;
      };
   }

   public void method_2(Setting<Float> var1, Setting<Float> var2, boolean var3, float var4) {
      if (Class_0018.method_23()) {
         if (var3) {
            var2.method_78((Float)var2.getValue() + var4);
         } else {
            var1.method_78((Float)var1.getValue() + var4);
         }
      } else {
         var1.method_78((Float)var1.getValue() + var4);
         var2.method_78((Float)var2.getValue() + var4);
      }
   }

   public void method_2(Class_0417 var1) {
      this.field_606 = var1;
   }
}
