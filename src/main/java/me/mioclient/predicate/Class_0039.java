package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0207;
import me.mioclient.module.abstract_.AbstractModule_36;

public class Class_0039 implements Predicate {
   public AbstractModule_36 field_78;

   public Class_0039(AbstractModule_36 var1) {
      super();
      this.field_78 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_78.field_663.getValue() == Class_0207.MOTION;
   }
}
