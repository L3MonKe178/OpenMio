package me.mioclient.internal;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public final class Class_0421 {
   public final Timer field_1335 = new Timer();
   public final int field_1336;
   public final BlockPos field_1337;
   public float field_877;
   public float field_876;
   public int field_31;

   public Class_0421(int var1, BlockPos var2) {
      super();
      this.field_1336 = var1;
      this.field_1337 = var2;
   }

   public int getEntityId() {
      return this.field_1336;
   }

   public Entity method_11() {
      return MinecraftClient.getInstance().world.getEntityById(this.field_1336);
   }

   public BlockPos method_111() {
      return this.field_1337;
   }

   public float method_200() {
      return this.field_877;
   }

   public float method_209(float var1) {
      return MathHelper.lerp(var1, this.field_876, this.field_877);
   }

   public void method_263(float var1) {
      if (this.field_877 != var1) {
         if (var1 == 0.0F) {
            this.field_876 = 0.0F;
         } else {
            this.field_876 = this.field_877;
         }

         this.method_461();
         this.field_877 = MathHelper.clamp(var1, 0.0F, Float.intBitsToFloat(1065353216));
      }
   }

   public int method_165() {
      return this.field_31;
   }

   public void method_460(int var1) {
      this.field_31 = var1;
   }

   public void method_461() {
      this.field_1335.reset();
   }

   public boolean method_462() {
      if (this.field_877 == Float.intBitsToFloat(1065353216) && this.field_1335.method_9(2000L)) {
         return true;
      } else {
         Entity var1 = this.method_11();
         return var1 == null ? true : var1.getEyePos().squaredDistanceTo(this.field_1337.toCenterPos()) >= Double.longBitsToDouble(4634204016564240384L);
      }
   }
}
