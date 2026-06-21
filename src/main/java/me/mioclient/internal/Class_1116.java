package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.event.Event_4;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

public final class Class_1116 {
   public static final long field_3455 = 750L;
   public final SpeedMineModule field_3456;
   public final Class_0242 field_3457 = new Class_0242();
   public boolean field_3458;
   public BlockPos field_355;

   public Class_1116(SpeedMineModule var1) {
      super();
      this.field_3456 = var1;
   }

   public void method_7(Event_4 var1) {
      if (var1.method_127() instanceof BlockUpdateS2CPacket var2 && var2.getState().isAir() && var2.getPos().equals(this.field_355)) {
         this.reset();
      }

      if (var1.method_127() instanceof ChunkDeltaUpdateS2CPacket var4) {
         var4.visitUpdates((var1x, var2x) -> {
            if (var1x.equals(this.field_355) && var2x.isAir()) {
               this.reset();
            }
         });
      }
   }

   public void method_994(BlockPos var1) {
      if (!this.field_3458) {
         this.field_3458 = true;
         this.field_355 = var1;
         this.field_3457.reset();
      }
   }

   public void method_142() {
      if (this.field_3458) {
         if (!Class_1225.method_3(this.field_355) || Class_1225.method_14(this.field_355)) {
            this.reset();
         }

         if (this.field_355 != null && !this.field_355.equals(this.field_3456.method_1120())) {
            this.reset();
         }
      }
   }

   public boolean method_995() {
      if (this.field_3458 && this.field_355 != null) {
         long var1 = (long)(
            Math.ceil((double)((float)Hub.field_2602.method_983() * Float.intBitsToFloat(1097859072) / Float.intBitsToFloat(1112014848)))
               * Double.longBitsToDouble(4632233691727265792L)
         );
         var1 = Math.max(var1, 750L);
         if (this.field_3456.field_3991.getValue()) {
            var1 = (long)((float)var1 / Hub.field_2602.method_990());
         }

         var1 = (long)((float)var1 + this.field_3456.field_3996.getValue());
         return this.field_3457.method_9(var1);
      } else {
         return false;
      }
   }

   public void reset() {
      this.field_3458 = false;
      this.field_355 = null;
   }
}
