package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.BowAimModule;

public class Class_0293 implements Predicate {
   public BowAimModule field_946;

   public Class_0293(BowAimModule var1) {
      super();
      this.field_946 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_946.field_2419.method_194();
   }
}
