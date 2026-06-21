package me.mioclient.internal;

import java.util.LinkedList;
import java.util.Queue;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0399;
import me.mioclient.enum_.Class_1269;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Subscribe;
import me.mioclient.module.movement.WarpModule;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CommonPongC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Class_1359 implements MioAPI {
   public final Queue<Packet<?>> field_4427 = new LinkedList<>();
   public final WarpModule field_4428;

   public Class_1359(WarpModule var1) {
      super();
      field_4220.method_14(this);
      this.field_4428 = var1;
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.field_4428.method_363() && this.field_4428.field_1108.getValue() == Class_1269.PLAIN) {
         this.field_4428.field_1119 = Math.min(this.field_4428.field_1110.getValue(), this.field_4428.field_1119 + this.field_4428.field_1111.getValue());
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (!this.method_535()) {
         if (this.field_4428.isToggled()
            && this.field_4428.field_1108.getValue() == Class_1269.ALTERNATIVE
            && var1.method_127() instanceof CommonPongC2SPacket var2) {
            if (!this.field_4428.method_385() && this.field_4427.size() < this.field_4428.field_1110.getValue()) {
               var1.method_463();
               this.field_4427.add(var2);
            }

            if (!this.field_4427.isEmpty()) {
               var1.method_463();
            }
         }

         if (this.field_4428.field_1108.getValue() == Class_1269.PLAIN && var1.method_127() instanceof PlayerMoveC2SPacket) {
            if (!this.field_4428.method_363()
               || this.field_4428.field_1119 == 0
               || !this.field_4428.isToggled() && this.field_4428.field_1112.getValue() == Class_0399.HOLD) {
               return;
            }

            this.field_4428.field_1119 = Math.max(0, this.field_4428.field_1119 - 1);
         }
      }
   }
}
