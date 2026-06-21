package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0836;
import me.mioclient.record.Class_0371;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0897 implements ArgumentType<String> {
   public Class_0897() {
      super();
   }

   public static Class_0371 getPreset(CommandContext<?> var0, String var1, String var2) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      Class_0836 var3 = (Class_0836)var0.getArgument(var1, Class_0836.class);
      String var4 = (String)var0.getArgument(var2, String.class);
      Optional var5 = Hub.field_2597.method_2(var3).method_2(var1x -> var1x.getName().equalsIgnoreCase(var4));
      if (var5.isEmpty()) {
         throw new DynamicCommandExceptionType(var0x -> Text.of(new TextBuilder().method_2(String.valueOf(var0x)).method_9("Preset not found \u0001")))
            .create(var4);
      } else {
         return (Class_0371)var5.get();
      }
   }

   public String parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      return var1.readString();
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CompletableFuture.supplyAsync(() -> {
         Class_0852 var2x = Hub.field_2597.method_2((Class_0836)var1.getArgument("category", Class_0836.class));

         try {
            var2x.method_56();
         } catch (Throwable var6) {
         }

         Stream var3 = ((List<Class_0371>)var2x.getRegistry()).stream().map(Class_0371::getName);

         try {
            return (Suggestions)CommandSource.suggestMatching(var3, var2).get();
         } catch (Exception var5) {
            return null;
         }
      }, MioAPI.field_4221);
   }
}
