package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;

public class Event_35 extends Event {
   public static final Event_35 field_3318 = new Event_35();
   public VoxelShape field_3319;
   public BlockPos field_355;
   public BlockState field_3320;

   public Event_35() {
      super();
   }

   public static Event_35 method_2(VoxelShape var0, BlockPos var1, BlockState var2) {
      field_3318.method_9(var0);
      field_3318.method_425(var1);
      field_3318.method_9(var2);
      return field_3318;
   }

   public void method_9(VoxelShape var1) {
      this.field_3319 = var1;
   }

   public void method_425(BlockPos var1) {
      this.field_355 = var1;
   }

   public void method_9(BlockState var1) {
      this.field_3320 = var1;
   }

   public VoxelShape method_957() {
      return this.field_3319;
   }

   public BlockPos method_111() {
      return this.field_355;
   }

   public BlockState method_958() {
      return this.field_3320;
   }
}
