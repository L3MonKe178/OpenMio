package me.mioclient.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0358;
import me.mioclient.module.abstract_.AbstractModule_37;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class Class_0576 {
   public final ItemStack field_1815;
   public final ItemStack field_1816;
   public static final AbstractModule_37 tooltips = Hub.field_2595.method_78(AbstractModule_37.class);

   public Class_0576(ItemStack var1, ItemStack var2) {
      super();
      this.field_1815 = var1;
      this.field_1816 = var2;
   }

   public static Class_0576 method_460(ItemStack var0) {
      if (!Class_0358.method_29(var0.getItem())) {
         return null;
      } else {
         ContainerComponent var1 = (ContainerComponent)var0.get(DataComponentTypes.CONTAINER);
         if (var1 == null) {
            return null;
         } else {
            HashMap<Item, Integer> var2 = new HashMap<>();
            List<ItemStack> var3 = var1.stream().toList();

            for (int var4 = 0; var4 < var3.size(); var4++) {
               Item var5 = ((ItemStack)var3.get(var4)).getItem();
               var2.put(var5, 1 + var2.getOrDefault(var5, 0));
            }

            if (var2.isEmpty()) {
               return null;
            } else {
               Entry<Item, Integer> var6 = var2.entrySet().stream().max(Entry.comparingByValue()).orElse(null);
               return var6 == null ? null : new Class_0576(var0, ((Item)var6.getKey()).getDefaultStack());
            }
         }
      }
   }

   public ItemStack method_594() {
      return this.field_1815;
   }

   public ItemStack method_595() {
      return this.field_1816;
   }
}
