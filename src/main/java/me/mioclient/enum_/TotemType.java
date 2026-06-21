package me.mioclient.enum_;

import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0136;
import me.mioclient.module.combat.OffhandModule;
import me.mioclient.module.exploit.XCarryModule;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public enum TotemType implements Class_1309, Class_0013 {
   TOTEM("Totem", Items.TOTEM_OF_UNDYING),
   CRYSTAL("Crystal", Items.END_CRYSTAL),
   GAPPLE("Gapple", Items.GOLDEN_APPLE) {
      @Override
      public Item method_98(boolean var1) {
         return var1 ? super.method_98(true) : Items.ENCHANTED_GOLDEN_APPLE;
      }
   },
   CUSTOM("Custom", Items.SHIELD) {
      @Override
      public Item method_98(boolean var1) {
         Set var2 = TotemType.field_451.field_1974.getValue();
         return (Item)var2.stream().findAny().orElse(null);
      }
   };

   public static final XCarryModule field_450 = Hub.field_2595.method_78(XCarryModule.class);
   public static final OffhandModule field_451 = Hub.field_2595.method_78(OffhandModule.class);
   public final String field_452;
   public final Item field_453;

    TotemType(String var3, Item var4) {
      this.field_452 = var3;
      this.field_453 = var4;
   }

   public int method_186(boolean var1) {
      Item var2 = this.method_98(var1);
      if (var2 != null && !field_4219.player.getOffHandStack().isOf(var2)) {
         if (field_4219.currentScreen instanceof ShulkerBoxScreen || field_4219.currentScreen instanceof GenericContainerScreen) {
            for (Slot var4 : Class_1309.field_4219.player.currentScreenHandler.slots) {
               if (var4.getStack().isOf(var2)) {
                  return var4.id;
               }
            }
         }

         return Class_0136.method_2(var2, field_450.isToggled());
      } else {
         return -1;
      }
   }

   public Item method_98(boolean var1) {
      return this.field_453;
   }

   @Override
   public String getName() {
      return this.field_452;
   }
}
