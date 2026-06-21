package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.module.abstract_.AbstractModule_42;
import me.mioclient.record.Class_0681;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class Class_0700 extends Class_0351 {
   public Class_0700(AbstractModule_42 var1) {
      super(var1);
   }

   @Override
   public boolean method_2(Class_0681 var1) {
      for (AbstractClientPlayerEntity var3 : field_4219.world.getPlayers()) {
         if (!this.field_1150.field_774.getValue() || Class_0382.method_29(var3)) {
            Box var4 = this.field_1150.field_777.getValue()
               ? Hub.field_2612.method_2(var3, Class_0719.method_2(var3, this.field_1150.field_778))
               : Class_0719.method_4(var3);
            boolean var5 = !Class_0464.method_9(var3) || Class_0464.method_2(var3, var1.method_406().toCenterPos());
            if (!(Class_1334.method_5(var1.method_406().toCenterPos(), Class_0719.method_2(var4)) > this.field_1150.field_769.getValue())
               && !(var3.getY() <= (double)var1.method_406().getY())
               && !(var4.minY - (double)var1.method_406().getY() > Double.longBitsToDouble(4616189618054758400L))
               && !var3.isDead()
               && field_4219.player != var3
               && !Hub.field_2603.method_1009(var3.getGameProfile().getName())
               && !Hub.field_2605.method_221(BlockPos.ofFloored(var3.getPos()))
               && var5
               && (
                  !this.field_1150.field_773.getValue()
                     || Hub.field_2605.method_221(BlockPos.ofFloored(field_4219.player.getPos()))
                     || !(field_4219.player.getPos().distanceTo(var1.method_406().toCenterPos()) < Double.longBitsToDouble(4611686018427387904L))
               )) {
               return super.method_2(var1);
            }
         }
      }

      return false;
   }
}
