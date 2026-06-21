package me.mioclient.module.combat;

import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0922;
import me.mioclient.internal.Class_1016;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.player.PlayerEntity;
import nick.Settings;

public class AntiBotModule extends Module {
   public Setting<Boolean> field_11;
   public Setting<Boolean> field_12;

   public AntiBotModule() {
      super("AntiBot", "Removes the bots that will be spawned by some anti cheats.", Category.COMBAT);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      PlayerEntity var2 = null;
      synchronized (field_4219.world.getPlayers()) {
         for (PlayerEntity var5 : field_4219.world.getPlayers()) {
            if (this.method_3(var5)) {
               var2 = var5;
            }
         }
      }

      if (var2 != null) {
         var2.setRemoved(RemovalReason.KILLED);
      }
   }

   public boolean method_3(PlayerEntity var1) {
      if (field_4219.player != var1 && !(var1 instanceof Class_0922)) {
         String var2 = var1.getName().getString();
         if ((var2.length() < 3 || var2.length() > 16) && this.field_12.getValue()) {
            return true;
         } else {
            if (this.field_11.getValue()) {
               String var3 = "1234567890_qwertyuiopasdfghjklzxcvbnm";

               for (char var7 : var2.toLowerCase().toCharArray()) {
                  if (!Class_1016.method_2("1234567890_qwertyuiopasdfghjklzxcvbnm", var7)) {
                     return true;
                  }
               }
            }

            PlayerListEntry var8 = field_4219.player.networkHandler.getPlayerListEntry(var1.getGameProfile().getId());
            return !field_4219.player.networkHandler.getPlayerList().contains(var8) || var8 == null || var8.getProfile() == null && var1.hasCustomName();
         }
      } else {
         return false;
      }
   }
}
