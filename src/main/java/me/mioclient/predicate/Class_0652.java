package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoBreedModule;

public class Class_0652 implements Predicate {
   public AutoBreedModule field_2099;

   public Class_0652(AutoBreedModule var1) {
      super();
      this.field_2099 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2099.field_811.method_194();
   }
}
