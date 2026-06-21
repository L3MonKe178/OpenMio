package me.mioclient.clickgui.component;

import me.mioclient.clickgui.ModuleButton;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/** Class_1031 in the original — boolean rendered as text + colored dot. */
public class BooleanComponent extends SettingComponent {
   @SuppressWarnings("unchecked")
   private Setting<Boolean> s() { return (Setting<Boolean>) this.setting; }

   public BooleanComponent(ModuleButton owner, Setting<Boolean> setting) { super(owner, setting); }

   @Override
   public void render(DrawContext ctx, int x, int y, int width, int mouseX, int mouseY) {
      UIModule ui = UIModule.field_2843;
      MinecraftClient mc = MinecraftClient.getInstance();
      boolean v = s().getValue();
      int color = v ? ColorUtil.argb(ui.field_2877.getValue()) : ColorUtil.argb(ui.field_2876.getValue());
      ctx.drawText(mc.textRenderer, this.setting.getName(), x + 4, y + 2, color, false);
      int dot = 4;
      ctx.fill(x + width - dot - 4, y + (ROW_HEIGHT - dot) / 2, x + width - 4, y + (ROW_HEIGHT - dot) / 2 + dot, color);
   }

   @Override
   public boolean mouseClicked(double mx, double my, int button, int x, int y, int width) {
      if (button == 0 && within(mx, my, x, y, width, height())) {
         s().method_78(!s().getValue());
         return true;
      }
      return false;
   }
}
