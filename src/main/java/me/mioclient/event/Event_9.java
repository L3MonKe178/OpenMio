package me.mioclient.event;

import me.mioclient.internal.Event;
import me.mioclient.internal.Class_1334;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class Event_9 extends Event {
   public final Vec3d field_3852;
   public final Vec3d field_3853;
   public MovementType field_3854;

   public Event_9(Vec3d var1, MovementType var2) {
      super();
      this.field_3852 = new Vec3d(var1.x, var1.y, var1.z);
      this.field_3853 = var1;
      this.field_3854 = var2;
   }

   public Vec3d method_1066() {
      return this.field_3853;
   }

   public void method_4(Vec3d var1) {
      Class_1334.method_2(this.field_3853, var1.x, var1.y, var1.z);
   }

   public void method_7(double var1, double var3) {
      this.method_4(new Vec3d(var1, this.method_395(), var3));
   }

   public double method_380() {
      return this.field_3853.x;
   }

   public double method_395() {
      return this.field_3853.y;
   }

   public void setY(double var1) {
      this.method_4(new Vec3d(this.method_380(), var1, this.method_396()));
   }

   public double method_396() {
      return this.field_3853.z;
   }

   public MovementType method_1067() {
      return this.field_3854;
   }

   public void method_2(MovementType var1) {
      this.field_3854 = var1;
   }

   public Vec3d method_1068() {
      return this.field_3852;
   }
}
