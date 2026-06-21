package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.awt.Desktop;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.record.Class_1107;
import net.minecraft.command.CommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class Class_0343 extends Class_0618 {
   public final String field_1143 = "https://api.mojang.com/users/profiles/minecraft/";
   public final String field_1144 = "https://laby.net/api/user/%s/get-names";
   public CompletableFuture<List<Class_1107>> field_1145;

   public Class_0343() {
      super("namehistory");   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(RequiredArgumentBuilder.<CommandSource, String>argument("name", new Class_0246()).executes(var1x -> {
         if (this.field_1145 != null && !this.field_1145.isDone() && !this.field_1145.isCompletedExceptionally()) {
            return 1;
         } else {
            String var2 = (String)var1x.getArgument("name", String.class);
            this.field_1145 = this.method_84(var2);
            this.field_1145.thenAccept(var1xx -> Hub.field_2619.method_2(() -> this.method_4((List<Class_1107>)var1xx), 0));
            this.field_1145.exceptionally(var1xx -> {
               Hub.field_2619.method_2(() -> this.method_2(var1xx), 0);
               return null;
            });
            Class_1245.method_2(Text.literal("Loading name history for ").append(this.method_5(var2)), Class_1245.method_38(-3));
            return 1;
         }
      }));
      var1.then(
         LiteralArgumentBuilder.<CommandSource>literal("open")
            .then(
               RequiredArgumentBuilder.<CommandSource, String>argument("name", new Class_0246())
                  .executes(
                     var0 -> {
                        try {
                           Desktop.getDesktop()
                              .browse(
                                 new URL(
                                       new Class_1303().method_2((String)var0.getArgument("name", String.class)).method_9("https://namemc.com/profile/\u0001")
                                    )
                                    .toURI()
                              );
                        } catch (Throwable var2) {
                        }

                        return 1;
                     }
                  )
            )
      );
   }

   public CompletableFuture<List<Class_1107>> method_84(String var1) {
      return CompletableFuture.supplyAsync(
         () -> {
            String var2 = Class_0130.method_2(
                  Class_0130.method_92(new Class_1303().method_2((Object)var1).method_9("https://api.mojang.com/users/profiles/minecraft/\u0001")).build()
               )
               .body();
            JsonElement var3 = JsonParser.parseString(var2);
            if (var3.isJsonObject() && var3.getAsJsonObject().has("id")) {
               String var4 = var3.getAsJsonObject().get("id").getAsString();
               return this.method_294(Class_0130.method_2(Class_0130.method_92("https://laby.net/api/user/%s/get-names".formatted(var4)).build()).body());
            } else {
               throw new RuntimeException("Player not found");
            }
         },
         field_4221
      );
   }

   public List<Class_1107> method_294(String var1) {
      JsonElement var2 = JsonParser.parseString(var1);
      return !var2.isJsonArray()
         ? null
         : var2.getAsJsonArray().asList().stream().map(var0 -> (Class_1107)field_4218.fromJson(var0, Class_1107.class)).collect(Collectors.toList());
   }

   public void method_4(List<Class_1107> var1) {
      if (var1 != null) {
         try {
            MutableText var2 = Text.empty();
            var2.append(this.method_5(((Class_1107)var1.get(var1.size() - 1)).method_978()));
            var2.append("'s Name History: \n");
            StringBuilder var3 = new StringBuilder();

            for (int var4 = 0; var4 < var1.size(); var4++) {
               Class_1107 var5 = (Class_1107)var1.get(var4);
               var3.append("%d. %s (%s)".formatted(var4 + 1, var5.method_978(), var5.method_977()));
               if (var4 != var1.size() - 1) {
                  var3.append('\n');
               }
            }

            var2.append(var3.toString());
            Class_1245.method_2(var2, Class_1245.method_38(-3));
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }
   }

   public void method_2(Throwable var1) {
      Class_1245.method_2(Text.literal(var1.getCause().getMessage()), Class_1245.method_38(-3), Priority.HIGH);
   }
}
