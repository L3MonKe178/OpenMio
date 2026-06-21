package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoBreedModule;

public class Class_0061 implements Predicate {
   public AutoBreedModule field_183;

   public Class_0061(AutoBreedModule var1) {
      super();
      this.field_183 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_183.field_811.method_194();
   }
}
