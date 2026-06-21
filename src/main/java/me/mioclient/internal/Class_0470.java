package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0322;
import me.mioclient.module.abstract_.AbstractModule_13;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Class_0470 implements Class_0322<LivingEntity, BlockPos> {
   public final AbstractModule_13 field_1490;
   public Vec3d field_1491;
   public float field_1492;
   public float field_1493;
   public float field_1494;

   public Class_0470(AbstractModule_13 var1) {
      super();
      this.field_1490 = var1;
   }

   public void method_142() {
      this.field_1491 = field_4219.player.getEyePos();
      this.field_1492 = this.field_1490.field_3574.getValue();
      this.field_1493 = this.field_1490.field_3587.getValue();
      this.field_1494 = this.field_1490.field_3588.getValue();
   }

   @Override
   public BlockPos method_35(LivingEntity var1) {
      return this.method_31(var1);
   }

   public BlockPos method_31(LivingEntity var1) {
      this.method_142();
      float var2 = 0.0F;
      BlockPos var3 = null;

      for (BlockEntity var5 : Hub.field_2622.method_347()) {
         if (var5 instanceof BedBlockEntity) {
            BedPart var6 = (BedPart)var5.getCachedState().get(BedBlock.PART);
            if (var6 != BedPart.FOOT && !(var5.getPos().toCenterPos().distanceTo(this.field_1491) > (double)this.field_1492)) {
               float var7 = Class_1323.method_9(var5.getPos().toCenterPos(), var1);
               float var8 = Class_1323.method_9(var5.getPos().toCenterPos(), field_4219.player);
               if ((!(var8 >= Class_0396.method_76()) || !this.field_1490.field_3589.getValue())
                  && !(var8 > this.field_1494)
                  && !(var7 < this.field_1493)
                  && (var3 == null || var7 > var2)) {
                  var3 = var5.getPos();
                  var2 = var7;
               }
            }
         }
      }

      return var3;
   }
}
