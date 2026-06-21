package me.mioclient.enum_;

import java.util.Collections;
import java.util.List;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.player.AutoFarmModule;
import net.minecraft.util.math.BlockPos;

public enum Class_0195 implements Class_0013 {
   CROSSHAIR("Crosshair") {
      @Override
      public List<BlockPos> method_2(AutoFarmModule var1) {
         return Collections.singletonList(BlockPos.ofFloored(Class_1309.field_4219.crosshairTarget.getPos()));
      }
   },
   SPHERE("Sphere") {
      @Override
      public List<BlockPos> method_2(AutoFarmModule var1) {
         return Class_1225.method_2(Class_1309.field_4219.player.getPos(), var1.field_2246.getValue(), true);
      }
   };

   public final String field_551;

    Class_0195(String var3) {
      this.field_551 = var3;
   }

   @Override
   public String getName() {
      return this.field_551;
   }

   public abstract List<BlockPos> method_2(AutoFarmModule var1);
}
