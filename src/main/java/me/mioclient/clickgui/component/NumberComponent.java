package me.mioclient.clickgui.component;

import me.mioclient.clickgui.ModuleButton;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/**
 * Class_1170 in the original — number setting rendered as label + value + filled bar underneath.
 * Original adds 3 extra px under the row for the slider; matched here.
 */
public class NumberComponent extends SettingComponent {
   private static final int BAR_HEIGHT = 2;
   private boolean dragging;

   public NumberComponent(ModuleButton owner, Setting<? extends Number> setting) { super(owner, setting); }

   @SuppressWarnings({"unchecked","rawtypes"})
   private Setting<Number> s() { return (Setting) this.setting; }

   @Override
   public int height() { return ROW_HEIGHT + 3; }

   @Override
   public void render(DrawContext ctx, int x, int y, int width, int mouseX, int mouseY) {
      MinecraftClient mc = MinecraftClient.getInstance();
      UIModule ui = UIModule.field_2843;
      if (ui == null) return;

      double min = ((Number) this.setting.method_100()).doubleValue();
      double max = ((Number) this.setting.method_101()).doubleValue();
      double range = Math.max(1e-9, max - min);

      if (this.dragging) {
         double clamped = Math.max(min, Math.min(max, min + (mouseX - (x + 4)) / (double) Math.max(1, width - 8) * range));
         write(clamped);
      }
      double v = s().getValue().doubleValue();
      int color = ColorUtil.argb(ui.field_2876.getValue());
      ctx.drawText(mc.textRenderer, this.setting.getName(), x + 4, y + 2, color, false);
      String text = formatValue(v);
      int tw = mc.textRenderer.getWidth(text);
      ctx.drawText(mc.textRenderer, text, x + width - tw - 4, y + 2, color, false);

      int barL = x + 4;
      int barR = x + width - 4;
      int barY = y + height() - BAR_HEIGHT - 2;
      ctx.fill(barL, barY, barR, barY + BAR_HEIGHT, ColorUtil.argb(ui.field_2881.getValue()));
      int filled = (int) ((v - min) / range * (barR - barL));
      ctx.fill(barL, barY, barL + filled, barY + BAR_HEIGHT, ColorUtil.argb(ui.field_2882.getValue()));
   }

   private String formatValue(double v) {
      Number cur = s().getValue();
      if (cur instanceof Integer || cur instanceof Long || cur instanceof Short) return Long.toString(Math.round(v));
      return String.format("%.2f", v);
   }

   @SuppressWarnings({"unchecked","rawtypes"})
   private void write(double v) {
      Number cur = s().getValue();
      Object next;
      if      (cur instanceof Integer) next = (int) Math.round(v);
      else if (cur instanceof Long)    next = Math.round(v);
      else if (cur instanceof Short)   next = (short) Math.round(v);
      else if (cur instanceof Float)   next = (float) v;
      else                              next = v;
      ((Setting) this.setting).method_78(next);
   }

   @Override
   public boolean mouseClicked(double mx, double my, int button, int x, int y, int width) {
      if (button == 0 && within(mx, my, x, y, width, height())) {
         this.dragging = true;
         return true;
      }
      return false;
   }

   @Override
   public void mouseReleased(double mx, double my, int button, int x, int y, int width) {
      this.dragging = false;
   }
}
