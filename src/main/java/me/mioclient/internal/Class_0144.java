package me.mioclient.internal;

import io.netty.buffer.Unpooled;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0034;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;

public class Class_0144 implements MioAPI {
   public Class_0144() {
      super();
   }

   public static Entity method_2(PlayerInteractEntityC2SPacket var0) {
      PacketByteBuf var1 = new PacketByteBuf(Unpooled.buffer());
      var0.write(var1);
      return field_4219.world.getEntityById(var1.readVarInt());
   }

   public static Class_0034 method_9(PlayerInteractEntityC2SPacket var0) {
      PacketByteBuf var1 = new PacketByteBuf(Unpooled.buffer());
      var0.write(var1);
      var1.readVarInt();
      return (Class_0034)var1.readEnumConstant(Class_0034.class);
   }
}
