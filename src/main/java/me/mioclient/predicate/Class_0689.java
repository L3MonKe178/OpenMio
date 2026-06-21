package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0151;
import me.mioclient.module.render.ShaderModule;

public class Class_0689 implements Predicate {
   public ShaderModule field_2190;

   public Class_0689(ShaderModule var1) {
      super();
      this.field_2190 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2190.field_2017.getValue() == Class_0151.RAINBOW;
   }
}
