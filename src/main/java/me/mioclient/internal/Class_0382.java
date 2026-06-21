package me.mioclient.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0388;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.block.Blocks;
import net.minecraft.client.input.Input;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;

public class Class_0382 implements Class_1309 {
   public Class_0382() {
      super();
   }

   public static boolean method_5(PlayerEntity var0) {
      return field_4219.world.getStatesInBox(var0.getBoundingBox()).anyMatch(var0x -> var0x.isOf(Blocks.COBWEB));
   }

   public static boolean method_7(PlayerEntity var0) {
      return Class_1035.method_30(BlockPos.ofFloored(var0.getX(), var0.getBoundingBox().minY, var0.getZ())) == Blocks.WATER || var0.isTouchingWater();
   }

   public static boolean method_29(PlayerEntity var0) {
      return method_7(var0) || var0.isInLava();
   }

   public static BlockPos method_425() {
      return method_9(field_4219.player);
   }

   public static BlockPos method_9(LivingEntity var0) {
      return BlockPos.ofFloored(var0.getX(), (double)Math.round(var0.getY()), var0.getZ());
   }

   public static Direction method_426() {
      Input var0 = field_4219.player.input;
      double[] var1 = Class_0464.method_2(field_4219.player.getYaw(), var0, Double.longBitsToDouble(4607182418800017408L));
      return Direction.fromRotation(Math.toDegrees(Math.atan2(var1[1], var1[0])) - (double)Class_0245.field_685);
   }

   public static List<BlockPos> method_5(LivingEntity var0) {
      ArrayList<BlockPos> var1 = new ArrayList<>();
      Set<BlockPos> var2 = method_7(var0);

      for (BlockPos var4 : var2) {
         for (Vec3i var8 : Class_0388.field_1258.method_440()) {
            BlockPos var9 = var4.add(var8);
            if (!var2.contains(var9)) {
               var1.add(var9);
            }
         }
      }

      return var1;
   }

   public static Set<BlockPos> method_7(LivingEntity var0) {
      BlockPos var1 = method_9(var0);
      HashSet var2 = new HashSet();
      Box var3 = var0.getBoundingBox();
      var3 = var3.expand(-Class_0719.field_2280);
      var2.add(BlockPos.ofFloored(var3.minX, (double)var1.getY(), var3.minZ));
      var2.add(BlockPos.ofFloored(var3.minX, (double)var1.getY(), var3.maxZ));
      var2.add(BlockPos.ofFloored(var3.maxX, (double)var1.getY(), var3.minZ));
      var2.add(BlockPos.ofFloored(var3.maxX, (double)var1.getY(), var3.maxZ));
      return var2;
   }

   public static boolean method_427() {
      return field_4219.player.getHungerManager().getFoodLevel() < 6;
   }

   public static boolean method_29(LivingEntity var0) {
      for (ItemStack var2 : var0.getArmorItems()) {
         if (!var2.isEmpty()) {
            return true;
         }
      }

      return false;
   }

   public static boolean method_4(PlayerEntity var0) {
      return var0.getInventory().getArmorStack(EquipmentSlot.CHEST.getEntitySlotId()).isOf(Items.ELYTRA);
   }

   public static boolean method_428() {
      if (ElytraFlyModule.field_885) {
         return field_4219.player.isFallFlying();
      } else {
         ElytraFlyModule.field_885 = true;
         boolean var0 = field_4219.player.isFallFlying();
         ElytraFlyModule.field_885 = false;
         return var0;
      }
   }

   public static Hand method_5(Hand var0) {
      return var0 == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
   }
}
