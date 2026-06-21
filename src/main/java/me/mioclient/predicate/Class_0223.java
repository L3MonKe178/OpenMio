package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0223 implements Predicate {
   public NameTagsModule field_615;

   public Class_0223(NameTagsModule var1) {
      super();
      this.field_615 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_615.field_1713.method_194();
   }
}
