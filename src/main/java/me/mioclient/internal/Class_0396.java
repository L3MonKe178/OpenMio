package me.mioclient.internal;

import me.mioclient.api.Class_0875;
import me.mioclient.api.MioAPI;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PiglinActivity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.WolfVariant;

public class Class_0396 implements MioAPI {
   public Class_0396() {
      super();
   }

   public static float method_76() {
      return method_2((Entity) field_4219.player);
   }

   public static float method_2(Entity var0) {
      return var0 instanceof LivingEntity var1 ? var1.getHealth() + var1.getAbsorptionAmount() : Float.intBitsToFloat(1073741824);
   }

   public static boolean method_9(Entity var0) {
      if (var0 instanceof EndermanEntity var6) {
         return var6.isAngry();
      } else if (var0 instanceof Class_0875 var5) {
         return var5.mio$isAttacking();
      } else if (var0 instanceof WolfEntity var4) {
         return var4.getTextureId() == ((WolfVariant)var4.getVariant().value()).getAngryTextureId();
      } else if (var0 instanceof Angerable var3) {
         return var3.hasAngerTime();
      } else if (!(var0 instanceof AbstractPiglinEntity var1)) {
         return var0 instanceof PolarBearEntity ? true : var0 instanceof Monster;
      } else {
         PiglinActivity var2 = var1.getActivity();
         return var2 == PiglinActivity.CROSSBOW_CHARGE || var2 == PiglinActivity.CROSSBOW_HOLD || var2 == PiglinActivity.ATTACKING_WITH_MELEE_WEAPON;
      }
   }

   public static boolean method_5(Entity var0) {
      return var0 instanceof FishEntity || var0 instanceof PassiveEntity || var0 instanceof SquidEntity || var0 instanceof DolphinEntity;
   }

   public static boolean method_2(LivingEntity var0) {
      return !field_4219.world.isBlockSpaceEmpty(var0, var0.getBoundingBox().stretch(0.0, Double.longBitsToDouble(-4631501856787818086L), 0.0));
   }

   public static boolean method_7(Entity var0) {
      if (field_4219.options.getPerspective() != Perspective.FIRST_PERSON) {
         return false;
      } else {
         return var0 == field_4219.player ? false : field_4219.cameraEntity == var0;
      }
   }

   public static void method_9(LivingEntity var0, float var1) {
      var0.getAttributeInstance(EntityAttributes.GENERIC_STEP_HEIGHT).setBaseValue((double)var1);
   }
}
