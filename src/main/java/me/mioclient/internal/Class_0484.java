package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.deobf.Named;
import me.mioclient.module.Module;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0484 implements ArgumentType<Module> {
   public Class_0484() {
      super();
   }

   public Module parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.readString();
      Optional var3 = Hub.field_2599.method_2(var1x -> {
         for (String var5 : var1x.getAliases()) {
            if (var5.equalsIgnoreCase(var2)) {
               return true;
            }
         }

         return false;
      });
      if (var3.isEmpty()) {
         throw new DynamicCommandExceptionType(var0 -> Text.of(new Class_1303().method_2(String.valueOf(var0)).method_9("Module not found \u0001")))
            .create(var2);
      } else {
         return (Module)var3.get();
      }
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      return CommandSource.suggestMatching(((List<Module>)Hub.field_2599.getRegistry()).stream().map(Module::getName), var2);
   }
}
