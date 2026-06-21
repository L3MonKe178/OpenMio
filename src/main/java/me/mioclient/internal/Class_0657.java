package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class Class_0657 extends Class_0618 {
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
                     Class_1032.method_270(var1x);
                     Class_1245.method_2(
                        Text.literal(
                           new Class_1303().method_2(Class_1032.method_927()).method_2(String.valueOf(Formatting.GRAY)).method_9("\u0001Prefix set to \u0001")
                        ),
                        Class_1245.method_38(1)
                     );
                     return 1;
                  }
               )
         ))
         .executes(
            var0 -> {
               Class_1245.method_2(
                  Text.literal(
                     new Class_1303()
                        .method_2(Class_1032.method_927())
                        .method_2(String.valueOf(Formatting.GRAY))
                        .method_9("\u0001The current prefix is \u0001")
                  ),
                  Class_1245.method_38(1)
               );
               return 1;
            }
         );
   }
}
