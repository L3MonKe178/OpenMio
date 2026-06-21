package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_0827 implements Predicate {
   public BetterChatModule field_2641;

   public Class_0827(BetterChatModule var1) {
      super();
      this.field_2641 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2641.field_3925.method_194();
   }
}
