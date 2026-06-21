package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import net.minecraft.command.CommandSource;

public class Class_0611 implements ArgumentType<String> {
   public final String[] field_1890;

   public Class_0611(String... var1) {
      super();
      this.field_1890 = var1;
   }

   public String parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();

      for (String var6 : this.field_1890) {
         if (var6.equals(var2)) {
            return var6;
         }
      }

      throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.literalIncorrect().createWithContext(var1, Arrays.toString((Object[])this.field_1890));
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      String var3 = var2.getRemaining();

      for (String var7 : this.field_1890) {
         if (var7.equals(var3)) {
            var2.suggest(var7);
            return var2.buildFuture();
         }
      }

      if (CommandSource.shouldSuggest(var2.getRemaining(), this.field_1890[0])) {
         var2.suggest(this.field_1890[0]);
      }

      return var2.buildFuture();
   }
}
