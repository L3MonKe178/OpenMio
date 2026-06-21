package me.mioclient.internal;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public final class Class_1149 {
   public Class_1149() {
      super();
   }

   public Direction method_4(float[] var1) {
      float var2 = var1[1] * Float.intBitsToFloat(1016003125);
      float var3 = -var1[0] * Float.intBitsToFloat(1016003125);
      float var4 = MathHelper.sin(var2);
      float var5 = MathHelper.cos(var2);
      float var6 = MathHelper.sin(var3);
      float var7 = MathHelper.cos(var3);
      boolean var8 = var6 > 0.0F;
      boolean var9 = var4 < 0.0F;
      boolean var10 = var7 > 0.0F;
      float var11 = var8 ? var6 : -var6;
      float var12 = var9 ? -var4 : var4;
      float var13 = var10 ? var7 : -var7;
      float var14 = var11 * var5;
      float var15 = var13 * var5;
      Direction var16 = var8 ? Direction.EAST : Direction.WEST;
      Direction var17 = var9 ? Direction.UP : Direction.DOWN;
      Direction var18 = var10 ? Direction.SOUTH : Direction.NORTH;
      if (var11 > var13) {
         return var12 > var14 ? var17 : var16;
      } else {
         return var12 > var15 ? var17 : var18;
      }
   }
}
