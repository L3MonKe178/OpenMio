package me.mioclient.internal;

import me.mioclient.enum_.Class_1072;
import me.mioclient.record.Class_0702;

public class Class_0678 extends Class_0260 {
   public boolean field_2175;

   public Class_0678(String var1, Class_0702 var2) {
      super(var1, Class_1072.HOLD, var2);
   }

   @Override
   public void run() {
   }

   public void method_92(boolean var1) {
      if (this.field_2175 != var1) {
         this.field_2175 = var1;
         if (!this.field_789.isEmpty()) {
            if (this.field_2175) {
               this.method_7(this.field_789.getFirst());
            } else {
               this.method_7(this.field_789.getLast());
            }
         }
      }
   }
}
