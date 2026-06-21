package me.mioclient.internal;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.module.Module;
import net.minecraft.command.CommandSource;

public final class Class_0480 extends Command {
   public Class_0480() {
      super("toggle");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, Module>argument("module", new Class_0484())
               .then(RequiredArgumentBuilder.<CommandSource, Boolean>argument("state", BoolArgumentType.bool()).executes(var0 -> {
                  Module var1x = (Module)var0.getArgument("module", Module.class);
                  var1x.method_38((Boolean)var0.getArgument("state", Boolean.class));
                  return 1;
               }))
            .executes(var0 -> {
               Module var1x = (Module)var0.getArgument("module", Module.class);
               var1x.method_68();
               return 1;
            })
      );
   }
}
