package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0577;
import me.mioclient.module.combat.AutoArmorModule;
import net.minecraft.screen.slot.SlotActionType;

public class Class_1264 {
   public boolean field_3965 = false;
   public boolean field_3966;
   public final Class_0577 field_3967;
   public final int field_3968;

   public Class_1264(AutoArmorModule var1, Class_0577 var2, int var3) {
      super();
      this.field_3967 = var2;
      this.field_3968 = var3;
   }

   public void method_222(boolean var1) {
      this.field_3966 = var1;
   }

   public boolean method_497() {
      return this.field_3965;
   }

   public void method_273() {
      if (this.field_3966) {
         MioAPI.field_4219
            .interactionManager
            .clickSlot(
               MioAPI.field_4219.player.currentScreenHandler.syncId,
               8 - this.field_3967.field_1822,
               0,
               SlotActionType.QUICK_MOVE,
               MioAPI.field_4219.player
            );
         this.field_3965 = true;
      } else {
         int var1 = PlayerUtil.method_30(this.field_3968);
         Hub.field_2611.method_154(true);
         PlayerUtil.method_5(var1, 8 - this.field_3967.method_596());
         Hub.field_2611.method_154(false);
         this.field_3965 = true;
      }
   }
}
