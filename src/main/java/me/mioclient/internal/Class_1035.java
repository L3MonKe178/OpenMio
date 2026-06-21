package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_1010;
import me.mioclient.enum_.Class_1172;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.mixin.ducks.DuckLivingEntity;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.misc.KillEffectsModule;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.NoteBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.EggItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.item.TridentItem;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class Class_1035 implements MioAPI {
   public static final Timer field_3195 = new Timer();
   public static boolean field_3196 = false;
   public static final Direction[] field_3197 = new Direction[]{Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP};
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static KillEffectsModule field_3198 = Hub.field_2595.method_78(KillEffectsModule.class);

   public Class_1035() {
      super();
   }

   public static Direction method_78(BlockPos var0) {
      return method_9(var0, false);
   }

   public static Direction method_9(BlockPos var0, boolean var1) {
      return method_2(var0, var1, false);
   }

   public static Direction method_2(BlockPos var0, boolean var1, boolean var2) {
      for (Direction var6 : field_3197) {
         if (var0.add(var6.getVector()).getY() < field_4219.world.getTopY()) {
            BlockPos var7 = var0.offset(var6);
            if ((!var2 || Class_1225.method_2(Class_1172.field_3634.method_2(var7, var6.getOpposite())))
               && Hub.field_2622.method_105(var7)
               && (!var1 || method_39(var7).contains(var6.getOpposite()))) {
               return var6;
            }
         }
      }

      return null;
   }

   public static List<Direction> method_39(BlockPos var0) {
      ArrayList var1 = new ArrayList<>(List.of(Direction.values()));

      for (Direction var5 : Direction.values()) {
         BlockPos var6 = var0.offset(var5);
         BlockState var7 = field_4219.world.getBlockState(var6);
         boolean var8 = !var7.isReplaceable() && var7.isOpaqueFullCube(field_4219.world, var6);
         if (var8) {
            var1.remove(var5);
         }
      }

      if (var0.getY() + 1 >= field_4219.world.getTopY()) {
         var1.remove(Direction.UP);
      }

      double var9 = field_4219.player.getEyePos().x - var0.toCenterPos().x;
      double var10 = field_4219.player.getEyePos().z - var0.toCenterPos().z;
      if (var9 > Constants.field_688) {
         var1.remove(Direction.WEST);
      } else if (var9 < -Constants.field_688) {
         var1.remove(Direction.EAST);
      } else {
         var1.remove(Direction.WEST);
         var1.remove(Direction.EAST);
      }

      if (var10 > Constants.field_688) {
         var1.remove(Direction.NORTH);
      } else if (var10 < -Constants.field_688) {
         var1.remove(Direction.SOUTH);
      } else {
         var1.remove(Direction.NORTH);
         var1.remove(Direction.SOUTH);
      }

      if (field_4219.player.getEyePos().y < (double)(var0.getY() + (field_144.field_3746.getValue() == Class_1010.GRIM ? 1 : 0))) {
         var1.remove(Direction.UP);
      }

      if (field_4219.player.getEyePos().y > (double)var0.getY()) {
         var1.remove(Direction.DOWN);
      }

      return var1;
   }

   public static BlockPos method_2(BlockPos var0, int var1, boolean var2, boolean var3) {
      return method_2(var0, var1, var2, var3, var0x -> true);
   }

   public static BlockPos method_2(BlockPos var0, int var1, boolean var2, boolean var3, Predicate<BlockPos> var4) {
      for (int var5 = 1; var5 <= var1; var5++) {
         for (Direction var9 : field_3197) {
            BlockPos var10 = var0.offset(var9, var5);
            if (var4.test(var10)
               && method_9(var10, var3) != null
               && field_4219.world.getEntitiesByClass(PlayerEntity.class, new Box(var10), var1x -> var2).isEmpty()) {
               return var10;
            }
         }
      }

      return null;
   }

   public static boolean method_5(BlockPos var0, boolean var1) {
      return method_2(var0, var1, Hand.MAIN_HAND);
   }

   public static boolean method_2(BlockPos var0, boolean var1, Hand var2) {
      return method_2(var0, null, method_78(var0), var1, var2);
   }

   public static boolean method_2(BlockPos var0, Vec3d var1, Direction var2, boolean var3, Hand var4) {
      if (field_4219.world != null && field_4219.player != null && field_4219.interactionManager != null) {
         Block var6 = field_4219.player.getStackInHand(var4).getItem() instanceof BlockItem var7 ? var7.getBlock() : null;
         if (!method_2(var0, var6, false)) {
            return false;
         } else {
            boolean var11 = var3 && var2 == null;
            if (var2 == null) {
               if (!var3) {
                  return false;
               }

               var2 = field_4219.crosshairTarget instanceof BlockHitResult var8 ? var8.getSide() : Direction.DOWN;
            }

            BlockPos var12 = var11 ? var0 : var0.offset(var2);
            boolean var13 = method_31(var12);
            if (var1 == null) {
               var1 = var0.toCenterPos().add(Vec3d.of(var2.getVector()).multiply(Constants.field_688));
            }

            if (var13) {
               field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.PRESS_SHIFT_KEY));
            }

            field_3196 = true;
            ActionResult var10 = field_4219.interactionManager
               .interactBlock(field_4219.player, var4, new BlockHitResult(var1, var11 ? var2 : var2.getOpposite(), var12, false));
            field_3196 = false;
            if (var10.shouldSwingHand()) {
               field_4219.player.swingHand(var4);
            }

            if (var13) {
               field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.RELEASE_SHIFT_KEY));
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean method_2(BlockPos var0, Direction var1, boolean var2, Hand var3) {
      return method_9(var0, null, var1, var2, var3);
   }

   public static boolean method_9(BlockPos var0, Vec3d var1, Direction var2, boolean var3, Hand var4) {
      if (field_4219.world != null && field_4219.player != null && field_4219.interactionManager != null) {
         if (!method_2(var0, field_4219.player.getStackInHand(var4).getItem() instanceof BlockItem var5 ? var5.getBlock() : null, false)) {
            return false;
         } else {
            boolean var8 = var3 && var2 == null;
            if (var2 == null) {
               List var9 = method_39(var0);
               if (!var3) {
                  return false;
               }

               var2 = var9.isEmpty() ? Direction.DOWN : (Direction)var9.getFirst();
            }

            BlockPos var10 = var8 ? var0 : var0.offset(var2);
            boolean var7 = method_31(var10);
            if (var7) {
               field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.PRESS_SHIFT_KEY));
            }

            if (var1 == null) {
               var1 = var0.toCenterPos().add(Vec3d.of(var2.getVector()).multiply(Constants.field_688));
            }

            PacketUtil.method_2(var4, new BlockHitResult(var1, var8 ? var2 : var2.getOpposite(), var10, false));
            if (field_144.method_1052() && field_144.field_3744.getValue()) {
               if (!field_4219.player.handSwinging
                  || field_4219.player.handSwingTicks >= ((DuckLivingEntity)field_4219.player).getSwingDuration() / 2
                  || field_4219.player.handSwingTicks < 0) {
                  field_4219.player.handSwingTicks = -1;
                  field_4219.player.handSwinging = true;
                  field_4219.player.preferredHand = var4;
               }
            } else {
               field_4219.player.swingHand(var4);
            }

            if (var7) {
               field_4219.player.networkHandler.sendPacket(new ClientCommandC2SPacket(field_4219.player, Mode.RELEASE_SHIFT_KEY));
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean method_2(BlockPos var0, Block var1, boolean var2) {
      return method_2(var0, var1, var2, false);
   }

   public static boolean method_2(BlockPos var0, Block var1, boolean var2, boolean var3) {
      if (!field_4219.world.getBlockState(var0).isReplaceable() || !field_4219.world.getWorldBorder().contains(var0) || !World.isValid(var0)) {
         return false;
      } else if (var0.getY() < field_4219.world.getTopY() && var0.getY() >= field_4219.world.getBottomY()) {
         VoxelShape var4 = var1 == null ? VoxelShapes.empty() : var1.getDefaultState().getCollisionShape(field_4219.world, BlockPos.ORIGIN);
         Box var5 = var4.isEmpty() ? new Box(var0) : Class_0719.method_2(var4.getBoundingBox(), var0);
         if (var1 != null && !((DuckAbstractBlock)var1).isCollidable()) {
            return true;
         } else {
            for (Entity var7 : field_4219.world
               .getEntitiesByClass(
                  Entity.class,
                  var5,
                  var0x -> !(var0x instanceof ExperienceBottleEntity)
                        && !(var0x instanceof ItemEntity)
                        && !(var0x instanceof ItemFrameEntity)
                        && !(var0x instanceof ExperienceOrbEntity)
               )) {
               if ((Class_0719.method_2(var5, var7.getBoundingBox()) || var7 == field_4219.player) && !(var7 instanceof ArrowEntity)) {
                  if (!(var7 instanceof PlayerEntity)) {
                     if (!(var7 instanceof EndCrystalEntity) || !var3) {
                        return !var2;
                     }
                  } else if (var7.getBoundingBox().intersects(var5)) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean method_7(BlockPos var0, boolean var1) {
      return method_9(var0, var1, false);
   }

   public static boolean method_9(BlockPos var0, boolean var1, boolean var2) {
      if (field_4219.world.getBlockState(var0).isReplaceable() && field_4219.world.getWorldBorder().contains(var0)) {
         for (Entity var4 : field_4219.world
            .getEntitiesByClass(
               Entity.class,
               new Box(var0),
               var0x -> !(var0x instanceof ExperienceBottleEntity) && !(var0x instanceof ItemEntity) && !(var0x instanceof ExperienceOrbEntity)
            )) {
            if ((Class_0719.method_2(new Box(var0), var4.getBoundingBox()) || var4 == field_4219.player)
               && (!(var4 instanceof EndCrystalEntity) || !var2)
               && !(var4 instanceof ArrowEntity)) {
               if (var4 instanceof PlayerEntity) {
                  return false;
               }

               if (var1) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean method_2(BlockPos var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      if (!Class_1225.method_36(var0)) {
         return false;
      } else {
         BlockPos var7 = var0.add(0, 1, 0);
         Block var8 = field_4219.world.getBlockState(var7).getBlock();
         if (var4) {
            if (var8 != Blocks.AIR && var8 != Blocks.FIRE) {
               return false;
            }
         } else if (var8 != Blocks.AIR) {
            return false;
         }

         if (var1 && field_4219.world.getBlockState(var0.add(0, 2, 0)).getBlock() != Blocks.AIR) {
            return false;
         } else {
            Box var9 = new Box(
               (double)var7.getX(),
               (double)var7.getY(),
               (double)var7.getZ(),
               (double)(var7.getX() + 1),
               (double)var7.getY() + (var5 ? Double.longBitsToDouble(4606281698874543309L) : Double.longBitsToDouble(4611686018427387904L)),
               (double)(var7.getZ() + 1)
            );
            int var10 = 0;

            for (Entity var12 : field_4219.world.getEntities()) {
               if (!var12.isRemoved() && var12.isAlive()) {
                  Box var13 = var12.getBoundingBox();
                  if (var12 instanceof PlayerEntity && var12 != field_4219.player && var6) {
                     var13 = var13.expand(Double.longBitsToDouble(4576918229304087675L));
                  } else if (var12 instanceof EndCrystalEntity && !var3) {
                     continue;
                  }

                  if (var13.intersects(var9)) {
                     var10++;
                     break;
                  }
               }
            }

            return !var2 || var10 == 0;
         }
      }
   }

   public static Block method_30(BlockPos var0) {
      return var0 == null ? null : field_4219.world.getBlockState(var0).getBlock();
   }

   public static boolean method_16(BlockPos var0) {
      return ((DuckAbstractBlock)method_30(var0)).isCollidable();
   }

   public static Entity method_2(BlockPos var0, int var1) {
      for (Entity var3 : field_4219.world.getEntities()) {
         if (var3 instanceof EndCrystalEntity && var3.getBoundingBox().intersects(new Box(var0)) && var3.age >= var1) {
            method_29(var3);
            return var3;
         }
      }

      return null;
   }

   public static void method_29(Entity var0) {
      method_2(var0, false);
   }

   public static void method_2(Entity var0, boolean var1) {
      if (Hub.field_2623.method_80(var0.getId())) {
         if (field_3198.isToggled() && var0 instanceof PlayerEntity) {
            field_3198.method_185((PlayerEntity)var0);
         }

         field_4219.player.resetLastAttackedTicks();
      }
   }

   public static void method_2(Entity var0, Hand var1) {
      field_4219.interactionManager.interactEntityAtLocation(field_4219.player, var0, new EntityHitResult(var0), var1);
      field_4219.interactionManager.interactEntity(field_4219.player, var0, var1);
      field_4219.player.swingHand(var1);
   }

   public static boolean method_2(Block var0) {
      return var0 instanceof CraftingTableBlock
         || var0 instanceof AnvilBlock
         || var0 instanceof ButtonBlock
         || var0 instanceof AbstractPressurePlateBlock
         || var0 instanceof BlockWithEntity
         || var0 instanceof BedBlock
         || var0 instanceof FenceGateBlock
         || var0 instanceof DoorBlock
         || var0 instanceof NoteBlock
         || var0 instanceof TrapdoorBlock;
   }

   public static boolean method_31(BlockPos var0) {
      BlockState var1 = field_4219.world.getBlockState(var0);
      Block var2 = var1.getBlock();
      if (var2 == Blocks.RESPAWN_ANCHOR) {
         return true;
      } else {
         return var1.hasBlockEntity()
            ? true
            : var2 instanceof BedBlock
               || var2 instanceof AbstractChestBlock
               || var2 instanceof DoorBlock
               || var2 instanceof TrapdoorBlock
               || var2 instanceof ButtonBlock
               || var2 instanceof ComposterBlock
               || var2 instanceof NoteBlock
               || var2 instanceof AnvilBlock
               || var2 instanceof AbstractFurnaceBlock
               || var2 instanceof CraftingTableBlock
               || var2 instanceof EnchantingTableBlock
               || var2 instanceof CakeBlock;
      }
   }

   public Direction method_2(Direction var1, int var2) {
      return Direction.values()[2 + (var1.getId() + var2) % Direction.values().length];
   }

   public static boolean method_2(Item var0) {
      return var0 instanceof EggItem
         || var0 instanceof SnowballItem
         || var0 instanceof EnderPearlItem
         || var0 instanceof ExperienceBottleItem
         || var0 instanceof ThrowablePotionItem
         || var0 instanceof TridentItem;
   }

   public static void method_873() {
      field_3195.reset();
   }

   public static boolean method_932() {
      return !field_3195.method_9(50L);
   }
}
