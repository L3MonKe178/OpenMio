package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import me.mioclient.api.Class_1309;
import me.mioclient.record.Class_0559;

public final class Class_0465 extends Class_0939<Class_0559> implements Class_1309 {
   public final List<CompletableFuture<?>> field_1472 = new ArrayList<>();
   public int field_1473 = 0;

   public Class_0465() {
      super();
   }

   public void method_2(String var1, BiConsumer<String, ? super Throwable> var2) {
   }

   public boolean method_497() {
      for (CompletableFuture var2 : this.field_1472) {
         if (!var2.isDone()) {
            return false;
         }
      }

      return true;
   }

   public boolean method_498() {
      for (CompletableFuture var2 : this.field_1472) {
         if (var2.isCompletedExceptionally()) {
            return true;
         }
      }

      return false;
   }

   public int method_499() {
      return this.field_1473;
   }
}
