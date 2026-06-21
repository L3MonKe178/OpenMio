package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.net.SocketAddress;
import me.mioclient.Hub;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public final class Class_1028 extends Command {
   public Class_1028() {
      super("irc");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("ping").then(RequiredArgumentBuilder.<CommandSource, Vec3d>argument("pos", new Class_0019()).executes(var1x -> {
               Vec3d var2 = (Vec3d)var1x.getArgument("pos", Vec3d.class);
               this.method_92(BlockPos.ofFloored(var2));
               return 1;
            }))).executes(var1x -> {
               if (field_4219.crosshairTarget instanceof BlockHitResult var2) {
                  this.method_92(var2.getBlockPos());
               }

               return 1;
            })
         ))
         .then(LiteralArgumentBuilder.<CommandSource>literal("help").executes(var0 -> {
            int var1x = -8134;
            if (Hub.field_2610.method_330() == null) {
               return 1;
            } else {
               for (String var5 : Hub.field_2610.method_330()) {
                  String var6 = new TextBuilder().method_2((Object)var5).method_2(CommandManager.method_927()).method_9("\u0001irc \u0001");
                  ChatUtil.method_2(Text.literal(var6), ChatUtil.method_38(--var1x));
               }

               return 1;
            }
         }));
      this.method_2(new Class_1281(), var1);
      if (this.method_2((CommandSource)null)) {
         var1.then(((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("online").requires(var0 -> this.method_2(var0))).executes(var0 -> {
            Hub.field_2610.method_325();
            return 1;
         }));
         var1.then(
            ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("crash").requires(var0 -> this.method_2(var0)))
               .then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word()).executes(var0 -> {
                  Hub.field_2610.method_132((String)var0.getArgument("name", String.class));
                  return 1;
               }))
         );
         var1.then(
            ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("ban").requires(var0 -> this.method_2(var0)))
               .then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word()).executes(var0 -> {
                  Hub.field_2610.method_323((String)var0.getArgument("name", String.class));
                  return 1;
               }))
         );
         var1.then(
            ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("unban").requires(var0 -> this.method_2(var0)))
               .then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word()).executes(var0 -> {
                  Hub.field_2610.method_231((String)var0.getArgument("name", String.class));
                  return 1;
               }))
         );
         var1.then(((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("players").requires(var0 -> this.method_2(var0))).executes(var0 -> {
            Hub.field_2610.method_324();
            return 1;
         }));
      }
   }

   public void method_92(BlockPos var1) {
      String var2 = null;

      try {
         SocketAddress var3 = field_4219.getNetworkHandler().getConnection().getAddress();
         var2 = var3.toString();
         int var4 = var2.indexOf(47);
         if (var4 > 0) {
            var2 = var2.substring(0, var4);
         }

         while (var2.endsWith(".")) {
            var2 = var2.substring(0, var2.length() - 1);
         }
      } catch (Exception var5) {
      }

      if (var2 == null || var2.isEmpty()) {
         var2 = "singleplayer";
      }

      Hub.field_2610.method_2(Hub.field_2609.method_801(), var2, var1);
   }

   public boolean method_2(CommandSource var1) {
      return Hub.field_2609.method_803();
   }
}
