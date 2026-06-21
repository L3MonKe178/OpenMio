package me.mioclient.internal;

import java.util.Random;
import me.mioclient.enum_.Class_0266;

public class Class_1227 {
   public Class_0266 field_3866;
   public int[][] field_3867;
   public int[][][] field_3868;
   public Random field_3869 = new Random();

   public Class_1227() {
      super();
      this.method_1072();
   }

   public void method_1072() {
      this.field_3867 = new int[4][2];
      this.field_3868 = new int[][][]{
         {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
         {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
         {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
         {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
         {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
         {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
         {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
         {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
      };
      this.method_2(Class_0266.field_827);
   }

   public void method_2(Class_0266 var1) {
      for (int var2 = 0; var2 < 4; var2++) {
         System.arraycopy(this.field_3868[var1.ordinal()][var2], 0, this.field_3867[var2], 0, 2);
      }

      this.field_3866 = var1;
   }

   public void method_3(int var1, int var2) {
      this.field_3867[var1][0] = var2;
   }

   public void method_36(int var1, int var2) {
      this.field_3867[var1][1] = var2;
   }

   public int method_123(int var1) {
      return this.field_3867[var1][0];
   }

   public int method_1009(int var1) {
      return this.field_3867[var1][1];
   }

   public Class_0266 method_1073() {
      return this.field_3866;
   }

   public void method_1074() {
      int var1 = Math.abs(this.field_3869.nextInt()) % 7 + 1;
      Class_0266[] var2 = Class_0266.values();
      this.method_2(var2[var1]);
   }

   public int method_1075() {
      int var1 = this.field_3867[0][0];

      for (int var2 = 0; var2 < 4; var2++) {
         var1 = Math.min(var1, this.field_3867[var2][0]);
      }

      return var1;
   }

   public int method_1076() {
      int var1 = this.field_3867[0][1];

      for (int var2 = 0; var2 < 4; var2++) {
         var1 = Math.min(var1, this.field_3867[var2][1]);
      }

      return var1;
   }

   public Class_1227 method_1077() {
      if (this.field_3866 == Class_0266.field_832) {
         return this;
      } else {
         Class_1227 var1 = new Class_1227();
         var1.field_3866 = this.field_3866;

         for (int var2 = 0; var2 < 4; var2++) {
            var1.method_3(var2, this.method_1009(var2));
            var1.method_36(var2, -this.method_123(var2));
         }

         return var1;
      }
   }

   public Class_1227 method_1078() {
      if (this.field_3866 == Class_0266.field_832) {
         return this;
      } else {
         Class_1227 var1 = new Class_1227();
         var1.field_3866 = this.field_3866;

         for (int var2 = 0; var2 < 4; var2++) {
            var1.method_3(var2, -this.method_1009(var2));
            var1.method_36(var2, this.method_123(var2));
         }

         return var1;
      }
   }
}
