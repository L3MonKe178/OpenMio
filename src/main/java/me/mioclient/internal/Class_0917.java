package me.mioclient.internal;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0917 extends Command {
   public Class_0917() {
      super("yaw");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, Float>argument("yaw", FloatArgumentType.floatArg(Float.intBitsToFloat(-1020002304), Float.intBitsToFloat(1127481344)))
            .executes(
               var0 -> {
                  field_4219.player.setYaw((Float)var0.getArgument("yaw", Float.class));
                  ChatUtil.method_2(
                     Text.literal(
                        new TextBuilder().method_2(String.valueOf(var0.getArgument("yaw", Float.class))).method_9("Player's yaw has been set to \u0001.")
                     ),
                     ChatUtil.method_38(-1)
                  );
                  return 1;
               }
            )
      );
   }
}
