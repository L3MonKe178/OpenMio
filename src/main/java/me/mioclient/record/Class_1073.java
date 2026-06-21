package me.mioclient.record;

import me.mioclient.Hub;
import me.mioclient.api.Class_0705;
import me.mioclient.api.Class_1309;
import net.minecraft.entity.player.PlayerEntity;

public final class Class_1073 implements Class_1309 {
   public final PlayerEntity field_3292;
   public final double field_3293;

   public Class_1073(PlayerEntity var1, double var2) {
      super();
      this.field_3292 = var1;
      this.field_3293 = var2;
   }

   public static boolean method_2(PlayerEntity var0, double var1) {
      return var0 == null
         || var0 == field_4219.player
         || !var0.isAlive()
         || Hub.field_2603.method_30(var0)
         || ((Class_0705)var0).isServerSideDead()
         || var0.isSpectator()
         || var0.getAbilities().creativeMode
         || (double)field_4219.player.distanceTo(var0) > var1;
   }

   public PlayerEntity method_956() {
      return this.field_3292;
   }

   public double method_445() {
      return this.field_3293;
   }
}
