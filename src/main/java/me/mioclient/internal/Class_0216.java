package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1136;
import me.mioclient.module.Module;
import net.minecraft.command.CommandSource;

public final class Class_0216 extends Class_0618 {
   public Class_0216() {
      super("alias");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(this.method_2(Class_1136.field_3518))).then(this.method_2(Class_1136.field_3519));
   }

   public ArgumentBuilder<CommandSource, ?> method_2(Class_1136 var1) {
      return ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal(var1.name().toLowerCase())
            .then(
               LiteralArgumentBuilder.<CommandSource>literal("set")
                  .then(
                     RequiredArgumentBuilder.<CommandSource, Object>argument("target", (com.mojang.brigadier.arguments.ArgumentType<Object>)(com.mojang.brigadier.arguments.ArgumentType)var1.field_3520)
                        .then(RequiredArgumentBuilder.<CommandSource, String>argument("alias", StringArgumentType.string()).executes(var1x -> {
                           String var2 = (String)var1x.getArgument("alias", String.class);
                           if (var1 == Class_1136.field_3518) {
                              Hub.field_2626.method_2((Module)var1x.getArgument("target", Module.class), var2);
                           } else {
                              Hub.field_2626.method_4((String)var1x.getArgument("target", String.class), var2);
                           }

                           return 1;
                        }))
                  )
            ))
         .then(LiteralArgumentBuilder.<CommandSource>literal("remove").then(RequiredArgumentBuilder.<CommandSource, Object>argument("target", (com.mojang.brigadier.arguments.ArgumentType<Object>)(com.mojang.brigadier.arguments.ArgumentType)var1.field_3520).executes(var1x -> {
            if (var1 == Class_1136.field_3518) {
               Hub.field_2626.method_5((Module)var1x.getArgument("target", Module.class));
            } else {
               Hub.field_2626.method_6((String)var1x.getArgument("target", String.class));
            }

            return 1;
         })));
   }
}
