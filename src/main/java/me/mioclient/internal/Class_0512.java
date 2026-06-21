package me.mioclient.internal;

import me.mioclient.api.Class_0957;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public final class Class_0512 {
   public final Vec3d field_1625;
   public final Vec3d field_1626;
   public final ShapeType field_1627;
   public final FluidHandling field_1628;
   public final ShapeContext field_1629;
   public final Class_0957 field_1630;

   public Class_0512(Vec3d var1, Vec3d var2, ShapeType var3, FluidHandling var4, Entity var5, Class_0957 var6) {
      super();
      this.field_1625 = var1;
      this.field_1626 = var2;
      this.field_1627 = var3;
      this.field_1628 = var4;
      this.field_1629 = ShapeContext.of(var5);
      this.field_1630 = var6;
   }

   public Vec3d method_543() {
      return this.field_1625;
   }

   public Vec3d method_544() {
      return this.field_1626;
   }

   public ShapeType method_545() {
      return this.field_1627;
   }

   public FluidHandling method_546() {
      return this.field_1628;
   }

   public ShapeContext method_547() {
      return this.field_1629;
   }

   public Class_0957 method_548() {
      return this.field_1630;
   }

   public VoxelShape method_2(BlockState var1, BlockView var2, BlockPos var3) {
      return this.field_1627.get(var1, var2, var3, this.field_1629);
   }

   public VoxelShape method_2(FluidState var1, BlockView var2, BlockPos var3) {
      return this.field_1628.handled(var1) ? var1.getShape(var2, var3) : VoxelShapes.empty();
   }
}
