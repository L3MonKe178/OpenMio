package me.mioclient.internal;

import java.util.LinkedList;
import me.mioclient.api.Class_1309;
import net.minecraft.util.math.MathHelper;

public final class Class_0904 implements Class_1309 {
   public final LinkedList<Long> field_2834 = new LinkedList<>();
   public int field_2835;

   public Class_0904() {
      super();
      field_4220.method_14(this);
   }

   public void method_813() {
      long var1 = System.nanoTime();
      this.field_2834.add(var1);

      while (true) {
         long var3 = this.field_2834.getFirst();
         long var5 = 1000000000L;
         if (var1 - var3 <= 1000000000L) {
            this.field_2835 = this.field_2834.size();
            return;
         }

         this.field_2834.remove();
      }
   }

   public int method_814() {
      return this.field_2835;
   }

   public float method_154(float var1) {
      float var2 = Float.intBitsToFloat(1065353216) / ((float)this.field_2835 * var1);
      return MathHelper.clamp(var2, 0.0F, Float.intBitsToFloat(1065353216));
   }
}
