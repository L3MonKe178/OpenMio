package me.mioclient.internal;

import java.util.Arrays;
import java.util.Map;
import me.mioclient.Hub;
import me.mioclient.api.Class_0957;
import me.mioclient.api.Class_1309;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Class_1323 implements Class_1309 {
   public static AutoCrystalModule field_219 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static CombatmineModule field_4276 = Hub.field_2595.method_78(CombatmineModule.class);

   public Class_1323() {
      super();
   }

   public static float method_2(Vec3d var0, LivingEntity var1) {
      return method_2(var0, var1, Double.longBitsToDouble(4618441417868443648L), true, null);
   }

   public static float method_2(Vec3d var0, LivingEntity var1, Box var2) {
      return method_2(var0, var1, var2, Double.longBitsToDouble(4618441417868443648L), true, null, null);
   }

   public static float method_9(Vec3d var0, LivingEntity var1) {
      return method_9(var0, var1, var1.getBoundingBox());
   }

   public static float method_9(Vec3d var0, LivingEntity var1, Box var2) {
      return method_2(var0, var1, var2, Double.longBitsToDouble(4617315517961601024L), true, null, null);
   }

   public static float method_2(Vec3d var0, LivingEntity var1, double var2, boolean var4, BlockPos var5) {
      return method_2(var0, var1, var1.getBoundingBox(), var2, var4, var5, null);
   }

   public static float method_2(Vec3d var0, LivingEntity var1, Box var2, double var3, boolean var5, BlockPos var6, BlockPos var7) {
      if (var1.equals(field_4219.player) && field_4219.player.isCreative()) {
         return 0.0F;
      } else {
         Vec3d var8 = new Vec3d(
            MathHelper.lerp(Class_0245.field_688, var2.minX, var2.maxX), var2.minY, MathHelper.lerp(Class_0245.field_688, var2.minZ, var2.maxZ)
         );
         double var9 = var3 * Double.longBitsToDouble(4611686018427387904L);
         double var11 = var8.distanceTo(var0) / var9;
         if (var11 > Double.longBitsToDouble(4607182418800017408L)) {
            return 0.0F;
         } else {
            float var13 = method_2(var0, var1, var2, var6, var7, var5);
            double var14 = (Double.longBitsToDouble(4607182418800017408L) - var11) * (double)var13;
            float var16 = (float)(
               (var14 * var14 + var14) / Double.longBitsToDouble(4611686018427387904L) * Double.longBitsToDouble(4619567317775286272L) * var9
                  + Double.longBitsToDouble(4607182418800017408L)
            );
            return Math.max(method_2(var1, method_5(var16)), 0.0F);
         }
      }
   }

   public static float method_2(Vec3d var0, Entity var1, BlockPos var2, boolean var3) {
      return method_2(var0, var1, var1.getBoundingBox(), var2, null, var3);
   }

   public static float method_2(Vec3d var0, Entity var1, Box var2, BlockPos var3, BlockPos var4, boolean var5) {
      double var6 = Double.longBitsToDouble(4607182418800017408L)
         / ((var2.maxX - var2.minX) * Double.longBitsToDouble(4611686018427387904L) + Double.longBitsToDouble(4607182418800017408L));
      double var8 = Double.longBitsToDouble(4607182418800017408L)
         / ((var2.maxY - var2.minY) * Double.longBitsToDouble(4611686018427387904L) + Double.longBitsToDouble(4607182418800017408L));
      double var10 = Double.longBitsToDouble(4607182418800017408L)
         / ((var2.maxZ - var2.minZ) * Double.longBitsToDouble(4611686018427387904L) + Double.longBitsToDouble(4607182418800017408L));
      double var12 = (Double.longBitsToDouble(4607182418800017408L) - Math.floor(Double.longBitsToDouble(4607182418800017408L) / var6) * var6)
         / Double.longBitsToDouble(4611686018427387904L);
      double var14 = (Double.longBitsToDouble(4607182418800017408L) - Math.floor(Double.longBitsToDouble(4607182418800017408L) / var10) * var10)
         / Double.longBitsToDouble(4611686018427387904L);
      if (!(var6 < 0.0) && !(var8 < 0.0) && !(var10 < 0.0)) {
         int var16 = 0;
         int var17 = 0;

         for (double var18 = 0.0; var18 <= Double.longBitsToDouble(4607182418800017408L); var18 += var6) {
            for (double var20 = 0.0; var20 <= Double.longBitsToDouble(4607182418800017408L); var20 += var8) {
               for (double var22 = 0.0; var22 <= Double.longBitsToDouble(4607182418800017408L); var22 += var10) {
                  double var24 = MathHelper.lerp(var18, var2.minX, var2.maxX);
                  double var26 = MathHelper.lerp(var20, var2.minY, var2.maxY);
                  double var28 = MathHelper.lerp(var22, var2.minZ, var2.maxZ);
                  Vec3d var30 = new Vec3d(var24 + var12, var26, var28 + var14);
                  Class_0512 var31 = new Class_0533(var30, var0)
                     .method_22(var1)
                     .method_2(
                        Class_0957.method_2(Arrays.asList(var3, var3 == null ? null : field_4276.field_3712.method_111())),
                        Class_0957.method_374(var4),
                        var5 ? Class_0957.field_2969 : Class_0957.field_2968
                     )
                     .method_565();
                  if (Class_0981.method_2(var31).getType() == Type.MISS) {
                     var16++;
                  }

                  var17++;
               }
            }
         }

         return (float)var16 / (float)var17;
      } else {
         return 0.0F;
      }
   }

   public static float method_2(LivingEntity var0, float var1) {
      DamageSource var2 = field_4219.world.getDamageSources().explosion(null);
      var1 = DamageUtil.getDamageLeft(var0, var1, var2, (float)var0.getArmor(), (float)var0.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
      if (var0.hasStatusEffect(StatusEffects.RESISTANCE)) {
         var1 = var1 * (float)(25 - (var0.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1) * 5) / Float.intBitsToFloat(1103626240);
      }

      int var3 = 0;

      for (ItemStack var5 : var0.getArmorItems()) {
         Map<net.minecraft.registry.RegistryKey<net.minecraft.enchantment.Enchantment>, Integer> var6 = Class_0756.method_7(var5);
         var3 += var6.getOrDefault(Enchantments.BLAST_PROTECTION, 0) * 2 + var6.getOrDefault(Enchantments.PROTECTION, 0);
      }

      if (var3 > 20 || field_219.isToggled() && field_219.field_4108.getValue()) {
         var3 = 20;
      }

      var1 *= Float.intBitsToFloat(1065353216) - (float)var3 / Float.intBitsToFloat(1103626240);
      return var1 < 0.0F ? 0.0F : var1;
   }

   public static float method_5(float var0) {
      return switch (field_4219.world.getDifficulty()) {
         case PEACEFUL -> 0.0F;
         case EASY -> Math.min(var0 / Float.intBitsToFloat(1073741824) + Float.intBitsToFloat(1065353216), var0);
         case HARD -> var0 * Float.intBitsToFloat(1077936128) / Float.intBitsToFloat(1073741824);
         default -> var0;
      };
   }

   public static int method_5(ItemStack var0) {
      float var1 = ((float)var0.getMaxDamage() - (float)var0.getDamage()) / (float)var0.getMaxDamage();
      float var2 = Float.intBitsToFloat(1065353216) - var1;
      return MathHelper.clamp(100 - (int)Math.ceil((double)(var2 * Float.intBitsToFloat(1120403456))), 1, 100);
   }
}
