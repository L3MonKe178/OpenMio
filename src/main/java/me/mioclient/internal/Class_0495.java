package me.mioclient.internal;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.module.Module;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0495 extends Command {
   public Class_0495() {
      super("drawn");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            RequiredArgumentBuilder.<CommandSource, Module>argument("module", new Class_0484())
               .executes(
                  var0 -> {
                     Module var1x = (Module)var0.getArgument("module", Module.class);
                     var1x.setDrawn(!var1x.isDrawn());
                     ChatUtil.method_2(
                        Text.literal(
                           new TextBuilder().method_2(var1x.isDrawn()).method_2(var1x.getName()).method_9("Drawing for \u0001 has been set to \u0001.")
                        ),
                        ChatUtil.method_38(-1)
                     );
                     return 1;
                  }
               )
         ))
         .then(
            LiteralArgumentBuilder.<CommandSource>literal("all")
               .then(
                  RequiredArgumentBuilder.<CommandSource, Boolean>argument("state", BoolArgumentType.bool())
                     .executes(
                        var0 -> {
                           boolean var1x = (Boolean)var0.getArgument("state", Boolean.class);

                           for (Module var3 : (List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()) {
                              var3.setDrawn(var1x);
                           }

                           ChatUtil.method_2(
                              Text.literal(new TextBuilder().method_2(var1x).method_9("Drawing for all modules has been set to \u0001.")),
                              ChatUtil.method_38(-1)
                           );
                           return 1;
                        }
                     )
               )
         );
   }
}
