package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_0877 implements Predicate {
   public BetterChatModule field_2783;

   public Class_0877(BetterChatModule var1) {
      super();
      this.field_2783 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2783.field_3925.method_194() && this.field_2783.field_3930.getValue();
   }
}
