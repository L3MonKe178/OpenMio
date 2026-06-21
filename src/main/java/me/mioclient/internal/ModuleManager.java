package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.deobf.Named;
import me.mioclient.enum_.Class_0046;
import me.mioclient.event.Event_18;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_22;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.abstract_.AbstractModule_32;
import me.mioclient.module.abstract_.AbstractModule_6;
import me.mioclient.record.Class_0702;

public final class ModuleManager extends Registry<Module> implements MioAPI, Class_1146 {
   public final Object2ObjectOpenHashMap<Class<? extends Module>, Module> field_2974 = new Object2ObjectOpenHashMap();

   public ModuleManager() {
      super();
      field_4220.method_14(this);
      MioModules.init(this);
      this.method_866();
      this.method_865();
      this.field_3243.sort(Comparator.comparing(m -> m.getName() == null ? "" : m.getName()));
   }

   public void method_865() {
   }

   public void method_866() {
      if (Hub.field_2630.method_264()) {
         this.method_29(new AbstractModule_22());
         this.method_29(new AbstractModule_6());
      }
   }

   @Subscribe
   public void method_2(Event_18 var1) {
      for (Module var3 : this.field_3243) {
         if (var3.getKeybind().method_39() == var1.method_615()
            && var3.getKeybind().method_38() == var1.method_614()
            && var3.getKeybind().method_78() == Class_0046.TOGGLE) {
            var3.method_68();
         }
      }
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_9(Event_3 var1) {
      for (Module var3 : this.field_3243) {
         Class_0702 var4 = var3.getKeybind();
         boolean var5 = var4.method_78() == Class_0046.HOLD;
         if (!var4.method_29() && var4.method_78() != Class_0046.TOGGLE && (field_4219.currentScreen == null || var3.isToggled() == var5)) {
            var3.method_38(var5 == Class_0018.method_9(var4));
         }
      }
   }

   public List<Module> method_2(Category var1) {
      return this.field_3243.stream().filter(var1x -> var1x.getCategory() == var1).collect(Collectors.toList());
   }

   public List<AbstractModule_32> method_867() {
      ArrayList var1 = new ArrayList();
      this.field_3243.forEach(var1x -> {
         if (var1x instanceof AbstractModule_32 var2) {
            var1.add(var2);
         }
      });
      return var1;
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("client", "mio-fabric");
      var1.addProperty("category", "all");

      for (Module var3 : (Iterable<Module>)(Iterable<?>)((List)this.getRegistry())) {
         if (!(var3 instanceof AbstractModule_26)) {
            try {
               var1.add(var3.getConfigName(), var3.toJson());
            } catch (Exception var5) {
            }
         }
      }

      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      for (Module var3 : (Iterable<Module>)(Iterable<?>)((List)this.getRegistry())) {
         if (!(var3 instanceof AbstractModule_26)) {
            try {
               var3.fromJson(var1.getAsJsonObject().get(var3.getConfigName()));
            } catch (Exception var5) {
            }
         }
      }
   }

   @Override
   public String getConfigName() {
      return "modules.json";
   }

   public boolean method_29(Module var1) {
      if (Class_0345.method_9(var1.getClass())) {
         return false;
      } else {
         Class_0345.method_9(var1);
         this.field_2974.put(var1.getClass(), var1);
         return super.method_2(var1);
      }
   }

   public boolean method_4(Module var1) {
      this.field_2974.remove(var1.getClass(), var1);
      return super.method_9(var1);
   }

   @Deprecated
   public <T extends Module> T method_78(Class<T> var1) {
      return (T)this.field_2974.get(var1);
   }
}
