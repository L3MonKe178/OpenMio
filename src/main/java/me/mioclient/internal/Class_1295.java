package me.mioclient.internal;

import java.util.HashMap;
import java.util.function.Supplier;
import me.mioclient.setting.Setting;

public class Class_1295<E extends Enum<?>, T> {
   public final HashMap<E, T> field_4184 = new HashMap<>();
   public final Supplier<E> field_4185;

   public Class_1295(Supplier<E> var1) {
      super();
      this.field_4185 = var1;
   }

   public Class_1295(Setting<E> var1) {
      super();
      this.field_4185 = var1::getValue;
   }

   public T method_1156() {
      return this.field_4184.get(this.field_4185.get());
   }

   public void method_2(E var1, T var2) {
      this.field_4184.put((E)var1, (T)var2);
   }
}
