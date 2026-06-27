package me.mioclient.clickgui.component;

import me.mioclient.clickgui.ModuleButton;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/** Class_0414 — enum cycler: left click → next, right click → previous. */
public class EnumComponent extends SettingComponent {
   public EnumComponent(ModuleButton owner, Setting<? extends Enum<?>> setting) { super(owner, setting); }

   @SuppressWarnings({"unchecked","rawtypes"})
   private Setting<Enum> s() { return (Setting) this.setting; }

   @Override
   public void render(DrawContext ctx, int x, int y, int width, int mouseX, int mouseY) {
      MinecraftClient mc = MinecraftClient.getInstance();
      UIModule ui = UIModule.field_2843;
      if (ui == null) return;
      int label = ColorUtil.argb(ui.field_2876.getValue());
      int value = ColorUtil.argb(ui.field_2877.getValue());
      ctx.drawText(mc.textRenderer, this.setting.getName(), x + 4, y + 2, label, false);

      String text = pretty(((Enum<?>) s().getValue()).name());
      int tw = mc.textRenderer.getWidth(text);
      ctx.drawText(mc.textRenderer, text, x + width - tw - 4, y + 2, value, false);
   }

   private static String pretty(String name) {
      if (name == null || name.isEmpty()) return "";
      return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase().replace('_', ' ');
   }

   @Override
   @SuppressWarnings({"unchecked","rawtypes"})
   public boolean mouseClicked(double mx, double my, int button, int x, int y, int width) {
      if (!within(mx, my, x, y, width, height())) return false;
      if (button != 0 && button != 1) return false;
      Enum<?> cur = (Enum<?>) s().getValue();
      Object[] all = cur.getDeclaringClass().getEnumConstants();
      int step = button == 0 ? 1 : -1;
      Enum<?> next = (Enum<?>) all[((cur.ordinal() + step) % all.length + all.length) % all.length];
      ((Setting) this.setting).method_78(next);
      return true;
   }
}
