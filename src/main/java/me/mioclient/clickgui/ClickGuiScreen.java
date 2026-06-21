package me.mioclient.clickgui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.client.HUDModule;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/**
 * Faithful rewrite of Class_0406 + Class_1117.
 *
 * Visual model (matches original):
 *   • Panels laid out left→right at y=5, x starting at 10, gap 3px.
 *   • Gradient background using UIModule's bg + accent colors.
 *   • 150ms fade-in / fade-out animation on open and ESC close.
 *   • Search bar at top-right with autocomplete suggestion; ENTER expands+blinks
 *     the first matching module.
 *   • Konami code (UP UP DOWN DOWN LEFT RIGHT LEFT RIGHT B A) acts as a hook for
 *     future easter-egg unlock — currently latched but does not add a hidden panel.
 *   • Tooltip text rendered last as a small bg+text bubble near the cursor.
 *   • Mouse-wheel scrolls all panels (clamped between -maxHeight..5 as in original).
 *
 * What this build intentionally leaves out vs the original:
 *   • The Tetris/Snake hidden panel (Class_0185 + Class_0816 + Class_0571 — 700+
 *     lines of game state). The Konami latch still ticks so a future hidden panel
 *     can hook into {@link #easterEggUnlocked}.
 *   • Winter snowflake overlay (Class_0325).
 *   • Custom font and gui-scaling (uses vanilla TextRenderer at 1:1).
 *   • The BlurModule mask pass (depends on a custom post-process shader).
 */
public class ClickGuiScreen extends Screen {
   private static final int[] KONAMI = { 265, 265, 264, 264, 263, 262, 263, 262, 66, 65 };
   private static final long  FADE_MS = 150L;

   private static ClickGuiScreen INSTANCE;

   public final List<Panel> panels = new ArrayList<>();
   public final List<ModuleButton> blinking = new ArrayList<>();

   private String searchText = "";
   private String suggestion = "";
   private boolean searchFocused;
   private long caretToggleMs;
   private boolean caretOn;

   private int konamiIdx;
   private boolean easterEggUnlocked;

   private long openMs;
   private boolean closing;

   private String pendingTooltip;

   public ClickGuiScreen() {
      super(Text.literal("mio"));
      int x = 10;
      for (Category cat : Category.values()) {
         if (cat == Category.HUD) continue;
         Panel p = new CategoryPanel(this, cat);
         p.setX(x);
         p.setY(5);
         this.panels.add(p);
         x += p.width() + 3;
      }
   }

   public static ClickGuiScreen instance() {
      if (INSTANCE == null) INSTANCE = new ClickGuiScreen();
      return INSTANCE;
   }

   public boolean matchesSearch(ModuleButton b) {
      if (this.searchText.isEmpty()) return true;
      String q = this.searchText.toLowerCase(Locale.ROOT);
      for (String a : b.module.getAliases()) {
         if (a != null && a.toLowerCase(Locale.ROOT).contains(q)) return true;
      }
      return false;
   }

   public void setTooltip(String text) { this.pendingTooltip = text; }

   @Override
   protected void init() {
      super.init();
      this.openMs = System.currentTimeMillis();
      this.closing = false;
      ModuleButton.resetHoverState();
   }

   private float fadeAlpha() {
      float t = (System.currentTimeMillis() - this.openMs) / (float) FADE_MS;
      if (this.closing) t = 1F - t;
      return Math.max(0F, Math.min(1F, t));
   }

   @Override
   public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
      // tick close animation
      if (this.closing && System.currentTimeMillis() - this.openMs >= FADE_MS) {
         super.close();
         return;
      }

      float alpha = this.fadeAlpha();
      drawBackground(ctx, alpha);

      // Drag follow — exactly one panel may be dragging.
      for (Panel p : this.panels) p.onMouseMove(mouseX, mouseY, this.width, this.height);

      // Render panels back-to-front so newer click order draws on top.
      this.pendingTooltip = null;
      for (Panel p : this.panels) p.render(ctx, mouseX, mouseY);

      // Reap finished blinks like the original (keeps at most 3 highlighted).
      this.blinking.removeIf(ModuleButton::isClosed);
      while (this.blinking.size() > 3) this.blinking.remove(0);

