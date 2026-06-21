package me.mioclient.event;

import me.mioclient.internal.Class_0605;
import net.minecraft.util.math.Vec3d;

public class Event_31 extends Class_0605 {
   public double field_608;
   public double field_609;
   public double field_2311;

   public Event_31(double var1, double var3, double var5) {
      super();
      this.field_608 = var1;
      this.field_609 = var3;
      this.field_2311 = var5;
   }

   public void method_38(Vec3d var1) {
      this.field_608 = var1.x;
      this.field_609 = var1.y;
      this.field_2311 = var1.z;
   }

   public Vec3d method_733() {
      return new Vec3d(this.field_608, this.field_609, this.field_2311);
   }
}
