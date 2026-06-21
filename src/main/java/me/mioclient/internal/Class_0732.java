package me.mioclient.internal;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0732 extends Command {
   public Class_0732() {
      super("pitch");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, Float>argument("pitch", FloatArgumentType.floatArg((float)(-Constants.field_685), (float)Constants.field_685))
            .executes(
               var0 -> {
                  field_4219.player.setPitch((Float)var0.getArgument("pitch", Float.class));
                  ChatUtil.method_2(
                     Text.literal(
                        new TextBuilder().method_2(String.valueOf(var0.getArgument("pitch", Float.class))).method_9("Player's pitch has been set to \u0001.")
                     ),
                     ChatUtil.method_38(-1)
                  );
                  return 1;
               }
            )
      );
   }
}
