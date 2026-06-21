package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import me.mioclient.api.Class_1309;
import net.minecraft.command.CommandSource;

public class Class_0246 implements ArgumentType<String>, Class_1309 {
   public static final List<String> field_692 = List.of("cat", "fit", "asphyxia1337");

   public Class_0246() {
      super();
   }

   public String parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      return var1.readString();
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(field_4219.player.networkHandler.getPlayerList().stream().map(var0 -> var0.getProfile().getName()), var2);
   }

   public Collection<String> getExamples() {
      return field_692;
   }
}
