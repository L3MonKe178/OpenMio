package me.mioclient.enum_;

import java.awt.Color;
import me.mioclient.api.Nameable;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.client.HUDModule;
import me.mioclient.setting.ColorSetting;

public enum Class_0556 implements Nameable {
   NONE("None") {
      @Override
      public Color method_2(HUDModule var1, float var2) {
         return ((ColorSetting)var1.field_960).method_132()
            ? ((ColorSetting)var1.field_960)
               .method_2(var1.field_960.getValue(), (int)(var2 * (float)var1.field_962.getValue().intValue() * Class_0556.method_584()))
            : var1.field_960.getValue();
      }
   },
   PULSE("Pulse") {
      @Override
      public Color method_2(HUDModule var1, float var2) {
         return Class_1081.method_2(
            var1.field_960.getValue(),
            var1.field_963.getValue(),
            2000.0,
            (double)(var2 * (float)var1.field_962.getValue().intValue() * Class_0556.method_584())
         );
      }
   },
   RAW("Raw") {
      @Override
      public Color method_2(HUDModule var1, float var2) {
         int var3 = var1.field_964.getValue();
         int var4 = var1.field_962.getValue();
         float var5 = 1.0F / (float)var4;
         int var6 = (int)(
            (float)(System.currentTimeMillis() / (long)var1.field_965.getValue().intValue() % (long)(var3 * var4)) + var2 / Class_0556.method_584()
         );
         int var7 = (int)Math.floor((double)((float)var6 * var5));
         if (var7 < 0) {
            var7 = 0;
         }

         return var1.field_966.getValue()
            ? Class_1081.method_2(
               var1.field_967.get(var7 % var3).getValue(), var1.field_967.get((var7 + 1) % var3).getValue(), 1.0F - ((float)var6 * var5 - (float)var7)
            )
            : var1.field_967.get(var7 % var3).getValue();
      }
   };

   public final String field_1770;

    Class_0556(String var3) {
      this.field_1770 = var3;
   }

   @Override
   public String getName() {
      return this.field_1770;
   }

   public abstract Color method_2(HUDModule var1, float var2);

   public static float method_584() {
      return 10.0F / (float)(FontRenderer.field_3143.method_66() + 2);
   }
}
