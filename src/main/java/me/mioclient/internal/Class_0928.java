package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_34;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_43;
import me.mioclient.event.Event_47;
import me.mioclient.event.Event_59;
import me.mioclient.event.Subscribe;
import me.mioclient.module.exploit.XCarryModule;
import me.mioclient.record.Class_0081;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.CloseScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;

public final class Class_0928 implements Class_1309 {
   public static final XCarryModule field_1340 = Hub.field_2595.method_78(XCarryModule.class);
   public final Map<Integer, Class_0081> field_2909 = Collections.synchronizedMap(new HashMap<>());
   public final Class_0242 field_2910 = new Class_0242();
   public volatile boolean field_111;
   public volatile boolean field_2500;
   public volatile boolean field_478;
   public boolean field_2911;
   public int field_682 = -1;

   public Class_0928() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      synchronized (this.field_2909) {
         ArrayList var3 = new ArrayList();
         this.field_2909.forEach((var1x, var2) -> {
            if (System.currentTimeMillis() >= var2.method_118()) {
               var3.add(var1x);
            }
         });

         for (Integer var5 : (Iterable<Integer>)(Iterable<?>)(var3)) {
            Class_0081 var6 = this.field_2909.remove(var5);
            field_4219.player.networkHandler.onScreenHandlerSlotUpdate(var6.method_117());
         }
      }

      this.field_2911 = false;
      if (!field_1340.isToggled()
         && !(field_4219.currentScreen instanceof HandledScreen)
         && field_4219.player.currentScreenHandler.getCursorStack().isEmpty()
         && this.field_2910.method_9(50L)
         && this.field_111
         && System.currentTimeMillis() - Hub.field_2602.method_987() <= 2500L) {
         this.close();
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_2(Event_10 var1) {
      if (!var1.method_464()) {
         if (var1.method_127() instanceof ClickSlotC2SPacket) {
            this.field_111 = true;
            this.field_2910.reset();
            if (!this.field_2500 || !this.field_478) {
               field_4220.method_36(new Event_47(PreType.PRE));
            }
         }

         if (var1.method_127() instanceof CloseHandledScreenC2SPacket || var1.method_127() instanceof DisconnectS2CPacket) {
            this.field_111 = false;
         }

         if (var1.method_127() instanceof UpdateSelectedSlotC2SPacket var2) {
            if (var2.getSelectedSlot() != this.field_682) {
               field_4220.method_36(new Event_34(var2.getSelectedSlot()));
               this.field_682 = var2.getSelectedSlot();
            } else if (Class_0485.method_513()) {
               var1.method_463();
            }
         }

         if (var1.method_127() instanceof DisconnectS2CPacket) {
            this.field_682 = -1;
         }
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof CloseScreenS2CPacket var2) {
         this.field_111 = false;
      }

      if (var1.method_127() instanceof InventoryS2CPacket) {
         this.field_2909.clear();
      }

      if (var1.method_127() instanceof ScreenHandlerSlotUpdateS2CPacket var4 && Class_0485.method_513()) {
         if (var4.getSlot() == 36 + field_4219.player.getInventory().selectedSlot) {
            return;
         }

         if (var4.getSlot() < 36 || var4.getSlot() > 44) {
            return;
         }

         if (!this.method_2(var4.getStack(), var4.getSlot())) {
            this.field_2909.remove(var4.getSlot());
            return;
         }

         this.field_2909.put(var4.getSlot(), new Class_0081(var4, System.currentTimeMillis() + 500L));
         var1.method_463();
      }

      if (var1.method_127() instanceof EntityStatusS2CPacket var5 && var5.getStatus() == 35 && var5.getEntity(field_4219.world) == field_4219.player) {
         this.field_2911 = true;
      }
   }

   @Subscribe
   public void method_2(Event_43 var1) {
      if (var1.method_127() instanceof ClickSlotC2SPacket && (!this.field_2500 || !this.field_478)) {
         field_4220.method_36(new Event_47(PreType.POST));
         this.field_478 = true;
      }
   }

   @Subscribe
   public void method_2(Event_59 var1) {
      if (!field_4219.isInSingleplayer()) {
         Class_1261.method_14(field_4219.player.getInventory().selectedSlot);
         var1.method_463();
      }
   }

   public boolean method_2(ItemStack var1, int var2) {
      for (int var3 = 0; var3 < 9; var3++) {
         if (var2 - 36 != var3 && ItemStack.areEqual(field_4219.player.getInventory().getStack(var3), var1)) {
            return true;
         }
      }

      return false;
   }

   public void close() {
      field_4219.player.networkHandler.sendPacket(new CloseHandledScreenC2SPacket(field_4219.player.currentScreenHandler.syncId));
      this.field_111 = false;
   }

   public void method_154(boolean var1) {
      if (this.field_2500 != var1) {
         this.field_2500 = var1;
         this.field_478 = false;
      }
   }

   public boolean method_842() {
      return this.field_2911;
   }

   public int method_843() {
      return this.field_682;
   }
}
