package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import net.minecraft.screen.slot.SlotActionType;

public enum NoneType implements Nameable {
   NONE("None", SlotActionType.QUICK_MOVE),
   THROW("Throw", SlotActionType.THROW),
   PICKUP("Pickup", SlotActionType.PICKUP);

   public final String field_3962;
   public final SlotActionType field_3963;

    NoneType(String var3, SlotActionType var4) {
      this.field_3962 = var3;
      this.field_3963 = var4;
   }

   @Override
   public String getName() {
      return this.field_3962;
   }

   public SlotActionType method_1103() {
      return this.field_3963;
   }
}
