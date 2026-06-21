package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;
import me.mioclient.Hub;
import me.mioclient.api.Class_0957;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0200;
import me.mioclient.enum_.Class_1172;
import me.mioclient.module.combat.AutoCrystalModule;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;

public class Class_1225 implements MioAPI {
   public static final AutoCrystalModule field_3862 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static final List<Block> field_3863 = List.of(Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.GILDED_BLACKSTONE);
   public static final List<Block> field_3864 = List.of(
      Blocks.DEEPSLATE_BRICKS,
      Blocks.CRACKED_DEEPSLATE_BRICKS,
      Blocks.DEEPSLATE_TILES,
      Blocks.CRACKED_DEEPSLATE_TILES,
      Blocks.DEEPSLATE,
      Blocks.CHISELED_DEEPSLATE,
      Blocks.SCULK
   );
   public static final List<Block> field_3865 = List.of(
      Blocks.WAXED_OXIDIZED_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER, Blocks.WAXED_COPPER_BLOCK, Blocks.WAXED_OXIDIZED_COPPER_GRATE
   );

   public Class_1225() {
      super();
   }

   public static List<WorldChunk> method_1069() {
      ArrayList var0 = new ArrayList();
      int var1 = (Integer)field_4219.options.getViewDistance().getValue();

      for (int var2 = -var1; var2 <= var1; var2++) {
         for (int var3 = -var1; var3 <= var1; var3++) {
            WorldChunk var4 = field_4219.world
               .getChunkManager()
               .getWorldChunk((int)field_4219.player.getX() / 16 + var2, (int)field_4219.player.getZ() / 16 + var3);
            if (var4 != null) {
               var0.add(var4);
            }
         }
      }

      return var0;
   }

   public static List<BlockEntity> method_1070() {
      ArrayList var0 = new ArrayList();

      for (WorldChunk var2 : method_1069()) {
         if (!var2.getBlockEntities().isEmpty()) {
            try {
               var0.addAll(new ArrayList(var2.getBlockEntities().values()));
            } catch (Throwable var4) {
               field_3862.method_463();
               break;
            }
         }
      }

      return var0;
   }

   public static boolean method_2(Chunk var0, BlockPos var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      BlockState var7 = var0.getBlockState(var1);
      if (var7.isOf(Blocks.CHEST)) {
         BlockState var8 = var0.getBlockState(var1.down());
         boolean var9 = var7.get(ChestBlock.CHEST_TYPE) == ChestType.SINGLE;
         if (method_1071() == Class_0200.THE_NETHER) {
            if (var2 && var9 && var8.isOf(Blocks.NETHER_BRICKS)) {
               return true;
            }

            return var3 && field_3863.contains(var8.getBlock());
         }

         if (method_1071() == Class_0200.OVERWORLD) {
            if (!var9) {
               return false;
            }

            if (var6 && var1.getY() <= 34) {
               boolean var10 = var8.isOf(Blocks.CHISELED_TUFF_BRICKS);
               if (field_3865.contains(var8.getBlock()) || var10) {
                  boolean var11 = false;

                  for (Direction var15 : Direction.values()) {
                     if (var15.getAxis().isHorizontal()) {
                        BlockState var16 = var0.getBlockState(var1.offset(var15));
                        if (var10) {
                           if (!var16.isOf(Blocks.AIR)) {
                              var11 = true;
                              break;
                           }
                        } else if (field_3865.contains(var16.getBlock())) {
                           return true;
                        }
                     }
                  }

                  if (var10 && !var11) {
                     return true;
                  }
               }
            }

            if (var4 && var1.getY() <= -32 && var1.getY() >= -51 && field_3864.contains(var8.getBlock())) {
               return true;
            }

            return var5 && (var8.isOf(Blocks.MOSSY_COBBLESTONE) || var8.isOf(Blocks.COBBLESTONE));
         }
      }

      return false;
   }

   public static List<BlockPos> method_2(Vec3d var0, float var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      BlockPos var4 = BlockPos.ofFloored(var0);

      for (float var5 = -var1; var5 < var1; var5 += Float.intBitsToFloat(1065353216)) {
         for (float var6 = -var1; var6 < var1; var6 += Float.intBitsToFloat(1065353216)) {
            for (float var7 = -var1; var7 < var1; var7 += Float.intBitsToFloat(1065353216)) {
               BlockPos var8 = var4.add((int)var5, (int)var6, (int)var7);
               if (var4.isWithinDistance(var8, (double)var1) && (!var2 || !field_4219.world.getBlockState(var8).isAir())) {
                  var3.add(var8);
               }
            }
         }
      }

      return var3;
   }

