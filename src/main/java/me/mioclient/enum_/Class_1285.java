package me.mioclient.enum_;

import java.awt.Color;
import me.mioclient.api.Nameable;
import me.mioclient.api.Class_0964;
import me.mioclient.module.render.ChamsModule;

public enum Class_1285 implements Nameable, Class_0964 {
   BOTH("Both") {
      @Override
      public Color[] method_2(ChamsModule var1) {
         return new Color[]{var1.field_252.getValue(), var1.field_251.getValue()};
      }
   },
   FILL("Fill") {
      @Override
      public Color[] method_2(ChamsModule var1) {
         return new Color[]{field_2973, var1.field_251.getValue()};
      }
   },
   LINE("Line") {
      @Override
      public Color[] method_2(ChamsModule var1) {
         return new Color[]{var1.field_252.getValue(), field_2973};
      }
   };

   public final String field_4163;

    Class_1285(String var3) {
      this.field_4163 = var3;
   }

   @Override
   public String getName() {
      return this.field_4163;
   }
}
