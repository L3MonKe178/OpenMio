package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiPredicate;
import me.mioclient.api.Class_1309;
import net.minecraft.command.CommandSource;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Class_0624<T> implements ArgumentType<T>, Class_1309 {
   public final BiPredicate<CommandContext<?>, T> field_1990;
   public final Registry<T> field_1991;

   public Class_0624(Registry<T> var1, BiPredicate<CommandContext<?>, T> var2) {
      super();
      this.field_1990 = var2;
      this.field_1991 = var1;
   }

   public T parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();
      return (T)this.field_1991
         .getOrEmpty(Identifier.of(var2))
         .orElseThrow(
            () -> new DynamicCommandExceptionType(var0x -> Text.of(new Class_1303().method_2(String.valueOf(var0x)).method_9("Element not found \u0001")))
                  .create(var2)
         );
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(
         this.field_1991.getIds().stream().filter(var2x -> this.test(var1, var2x)).map(Identifier::toShortTranslationKey), var2
      );
   }

   public boolean test(CommandContext<?> var1, Identifier var2) {
      return this.field_1990.test(var1, (T)this.field_1991.get(var2));
   }

   public static <T> Class_0624<T> registry(Registry<T> var0) {
      return new Class_0624<>(var0, (var0x, var1) -> true);
   }

   public static <T> Class_0624<T> registry(Registry<T> var0, BiPredicate<CommandContext<?>, T> var1) {
      return new Class_0624<>(var0, var1);
   }
}
