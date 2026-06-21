package me.mioclient.internal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.mioclient.api.Class_0098;

public class Class_1057 extends MessageToByteEncoder<Class_0098> {
   public Class_1057() {
      super();
   }

   @Override
   public void encode(ChannelHandlerContext var1, Class_0098 var2, ByteBuf var3) {
      byte[] var4 = Class_0338.method_37(var2.method_26());
      var1.writeAndFlush(Unpooled.copiedBuffer(var4));
   }
}
