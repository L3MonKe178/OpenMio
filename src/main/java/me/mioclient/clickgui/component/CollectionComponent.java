package me.mioclient.clickgui.component;

import java.util.Collection;
import me.mioclient.clickgui.ModuleButton;
import me.mioclient.clickgui.util.ColorUtil;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

/**
 * Read-only display for {@code Setting<Set<X>>}, {@code Setting<List<X>>}, etc.
 * The original Mio Client pops out a full picker for these (block / item / sound
 * grids); here we just render "name: [count]" so the user can see they exist —
 * a richer picker can be wired later by replacing this component.
 */
public class CollectionComponent extends SettingComponent {

   public CollectionComponent(ModuleButton owner, Setting<? extends Collection<?>> setting) {
      super(owner, setting);
   }

   @SuppressWarnings("unchecked")
   private Setting<Collection<?>> s() { return (Setting<Collection<?>>) this.setting; }

   @Override
   public void render(DrawContext ctx, int x, int y, int width, int mouseX, int mouseY) {
      MinecraftClient mc = MinecraftClient.getInstance();
      UIModule ui = UIModule.field_2843;
      int color = ColorUtil.argb(ui.field_2876.getValue());
      ctx.drawText(mc.textRenderer, this.setting.getName(), x + 4, y + 2, color, false);

      Collection<?> coll = s().getValue();
      String tag = "[" + (coll == null ? 0 : coll.size()) + "]";
      int tw = mc.textRenderer.getWidth(tag);
      ctx.drawText(mc.textRenderer, tag, x + width - tw - 4, y + 2, color, false);
   }
}
