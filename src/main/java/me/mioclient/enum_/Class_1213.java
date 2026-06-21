package me.mioclient.enum_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.player.NukerModule;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public enum Class_1213 implements Nameable {
   SPHERE("Sphere") {
      @Override
      public List<BlockPos> method_2(NukerModule var1) {
         return Class_1225.method_2(MioAPI.field_4219.player.getPos(), var1.field_3153.getValue(), true);
      }
   },
   TUNNEL("Tunnel") {
      @Override
      public List<BlockPos> method_2(NukerModule var1) {
         ArrayList var2 = new ArrayList();

         for (int var3 = 0; (double)var3 <= Math.ceil((double)var1.field_3153.getValue().floatValue()); var3++) {
            for (int var4 = 0; (double)var4 < Math.ceil((double)MioAPI.field_4219.player.getHeight()); var4++) {
               BlockPos var5 = MioAPI.field_4219.player.getBlockPos();
               var5 = var5.up(var4);
               var5 = var5.offset(MioAPI.field_4219.player.getHorizontalFacing(), var3);
               var2.add(var5);
            }
         }

         return var2;
      }
   },
   CROSSHAIR("Crosshair") {
      @Override
      public List<BlockPos> method_2(NukerModule var1) {
         return MioAPI.field_4219.crosshairTarget instanceof BlockHitResult var2 ? Collections.singletonList(var2.getBlockPos()) : Collections.emptyList();
      }
   };

   public final String field_3797;

    Class_1213(String var3) {
      this.field_3797 = var3;
   }

   @Override
   public String getName() {
      return this.field_3797;
   }

   public abstract List<BlockPos> method_2(NukerModule var1);
}
