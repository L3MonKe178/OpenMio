package me.mioclient.module.misc;

import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import nick.Settings;

public class NoPacketKickModule extends Module {
   public Setting<Boolean> field_4173;

   public NoPacketKickModule() {
      super("NoPacketKick", "Cancels broken packets that may cause you getting kicked.", Category.MISC);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   public Text method_38(Throwable var1) {
      return Text.translatable("disconnect.genericReason", new Object[]{new TextBuilder().method_2(String.valueOf(var1)).method_9("Internal Exception: \u0001")})
         .formatted(Formatting.RED);
   }
}
