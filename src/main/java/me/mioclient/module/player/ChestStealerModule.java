package me.mioclient.module.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0087;
import me.mioclient.enum_.Class_0710;
import me.mioclient.enum_.Class_0766;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_42;
import me.mioclient.event.Event_61;
import me.mioclient.event.Event_62;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0358;
import me.mioclient.internal.Class_0539;
import me.mioclient.internal.Class_0584;
import me.mioclient.internal.Class_1009;
import me.mioclient.internal.Class_1069;
import me.mioclient.internal.Class_1308;
import me.mioclient.mixin.ducks.DuckHandledScreen;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.misc.ChestSearchBarModule;
import me.mioclient.record.Class_0362;
import me.mioclient.record.Class_0501;
import me.mioclient.record.Class_0510;
import me.mioclient.setting.Setting;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import nick.Settings;

public class ChestStealerModule extends Module {
   public static ChestSearchBarModule field_3025 = Hub.field_2595.method_78(ChestSearchBarModule.class);
   public Setting<Set<Item>> field_3200;
   public Setting<Set<String>> field_3201;
   public Setting<Class_0766> field_3202;
   public Setting<Class_0710> field_3203;
   public Setting<Class_0087> field_3204;
   public Setting<Boolean> field_3205;
   public Setting<Boolean> field_3206;
   public Setting<Boolean> field_3207;
   public Setting<Boolean> field_3208;
   public Setting<Integer> field_3209;
   public Setting<Integer> field_3210;
   public Setting<Integer> field_3211;
   public final List<Class_0584> field_3212;
   public final Class_0242 field_3213;
   public final Class_0242 field_3214;
   public Class_0766 field_3215;
   public boolean field_947;
   public Slot field_3216;

