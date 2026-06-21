package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.Set;
import me.mioclient.Hub;
import net.minecraft.command.CommandSource;

public class Class_0553 extends Class_0618 {
   public Class_0553() {
      super("glint");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(LiteralArgumentBuilder.<CommandSource>literal("clear").executes(var0 -> {
            ((Set)Hub.field_2628.method_141().getValue()).clear();
            return 1;
         })))
         .then(
            RequiredArgumentBuilder.<CommandSource, String>argument("item", StringArgumentType.string())
               .suggests((var0, var1x) -> CommandSource.suggestMatching(Hub.field_2628.method_141().method_186(), var1x))
               .executes(var0 -> {
                  String var1x = (String)var0.getArgument("item", String.class);

                  try {
                     Hub.field_2628.method_141().method_78(var1x);
                  } catch (Exception var3) {
                  }

                  return 1;
               })
         );
   }
}
