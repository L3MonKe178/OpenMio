package me.mioclient.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.function.Predicate;
import me.mioclient.api.Class_0013;
import me.mioclient.internal.Class_0841;

public final class CustomSetting<T extends Enum<T>> extends Setting<T> {
   public final Class_0841 field_589 = new Class_0841((Class<? extends Enum<?>>)this.getValue().getDeclaringClass());

   public CustomSetting(String var1, T var2, Predicate<T> var3) {
      super(var1, (T)var2, var3);
      if (!Class_0013.method_16(var2)) {
         throw new IllegalArgumentException();
      }
   }

   public CustomSetting(String var1, T var2) {
      super(var1, (T)var2);
      if (!Class_0013.method_16(var2)) {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void method_78(String var1) {
      this.method_78((T)this.field_589.method_9(new JsonPrimitive(var1)));
   }

   @Override
   public JsonElement toJson() {
      return this.field_589.method_7(this.getValue());
   }

   @Override
   public void fromJson(JsonElement var1) {
      this.method_78((T)this.field_589.method_9(var1));
   }
}
