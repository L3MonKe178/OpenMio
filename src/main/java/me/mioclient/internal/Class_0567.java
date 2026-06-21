package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.List;
import me.mioclient.Hub;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0567 extends Class_0618 {
   public Class_0567() {
      super("kit");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(Class_0618.method_2("save", "add").then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string()).executes(var0 -> {
         String var1x = (String)var0.getArgument("name", String.class);
         Hub.field_2625.method_468(var1x);
         Class_1245.method_2(Text.literal("Kit %s has been saved".formatted(var1x)), Class_1245.method_38(-1));
         return 1;
      })));
      var1.then(Class_0618.method_2("remove", "delete", "del").then(RequiredArgumentBuilder.<CommandSource, Class_1009>argument("kit", new Class_1363()).executes(var0 -> {
         Class_1009 var1x = (Class_1009)var0.getArgument("kit", Class_1009.class);
         ((List<Class_1009>)Hub.field_2625.getRegistry()).removeIf(var1xx -> var1x.getName().equalsIgnoreCase(var1xx.getName()));
         Class_1245.method_2(Text.literal("Kit %s has been removed".formatted(var1x.getName())), Class_1245.method_38(-1));
         return 1;
      })));
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("load").then(RequiredArgumentBuilder.<CommandSource, Class_1009>argument("kit", new Class_1363()).executes(var0 -> {
         Class_1009 var1x = (Class_1009)var0.getArgument("kit", Class_1009.class);
         Hub.field_2625.method_27(var1x.getName());
         Class_1245.method_2(Text.literal("Kit %s has been loaded".formatted(var1x.getName())), Class_1245.method_38(-1));
         return 1;
      })));
   }
}
