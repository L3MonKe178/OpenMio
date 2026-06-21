package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.NoneType;
import me.mioclient.module.player.AutoCraftModule;

public class Class_0058 implements Predicate {
   public AutoCraftModule field_143;

   public Class_0058(AutoCraftModule var1) {
      super();
      this.field_143 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_143.field_2473.getValue() == NoneType.NONE;
   }
}
