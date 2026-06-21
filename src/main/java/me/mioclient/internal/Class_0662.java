package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.enum_.Priority;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0662 extends Command {
   public Class_0662() {
      super("print");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            RequiredArgumentBuilder.<CommandSource, String>argument("message", StringArgumentType.greedyString())
               .executes(
                  var0 -> {
                     field_4219.inGameHud
                        .getChatHud()
                        .addMessage(
                           Text.of((String)var0.getArgument("message", String.class)),
                           ChatUtil.method_38((int)(Math.random() * Double.longBitsToDouble(4666723172467343360L))),
                           ChatUtil.field_3910
                        );
                     return 1;
                  }
               )
         ))
         .executes(var0 -> {
            ChatUtil.method_2(Text.literal("Please enter a message to print."), ChatUtil.method_38(-1), Priority.LOW);
            return 1;
         });
   }
}