   public static Vec3d method_2(BlockPos var0, Class_1172 var1) {
      for (Vec3d var3 : var1.method_38(var0)) {
         if (method_9(var3)) {
            return var3;
         }
      }

      return null;
   }

   public static boolean method_2(Vec3i var0) {
      return method_9(Vec3d.ofCenter(var0));
   }

   public static boolean method_2(Collection<Vec3d> var0) {
      for (Vec3d var2 : var0) {
         if (method_9(var2)) {
            return true;
         }
      }

      return false;
   }

   public static boolean method_9(Vec3d var0) {
      Vec3d var1 = field_4219.player.getEyePos();
      BlockPos var2 = BlockPos.ofFloored(var0);
      if (var0.distanceTo(var1) > Double.longBitsToDouble(4638707616191610880L)) {
         return false;
      } else {
         Class_0512 var3 = new Class_0533(field_4219.player.getEyePos(), var0).method_2(Class_0957.method_107(var2)).method_565();
         BlockHitResult var4 = Class_0981.method_2(var3);
         if (var4.getType() == Type.MISS || var4.getPos().equals(var0)) {
            return true;
         } else {
            if (var4 instanceof BlockHitResult var5 && var5.getBlockPos().equals(var2)) {
               return true;
            }

            return false;
         }
      }
   }

   public static boolean method_2(Vec3d var0, Vec3d var1) {
      Vec3d var2 = field_4219.player.getEyePos();
      if (var0.distanceTo(var2) > Double.longBitsToDouble(4638707616191610880L)) {
         return false;
      } else if (field_4219.world.raycast(new RaycastContext(var2, var0, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player)).getType() == Type.MISS) {
         return true;
      } else {
         return field_4219.player.getY() >= var0.y
            ? field_4219.world.raycast(new RaycastContext(var2, var1, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player)).getType() == Type.MISS
            : false;
      }
   }

   public static boolean method_3(BlockPos var0) {
      if (var0 == null) {
         return false;
      } else {
         BlockState var1 = field_4219.world.getBlockState(var0);
         if (!field_4219.player.isCreative() && var1.getHardness(field_4219.world, var0) < 0.0F) {
            return false;
         } else {
            return var1.isAir() ? false : var1.getOutlineShape(field_4219.world, var0) != VoxelShapes.empty();
         }
      }
   }

   public static double method_2(ItemStack var0, BlockState var1, boolean var2) {
      float var3 = var1.getHardness(null, null);
      return var3 == Float.intBitsToFloat(-1082130432)
         ? 0.0
         : method_9(var0, var1, var2) / (double)var3 / (double)(var1.isToolRequired() && !var0.isSuitableFor(var1) ? 100 : 30);
   }

   public static double method_9(ItemStack var0, BlockState var1, boolean var2) {
      double var3 = (double)var0.getMiningSpeedMultiplier(var1);
      if (var3 > Double.longBitsToDouble(4607182418800017408L)) {
         int var5 = Class_0756.method_2(Enchantments.EFFICIENCY, var0);
         if (var5 > 0 && !var0.isEmpty()) {
            var3 += (double)(var5 * var5 + 1);
         }
      }

      if (StatusEffectUtil.hasHaste(field_4219.player)) {
         var3 *= (double)(
            Float.intBitsToFloat(1065353216) + (float)(StatusEffectUtil.getHasteAmplifier(field_4219.player) + 1) * Float.intBitsToFloat(1045220557)
         );
      }

      if (field_4219.player.hasStatusEffect(StatusEffects.MINING_FATIGUE)) {
         float var6 = switch (field_4219.player.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) {
            case 0 -> Float.intBitsToFloat(1050253722);
            case 1 -> Float.intBitsToFloat(1035489772);
            case 2 -> Float.intBitsToFloat(993063548);
            default -> Float.intBitsToFloat(978605614);
         };
         var3 *= (double)var6;
      }

      if (field_4219.player.isSubmergedIn(FluidTags.WATER) && !Class_0756.method_9(Enchantments.AQUA_AFFINITY, EquipmentSlot.HEAD)) {
         var3 /= Double.longBitsToDouble(4617315517961601024L);
      }

      if (!var2) {
         var3 /= Double.longBitsToDouble(4617315517961601024L);
      }

      return var3;
   }

