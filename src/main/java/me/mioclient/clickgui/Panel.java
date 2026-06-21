package me.mioclient.clickgui;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.clickgui.component.SettingComponent;
import me.mioclient.clickgui.util.Animation;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/**
 * Replica of Class_0746 — base draggable panel with a 14px header and a body that
 * holds {@link ModuleButton}s. Right-click on the header collapses; left-click drags.
 * Width is the original 92 + the UIModule "extra width" setting (field_2852).
 */
public class Panel {
   public static final int HEADER_HEIGHT = 14;
   public static final int DEFAULT_WIDTH = 92;

   public final ClickGuiScreen screen;
   public final String title;
   public final List<ModuleButton> buttons = new ArrayList<>();

   private int x, y;
   private int dragOffsetX, dragOffsetY;
   private boolean dragging;
   private boolean expanded = true;
   private final Animation expandAnim = new Animation(8F, 1F);
   private int lastFullHeight = HEADER_HEIGHT;

   public Panel(ClickGuiScreen screen, String title) {
      this.screen = screen;
      this.title = title;
   }

   public int x() { return this.x; }
   public int y() { return this.y; }
   public void setX(int x) { this.x = x; }
   public void setY(int y) { this.y = y; }

   public int width() {
      UIModule ui = UIModule.field_2843;
      Integer extra = ui == null ? 0 : ui.field_2852.getValue();
      return DEFAULT_WIDTH + (extra == null ? 0 : extra);
   }

   public int totalHeight() { return this.lastFullHeight; }
   public boolean isDragging() { return this.dragging; }
   public boolean isHeader(double mx, double my) {
      return mx >= this.x && mx < this.x + this.width() && my >= this.y && my < this.y + HEADER_HEIGHT;
   }

   public void render(DrawContext ctx, int mouseX, int mouseY) {
      UIModule ui = UIModule.field_2843;
      MinecraftClient mc = MinecraftClient.getInstance();
      int w = this.width();

      // header
      ctx.fill(this.x, this.y, this.x + w, this.y + HEADER_HEIGHT, ColorUtil.argb(ui.field_2879.getValue()));
      String title = this.title;
      ctx.drawText(mc.textRenderer, title,
            this.x + 2,
            this.y + (HEADER_HEIGHT - mc.textRenderer.fontHeight) / 2 + 1,
            0xFFFFFFFF, false);

      // body
      this.expandAnim.target(this.expanded);
      float anim = this.expandAnim.tick();

      int bodyStart = this.y + HEADER_HEIGHT;
      int bodyEnd   = bodyStart;
      if (anim > 0F) {
         int contentH = 0;
         for (ModuleButton b : this.buttons) {
            if (this.screen.matchesSearch(b)) contentH += b.height();
         }
         int clipped = Math.round(contentH * anim);
         bodyEnd = bodyStart + clipped;
         ctx.fill(this.x, bodyStart, this.x + w, bodyEnd, ColorUtil.argb(ui.field_2880.getValue()));
         ctx.enableScissor(this.x, bodyStart, this.x + w, bodyEnd);
         int rowY = bodyStart;
         for (ModuleButton b : this.buttons) {
            if (!this.screen.matchesSearch(b)) continue;
            b.render(ctx, this.x, rowY, w, mouseX, mouseY);
            rowY += b.height();
         }
         ctx.disableScissor();
      }

      // outline (toggle via UIModule.field_2858)
      if (ui.field_2858.getValue()) {
         int outline = ColorUtil.argb(ui.field_2879.getValue());
         int h = bodyEnd - this.y;
         ctx.drawBorder(this.x - 1, this.y - 1, w + 2, h + 2, outline);
      }
      // shadow (toggle via UIModule.field_2861)
      if (ui.field_2861.getValue()) {
         Float sz = ui.field_2863.getValue();
         int s = sz == null ? 1 : Math.max(1, sz.intValue());
         int shadow = ColorUtil.argb(ui.field_2862.getValue());
         ctx.fill(this.x + s, bodyEnd, this.x + w + s, bodyEnd + s, shadow);
         ctx.fill(this.x + w, this.y + s, this.x + w + s, bodyEnd + s, shadow);
      }

      this.lastFullHeight = bodyEnd - this.y;
   }

   public void onMouseMove(int mouseX, int mouseY, int screenW, int screenH) {
      if (this.dragging) {
         this.x = clamp(mouseX - this.dragOffsetX, 0, screenW - this.width());
         this.y = clamp(mouseY - this.dragOffsetY, 0, screenH - HEADER_HEIGHT);
      }
   }

   public boolean mouseClicked(double mx, double my, int button) {
      if (isHeader(mx, my)) {
         if (button == 0) {
            this.dragging = true;
            this.dragOffsetX = (int) (mx - this.x);
            this.dragOffsetY = (int) (my - this.y);
            return true;
         }
         if (button == 1) {
            this.expanded = !this.expanded;
            return true;
         }
      }
      if (this.expanded && this.expandAnim.value() > 0.95F) {
         int rowY = this.y + HEADER_HEIGHT;
         int w = this.width();
         for (ModuleButton b : this.buttons) {
            if (!this.screen.matchesSearch(b)) continue;
            if (b.mouseClicked(mx, my, button, this.x, rowY, w)) return true;
            rowY += b.height();
         }
      }
      return false;
   }

   public void mouseReleased(double mx, double my, int button) {
      this.dragging = false;
      if (this.expanded) {
         int rowY = this.y + HEADER_HEIGHT;
         int w = this.width();
         for (ModuleButton b : this.buttons) {
            if (!this.screen.matchesSearch(b)) continue;
            b.mouseReleased(mx, my, button, this.x, rowY, w);
            rowY += b.height();
         }
      }
   }

   public boolean mouseScrolled(double mx, double my, double dy) {
      if (this.expanded) {
         int rowY = this.y + HEADER_HEIGHT;
         int w = this.width();
         for (ModuleButton b : this.buttons) {
            if (!this.screen.matchesSearch(b)) continue;
            if (b.mouseScrolled(mx, my, dy, this.x, rowY, w)) return true;
            rowY += b.height();
         }
      }
      return false;
   }

   public boolean keyPressed(int key, int scan, int mods) {
      if (this.expanded) {
         int rowY = this.y + HEADER_HEIGHT;
         int w = this.width();
         for (ModuleButton b : this.buttons) {
            if (!this.screen.matchesSearch(b)) continue;
            if (b.keyPressed(key, scan, mods, this.x, rowY, w)) return true;
            rowY += b.height();
         }
      }
      return false;
   }

   public boolean charTyped(char ch, int mods) {
      if (this.expanded) {
         for (ModuleButton b : this.buttons) {
            if (!this.screen.matchesSearch(b)) continue;
            for (SettingComponent c : b.components) {
               if (c.charTyped(ch, mods)) return true;
            }
         }
      }
      return false;
   }

   public void shiftY(int dy, int clampMin, int clampMax) {
      this.y = clamp(this.y + dy, clampMin, clampMax);
   }

   private static int clamp(int v, int lo, int hi) { return Math.max(lo, Math.min(hi, v)); }
}
