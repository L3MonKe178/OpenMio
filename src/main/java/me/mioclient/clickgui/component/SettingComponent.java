package me.mioclient.clickgui.component;

import me.mioclient.clickgui.ModuleButton;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;

/**
 * Mirrors Class_1015 — one row under a ModuleButton representing a single setting.
 * Height is the per-row constant (11 in the original) unless the component opts to draw extra.
 */
public abstract class SettingComponent {
   public static final int ROW_HEIGHT = 11;

   public final ModuleButton owner;
   public final Setting<?> setting;

   protected SettingComponent(ModuleButton owner, Setting<?> setting) {
      this.owner = owner;
      this.setting = setting;
   }

   public int height() { return ROW_HEIGHT; }

   public boolean isVisible() {
      // Matches Class_1015.method_911 — hidden if predicate fails or marked hidden.
      return this.setting == null || (this.setting.method_176() && !this.setting.method_113());
   }

   public abstract void render(DrawContext ctx, int x, int y, int width, int mouseX, int mouseY);

   public boolean mouseClicked(double mx, double my, int button, int x, int y, int width)  { return false; }
   public void    mouseReleased(double mx, double my, int button, int x, int y, int width) {}
   public boolean mouseScrolled(double mx, double my, double dy, int x, int y, int width)  { return false; }
   public boolean keyPressed(int key, int scan, int mods, int x, int y, int width)         { return false; }
   public boolean charTyped(char ch, int mods)                                              { return false; }

   protected static boolean within(double mx, double my, int x, int y, int w, int h) {
      return mx >= x && mx < x + w && my >= y && my < y + h;
   }
}
