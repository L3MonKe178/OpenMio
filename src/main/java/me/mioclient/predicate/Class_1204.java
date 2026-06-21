package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_1204 implements Predicate {
   public BetterChatModule field_3736;

   public Class_1204(BetterChatModule var1) {
      super();
      this.field_3736 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3736.field_3925.method_194();
   }
}
