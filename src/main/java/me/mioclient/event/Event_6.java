package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

public class Event_6 extends Event {
   public MatrixStack field_1577;
   public VertexConsumer field_3915;
   public BlockPos field_355;
   public BlockState field_3320;

   public Event_6(MatrixStack var1, VertexConsumer var2, BlockPos var3, BlockState var4) {
      super();
      this.field_1577 = var1;
      this.field_3915 = var2;
      this.field_355 = var3;
      this.field_3320 = var4;
   }

   public MatrixStack method_1089() {
      return this.field_1577;
   }

   public void method_29(MatrixStack var1) {
      this.field_1577 = var1;
   }

   public VertexConsumer method_1090() {
      return this.field_3915;
   }

   public void method_2(VertexConsumer var1) {
      this.field_3915 = var1;
   }

   public BlockPos method_111() {
      return this.field_355;
   }

   public void method_425(BlockPos var1) {
      this.field_355 = var1;
   }

   public BlockState method_958() {
      return this.field_3320;
   }

   public void method_9(BlockState var1) {
      this.field_3320 = var1;
   }
}
