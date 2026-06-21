package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.BowAimModule;

public class Class_1347 implements Predicate {
   public BowAimModule field_4344;

   public Class_1347(BowAimModule var1) {
      super();
      this.field_4344 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4344.field_2419.method_194();
   }
}
