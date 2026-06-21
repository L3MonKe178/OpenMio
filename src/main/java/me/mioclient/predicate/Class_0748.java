package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0388;
import me.mioclient.module.abstract_.AbstractModule_16;

public class Class_0748 implements Predicate {
   public AbstractModule_16 field_2386;

   public Class_0748(AbstractModule_16 var1) {
      super();
      this.field_2386 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2386.field_1331.getValue() != Class_0388.field_1259;
   }
}
