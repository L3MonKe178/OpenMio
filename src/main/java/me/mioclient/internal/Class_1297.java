package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_43;
import me.mioclient.event.Subscribe;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.math.Vec3d;

public final class Class_1297 implements Class_1309 {
   public double field_608;
   public double field_609;
   public double field_2311;
   public boolean field_2314;
   public boolean field_4193;
   public boolean field_4194;
   public boolean field_2838;
   public int field_1014;
   public int field_4195;
   public float[] field_4196;

   public Class_1297() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_4194 = this.field_4193;
      this.field_4193 = field_4219.player.isFallFlying();
      if (!field_4219.player.isOnGround() && !this.field_2314) {
         this.field_1014 = 0;
         this.field_4195++;
      } else {
         this.field_4195 = 0;
         this.field_1014++;
      }
   }

   @Subscribe
   public void method_9(Event_43 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket var2) {
         this.field_608 = var2.getX(field_4219.player.getX());
         this.field_609 = var2.getY(field_4219.player.getY());
         this.field_2311 = var2.getZ(field_4219.player.getZ());
         this.field_2314 = var2.isOnGround();
         this.field_4196 = new float[]{var2.getYaw(field_4219.player.getYaw()), var2.getPitch(field_4219.player.getPitch())};
      }

      if (var1.method_127() instanceof ClientCommandC2SPacket var4) {
         if (var4.getMode() == Mode.PRESS_SHIFT_KEY) {
            this.field_2838 = true;
         } else if (var4.getMode() == Mode.RELEASE_SHIFT_KEY) {
            this.field_2838 = false;
         }
      }
   }

   public void method_22(Runnable var1) {
      boolean var2 = Hub.field_2615.method_1160();
      if (var2) {
         Class_1261.method_2(new ClientCommandC2SPacket(field_4219.player, Mode.RELEASE_SHIFT_KEY));
         var1.run();
         Class_1261.method_2(new ClientCommandC2SPacket(field_4219.player, Mode.PRESS_SHIFT_KEY));
      } else {
         var1.run();
      }
   }

   public void method_3(Runnable var1) {
      boolean var2 = !Hub.field_2615.method_1160();
      if (var2) {
         Class_1261.method_2(new ClientCommandC2SPacket(field_4219.player, Mode.PRESS_SHIFT_KEY));
         var1.run();
         Class_1261.method_2(new ClientCommandC2SPacket(field_4219.player, Mode.RELEASE_SHIFT_KEY));
      } else {
         var1.run();
      }
   }

   public double method_380() {
      return this.field_608;
   }

   public double method_395() {
      return this.field_609;
   }

   public double method_396() {
      return this.field_2311;
   }

   public Vec3d method_384() {
      return new Vec3d(this.field_608, this.field_609, this.field_2311);
   }

   public boolean method_707() {
      return this.field_2314;
   }

   public boolean method_1159() {
      return this.field_4194;
   }

   public boolean method_1160() {
      return this.field_2838;
   }

   public int method_1161() {
      return this.field_1014;
   }

   public int method_1162() {
      return this.field_4195;
   }

   public float[] method_1163() {
      return this.field_4196;
   }
}
