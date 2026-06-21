package me.mioclient.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.module.misc.StashFinderModule;

public final class Class_0306 extends Registry<Class_0580> implements MioAPI, Class_1146 {
   public static StashFinderModule field_993 = Hub.field_2595.method_78(StashFinderModule.class);

   public Class_0306() {
      super();
      field_4220.method_14(this);
   }

   @Override
   public JsonElement toJson() {
      if (!field_993.field_1501.getValue()) {
         return new JsonObject();
      } else {
         JsonArray var1 = new JsonArray();
         JsonObject var2 = new JsonObject();

         for (Class_0580 var4 : (Iterable<Class_0580>)(Iterable<?>)((List)Hub.field_2618.getRegistry())) {
            var1.add(field_4218.toJsonTree(var4));
         }

         var2.add("stashes", var1);
         return var2;
      }
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonObject() && var1.getAsJsonObject().has("stashes")) {
         for (JsonElement var4 : var1.getAsJsonObject().getAsJsonArray("stashes")) {
            try {
               this.field_3243.add((Class_0580)field_4218.fromJson(var4, Class_0580.class));
            } catch (Exception var6) {
            }
         }
      }
   }

   @Override
   public String getConfigName() {
      return "stashfinder.json";
   }
}
