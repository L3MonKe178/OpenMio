package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_1034 implements Predicate {
   public NameTagsModule field_3194;

   public Class_1034(NameTagsModule var1) {
      super();
      this.field_3194 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3194.field_1713.method_194();
   }
}