   public static Class_0200 method_1071() {
      if (field_4219.world == null) {
         return Class_0200.OVERWORLD;
      } else {
         String var0 = field_4219.world.getRegistryKey().getValue().getPath();

         return switch (var0) {
            case "the_nether" -> Class_0200.THE_NETHER;
            case "the_end" -> Class_0200.THE_END;
            default -> Class_0200.OVERWORLD;
         };
      }
   }

   public static Entity method_2(boolean var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, float var6, float var7) {
      return method_2(var0, var1, var2, var3, var4, var5, var6, var7, false, false);
   }

   public static Entity method_2(
      boolean var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, float var6, float var7, boolean var8, boolean var9
   ) {
      return method_2(var0, var1, var2, var3, var4, var5, var6, var7, false, false, Comparator.comparing(field_4219.player::squaredDistanceTo), null);
   }

   public static Entity method_2(
      boolean var0,
      boolean var1,
      boolean var2,
      boolean var3,
      boolean var4,
      boolean var5,
      float var6,
      float var7,
      boolean var8,
      boolean var9,
      Comparator<Entity> var10,
      Predicate<Entity> var11
   ) {
      Entity var12 = StreamSupport.<Entity>stream(field_4219.world.getEntities().spliterator(), false)
         .filter(
            var7x -> !(var7x instanceof ExperienceBottleEntity)
                  && method_78(var7x)
                  && (
                     var7x instanceof PlayerEntity && var0 && (!var9 || Class_0382.method_29((LivingEntity)((PlayerEntity)var7x)))
                        || Class_0396.method_5(var7x) && var2
                        || Class_0396.method_9(var7x) && var3
                        || var7x instanceof PiglinEntity && var1
                        || var7x instanceof Angerable && var1
                        || var7x instanceof EndCrystalEntity && var4
                        || var7x instanceof ProjectileEntity && var5 && var7x.isAttackable()
                  )
         )
         .filter(var2x -> method_39(var2x) <= (double)(Class_0981.method_30(var2x) ? var7 : var6))
         .filter(var1x -> var11 == null || var11.test(var1x))
         .min(var10)
         .orElse(null);
      if (var12 != null && !(var12 instanceof PlayerEntity) && var12.hasCustomName() && var8) {
         var12 = null;
      }

      return var12;
   }

   public static boolean method_78(Entity var0) {
      if (var0 == field_4219.player) {
         return false;
      } else {
         if (var0 instanceof PlayerEntity var1 && Hub.field_2603.method_30(var1)) {
            return false;
         }

         return var0.isAlive();
      }
   }

   public static double method_39(Entity var0) {
      Box var1 = var0.getBoundingBox();
      Vec3d var2 = Class_0719.method_4(field_4219.player).getBottomCenter().add(0.0, (double)field_4219.player.getStandingEyeHeight(), 0.0);
      if (!(var0 instanceof EnderDragonEntity var3)) {
         return method_2(var2, var1);
      } else {
         double var4 = Double.longBitsToDouble(4666722622711529472L);

         for (EnderDragonPart var9 : var3.getBodyParts()) {
            var4 = Math.min(var4, method_2(var2, var9.getBoundingBox()));
         }

         return var4;
      }
   }

   public static double method_2(Vec3d var0, Box var1) {
      return var0.distanceTo(Class_0719.method_9(var0, var1));
   }

   public static boolean method_36(BlockPos var0) {
      return field_4219.world.getBlockState(var0).getBlock() == Blocks.BEDROCK || field_4219.world.getBlockState(var0).getBlock() == Blocks.OBSIDIAN;
   }

   public static boolean method_14(BlockPos var0) {
      return field_4219.world.getBlockState(var0).isAir();
   }

   public static boolean method_17(BlockPos var0) {
      return !field_4219.world.getFluidState(var0).isEmpty();
   }

   public static boolean method_37(BlockPos var0) {
      return Hub.field_2622.method_105(var0);
   }

   public static Block method_33(BlockPos var0) {
      return field_4219.world.getBlockState(var0).getBlock();
   }

   public static boolean method_2(HitResult var0) {
      if (var0 == null) {
         return false;
      } else {
         if (var0 instanceof BlockHitResult var1) {
            BlockPos var2 = var1.getBlockPos();
            BlockState var3 = field_4219.world.getBlockState(var2);
            if (var3.hasBlockEntity()) {
               return true;
            }

            if (Class_1035.method_2(var3.getBlock())) {
               return true;
            }
         }

         if (!(var0 instanceof EntityHitResult var4)) {
            return false;
         } else {
            Entity var5 = var4.getEntity();
            return var5 instanceof VehicleEntity || var5 instanceof VillagerEntity;
         }
      }
   }
}