   public ChestStealerModule() {
      super("ChestStealer", "Steals items from the storages you open.", Category.PLAYER, "stealer");
      Settings.initialize(this);
      this.field_3212 = Collections.synchronizedList(new ArrayList<>());
      this.field_3213 = new Class_0242();
      this.field_3214 = new Class_0242();
      this.field_3208.method_9(this.field_3212::clear);
      this.field_3202.method_5(var1 -> !this.field_3207.getValue());
      ScreenEvents.AFTER_INIT.register(new Class_1308(this));
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if ((field_4219.currentScreen instanceof GenericContainerScreen || field_4219.currentScreen instanceof ShulkerBoxScreen)
         && this.field_3213.method_9((long)this.field_3209.getValue().intValue())
         && (!this.field_3207.getValue() || this.field_3215 != null)
         && this.field_3214.method_9((long)this.field_3210.getValue().intValue())
         && !(field_4219.currentScreen instanceof Class_0539)) {
         Class_0766 var2 = this.field_3207.getValue() ? this.field_3215 : this.field_3202.getValue();
         ScreenHandler var3 = field_4219.player.currentScreenHandler;
         int var4 = 0;
         int var5 = 27;
         int var6 = var3.syncId;
         if (field_4219.currentScreen instanceof GenericContainerScreen var7) {
            var5 = 9 * ((GenericContainerScreenHandler)var7.getScreenHandler()).getRows();
         }

         DuckHandledScreen var23 = (DuckHandledScreen)field_4219.currentScreen;
         Map var24 = this.method_493(var5);
         if (var2 == Class_0766.FILL) {
            var4 = var5;
            var5 = 36;
         }

         int var9 = 0;

         for (int var10 = var4; var10 < var4 + var5; var10++) {
            ItemStack var11 = var3.getSlot(var10).getStack();
            if (!var11.isEmpty() && (!this.field_3205.getValue() || !field_3025.isToggled() || field_3025.match(var11, this.field_3206.getValue()))) {
               boolean var12 = field_4219.currentScreen instanceof ShulkerBoxScreen;
               if (this.field_3204.getValue() == Class_0087.ITEM
                  ? this.field_3203.getValue().method_2(var11.getItem(), this.field_3200)
                  : this.field_3203.getValue().method_2(var11.getName().getString().toLowerCase(Locale.ROOT), this.field_3201)) {
                  List var13 = (List)var24.get(new Class_0510(var11.getItem(), var11.getComponents()));
                  switch (var2) {
                     case DROP:
                        field_4219.interactionManager.clickSlot(var6, var10, 1, SlotActionType.THROW, field_4219.player);
                        break;
                     case FILL:
                     case STEAL:
                        if (var12 && Class_0358.method_29(var11.getItem())) {
                           continue;
                        }

                        this.field_3216 = null;
                        this.field_947 = true;
                        field_4219.interactionManager.clickSlot(var6, var10, 0, SlotActionType.QUICK_MOVE, field_4219.player);
                        this.field_947 = false;
                        if (this.field_3216 != null) {
                           this.method_2(var3.getSlot(var10), this.field_3216, this.field_3216.getStack());
                        }
                        break;
                     case REFILL:
                        if (var13 == null) {
                           continue;
                        }

                        this.method_9(var10, var13);
                        var9 = 999;
                        break;
                     case REKIT:
                        if (var13 != null) {
                           this.method_9(var10, var13);
                           var9 = 999;
                        } else {
                           Class_1009 var14 = Hub.field_2625.method_487();
                           if (var14 == null) {
                              continue;
                           }

                           Slot var15 = var3.getSlot(var10);
                           boolean var16 = false;

                           for (Entry var18 : var14.method_906().entrySet()) {
                              if (((Class_0501)var18.getValue()).method_16(var11)) {
                                 boolean var19 = (Integer)var18.getKey() < 9;
                                 int var20 = var5 + (var19 ? (Integer)var18.getKey() + 27 : (Integer)var18.getKey() - 9);
                                 ItemStack var21 = var3.getSlot(var20).getStack();
                                 if (!((Class_0501)var18.getValue()).method_16(var21)
                                    && (!Class_0358.method_29(var21.getItem()) || !(var3 instanceof ShulkerBoxScreenHandler))) {
                                    Slot var22 = var3.getSlot(var20);
                                    this.method_2(var15, var22, var15.getStack());
                                    if (var19) {
                                       field_4219.interactionManager.clickSlot(var6, var10, (Integer)var18.getKey(), SlotActionType.SWAP, field_4219.player);
                                    } else {
                                       field_4219.interactionManager.clickSlot(var6, var10, 0, SlotActionType.PICKUP, field_4219.player);
                                       field_4219.interactionManager.clickSlot(var6, var20, 0, SlotActionType.PICKUP, field_4219.player);
                                       field_4219.interactionManager.clickSlot(var6, var10, 0, SlotActionType.PICKUP, field_4219.player);
                                    }

                                    var9 = 999;
                                    var16 = true;
                                    break;
                                 }
                              }
                           }

                           if (!var16) {
                              continue;
                           }
                        }
                  }

                  this.field_3213.reset();
                  if (++var9 >= this.field_3211.getValue()) {
                     break;
                  }
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_61 var1) {
      this.field_3214.reset();
      this.field_3212.clear();
   }

   @Subscribe
   public void onRender(Event_42 var1) {
      synchronized (this.field_3212) {
         this.field_3212.removeIf(Class_0584::method_604);

         for (Class_0584 var4 : this.field_3212) {
            var4.method_5(var1.method_881());
         }
      }
   }

   @Subscribe
   public void method_2(Event_62 var1) {
      if (this.field_947 && !var1.method_1006().getStack().isEmpty()) {
         this.field_3216 = var1.method_1006();
      }
   }

   public void method_9(int var1, List<Class_1069> var2) {
      Slot var3 = field_4219.player.currentScreenHandler.getSlot(var1);
      if (var3.getStack().isStackable()) {
         int var4 = field_4219.player.currentScreenHandler.syncId;
         int var5 = var3.getStack().getCount();
         int var6 = 0;
         field_4219.interactionManager.clickSlot(var4, var1, 0, SlotActionType.PICKUP, field_4219.player);

         for (Class_1069 var8 : var2) {
            var3 = field_4219.player.currentScreenHandler.getSlot(var8.method_34());
            field_4219.interactionManager.clickSlot(var4, var8.method_34(), 0, SlotActionType.PICKUP, field_4219.player);
            int var9 = var8.method_954() - var8.method_955();
            var8.method_494(var8.method_955() + var5);
            var5 -= var9;
            this.method_2(field_4219.player.currentScreenHandler.getSlot(var1), var3, var3.getStack().copyWithCount(var9));
            if (field_4219.player.currentScreenHandler.getCursorStack().isEmpty() || ++var6 >= this.field_3211.getValue()) {
               break;
            }
         }

         if (!field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
            field_4219.interactionManager.clickSlot(var4, var1, 0, SlotActionType.PICKUP, field_4219.player);
         }
      }
   }

   public boolean method_157(ItemStack var1) {
      return !(field_4219.player.currentScreenHandler instanceof ShulkerBoxScreenHandler) ? true : !Class_0358.method_29(var1.getItem());
   }

   public void method_2(Slot var1, Slot var2, ItemStack var3) {
      DuckHandledScreen var4 = (DuckHandledScreen)field_4219.currentScreen;

      assert var4 != null;

      if (this.field_3208.getValue()) {
         this.field_3212
            .add(new Class_0584(var3, new Class_0362(var4.getX() + var1.x, var4.getY() + var1.y), new Class_0362(var4.getX() + var2.x, var4.getY() + var2.y)));
      }
   }

   public Map<Class_0510, List<Class_1069>> method_493(int var1) {
      HashMap var2 = new HashMap();

      for (int var3 = 0; var3 < 36; var3++) {
         if (var3 + var1 < field_4219.player.currentScreenHandler.slots.size()) {
            ItemStack var4 = field_4219.player.currentScreenHandler.getSlot(var1 + var3).getStack();
            if (var4.getCount() < var4.getMaxCount() && !var4.isEmpty() && var4.isStackable()) {
               ComponentMap var5 = var4.getComponents();
               Class_0510 var6 = new Class_0510(var4.getItem(), var5);
               var2.putIfAbsent(var6, new ArrayList());
               int var7 = var4.getMaxCount();
               ((List)var2.get(var6)).add(new Class_1069(var1 + var3, var7, var4.getCount()));
            }
         }
      }

      return var2;
   }

   public void method_2(Class_0766 var1) {
      this.field_3215 = var1;
   }

   public Class_0766 method_933() {
      return this.field_3215;
   }
}
