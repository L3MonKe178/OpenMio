package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.PusherModule;

public class Class_0268 implements Predicate {
   public PusherModule field_838;

   public Class_0268(PusherModule var1) {
      super();
      this.field_838 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_838.field_3012.method_194();
   }
}
