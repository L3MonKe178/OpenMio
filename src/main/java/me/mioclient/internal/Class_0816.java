package me.mioclient.internal;

import java.awt.Color;
import me.mioclient.enum_.Class_0266;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0816 extends Class_0370 {
   public boolean field_2585 = false;
   public boolean field_2586 = false;
   public boolean field_1804 = false;
   public int field_2587 = 0;
   public int field_2588 = 0;
   public int field_2589 = 0;
   public Class_1227 field_2590;
   public Class_0266[] field_2591;

   public Class_0816(Class_0746 var1, int var2) {
      super(var1, var2, 22, 22);
   }

   @Override
   public void method_411() {
      if (this.field_2590 == null) {
         this.method_47();
      }

      if (!this.field_2586) {
         if (this.field_2585) {
            this.field_2585 = false;
            this.method_751();
         } else {
            this.method_748();
         }
      }
   }

   @Override
   public int method_412() {
      return 500;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      super.method_2(var1, var2, var3, var5);

      for (int var7 = 0; var7 < this.field_1201; var7++) {
         for (int var8 = 0; var8 < this.field_1200; var8++) {
            Class_0266 var9 = this.method_14(var8, this.field_1201 - var7 - 1);
            if (var9 != Class_0266.field_827) {
               this.method_2(var2, var8, var7, var9.method_152());
            }
         }
      }

      if (this.field_2590.method_1073() != Class_0266.field_827) {
         Class_1227 var13 = new Class_1227();
         var13.method_2(this.field_2590.method_1073());

         for (int var15 = 0; var15 < 4; var15++) {
            var13.method_3(var15, this.field_2590.method_123(var15));
            var13.method_36(var15, this.field_2590.method_1009(var15));
            int var17 = this.field_2588 + this.field_2590.method_123(var15);
            int var10 = this.field_2589 - this.field_2590.method_1009(var15);
            Color var11 = this.field_2590.method_1073().method_152();
            this.method_2(var2, var17, this.field_1201 - var10 - 1, var11);
         }

         int var16 = this.field_2589;

         while (var16 > 0 && this.method_2(var13, this.field_2588, var16 - 1)) {
            var16--;
         }

         for (int var18 = 0; var18 < 4; var18++) {
            int var19 = this.field_2588 + var13.method_123(var18);
            int var20 = var16 - var13.method_1009(var18);
            Color var12 = this.field_2590.method_1073().method_152();
            this.method_2(var2, var19, this.field_1201 - var20 - 1, Class_1081.method_9(var12, 50));
         }
      }

      if (this.field_1804) {
         int var10000 = this.field_2587;
         String var14 = new Class_1303().method_2(var10000).method_9("Score \u0001. Press R.");
         Class_1016.field_3143
            .method_9(
               var1,
               var14,
               (float)(
                  (double)this.method_167().getX()
                     + (double)this.method_167().method_216() * Class_0245.field_688
                     - (double)Class_1016.field_3143.method_221(var14) * Class_0245.field_688
               ),
               (float)((double)(this.method_167().getY() + this.field_419) + (double)this.method_66() * Class_0245.field_688),
               Color.white
            );
      } else {
         Class_1016.field_3143
            .method_9(
               var1,
               String.valueOf(this.field_2587),
               (float)this.method_167().getX() + Float.intBitsToFloat(1069547520),
               (float)(this.method_167().getY() + this.field_419 + 1),
               Float.intBitsToFloat(1061158912),
               Color.white
            );
      }
   }

   @Override
   public void method_68(int var1) {
      switch (var1) {
         case 32:
            this.method_747();
            break;
         case 68:
            this.method_748();
            break;
         case 82:
            this.method_47();
            break;
         case 262:
            this.method_9(this.field_2590, this.field_2588 + 1, this.field_2589);
            break;
         case 263:
            this.method_9(this.field_2590, this.field_2588 - 1, this.field_2589);
            break;
         case 264:
            this.method_9(this.field_2590.method_1078(), this.field_2588, this.field_2589);
            break;
         case 265:
            this.method_9(this.field_2590.method_1077(), this.field_2588, this.field_2589);
      }
   }

   public void method_47() {
      this.field_1804 = false;
      this.field_2590 = new Class_1227();
      this.field_2591 = new Class_0266[this.field_1200 * this.field_1201];
      this.method_749();
      this.method_751();
   }

   public Class_0266 method_14(int var1, int var2) {
      return this.field_2591[var2 * this.field_1200 + var1];
   }

   public void method_747() {
      int var1 = this.field_2589;

      while (var1 > 0 && this.method_9(this.field_2590, this.field_2588, var1 - 1)) {
         var1--;
      }

      this.method_750();
   }

   public void method_748() {
      if (!this.method_9(this.field_2590, this.field_2588, this.field_2589 - 1)) {
         this.method_750();
      }
   }

   public void method_749() {
      this.field_2587 = 0;

      for (int var1 = 0; var1 < this.field_1201 * this.field_1200; var1++) {
         this.field_2591[var1] = Class_0266.field_827;
      }
   }

   public void method_750() {
      for (int var1 = 0; var1 < 4; var1++) {
         int var2 = this.field_2588 + this.field_2590.method_123(var1);
         int var3 = this.field_2589 - this.field_2590.method_1009(var1);
         this.field_2591[var3 * this.field_1200 + var2] = this.field_2590.method_1073();
      }

      this.method_752();
      if (!this.field_2585) {
         this.method_751();
      }
   }

   public void method_751() {
      this.field_2590.method_1074();
      this.field_2588 = this.field_1200 / 2 + 1;
      this.field_2589 = this.field_1201 - 1 + this.field_2590.method_1076();
      if (!this.method_9(this.field_2590, this.field_2588, this.field_2589)) {
         this.field_2590.method_2(Class_0266.field_827);
         this.field_1804 = true;
      }
   }

   public boolean method_2(Class_1227 var1, int var2, int var3) {
      for (int var4 = 0; var4 < 4; var4++) {
         int var5 = var2 + var1.method_123(var4);
         int var6 = var3 - var1.method_1009(var4);
         if (var5 < 0 || var5 >= this.field_1200 || var6 < 0 || var6 >= this.field_1201) {
            return false;
         }

         if (this.method_14(var5, var6) != Class_0266.field_827) {
            return false;
         }
      }

      return true;
   }

   public boolean method_9(Class_1227 var1, int var2, int var3) {
      if (!this.method_2(var1, var2, var3)) {
         return false;
      } else {
         this.field_2590 = var1;
         this.field_2588 = var2;
         this.field_2589 = var3;
         return true;
      }
   }

   public void method_752() {
      int var1 = 0;

      for (int var2 = this.field_1201 - 1; var2 >= 0; var2--) {
         boolean var3 = true;

         for (int var4 = 0; var4 < this.field_1200; var4++) {
            if (this.method_14(var4, var2) == Class_0266.field_827) {
               var3 = false;
               break;
            }
         }

         if (var3) {
            var1++;

            for (int var6 = var2; var6 < this.field_1201 - 1; var6++) {
               for (int var5 = 0; var5 < this.field_1200; var5++) {
                  this.field_2591[var6 * this.field_1200 + var5] = this.method_14(var5, var6 + 1);
               }
            }
         }
      }

      if (var1 > 0) {
         this.field_2587 += var1;
         this.field_2585 = true;
         this.field_2590.method_2(Class_0266.field_827);
      }
   }

   @Override
   public int method_66() {
      return this.method_167().method_216();
   }
}
