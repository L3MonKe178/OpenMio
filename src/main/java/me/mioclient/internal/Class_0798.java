package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0836;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0798 implements ArgumentType<Class_0836> {
   public Class_0798() {
      super();
   }

   public static Class_0852 getManager(CommandContext<?> var0, String var1) {
      Class_0836 var2 = (Class_0836)var0.getArgument(var1, Class_0836.class);
      return Hub.field_2597.method_2(var2);
   }

   public Class_0836 parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();
      Class_0836 var3 = Class_0836.method_113(var2);
      if (var3 == null) {
         throw new DynamicCommandExceptionType(var0 -> Text.of(new TextBuilder().method_2(String.valueOf(var0)).method_9("Preset category not found \u0001")))
            .create(var2);
      } else {
         return var3;
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(Arrays.stream(Class_0836.values()).map(var0 -> var0.getName().toLowerCase(Locale.ROOT)), var2);
   }
}
