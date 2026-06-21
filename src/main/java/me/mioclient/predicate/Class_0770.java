package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0151;
import me.mioclient.module.render.ShaderModule;

public class Class_0770 implements Predicate {
   public ShaderModule field_2426;

   public Class_0770(ShaderModule var1) {
      super();
      this.field_2426 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2426.field_2017.getValue() == Class_0151.RAINBOW;
   }
}
