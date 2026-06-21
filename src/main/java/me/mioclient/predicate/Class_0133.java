package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0710;
import me.mioclient.module.player.ChestStealerModule;

public class Class_0133 implements Predicate {
   public ChestStealerModule field_400;

   public Class_0133(ChestStealerModule var1) {
      super();
      this.field_400 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_400.field_3203.getValue() != Class_0710.ANY;
   }
}
