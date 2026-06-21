package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class Class_0657 extends Command {
   public Class_0657() {
      super("prefix");
      field_4220.method_14(this);
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            RequiredArgumentBuilder.<CommandSource, String>argument("prefix", StringArgumentType.greedyString())
               .executes(
                  var0 -> {
                     String var1x = (String)var0.getArgument("prefix", String.class);
                     CommandManager.method_270(var1x);
                     ChatUtil.method_2(
                        Text.literal(
                           new TextBuilder().method_2(CommandManager.method_927()).method_2(String.valueOf(Formatting.GRAY)).method_9("\u0001Prefix set to \u0001")
                        ),
                        ChatUtil.method_38(1)
                     );
                     return 1;
                  }
               )
         ))
         .executes(
            var0 -> {
               ChatUtil.method_2(
                  Text.literal(
                     new TextBuilder()
                        .method_2(CommandManager.method_927())
                        .method_2(String.valueOf(Formatting.GRAY))
                        .method_9("\u0001The current prefix is \u0001")
                  ),
                  ChatUtil.method_38(1)
               );
               return 1;
            }
         );
   }
}
