package me.mioclient.enum_;

import java.util.function.Function;
import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import net.minecraft.util.math.BlockPos;

public enum Class_0376 implements Nameable {
   CLOSEST("Closest", var0 -> MioAPI.field_4219.player.getEyePos().squaredDistanceTo(var0.toCenterPos())),
   FURTHEST("Furthest", var0 -> -MioAPI.field_4219.player.getEyePos().squaredDistanceTo(var0.toCenterPos())),
   TOP("Top", var0 -> (double)(-var0.getY())),
   BOTTOM("Bottom", var0 -> (double)var0.getY());

   public final String field_1222;
   public final Function<BlockPos, Double> field_1223;

    Class_0376(String var3, Function<BlockPos, Double> var4) {
      this.field_1222 = var3;
      this.field_1223 = var4;
   }

   @Override
   public String getName() {
      return this.field_1222;
   }
}
