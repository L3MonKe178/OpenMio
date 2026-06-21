package me.mioclient.enum_;

import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_1016;

public enum Side2 {
   CENTER {
      @Override
      public float method_270(float var1) {
         return -var1 * 0.5F;
      }
   },
   LEFT {
      @Override
      public float method_270(float var1) {
         return 0.0F;
      }
   },
   RIGHT {
      @Override
      public float method_270(float var1) {
         return -var1;
      }
   };

   public abstract float method_270(float var1);

   public float method_9(String var1, boolean var2) {
      return this.method_270(var2 ? (float)Class_1309.field_4219.textRenderer.getWidth(var1) : Class_1016.field_3143.method_221(var1));
   }
}
