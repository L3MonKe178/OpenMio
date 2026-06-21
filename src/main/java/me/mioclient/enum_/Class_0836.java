package me.mioclient.enum_;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1146;
import me.mioclient.internal.Class_0033;
import me.mioclient.internal.Class_1152;
import me.mioclient.internal.Class_1328;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.client.FontsModule;
import me.mioclient.module.client.HUDModule;
import me.mioclient.module.client.NotificationsModule;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.Setting;

public enum Class_0836 implements Class_0013, Class_1146 {
   MODULES("Modules") {
      @Override
      public JsonElement toJson() {
         JsonObject var1 = new JsonObject();
         this.method_7(var1);

         for (Module var3 : (List<Module>)Hub.field_2599.getRegistry()) {
            if (!(var3 instanceof AbstractModule_26) && !(var3 instanceof FontsModule)) {
               JsonObject var4 = new JsonObject();
               var4.addProperty("enabled", var3.isToggled());
               JsonObject var5 = new JsonObject();

               for (Setting var7 : var3.getRegistry()) {
                  if (!(var7 instanceof ColorSetting) && !var7.method_158() && !var7.method_113()) {
                     var5.add(var7.getConfigName(), var7.toJson());
                  }
               }

               if (!Class_0033.method_2(var5)) {
                  var4.add("settings", var5);
               }

               var1.add(var3.getConfigName(), var4);
            }
         }

         return var1;
      }

      @Override
      public void fromJson(JsonElement var1) {
         Class_1152.field_3545.method_9(var1.getAsJsonObject());
         super.fromJson(var1);
      }
   },
   BINDS("Binds") {
      @Override
      public JsonElement toJson() {
         JsonObject var1 = new JsonObject();
         this.method_7(var1);

         for (Module var3 : (List<Module>)Hub.field_2599.getRegistry()) {
            JsonObject var4 = new JsonObject();
            var4.addProperty("key", var3.getKeybind().method_38());
            var4.addProperty("state", var3.getKeybind().method_78().name().toLowerCase());
            var4.addProperty("mouse", var3.getKeybind().method_39());
            var1.add(var3.getConfigName(), var4);
         }

         return var1;
      }

      @Override
      public void fromJson(JsonElement var1) {
         Class_1152.field_3545.method_5(var1.getAsJsonObject());
         super.fromJson(var1);
      }
   },
   COLORS("Colors") {
      @Override
      public JsonElement toJson() {
         JsonObject var1 = new JsonObject();
         this.method_7(var1);

         for (Module var3 : (List<Module>)Hub.field_2599.getRegistry()) {
            JsonObject var4 = new JsonObject();
            JsonObject var5 = new JsonObject();

            for (Setting var7 : var3.getRegistry()) {
               if (var7 instanceof ColorSetting && !var7.method_113()) {
                  var5.add(var7.getConfigName(), var7.toJson());
               }
            }

            var4.add("settings", var5);
            if (!Class_0033.method_2(var4)) {
               var1.add(var3.getConfigName(), var4);
            }
         }

         return var1;
      }

      @Override
      public void fromJson(JsonElement var1) {
         Class_1152.field_3545.method_2(var1.getAsJsonObject(), var0 -> false);
         super.fromJson(var1);
      }
   },
   VISUALS("Visuals") {
      public static final Set<Class<? extends Module>> field_3409 = new HashSet<>(
         List.of(UIModule.class, FontsModule.class, HUDModule.class, AbstractModule_21.class, NotificationsModule.class)
      );

      @Override
      public JsonElement toJson() {
         JsonObject var1 = new JsonObject();
         this.method_7(var1);

         for (Module var3 : (List<Module>)Hub.field_2599.getRegistry()) {
            JsonObject var4 = new JsonObject();
            boolean var5 = this.method_39(var3);
            if (var5) {
               var4.addProperty("enabled", var3.isToggled());
            }

            JsonObject var6 = new JsonObject();

            for (Setting var8 : var3.getRegistry()) {
               if (!var8.method_113() && (var8 instanceof ColorSetting || var5)) {
                  var6.add(var8.getConfigName(), var8.toJson());
               }
            }

            var4.add("settings", var6);
            if (!Class_0033.method_2(var4)) {
               var1.add(var3.getConfigName(), var4);
            }
         }

         return var1;
      }

      @Override
      public void fromJson(JsonElement var1) {
         Class_1152.field_3545.method_2(var1.getAsJsonObject(), this::method_39);
         super.fromJson(var1);
      }

      public boolean method_39(Module var1) {
         if (var1 instanceof AbstractModule_26) {
            return true;
         } else {
            return field_3409.contains(var1.getClass()) ? true : var1.getCategory() == Category.RENDER || var1.getCategory() == Category.HUD;
         }
      }
   },
   MACRO("Macro") {
      @Override
      public JsonElement toJson() {
         return Hub.field_2607.toJson();
      }

      @Override
      public void fromJson(JsonElement var1) {
         synchronized ((List)Hub.field_2607.getRegistry()) {
            ((List)Hub.field_2607.getRegistry()).clear();
            Hub.field_2607.fromJson(var1);
         }
      }
   },
   ALL("All") {
      @Override
      public JsonElement toJson() {
         return Hub.field_2599.toJson().getAsJsonObject();
      }
   };

   public final String field_2669;
   public final Path field_2670;

    Class_0836(String var3) {
      this.field_2669 = var3;
      this.field_2670 = Class_1328.field_4282.resolve(var3.toLowerCase());
   }

   public Path method_774() {
      return this.field_2670;
   }

   public static Class_0836 method_113(String var0) {
      for (Class_0836 var4 : values()) {
         if (var4.getName().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public String getName() {
      return this.field_2669;
   }

   @Override
   public void fromJson(JsonElement var1) {
      Hub.field_2599.fromJson(var1);
   }

   public void method_7(JsonObject var1) {
      var1.addProperty("client", "mio-fabric");
      var1.addProperty("category", this.getName().toLowerCase(Locale.ROOT));
   }
}
