package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_1318 implements Predicate {
   public NameTagsModule field_4243;

   public Class_1318(NameTagsModule var1) {
      super();
      this.field_4243 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4243.field_1713.method_194();
   }
}
