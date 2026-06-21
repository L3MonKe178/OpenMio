package me.mioclient.module.combat;

import java.util.Comparator;
import me.mioclient.Hub;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Constants;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.RenderUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import nick.Settings;

public class AimAssistModule extends Module {
   public Setting<Float> field_3261;
   public Setting<Float> field_3262;
   public Setting<Float> field_3263;
   public Setting<Boolean> field_3264;
   public Setting<Boolean> field_3265;
   public Setting<Boolean> field_3266;
   public Setting<Boolean> field_3267;
   public Setting<Boolean> field_3268;
   public AbstractClientPlayerEntity field_3269;

   public AimAssistModule() {
      super("AimAssist", "Helps you aiming at players.", Category.COMBAT);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (field_4219.currentScreen == null) {
         if (field_4219.player.getMainHandStack().getItem() instanceof AxeItem
            || field_4219.player.getMainHandStack().getItem() instanceof SwordItem
            || !this.field_3264.getValue()) {
            if (this.field_3269 == null || !this.field_3266.getValue()) {
               this.field_3269 = field_4219.world
                  .getPlayers()
                  .stream()
                  .sorted(Comparator.comparing(var0 -> field_4219.player.distanceTo(var0)))
                  .filter(this::method_33)
                  .findFirst()
                  .orElse(null);
            }

            if (!this.method_33(this.field_3269)) {
               this.field_3269 = null;
            } else if (field_4219.targetedEntity != this.field_3269) {
               if (this.field_3268.getValue()) {
                  float[] var2 = RotationManager.method_78(this.field_3269.getBoundingBox().getCenter());
                  field_4219.player.setYaw(var2[0]);
                  field_4219.player.setPitch(var2[1]);
               } else {
                  double var5 = method_34(this.field_3269);
                  if (var5 > Double.longBitsToDouble(4607182418800017408L) || var5 < Double.longBitsToDouble(-4616189618054758400L)) {
                     float var4 = (float)(var5 * (double)(this.field_3262.getValue() != null ? this.field_3262.getValue().floatValue() : 0.0f) * Double.longBitsToDouble(4576918229175238656L));
                     field_4219.player.setYaw(field_4219.player.getYaw(RenderUtil.method_776()) - var4);
                  }
               }
            }
         }
      }
   }

   public boolean method_33(Entity var1) {
      return var1 instanceof PlayerEntity
         && var1 != field_4219.player
         && var1.isAlive()
         && (!this.field_3265.getValue() || !var1.isInvisible())
         && method_2(var1, (double)(this.field_3263.getValue() != null ? this.field_3263.getValue().floatValue() : 0.0f))
         && field_4219.player.distanceTo(var1) <= this.field_3261.getValue()
         && (!this.field_3267.getValue() || !Hub.field_2603.method_1009(((PlayerEntity)var1).getGameProfile().getName()))
         && this.method_30(var1);
   }

   public boolean method_30(Entity var1) {
      Vec3d var2 = new Vec3d(field_4219.player.getX(), field_4219.player.getY() + (double)field_4219.player.getStandingEyeHeight(), field_4219.player.getZ());
      Vec3d var3 = new Vec3d(var1.getX(), var1.getY(), var1.getZ());
      if (field_4219.world.raycast(new RaycastContext(var2, var3, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player)).getType() == Type.MISS) {
         return true;
      } else {
         Vec3d var4 = new Vec3d(var1.getX(), var1.getY() + (double)var1.getStandingEyeHeight(), var1.getZ());
         return field_4219.world.raycast(new RaycastContext(var2, var4, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player)).getType() == Type.MISS;
      }
   }

   public static double method_34(Entity var0) {
      return (
               (double)(field_4219.player.getYaw(RenderUtil.method_776()) - method_35(var0)) % (double)Constants.field_686
                  + Double.longBitsToDouble(4647961106050973696L)
            )
            % (double)Constants.field_686
         - Double.longBitsToDouble(4640537203540230144L);
   }

   public static boolean method_2(Entity var0, double var1) {
      var1 = (double)((float)(var1 * Constants.field_688));
      double var3 = (
               (double)(field_4219.player.getYaw(RenderUtil.method_776()) - method_35(var0)) % (double)Constants.field_686
                  + Double.longBitsToDouble(4647961106050973696L)
            )
            % (double)Constants.field_686
         - Double.longBitsToDouble(4640537203540230144L);
      return var3 > 0.0 && var3 < var1 || -var1 < var3 && var3 < 0.0;
   }

   public static float method_35(Entity var0) {
      double var1 = var0.getX() - field_4219.player.getX();
      double var3 = var0.getZ() - field_4219.player.getZ();
      double var5 = Math.atan2(var1, var3) * Double.longBitsToDouble(4633260481409690083L);
      return (float)(var5 * Double.longBitsToDouble(-4616189618054758400L));
   }
}