      drawSearch(ctx);
      drawTooltip(ctx, mouseX, mouseY);
   }

   private void drawBackground(DrawContext ctx, float alpha) {
      UIModule ui = UIModule.field_2843;
      java.awt.Color bgCol = ui.field_2883.getValue();
      java.awt.Color fgCol = ui.field_2884.getValue();
      if (bgCol != null && bgCol.getAlpha() > 0) {
         ctx.fill(0, 0, this.width, this.height, ColorUtil.withAlphaMul(bgCol, alpha));
      }
      if (fgCol != null && fgCol.getAlpha() > 0) {
         int top = ColorUtil.withAlphaMul(fgCol, 0F);
         int bot = ColorUtil.withAlphaMul(fgCol, alpha);
         ctx.fillGradient(0, 0, this.width, this.height, top, bot);
      }
   }

   private void drawSearch(DrawContext ctx) {
      MinecraftClient mc = MinecraftClient.getInstance();
      UIModule ui = UIModule.field_2843;
      if (this.searchFocused) {
         long now = System.currentTimeMillis();
         if (now - this.caretToggleMs > 500L) {
            this.caretOn = !this.caretOn;
            this.caretToggleMs = now;
         }
         String shown = (this.caretOn ? "_" : "") + this.searchText + this.suggestion;
         int w = mc.textRenderer.getWidth(shown);
         int y = this.height - mc.textRenderer.fontHeight - 2;
         int x = this.width - w - 4;
         ctx.drawText(mc.textRenderer, this.caretOn ? "_" : "",
               x, y, 0xFFFFFFFF, false);
         ctx.drawText(mc.textRenderer, this.searchText,
               x + mc.textRenderer.getWidth(this.caretOn ? "_" : ""), y,
               ColorUtil.argb(ui.field_2877.getValue()), false);
         ctx.drawText(mc.textRenderer, this.suggestion,
               x + mc.textRenderer.getWidth((this.caretOn ? "_" : "") + this.searchText), y,
               ColorUtil.argb(ui.field_2876.getValue()), false);
      } else {
         String hint = "Ctrl + F to activate search.";
         int w = mc.textRenderer.getWidth(hint);
         ctx.drawText(mc.textRenderer, hint,
               this.width - w - 2,
               this.height - mc.textRenderer.fontHeight - 2,
               0xC0A0A0A0, false);
      }
   }

   private void drawTooltip(DrawContext ctx, int mouseX, int mouseY) {
      if (this.pendingTooltip == null || this.pendingTooltip.isBlank()) return;
      MinecraftClient mc = MinecraftClient.getInstance();
      String[] lines = this.pendingTooltip.split("\n");
      int textW = 0;
      for (String s : lines) textW = Math.max(textW, mc.textRenderer.getWidth(s));
      int padX = 3, padY = 2;
      int x0 = mouseX + 9;
      int y0 = mouseY - 1;
      int w = textW + padX * 2;
      int h = lines.length * (mc.textRenderer.fontHeight + 1) - 1 + padY * 2;
      if (x0 + w > this.width)  x0 = mouseX - w - 2;
      if (y0 + h > this.height) y0 = this.height - h;
      ctx.fill(x0, y0, x0 + w, y0 + h, 0xE0101010);
      ctx.drawBorder(x0, y0, w, h, 0xFF202830);
      int yi = y0 + padY;
      for (String s : lines) {
         ctx.drawText(mc.textRenderer, s, x0 + padX, yi, 0xFFFFFFFF, false);
         yi += mc.textRenderer.fontHeight + 1;
      }
   }

   @Override
   public boolean mouseClicked(double mx, double my, int button) {
      // Bring topmost hit panel to front (original behaviour).
      Panel top = null;
      for (int i = this.panels.size() - 1; i >= 0; i--) {
         Panel p = this.panels.get(i);
         if (p.mouseClicked(mx, my, button)) {
            top = p;
            break;
         }
      }
      if (top != null) {
         this.panels.remove(top);
         this.panels.add(top);
         return true;
      }
      // Click anywhere drops search focus.
      this.searchFocused = false;
      return super.mouseClicked(mx, my, button);
   }

   @Override
   public boolean mouseReleased(double mx, double my, int button) {
      for (Panel p : this.panels) p.mouseReleased(mx, my, button);
      return super.mouseReleased(mx, my, button);
   }

   @Override
   public boolean mouseScrolled(double mx, double my, double horiz, double vert) {
      MinecraftClient mc = MinecraftClient.getInstance();
      UIModule ui = UIModule.field_2843;
      int step = 2 + mc.textRenderer.fontHeight + ui.field_2851.getValue();
      int maxBottom = 5;
      int maxTop = 0;
      for (Panel p : this.panels) maxTop = Math.max(maxTop, p.totalHeight());
      maxTop = -Math.max(maxTop - this.height + 5, 0);

      for (Panel p : this.panels) {
         if (p.isDragging()) continue;
         if (p.mouseScrolled(mx, my, vert)) continue;
         p.shiftY((int) (vert * step), maxTop, maxBottom);
      }
      return super.mouseScrolled(mx, my, horiz, vert);
   }

   @Override
   public boolean keyPressed(int key, int scan, int mods) {
      // Konami latch — runs every keypress.
      if (!this.easterEggUnlocked && key == KONAMI[this.konamiIdx]) {
         this.konamiIdx++;
         if (this.konamiIdx == KONAMI.length) { this.easterEggUnlocked = true; this.konamiIdx = 0; }
      } else if (!this.easterEggUnlocked) {
         this.konamiIdx = 0;
      }

      // Ctrl+F → toggle / focus search
      if (key == 70 && (mods & 2) != 0) {
         if (this.searchFocused) {
            this.searchFocused = false;
            this.searchText = "";
            this.suggestion = "";
         } else {
            this.searchFocused = true;
         }
         return true;
      }

      // Search-focused ENTER → expand & blink first match (matches Class_0406 line 159)
      if (this.searchFocused && key == 257 && !this.searchText.isEmpty()) {
         String full = (this.searchText + this.suggestion).toLowerCase(Locale.ROOT);
         outer:
         for (Panel p : this.panels) {
            for (ModuleButton b : p.buttons) {
               if (b.module.getName().equalsIgnoreCase(this.searchText + this.suggestion)
                     || b.module.getName().toLowerCase(Locale.ROOT).equals(full)) {
                  if (!b.isExpanded()) b.mouseClicked(0, 0, 1, 0, 0, p.width());
                  b.blink();
                  this.blinking.add(b);
                  break outer;
               }
            }
         }
         this.searchText = "";
         this.suggestion = "";
         this.searchFocused = false;
         return true;
      }

      // TAB → accept suggestion
      if (this.searchFocused && key == 258 && !this.suggestion.isEmpty()) {
         this.searchText += this.suggestion;
         this.suggestion = "";
         return true;
      }

      // BACKSPACE / DELETE on search
      if (this.searchFocused && (key == 259 || key == 261) && !this.searchText.isEmpty()) {
         this.searchText = this.searchText.substring(0, this.searchText.length() - 1);
         updateSuggestion();
         return true;
      }

      // ESC closes with animation (Class_1117.method_257)
      if (key == 256) {
         beginClose();
         return true;
      }

      for (Panel p : this.panels) {
         if (p.keyPressed(key, scan, mods)) return true;
      }
      return super.keyPressed(key, scan, mods);
   }

   @Override
   public boolean charTyped(char ch, int mods) {
      if (this.searchFocused) {
         if (ch >= 32 && ch != 127) {
            this.searchText += ch;
            updateSuggestion();
            return true;
         }
         return false;
      }
      for (Panel p : this.panels) if (p.charTyped(ch, mods)) return true;
      return super.charTyped(ch, mods);
   }

   private void updateSuggestion() {
      this.suggestion = "";
      if (this.searchText.isEmpty()) return;
      String q = this.searchText.toLowerCase(Locale.ROOT);
      for (Panel p : this.panels) {
         for (ModuleButton b : p.buttons) {
            Module m = b.module;
            if (m instanceof HUDModule || m instanceof AbstractModule_26) continue;
            String name = m.getName();
            if (name.toLowerCase(Locale.ROOT).startsWith(q)) {
               this.suggestion = name.substring(this.searchText.length());
               return;
            }
         }
      }
   }

   private void beginClose() {
      if (this.closing) return;
      this.closing = true;
      this.openMs = System.currentTimeMillis();
      if (UIModule.field_2843 != null && UIModule.field_2843.isToggled()) {
         UIModule.field_2843.method_68();
      }
   }

   @Override
   public void close() {
      beginClose();
   }

   @Override
   public boolean shouldPause() { return false; }
}
