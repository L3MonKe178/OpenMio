package me.mioclient.module.misc;

import me.mioclient.event.Event_1;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import nick.Settings;

public class CoordLoggerModule extends Module {
   public Setting<Boolean> field_839;
   public Setting<Boolean> field_840;
   public String text;

   public CoordLoggerModule() {
      super("CoordLogger", "Copies your coordinates whenever you log/die.", Category.MISC);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.text = "X: %d, Y: %d, Z: %d in The %s"
         .formatted(field_4219.player.getBlockX(), field_4219.player.getBlockY(), field_4219.player.getBlockZ(), Class_1225.method_1071().method_235());
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof DeathMessageS2CPacket && this.field_840.getValue()) {
         this.method_300();
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      if (field_4219.player != null && this.field_839.getValue()) {
         this.method_300();
      }
   }

   public void method_300() {
      field_4219.keyboard.setClipboard(this.text);
   }
}
