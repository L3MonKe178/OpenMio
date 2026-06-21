package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_1173 implements Predicate {
   public BetterChatModule field_3638;

   public Class_1173(BetterChatModule var1) {
      super();
      this.field_3638 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3638.field_3937.method_194();
   }
}
