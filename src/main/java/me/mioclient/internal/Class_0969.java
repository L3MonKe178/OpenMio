package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0687;
import me.mioclient.api.Class_1309;
import me.mioclient.module.render.TrajectoriesModule;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ExperienceBottleEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.EggItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import org.joml.Quaterniond;
import org.joml.Vector3d;

public class Class_0969 implements Class_1309 {
   public static TrajectoriesModule field_2978 = Hub.field_2595.method_78(TrajectoriesModule.class);
   public static final HitResult field_2979 = new HitResult(Vec3d.ZERO) {
      public Type getType() {
         return Type.MISS;
      }
   };
   public static final Mutable field_2980 = new Mutable();
   public static Vec3d field_2981 = new Vec3d(0.0, 0.0, 0.0);
   public static Vec3d field_2982 = new Vec3d(0.0, 0.0, 0.0);
   public final Vector3d field_2983 = new Vector3d();
   public final Vector3d field_2984 = new Vector3d();
   public double field_2985;
   public double field_2986;
   public double field_2987;

   public Class_0969() {
      super();
   }

   public boolean method_2(Entity var1, ItemStack var2, double var3, boolean var5, double var6) {
      Item var8 = var2.getItem();
      if (var8 instanceof BowItem && field_2978.field_2367.getValue()) {
         double var11 = MathHelper.lerp(var6, (double)field_2978.method_711(), (double)BowItem.getPullProgress(field_4219.player.getItemUseTime()));
         if (var11 <= 0.0) {
            var11 = Double.longBitsToDouble(4607182418800017408L);
         }

         this.method_2(
            var1,
            0.0,
            var11 * Double.longBitsToDouble(4613937818241073152L),
            var3,
            Double.longBitsToDouble(4587366580546961408L),
            Double.longBitsToDouble(4603579539098121011L),
            var5,
            var6
         );
      } else if (var8 instanceof CrossbowItem && field_2978.field_2368.getValue()) {
         if (!CrossbowItem.isCharged(var2)) {
            return false;
         }

         ChargedProjectilesComponent var9 = (ChargedProjectilesComponent)var2.get(DataComponentTypes.CHARGED_PROJECTILES);
         if (var9 == null) {
            return false;
         }

         if (var9.contains(Items.FIREWORK_ROCKET)) {
            this.method_2(var1, 0.0, (double)CrossbowItem.getSpeed(var9), var3, 0.0, Double.longBitsToDouble(4603579539098121011L), var5, var6);
         } else {
            this.method_2(
               var1,
               0.0,
               (double)CrossbowItem.getSpeed(var9),
               var3,
               Double.longBitsToDouble(4587366580546961408L),
               Double.longBitsToDouble(4603579539098121011L),
               var5,
               var6
            );
         }
      } else if (var8 instanceof FishingRodItem && field_2978.field_2372.getValue()) {
         this.method_5(var1, var6);
      } else if (var8 instanceof TridentItem && field_2978.field_2369.getValue()) {
         this.method_2(
            var1,
            0.0,
            Double.longBitsToDouble(4612811918334230528L),
            var3,
            Double.longBitsToDouble(4587366580546961408L),
            Double.longBitsToDouble(4607092346807469998L),
            var5,
            var6
         );
      } else if ((!(var8 instanceof SnowballItem) && !(var8 instanceof EggItem) || !field_2978.field_2372.getValue())
         && (!(var8 instanceof EnderPearlItem) || !field_2978.field_2370.getValue())) {
         if (var8 instanceof ExperienceBottleItem && field_2978.field_2371.getValue()) {
            this.method_2(
               var1,
               Double.longBitsToDouble(-4597049319638433792L),
               Double.longBitsToDouble(4604480259023595110L),
               var3,
               Double.longBitsToDouble(4589708452245819884L),
               Double.longBitsToDouble(4605380978949069210L),
               var5,
               var6
            );
         } else {
            if (!(var8 instanceof ThrowablePotionItem) || !field_2978.field_2372.getValue()) {
               return false;
            }

            this.method_2(
               var1,
               Double.longBitsToDouble(-4597049319638433792L),
               Class_0245.field_688,
               var3,
               Double.longBitsToDouble(4587366580439587226L),
               Double.longBitsToDouble(4605380978949069210L),
               var5,
               var6
            );
         }
      } else {
         this.method_2(
            var1,
            0.0,
            Double.longBitsToDouble(4609434218613702656L),
            var3,
            Double.longBitsToDouble(4584304132692975288L),
            Double.longBitsToDouble(4605380978949069210L),
            var5,
            var6
         );
      }

      return true;
   }

