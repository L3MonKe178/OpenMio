package me.mioclient.module;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import me.mioclient.Hub;
import me.mioclient.api.Class_0945;
import me.mioclient.api.Class_1146;
import me.mioclient.api.Class_1240;
import me.mioclient.api.MioAPI;
import me.mioclient.deobf.Named;
import me.mioclient.enum_.Class_0046;
import me.mioclient.event.Event_45;
import me.mioclient.internal.TextBuilder;
import me.mioclient.record.Class_0702;
import me.mioclient.setting.Setting;
import net.minecraft.util.Formatting;

public class Module extends Named implements MioAPI, Class_1240, Class_0945<Setting<?>, List<Setting<?>>>, Class_1146 {
   public final Category field_37;
   public List<Setting<?>> field_38 = new ArrayList<>();
   public String[] field_39;
   public Class_0702 field_40 = Class_0702.field_2229;
   public boolean field_41;
   public boolean field_42 = true;
   public boolean field_43;

   public Module(String var1, String var2, Category var3, String... var4) {
      super(var1);
      this.field_37 = var3;
      this.setDescription(var2);
      String[] var5 = Objects.requireNonNullElseGet(var4, () -> new String[0]);
      this.field_39 = Arrays.copyOf(var5, var5.length + 1);
      this.field_39[var5.length] = var1;
   }

   public Module(String var1, Category var2, String... var3) {
      this(var1, "", var2, var3);
   }

   public Category getCategory() {
      return this.field_37;
   }

   public Class_0702 getKeybind() {
      if (this.field_40 == null) this.field_40 = Class_0702.field_2229;
      return this.field_40;
   }

   public String getInfoString() {
      return new TextBuilder()
         .method_2(
            this.getInfo() != null
               ? new TextBuilder()
                  .method_2(String.valueOf(Formatting.GRAY))
                  .method_2(this.getInfo())
                  .method_2(String.valueOf(Formatting.WHITE))
                  .method_2(String.valueOf(Formatting.GRAY))
                  .method_9("\u0001 [\u0001\u0001\u0001]")
               : ""
         )
         .method_2(Hub.field_2626.method_7(this))
         .method_9("\u0001\u0001");
   }

   public void setKeybind(Class_0702 var1) {
      this.field_40 = var1;
   }

   public void modifyKeybind(Function<Class_0702, Class_0702> var1) {
      this.field_40 = (Class_0702)var1.apply(this.field_40);
   }

   public List<Setting<?>> getRegistry() {
      if (this.field_38 == null) this.field_38 = new ArrayList<>();
      return this.field_38;
   }

   public boolean register(Setting<?> var1) {
      return this.field_38.add(var1);
   }

   public boolean unregister(Setting<?> var1) {
      return this.field_38.remove(var1);
   }

   public <T> Setting<T> add(Setting<T> var1) {
      this.register(var1);
      return var1;
   }

   public <T> Setting<T> add(Setting<T> var1, Setting<?> var2) {
      int var3 = this.field_38.indexOf(var2) + 1;
      this.field_38.add(var3, var1);
      return var1;
   }

   @Override
   public boolean isToggled() {
      return this.field_41;
   }

   @Override
   public void enable() {
      if (!this.isToggled()) {
         this.field_41 = true;
         field_4220.method_14(this);
         field_4220.method_36(new Event_45(this));
         this.onToggle();
         this.onEnable();
      }
   }

   @Override
   public void disable() {
      if (this.isToggled()) {
         field_4220.method_17(this);
         this.field_41 = false;
         field_4220.method_36(new Event_45(this));
         this.onToggle();
         this.onDisable();
      }
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void onToggle() {
   }

   public String getInfo() {
      return null;
   }

   public boolean isDrawn() {
      return this.field_42;
   }

   public void setDrawn(boolean var1) {
      this.field_42 = var1;
   }

   public String[] getAliases() {
      if (this.field_39 == null) this.field_39 = new String[0];
      return this.field_39;
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("enabled", this.field_41);
      var1.addProperty("key", this.field_40.method_38());
      var1.addProperty("state", this.field_40.method_78().method_30());
      var1.addProperty("mouse", this.field_40.method_39());
      var1.addProperty("drawn", this.field_42);
      JsonObject var2 = new JsonObject();

      for (Setting var4 : this.getRegistry()) {
         if (!var4.method_158() && !var4.method_113()) {
            var2.add(var4.getConfigName(), var4.toJson());
         }
      }

      var1.add("settings", var2);
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1 instanceof JsonObject var2) {
         if (var2.has("settings") && var2.get("settings") instanceof JsonObject var3) {
            for (Setting var5 : this.getRegistry()) {
               if (!var5.method_113() && var3.has(var5.getConfigName())) {
                  try {
                     var5.fromJson(var3.get(var5.getConfigName()));
                  } catch (Exception var7) {
                  }
               }
            }
         }

         if (var2.has("drawn")) {
            this.setDrawn(var2.get("drawn").getAsBoolean());
         }

         if (var2.has("key")) {
            this.modifyKeybind(
               var1x -> var1x.method_9(var2.get("key").getAsInt())
                     .method_2(var2.has("state") ? Class_0046.method_4(var2.get("state").getAsString()) : Class_0046.TOGGLE)
            );
         }

         if (var2.has("mouse")) {
            this.modifyKeybind(var1x -> var1x.method_9(var2.get("mouse").getAsBoolean()));
         }

         if (var2.has("enabled")) {
            this.method_38(var2.get("enabled").getAsBoolean());
         }
      }
   }

   @Override
   public String getConfigName() {
      return this.getName();
   }

   public boolean isWip() {
      return this.field_43;
   }

   public void setWip(boolean var1) {
      this.field_43 = var1;
   }
}
