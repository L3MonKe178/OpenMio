package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_0606 implements Predicate {
   public BetterChatModule field_1880;

   public Class_0606(BetterChatModule var1) {
      super();
      this.field_1880 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1880.field_3937.method_194();
   }
}
