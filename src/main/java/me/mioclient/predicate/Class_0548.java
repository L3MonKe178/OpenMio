package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0151;
import me.mioclient.module.render.ShaderModule;

public class Class_0548 implements Predicate {
   public ShaderModule field_1753;

   public Class_0548(ShaderModule var1) {
      super();
      this.field_1753 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1753.field_2017.getValue() == Class_0151.RAINBOW;
   }
}
