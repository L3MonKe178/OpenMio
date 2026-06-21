package me.mioclient.clickgui.component;

import java.util.Locale;
import me.mioclient.clickgui.ModuleButton;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.Module;
import me.mioclient.module.client.UIModule;
import me.mioclient.record.Class_0702;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;

/** Class_0381 — keybind row. Click → wait for next key; ESC → unbind. */
public class KeybindComponent extends SettingComponent {
   private final Module module;
   private boolean listening;

   public KeybindComponent(ModuleButton owner, Module module) {
      super(owner, null);
      this.module = module;
   }

   @Override
   public boolean isVisible() { return true; }

   @Override
   public void render(DrawContext ctx, int x, int y, int width, int mouseX, int mouseY) {
      MinecraftClient mc = MinecraftClient.getInstance();
      UIModule ui = UIModule.field_2843;
      int color = this.listening ? ColorUtil.argb(ui.field_2877.getValue()) : ColorUtil.argb(ui.field_2876.getValue());
      ctx.drawText(mc.textRenderer, "Bind", x + 4, y + 2, color, false);

      String text;
      if (this.listening) {
         text = "...";
      } else {
         int key = this.module.getKeybind().method_38();
         text = key == -1 ? "NONE" : keyName(key);
      }
      int tw = mc.textRenderer.getWidth(text);
      ctx.drawText(mc.textRenderer, text, x + width - tw - 4, y + 2, color, false);
   }

   private static String keyName(int code) {
      try { return InputUtil.fromKeyCode(code, 0).getLocalizedText().getString().toUpperCase(Locale.ROOT); }
      catch (Throwable t) { return "K" + code; }
   }

   @Override
   public boolean mouseClicked(double mx, double my, int button, int x, int y, int width) {
      if (button == 0 && within(mx, my, x, y, width, height())) {
         this.listening = true;
         return true;
      }
      return false;
   }

   @Override
   public boolean keyPressed(int key, int scan, int mods, int x, int y, int width) {
      if (!this.listening) return false;
      Class_0702 kb = this.module.getKeybind();
      this.module.setKeybind(kb.method_9(key == 256 ? -1 : key));
      this.listening = false;
      return true;
   }
}
