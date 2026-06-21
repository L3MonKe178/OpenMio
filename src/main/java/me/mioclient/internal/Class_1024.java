package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import me.mioclient.api.Class_1146;
import me.mioclient.api.MioAPI;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_1024 implements ArgumentType<String>, MioAPI {
   public final Module field_3175;

   public Class_1024(Module var1) {
      super();
      this.field_3175 = var1;
   }

   public String parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      return var1.readString();
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      Stream var3 = this.field_3175.getRegistry().stream().filter(var0 -> !var0.method_158()).map(Class_1146::getConfigName);
      return CommandSource.suggestMatching(var3, var2);
   }

   public static Setting<?> getOption(CommandContext<?> var0, Module var1, String var2) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var3 = (String)var0.getArgument(var2, String.class);
      Optional var4 = var1.getRegistry().stream().filter(var1x -> var1x.getConfigName().equalsIgnoreCase(var3)).findFirst();
      if (var4.isEmpty()) {
         throw new DynamicCommandExceptionType(var0x -> Text.literal(new TextBuilder().method_2(String.valueOf(var0x)).method_9("Setting not found \u0001")))
            .create(var3);
      } else {
         return (Setting<?>)var4.get();
      }
   }
}
