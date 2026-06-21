package me.mioclient.module.misc;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import me.mioclient.api.Class_1322;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_46;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0123;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.hud.ChatHudLine.Visible;
import net.minecraft.text.Text;
import nick.Settings;

public class AntiSpamModule extends Module {
   public static final Pattern field_3856 = Pattern.compile(
      "(https?:\\/\\/)?([\\w\\-])+\\.{1}([a-zA-Z]{2,63})([\\/\\w-]*)*\\/?\\??([^#\\n\\r]*)?#?([^\\n\\r]*)"
   );
   public final Map<String, Class_0123<Long, Visible>> field_3857 = new HashMap<>();
   public Setting<Boolean> field_3858;
   public Setting<Boolean> field_3859;
   public String field_3860;

   public AntiSpamModule() {
      super("AntiSpam", "Stacks similar chat messages with each other.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Override
   public void onToggle() {
      this.field_3857.clear();
      this.field_3860 = null;
   }

   @Subscribe
   public void method_9(Event_20 var1) {
      this.field_3857.clear();
      this.field_3860 = null;
   }

   @Subscribe
   public void method_9(Event_46 var1) {
      if (var1.method_213() == PreType.PRE) {
         if (var1.getSignature() != null && var1.getSignature().toByteBuffer().getInt() < 0) {
            return;
         }

         this.field_3860 = var1.method_1033().getString().toLowerCase(Locale.ROOT);
         this.field_3857.computeIfPresent(this.field_3860, (var0, var1x) -> {
            ((Class_1322)field_4219.inGameHud.getChatHud()).getVisible().remove(var1x.method_145());
            return var1x;
         });
         this.field_3857.putIfAbsent(this.field_3860, new Class_0123<>(1L, var1.method_1034()));
         if (this.field_3859.getValue() && field_3856.matcher(this.field_3860).find()) {
            var1.method_463();
         }

         long var2 = this.field_3857.get(this.field_3860).method_144();
         if (var2 > 1L && this.field_3858.getValue()) {
            var1.method_9(Text.empty().append(var1.method_1033()).append(Text.literal(new TextBuilder().method_2(var2).method_9(" [x\u0001]"))));
         }
      } else if (this.field_3860 != null) {
         this.field_3857.computeIfPresent(this.field_3860, (var1x, var2x) -> new Class_0123<>(var2x.method_144() + 1L, var1.method_1034()));
      }
   }
}
