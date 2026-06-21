package me.mioclient.internal;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import me.mioclient.api.Class_1309;

public final class Class_0130 implements Class_1309 {
   public Class_0130() {
      super();
   }

   public static HttpResponse<String> method_2(HttpRequest var0) {
      return method_2(var0, BodyHandlers.ofString());
   }

   public static <T> HttpResponse<T> method_2(HttpRequest var0, BodyHandler<T> var1) {
      HttpClient var2 = HttpClient.newHttpClient();

      try {
         return var2.send(var0, var1);
      } catch (Throwable var4) {
         throw new RuntimeException(var4);
      }
   }

   public static CompletableFuture<HttpResponse<String>> method_9(HttpRequest var0) {
      return method_9(var0, BodyHandlers.ofString());
   }

   public static <T> CompletableFuture<HttpResponse<T>> method_9(HttpRequest var0, BodyHandler<T> var1) {
      HttpClient var2 = HttpClient.newHttpClient();
      return CompletableFuture.supplyAsync(() -> {
         try {
            return var2.send(var0, var1);
         } catch (Throwable var4) {
            throw new RuntimeException(var4);
         }
      }, field_4221);
   }

   public static Builder method_92(String var0) {
      return method_154(var0).GET();
   }

   public static Builder method_2(String var0, BodyPublisher var1) {
      return method_154(var0).POST(var1);
   }

   public static Builder method_154(String var0) {
      return HttpRequest.newBuilder().uri(URI.create(var0));
   }
}
