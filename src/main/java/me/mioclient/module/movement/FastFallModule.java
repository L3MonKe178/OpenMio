package me.mioclient.module.movement;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0753;
import me.mioclient.enum_.StrafeType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_0981;
import me.mioclient.internal.Class_1334;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.mixin.ducks.DuckClientPlayerEntity;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.exploit.PhaseModule;
import me.mioclient.setting.Setting;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import nick.Settings;

public class FastFallModule extends Module {
   public static SpeedModule field_1001 = Hub.field_2595.method_78(SpeedModule.class);
   public static HoleSnapModule field_4398 = Hub.field_2595.method_78(HoleSnapModule.class);
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static PhaseModule field_4399 = Hub.field_2595.method_78(PhaseModule.class);
   public Setting<Class_0753> field_4400;
   public Setting<Float> field_4401;
   public Setting<Float> field_4402;
   public Setting<Boolean> field_4403;
   public Setting<Boolean> field_4404;
   public Setting<Boolean> field_4405;
   public boolean field_4406;
   public boolean field_4407;
   public boolean field_4408;
   public boolean field_4409;
   public boolean field_264;
   public boolean field_2197;
   public double field_4410;
   public double field_4411;
   public final Timer field_4412;

   public FastFallModule() {
      super("FastFall", "Allows you to get in holes with ease!", Category.MOVEMENT, "reversestep");
      Settings.initialize(this);
      this.field_4412 = new Timer();
   }

