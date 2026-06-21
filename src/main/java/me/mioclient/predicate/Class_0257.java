package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.BowAimModule;

public class Class_0257 implements Predicate {
   public BowAimModule field_784;

   public Class_0257(BowAimModule var1) {
      super();
      this.field_784 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_784.field_2419.method_194();
   }
}
