package me.mioclient.internal;

import me.mioclient.record.Class_0146;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class Class_0003 extends Class_0088 {
   public Class_0003() {
      super();
   }

   @Override
   public boolean method_2(BlockPos var1, PlayerEntity var2, Class_0146 var3) {
      return Class_0719.method_4(var2).intersects(var3.method_172().offset(var1));
   }
}
