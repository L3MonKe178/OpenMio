package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.IRCModule;

public class Class_0915 implements Predicate {
   public IRCModule field_2896;

   public Class_0915(IRCModule var1) {
      super();
      this.field_2896 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2896.field_566.method_194() && this.field_2896.field_568.method_194();
   }
}
