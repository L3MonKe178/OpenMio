package me.mioclient.module.player;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0710;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0464;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.exploit.XCarryModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.collection.DefaultedList;
import nick.Settings;

public class ReplenishModule extends Module {
   public static XCarryModule field_1340 = Hub.field_2595.method_78(XCarryModule.class);
   public static AutoCrystalModule field_219 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public Setting<Set<Item>> field_1341;
   public Setting<Class_0710> field_1342;
   public Setting<Integer> field_1343;
   public Setting<Integer> field_1344;
   public Setting<Boolean> field_1345;
   public Setting<Boolean> field_1346;
   public Setting<Boolean> field_1347;
   public Setting<Boolean> field_1348;
   public Setting<Boolean> field_1349;
   public final List<Item> field_1350;
   public final Class_0242 field_1351;

   public ReplenishModule() {
      super("Replenish", "Replenishes your hotbar.", Category.PLAYER);
      Settings.initialize(this);
      this.field_1350 = DefaultedList.ofSize(9, Items.AIR);
      this.field_1351 = new Class_0242();
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_1351.method_9((long)this.field_1343.getValue().intValue()) && field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
         if ((!Class_0464.method_363() || !this.field_1348.getValue()) && (!(field_4219.player.getVelocity().getY() > 0.0) || !this.field_1349.getValue())) {
            boolean var2 = field_4219.currentScreen instanceof HandledScreen;

            for (int var3 = 0; var3 < 9; var3++) {
               ItemStack var4 = field_4219.player.getInventory().getStack(var3);
               Item var5 = this.field_1350.get(var3);
               if (this.field_1346.getValue() && var4.isEmpty() && var5 != Items.AIR && !var2 && this.method_2(var5, var3)) {
                  this.field_1351.reset();
                  return;
               }

               this.field_1350.set(var3, var4.getItem());
            }

            if (!var2 || field_4219.currentScreen instanceof InventoryScreen) {
               for (int var6 = 0; var6 < 9; var6++) {
                  if (this.method_465(var6)) {
                     this.field_1351.reset();
                     return;
                  }
               }
            }
         }
      }
   }

   public boolean method_2(Item var1, int var2) {
      if (!this.field_1342.getValue().method_2(var1, this.field_1341.getValue())) {
         return false;
      } else {
         boolean var3 = field_219.isToggled() && field_219.field_4106.getValue();
         int var4 = Class_0136.method_2(
            var1, var1x -> var1x != field_4219.player.getOffHandStack() && (!var3 || var1x.getItem() != Items.TOTEM_OF_UNDYING), true
         );
         if (var4 == -1 && var1 == Items.TOTEM_OF_UNDYING && !var3) {
            var4 = Class_0136.method_2((Predicate<ItemStack>)(var1x -> this.method_35(var1x.getItem()) && var1x != field_4219.player.getOffHandStack()), true);
         }

         if (var4 < 36 && var4 != -1) {
            if (this.method_113(var2)) {
               field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var4, 0, SlotActionType.QUICK_MOVE, field_4219.player);
            } else {
               Class_0136.method_5(var4, Class_0136.method_30(var2));
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean method_113(int var1) {
      for (int var2 = 0; var2 < 9; var2++) {
         if (var2 != var1) {
            ItemStack var3 = field_4219.player.getInventory().getStack(var2);
            if (var3.isEmpty()) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean method_35(Item var1) {
      return var1 == Items.ENCHANTED_GOLDEN_APPLE || var1 == Items.GOLDEN_APPLE;
   }

   public boolean method_465(int var1) {
      ItemStack var2 = field_4219.player.getInventory().getStack(var1);
      if (!this.field_1342.getValue().method_2(var2.getItem(), this.field_1341.getValue())) {
         return false;
      } else {
         float var3 = (float)this.field_1344.getValue().intValue() / Float.intBitsToFloat(1115684864);
         if (!var2.isEmpty() && var2.getItem() != Items.AIR && var2.isStackable() && !((float)var2.getCount() / (float)var2.getMaxCount() >= var3)) {
            int var4 = -1;
            int var5 = 0;

            for (int var6 = 9; var6 < 36; var6++) {
               ItemStack var7 = field_4219.player.getInventory().getStack(var6);
               boolean var8 = ItemStack.areItemsAndComponentsEqual(var7, var2);
               if (!var7.isEmpty() && var8 && var7.getCount() > var5) {
                  var4 = var6;
                  var5 = var7.getCount();
               }
            }

            if (field_1340.isToggled() && !(field_4219.currentScreen instanceof InventoryScreen)) {
               for (int var9 = 0; var9 <= 3; var9++) {
                  ItemStack var11 = (ItemStack)field_4219.player.playerScreenHandler.getCraftingInput().getHeldStacks().get(var9);
                  boolean var12 = ItemStack.areItemsAndComponentsEqual(var11, var2);
                  if (!var11.isEmpty() && var12 && var11.getCount() > var5) {
                     var4 = var9 + 1;
                     var5 = var11.getCount();
                  }
               }
            }

            if (var4 == -1) {
               return false;
            } else {
               int var10 = field_4219.player.currentScreenHandler.syncId;
               if (this.field_1345.getValue() && var5 != 1 && var2.getMaxCount() - var2.getCount() > var5) {
                  Hub.field_2611.method_154(true);
                  field_4219.interactionManager.clickSlot(var10, var4, 0, SlotActionType.PICKUP, field_4219.player);
                  field_4219.interactionManager.clickSlot(var10, var4, 1, SlotActionType.PICKUP, field_4219.player);
                  field_4219.interactionManager.clickSlot(var10, Class_0136.method_30(var1), 0, SlotActionType.PICKUP, field_4219.player);
                  Hub.field_2611.method_154(false);
               } else if (Class_0136.method_162()) {
                  field_4219.interactionManager.clickSlot(var10, var4, 0, SlotActionType.QUICK_MOVE, field_4219.player);
               } else {
                  Class_0136.method_5(var4, Class_0136.method_30(var1));
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }
}
