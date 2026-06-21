package me.mioclient.module.abstract_;

import java.nio.file.Path;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_0889;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1328;
import net.minecraft.client.gui.DrawContext;
import org.apache.commons.lang3.SystemUtils;

public class AbstractModule_27 extends AbstractModule_26 {
   public static Path field_4222;

   public AbstractModule_27() {
      super("Music");
      this.method_2(new Class_0149(this));
   }

   @Override
   public void onEnable() {
      if (!SystemUtils.IS_OS_WINDOWS_10 && !SystemUtils.OS_NAME.startsWith("Windows 11")) {
         this.method_38(false);
      }
   }

   @Override
   public void method_2(DrawContext var1) {
      if (!this.method_1170()) {
         FontRenderer.field_3143.method_9(var1, this.method_4(), 0.0F, 0.0F, this.method_9(this.field_1844.method_60()));
      }
   }

   @Override
   public float[] method_31() {
      return this.method_1170()
         ? new float[]{0.0F, 0.0F}
         : new float[]{FontRenderer.field_3143.method_221(this.method_4()), (float)FontRenderer.field_3143.method_66()};
   }

   public static Path method_1169() {
      if (field_4222 == null) {
         field_4222 = Class_1328.field_4281.resolve("music");
      }

      return field_4222;
   }

   public String method_4() {
      String var1 = Class_0889.method_811();
      return this.method_22() && Class_0889.field_2811 != 4 ? "HydrachFM - f3dot" : var1;
   }

   public boolean method_1170() {
      return !this.method_22() && Class_0889.field_2811 != 4;
   }
}
