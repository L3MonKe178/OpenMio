package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.module.misc.ChatFilterModule;
import me.mioclient.record.Class_1127;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0093 extends Class_0618 {
   public static ChatFilterModule field_302 = Hub.field_2595.method_78(ChatFilterModule.class);

   public Class_0093() {
      super("chatfilter");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(
               LiteralArgumentBuilder.<CommandSource>literal("add")
                  .then(
                     RequiredArgumentBuilder.<CommandSource, String>argument("id", StringArgumentType.string())
                        .then(RequiredArgumentBuilder.<CommandSource, String>argument("filter", StringArgumentType.string()).executes(var0 -> {
                           String var1x = (String)var0.getArgument("id", String.class);
                           String var2 = (String)var0.getArgument("filter", String.class);
                           if (Hub.field_2627.method_334(var1x)) {
                              Class_1245.method_2(Text.of("Chat filter \"%s\" already exists.".formatted(var1x)), Class_1245.method_38(-3469856), Priority.MID);
                           } else {
                              Hub.field_2627.method_38(var1x, var2);
                              Class_1245.method_2(Text.of("Chat filter \"%s\" has been added.".formatted(var1x)), Class_1245.method_38(-3469857));
                           }

                           return 1;
                        }))
                  )
            ))
            .then(LiteralArgumentBuilder.<CommandSource>literal("remove").then(RequiredArgumentBuilder.<CommandSource, Class_1127>argument("id", new Class_0882()).executes(var0 -> {
               Class_1127 var1x = (Class_1127)var0.getArgument("id", Class_1127.class);
               Hub.field_2627.method_2(var1x);
               Class_1245.method_2(Text.of("Chat filter \"%s\" has been removed.".formatted(var1x.method_1007())), Class_1245.method_38(-3469858));
               return 1;
            }))))
         .then(LiteralArgumentBuilder.<CommandSource>literal("list").executes(var0 -> {
            Class_1245.method_2(Text.of("%d chat filter(s)".formatted(Hub.field_2627.method_336())), Class_1245.method_38(-3569855));
            int var1x = -3469852;

            for (String var4 : Hub.field_2627.method_337()) {
               Class_1245.method_2(Text.of(var4), Class_1245.method_38(++var1x));
            }

            return 1;
         }));
   }
}
