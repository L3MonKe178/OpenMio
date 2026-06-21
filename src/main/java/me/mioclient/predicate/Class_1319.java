package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.ScaffoldModule;

public class Class_1319 implements Predicate {
   public ScaffoldModule field_4244;

   public Class_1319(ScaffoldModule var1) {
      super();
      this.field_4244 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4244.field_158.method_194();
   }
}
