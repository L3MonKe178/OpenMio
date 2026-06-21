package me.mioclient.internal;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.command.IBaritoneChatControl;
import baritone.api.event.events.ChatEvent;
import baritone.api.event.events.TabCompleteEvent;
import baritone.api.event.listener.IEventBus;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.Hub;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;

public class Class_1321 extends Command {
   public static final String field_4275 = "#";

   public Class_1321() {
      super("baritone");
      this.method_9("b");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, String>argument("command", StringArgumentType.greedyString())
            .suggests(
               (var1x, var2) -> {
                  IBaritone var3 = BaritoneAPI.getProvider().getPrimaryBaritone();
                  String var4 = "#";

                  try {
                     var4 = new TextBuilder().method_2(var2.getRemaining()).method_2((Object)var4).method_9("");
                  } catch (Exception var11) {
                  }

                  TabCompleteEvent var5 = new TabCompleteEvent();
                  this.method_16(() -> {});
                  return var2.buildFuture();
               }
            )
            .executes(var1x -> {
               IBaritone var2 = BaritoneAPI.getProvider().getPrimaryBaritone();
               String var3 = (String)var1x.getArgument("command", String.class);
               if ("goto".equalsIgnoreCase(var3)) {
                  Hub.field_2630.method_154(field_4219.gameRenderer.getCamera().getBlockPos());
                  return 1;
               } else {
                  if ("goto ping".equalsIgnoreCase(var3)) {
                     BlockPos var4 = this.method_1172();
                     if (var4 != null) {
                        Hub.field_2630.method_154(var4);
                        return 1;
                     }
                  } else if ("elytra ping".equalsIgnoreCase(var3)) {
                     BlockPos var5 = this.method_1172();
                     if (var5 != null) {
                        Hub.field_2630.method_114(var5.withY(field_4219.player.getBlockY()));
                        return 1;
                     }
                  }

                  IEventBus var10000 = var2.getGameEventHandler();
                  // FORCE_COMMAND_PREFIX & onSendChatMessage not available in stub
                  return 1;
               }
            })
      );
   }

   public void method_16(Runnable var1) {
      String var2 = (String)BaritoneAPI.getSettings().prefix.value;
      BaritoneAPI.getSettings().prefix.value = "#";
      var1.run();
      BaritoneAPI.getSettings().prefix.value = var2;
   }

   public BlockPos method_1172() {
      synchronized (Hub.field_2610.method_328()) {
         for (Class_1257 var3 : Hub.field_2610.method_328()) {
            if (var3.method_1098() && !var3.method_1095().equalsIgnoreCase(field_4219.player.getName().getString())) {
               float var4 = (float)field_4219.player.getEyePos().distanceTo(var3.method_111().toCenterPos());
               if (!(var4 >= Float.intBitsToFloat(1176255488))) {
                  return var3.method_111();
               }
            }
         }

         return null;
      }
   }
}
