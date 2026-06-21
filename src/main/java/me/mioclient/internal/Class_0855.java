package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;

public class Class_0855 {
   public static final int field_2753 = 5;
   public final List<String> field_2754 = new ArrayList<>();
   public final Class_0242 field_2755 = new Class_0242();
   public int field_1323 = 0;

   public Class_0855() {
      super();
   }

   public void method_142() {
      if (this.field_2755.method_9(1500L)) {
         this.field_1323++;
         this.field_2755.reset();
      }

      if (this.field_1323 >= this.field_2754.size()) {
         this.field_1323 = 0;
      }
   }

   public void reset() {
      this.field_2754.clear();
      this.field_1323 = 0;
   }

   public List<String> method_796() {
      if (this.field_2754.size() <= 5) {
         return this.field_2754;
      } else {
         ArrayList var1 = new ArrayList();

         for (int var2 = 0; var2 < 5; var2++) {
            var1.add(this.field_2754.get((var2 + this.field_1323) % this.field_2754.size()));
         }

         return var1;
      }
   }

   public int method_36() {
      return this.field_1323;
   }

   public List<String> method_797() {
      return this.field_2754;
   }
}
