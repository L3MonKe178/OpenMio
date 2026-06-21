package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoBreedModule;

public class Class_0772 implements Predicate {
   public AutoBreedModule field_2428;

   public Class_0772(AutoBreedModule var1) {
      super();
      this.field_2428 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2428.field_811.method_194();
   }
}
