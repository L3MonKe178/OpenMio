package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public final class Event_58 extends Event {
   public final BlockPos field_3484;
   public final Direction field_3485;

   public Event_58(BlockPos var1, Direction var2) {
      super();
      this.field_3484 = var1;
      this.field_3485 = var2;
   }

   public BlockPos method_111() {
      return this.field_3484;
   }

   public Direction method_1010() {
      return this.field_3485;
   }
}