   public void method_2(Entity var1, double var2, double var4, double var6, double var8, double var10, boolean var12, double var13) {
      this.field_2983
         .set(
            MathHelper.lerp(var13, var1.prevX, var1.getX()),
            MathHelper.lerp(var13, var1.prevY, var1.getY()) + (double)var1.getEyeHeight(var1.getPose()),
            MathHelper.lerp(var13, var1.prevZ, var1.getZ())
         );
      double var15 = MathHelper.lerp(var13, (double)var1.prevYaw, (double)var1.getYaw());
      double var17 = MathHelper.lerp(var13, (double)var1.prevPitch, (double)var1.getPitch());
      if (var1 == field_4219.player) {
         var15 = MathHelper.lerp(var13, (double)Hub.field_2598.method_518(), (double)Hub.field_2598.method_515());
         var17 = MathHelper.lerp(var13, (double)Hub.field_2598.method_519(), (double)Hub.field_2598.method_516());
      }

      double var19;
      double var21;
      double var23;
      if (var6 == 0.0) {
         var19 = -Math.sin(var15 * (double)Class_0245.field_690) * Math.cos(var17 * (double)Class_0245.field_690);
         var21 = -Math.sin((var17 + var2) * (double)Class_0245.field_690);
         var23 = Math.cos(var15 * (double)Class_0245.field_690) * Math.cos(var17 * (double)Class_0245.field_690);
      } else {
         Vec3d var25 = var1.getOppositeRotationVector(Float.intBitsToFloat(1065353216));
         Quaterniond var26 = new Quaterniond().setAngleAxis(var6, var25.x, var25.y, var25.z);
         Vec3d var27 = var1.getRotationVec(Float.intBitsToFloat(1065353216));
         Vector3d var28 = new Vector3d(var27.x, var27.y, var27.z);
         var28.rotate(var26);
         var19 = var28.x;
         var21 = var28.y;
         var23 = var28.z;
      }

      this.field_2984.set(var19, var21, var23).normalize().mul(var4);
      if (var12) {
         Vec3d var29 = var1.getVelocity();
         if (field_4219.player == var1) {
            this.field_2984
               .add(
                  -MathHelper.lerp(var13, field_2978.method_710().x, var29.x),
                  var1.isOnGround() ? 0.0 : MathHelper.lerp(var13, field_2978.method_710().y, var29.y),
                  -MathHelper.lerp(var13, field_2978.method_710().z, var29.z)
               );
         } else {
            this.field_2984.add(-var29.x, var1.isOnGround() ? 0.0 : var29.y, -var29.z);
         }
      }

      this.field_2985 = var8;
      this.field_2986 = Double.longBitsToDouble(4607092346807469998L);
      this.field_2987 = var10;
   }

