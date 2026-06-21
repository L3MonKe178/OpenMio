package me.mioclient.internal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import java.util.List;

public class Class_1307 extends ByteArrayDecoder {
   public Class_1307() {
      super();
   }

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      int var4 = var2.readableBytes();
      if (var4 > 0) {
         byte[] var5 = new byte[var4];
         var2.readBytes(var5);
         byte[] var6 = Class_0338.method_33(var5);
         var3.add(Unpooled.copiedBuffer(var6));
      }
   }
}
