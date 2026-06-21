package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0568;
import me.mioclient.module.misc.ExtraScreenshotModule;

public class Class_0796 implements Predicate {
   public ExtraScreenshotModule field_2517;

   public Class_0796(ExtraScreenshotModule var1) {
      super();
      this.field_2517 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2517.field_2794.getValue() != Class_0568.NONE;
   }
}
