package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.player.AutoTameModule;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public enum Class_0494 implements Nameable {
   TAME("Tame") {
      @Override
      public Vec3d method_5(TameableEntity var1) {
         if (var1.getOwner() != null) {
            return null;
         } else {
            int var2 = PlayerUtil.method_7(AutoTameModule.method_9(var1));
            if (var2 == -1) {
               return null;
            } else {
               PlayerUtil.method_16(var2);
               Class_1035.method_2(var1, Hand.MAIN_HAND);
               return var1.getBoundingBox().getCenter();
            }
         }
      }
   },
   SIT("Sit") {
      @Override
      public Vec3d method_5(TameableEntity var1) {
         if (!var1.isInSittingPose()
            && !var1.isSitting()
            && MioAPI.field_4219.player == var1.getOwner()
            && !var1.isBreedingItem(MioAPI.field_4219.player.getMainHandStack())) {
            Class_1035.method_2(var1, Hand.MAIN_HAND);
            return var1.getBoundingBox().getCenter();
         } else {
            return null;
         }
      }
   },
   STAND("Stand") {
      @Override
      public Vec3d method_5(TameableEntity var1) {
         if ((var1.isInSittingPose() || var1.isSitting())
            && MioAPI.field_4219.player == var1.getOwner()
            && !var1.isBreedingItem(MioAPI.field_4219.player.getMainHandStack())) {
            Class_1035.method_2(var1, Hand.MAIN_HAND);
            return var1.getBoundingBox().getCenter();
         } else {
            return null;
         }
      }
   };

   public final String field_1563;

    Class_0494(String var3) {
      this.field_1563 = var3;
   }

   @Override
   public String getName() {
      return this.field_1563;
   }

   public abstract Vec3d method_5(TameableEntity var1);
}
