package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.AmbienceModule;

public class Class_0592 implements Predicate {
   public AmbienceModule field_1855;

   public Class_0592(AmbienceModule var1) {
      super();
      this.field_1855 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1855.field_210.method_194() && !this.field_1855.field_211.getValue();
   }
}
