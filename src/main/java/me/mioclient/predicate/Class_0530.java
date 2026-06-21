package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1296;
import me.mioclient.module.render.AmbienceModule;

public class Class_0530 implements Predicate {
   public AmbienceModule field_1672;

   public Class_0530(AmbienceModule var1) {
      super();
      this.field_1672 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1672.field_214.getValue() != Class_1296.DUSTY;
   }
}
