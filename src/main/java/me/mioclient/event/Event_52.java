package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;

public class Event_52 extends Event {
   public PlayerInteractItemC2SPacket field_3958;

   public Event_52(PlayerInteractItemC2SPacket var1) {
      super();
      this.field_3958 = var1;
   }

   public PlayerInteractItemC2SPacket method_1102() {
      return this.field_3958;
   }

   public void method_2(PlayerInteractItemC2SPacket var1) {
      this.field_3958 = var1;
   }

   public void method_9(float[] var1) {
      this.method_2(new PlayerInteractItemC2SPacket(this.field_3958.getHand(), this.field_3958.getSequence(), var1[0], var1[1]));
      this.method_463();
   }
}
