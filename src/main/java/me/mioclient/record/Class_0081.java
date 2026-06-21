package me.mioclient.record;

import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;

public final class Class_0081 {
   public final ScreenHandlerSlotUpdateS2CPacket field_281;
   public final long field_282;

   public Class_0081(ScreenHandlerSlotUpdateS2CPacket var1, long var2) {
      super();
      this.field_281 = var1;
      this.field_282 = var2;
   }

   public ScreenHandlerSlotUpdateS2CPacket method_117() {
      return this.field_281;
   }

   public long method_118() {
      return this.field_282;
   }
}
