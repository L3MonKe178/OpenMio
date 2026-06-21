package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0214;
import me.mioclient.module.abstract_.AbstractModule_42;

public class Class_1070 implements Predicate {
   public AbstractModule_42 field_3283;

   public Class_1070(AbstractModule_42 var1) {
      super();
      this.field_3283 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3283.field_768.getValue() == Class_0214.SMART;
   }
}
