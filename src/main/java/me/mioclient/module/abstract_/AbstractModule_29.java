package me.mioclient.module.abstract_;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_0585;
import me.mioclient.internal.Class_1016;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;

public class AbstractModule_29 extends AbstractModule_26 {
   public final Setting<Color> field_2893 = this.add(new ColorSetting("Color", Color.gray));
   public final Class_0585 field_2894 = new Class_0585(Float.intBitsToFloat(1065353216));

   public AbstractModule_29() {
      super("Lag'O'Meter", "lag", "lagometer");
      this.method_2(new Class_0149(this));
   }

   @Override
   public void method_2(DrawContext var1) {
      String var2 = this.method_4();
      this.field_2894.method_3(var2 != null);
      if (var2 != null) {
         float var3 = this.field_2894.method_45();
         Class_1016.field_3143
            .method_9(var1, var2, 0.0F, (Float.intBitsToFloat(1065353216) - var3) * (float)(-Class_1016.field_3143.method_66()), this.field_2893.getValue());
      }
   }

   @Override
   public float[] method_31() {
      String var1 = this.method_4();
      return var1 == null ? new float[]{0.0F, 0.0F} : new float[]{Class_1016.field_3143.method_221(var1), (float)(Class_1016.field_3143.method_66() + 1)};
   }

   public String method_4() {
      long var1 = Math.max(System.currentTimeMillis() - Hub.field_2602.method_987(), 0L);
      return (var1 <= 1000L || field_4219.isInSingleplayer()) && !this.method_22()
         ? null
         : "The server is not responding for %.1fs"
            .formatted((float)(System.currentTimeMillis() - Hub.field_2602.method_987()) / Float.intBitsToFloat(1148846080));
   }
}
