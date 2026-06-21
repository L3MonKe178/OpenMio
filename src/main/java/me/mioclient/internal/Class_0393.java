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

public class Class_0393 extends Class_0618 {
   public Class_0393() {
      super("elytra");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string())
            .suggests((CommandContext<CommandSource> var0, SuggestionsBuilder var1x) -> Class_1207.method_2(var0, var1x, Class_1207.method_1054()))
            .executes(var1x -> {
               Vec3d var2 = Class_1207.method_107(var1x);
               if (var2 == null) {
                  return 0;
               } else {
                  IBaritone var3 = BaritoneAPI.getProvider().getPrimaryBaritone();
                  GoalXZ var4 = new GoalXZ((int)var2.getX(), (int)var2.getZ());
                  var3.getCustomGoalProcess().setGoal(var4);
                  var3.getElytraProcess().pathTo(net.minecraft.util.math.BlockPos.ofFloored(var2));
                  if (field_4219.player.isFallFlying()) {
                     return 1;
                  } else {
                     if (field_4219.player.isOnGround()) {
                        field_4219.player.addVelocity(0.0, Double.longBitsToDouble(4601237667291888353L), 0.0);
                     }

                     this.method_2(() -> {
                        Class_1261.method_1099();
                        field_4219.player.startFallFlying();
                     });
                     return 1;
                  }
               }
            })
      );
   }
}
