package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import me.mioclient.setting.BooleanSetting;

public class Class_1201 implements ArgumentType<Boolean> {
   public static final Collection<String> field_3722 = Arrays.asList("true", "false", "toggle");
   public final BooleanSetting field_3723;

   public Class_1201(BooleanSetting var1) {
      super();
      this.field_3723 = var1;
   }

   public static Class_1201 bool(BooleanSetting var0) {
      return new Class_1201(var0);
   }

   public static boolean getBool(CommandContext<?> var0, String var1) {
      return (Boolean)var0.getArgument(var1, Boolean.class);
   }

   public Boolean parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();
      if (var2.equals("0") || var2.equalsIgnoreCase("false")) {
         return false;
      } else if (var2.equals("1") || var2.equalsIgnoreCase("true")) {
         return true;
      } else if (var2.equalsIgnoreCase("toggle")) {
         return !(Boolean)this.field_3723.getValue();
      } else {
         throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidBool().createWithContext(var1, var2);
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      if ("true".startsWith(var2.getRemainingLowerCase())) {
         var2.suggest("true");
      }

      if ("false".startsWith(var2.getRemainingLowerCase())) {
         var2.suggest("false");
      }

      if ("toggle".startsWith(var2.getRemainingLowerCase())) {
         var2.suggest("toggle");
      }

      return var2.buildFuture();
   }

   public Collection<String> getExamples() {
      return field_3722;
   }
}
