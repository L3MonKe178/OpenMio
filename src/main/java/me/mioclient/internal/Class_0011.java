package me.mioclient.internal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.List;
import me.mioclient.api.Class_0098;

public class Class_0011 extends ByteToMessageDecoder {
   public Class_0011() {
      super();
   }

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.io.IOException {
      byte[] var4 = new byte[var2.readableBytes()];
      var2.readBytes(var4);
      ByteArrayInputStream var5 = new ByteArrayInputStream(var4);
      DataInputStream var6 = new DataInputStream(var5);
      short var7 = var6.readShort();
      Class_0098 var8 = Class_1169.method_9(var7);
      if (var8 == null) {
         throw new NullPointerException(new Class_1303().method_2(var7).method_9("Couldn't find incoming packet with ID \u0001"));
      } else {
         var3.add(var8.method_9(var4));
      }
   }
}
