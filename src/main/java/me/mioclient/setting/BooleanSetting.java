package me.mioclient.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.function.Predicate;

public final class BooleanSetting extends Setting<Boolean> {
   public BooleanSetting(String var1, Boolean var2, Predicate<Boolean> var3) {
      super(var1, var2, var3);
   }

   public BooleanSetting(String var1, Boolean var2) {
      super(var1, var2);
   }

   @Override
   public void method_78(String var1) {
      if ("toggle".equalsIgnoreCase(var1)) {
         this.method_78(!(Boolean)this.getValue());
      } else if (var1.equals("0") || var1.equalsIgnoreCase("false")) {
         this.method_78(false);
      } else if (var1.equals("1") || var1.equalsIgnoreCase("true")) {
         this.method_78(true);
      }
   }

   @Override
   public JsonElement toJson() {
      return new JsonPrimitive((Boolean)this.getValue());
   }

   @Override
   public void fromJson(JsonElement var1) {
      this.method_78(var1.getAsBoolean());
   }
}
