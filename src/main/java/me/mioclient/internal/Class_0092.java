package me.mioclient.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0046;
import me.mioclient.enum_.Class_1072;
import me.mioclient.event.Event_18;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.record.Class_0702;

public final class Class_0092 extends Registry<Class_0260> implements MioAPI, Class_1146 {
   public Class_0092() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_18 var1) {
      if (field_4219.currentScreen == null && !this.method_535()) {
         for (Class_0260 var3 : this.field_3243) {
            if (var3.getKeybind().method_39() == var1.method_615() && var3.getKeybind().method_38() == var1.method_614()) {
               var3.run();
               field_4220.method_36(new Class_0368(var3));
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (field_4219.currentScreen == null && !this.method_535()) {
         for (Class_0260 var3 : this.field_3243) {
            if (var3 instanceof Class_0678 var4) {
               var4.method_92(Class_0018.method_9(var4.getKeybind()));
            }
         }
      }
   }

   @Override
   public JsonElement toJson() {
      JsonArray var1 = new JsonArray();
      JsonObject var2 = new JsonObject();

      for (Class_0260 var4 : this.field_3243) {
         JsonObject var5 = new JsonObject();
         var5.addProperty("name", var4.getName());
         var5.addProperty("type", var4.method_2().getName());
         var5.addProperty("key", var4.getKeybind().method_38());
         var5.addProperty("mouse", var4.getKeybind().method_39());
         JsonArray var6 = new JsonArray();

         for (String var8 : var4.method_9()) {
            var6.add(var8);
         }

         var5.add("commands", var6);
         var1.add(var5);
      }

      var2.add("macros", var1);
      return var2;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonObject() && var1.getAsJsonObject().has("macros")) {
         for (JsonElement var4 : var1.getAsJsonObject().getAsJsonArray("macros")) {
            if (var4.isJsonObject()) {
               JsonObject var5 = var4.getAsJsonObject();
               if (Class_0033.method_2(var5, "name", "type", "key", "mouse", "commands")) {
                  try {
                     Class_1072 var6 = Class_1072.method_29(var5.get("type").getAsString());
                     Class_0260 var7 = var6.method_2(
                        var5.get("name").getAsString(), new Class_0702(var5.get("key").getAsInt(), Class_0046.TOGGLE, var5.get("mouse").getAsBoolean())
                     );

                     for (JsonElement var9 : var5.get("commands").getAsJsonArray()) {
                        var7.method_9().add(var9.getAsString());
                     }

                     if (var6 == Class_1072.HOLD) {
                        while (var7.method_9().size() > 2) {
                           var7.method_9().removeLast();
                        }
                     }

                     this.method_2(var7);
                  } catch (Exception var10) {
                  }
               }
            }
         }
      }
   }

   @Override
   public String getConfigName() {
      return "macros.json";
   }
}
