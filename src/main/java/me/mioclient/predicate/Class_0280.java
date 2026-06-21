package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0151;
import me.mioclient.module.render.ShaderModule;

public class Class_0280 implements Predicate {
   public ShaderModule field_882;

   public Class_0280(ShaderModule var1) {
      super();
      this.field_882 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_882.field_2017.getValue() == Class_0151.GRADIENT;
   }
}
