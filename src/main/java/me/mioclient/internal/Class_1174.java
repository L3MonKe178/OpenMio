package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public final class Class_1174 extends Class_0844 {
   public Class_1174(CombatmineModule var1) {
      super(var1);
   }

   @Override
   public void method_2(Class_0994 var1) {
      BlockPos var2 = null;

      for (BlockEntity var4 : Hub.field_2622.method_347()) {
         if (!(field_4219.player.getEyePos().distanceTo(var4.getPos().toCenterPos()) > Double.longBitsToDouble(4612136378390124954L))
            && var4 instanceof EnderChestBlockEntity
            && this.method_493(var4.getPos())
            && (var2 == null || this.method_494(var4.getPos()))) {
            var2 = var4.getPos();
         }
      }

      BlockPos var5 = var2;
      if (var5 != null) {
         var1.method_2(5, var1x -> var1x.method_425(var5));
      }
   }

   @Override
   public boolean method_206() {
      return this.field_2705.field_3698.getValue();
   }
}
