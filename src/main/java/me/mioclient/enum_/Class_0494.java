package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.player.AutoTameModule;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public enum Class_0494 implements Class_0013 {
   TAME("Tame") {
      @Override
      public Vec3d method_5(TameableEntity var1) {
         if (var1.getOwner() != null) {
            return null;
         } else {
            int var2 = Class_0136.method_7(AutoTameModule.method_9(var1));
            if (var2 == -1) {
               return null;
            } else {
               Class_0136.method_16(var2);
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
            && Class_1309.field_4219.player == var1.getOwner()
            && !var1.isBreedingItem(Class_1309.field_4219.player.getMainHandStack())) {
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
            && Class_1309.field_4219.player == var1.getOwner()
            && !var1.isBreedingItem(Class_1309.field_4219.player.getMainHandStack())) {
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
