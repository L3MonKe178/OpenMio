package me.mioclient.internal;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import me.mioclient.Hub;

public class Class_0648 extends ChannelInitializer<SocketChannel> {
   public Class_0648() {
      super();
   }

   @Override
   public void initChannel(SocketChannel var1) {
      ChannelPipeline var2 = var1.pipeline();
      var1.config().setRecvByteBufAllocator(new FixedRecvByteBufAllocator(65536));
      var2.addLast(new ChannelHandler[]{new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 4)});
      var2.addLast(new ChannelHandler[]{new LengthFieldPrepender(4)});
      var2.addLast(new ChannelHandler[]{new Class_0709()});
      var2.addLast(new ChannelHandler[]{new Class_1057(), new Class_1307()});
      var2.addLast(new ChannelHandler[]{new Class_0011()});
      var2.addLast(new ChannelHandler[]{new Class_1055()});
   }

   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      Hub.field_2610.method_114(true);
   }
}
