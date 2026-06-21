package me.mioclient.record;

import me.mioclient.Hub;
import me.mioclient.api.Class_0957;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0512;
import me.mioclient.internal.Class_0533;
import me.mioclient.internal.Class_0981;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class Class_0258 {
   public final float[] field_785;

   public Class_0258(float[] var1) {
      super();
      this.field_785 = var1;
   }

   public boolean method_4(BlockPos var1) {
      HitResult var2 = this.method_263();
      if (var2.getType() == Type.BLOCK) {
         Box var3 = new Box(
            var2.getPos()
               .add(
                  Double.longBitsToDouble(-4646453807550688133L),
                  Double.longBitsToDouble(-4646453807550688133L),
                  Double.longBitsToDouble(-4646453807550688133L)
               ),
            var2.getPos()
               .add(Double.longBitsToDouble(4576918229304087675L), Double.longBitsToDouble(4576918229304087675L), Double.longBitsToDouble(4576918229304087675L))
         );
         return var3.intersects(new Box(var1));
      } else {
         return false;
      }
   }

   public HitResult method_263() {
      float var1 = this.field_785[1] * Class_0245.field_690;
      float var2 = -this.field_785[0] * Class_0245.field_690;
      float var3 = MathHelper.cos(var2);
      float var4 = MathHelper.sin(var2);
      float var5 = MathHelper.cos(var1);
      float var6 = MathHelper.sin(var1);
      Vec3d var7 = new Vec3d((double)(var4 * var5), (double)(-var6), (double)(var3 * var5));
      Vec3d var8 = Class_1309.field_4219
         .player
         .getEyePos()
         .add(
            var7.x * Double.longBitsToDouble(4617315517961601024L),
            var7.y * Double.longBitsToDouble(4617315517961601024L),
            var7.z * Double.longBitsToDouble(4617315517961601024L)
         );
      Class_0512 var9 = new Class_0533(Class_1309.field_4219.player.getEyePos(), var8)
         .method_2(Class_0957.method_9(Hub.field_2622.method_345().keySet()))
         .method_565();
      return Class_0981.method_2(var9);
   }

   public float[] method_221() {
      return this.field_785;
   }
}
