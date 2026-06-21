package me.mioclient.module.player;

import java.util.Set;
import me.mioclient.enum_.Class_0710;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0539;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import nick.Settings;

public class InventoryCleanerModule extends Module {
   public Setting<Set<Item>> field_1273;
   public Setting<Class_0710> field_1274;
   public Setting<Integer> field_1275;
   public Setting<Integer> field_1276;
   public Setting<Boolean> field_1277;
   public Setting<Boolean> field_1278;
   public final Timer field_1279;

   public InventoryCleanerModule() {
      super("InventoryCleaner", "Gets rid of the selected items in your inventory.", Category.PLAYER);
      Settings.initialize(this);
      this.field_1279 = new Timer();
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_2(Event_1 var1) {
      int var2 = 0;
      if (this.field_1279.method_9((long)(this.field_1275.getValue() != null ? this.field_1275.getValue().intValue() : 0))) {
         Screen var3 = field_4219.currentScreen;
         boolean var4 = var3 instanceof InventoryScreen
            || var3 instanceof GenericContainerScreen
            || var3 instanceof ShulkerBoxScreen && !(var3 instanceof Class_0539);
         if (!this.field_1277.getValue() || var4) {
            int var5 = field_4219.player.currentScreenHandler.slots.size();
            if (field_4219.player.currentScreenHandler instanceof PlayerScreenHandler) {
               var5--;
            }

            for (Slot var7 : field_4219.player.currentScreenHandler.slots) {
               ItemStack var8 = var7.getStack();
               if (!var8.isEmpty()
                  && (this.field_1274.getValue() != null ? this.field_1274.getValue().method_2(var8.getItem(), this.field_1273) : false)
                  && (var7.id < var5 - 9 || !this.field_1278.getValue())) {
                  field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var7.id, 1, SlotActionType.THROW, field_4219.player);
                  this.field_1279.reset();
                  if (++var2 >= this.field_1276.getValue()) {
                     break;
                  }
               }
            }
         }
      }
   }
}
