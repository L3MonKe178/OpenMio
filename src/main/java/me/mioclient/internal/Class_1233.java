package me.mioclient.internal;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.api.Class_0078;
import me.mioclient.api.Class_1309;
import me.mioclient.module.Module;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.Class0211Setting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.CustomSetting2;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.command.CommandSource;

public class Class_1233 implements ArgumentType<String>, Class_1309 {
   public final String field_3879;
   public final Module field_3880;

   public String parse(StringReader var1) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
      String var2 = var1.getRemaining();
      var1.setCursor(var1.getTotalLength());
      return var2;
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var1, SuggestionsBuilder var2) {
      Setting var3;
      try {
         var3 = Class_1024.getOption(var1, this.field_3880, this.field_3879);
      } catch (Exception var11) {
         return Suggestions.empty();
      }

      if (var3 instanceof BooleanSetting) {
         return Class_1201.bool((BooleanSetting)var3).listSuggestions(var1, var2);
      } else if (var3 instanceof CustomSetting var4) {
         return CommandSource.suggestMatching(
            Arrays.stream((Enum[])((Enum)var4.method_99()).getDeclaringClass().getEnumConstants())
               .filter(var0 -> !Class_0345.method_29((Enum<?>)var0))
               .map(var0 -> var0.name().toLowerCase()),
            var2
         );
      } else if (var3 instanceof CustomSetting3 var5) {
         Number var12 = (Number)var5.getValue();
         if (var2.getRemaining().isBlank()) {
            return var2.suggest(String.valueOf(var5.method_99())).buildFuture();
         } else if (var12 instanceof Float) {
            return FloatArgumentType.floatArg((Float)((Number)var5.method_100()), (Float)((Number)var5.method_101())).listSuggestions(var1, var2);
         } else {
            return var12 instanceof Double
               ? DoubleArgumentType.doubleArg((Double)((Number)var5.method_100()), (Double)((Number)var5.method_101())).listSuggestions(var1, var2)
               : IntegerArgumentType.integer((Integer)((Number)var5.method_100()), (Integer)((Number)var5.method_101())).listSuggestions(var1, var2);
         }
      } else if (var3 instanceof CustomSetting2 var6) {
         Collection var8 = var6.method_186();
         if (var3 instanceof Class_0199 var9) {
            Class_0078 var10 = Class_0078.method_2(var9.method_231());
            if (var10 != Class_0078.field_280) {
               var8.addAll(var10.method_115());
            }
         }

         return CommandSource.suggestMatching(var8, var2);
      } else {
         return var3 instanceof Class0211Setting var7
            ? CommandSource.suggestMatching(
               Hub.field_2606
                  .method_533()
                  .stream()
                  .map(var0 -> new Class_1303().method_2(var0.getName()).method_2(var0.method_243()).method_9("\u0001:\u0001")),
               var2
            )
            : ArgumentType.super.listSuggestions(var1, var2);
      }
   }

   public Class_1233(Module var1, String var2) {
      super();
      this.field_3879 = var2;
      this.field_3880 = var1;
   }

   public static Class_1233 value(Module var0, String var1) {
      return new Class_1233(var0, var1);
   }
}
