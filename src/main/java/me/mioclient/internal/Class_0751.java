package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.net.URL;
import me.mioclient.Hub;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0751 extends Command {
   public Class_0751() {
      super("webhook");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(LiteralArgumentBuilder.<CommandSource>literal("get").executes(var1x -> {
         String var2 = Hub.field_2621.method_695();
         if (var2 != null && !var2.isEmpty()) {
            ChatUtil.method_2(Text.literal("Your webhook URL is ").append(this.method_5(var2)), ChatUtil.method_38(-1035361));
         } else {
            ChatUtil.method_2(Text.literal("You don't have a webhook URL set"), ChatUtil.method_38(-1035361));
         }

         return 1;
      }))).then(LiteralArgumentBuilder.<CommandSource>literal("set").then(RequiredArgumentBuilder.<CommandSource, String>argument("url", StringArgumentType.greedyString()).executes(var1x -> {
         String var2 = (String)var1x.getArgument("url", String.class);
         if (!var2.startsWith("http://") && !var2.startsWith("https://")) {
            var2 = new TextBuilder().method_2((Object)var2).method_9("https://\u0001");
         }

         try {
            new URL(var2);
            Hub.field_2621.method_138(var2);
            ChatUtil.method_2(Text.literal("Your webhook URL has been updated to ").append(this.method_5(var2)), ChatUtil.method_38(-1035362));
         } catch (Exception var4) {
            ChatUtil.method_2(Text.literal("The provided webhook URL is invalid"), ChatUtil.method_38(-1035362));
         }

         return 1;
      })))).then(LiteralArgumentBuilder.<CommandSource>literal("clear").executes(var0 -> {
         Hub.field_2621.method_138("");
         ChatUtil.method_2(Text.literal("Your webhook URL has been cleared out"), ChatUtil.method_38(-1035363));
         return 1;
      }));
   }
}
