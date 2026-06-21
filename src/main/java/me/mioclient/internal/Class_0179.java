package me.mioclient.internal;

import me.mioclient.enum_.Class_1072;
import me.mioclient.record.Class_0702;

public class Class_0179 extends Class_0260 {
   public int field_503 = 0;

   public Class_0179(String var1, Class_0702 var2) {
      super(var1, Class_1072.QUEUE, var2);
   }

   @Override
   public void run() {
      if (!this.field_789.isEmpty()) {
         this.field_503 = this.field_503 % this.field_789.size();
         String var1 = this.field_789.get(this.field_503);
         String[] var2 = var1.split(";");

         for (String var6 : var2) {
            this.method_7(var6);
         }

         this.field_503++;
      }
   }
}
