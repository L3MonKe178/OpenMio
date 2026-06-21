package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.BowAimModule;

public class Class_1051 implements Predicate {
   public BowAimModule field_3247;

   public Class_1051(BowAimModule var1) {
      super();
      this.field_3247 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3247.field_2419.method_194() && this.field_3247.field_2420.method_194();
   }
}
