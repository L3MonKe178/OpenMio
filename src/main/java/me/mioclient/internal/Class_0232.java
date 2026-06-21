package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class Class_0232<T> implements ArgumentType<Collection<T>> {
   public final ArgumentType<T> field_643;

   public Class_0232(ArgumentType<T> var1) {
      super();
      this.field_643 = var1;
   }

   public Collection<T> parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.getRemaining();
      var1.setCursor(var1.getTotalLength());
      String[] var3 = var2.split(" ");
      ArrayList var4 = new ArrayList();

      for (String var8 : var3) {
         var4.add(this.field_643.parse(new StringReader(var8)));
      }

      return var4;
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      String[] var3 = var2.getRemaining().split(" ");
      int var4 = var2.getStart() + var2.getRemaining().length() - var3[var3.length - 1].length();
      return this.field_643.listSuggestions(var1, new SuggestionsBuilder(var2.getInput(), var4));
   }
}
