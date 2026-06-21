package me.mioclient.internal;

import me.mioclient.api.Class_1019;
import me.mioclient.record.Class_0635;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.SlotActionType;

public class Class_1259 implements Class_1019 {
   public Class_1259() {
      super();
   }

   @Override
   public void method_2(Class_0635 var1) {
      field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var1.method_646(), 40, SlotActionType.SWAP, field_4219.player);
   }

   @Override
   public void method_9(Class_0635 var1) {
      field_4219.interactionManager.clickSlot(field_4219.player.currentScreenHandler.syncId, var1.method_646(), 40, SlotActionType.SWAP, field_4219.player);
   }

   @Override
   public Class_0635 method_31(Item var1) {
      return new Class_0635(Class_0136.method_9(var1), 40);
   }
}
