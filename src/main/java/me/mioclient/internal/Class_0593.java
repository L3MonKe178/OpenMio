package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.mioclient.api.MioAPI;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.text.Text;

public final class Class_0593 implements MioAPI {
   public static boolean field_1856 = false;
   public static List<TooltipComponent> field_1666;

   public Class_0593() {
      super();
      throw new AssertionError();
   }

   public static List<TooltipComponent> method_2(DrawContext var0, List<Text> var1, Optional<TooltipData> var2) {
      field_1856 = true;
      var0.drawTooltip(field_4219.textRenderer, var1, var2, 0, 0);
      field_1856 = false;
      return field_1666 == null ? new ArrayList<>() : new ArrayList<>(field_1666);
   }

   public static boolean method_607() {
      return field_1856;
   }

   public static void method_29(List<TooltipComponent> var0) {
      field_1666 = var0;
   }
}
