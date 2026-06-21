package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ReplenishModule;

public class Class_0782 implements Predicate {
   public ReplenishModule field_2441;

   public Class_0782(ReplenishModule var1) {
      super();
      this.field_2441 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2441.field_1347.method_194();
   }
}