   public boolean method_2(Entity var1, boolean var2, double var3) {
      if (!field_4219.world.isSpaceEmpty(var1, var1.getBoundingBox().expand(Class_0719.field_2280))) {
         return false;
      } else {
         if (var1 instanceof ArrowEntity var5) {
            this.method_2(
               var1, var5.getVelocity().length(), Double.longBitsToDouble(4587366580546961408L), Double.longBitsToDouble(4603579539098121011L), var2, var3
            );
         } else if (var1 instanceof EnderPearlEntity || var1 instanceof SnowballEntity || var1 instanceof EggEntity) {
            this.method_2(
               var1,
               Double.longBitsToDouble(4609434218613702656L),
               Double.longBitsToDouble(4584304132692975288L),
               Double.longBitsToDouble(4605380978949069210L),
               var2,
               var3
            );
            if (BlockPos.stream(var1.getBoundingBox().expand(0.0, Double.longBitsToDouble(4611686018427387904L), 0.0))
               .<BlockPos>map(BlockPos::toImmutable)
               .anyMatch(var0 -> field_4219.world.getBlockState(var0).getBlock() == Blocks.BUBBLE_COLUMN)) {
               Vec3d var6 = var1.getVelocity();
               this.method_2(
                  var1,
                  new Vec3d(var6.x, Double.longBitsToDouble(-4571373524106608640L), var6.z),
                  Double.longBitsToDouble(4607182418800017408L),
                  Double.longBitsToDouble(4609434218613702656L)
               );
            }
         } else if (var1 instanceof TridentEntity) {
            this.method_2(
               var1,
               Double.longBitsToDouble(4612811918334230528L),
               Double.longBitsToDouble(4587366580546961408L),
               Double.longBitsToDouble(4607092346807469998L),
               var2,
               var3
            );
         } else if (var1 instanceof ExperienceBottleEntity) {
            this.method_2(
               var1,
               Double.longBitsToDouble(4604480259023595110L),
               Double.longBitsToDouble(4589708452245819884L),
               Double.longBitsToDouble(4605380978949069210L),
               var2,
               var3
            );
         } else if (var1 instanceof ThrownEntity) {
            this.method_2(var1, Class_0245.field_688, Double.longBitsToDouble(4587366580439587226L), Double.longBitsToDouble(4605380978949069210L), var2, var3);
         } else {
            if (!(var1 instanceof WitherSkullEntity) && !(var1 instanceof FireballEntity) && !(var1 instanceof DragonFireballEntity)) {
               return false;
            }

            this.method_2(var1, Double.longBitsToDouble(4606732058837280358L), 0.0, Double.longBitsToDouble(4605380978949069210L), var2, var3);
         }

         if (var1.hasNoGravity()) {
            this.field_2985 = 0.0;
         }

         return true;
      }
   }

   public void method_2(Entity var1, double var2, double var4, double var6, boolean var8, double var9) {
      this.field_2983
         .set(MathHelper.lerp(var9, var1.prevX, var1.getX()), MathHelper.lerp(var9, var1.prevY, var1.getY()), MathHelper.lerp(var9, var1.prevZ, var1.getZ()));
      this.method_2(var1, var1.getVelocity(), var9, var2);
      if (var8) {
         Vec3d var11 = var1.getVelocity();
         this.field_2984.add(var11.x, var1.isOnGround() ? 0.0 : var11.y, var11.z);
      }

      this.field_2985 = var4;
      this.field_2986 = Double.longBitsToDouble(4607092346807469998L);
      this.field_2987 = var6;
   }

   public void method_2(Entity var1, Vec3d var2, double var3, double var5) {
      Class_0687 var7 = (Class_0687)var1;
      this.field_2984
         .set(
            MathHelper.lerp(var3, var7.mio$getPrevVelocity().x, var2.x),
            MathHelper.lerp(var3, var7.mio$getPrevVelocity().y, var2.y),
            MathHelper.lerp(var3, var7.mio$getPrevVelocity().z, var2.z)
         )
         .normalize()
         .mul(var5);
   }

