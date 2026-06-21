package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_0418 implements Predicate {
   public KillEffectsModule field_1330;

   public Class_0418(KillEffectsModule var1) {
      super();
      this.field_1330 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1330.field_886.method_194() && this.field_1330.field_887.method_194();
   }
}
