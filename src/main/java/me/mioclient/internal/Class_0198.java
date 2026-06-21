package me.mioclient.internal;

import me.mioclient.event.Event_37;
import me.mioclient.event.Event_4;
import me.mioclient.mixin.ducks.DuckEntityVelocityUpdateS2CPacket;
import me.mioclient.module.movement.VelocityModule;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;

public class Class_0198 extends Class_0043 {
   public Class_0198(VelocityModule var1) {
      super(var1);
   }

   @Override
   public void method_5(Event_4 var1) {
      int var2 = this.field_88.field_2510.getValue();
      int var3 = this.field_88.field_2511.getValue();
      int var4 = this.field_88.field_2512.getValue() ? -1 : 1;
      if (var1.method_127() instanceof EntityVelocityUpdateS2CPacket var5 && var5.getEntityId() == field_4219.player.getId()) {
         DuckEntityVelocityUpdateS2CPacket var7 = (DuckEntityVelocityUpdateS2CPacket)var5;
         var7.setX(
            (int)(var5.getVelocityX() * Double.longBitsToDouble(4665518107723300864L) * (double)((float)var2 * Float.intBitsToFloat(1008981770)) * (double)var4)
         );
         var7.setY((int)(var5.getVelocityY() * Double.longBitsToDouble(4665518107723300864L) * (double)((float)var3 * Float.intBitsToFloat(1008981770))));
         var7.setZ(
            (int)(var5.getVelocityZ() * Double.longBitsToDouble(4665518107723300864L) * (double)((float)var2 * Float.intBitsToFloat(1008981770)) * (double)var4)
         );
         if (var2 == 0 && var3 == 0) {
            var1.method_463();
         }
      }
   }

   @Override
   public void method_2(Event_37 var1) {
      if (this.field_88.field_2513.getValue()) {
         int var2 = this.field_88.field_2510.getValue();
         int var3 = this.field_88.field_2511.getValue();
         int var4 = this.field_88.field_2512.getValue() ? -1 : 1;
         var1.method_52((float)((int)(var1.method_398() * (float)var2 * Float.intBitsToFloat(1008981770) * (float)var4)));
         var1.method_185((float)((int)(var1.method_399() * (float)var3 * Float.intBitsToFloat(1008981770))));
         var1.method_92((float)((int)(var1.method_400() * (float)var2 * Float.intBitsToFloat(1008981770) * (float)var4)));
      }
   }
}
