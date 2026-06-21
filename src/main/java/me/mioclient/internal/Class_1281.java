package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.Hub;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_1281 extends Class_0618 {
   public Class_1281() {
      super("ignore");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            LiteralArgumentBuilder.<CommandSource>literal("add").then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word()).executes(var0 -> {
               String var1x = ((String)var0.getArgument("name", String.class)).toLowerCase();
               if (Hub.field_2610.method_331().contains(var1x)) {
                  Class_1245.method_2(
                     Text.literal(new Class_1303().method_2((Object)var1x).method_9("You are already ignoring \u0001")), Class_1245.method_38(-1)
                  );
               } else {
                  Hub.field_2610.method_331().add(var1x);
                  Class_1245.method_2(
                     Text.literal(new Class_1303().method_2((Object)var1x).method_9("Player \u0001 has been ignored")), Class_1245.method_38(-1)
                  );
               }

               return 1;
            }))
         ))
         .then(
            LiteralArgumentBuilder.<CommandSource>literal("remove")
               .then(
                  RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word())
                     .suggests((var0, var1x) -> CommandSource.suggestMatching(Hub.field_2610.method_331(), var1x))
                     .executes(var0 -> {
                        String var1x = ((String)var0.getArgument("name", String.class)).toLowerCase();
                        if (Hub.field_2610.method_331().contains(var1x)) {
                           Class_1245.method_2(
                              Text.literal(new Class_1303().method_2((Object)var1x).method_9("\u0001 is no longer ignored")), Class_1245.method_38(-1)
                           );
                           Hub.field_2610.method_331().remove(var1x);
                        } else {
                           Class_1245.method_2(
                              Text.literal(new Class_1303().method_2((Object)var1x).method_9("You are not ignoring \u0001")), Class_1245.method_38(-1)
                           );
                        }

                        return 1;
                     })
               )
         );
   }
}
