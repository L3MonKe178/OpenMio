package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.module.combat.CombatmineModule;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.util.math.BlockPos;

public final class Class_0077 implements Class_1309 {
   public static SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   public final Class_0242 field_276 = new Class_0242();
   public final CombatmineModule field_277;
   public BlockPos field_278;

   public Class_0077(CombatmineModule var1) {
      super();
      this.field_277 = var1;
   }

   public boolean method_110() {
      BlockPos var1 = this.method_112();
      return var1 != null && !field_4219.world.isAir(var1) ? true : speedmine.method_1123() != null;
   }

   public BlockPos method_111() {
      Class_0277 var1 = speedmine.method_1123();
      return var1 == null ? null : var1.method_111();
   }

   public BlockPos method_112() {
      return this.field_276.method_9(200L) ? null : this.field_278;
   }

   public void method_113(BlockPos var1) {
      this.field_278 = var1;
      this.field_276.reset();
   }

   public boolean method_29(long var1) {
      return this.field_276.method_9(var1);
   }
}
