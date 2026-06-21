package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_1036 implements Predicate {
   public NameTagsModule field_3199;

   public Class_1036(NameTagsModule var1) {
      super();
      this.field_3199 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3199.field_1713.method_194();
   }
}
