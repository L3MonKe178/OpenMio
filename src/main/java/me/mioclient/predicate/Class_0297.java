package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.PusherModule;

public class Class_0297 implements Predicate {
   public PusherModule field_950;

   public Class_0297(PusherModule var1) {
      super();
      this.field_950 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_950.field_3012.method_194();
   }
}
