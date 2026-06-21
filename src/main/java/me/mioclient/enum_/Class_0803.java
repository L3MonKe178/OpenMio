package me.mioclient.enum_;

import java.awt.Color;
import me.mioclient.module.render.PhaseESPModule;

public enum Class_0803 {
   SAFE {
      @Override
      public Color method_2(PhaseESPModule var1) {
         return var1.field_3496.getValue();
      }
   },
   SEMI {
      @Override
      public Color method_2(PhaseESPModule var1) {
         return var1.field_3497.getValue();
      }
   },
   UNSAFE {
      @Override
      public Color method_2(PhaseESPModule var1) {
         return var1.field_3498.getValue();
      }
   };

   public Color method_2(PhaseESPModule var1) {
      return null;
   }
}
