package me.mioclient.internal;

import com.google.common.base.Converter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;

public class Class_0044 extends Converter<Color, JsonElement> {
   public Class_0044() {
      super();
   }

   @NotNull
   public JsonElement method_5(Color var1) {
      JsonArray var2 = new JsonArray();
      var2.add(var1.getRed());
      var2.add(var1.getGreen());
      var2.add(var1.getBlue());
      var2.add(var1.getAlpha());
      return var2;
   }

   @NotNull
   public Color method_2(JsonElement var1) {
      if (var1.isJsonArray() && var1.getAsJsonArray().size() >= 4) {
         JsonArray var2 = var1.getAsJsonArray();
         return new Color(var2.get(0).getAsInt(), var2.get(1).getAsInt(), var2.get(2).getAsInt(), var2.get(3).getAsInt());
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   protected JsonElement doForward(Color var1) {
      return method_5(var1);
   }

   @Override
   protected Color doBackward(JsonElement var1) {
      return method_2(var1);
   }
}
