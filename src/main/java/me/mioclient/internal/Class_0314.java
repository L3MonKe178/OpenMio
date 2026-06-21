package me.mioclient.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;

public final class Class_0314 extends Class_1049<Class_1368, List<Class_1368>> implements MioAPI, Class_1146 {
   public Class_0314() {
      super(new ArrayList<>());
      field_4220.method_14(this);
   }

   public List<Class_1368> getVisible() {
      return this.method_535()
         ? Collections.emptyList()
         : this.field_3243
            .stream()
            .filter(
               var0 -> var0.method_106()
                     .equalsIgnoreCase(
                        field_4219.player.networkHandler.getServerInfo() == null ? "singleplayer" : field_4219.player.networkHandler.getServerInfo().address
                     )
            )
            .sorted(Comparator.comparing(var0 -> field_4219.player.squaredDistanceTo(var0.method_380(), var0.method_395(), var0.method_396())))
            .toList();
   }

   @Override
   public JsonElement toJson() {
      JsonArray var1 = new JsonArray();
      JsonObject var2 = new JsonObject();

      for (Class_1368 var4 : this.field_3243) {
         var1.add(field_4218.toJsonTree(var4));
      }

      var2.add("waypoints", var1);
      return var2;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonObject() && var1.getAsJsonObject().has("waypoints")) {
         for (JsonElement var4 : var1.getAsJsonObject().getAsJsonArray("waypoints")) {
            try {
               this.field_3243.add((Class_1368)field_4218.fromJson(var4, Class_1368.class));
            } catch (Exception var6) {
               var6.printStackTrace();
            }
         }
      }
   }

   @Override
   public String getConfigName() {
      return "waypoints.json";
   }

   @Override public boolean register(Class_1368 var1)   { return method_9(var1); }
   @Override public boolean unregister(Class_1368 var1) { return method_5(var1); }

   public boolean method_9(Class_1368 var1) {
      for (Class_1368 var3 : (Iterable<Class_1368>)(Iterable<?>)((List)this.getRegistry())) {
         if (var3.getName().equalsIgnoreCase(var1.getName()) && var3.method_106().equalsIgnoreCase(var1.method_106())) {
            return false;
         }
      }

      return ((List)this.getRegistry()).add(var1);
   }

   public boolean method_5(Class_1368 var1) {
      return ((List<Class_1368>)this.getRegistry())
         .removeIf(var1x -> var1x.getName().equalsIgnoreCase(var1.getName()) && var1x.method_106().equalsIgnoreCase(var1.getName()));
   }
}
