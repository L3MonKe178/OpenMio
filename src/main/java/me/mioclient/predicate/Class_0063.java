package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ScaffoldModule;

public class Class_0063 implements Predicate {
   public ScaffoldModule field_189;

   public Class_0063(ScaffoldModule var1) {
      super();
      this.field_189 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_189.field_158.method_194();
   }
}
