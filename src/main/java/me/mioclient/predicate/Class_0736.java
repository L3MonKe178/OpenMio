package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0214;
import me.mioclient.module.abstract_.AbstractModule_42;

public class Class_0736 implements Predicate {
   public AbstractModule_42 field_2320;

   public Class_0736(AbstractModule_42 var1) {
      super();
      this.field_2320 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2320.field_768.getValue() == Class_0214.SMART;
   }
}
