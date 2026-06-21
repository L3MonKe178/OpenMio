package me.mioclient.module.player;

import me.mioclient.event.Event_1;
import me.mioclient.event.Event_17;
import me.mioclient.event.Subscribe;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import nick.Settings;

public class FastPlaceModule extends Module {
   public Setting<Integer> field_3298;
   public Setting<Boolean> field_3299;
   public Setting<Boolean> field_3300;
   public Setting<Boolean> field_3301;
   public Setting<Boolean> field_3302;
   public Setting<Boolean> field_3303;
   public Setting<Boolean> field_3304;

   public FastPlaceModule() {
      super("FastPlace", "Removes the right-click delay for some items.", Category.PLAYER);
      Settings.initialize(this);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      DuckMinecraftClient var2 = (DuckMinecraftClient)field_4219;
      if (!field_4219.player.getMainHandStack().contains(DataComponentTypes.FOOD)) {
         if (this.field_3304.getValue() && var2.getItemUseCooldown() == 0) {
            var2.interact();
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         Item var2 = field_4219.player.getMainHandStack().getItem();
         if (var2 instanceof ExperienceBottleItem && this.field_3299.getValue()
            || var2 instanceof FireworkRocketItem && this.field_3300.getValue()
            || var2 instanceof BlockItem && this.field_3301.getValue()
            || var2 == Items.ENDER_CHEST && this.field_3302.getValue()
            || this.field_3303.getValue()) {
            ((DuckMinecraftClient)field_4219).setItemUseCooldown(Math.min(((DuckMinecraftClient)field_4219).getItemUseCooldown(), this.field_3298.getValue()));
         }
      }
   }
}
