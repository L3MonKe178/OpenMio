package me.mioclient.module.player;

import me.mioclient.api.Class_1226;
import me.mioclient.event.Event_58;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemSaverModule extends Module {
   public ItemSaverModule() {
      super("ItemSaver", "Prevents your tools from breaking.", Category.PLAYER);
   }

   @Subscribe
   public void method_2(Event_58 var1) {
      int var2 = field_4219.player.getInventory().selectedSlot;
      ItemStack var3 = field_4219.player.getInventory().getStack(var2);
      if (!method_101(var3)) {
         if (((Class_1226)field_4219.interactionManager).getCurrentBreakingBlock() != null && field_4219.options.attackKey.isPressed()) {
            PlayerUtil.method_16(var2 == 8 ? var2 - 1 : var2 + 1);
            var1.method_463();
         }
      }
   }

   public static boolean method_101(ItemStack var0) {
      return !var0.isEmpty() && var0.isDamageable() && !(var0.getItem() instanceof ArmorItem) && !var0.isOf(Items.ELYTRA)
         ? Class_1323.method_5(var0) > 10
         : true;
   }
}
