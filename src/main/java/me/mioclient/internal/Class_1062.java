package me.mioclient.internal;

import me.mioclient.record.Class_0371;

public final class Class_1062 extends Class_1266 {
   public final Class_0311 field_3270;
   public final Class_0371 field_1636;

   public Class_1062(Class_0311 var1, Class_0746 var2, Class_0371 var3) {
      super(var2, var3.getName(), () -> {
         var1.method_368().method_9(var3);
         var1.method_368().method_142();
      });
      this.field_1636 = var3;
      this.field_3270 = var1;
   }

   @Override
   public boolean method_949() {
      return this.field_1636.equals(this.field_3270.method_368().method_550());
   }
}
