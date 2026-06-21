package me.mioclient.internal;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import me.mioclient.Hub;
import me.mioclient.api.Class_0098;

public class Class_1055 extends SimpleChannelInboundHandler<Class_0098> {
   public final CopyOnWriteArrayList<ScheduledFuture<?>> field_3254 = new CopyOnWriteArrayList<>();

   public Class_1055() {
      super();
   }

   @Override
   protected void channelRead0(ChannelHandlerContext var1, Class_0098 var2) {
      var2.method_2(var1);
      var1.flush();
   }

   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      var2.printStackTrace();
      var1.close();
      Class_0968.reset();
      Class_0170.method_208();
      Hub.field_2610.method_202();
      Hub.field_2610.method_114(true);
   }
}
