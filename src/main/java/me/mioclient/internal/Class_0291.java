package me.mioclient.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0075;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class Class_0291 implements MioAPI, Class_1146 {
   public static final String field_932 = "auth.mioclient.me";
   public static final int field_933 = 48002;
   public static final int field_934 = 65536;
   public CompletableFuture<?> field_935 = CompletableFuture.completedFuture(null);
   public final Set<String> field_936 = new HashSet<>();
   public final Map<String, String> field_937 = new ConcurrentHashMap<>();
   public final List<Class_1257> field_938 = new ArrayList<>();
   public String[] field_939;
   public final EventLoopGroup field_940 = new NioEventLoopGroup();
   public final Bootstrap field_941;
   public Channel field_942;
   public boolean field_943;
   public long field_944;

   public Class_0291() {
      super();
      field_4220.method_14(this);
      this.field_941 = new Bootstrap();
      ((Bootstrap)((Bootstrap)this.field_941.group(this.field_940)).channel(NioSocketChannel.class)).handler(new Class_0648());
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_943 && !this.method_326() && this.field_944 + 10000L < System.currentTimeMillis()) {
         this.field_943 = false;
         this.method_322();
      }
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      JsonArray var2 = new JsonArray();

      for (String var4 : this.field_936) {
         var2.add(var4);
      }

      var1.add("ignore", var2);
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();
      if (var2.has("ignore")) {
         for (JsonElement var4 : var2.getAsJsonArray("ignore")) {
            this.field_936.add(var4.getAsString());
         }
      }
   }

   @Override
   public String getConfigName() {
      return "irc.json";
   }

   public void method_322() {
      if (this.field_935.isDone()) {
         this.field_935 = CompletableFuture.supplyAsync(() -> {
            try {
               this.field_942 = this.field_941.connect("auth.mioclient.me", 48002).sync().channel();
            } catch (InterruptedException var2) {
            }

            return this.field_942;
         }, field_4221);
      }
   }

   public void method_202() {
      if (this.method_326()) {
         if (this.field_935.isDone()) {
            this.field_942.disconnect();
         } else {
            this.field_935.thenAccept(var1 -> this.method_202());
         }
      }

      this.field_942 = null;
   }

   public void method_132(String var1) {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_1335("crash", var1));
      }
   }

   public void method_323(String var1) {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_1335("ban", var1));
      }
   }

   public void method_231(String var1) {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_1335("unban", var1));
      }
   }

   public void method_324() {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_1335("players", "asd"));
      }
   }

   public void method_214(String var1) {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_0167(var1));
      } else {
         this.method_327();
      }
   }

   public void method_325() {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_1228());
      } else {
         this.method_327();
      }
   }

   public void method_2(String var1, String var2, BlockPos var3) {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_0476(var1, var2, var3.getX(), var3.getY(), var3.getZ()));
      } else {
         this.method_327();
      }
   }

   public void method_15(String var1) {
      if (this.method_326()) {
         this.field_942.writeAndFlush(new Class_1065(var1, field_4219.getSession().getUsername()));
      } else {
         this.method_327();
      }
   }

   public boolean method_326() {
      return this.field_942 != null && this.field_942.isActive();
   }

   public void method_327() {
      try {
         ChatUtil.method_2(
            Text.literal(new TextBuilder().method_2(String.valueOf(Formatting.RED)).method_9("\u0001You are not connected to the chat server")),
            ChatUtil.method_38(12482345),
            Priority.MID
         );
      } catch (Exception var2) {
      }
   }

   public List<Class_1257> method_328() {
      return this.field_938;
   }

   public Map<String, String> method_329() {
      return this.field_937;
   }

   public Identifier method_68(String var1) {
      for (Class_0075 var5 : Class_0075.values()) {
         if (var5.getName().equalsIgnoreCase(this.field_937.get(var1))) {
            return var5.method_108();
         }
      }

      return null;
   }

   public String[] method_330() {
      return this.field_939;
   }

   public void method_5(String[] var1) {
      this.field_939 = var1;
   }

   public Set<String> method_331() {
      return this.field_936;
   }

   public void method_114(boolean var1) {
      if (var1 && !this.field_943) {
         String var2 = "Lost connection to the chat server. Retrying in 10 seconds";
         System.err.println(var2);
         Hub.field_2619
            .method_2(
               () -> ChatUtil.method_2(
                     Text.literal(new TextBuilder().method_2((Object)var2).method_2(String.valueOf(Formatting.RED)).method_9("\u0001\u0001")),
                     ChatUtil.method_38(8345486),
                     Priority.MID
                  ),
               0
            );
      }

      this.field_943 = var1;
      this.field_944 = System.currentTimeMillis();
   }
}
