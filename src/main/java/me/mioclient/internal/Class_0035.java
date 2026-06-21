package me.mioclient.internal;

import me.mioclient.api.Class_0742;
import me.mioclient.module.combat.AntiPhaseModule;
import me.mioclient.record.Class_1013;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class Class_0035 implements Class_0742 {
   public Class_0035() {
      super();
   }

   @Override
   public Class_1013 method_2(AntiPhaseModule var1, PlayerEntity var2) {
      BlockPos var3 = Class_0382.method_9(var2);
      Direction var4 = Class_1035.method_9(var3, var1.field_848.getValue());
      return var4 != null && Class_1225.method_14(var3) ? new Class_1013(var3.offset(var4), var4.getOpposite(), this.method_5(var3, var4)) : null;
   }

   public Box method_5(BlockPos var1, Direction var2) {
      return new Box(var1);
   }
}
