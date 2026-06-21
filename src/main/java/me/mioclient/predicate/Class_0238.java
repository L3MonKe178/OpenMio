package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoBreedModule;

public class Class_0238 implements Predicate {
   public AutoBreedModule field_651;

   public Class_0238(AutoBreedModule var1) {
      super();
      this.field_651 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_651.field_811.method_194();
   }
}
