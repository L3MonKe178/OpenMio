package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0622 implements Predicate {
   public NameTagsModule field_1988;

   public Class_0622(NameTagsModule var1) {
      super();
      this.field_1988 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1988.field_1713.method_194();
   }
}
