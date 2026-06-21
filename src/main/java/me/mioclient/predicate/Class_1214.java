package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ScaffoldModule;

public class Class_1214 implements Predicate {
   public ScaffoldModule field_3799;

   public Class_1214(ScaffoldModule var1) {
      super();
      this.field_3799 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3799.field_158.method_194();
   }
}
