package me.mioclient.enum_;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.mioclient.internal.Class_1081;

public enum Class_1202 {
   RU("ru", Color.white, Color.blue, Color.red),
   field_3725("ua", Color.blue, new Color(239, 208, 12)),
   field_3726("by", Color.red, Color.red, new Color(73, 165, 86)),
   field_3727("lt", new Color(211, 171, 11), new Color(38, 117, 47), Color.red.darker()),
   field_3728("good_day", new Color(30, 30, 30), new Color(214, 102, 17), new Color(30, 30, 30), new Color(214, 102, 17), new Color(30, 30, 30)),
   DEFAULT("") {
      @Override
      public List<Color> method_492() {
         return Collections.singletonList(Class_1081.method_2(new Color(199, 146, 234), new Color(255, 49, 93), 1500.0, 0.0));
      }
   };

   public final String field_3730;
   public final List<Color> field_3731 = new ArrayList<>();

    Class_1202(String var3, Color... var4) {
      this.field_3730 = var3;
      this.field_3731.addAll(List.of(var4));
   }

   public static Class_1202 method_35(String var0) {
      for (Class_1202 var4 : values()) {
         if (var4.field_3730.equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return DEFAULT;
   }

   public List<Color> method_492() {
      return this.field_3731;
   }
}
