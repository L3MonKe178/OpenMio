package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.ShaderModule;

public class Class_0609 implements Predicate {
   public ShaderModule field_1884;

   public Class_0609(ShaderModule var1) {
      super();
      this.field_1884 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1884.field_2019.getValue() > 2;
   }
}