   public void method_5(Entity var1, double var2) {
      double var4 = MathHelper.lerp(var2, (double)var1.prevYaw, (double)var1.getYaw());
      double var6 = MathHelper.lerp(var2, (double)var1.prevPitch, (double)var1.getPitch());
      double var8 = Math.cos(-var4 * (double)Class_0245.field_690 - Double.longBitsToDouble(4614256656748904448L));
      double var10 = Math.sin(-var4 * (double)Class_0245.field_690 - Double.longBitsToDouble(4614256656748904448L));
      double var12 = -Math.cos(-var6 * (double)Class_0245.field_690);
      double var14 = Math.sin(-var6 * (double)Class_0245.field_690);
      this.field_2983
         .set(
            MathHelper.lerp(var2, var1.prevX, var1.getX()),
            MathHelper.lerp(var2, var1.prevY, var1.getY()) + (double)var1.getEyeHeight(var1.getPose()),
            MathHelper.lerp(var2, var1.prevZ, var1.getZ())
         );
      this.field_2983.sub(var10 * Double.longBitsToDouble(4599075939470750515L), 0.0, var8 * Double.longBitsToDouble(4599075939470750515L));
      this.field_2984
         .set(-var10, MathHelper.clamp(-(var14 / var12), Double.longBitsToDouble(-4606056518893174784L), Double.longBitsToDouble(4617315517961601024L)), -var8);
      double var16 = this.field_2984.length();
      this.field_2984
         .mul(
            Double.longBitsToDouble(4603579539098121011L) / var16 + Class_0245.field_688,
            Double.longBitsToDouble(4603579539098121011L) / var16 + Class_0245.field_688,
            Double.longBitsToDouble(4603579539098121011L) / var16 + Class_0245.field_688
         );
      this.field_2985 = Double.longBitsToDouble(4584304132692975288L);
      this.field_2986 = Double.longBitsToDouble(4606461842859638129L);
      this.field_2987 = 0.0;
   }

   public HitResult method_870() {
      field_2982 = new Vec3d(this.field_2983.x, this.field_2983.y, this.field_2983.z);
      this.field_2983.add(this.field_2984);
      this.field_2984.mul(this.method_871() ? this.field_2987 : this.field_2986);
      this.field_2984.sub(0.0, this.field_2985, 0.0);
      if (this.field_2983.y < (double)field_4219.world.getBottomY()) {
         return field_2979;
      } else {
         int var1 = (int)(this.field_2983.x / Double.longBitsToDouble(4625196817309499392L));
         int var2 = (int)(this.field_2983.z / Double.longBitsToDouble(4625196817309499392L));
         if (!field_4219.world.getChunkManager().isChunkLoaded(var1, var2)) {
            return field_2979;
         } else {
            field_2981 = new Vec3d(this.field_2983.x, this.field_2983.y, this.field_2983.z);
            HitResult var3 = this.method_872();
            return var3.getType() == Type.MISS ? null : var3;
         }
      }
   }

   public boolean method_871() {
      field_2980.set(this.field_2983.x, this.field_2983.y, this.field_2983.z);
      FluidState var1 = field_4219.world.getFluidState(field_2980);
      return var1.getFluid() != Fluids.WATER && var1.getFluid() != Fluids.FLOWING_WATER
         ? false
         : this.field_2983.y - (double)((int)this.field_2983.y) <= (double)var1.getHeight();
   }

   public HitResult method_872() {
      Vec3d var1 = field_2982;
      HitResult var2 = field_4219.world
         .raycast(new RaycastContext(var1, field_2981, ShapeType.COLLIDER, this.field_2987 == 0.0 ? FluidHandling.ANY : FluidHandling.NONE, field_4219.player));
      if (var2.getType() != Type.MISS) {
         var1 = var2.getPos();
      }

      EntityHitResult var3 = ProjectileUtil.getEntityCollision(
         field_4219.world,
         field_4219.player,
         var1,
         field_2981,
         new Box(this.field_2983.x, this.field_2983.y, this.field_2983.z, this.field_2983.x, this.field_2983.y, this.field_2983.z)
            .stretch(field_4219.player.getVelocity())
            .expand(Double.longBitsToDouble(4607182418800017408L)),
         var0 -> !var0.isSpectator() && var0.isAlive() && var0.canHit()
      );
      if (var3 != null) {
         var2 = var3;
      }

      return (HitResult)var2;
   }
}
