package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.module.Module;

public class Class_1152 {
   public static final Class_1152 field_3545 = new Class_1152();

   public Class_1152() {
      super();
   }

   public void method_9(JsonObject var1) {
      for (JsonElement var3 : var1.asMap().values()) {
         if (var3 instanceof JsonObject) {
            JsonObject var4 = (JsonObject)var3;
            var4.remove("key");
            if (var4.has("settings")) {
               JsonElement var5 = var4.get("settings");
               if (var5.isJsonObject()) {
                  ArrayList<String> var6 = new ArrayList<>();

                  for (Entry var8 : var5.getAsJsonObject().asMap().entrySet()) {
                     if (((JsonElement)var8.getValue()).isJsonObject()) {
                        var6.add((String)var8.getKey());
                     }
                  }

                  for (String var10 : var6) {
                     var5.getAsJsonObject().remove(var10);
                  }
               }
            }
         }
      }
   }

   public void method_2(JsonObject var1, Predicate<Module> var2) {
      for (String var4 : var1.asMap().keySet()) {
         JsonElement var6 = var1.get(var4);
         if (var6 instanceof JsonObject) {
            JsonObject var5 = (JsonObject)var6;
            Optional var12 = Hub.field_2599.method_2(var1x -> var1x.getConfigName().equals(var4));
            if (!var12.isEmpty()) {
               boolean var7 = var2.test((Module)var12.get());
               if (!var7) {
                  var5.remove("enabled");
               }

               var5.remove("key");
               if (var5.has("settings")) {
                  JsonElement var8 = var5.get("settings");
                  if (var8.isJsonObject()) {
                     ArrayList<String> var9 = new ArrayList<>();

                     for (Entry var11 : var8.getAsJsonObject().asMap().entrySet()) {
                        if (var7) {
                           break;
                        }

                        if (!((JsonElement)var11.getValue()).isJsonObject()) {
                           var9.add((String)var11.getKey());
                        }
                     }

                     for (String var14 : var9) {
                        var8.getAsJsonObject().remove(var14);
                     }
                  }
               }
            }
         }
      }
   }

   public void method_5(JsonObject var1) {
      for (JsonElement var3 : var1.asMap().values()) {
         if (var3 instanceof JsonObject var4) {
            var4.remove("enabled");
            var4.remove("settings");
         }
      }
   }
}
