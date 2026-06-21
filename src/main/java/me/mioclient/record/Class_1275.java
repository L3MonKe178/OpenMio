package me.mioclient.record;

import me.mioclient.enum_.Class_1163;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public final class Class_1275 {
   public final BlockPos field_4042;
   public final PlayerEntity field_4043;
   public final double field_4044;
   public final Class_1163 field_4045;

   public Class_1275(BlockPos var1, PlayerEntity var2, double var3, Class_1163 var5) {
      super();
      this.field_4042 = var1;
      this.field_4043 = var2;
      this.field_4044 = var3;
      this.field_4045 = var5;
   }

   public Class_1275 method_2(Class_1275 var1) {
      if (var1 == null) {
         return this;
      } else if (this.field_4045.ordinal() < var1.field_4045.ordinal()) {
         return this;
      } else if (this.field_4045.ordinal() > var1.field_4045.ordinal()) {
         return var1;
      } else {
         return var1.field_4044 > this.field_4044 ? var1 : this;
      }
   }

   public BlockPos method_406() {
      return this.field_4042;
   }

   public PlayerEntity method_444() {
      return this.field_4043;
   }

   public double method_445() {
      return this.field_4044;
   }

   public Class_1163 method_446() {
      return this.field_4045;
   }
}
