package me.mioclient.internal;

import me.mioclient.enum_.Class_1072;
import me.mioclient.record.Class_0702;

public class Class_0084 extends Class_0260 {
   public final Class_0242 field_284 = new Class_0242();

   public Class_0084(String var1, Class_0702 var2) {
      super(var1, Class_1072.DOUBLE_TAP, var2);
   }

   @Override
   public void run() {
      if (this.field_284.method_9(300L)) {
         this.field_284.reset();
      } else {
         for (String var2 : this.field_789) {
            this.method_7(var2);
         }

         this.field_284.setTime(-1L);
      }
   }
}
