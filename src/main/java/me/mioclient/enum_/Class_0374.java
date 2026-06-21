package me.mioclient.enum_;

import java.util.concurrent.TimeUnit;
import me.mioclient.api.Class_0013;
import me.mioclient.module.exploit.TimerModule;

public enum Class_0374 implements Class_0013 {
   NORMAL("Normal") {
      @Override
      public void method_2(TimerModule var1) {
         var1.field_1790 = var1.field_1782.getValue();
         var1.field_1789.method_2(var1, var1.field_1782.getValue());
      }
   },
   PULSE("Pulse") {
      @Override
      public void method_2(TimerModule var1) {
         boolean var2 = var1.field_1788
            .method_2((double)(var1.field_696 ? var1.field_1785.getValue() : var1.field_1786.getValue()).floatValue(), TimeUnit.SECONDS);
         var1.field_1790 = var1.field_696 ? var1.field_1784.getValue() : var1.field_1783.getValue();
         var1.field_1789.method_2(var1, var1.field_696 ? var1.field_1783.getValue() : var1.field_1784.getValue());
         if (var2) {
            var1.field_696 = !var1.field_696;
            var1.field_1788.reset();
         }
      }
   },
   DISABLE("Disable") {
      @Override
      public void method_2(TimerModule var1) {
         var1.field_1790 = var1.field_1782.getValue();
         var1.field_1789.method_2(var1, var1.field_1782.getValue());
         if (var1.field_1788.method_2((double)var1.field_1787.getValue().floatValue(), TimeUnit.SECONDS)) {
            var1.field_1788.reset();
            var1.disable();
         }
      }
   };

   public final String field_1208;

    Class_0374(String var3) {
      this.field_1208 = var3;
   }

   @Override
   public String getName() {
      return this.field_1208;
   }

   public abstract void method_2(TimerModule var1);
}
