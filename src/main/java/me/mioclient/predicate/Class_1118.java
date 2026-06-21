package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.abstract_.AbstractModule_2;

public class Class_1118 implements Predicate {
   public AbstractModule_2 field_3471;

   public Class_1118(AbstractModule_2 var1) {
      super();
      this.field_3471 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3471.field_794.method_194();
   }
}
