package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;

public final class Class_0460 extends Command {
   public Class_0460() {
      super("say");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(RequiredArgumentBuilder.<CommandSource, String>argument("message", StringArgumentType.greedyString()).executes(var0 -> {
         String var1x = (String)var0.getArgument("message", String.class);
         ChatUtil.method_425(var1x);
         return 1;
      }));
   }
}
