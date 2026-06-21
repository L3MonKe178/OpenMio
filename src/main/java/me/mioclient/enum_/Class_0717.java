package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.internal.Class_0245;
import me.mioclient.module.misc.AntiAimModule;

public enum Class_0717 implements Class_0013 {
   STATIC("Static") {
      @Override
      public float method_2(AntiAimModule var1, float var2) {
         return var1.field_2579.getValue();
      }
   },
   RANDOM("Random") {
      @Override
      public float method_2(AntiAimModule var1, float var2) {
         return (float)(Math.random() * 180.0 - (double)Class_0245.field_685);
      }
   };

   public final String field_2278;

    Class_0717(String var3) {
      this.field_2278 = var3;
   }

   @Override
   public String getName() {
      return this.field_2278;
   }

   public abstract float method_2(AntiAimModule var1, float var2);
}
