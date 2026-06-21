package me.mioclient.internal;

import java.awt.Color;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;

public class Class_1138 {
   public Box field_3523;
   public long field_677;
   public float field_1889 = Float.intBitsToFloat(1065353216);

   public Class_1138() {
      super();
   }

   public void method_1016() {
      this.field_677 = System.currentTimeMillis() + 150L;
   }

   public void method_425(BlockPos var1) {
      this.field_3523 = new Box(var1);
   }

   public void method_29(Box var1) {
      this.field_3523 = var1;
   }

   public void method_17(float var1) {
      this.field_1889 = var1;
   }

   public void method_2(MatrixStack var1, Color var2, Color var3, float var4, boolean var5) {
      if (this.field_3523 != null && RotationManager.method_4(this.field_3523)) {
         float var6 = this.method_37(var4);
         Class_0612.method_5(var1, this.field_3523, var5 ? Class_1081.method_9(var2, (int)((float)var2.getAlpha() * var6)) : var2);
         Class_0612.method_2(var1, this.field_3523, var5 ? Class_1081.method_9(var3, (int)((float)var3.getAlpha() * var6)) : var3, this.field_1889);
         if (var6 == 0.0F) {
            this.field_3523 = null;
         }
      }
   }

   public float method_37(float var1) {
      return MathHelper.clamp(
         Float.intBitsToFloat(1065353216) - (float)Math.max(System.currentTimeMillis() - this.field_677, 0L) / var1, 0.0F, Float.intBitsToFloat(1065353216)
      );
   }

   public Box method_1017() {
      return this.field_3523;
   }
}
