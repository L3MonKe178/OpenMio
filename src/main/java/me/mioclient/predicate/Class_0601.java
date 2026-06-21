package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoBreedModule;

public class Class_0601 implements Predicate {
   public AutoBreedModule field_1870;

   public Class_0601(AutoBreedModule var1) {
      super();
      this.field_1870 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1870.field_811.method_194();
   }
}
