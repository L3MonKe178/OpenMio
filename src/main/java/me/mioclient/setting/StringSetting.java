package me.mioclient.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.function.Predicate;

public final class StringSetting extends Setting<String> {
   public StringSetting(String var1, String var2, Predicate<String> var3) {
      super(var1, var2, var3);
   }

   public StringSetting(String var1, String var2) {
      super(var1, var2);
   }

   @Override
   public void method_78(String var1) {
      this.method_4(var1);
      this.field_3125 = this.field_3126;
   }

   @Override
   public JsonElement toJson() {
      return new JsonPrimitive((String)this.getValue());
   }

   @Override
   public void fromJson(JsonElement var1) {
      this.method_78(var1.getAsString());
   }
}
