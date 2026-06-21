package me.mioclient.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_0415;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0304;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.record.Class_0210;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;

public final class Class_1280 extends Registry<Class_0210> implements MioAPI, Class_1146 {
   public static final AbstractModule_21 field_4150 = Hub.field_2595.method_78(AbstractModule_21.class);
   public static final Class_0841 field_4151 = new Class_0841(Class_0304.class);

   public Class_1280() {
      super();
   }

   public boolean method_1009(String var1) {
      return this.method_253(var1) == Class_0304.FRIEND;
   }

   public boolean method_30(PlayerEntity var1) {
      return ((Class_0415)var1).mio$getRole() == Class_0304.FRIEND;
   }

   public boolean method_289(String var1) {
      return this.method_253(var1) == Class_0304.ENEMY;
   }

   public boolean method_16(PlayerEntity var1) {
      return ((Class_0415)var1).mio$getRole() == Class_0304.ENEMY;
   }

   public void method_632(String var1) {
      this.method_869(var1);
      this.method_7(new Class_0210(var1, Class_0304.FRIEND));
   }

   public void method_175(String var1) {
      this.method_869(var1);
      this.method_7(new Class_0210(var1, Class_0304.ENEMY));
   }

   public Class_0304 method_253(String var1) {
      return this.method_2(var1x -> var1x.getName().equalsIgnoreCase(var1)).map(Class_0210::method_242).orElse(null);
   }

   public List<String> method_2(Class_0304 var1) {
      ArrayList var2 = new ArrayList();
      synchronized ((List)this.field_3243) {
         this.field_3243.forEach(var2x -> {
            if (var2x.method_242() == var1) {
               var2.add(var2x.getName());
            }
         });
         return var2;
      }
   }

   public boolean method_869(String var1) {
      this.method_2(var1, null);
      return this.field_3243.removeIf(var1x -> var1x.getName().equalsIgnoreCase(var1));
   }

   public Color method_1145() {
      return field_4150.field_2130.getValue();
   }

   public Color method_1146() {
      return field_4150.field_2131.getValue();
   }

   public Color method_9(String var1, Color var2) {
      if (this.method_1009(var1)) {
         return this.method_1145();
      } else {
         return this.method_289(var1) ? this.method_1146() : var2;
      }
   }

   public void method_2(String var1, Class_0304 var2) {
      if (!this.method_535()) {
         for (AbstractClientPlayerEntity var4 : field_4219.world.getPlayers()) {
            if (var4.getGameProfile().getName().equalsIgnoreCase(var1) && var4 instanceof Class_0415 var5) {
               var5.mio$setRole(var2);
               return;
            }
         }
      }
   }

   public boolean method_7(Class_0210 var1) {
      this.method_2(var1.method_228(), var1.method_242());
      return super.method_2(var1);
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      JsonArray var2 = new JsonArray();

      for (Class_0210 var4 : this.field_3243) {
         JsonObject var5 = new JsonObject();
         var5.addProperty("name", var4.method_228());
         var5.add("role", field_4151.method_7(var4.method_242()));
         var2.add(var5);
      }

      var1.add("socials", var2);
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.getAsJsonObject().has("socials")) {
         for (JsonElement var3 : var1.getAsJsonObject().getAsJsonArray("socials")) {
            JsonObject var4 = var3.getAsJsonObject();
            if (var4.has("name") && var4.has("role")) {
               String var5 = var4.get("name").getAsString();
               Class_0304 var6 = (Class_0304)field_4151.method_9(var4.get("role"));
               this.method_7(new Class_0210(var5, var6));
            }
         }
      }
   }

   @Override
   public String getConfigName() {
      return "socials.json";
   }
}
