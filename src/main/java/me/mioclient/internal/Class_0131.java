package me.mioclient.internal;

import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_10;
import me.mioclient.mixin.ducks.DuckVehicleMoveC2SPacket;
import me.mioclient.module.movement.EntityControlModule;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.BoatPaddleStateC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public final class Class_0131 implements MioAPI {
   public final Timer field_393 = new Timer();
   public final EntityControlModule field_394;
   public int field_395;
   public int field_396;
   public boolean field_397 = true;
   public Vec3d field_398;

   public Class_0131(EntityControlModule var1) {
      super();
      this.field_394 = var1;
   }

   public boolean method_155() {
      if (!field_4219.player.hasVehicle()) {
         return true;
      } else {
         long var1 = 1000L;
         if (this.field_395 >= 3) {
            var1 = 250L;
         }

         if (this.field_393.method_9(var1)) {
            this.method_156();
         }

         Vec3d var3 = field_4219.player.getVehicle().getPos();
         if (this.field_396 > 0) {
            this.field_396--;
         }

         boolean var4 = false;
         if (this.field_398 == null || var4 || Math.abs(var3.y - this.field_398.y) >= Double.longBitsToDouble(4625759767262920704L)) {
            this.method_156();
            this.field_398 = var3;
         }

         return this.field_397;
      }
   }

   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket || var1.method_127() instanceof BoatPaddleStateC2SPacket) {
         var1.method_463();
      }
   }

   public void method_2(Class_0542 var1) {
      this.field_397 = true;
      Entity var2 = field_4219.player.getVehicle();
      if (var2 != null) {
         Vec3d var3 = var1.method_384();
         var2.updatePosition(var3.getX(), var3.getY(), var3.getZ());
         this.method_157(0.0F);
         var1.method_463();
         this.field_396 = 2;
      }
   }

   public void method_156() {
      Entity var1 = field_4219.player.getVehicle();
      if (var1 != null) {
         if (this.field_398 != null && this.field_398.getY() < var1.getY()) {
            this.field_395++;
         } else {
            this.field_395 = 0;
         }

         this.method_157(Float.intBitsToFloat(1148846080));
         this.field_393.reset();
         this.field_397 = false;
      }
   }

   public void method_157(float var1) {
      Entity var2 = field_4219.player.getVehicle();
      if (var2 != null) {
         VehicleMoveC2SPacket var3 = new VehicleMoveC2SPacket(var2);
         ((DuckVehicleMoveC2SPacket)var3).setY(var3.getY() + (double)var1);
         PacketUtil.method_2(var3);
      }
   }

   public boolean method_158(int var1) {
      return this.field_395 >= var1;
   }
}
