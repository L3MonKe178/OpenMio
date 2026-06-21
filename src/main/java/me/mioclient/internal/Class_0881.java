package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.api.Nameable;
import me.mioclient.enum_.Class_0304;
import me.mioclient.enum_.Class_0911;
import me.mioclient.enum_.Priority;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;

public final class Class_0881 extends Command {
   public final Class_0304 field_2790;

   public Class_0881(Class_0304 var1) {
      super(var1.name().toLowerCase());
      this.field_2790 = var1;
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("add").then(RequiredArgumentBuilder.<CommandSource, String>argument("name", new Class_0246()).executes(var1x -> {
         String var2 = (String)var1x.getArgument("name", String.class);
         if (Hub.field_2603.method_253(var2) == this.field_2790) {
            ChatUtil.method_2(this.method_5(var2).append(this.method_460(" is already in your %s list")), ChatUtil.method_38(-1));
         } else {
            if (this.field_2790 == Class_0304.ENEMY) {
               Hub.field_2603.method_175(var2);
            } else {
               Hub.field_2603.method_632(var2);
            }

            ChatUtil.method_2(this.method_5(var2).append(this.method_460(" has been added to your %s list")), ChatUtil.method_38(-1));
         }

         return 1;
      })));
      var1.then(
         Command.method_2("remove", "delete", "del")
            .then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word()).suggests(this::method_7).executes(var1x -> {
               String var2 = (String)var1x.getArgument("name", String.class);
               if (Hub.field_2603.method_253(var2) == this.field_2790) {
                  Hub.field_2603.method_869(var2);
                  ChatUtil.method_2(this.method_5(var2).append(this.method_460(" has been removed from your %s list")), ChatUtil.method_38(-1));
               } else {
                  ChatUtil.method_2(this.method_5(var2).append(this.method_460(" is not your %s")), ChatUtil.method_38(-1));
               }

               return 1;
            }))
      );
      var1.then(
         LiteralArgumentBuilder.<CommandSource>literal("list")
            .executes(
               var1x -> {
                  List var2 = ((List<me.mioclient.record.Class_0210>)Hub.field_2603.getRegistry())
                     .stream()
                     .filter(var1xx -> var1xx.method_242() == this.field_2790)
                     .map(var0 -> Text.of(var0.getName()))
                     .toList();
                  ChatUtil.method_2(Text.literal(this.method_460("%S list: ")).append(Texts.join(var2, Text.of(", "))), ChatUtil.method_38(-1));
                  return 1;
               }
            )
      );
      var1.then(
         LiteralArgumentBuilder.<CommandSource>literal("sync")
            .then(
               RequiredArgumentBuilder.<CommandSource, Class_0911>argument("importer", new Class_0699<>(Class_0911.class, "importer"))
                  .executes(
                     var0 -> {
                        Class_0911 var1x = (Class_0911)var0.getArgument("importer", Class_0911.class);

                        List<String> var2;
                        try {
                           var2 = var1x.method_201();
                        } catch (Exception var6) {
                           if (var6 instanceof FileNotFoundException) {
                              ChatUtil.method_2(
                                 Text.literal(String.format("Couldn't find %s friends file", var1x.method_827()))
                                    .styled(var0x -> var0x.withColor(Formatting.RED)),
                                 ChatUtil.method_38(-1),
                                 Priority.LOW
                              );
                           } else {
                              var6.printStackTrace();
                              ChatUtil.method_2(
                                 Text.literal(String.format("Couldn't import friends from %s due to an unknown error, check your log file", var1x.method_827()))
                                    .styled(var0x -> var0x.withColor(Formatting.RED)),
                                 ChatUtil.method_38(-1),
                                 Priority.LOW
                              );
                           }

                           return 1;
                        }

                        int var3 = 0;

                        for (String var5 : var2) {
                           if (!Hub.field_2603.method_1009(var5)) {
                              Hub.field_2603.method_632(var5);
                              var3++;
                           }
                        }

                        ChatUtil.method_2(Text.literal(String.format("Added %d new friends from %s", var3, var1x.method_827())), ChatUtil.method_38(-1));
                        return 1;
                     }
                  )
            )
      );
   }

   public CompletableFuture<Suggestions> method_7(CommandContext<CommandSource> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(
         ((List<me.mioclient.record.Class_0210>)Hub.field_2603.getRegistry()).stream().filter(var1x -> var1x.method_242() == this.field_2790).map(Nameable::getName), var2
      );
   }

   public String method_460(String var1) {
      return var1.replace("%s", this.field_2790.method_349()).replace("%S", FontRenderer.method_3(this.field_2790.method_349()));
   }
}
