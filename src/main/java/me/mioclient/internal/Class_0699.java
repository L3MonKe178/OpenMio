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
import me.mioclient.api.Nameable;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0699<T extends Enum<T>> implements ArgumentType<T> {
   public final Class<T> field_2223;
   public final String field_2224;

   public Class_0699(Class<T> var1) {
      this(var1, "Enum");
   }

   public Class_0699(Class<T> var1, String var2) {
      super();
      this.field_2223 = var1;
      this.field_2224 = var2;
   }

   public T parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();

      for (Enum var6 : (Enum[])this.field_2223.getEnumConstants()) {
         if (this.name((T)var6).equalsIgnoreCase(var2)) {
            return (T)var6;
         }
      }

      throw new DynamicCommandExceptionType(var1x -> Text.literal(String.format("%s %s doesn't exist", this.field_2224, var1x))).create(var2);
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(Arrays.<T>stream(this.field_2223.getEnumConstants()).map(this::name), var2);
   }

   public String name(T var1) {
      return var1 instanceof Nameable ? ((Nameable)var1).getName() : var1.name().toLowerCase(Locale.ROOT);
   }
}
