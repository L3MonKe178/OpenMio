package me.mioclient.module.movement;

import me.mioclient.event.Event_4;
import me.mioclient.event.Event_64;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0018;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_0838;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class EntityControlModule extends Module {
   public Setting<Boolean> field_3680;
   public Setting<Double> field_3681;
   public Setting<Boolean> field_3682;
   public Setting<Boolean> field_3683;
   public Setting<Float> field_3684;
   public Setting<Boolean> field_3685;
   public Setting<Boolean> field_3686;
   public Setting<Boolean> field_3687;
   public Setting<Boolean> field_3688;
   public boolean field_535;
   public long field_303;

   public EntityControlModule() {
      super("EntityControl", "Helps with riding entities.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_3681.method_9(() -> this.field_303 = System.currentTimeMillis());
   }

   @Subscribe
   public void method_2(Event_64 var1) {
      if (!this.method_535()) {
         if (!Class_0464.method_363()) {
            this.field_303 = System.currentTimeMillis();
         }

         if (field_4219.player.hasVehicle()) {
            Entity var2 = field_4219.player.getVehicle();
            if (var2 != null) {
               if (this.field_3687.getValue()) {
                  var2.setYaw(field_4219.player.getYaw(Class_0838.method_776()));
               }

               if (var2 instanceof Saddleable var3 && !var3.isSaddled()) {
                  return;
               }

               double var11 = this.field_3681.getValue();
               double var5 = (double)this.field_3684.getValue().floatValue();
               if (var11 >= Double.longBitsToDouble(4626322717216342016L)) {
                  var11 = Double.longBitsToDouble(4626319902466574909L);
               }

               if (this.field_3686.getValue()) {
                  var11 = Class_0464.method_2(
                     this.field_3681.getValue(), Double.longBitsToDouble(4591870180066957722L), this.field_3681.getValue(), this.field_303
                  );
               }

               if (this.field_3680.getValue() && !this.method_1044()) {
                  double[] var9 = Class_0464.method_2(field_4219.player.getYaw(Class_0838.method_776()), field_4219.player.input, var11);
                  double var7;
                  if (this.field_3683.getValue()) {
                     if (var2 instanceof BoatEntity var10) {
                        var10.setNoGravity(true);
                     }

                     if (field_4219.options.jumpKey.isPressed()) {
                        var7 = var5;
                     } else if (!this.field_3685.getValue() && !Class_0018.method_2(field_4219.options.sprintKey)) {
                        var7 = this.field_535 ? Double.longBitsToDouble(-4637266464074629120L) : Double.longBitsToDouble(4586105572780146688L);
                     } else {
                        var7 = -var5;
                     }
                  } else {
                     var7 = var1.method_395();
                  }

                  Vec3d var12 = new Vec3d(var9[0], var7, var9[1]);
                  var1.method_4(var12);
                  this.field_535 = !this.field_535;
               }
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_303 = System.currentTimeMillis();
      }
   }

   public boolean method_107(Object var1) {
      return false;
   }

   public boolean method_1044() {
      return field_4219.player.hasVehicle() && field_4219.player.getVehicle().horizontalCollision && this.field_3682.getValue();
   }
}
