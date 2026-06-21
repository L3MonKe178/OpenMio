package me.mioclient.internal;

import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public final class Class_0435 extends Class_0844 {
   public Class_0435(CombatmineModule var1) {
      super(var1);
   }

   @Override
   public void method_2(Class_0994 var1) {
      PlayerEntity var2 = this.field_2705.field_3711.method_691();
      if (var2 != null) {
         long var3 = BlockPos.stream(this.field_2705.field_3711.method_374(var2)).count();
         if (var3 == 1L) {
            BlockPos var5 = Class_0382.method_9(var2);
            if (!Class_1225.method_14(var5)) {
               if (!Class_1225.method_3(var5) && Class_1225.method_3(var5.down())) {
                  var1.method_2(400, var2x -> {
                     if (this.field_2705.method_1047()) {
                        var2x.method_465(var5.down());
                     } else {
                        var2x.method_425(var5.down());
                     }
                  });
               }
            }
         }
      }
   }

   @Override
   public boolean method_206() {
      return this.field_2705.field_3700.getValue();
   }
}
