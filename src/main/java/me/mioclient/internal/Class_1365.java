package me.mioclient.internal;

import java.util.function.Consumer;
import me.mioclient.api.Class_0671;

public class Class_1365<T> implements Class_0671 {
   public final Class<?> field_4434;
   public final int field_3082;
   public final Consumer<T> field_292;

   public Class_1365(Class<?> var1, int var2, Consumer<T> var3) {
      super();
      this.field_4434 = var1;
      this.field_3082 = var2;
      this.field_292 = var3;
   }

   public Class_1365(Class<?> var1, Consumer<T> var2) {
      this(var1, 0, var2);
   }

   @Override
   public void method_37(Object var1) {
      this.field_292.accept((T)var1);
   }

   @Override
   public Class<?> method_119() {
      return this.field_4434;
   }

   @Override
   public int method_52() {
      return this.field_3082;
   }

   @Override
   public boolean method_120() {
      return false;
   }
}
