package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_1054;
import me.mioclient.event.Event_10;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.abstract_.AbstractModule_28;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public final class Class_0910 implements Class_1309 {
   public final AbstractModule_28 field_2886;
   public boolean field_821;

   public Class_0910(AbstractModule_28 var1) {
      super();
      this.field_2886 = var1;
   }

   public void method_40() {
      if (this.method_295()) {
         if (this.field_2886.field_3745.getValue() == Class_1054.SILENT) {
            Class_1261.method_2(
               field_4219.player.getX(),
               field_4219.player.getY(),
               field_4219.player.getZ(),
               field_4219.player.getYaw(),
               field_4219.player.getPitch(),
               field_4219.player.isOnGround()
            );
         }

         this.method_221(true);
      }
   }

   public void method_9(Event_10 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket var2 && this.method_2(var2)) {
         float var4 = var2.getYaw(0.0F) + Float.intBitsToFloat(1359354950);
         ((DuckPlayerMoveC2SPacket)var2).setYaw(var4);
      }
   }

   public boolean method_295() {
      return this.method_2(null);
   }

   public boolean method_2(PlayerMoveC2SPacket var1) {
      if (!Class_0464.method_363() && Hub.field_2614.method_874() < Double.longBitsToDouble(4617315517961601024L)) {
         return false;
      } else if (!this.field_2886.field_3742.getValue()) {
         return false;
      } else if (Class_0382.method_428()) {
         return false;
      } else {
         if (var1 != null) {
            boolean var2 = field_4219.currentScreen instanceof HandledScreen && field_4219.player.currentScreenHandler == field_4219.player.playerScreenHandler;
            if (!var1.changesLook() && !var2) {
               return false;
            }
         }

         return this.field_2886.field_3749 || var1 == null;
      }
   }

   public boolean method_826() {
      return this.field_821;
   }

   public void method_221(boolean var1) {
      this.field_821 = var1;
   }
}
