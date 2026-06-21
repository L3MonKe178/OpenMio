package me.mioclient.module.player;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0776;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0396;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import nick.Settings;

public class AutoEatModule extends Module {
   public Setting<Integer> field_3068;
   public Setting<Integer> field_3069;
   public Setting<Boolean> field_3070;
   public Setting<Boolean> field_3071;
   public Setting<Class_0776> field_3072;
   public Setting<Boolean> field_3073;
   public Setting<Boolean> field_3074;
   public Setting<Boolean> field_3075;
   public int field_3076;
   public boolean field_3077;
   public int field_3078;

   public AutoEatModule() {
      super("AutoEat", "Eats your food for you.", Category.PLAYER);
      Settings.initialize(this);
      this.field_3070.method_334();
   }

   @Override
   public void onToggle() {
      this.method_84(false);
      this.field_3076 = -1;
      this.field_3078 = -1;
   }

   @Override
   public void onDisable() {
      field_4219.options.useKey.setPressed(false);
      Hub.field_2630.method_9(this);
   }

   @Subscribe
   public void method_5(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         Hub.field_2630.method_9(this);
         if (!this.field_3071.getValue() && this.field_3077 && !field_4219.player.getMainHandStack().contains(DataComponentTypes.FOOD)) {
            field_4219.interactionManager.stopUsingItem(field_4219.player);
            this.method_84(false);
         } else {
            int var2 = this.method_891();
            if ((var2 == -1 || !field_4219.player.getInventory().getStack(var2).isOf(Items.CHORUS_FRUIT)) && this.method_893()) {
               int var3 = Class_0136.method_9(Items.CHORUS_FRUIT);
               if (var3 != -1) {
                  var2 = -1;
                  this.field_3078 = var3;
                  this.method_78(this.field_3078);
               }
            }

            if (this.method_894() && !this.method_893()) {
               if (this.field_3077) {
                  field_4219.interactionManager.stopUsingItem(field_4219.player);
                  this.method_84(false);
                  if (this.field_3071.getValue() && this.field_3073.getValue() && this.field_3076 != -1) {
                     Class_0136.method_16(this.field_3076);
                  }

                  if (this.field_3078 != -1) {
                     this.method_78(this.field_3078);
                     this.field_3078 = -1;
                  }

                  if (this.field_3075.getValue() && this.field_3074.getValue()) {
                     this.disable();
                  }

                  this.field_3076 = -1;
               }
            } else {
               boolean var5 = var2 == 40;
               if (this.field_3071.getValue() && !var5 && var2 != -1 && field_4219.player.getInventory().selectedSlot != var2) {
                  this.field_3076 = field_4219.player.getInventory().selectedSlot;
                  Class_0136.method_16(var2);
               }

               ItemStack var4 = field_4219.player.getMainHandStack();
               if (!field_4219.player.isUsingItem() && (var5 || var4.contains(DataComponentTypes.FOOD))) {
                  field_4219.interactionManager.interactItem(field_4219.player, var5 ? Hand.OFF_HAND : Hand.MAIN_HAND);
                  this.method_84(true);
               }

               if (this.field_3070.getValue() && field_4219.player.isUsingItem()) {
                  Hub.field_2630.method_2(this);
               }
            }
         }
      }
   }

   public int method_891() {
      double var1 = Double.longBitsToDouble(-4616189618054758400L);
      int var3 = -1;

      for (int var4 = 0; var4 < 10; var4++) {
         if (var4 == 9) {
            var4 = 40;
         }

         ItemStack var5 = field_4219.player.getInventory().getStack(var4);
         FoodComponent var6 = (FoodComponent)var5.get(DataComponentTypes.FOOD);
         if (var6 != null && (var6.canAlwaysEat() || field_4219.player.getHungerManager().getFoodLevel() < 20)) {
            double var7 = this.field_3072.getValue().method_2(var6);
            if (var7 > var1 || var7 == var1 && var4 == field_4219.player.getInventory().selectedSlot) {
               var1 = var7;
               var3 = var4;
            }

            if (var5.getItem() == Items.CHORUS_FRUIT && this.method_893()) {
               var3 = var4;
               break;
            }
         }
      }

      return var3;
   }

   public boolean method_892() {
      return this.field_3077;
   }

   public boolean method_893() {
      if (!this.field_3074.getValue()) {
         return false;
      } else {
         PlayerEntity var1 = null;

         for (PlayerEntity var3 : field_4219.world.getPlayers()) {
            if (var3.distanceTo(field_4219.player) <= Float.intBitsToFloat(1094713344) && !Hub.field_2603.method_30(var3) && var3 != field_4219.player) {
               var1 = var3;
               break;
            }
         }

         return var1 != null && field_4219.player.age <= 50;
      }
   }

   public boolean method_894() {
      return (this.field_3069.getValue() == -1 || field_4219.player.getHungerManager().getFoodLevel() > this.field_3069.getValue())
         && (this.field_3068.getValue() == 0 || Class_0396.method_76() > (float)this.field_3068.getValue().intValue());
   }

   public void method_84(boolean var1) {
      if (this.field_3077 != var1) {
         this.field_3077 = var1;
      }
   }

   public void method_78(int var1) {
      field_4219.interactionManager
         .clickSlot(field_4219.player.currentScreenHandler.syncId, var1, field_4219.player.getInventory().selectedSlot, SlotActionType.SWAP, field_4219.player);
   }
}
