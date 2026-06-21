package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.client.input.Input;

public class Event_16 extends Event {
   public final Input field_714;
   public final float field_715;

   public Event_16(Input var1, float var2) {
      super();
      this.field_714 = var1;
      this.field_715 = var2;
   }

   public Input method_276() {
      return this.field_714;
   }

   public float method_277() {
      return this.field_715;
   }

   public boolean method_278() {
      return this.field_715 != -1.0F;
   }

   public void reset() {
      this.field_714.pressingForward = this.field_714.pressingBack = this.field_714.pressingLeft = this.field_714.pressingRight = this.field_714.jumping = this.field_714
         .sneaking = false;
      this.field_714.movementForward = 0.0F;
      this.field_714.movementSideways = 0.0F;
   }
}
