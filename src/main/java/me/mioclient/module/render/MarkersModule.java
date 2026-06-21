package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class MarkersModule extends Module {
   public Setting<Float> field_1076;
   public Setting<Boolean> field_1077;

   public MarkersModule() {
      super("Markers", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_2(Event_26 var1) {
      float var2 = MathHelper.lerp(Class_0838.method_776(), field_4219.player.prevYaw, field_4219.player.getYaw());

      for (AbstractClientPlayerEntity var4 : field_4219.world.getPlayers()) {
         double var5 = (double)field_4219.player.distanceTo(var4);
         if (field_4219.player != var4 && !(var5 > Double.longBitsToDouble(4629700416936869888L))) {
            float var7 = (float)(
               Double.longBitsToDouble(4607182418800017408L)
                  - MathHelper.clamp(
                     (var5 - Double.longBitsToDouble(4628574517030027264L)) / Double.longBitsToDouble(4616189618054758400L),
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L)
                  )
            );
            if (!((double)var7 < Double.longBitsToDouble(4591870180066957722L))) {
               float var8 = Class_0485.method_7(this.method_209(field_4219.player), this.method_209(var4))[0];
               if (!this.field_1077.getValue()
                  || !(
                     MathHelper.angleBetween(var8, var2)
                        <= (float)((Integer)field_4219.options.getFov().getValue()).intValue() / Float.intBitsToFloat(1073741824)
                  )) {
                  double var9 = Math.toRadians((double)(var8 - var2 - (float)Class_0245.field_685));
                  float var11 = Class_1016.field_3143.method_221(">") * Float.intBitsToFloat(1056964608);
                  float var12 = MathHelper.clamp(
                        field_4219.player.getPitch() + Float.intBitsToFloat(1106247680), (float)(-Class_0245.field_685), (float)Class_0245.field_685
                     )
                     / (float)Class_0245.field_685;
                  float var13 = (float)(
                     Math.cos(var9) * (double)this.field_1076.getValue().floatValue() + (double)var1.method_881().getScaledWindowWidth() * Class_0245.field_688
                  );
                  float var14 = (float)(
                     Math.sin(var9) * (double)this.field_1076.getValue().floatValue() * (double)var12
                        + (double)var1.method_881().getScaledWindowHeight() * Class_0245.field_688
                  );
                  var1.method_10().push();
                  var1.method_10().translate(var13, var14, 0.0F);
                  var1.method_10().multiply(RotationAxis.POSITIVE_Z.rotation((float)var9));
                  Class_1016.field_3143
                     .method_2(
                        var1.method_881(),
                        ">",
                        -var11,
                        (float)((double)(-Class_1016.field_3143.method_66()) * Class_0245.field_688),
                        Class_1081.method_9(this.method_114(var4), (int)(var7 * Float.intBitsToFloat(1132396544)))
                     );
                  var1.method_10().pop();
               }
            }
         }
      }
   }

   public Color method_114(PlayerEntity var1) {
      if (Hub.field_2603.method_30(var1)) {
         return Hub.field_2603.method_1145();
      } else if (Hub.field_2603.method_16(var1)) {
         return Hub.field_2603.method_1146();
      } else {
         double var2 = MathHelper.clamp(
            field_4219.player.getEyePos().distanceTo(var1.getPos()),
            Double.longBitsToDouble(4620693217682128896L),
            Double.longBitsToDouble(4629700416936869888L)
         );
         float var4 = Float.intBitsToFloat(1065353216)
            - (float)((var2 - Double.longBitsToDouble(4620693217682128896L)) / Double.longBitsToDouble(4627448617123184640L));
         return Class_1081.method_2(Color.red, Color.green, var4);
      }
   }

   public Vec3d method_209(PlayerEntity var1) {
      return new Vec3d(
         MathHelper.lerp((double)Class_0838.method_776(), var1.prevX, var1.getX()),
         MathHelper.lerp((double)Class_0838.method_776(), var1.prevY, var1.getY()),
         MathHelper.lerp((double)Class_0838.method_776(), var1.prevZ, var1.getZ())
      );
   }
}
