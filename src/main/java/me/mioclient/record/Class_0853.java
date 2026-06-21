package me.mioclient.record;

import java.awt.Color;
import java.util.Objects;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0200;
import me.mioclient.enum_.Class_0334;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_0978;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.exploit.NewChunksModule;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;

public final class Class_0853 implements MioAPI {
   public final ChunkPos field_2749;
   public final Class_0200 field_2750;
   public final Class_0334 field_2751;

   public Class_0853(ChunkPos var1, Class_0200 var2, Class_0334 var3) {
      super();
      this.field_2749 = var1;
      this.field_2750 = var2;
      this.field_2751 = var3;
   }

   public static Class_0853 method_2(ChunkPos var0, Class_0334 var1) {
      return new Class_0853(var0, Class_1225.method_1071(), var1);
   }

   public boolean method_2(Class_0334 var1) {
      return var1 == this.field_2751;
   }

   public void method_2(Class_0978 var1, NewChunksModule var2) {
      Color[] var3 = this.field_2751.method_2(var2);
      if (var3 != null) {
         BlockPos var4 = field_4219.gameRenderer.getCamera().getBlockPos();
         int var5 = field_4219.world.getBottomY() + var2.field_3368.getValue();
         if (var4.isWithinDistance(this.field_2749.getCenterAtY(var4.getY()), (double)(16 * var2.field_3367.getValue()))) {
            Box var6 = new Box(
               (double)this.field_2749.getStartX(),
               (double)var5,
               (double)this.field_2749.getStartZ(),
               (double)(this.field_2749.getStartX() + 16),
               (double)var5,
               (double)(this.field_2749.getStartZ() + 16)
            );
            if (!RotationManager.method_4(var6)) {
               return;
            }

            Class_0612.method_5(var1.method_10(), var6, var3[0]);
            Class_0612.method_2(var1.method_10(), var6, var3[1], var2.field_3370.getValue());
         }
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class_0853 var2 = (Class_0853)var1;
         return Objects.equals(this.field_2749, var2.field_2749) && this.field_2751 == var2.field_2751 && this.field_2750 == var2.field_2750;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field_2749, this.field_2750, this.field_2751);
   }

   public ChunkPos method_794() {
      return this.field_2749;
   }

   public Class_0200 method_418() {
      return this.field_2750;
   }

   public Class_0334 method_795() {
      return this.field_2751;
   }
}
