package me.mioclient.internal;

import me.mioclient.api.Class_0957;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class Class_0533 {
   public final Vec3d field_1680;
   public final Vec3d field_1681;
   public ShapeType field_1627 = ShapeType.COLLIDER;
   public FluidHandling field_1628 = FluidHandling.NONE;
   public Entity field_581 = MinecraftClient.getInstance().player;
   public Class_0957 field_1630 = Class_0957.field_2968;

   public Class_0533(Vec3d var1, Vec3d var2) {
      super();
      this.field_1680 = var1;
      this.field_1681 = var2;
   }

   public Class_0533 method_2(ShapeType var1) {
      this.field_1627 = var1;
      return this;
   }

   public Class_0533 method_2(FluidHandling var1) {
      this.field_1628 = var1;
      return this;
   }

   public Class_0533 method_22(Entity var1) {
      this.field_581 = var1;
      return this;
   }

   public Class_0533 method_2(Class_0957 var1) {
      this.field_1630 = var1;
      return this;
   }

   public Class_0533 method_2(Class_0957... var1) {
      this.field_1630 = Class_0957.method_9(var1);
      return this;
   }

   public Class_0512 method_565() {
      return new Class_0512(this.field_1680, this.field_1681, this.field_1627, this.field_1628, this.field_581, this.field_1630);
   }
}
