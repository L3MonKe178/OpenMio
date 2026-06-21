package me.mioclient.internal;

import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_1291;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_1172;
import me.mioclient.module.combat.CombatmineModule;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public abstract class Class_0844 implements Class_1309, Class_1291 {
   public static SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   public final CombatmineModule field_2705;

   public Class_0844(CombatmineModule var1) {
      super();
      this.field_2705 = var1;
   }

   public boolean method_493(BlockPos var1) {
      if (var1.equals(this.field_2705.field_3712.method_112())) {
         return false;
      } else if (field_4219.player.getEyePos().distanceTo(var1.toCenterPos()) > (double)this.field_2705.method_884()) {
         return false;
      } else if (!speedmine.method_686(var1)) {
         return false;
      } else {
         if (this.field_2705.field_3693.getValue()) {
            List var2 = Class_1035.method_39(var1);
            if (var2.isEmpty()) {
               return false;
            }
         }

         return this.field_2705.field_3694.getValue() && !Class_1225.method_2(Class_1172.field_3634.method_38(var1)) ? false : Class_1225.method_3(var1);
      }
   }

   public boolean method_494(BlockPos var1) {
      return var1 == null ? false : var1.equals(speedmine.method_1120()) || var1.equals(speedmine.method_1121());
   }

   public boolean method_263(Entity var1) {
      return field_4219.world.isSpaceEmpty(var1.getBoundingBox().stretch(0.0, Double.longBitsToDouble(-4631501856787818086L), 0.0));
   }

   @Override
   public boolean method_206() {
      return false;
   }
}
