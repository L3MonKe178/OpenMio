package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Subscribe;
import me.mioclient.module.movement.HoleSnapModule;
import me.mioclient.module.movement.WarpModule;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public final class Class_0444 implements MioAPI {
   public static boolean field_1422;
   public static WarpModule field_1423 = Hub.field_2595.method_78(WarpModule.class);
   public final HoleSnapModule field_1424;
   public int field_31;

   public Class_0444(HoleSnapModule var1) {
      super();
      this.field_1424 = var1;
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_363()) {
         this.field_31 = Math.min(field_1423.field_1110.getValue(), ++this.field_31);
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (this.method_363() && this.field_31 != 0) {
         if (var1.method_127() instanceof PlayerMoveC2SPacket && field_1422 || field_1423.isToggled()) {
            this.field_31 = Math.max(0, --this.field_31);
         }
      }
   }

   public boolean method_484() {
      if (this.field_31 > 0 && this.method_363() && this.field_1424.field_1170.getValue() && field_4219.player.isOnGround() && Hub.field_2615.method_1161() > 1
         )
       {
         Hub.field_2617.method_2(this.field_1424, (float)field_1423.field_1109.getValue().intValue());
         return true;
      } else {
         return false;
      }
   }

   public boolean method_363() {
      return field_4219.player.getPos().squaredDistanceTo(field_4219.player.prevX, field_4219.player.prevY, field_4219.player.prevZ) > 0.0;
   }
}
