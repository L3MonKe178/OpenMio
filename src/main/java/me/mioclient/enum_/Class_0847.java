package me.mioclient.enum_;

import java.util.function.Function;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0485;
import net.minecraft.util.math.BlockPos;

public enum Class_0847 implements Class_1309 {
   XP(var0 -> BlockPos.ofFloored(field_4219.player.getX() + (double)var0.intValue(), 0.0, 0.0)),
   XN(var0 -> BlockPos.ofFloored(field_4219.player.getX() - (double)var0.intValue(), 0.0, 0.0)),
   ZP(var0 -> BlockPos.ofFloored(0.0, 0.0, field_4219.player.getZ() + (double)var0.intValue())),
   ZN(var0 -> BlockPos.ofFloored(0.0, 0.0, field_4219.player.getZ() - (double)var0.intValue())),
   XP_ZP(var0 -> BlockPos.ofFloored((double)(method_401() + var0), 0.0, (double)(method_791() + var0))),
   XN_ZP(var0 -> BlockPos.ofFloored((double)(method_401() - var0), 0.0, (double)(method_791() + var0))),
   XP_ZN(var0 -> BlockPos.ofFloored((double)(method_401() + var0), 0.0, (double)(method_791() - var0))),
   XN_ZN(var0 -> BlockPos.ofFloored((double)(method_401() - var0), 0.0, (double)(method_791() - var0)));

   public final Function<Integer, BlockPos> field_2740;

    Class_0847(Function<Integer, BlockPos> var3) {
      this.field_2740 = var3;
   }

   public static int method_790() {
      return (int)Math.max(Math.abs(field_4219.player.getX()), Math.abs(field_4219.player.getZ()));
   }

   public static int method_401() {
      return (int)((double)method_790() * Math.signum(field_4219.player.getX()));
   }

   public static int method_791() {
      return (int)((double)method_790() * Math.signum(field_4219.player.getZ()));
   }

   public static Class_0847 method_294(float var0) {
      return switch (Class_0485.method_221(var0)) {
         case 0 -> ZP;
         case 1 -> XN_ZP;
         case 2 -> XN;
         case 3 -> XN_ZN;
         case 4 -> ZN;
         case 5 -> XP_ZN;
         case 6 -> XP;
         case 7 -> XP_ZP;
         default -> null;
      };
   }
}
