package me.mioclient.module.movement;

import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.api.Class_0957;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_7;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0444;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0512;
import me.mioclient.internal.Class_0981;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0123;
import me.mioclient.record.Class_0681;
import me.mioclient.setting.Setting;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import nick.Settings;

public class HoleSnapModule extends Module {
   public Setting<Boolean> field_1168;
   public Setting<Boolean> field_1169;
   public Setting<Boolean> field_1170;
   public Setting<Boolean> field_1171;
   public Setting<Float> field_1172;
   public Setting<Float> field_1173;
   public Setting<Float> field_1174;
   public Setting<Float> field_1175;
   public Setting<Float> field_1176;
   public final Class_0444 field_1177;
   public final Timer field_1178;
   public final Timer field_1179;
   public boolean field_1180;
   public boolean field_1181;

   public HoleSnapModule() {
      super("HoleSnap", "Pushes you into holes as you go past them.", Category.MOVEMENT, "holetp", "anchor");
      Settings.initialize(this);
      this.field_1177 = new Class_0444(this);
      this.field_1178 = new Timer();
      this.field_1179 = new Timer();
      this.field_1181 = false;
      this.field_1176.method_2("None", SettingMode.MIN);
   }

   @Override
   public void onToggle() {
      this.field_1179.setTime(-1L);
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      boolean var2 = Hub.field_2605.method_224();
      if (field_4219.player.isOnGround() && var2 != this.field_1180) {
         this.field_1180 = var2;
         this.field_1178.reset();
      }

      if (!this.method_404() && this.field_1179.method_2((double)(this.field_1173.getValue() != null ? this.field_1173.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)) {
         Class_0123 var3 = this.method_403();
         if (var3 == null) {
            this.field_1181 = false;
            Hub.field_2617.method_38(this);
         } else {
            if (RotationManager.method_513()) {
               Hub.field_2598.method_2(RotationManager.method_78((Vec3d)var3.method_144()), 1337, true);
               this.field_1181 = true;
            }

            Class_0444.field_1422 = this.field_1177.method_484();
            if (!Class_0444.field_1422) {
               Hub.field_2617.method_38(this);
            }
         }
      } else {
         Hub.field_2617.method_38(this);
      }
   }

   @Subscribe
   public void method_2(Event_9 var1) {
      if (!this.method_404()) {
         if (Hub.field_2605.method_221(Class_0382.method_425())) {
            if (this.field_1168.getValue()) {
               this.disable();
               this.field_1181 = false;
               return;
            }

            this.field_1179.reset();
         }

         if (this.field_1179.method_2((double)(this.field_1173.getValue() != null ? this.field_1173.getValue().floatValue() : 0.0f), TimeUnit.SECONDS) && !RotationManager.method_513()) {
            Class_0123 var2 = this.method_403();
            if (var2 == null) {
               this.field_1181 = false;
            } else {
               Vec3d var3 = ((Vec3d)var2.method_144())
                  .subtract(field_4219.player.getPos())
                  .normalize()
                  .multiply(Math.min(Class_0464.method_78(true) * (double)(this.field_1175.getValue() != null ? this.field_1175.getValue().floatValue() : 0.0f), (Double)var2.method_145()));
               var1.method_7(var3.x, var3.z);
            }
         }
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_2(Event_16 var1) {
      if (this.field_1181 && !this.method_404() && this.field_1179.method_2((double)(this.field_1173.getValue() != null ? this.field_1173.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)) {
         var1.method_276().movementForward = var1.method_278() ? var1.method_277() : Float.intBitsToFloat(1065353216);
         var1.method_276().pressingForward = true;
      }
   }

   @Subscribe
   public void method_9(Event_39 var1) {
      if (this.field_1181 && !this.method_404() && this.field_1179.method_2((double)(this.field_1173.getValue() != null ? this.field_1173.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)) {
         Class_0123 var2 = this.method_403();
         if (var2 == null) {
            return;
         }

         float[] var3 = RotationManager.method_78((Vec3d)var2.method_144());
         var1.setYaw(var3[0]);
         var1.setPitch(var3[1]);
         var1.method_463();
      }
   }

   public Class_0123<Vec3d, Double> method_403() {
      double var1 = Double.longBitsToDouble(4666722622711529472L);
      Vec3d var3 = null;
      Vec3d var4 = field_4219.player.getPos();

      for (Class_0681 var6 : Hub.field_2605.method_223()) {
         Vec3d var7 = var6.method_172().getCenter();
         var7 = var7.withAxis(
            Axis.Y,
            MathHelper.clamp(var4.getY(), var7.y, var7.y + (double)(this.field_1172.getValue() != null ? this.field_1172.getValue().floatValue() : 0.0f) - Double.longBitsToDouble(4602678819172646912L))
         );
         if (!((double)var6.method_406().getY() >= var4.getY()) && !var6.method_676()) {
            double var8 = var4.distanceTo(var7);
            if ((!this.field_1169.getValue() || Class_0464.method_2(var7) || !(var8 > Double.longBitsToDouble(4587366580439587226L)))
               && !(var8 > (double)(this.field_1174.getValue() != null ? this.field_1174.getValue().floatValue() : 0.0f))
               && field_4219.world.isSpaceEmpty(var6.method_172().withMaxY(field_4219.player.getBoundingBox().maxY))
               && !this.method_29(var4, var7)
               && !this.method_29(
                  var4.add(0.0, Double.longBitsToDouble(4610785298287165440L), 0.0), var7.add(0.0, Double.longBitsToDouble(4610785298287165440L), 0.0)
               )
               && var8 < var1) {
               var1 = var8;
               var3 = var7;
            }
         }
      }

      return var1 > (double)(this.field_1174.getValue() != null ? this.field_1174.getValue().floatValue() : 0.0f) ? null : new Class_0123<>(var3, var1);
   }

   public boolean method_404() {
      if (field_4219.player.isInSneakingPose()) {
         return true;
      } else {
         return field_4219.player.getPitch() < this.field_1176.getValue() && this.field_1176.getValue() != 0.0F
            ? true
            : !Hub.field_2602.method_984().method_9(250L) || field_4219.player.isSpectator() || field_4219.player.isFallFlying();
      }
   }

   public boolean method_29(Vec3d var1, Vec3d var2) {
      Class_0512 var3 = new Class_0512(var1, var2, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player, Class_0957.field_2968);
      return Class_0981.method_2(var3).getType() != Type.MISS;
   }

   public boolean method_405() {
      return !this.field_1178.method_9(400L) && Hub.field_2605.method_224() && this.field_1171.getValue();
   }
}
