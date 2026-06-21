package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.module.Module;

public final class Class_0005 implements MioAPI, Class_1146 {
   public final Map<Module, String> field_13 = new HashMap<>();
   public final Map<String, String> field_14 = new HashMap<>();

   public Class_0005() {
      super();
   }

   public void method_2(Module var1, String var2) {
      this.field_13.compute(var1, (var1x, var2x) -> var2);
   }

   public void method_4(String var1, String var2) {
      this.field_14.compute(var1, (var1x, var2x) -> var2);
   }

   public void method_5(Module var1) {
      this.field_13.remove(var1);
   }

   public void method_6(String var1) {
      this.field_14.remove(var1);
   }

   public String method_7(Module var1) {
      return this.field_13.getOrDefault(var1, var1.getName());
   }

   public String method_8(String var1) {
      return this.field_14.getOrDefault(var1, var1);
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      JsonObject var2 = new JsonObject();
      JsonObject var3 = new JsonObject();
      this.field_13.forEach((var1x, var2x) -> var2.addProperty(var1x.getName(), var2x));
      this.field_14.forEach(var3::addProperty);
      var1.add("module", var2);
      var1.add("players", var3);
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();
      if (var2.has("module")) {
         var2.getAsJsonObject("module")
            .asMap()
            .forEach(
               (var1x, var2x) -> Hub.field_2599
                     .method_2(var1xx -> var1xx.getName().equalsIgnoreCase(var1x))
                     .ifPresent(var2xx -> this.method_2(var2xx, var2x.getAsString()))
            );
      }

      if (var2.has("players")) {
         var2.getAsJsonObject("players").asMap().forEach((var1x, var2x) -> this.method_4(var1x, var2x.getAsString()));
      }
   }

   @Override
   public String getConfigName() {
      return "aliases.json";
   }
}
