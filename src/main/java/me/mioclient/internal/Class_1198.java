package me.mioclient.internal;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class Class_1198<S> extends LiteralCommandNode<S> {
   public Class_1198(String var1, Command<S> var2, Predicate<S> var3, CommandNode<S> var4, RedirectModifier<S> var5, boolean var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return Suggestions.empty();
   }
}
