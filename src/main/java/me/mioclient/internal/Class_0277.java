package me.mioclient.internal;

import java.awt.Color;
import me.mioclient.api.Class_1309;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;

public final class Class_0277 implements Class_1309 {
   public final BlockPos field_875;
   public float field_876;
   public float field_877;

   public Class_0277(BlockPos var1) {
      super();
      this.field_875 = var1;
   }

   public void method_2(SpeedMineModule var1, Class_0978 var2) {
      VoxelShape var3 = field_4219.world.getBlockState(this.field_875).getOutlineShape(field_4219.world, this.field_875);
      if (!var3.isEmpty() && Class_1225.method_3(this.field_875)) {
         float var4 = this.method_200();
         Color[] var5 = var1.field_4012.getValue().method_2(var1, var4);
         Box var6 = var1.field_4009.getValue().method_2(var1, var3.getBoundingBox(), var4).offset(this.field_875);
         Class_0612.method_5(var2.method_10(), var6, var5[0]);
         Class_0612.method_2(var2.method_10(), var6, var5[1], var1.field_4008.getValue());
      }
   }

   public BlockPos method_111() {
      return this.field_875;
   }

   public float method_200() {
      return MathHelper.clamp(MathHelper.lerp(Class_0838.method_776(), this.field_876, this.field_877), 0.0F, Float.intBitsToFloat(1065353216));
   }

   public void method_310(float var1) {
      this.method_263(this.field_877 + var1);
   }

   public void method_263(float var1) {
      this.field_876 = this.field_877;
      this.field_877 = var1;
   }
}
