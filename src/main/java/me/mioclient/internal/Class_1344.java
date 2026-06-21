package me.mioclient.internal;

import com.google.gson.JsonObject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import net.minecraft.command.CommandSource;

public class Class_1344 extends Command {
   public final ExecutorService field_4336 = Executors.newCachedThreadPool();
   public final HttpClient field_4337 = HttpClient.newHttpClient();
   public static final Pattern field_4338 = Pattern.compile("[a-zA-Z0-9_]{1,16}");

   public Class_1344(String var1) {
      super(var1);
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
   }

   public CompletableFuture<JsonObject> method_29(String var1, String var2) {
      CompletableFuture var3 = new CompletableFuture();
      this.field_4336
         .submit(
            () -> {
               if (var2 != null && !var2.isEmpty()) {
                  int var4 = var2.length();
                  if (var4 < 3 || var4 > 16 || !field_4338.matcher(var2).matches()) {
                     var3.completeExceptionally(new RuntimeException("Invalid username provided."));
                     return;
                  }
               }

               String var12 = var1;
               if (var2 != null && !var2.isEmpty()) {
                  var12 = new TextBuilder().method_2((Object)var1).method_9("\u0001?playerName=");
                  var12 = new TextBuilder().method_2((Object)var2).method_2((Object)var12).method_9("\u0001\u0001");
               }

               boolean var5 = false;

               for (int var6 = 0; var6 < 2; var6++) {
                  try {
                     HttpRequest var7 = HttpRequest.newBuilder()
                        .uri(new URI(new TextBuilder().method_2((Object)var12).method_9("https://api.2b2t.vc/\u0001")))
                        .GET()
                        .header("User-Agent", "MioClient/2.0")
                        .header("Accept", "application/json")
                        .timeout(Duration.ofSeconds(10L))
                        .build();
                     HttpResponse var8 = this.field_4337.send(var7, BodyHandlers.ofString());
                     if (var8.statusCode() != 204) {
                        if (var8.statusCode() == 429) {
                           var3.completeExceptionally(new RuntimeException("You are being rate limited, please try again later."));
                           return;
                        }

                        if (var8.statusCode() != 200) {
                           var3.completeExceptionally(new RuntimeException("Invalid server response code: %d.".formatted(var8.statusCode())));
                           return;
                        }

                        JsonObject var9 = (JsonObject)field_4218.fromJson((String)var8.body(), JsonObject.class);
                        var5 = true;
                        var3.complete(var9);
                        return;
                     }

                     var3.completeExceptionally(new RuntimeException("No data%s.".formatted(var2 != null && !var2.isEmpty() ? " for player" : "")));
                     return;
                  } catch (Exception var11) {
                     try {
                        Thread.sleep(1000L);
                     } catch (Exception var10) {
                     }
                  }
               }

               if (!var5) {
                  var3.completeExceptionally(new RuntimeException("Failed to fetch data."));
               }
            }
         );
      return var3;
   }

   public static String method_105(int var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = var0 / 86400;
      int var3 = var0 / 3600 % 24;
      int var4 = var0 / 60 % 60;
      int var5 = var0 % 60;
      if (var2 > 0) {
         var1.append(var2);
         var1.append(" day%s, ".formatted(method_84(var2)));
      }

      if (var2 > 0 || var3 > 0) {
         var1.append(var3);
         var1.append(" hour%s, ".formatted(method_84(var3)));
      }

      if (var2 > 0 || var3 > 0 || var4 > 0) {
         var1.append(var4);
         var1.append(" minute%s and ".formatted(method_84(var4)));
      }

      var1.append(var5);
      var1.append(" second%s".formatted(method_84(var5)));
      return var1.toString();
   }

   public static String method_84(int var0) {
      return var0 == 1 ? "" : "s";
   }
}
