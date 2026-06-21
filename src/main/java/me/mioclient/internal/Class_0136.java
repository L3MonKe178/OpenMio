package me.mioclient.internal;

import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.mixin.ducks.DuckInteractionManager;
import me.mioclient.module.player.ItemSaverModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class Class_0136 implements Class_1309 {
   public static ItemSaverModule field_405 = Hub.field_2595.method_78(ItemSaverModule.class);

   public Class_0136() {
      super();
   }

   public static void method_78(int var0) {
      if (var0 >= 0 && var0 <= 8) {
         field_4219.player.getInventory().selectedSlot = var0;
         ((DuckInteractionManager)field_4219.interactionManager).sync();
      }
   }

   public static void method_39(int var0) {
      if (var0 >= 0) {
         field_4219.interactionManager
            .clickSlot(
               field_4219.player.currentScreenHandler.syncId,
               method_30(var0),
               field_4219.player.getInventory().selectedSlot,
               SlotActionType.SWAP,
               field_4219.player
            );
      }
   }

   public static void method_9(int var0, int var1) {
      if (var0 >= 0 && var1 >= 0) {
         int var2 = field_4219.player.currentScreenHandler.syncId;
         field_4219.interactionManager.clickSlot(var2, var0, var1, SlotActionType.SWAP, field_4219.player);
      }
   }

   public static void method_5(int var0, int var1) {
      if (var0 != -1 && var1 != -1) {
         field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var0, 0, SlotActionType.PICKUP, field_4219.player);
         field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var1, 0, SlotActionType.PICKUP, field_4219.player);
         if (!field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
            field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var0, 0, SlotActionType.PICKUP, field_4219.player);
         }
      }
   }

   public static int method_2(Item var0, boolean var1) {
      return method_2(var0, var0x -> true, var1);
   }

   public static int method_9(Item var0) {
      return method_2(var0, var0x -> true, false);
   }

   public static int method_2(Item var0, Predicate<ItemStack> var1, boolean var2) {
      if (!Hub.field_2611.method_842() && var0.equals(field_4219.player.getOffHandStack().getItem()) && var1.test(field_4219.player.getOffHandStack())) {
         return -1;
      } else {
         for (int var3 = 36; var3 >= 0; var3--) {
            ItemStack var4 = field_4219.player.getInventory().getStack(var3);
            if (var4.getItem() == var0 && var1.test(var4)) {
               if (var3 < 9) {
                  var3 += 36;
               }

               return var3;
            }
         }

         if (var2) {
            for (int var5 = 0; var5 <= 3; var5++) {
               ItemStack var6 = (ItemStack)field_4219.player.playerScreenHandler.getCraftingInput().getHeldStacks().get(var5);
               if (var6.getItem() == var0 && var1.test(var6)) {
                  return var5 + 1;
               }
            }
         }

         return -1;
      }
   }

   public static int method_2(Predicate<ItemStack> var0, boolean var1) {
      for (int var2 = 36; var2 >= (var1 ? 9 : 0); var2--) {
         ItemStack var3 = field_4219.player.getInventory().getStack(var2);
         if (var0.test(var3)) {
            return var2;
         }
      }

      return -1;
   }

   public static int method_2(Item... var0) {
      return method_7((Predicate<ItemStack>)(var1 -> {
         for (Item var5 : var0) {
            if (var5.equals(var1.getItem())) {
               return true;
            }
         }

         return false;
      }));
   }

   public static int method_5(Item var0) {
      return method_7((Predicate<ItemStack>)(var1 -> var1.getItem().equals(var0)));
   }

   public static int method_7(Predicate<ItemStack> var0) {
      for (int var1 = 0; var1 < 9; var1++) {
         ItemStack var2 = field_4219.player.getInventory().getStack(var1);
         if (var0.test(var2)) {
            return var1;
         }
      }

      return -1;
   }

   public static int method_29(Predicate<ItemStack> var0) {
      int var1 = 0;

      for (int var2 = 40; var2 >= 0; var2--) {
         ItemStack var3 = field_4219.player.getInventory().getStack(var2);
         if (var0.test(var3)) {
            var1 += var3.getCount();
         }
      }

      for (int var4 = 0; var4 <= 3; var4++) {
         ItemStack var5 = (ItemStack)field_4219.player.playerScreenHandler.getCraftingInput().getHeldStacks().get(var4);
         if (var0.test(var5)) {
            var1 += var5.getCount();
         }
      }

      return var1;
   }

   public static Hand method_7(Item var0) {
      if (field_4219.player.getOffHandStack().getItem() == var0) {
         return Hand.OFF_HAND;
      } else {
         return field_4219.player.getMainHandStack().getItem() == var0 ? Hand.MAIN_HAND : null;
      }
   }

   public static float method_2(PlayerEntity var0) {
      float var1 = Float.intBitsToFloat(1120403456);

      for (ItemStack var3 : var0.getInventory().armor) {
         float var4 = method_29(var3);
         if (var4 < var1) {
            var1 = var4;
         }
      }

      return var1;
   }

   public static float method_29(ItemStack var0) {
      float var1 = ((float)var0.getMaxDamage() - (float)var0.getDamage()) / (float)var0.getMaxDamage();
      float var2 = Float.intBitsToFloat(1065353216) - var1;
      return (float)(100 - (int)(var2 * Float.intBitsToFloat(1120403456)));
   }

   public static int method_30(int var0) {
      return var0 > -1 && var0 < 9 ? 36 + var0 : var0;
   }

   public static boolean method_161() {
      for (int var0 = 36; var0 >= 0; var0--) {
         ItemStack var1 = field_4219.player.getInventory().getStack(var0);
         if (var1.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public static boolean method_162() {
      for (int var0 = 0; var0 < 9; var0++) {
         ItemStack var1 = field_4219.player.getInventory().getStack(var0);
         if (var1.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public static void method_16(int var0) {
      if (var0 != -1) {
         field_4219.player.getInventory().selectedSlot = var0;
         ((DuckInteractionManager)field_4219.interactionManager).sync();
      }
   }

   public static boolean method_2(EquipmentSlot var0, ItemStack var1) {
      if (var0 == null) {
         return false;
      } else {
         return var1.getItem() instanceof ArmorItem var2 ? var2.getSlotType() == var0 : var1.getItem() instanceof ElytraItem && var0 == EquipmentSlot.CHEST;
      }
   }

   public static EquipmentSlot method_31(int var0) {
      return switch (var0) {
         case 36 -> EquipmentSlot.FEET;
         case 37 -> EquipmentSlot.LEGS;
         case 38 -> EquipmentSlot.CHEST;
         case 39 -> EquipmentSlot.HEAD;
         default -> null;
      };
   }

   public static int method_29(BlockPos var0, boolean var1) {
      double var2 = Double.longBitsToDouble(-4616189618054758400L);
      int var4 = -1;
      BlockState var5 = field_4219.world.getBlockState(var0);

      for (int var6 = 0; var6 < (!var1 ? 36 : 9); var6++) {
         ItemStack var7 = field_4219.player.getInventory().getStack(var6);
         if (!field_405.isToggled() || ItemSaverModule.method_101(var7)) {
            double var8 = (double)var7.getMiningSpeedMultiplier(var5);
            if (var8 > var2) {
               var2 = var8;
               var4 = var6;
            } else if (var6 == field_4219.player.getInventory().selectedSlot && var8 == var2) {
               var4 = var6;
            }
         }
      }

      return var4;
   }

   public static boolean method_29(Item var0) {
      if (var0 instanceof BlockItem var1 && var1.getBlock() instanceof ShulkerBoxBlock) {
         return true;
      }

      return false;
   }
}
