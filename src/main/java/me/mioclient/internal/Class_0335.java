package me.mioclient.internal;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;

public final class Class_0335 extends Class_0618 {
   public Class_0335() {
      super("clip");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            LiteralArgumentBuilder.<CommandSource>literal("v")
               .then(
                  RequiredArgumentBuilder.<CommandSource, Float>argument("value", FloatArgumentType.floatArg())
                     .executes(
                        var0 -> {
                           float var1x = Math.clamp(
                              (Float)var0.getArgument("value", Float.class), Float.intBitsToFloat(-874192448), Float.intBitsToFloat(1273291200)
                           );
                           int var2 = (int)Math.ceil((double)Math.abs(var1x / Float.intBitsToFloat(1092616192)));
                           if (var2 > 20) {
                              var2 = 1;
                           }

                           if (field_4219.player.hasVehicle()) {
                              for (int var4 = 0; var4 < var2 - 1; var4++) {
                                 Class_1261.method_2(new VehicleMoveC2SPacket(field_4219.player.getVehicle()));
                              }

                              field_4219.player
                                 .getVehicle()
                                 .setPosition(
                                    field_4219.player.getVehicle().getX(),
                                    field_4219.player.getVehicle().getY() + (double)var1x,
                                    field_4219.player.getVehicle().getZ()
                                 );
                              return 1;
                           } else {
                              for (int var3 = 0; var3 < var2 - 1; var3++) {
                                 Class_1261.method_2(new OnGroundOnly(true));
                              }

                              field_4219.player.setPosition(field_4219.player.getX(), field_4219.player.getY() + (double)var1x, field_4219.player.getZ());
                              Class_1261.method_2(field_4219.player.getX(), field_4219.player.getY() + (double)var1x, field_4219.player.getZ(), true);
                              return 1;
                           }
                        }
                     )
               )
         ))
         .then(
            LiteralArgumentBuilder.<CommandSource>literal("h")
               .then(
                  RequiredArgumentBuilder.<CommandSource, Float>argument("value", FloatArgumentType.floatArg())
                     .executes(
                        var0 -> {
                           float var1x = Math.clamp(
                              (Float)var0.getArgument("value", Float.class), Float.intBitsToFloat(-874192448), Float.intBitsToFloat(1273291200)
                           );
                           double var2 = Math.cos(Math.toRadians((double)(field_4219.player.getYaw() + (float)Class_0245.field_685)));
                           double var4 = Math.sin(Math.toRadians((double)(field_4219.player.getYaw() + (float)Class_0245.field_685)));
                           if (field_4219.player.hasVehicle()) {
                              field_4219.player
                                 .getVehicle()
                                 .setPosition(
                                    field_4219.player.getVehicle().getX() + (double)var1x * var2,
                                    field_4219.player.getVehicle().getY(),
                                    field_4219.player.getVehicle().getZ() + (double)var1x * var4
                                 );
                              return 1;
                           } else {
                              field_4219.player
                                 .setPosition(
                                    field_4219.player.getX() + (double)var1x * var2, field_4219.player.getY(), field_4219.player.getZ() + (double)var1x * var4
                                 );
                              Class_1261.method_2(
                                 field_4219.player.getX() + (double)var1x * var2,
                                 field_4219.player.getY(),
                                 field_4219.player.getZ() + (double)var1x * var4,
                                 true
                              );
                              return 1;
                           }
                        }
                     )
               )
         );
   }
}
