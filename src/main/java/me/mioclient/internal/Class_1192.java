package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_63;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_11;
import me.mioclient.module.abstract_.AbstractModule_12;
import me.mioclient.module.abstract_.AbstractModule_14;
import me.mioclient.module.abstract_.AbstractModule_15;
import me.mioclient.module.abstract_.AbstractModule_18;
import me.mioclient.module.abstract_.AbstractModule_20;
import me.mioclient.module.abstract_.AbstractModule_23;
import me.mioclient.module.abstract_.AbstractModule_24;
import me.mioclient.module.abstract_.AbstractModule_25;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.abstract_.AbstractModule_27;
import me.mioclient.module.abstract_.AbstractModule_29;
import me.mioclient.module.abstract_.AbstractModule_3;
import me.mioclient.module.abstract_.AbstractModule_33;
import me.mioclient.module.abstract_.AbstractModule_34;
import me.mioclient.module.abstract_.AbstractModule_35;
import me.mioclient.module.abstract_.AbstractModule_4;
import me.mioclient.module.abstract_.AbstractModule_40;
import me.mioclient.module.abstract_.AbstractModule_5;
import me.mioclient.module.abstract_.AbstractModule_7;

public final class Class_1192 implements MioAPI, Class_1146 {
   public Class_1192() {
      super();
      field_4220.method_14(this);
      Hub.field_2599.method_29(new AbstractModule_7());
      Hub.field_2599.method_29(new AbstractModule_23());
      Hub.field_2599.method_29(new AbstractModule_12());
      Hub.field_2599.method_29(new AbstractModule_5());
      Hub.field_2599.method_29(new AbstractModule_35());
      Hub.field_2599.method_29(new AbstractModule_40());
      Hub.field_2599.method_29(new AbstractModule_18());
      Hub.field_2599.method_29(new AbstractModule_25());
      Hub.field_2599.method_29(new AbstractModule_34());
      Hub.field_2599.method_29(new AbstractModule_11());
      Hub.field_2599.method_29(new AbstractModule_15());
      Hub.field_2599.method_29(new AbstractModule_29());
      Hub.field_2599.method_29(new AbstractModule_33());
      Hub.field_2599.method_29(new AbstractModule_27());
      Hub.field_2599.method_29(new AbstractModule_24());
      Hub.field_2599.method_29(new AbstractModule_20());
      Hub.field_2599.method_29(new AbstractModule_14());
      Hub.field_2599.method_29(new AbstractModule_4());
      Hub.field_2599.method_29(new AbstractModule_3());
   }

   @Subscribe
   public void method_2(Event_63 var1) {
      for (Module var3 : (Iterable<Module>)(Iterable<?>)((List<me.mioclient.module.Module>)Hub.field_2599.getRegistry())) {
         if (var3 instanceof AbstractModule_26 var4) {
            var4.method_2(var4.method_3());
         }
      }
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();

      for (Module var3 : (Iterable<Module>)(Iterable<?>)((List<me.mioclient.module.Module>)Hub.field_2599.getRegistry())) {
         if (var3 instanceof AbstractModule_26) {
            try {
               var1.add(var3.getConfigName(), var3.toJson());
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }
      }

      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      var1.getAsJsonObject()
         .entrySet()
         .stream()
         .sorted(Comparator.comparing(var0 -> {
            try {
               return ((JsonElement)var0.getValue()).getAsJsonObject().getAsJsonObject("hud").get("index").getAsInt();
            } catch (Throwable var2) {
               return 0;
            }
         }))
         .map(
            var0 -> new SimpleEntry<>(
                  Hub.field_2599.method_2(var1x -> var1x.getConfigName().equalsIgnoreCase((String)var0.getKey())).orElse(null), (JsonElement)var0.getValue()
               )
         )
         .filter(var0 -> var0.getKey() instanceof AbstractModule_26)
         .forEach(var0 -> {
            try {
               var0.getKey().fromJson((JsonElement)var0.getValue());
            } catch (Exception var2) {
               var2.printStackTrace();
            }
         });
   }

   @Override
   public String getConfigName() {
      return "hud.json";
   }
}
