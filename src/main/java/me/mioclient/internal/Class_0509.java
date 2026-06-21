package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public abstract class Class_0509 implements MioAPI {
   public final Timer field_1616 = new Timer();

   public Class_0509() {
      super();
   }

   public abstract boolean method_206();

   public void method_142() {
      if (this.method_539()) {
         this.field_1616.reset();
      }
   }

   public boolean method_538() {
      return this.method_206() && !this.field_1616.method_9(750L);
   }

   public boolean method_539() {
      synchronized (field_4219.world.getEntities()) {
         for (Entity var3 : field_4219.world.getEntities()) {
            if (this.method_3(var3) && this.method_29(var3.getPos())) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean method_29(Vec3d var1) {
      if (var1.squaredDistanceTo(field_4219.player.getPos()) > Double.longBitsToDouble(4639270566145032192L)) {
         return false;
      } else {
         for (int var2 = 4; var2 >= 0; var2--) {
            Box var3 = Hub.field_2612.method_2(field_4219.player, var2);
            double var4 = (double)Class_1323.method_2(var1, field_4219.player, var3, Double.longBitsToDouble(4618441417868443648L), true, null, null);
            if (var4 >= (double)Class_0396.method_76()) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean method_3(Entity var1) {
      return var1 instanceof EndCrystalEntity && field_4219.player.squaredDistanceTo(var1) <= Double.longBitsToDouble(4639270566145032192L);
   }
}
