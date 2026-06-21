package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.event.Event_9;
import me.mioclient.module.movement.LongJumpModule;
import me.mioclient.module.movement.SpeedModule;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class Class_1376 extends Class_0806 {
   public static LongJumpModule field_1017 = Hub.field_2595.method_78(LongJumpModule.class);

   public Class_1376(SpeedModule var1) {
      super(var1);
   }

   @Override
   public void method_9(Event_9 var1) {
      if (!this.method_404()) {
         if (this.field_2537.field_2195.getValue() && Hub.field_2602.method_984().method_9(250L)) {
            Hub.field_2596.method_2(this.field_2537, Float.intBitsToFloat(1066098124));
         } else {
            Hub.field_2596.method_38(this.field_2537);
         }

         if (!Class_0464.method_363()) {
            var1.method_7(0.0, 0.0);
         } else {
            double var2 = Class_0464.method_78(false);
            if (Hub.field_2602.method_984().method_9(500L)) {
               var2 = Math.max(var2, Math.hypot(var1.method_380(), var1.method_396()));
            }

            Class_0464.method_2(var1, var2);
         }
      }
   }

   @Override
   public void method_738() {
      if (!this.method_404() && Class_0464.method_363() && field_4219.player.groundCollision) {
         double[] var1 = Class_0464.method_2(field_4219.player.getYaw(), field_4219.player.input, Double.longBitsToDouble(4607182418800017408L));
         float var2 = (float)(Math.toDegrees(Math.atan2(var1[1], var1[0])) - (double)Class_0245.field_685);
         Vec3d var3 = field_4219.player.getVelocity();
         float var4 = (float)Math.toRadians((double)var2);
         float var5 = Float.intBitsToFloat(1045220557);
         field_4219.player.setVelocity(var3.x, Double.longBitsToDouble(4600877379321698714L) + Class_0464.method_495(), var3.z);
         field_4219.player.addVelocityInternal(new Vec3d((double)(-MathHelper.sin(var4) * var5), 0.0, (double)(MathHelper.cos(var4) * var5)));
         field_4219.player.setSprinting(true);
      }
   }

   public boolean method_404() {
      return this.field_2537.method_526()
         || this.field_2537.method_525()
         || Class_0382.method_427()
         || field_4219.player.isFallFlying()
         || field_4219.player.isSpectator()
         || field_1017.isToggled();
   }
}
