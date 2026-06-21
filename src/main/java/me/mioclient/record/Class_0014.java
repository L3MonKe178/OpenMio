package me.mioclient.record;

import me.mioclient.enum_.Class_0499;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public final class Class_0014 {
   public final PlayerEntity field_21;
   public final Class_0499 field_22;
   @Nullable
   public final Direction field_23;

   public Class_0014(PlayerEntity var1, Class_0499 var2, @Nullable Direction var3) {
      super();
      this.field_21 = var1;
      this.field_22 = var2;
      this.field_23 = var3;
   }

   public PlayerEntity method_18() {
      return this.field_21;
   }

   public Class_0499 method_19() {
      return this.field_22;
   }

   @Nullable
   public Direction method_20() {
      return this.field_23;
   }
}
