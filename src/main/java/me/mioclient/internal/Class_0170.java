package me.mioclient.internal;

import io.netty.channel.Channel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0304;

public class Class_0170 implements Class_1309 {
   public static final CopyOnWriteArrayList<ScheduledFuture<?>> field_485 = new CopyOnWriteArrayList<>();

   public Class_0170() {
      super();
   }

   public static void method_2(Channel var0) {
      io.netty.util.concurrent.ScheduledFuture var1 = var0.eventLoop().scheduleAtFixedRate(() -> {
         List<String> var1x = Hub.field_2603.method_2(Class_0304.FRIEND);
         if (field_4219.player != null) {
            var1x.removeIf(var0xx -> field_4219.player.networkHandler.getPlayerListEntry(var0xx) == null);
         } else {
            var1x.clear();
         }

         var0.writeAndFlush(new Class_0085(field_4219.getSession().getUsername(), var1x.toArray(String[]::new)));
      }, 0L, 10L, TimeUnit.SECONDS);
      field_485.add(var1);
   }

   public static void method_208() {
      if (!field_485.isEmpty()) {
         field_485.forEach(var0 -> var0.cancel(true));
         field_485.clear();
      }
   }
}
