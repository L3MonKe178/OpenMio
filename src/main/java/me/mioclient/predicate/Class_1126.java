package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoCraftModule;

public class Class_1126 implements Predicate {
   public AutoCraftModule field_3480;

   public Class_1126(AutoCraftModule var1) {
      super();
      this.field_3480 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return !this.field_3480.field_2474.getValue();
   }
}
