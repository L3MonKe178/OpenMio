package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ScaffoldModule;

public class Class_1066 implements Predicate {
   public ScaffoldModule field_3273;

   public Class_1066(ScaffoldModule var1) {
      super();
      this.field_3273 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3273.field_158.method_194();
   }
}
