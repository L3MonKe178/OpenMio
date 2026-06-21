package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.record.Class_1127;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0882 implements ArgumentType<Class_1127> {
   public Class_0882() {
      super();
   }

   public Class_1127 parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();
      Optional var3 = Hub.field_2627.method_310(var2);
      if (var3.isEmpty()) {
         throw new DynamicCommandExceptionType(var0 -> Text.of(new TextBuilder().method_2(String.valueOf(var0)).method_9("Filter not found \u0001")))
            .create(var2);
      } else {
         return (Class_1127)var3.get();
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(Hub.field_2627.method_335(), var2);
   }
}
