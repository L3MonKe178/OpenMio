package me.mioclient.internal;

import me.mioclient.api.Class_1019;
import me.mioclient.record.Class_0635;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class Class_0867 implements Class_1019 {
   public Class_0867() {
      super();
   }

   @Override
   public void method_2(Class_0635 var1) {
      PlayerUtil.method_5(var1.method_645(), var1.method_646());
   }

   @Override
   public void method_9(Class_0635 var1) {
      PlayerUtil.method_5(var1.method_646(), var1.method_645());
   }

   @Override
   public Class_0635 method_31(Item var1) {
      if (!Class_0985.method_38(var1)) {
         return Class_0635.field_2010;
      } else {
         int var2 = 6;
         if (var1 instanceof ArmorItem var3) {
            var2 = 8 - var3.getSlotType().getEntitySlotId();
         }

         int var4 = PlayerUtil.method_9(var1);
         return new Class_0635(var4, var2);
      }
   }
}
