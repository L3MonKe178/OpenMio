package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import me.mioclient.enum_.Class_0046;
import me.mioclient.mixin.ducks.DuckInputUtilType;
import me.mioclient.record.Class_0702;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Type;
import net.minecraft.command.CommandSource;

public class Class_1189 implements ArgumentType<Class_0702> {
   public Class_1189() {
      super();
   }

   public Class_0702 parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString().toLowerCase(Locale.ROOT).trim();
      return var2.startsWith("mouse")
         ? new Class_0702(Integer.parseInt(var2.toLowerCase().replace("mouse", "")), Class_0046.TOGGLE, true)
         : new Class_0702(
            InputUtil.fromTranslationKey(new Class_1303().method_2(var2.replace("_", ".")).method_9("key.keyboard.\u0001")).getCode(), Class_0046.TOGGLE, false
         );
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      ((DuckInputUtilType)(Object)Type.KEYSYM).getKeyMap().forEach((var1x, var2x) -> {
         String var3x = var2x.getTranslationKey().replaceAll("key.keyboard.", "").replace(".", "_").toUpperCase();
         if (CommandSource.shouldSuggest(var2.getRemaining().toUpperCase(), var3x)) {
            var2.suggest(var3x);
         }
      });

      for (int var3 = 0; var3 < 5; var3++) {
         String var4 = new Class_1303().method_2(var3).method_9("MOUSE\u0001");
         if (CommandSource.shouldSuggest(var2.getRemaining(), var4)) {
            var2.suggest(var4);
         }
      }

      return var2.buildFuture();
   }
}
