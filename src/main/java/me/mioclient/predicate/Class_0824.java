package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.NameTagsModule;

public class Class_0824 implements Predicate {
   public NameTagsModule field_2638;

   public Class_0824(NameTagsModule var1) {
      super();
      this.field_2638 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2638.field_1728.method_194();
   }
}
