package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0153 implements Predicate {
   public NameTagsModule field_444;

   public Class_0153(NameTagsModule var1) {
      super();
      this.field_444 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_444.field_1713.method_194();
   }
}
