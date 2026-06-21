package me.mioclient.module.misc;

import me.mioclient.event.Event_42;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_0982;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.item.ItemStack;
import nick.Settings;

public class ChestSearchBarModule extends Module {
   public Setting<Boolean> field_1197;

   public ChestSearchBarModule() {
      super("ChestSearchBar", "Highlights items in chests and shulker boxes.", Category.MISC);
      Settings.initialize(this);
      Class_0982.field_3024.init();
      this.setDrawn(false);
   }

   @Subscribe
   public void onRender(Event_42 var1) {
      Class_0982.field_3024.onDrawGui(var1.method_881(), var1.method_562(), var1.method_10(), var1.method_59(), var1.method_60(), Class_0838.method_776());
   }

   public boolean match(ItemStack var1, boolean var2) {
      if (Class_0982.field_3026 == null) {
         return true;
      } else {
         String var3 = Class_0982.field_3026.getText();
         if (var3.isEmpty()) {
            return true;
         } else {
            return var2 && !Class_0982.isFull(var1, var3) ? false : Class_0982.namesMatch(var1, var3);
         }
      }
   }
}
