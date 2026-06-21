package me.mioclient.module.abstract_;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0274;
import me.mioclient.internal.Class_0158;
import me.mioclient.internal.Class_0617;
import me.mioclient.internal.Class_0841;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.client.HUDModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;

public abstract class AbstractModule_26 extends Module {
   public static final Class_0841 field_1843 = new Class_0841(Class_0274.class);
   public static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   public Class_0158 field_1844;

   public AbstractModule_26(String var1, String... var2) {
      super(var1, Category.HUD, var2);
   }

   public void method_2(DrawContext var1) {
   }

   public float[] method_31() {
      throw new RuntimeException();
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("enabled", this.isToggled());
      JsonObject var2 = new JsonObject();

      for (Setting var4 : this.getRegistry()) {
         if (!var4.method_158()) {
            var2.add(var4.getConfigName(), var4.toJson());
         }
      }

      var1.add("settings", var2);
      JsonObject var5 = var1.getAsJsonObject();
      JsonObject var6 = new JsonObject();
      var6.add("anchor", field_1843.method_7(this.field_1844.method_3()));
      var6.addProperty("index", this.method_36());
      if (this.field_1844.method_3() == Class_0274.NONE) {
         var6.addProperty("x", this.field_1844.method_190());
         var6.addProperty("y", this.field_1844.method_191());
      }

      var5.add("hud", var6);
      return var5;
   }

   @Override
   public void fromJson(JsonElement var1) {
      super.fromJson(var1);
      JsonObject var2 = var1.getAsJsonObject();
      if (var2.has("hud")) {
         var2 = var2.getAsJsonObject("hud");
         this.method_2((Class_0274)field_1843.method_9(var2.get("anchor")));
         if (this.field_1844.method_3() == Class_0274.NONE) {
            this.field_1844.method_2(var2.get("x").getAsFloat(), false);
            this.field_1844.method_9(var2.get("y").getAsFloat(), false);
         }
      }
   }

   @Override
   public boolean isDrawn() {
      return false;
   }

   public boolean method_22() {
      return field_4219.currentScreen instanceof Class_0617;
   }

   public Class_0274 method_3() {
      return this.field_1844.method_3();
   }

   public Color method_9(float var1) {
      return hud.field_961.getValue().method_2(hud, var1);
   }

   public void method_2(Class_0274 var1) {
      this.field_1844.method_2(var1);
      Hub.method_756().method_5(this.field_1844);
      Hub.method_756().method_2(var1, this.field_1844);
   }

   public void method_2(Class_0158 var1) {
      this.field_1844 = var1;
   }

   public int method_36() {
      int var1 = 0;

      for (Class_0158 var3 : Hub.method_756().method_5(this.field_1844.method_3()).method_566()) {
         if (var3.method_199().equals(this)) {
            return var1;
         }

         var1++;
      }

      return -1;
   }

   public Class_0158 method_14() {
      return this.field_1844;
   }
}
