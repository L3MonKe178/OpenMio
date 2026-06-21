package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.client.IRCModule;

public class Class_1242 implements Predicate {
   public IRCModule field_3908;

   public Class_1242(IRCModule var1) {
      super();
      this.field_3908 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3908.field_566.method_194() && this.field_3908.field_568.method_194();
   }
}
