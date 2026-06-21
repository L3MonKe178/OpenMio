package me.mioclient.internal;

import me.mioclient.api.Class_1019;
import me.mioclient.record.Class_0635;
import net.minecraft.item.Item;

public class Class_0891 implements Class_1019 {
   public Class_0891() {
      super();
   }

   @Override
   public void method_2(Class_0635 var1) {
      Class_0136.method_78(var1.method_645());
   }

   @Override
   public void method_9(Class_0635 var1) {
      Class_0136.method_78(var1.method_646());
   }

   @Override
   public Class_0635 method_31(Item var1) {
      int var2 = Class_0136.method_5(var1);
      return new Class_0635(var2, field_4219.player.getInventory().selectedSlot);
   }
}
