package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import me.mioclient.Hub;
import me.mioclient.deobf.Named;
import net.minecraft.command.CommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public abstract class Class_0618 extends Named {
   public String[] field_1958 = new String[0];

   public Class_0618(String var1) {
      super(var1);
   }

   public static RequiredArgumentBuilder<CommandSource, String> method_2(String... var0) {
      return RequiredArgumentBuilder.argument(var0[0], new Class_0611(var0));
   }

   public static CompletableFuture<Suggestions> method_2(SuggestionsBuilder var0, String... var1) {
      for (String var5 : var1) {
         var0.suggest(var5);
      }

      return var0.buildFuture();
   }

   public void method_2(Runnable var1) {
      Hub.field_2619.method_2(var1, 0);
   }

   public MutableText method_5(String var1) {
      return Text.empty().append(Class_1245.method_5(var1));
   }

   public void method_9(String... var1) {
      this.field_1958 = var1;
   }

   public String[] getAliases() {
      return this.field_1958;
   }

   public abstract void exec(LiteralArgumentBuilder<CommandSource> var1);

   public void method_2(Class_0618 var1, LiteralArgumentBuilder<CommandSource> var2) {
      LiteralArgumentBuilder var3 = LiteralArgumentBuilder.<CommandSource>literal(var1.getName());
      var1.exec(var3);
      var2.then(var3);
   }
}
