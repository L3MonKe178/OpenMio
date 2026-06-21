package me.mioclient.module.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;
import nick.Settings;

public class AutoSignModule extends Module {
   public static final DateFormat field_4300 = new SimpleDateFormat("dd/MM/yyyy");
   public Setting<String> field_4301;
   public Setting<String> field_4302;
   public Setting<String> field_4303;
   public Setting<String> field_4304;

   public AutoSignModule() {
      super("AutoSign", "Puts stuff on your signs automatically.", Category.MISC);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_29(Event_4 var1) {
      if (var1.method_127() instanceof SignEditorOpenS2CPacket var2) {
         PacketUtil.method_2(
            new UpdateSignC2SPacket(
               var2.getPos(),
               var2.isFront(),
               this.method_4(this.field_4301),
               this.method_4(this.field_4302),
               this.method_4(this.field_4303),
               this.method_4(this.field_4304)
            )
         );
         var1.method_463();
      }
   }

   public String method_4(Setting<String> var1) {
      String var2 = (String)var1.getValue();
      var2 = var2.replace("<date>", field_4300.format(Calendar.getInstance().getTime()));
      var2 = var2.replace("<name>", field_4219.player.getName().getString());
      return var2.substring(0, Math.min(16, var2.length()));
   }
}
