package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_0733 implements Predicate {
   public BetterChatModule field_2319;

   public Class_0733(BetterChatModule var1) {
      super();
      this.field_2319 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2319.field_3925.method_194();
   }
}
