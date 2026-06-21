package me.mioclient.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0044;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.abstract_.AbstractModule_21;

public final class ColorSetting extends Setting<Color> {
   public static AbstractModule_21 field_521 = Hub.field_2595.method_78(AbstractModule_21.class);
   public final Class_0044 field_1466 = new Class_0044();
   public boolean field_1467;
   public boolean field_1468;

   public ColorSetting(String var1, Color var2, Predicate<Color> var3) {
      super(var1, var2, var3);
   }

   public ColorSetting(String var1, Color var2) {
      super(var1, var2);
   }

   public Color method_465() {
      Color var1 = (Color)super.getValue();
      if (this.field_1468) {
         return Class_1081.method_9(field_521.field_2132.getValue(), var1.getAlpha());
      } else {
         return this.field_1467 ? this.method_2(var1, 0) : var1;
      }
   }

   public Color method_2(Color var1, int var2) {
      float[] var3 = Color.RGBtoHSB(var1.getRed(), var1.getGreen(), var1.getBlue(), null);
      return Class_1081.method_2(Constants.field_686, var2, var3[1], var3[2], var1.getAlpha());
   }

   @Override
   public void method_78(String var1) {
      try {
         this.method_7(Class_1081.method_209(var1));
      } catch (Throwable var3) {
      }
   }

   public void method_7(Color var1) {
      if (this.field_3119) {
         super.method_78(new Color(var1.hashCode(), false));
      } else {
         super.method_78(var1);
      }
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.add("rgba", this.field_1466.method_5(this.method_465()));
      var1.addProperty("rainbow", this.method_132());
      var1.addProperty("sync", this.method_493());
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.isJsonArray()) {
         this.method_7(this.field_1466.method_2(var1));
      } else {
         JsonObject var2 = var1.getAsJsonObject();
         this.method_7(this.field_1466.method_2(var2.get("rgba")));
         this.method_4(var2.get("rainbow").getAsBoolean());
         this.method_29(var2.get("sync").getAsBoolean());
      }
   }

   public boolean method_493() {
      return field_521 != null && this == field_521.field_2132 ? false : this.field_1468;
   }

   public void method_29(boolean var1) {
      if (field_521 == null || this != field_521.field_2132) {
         this.field_1468 = var1;
      }
   }

   public boolean method_494() {
      return field_521 != null && this == field_521.field_2132;
   }

   public boolean method_132() {
      return this.field_1467;
   }

   public void method_4(boolean var1) {
      this.field_1467 = var1;
   }
}
