package me.mioclient.internal;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.pathing.goals.GoalXZ;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.Vec3d;

public class Class_0412 extends Class_0618 {
   public Class_0412() {
      super("walk");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string())
            .suggests((CommandContext<CommandSource> var0, SuggestionsBuilder var1x) -> Class_1207.method_2(var0, var1x, Class_1207.method_1054()))
            .executes(var0 -> {
               Vec3d var1x = Class_1207.method_107(var0);
               if (var1x == null) {
                  return 0;
               } else {
                  IBaritone var2 = BaritoneAPI.getProvider().getPrimaryBaritone();
                  GoalXZ var3 = new GoalXZ((int)var1x.getX(), (int)var1x.getZ());
                  var2.getCustomGoalProcess().setGoalAndPath(var3);
                  return 1;
               }
            })
      );
   }
}
