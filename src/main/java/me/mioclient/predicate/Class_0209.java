package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.BetterChatModule;

public class Class_0209 implements Predicate {
   public BetterChatModule field_590;

   public Class_0209(BetterChatModule var1) {
      super();
      this.field_590 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_590.field_3925.method_194();
   }
}
