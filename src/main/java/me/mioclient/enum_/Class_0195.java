package me.mioclient.enum_;

import java.util.Collections;
import java.util.List;
import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.player.AutoFarmModule;
import net.minecraft.util.math.BlockPos;

public enum Class_0195 implements Nameable {
   CROSSHAIR("Crosshair") {
      @Override
      public List<BlockPos> method_2(AutoFarmModule var1) {
         return Collections.singletonList(BlockPos.ofFloored(MioAPI.field_4219.crosshairTarget.getPos()));
      }
   },
   SPHERE("Sphere") {
      @Override
      public List<BlockPos> method_2(AutoFarmModule var1) {
         return Class_1225.method_2(MioAPI.field_4219.player.getPos(), var1.field_2246.getValue(), true);
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
