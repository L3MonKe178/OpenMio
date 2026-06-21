package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ReplenishModule;

public class Class_0758 implements Predicate {
   public ReplenishModule field_2394;

   public Class_0758(ReplenishModule var1) {
      super();
      this.field_2394 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2394.field_1347.method_194();
   }
}
