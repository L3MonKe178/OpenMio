package me.mioclient.internal;

import net.minecraft.util.math.MathHelper;

public class Class_0055 {
   public float field_139;
   public float field_140;
   public long startTime;
   public long field_141;

   public Class_0055() {
      super();
   }

   public void method_2(float var1, long var2) {
      if (this.field_140 != var1) {
         this.field_139 = this.method_45();
         this.field_140 = var1;
         this.startTime = System.currentTimeMillis();
         this.field_141 = var2;
      }
   }

   public void method_2(boolean var1, long var2) {
      this.method_2(var1 ? Float.intBitsToFloat(1065353216) : 0.0F, var2);
   }

   public void method_36(float var1) {
      this.method_2(var1, 0L);
   }

   public float method_45() {
      return (
               Float.intBitsToFloat(1065353216)
                  - MathHelper.clamp(
                     (float)(this.startTime + this.field_141 - System.currentTimeMillis()) / (float)Math.max(this.field_141, 1L),
                     0.0F,
                     Float.intBitsToFloat(1065353216)
                  )
            )
            * (this.field_140 - this.field_139)
         + this.field_139;
   }
}
