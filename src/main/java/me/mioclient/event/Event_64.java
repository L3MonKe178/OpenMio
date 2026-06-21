package me.mioclient.event;

import me.mioclient.api.Class_1309;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public final class Event_64 extends Event_9 {
   public Event_64(Vec3d var1) {
      super(var1, null);
   }

   public static boolean method_33(Object var0) {
      return Class_1309.field_4219.player == null ? false : var0 instanceof Entity && Class_1309.field_4219.player.getVehicle() == var0;
   }
}
