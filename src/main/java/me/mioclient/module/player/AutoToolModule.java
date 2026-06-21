package me.mioclient.module.player;

import me.mioclient.api.Class_1226;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import nick.Settings;

public class AutoToolModule extends Module {
   public Setting<Boolean> field_1100;
   public int field_1071;
   public boolean field_1101;

   public AutoToolModule() {
      super("AutoTool", "Equips the best tool to use on a block for you.", Category.PLAYER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (((Class_1226)field_4219.interactionManager).getCurrentBreakingBlock() != null && field_4219.options.attackKey.isPressed()) {
         this.field_1101 = true;
         int var2 = field_4219.player.getInventory().selectedSlot;
         BlockState var3 = field_4219.world.getBlockState(((Class_1226)field_4219.interactionManager).getCurrentBreakingBlock());
         double var4 = (double)field_4219.player.getInventory().getStack(var2).getMiningSpeedMultiplier(var3);

         for (int var6 = 0; var6 < 9; var6++) {
            double var7 = (double)field_4219.player.getInventory().getStack(var6).getMiningSpeedMultiplier(var3);
            if (var7 > var4) {
               var4 = var7;
               var2 = var6;
            }
         }

         Class_0136.method_16(var2);
      } else if (this.field_1100.getValue()) {
         if (this.field_1101) {
            field_4219.player.getInventory().selectedSlot = this.field_1071;
            this.field_1101 = false;
         } else {
            this.field_1071 = field_4219.player.getInventory().selectedSlot;
         }
      }
   }
}