   @Override
   public void onToggle() {
      this.field_4406 = false;
      this.field_2197 = false;
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      if (this.field_4405.getValue() && Hub.field_2605.method_224() && this.field_2197) {
         this.disable();
      } else if (this.method_1187() && (this.field_4409 || !this.field_4404.getValue())) {
         if (!field_4219.player.isOnGround()) {
            if (this.field_4412.method_9(500L)) {
               this.field_4408 = !this.field_4408;
               this.field_4412.reset();
            }

            if (field_4219.player.getVelocity().y < 0.0 && this.field_4407 && this.field_4408) {
               Hub.field_2617.method_2(this, Float.intBitsToFloat(1073741824));
               this.field_2197 = true;
            } else {
               this.method_1188();
            }
         } else {
            this.field_4407 = true;
            Hub.field_2617.method_38(this);
            if (Hub.field_2615.method_1161() > 3) {
               this.field_4412.reset();
               this.field_4408 = true;
            }
         }
      } else {
         this.method_1188();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      boolean var2 = Class_0464.method_363() || field_4398.isToggled() && field_4398.method_403() != null;
      if ((this.field_4409 || !this.field_4404.getValue()) && var2) {
         this.field_264 = false;
         if (((DuckClientPlayerEntity)field_4219.player).lastOnGround()
            && field_4219.player.getVelocity().getY() <= 0.0
            && this.method_1186()
            && !this.method_724()) {
            double var3 = field_4219.player.getY();
            Box var5 = field_4219.player.getBoundingBox().stretch(0.0, (double)(-this.field_4401.getValue()), 0.0).withMaxY(var3);
            if (field_4219.world.isSpaceEmpty(field_4219.player, var5) && !field_4219.world.containsFluid(var5)) {
               return;
            }

            Iterable<net.minecraft.util.shape.VoxelShape> var6 = field_4219.world.getBlockCollisions(field_4219.player, var5);
            AtomicReference<Double> var7 = new AtomicReference<>((double)field_4219.world.getBottomY());
            var6.forEach(var1x -> var7.set(Math.max((Double)var7.get(), var1x.getMax(Axis.Y))));
            if ((Double)var7.get() < field_4219.player.getY()) {
               double var8 = (Double)var7.get();
               Vec3d var10 = field_4219.player.getVelocity();
               BlockPos var11 = BlockPos.ofFloored(field_4219.player.getX(), var8, field_4219.player.getZ());
               boolean var12 = field_4219.world.isAir(var11.add(new Vec3i((int)var10.x, -1, (int)var10.z)))
                  || !Hub.field_2602.method_985().method_9(25L)
                  || this.field_4400.getValue() == Class_0753.PLAIN;
               if (field_4219.player.getY() - var8 > Double.longBitsToDouble(4611686018427387904L) || var12) {
                  var8 = field_4219.player.getY() - Double.longBitsToDouble(4611686018427387904L);
                  this.field_264 = true;
                  float var13 = this.field_4400.getValue() == Class_0753.PLAIN ? this.field_4402.getValue() : Float.intBitsToFloat(1065353216);
                  if (var13 != 0.0F) {
                     field_4219.player.setVelocity(var10.withAxis(Axis.Y, (double)(-var13)));
                  }
               }

               if (!var12) {
                  field_4219.player.setPosition(field_4219.player.getX(), var8, field_4219.player.getZ());
               }

               field_4399.method_362();
               this.field_2197 = true;
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_9 var1) {
      this.field_4410 = (double)Class_0981.method_887();
      this.field_4411 = (double)Class_0981.method_886();
      this.field_4409 = Hub.field_2605.method_221(field_4219.player.getBlockPos().down((int)this.field_4411));
      if (this.field_4409 || !this.field_4404.getValue()) {
         double var2 = Double.longBitsToDouble(4599075939470750515L);
         boolean var4 = field_4398.isToggled() && field_4398.field_1179.method_9(500L) && field_4398.method_403() != null;
         if (this.method_724() && this.method_1186() && this.method_1184() && field_4219.player.isOnGround() || this.field_264 && !var4 && this.method_1185()) {
            Class_1334.method_2(var1.method_1066(), var1.method_380() * var2, var1.method_395(), var1.method_396() * var2);
         }
      }
   }

   public boolean method_1184() {
      Box var1 = field_4219.player.getBoundingBox();
      Vec3d var2 = var1.getCenter();
      double var3 = var1.minX;
      double var5 = var1.minZ;
      double var7 = var1.maxX;
      double var9 = var1.maxZ;
      HashMap var11 = new HashMap();
      var11.put(var2, new Vec3d(var2.x, var2.y - Double.longBitsToDouble(4607182418800017408L), var2.z));
      var11.put(new Vec3d(var3, var2.y, var5), new Vec3d(var3, var2.y - Double.longBitsToDouble(4607182418800017408L), var5));
      var11.put(new Vec3d(var7, var2.y, var5), new Vec3d(var7, var2.y - Double.longBitsToDouble(4607182418800017408L), var5));
      var11.put(new Vec3d(var3, var2.y, var9), new Vec3d(var3, var2.y - Double.longBitsToDouble(4607182418800017408L), var9));
      var11.put(new Vec3d(var7, var2.y, var9), new Vec3d(var7, var2.y - Double.longBitsToDouble(4607182418800017408L), var9));

      for (Vec3d var13 : (Iterable<Vec3d>)(Iterable<?>)(var11.keySet())) {
         BlockHitResult var14 = field_4219.world
            .raycast(new RaycastContext(var13, (Vec3d)var11.get(var13), ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player));
         if (var14 != null && var14.getType() == Type.BLOCK) {
            return false;
         }
      }

      if (!this.method_1185()) {
         return false;
      } else {
         BlockState var15 = field_4219.world
            .getBlockState(
               BlockPos.ofFloored(field_4219.player.getX(), field_4219.player.getY() - Double.longBitsToDouble(4607182418800017408L), field_4219.player.getZ())
            );
         return var15 == null || var15.getBlock() == Blocks.AIR;
      }
   }

   public boolean method_1185() {
      Vec3d var1 = field_4219.player.getVelocity();
      double var2 = var1.x;
      double var4 = var1.z;
      var2 = Math.abs(var4) > Math.abs(var2) ? 0.0 : Math.signum(var2);
      var4 = Math.abs(var2) > Math.abs(var4) ? 0.0 : Math.signum(var4);
      BlockState var6 = field_4219.world
         .getBlockState(
            BlockPos.ofFloored(
               field_4219.player.getX() + var2, field_4219.player.getY() - Double.longBitsToDouble(4607182418800017408L), field_4219.player.getZ() + var4
            )
         );
      return var6 != null ? ((DuckAbstractBlock)var6.getBlock()).isCollidable() : true;
   }

   public boolean method_1186() {
      return field_1001.isToggled() && field_1001.field_2191.getValue() != StrafeType.ON_GROUND && field_1001.field_2191.getValue() != StrafeType.VANILLA
         ? false
         : !field_4219.player.isSneaking()
            && !field_4219.player.isFallFlying()
            && !field_4219.player.isInsideWall()
            && (!Class_0382.method_29(field_4219.player) || !this.field_4403.getValue())
            && Hub.field_2602.method_984().method_9(500L)
            && !(field_4219.world.getBlockState(BlockPos.ofFloored(field_4219.player.getPos())).getBlock() instanceof BedBlock);
   }

   public boolean method_724() {
      return this.field_4400.getValue() == Class_0753.STRICT;
   }

   public boolean method_1187() {
      return this.method_1186()
         && this.method_724()
         && this.field_4410 != 0.0
         && this.field_4411 != 0.0
         && this.field_4411 <= (double)(this.field_4401.getValue() != null ? this.field_4401.getValue().floatValue() : 0.0f);
   }

   public void method_1188() {
      if (this.method_724()) {
         this.field_4407 = false;
         Hub.field_2617.method_38(this);
      }
   }
}
