package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoBreedModule;

public class Class_0784 implements Predicate {
   public AutoBreedModule field_2448;

   public Class_0784(AutoBreedModule var1) {
      super();
      this.field_2448 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2448.field_811.method_194();
   }
}
