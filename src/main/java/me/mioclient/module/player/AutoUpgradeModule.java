package me.mioclient.module.player;

import me.mioclient.enum_.Class_1168;
import me.mioclient.event.Event_17;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import nick.Settings;

public class AutoUpgradeModule extends Module {
   public static final Item field_3322 = Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE;
   public static final int field_3323 = 0;
   public static final int field_3324 = 1;
   public static final int field_3325 = 2;
   public static final int field_3326 = 3;
   public Setting<Class_1168> field_3327;
   public Setting<Integer> field_3328;
   public Setting<Boolean> field_3329;
   public final Timer field_3330;

   public AutoUpgradeModule() {
      super("AutoUpgrade", "Automatically upgrades diamond items to netherite.", Category.PLAYER);
      Settings.initialize(this);
      this.field_3330 = new Timer();
   }

   @Subscribe
   public void method_9(Event_17 var1) {
      ScreenHandler var2 = field_4219.player.currentScreenHandler;
      if (field_4219.currentScreen instanceof SmithingScreen) {
         if (this.field_3330.method_9((long)(this.field_3328.getValue() != null ? this.field_3328.getValue().intValue() : 0))) {
            if (this.method_2(var2, field_3322) > 1 || !this.field_3329.getValue()) {
               if (!var2.getSlot(3).getStack().isEmpty()) {
                  field_4219.interactionManager.clickSlot(var2.syncId, 3, 0, SlotActionType.QUICK_MOVE, field_4219.player);
                  this.field_3330.reset();
               } else {
                  ItemStack var3 = var2.getSlot(0).getStack();
                  if (var3.isEmpty() || var3.isOf(field_3322) && var3.getCount() == 1) {
                     for (int var6 = 4; var6 < 40; var6++) {
                        if (var2.getSlot(var6).getStack().isOf(field_3322)) {
                           PlayerUtil.method_5(var6, 0);
                           this.field_3330.reset();
                           break;
                        }
                     }
                  } else if (var2.getSlot(1).getStack().isEmpty()) {
                     int var5 = this.method_2(var2);
                     if (var5 != -1) {
                        PlayerUtil.method_5(var5, 1);
                        this.field_3330.reset();
                     }
                  } else {
                     if (var2.getSlot(2).getStack().isEmpty()) {
                        for (int var4 = 4; var4 < 40; var4++) {
                           if (var2.getSlot(var4).getStack().isOf(Items.NETHERITE_INGOT)) {
                              PlayerUtil.method_5(var4, 2);
                              this.field_3330.reset();
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public int method_2(ScreenHandler var1, Item var2) {
      int var3 = 0;

      for (Slot var5 : var1.slots) {
         if (var5.getStack().isOf(var2)) {
            var3 += var5.getStack().getCount();
         }
      }

      return var3;
   }

   public int method_2(ScreenHandler var1) {
      boolean var2 = this.field_3327.getValue() == Class_1168.TOOLS;
      boolean var3 = this.field_3327.getValue() == Class_1168.ARMOR;

      for (int var4 = 4; var4 < 40; var4++) {
         ItemStack var5 = var1.getSlot(var4).getStack();
         if (!var2
            && var5.getItem() instanceof ArmorItem var6
            && this.method_2((Ingredient)((ArmorMaterial)var6.getMaterial().value()).repairIngredient().get())) {
            return var4;
         }

         if (!var3 && var5.getItem() instanceof ToolItem var8 && this.method_2(var8.getMaterial().getRepairIngredient())) {
            return var4;
         }
      }

      return -1;
   }

   public boolean method_2(Ingredient var1) {
      for (ItemStack var5 : var1.getMatchingStacks()) {
         if (var5.isOf(Items.DIAMOND)) {
            return true;
         }
      }

      return false;
   }
}
