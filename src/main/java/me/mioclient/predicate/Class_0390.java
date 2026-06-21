package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_0390 implements Predicate {
   public BetterChatModule field_1264;

   public Class_0390(BetterChatModule var1) {
      super();
      this.field_1264 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1264.field_3942.method_194();
   }
}
