package me.mioclient.module.player;

import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.mixin.ducks.DuckHandledScreen;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import nick.Settings;
import org.lwjgl.glfw.GLFW;

public class InventoryTweaksModule extends Module {
   public Setting<Boolean> field_678;
   public Setting<Boolean> field_679;
   public Setting<Boolean> field_680;
   public Setting<Boolean> field_681;
   public int field_682;

   public InventoryTweaksModule() {
      super("InventoryTweaks", "Modifies your inventory actions.", Category.PLAYER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_26 var1) {
      if (this.field_678.getValue()) {
         if (field_4219.currentScreen instanceof HandledScreen) {
            if (!this.field_679.getValue()
               || field_4219.currentScreen instanceof GenericContainerScreen
               || field_4219.currentScreen instanceof ShulkerBoxScreen) {
               if (field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
                  Slot var2 = ((DuckHandledScreen)field_4219.currentScreen).mio$getFocusedSlot();
                  if (var2 != null && var2.id != this.field_682 && !var2.getStack().isEmpty()) {
                     byte var3 = -1;
                     if (GLFW.glfwGetMouseButton(field_4219.getWindow().getHandle(), 0) == 1) {
                        var3 = 0;
                     }

                     if (GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 340) == 1 && var3 != -1) {
                        field_4219.interactionManager
                           .clickSlot(field_4219.player.currentScreenHandler.syncId, var2.id, var3, SlotActionType.QUICK_MOVE, field_4219.player);
                        this.field_682 = var2.id;
                     }
                  }
               }
            }
         }
      }
   }
}
