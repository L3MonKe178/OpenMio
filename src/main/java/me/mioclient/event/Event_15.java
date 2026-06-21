package me.mioclient.event;

import me.mioclient.enum_.Class_1176;
import me.mioclient.internal.Event;
import net.minecraft.entity.player.PlayerEntity;

public class Event_15 extends Event {
   public final PlayerEntity field_3733;
   public final int field_3734;
   public final Class_1176 field_3735;

   public Event_15(PlayerEntity var1, int var2, Class_1176 var3) {
      super();
      this.field_3733 = var1;
      this.field_3734 = var2;
      this.field_3735 = var3;
   }

   public PlayerEntity method_1048() {
      return this.field_3733;
   }

   public int method_1049() {
      return this.field_3734;
   }

   public Class_1176 method_1050() {
      return this.field_3735;
   }

   public String getName() {
      return this.field_3733.getGameProfile().getName();
   }
}
