package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Constants;
import me.mioclient.module.misc.AntiAimModule;
import net.minecraft.util.math.MathHelper;

public enum Class_0616 implements Nameable {
   JITTER("Jitter") {
      @Override
      public float method_9(AntiAimModule var1, float var2) {
         double var3 = Math.random() * (double)var1.field_2575.getValue().floatValue() * 2.0
            - (double)var1.field_2575.getValue().floatValue()
            + (double)var1.field_2576.getValue().floatValue();
         return (float)MathHelper.wrapDegrees(var3);
      }
   },
   SPIN("Spin") {
      @Override
      public float method_9(AntiAimModule var1, float var2) {
         var1.field_2582 = var1.field_2582 + var1.field_2572.getValue();
         return MathHelper.wrapDegrees(var1.field_2582);
      }
   },
   FLIP("Flip") {
      @Override
      public float method_9(AntiAimModule var1, float var2) {
         float var3 = var1.field_2576.getValue() + (MioAPI.field_4219.player.age % 2 == 0 ? -var1.field_2574.getValue() : var1.field_2574.getValue());
         return MathHelper.wrapDegrees(var3);
      }
   },
   STATIC("Static") {
      @Override
      public float method_9(AntiAimModule var1, float var2) {
         return var1.field_2573.getValue();
      }
   },
   RANDOM("Random") {
      @Override
      public float method_9(AntiAimModule var1, float var2) {
         return (float)(Math.random() * (double)Constants.field_686);
      }
   };

   public final String field_1948;

    Class_0616(String var3) {
      this.field_1948 = var3;
   }

   @Override
   public String getName() {
      return this.field_1948;
   }

   public abstract float method_9(AntiAimModule var1, float var2);
}
