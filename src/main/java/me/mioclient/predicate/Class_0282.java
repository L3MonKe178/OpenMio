package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0282 implements Predicate {
   public NameTagsModule field_883;

   public Class_0282(NameTagsModule var1) {
      super();
      this.field_883 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_883.field_1713.method_194();
   }
}
