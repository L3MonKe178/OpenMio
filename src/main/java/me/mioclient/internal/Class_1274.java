package me.mioclient.internal;

import com.google.gson.JsonElement;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import me.mioclient.setting.CustomSetting2;

public final class Class_1274 extends CustomSetting2<String> {
   public Class_1274(String var1, String... var2) {
      super(var1, var2);
   }

   public Class_1274(String var1, Predicate<Set<String>> var2, String... var3) {
      super(var1, var2, var3);
   }

   @Override
   public String method_38(String var1) {
      return var1.toLowerCase(Locale.ROOT);
   }

   @Override
   public String method_9(String var1) {
      return var1.toLowerCase(Locale.ROOT);
   }

   @Override
   public Collection<String> method_230() {
      return Collections.emptyList();
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonArray()) {
         ((Set)this.getValue()).clear();

         for (JsonElement var4 : var1.getAsJsonArray()) {
            String var5 = var4.getAsString();
            ((Set)this.getValue()).add(var5);
         }
      }
   }
}
