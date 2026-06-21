package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0156;
import me.mioclient.enum_.Class_0786;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0681;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class HoleESPModule extends Module {
   public Setting<Class_0786> field_4316;
   public Setting<Float> field_4317;
   public Setting<Float> field_4318;
   public Setting<Integer> field_4319;
   public Setting<Boolean> field_4320;
   public Setting<Boolean> field_4321;
   public Setting<Float> field_4322;
   public Setting<Boolean> field_4323;
   public Setting<Color> field_4324;
   public Setting<Color> field_4325;
   public Setting<Boolean> field_4326;
   public Setting<Color> field_4327;
   public Setting<Color> field_4328;
   public Setting<Boolean> field_4329;
   public Setting<Color> field_4330;
   public Setting<Color> field_4331;

   public HoleESPModule() {
      super("HoleESP", "Highlights the spots that are safe from end crystals.", Category.RENDER);
      Settings.initialize(this);
   }

   @Override
   public String getInfo() {
      return String.valueOf(Hub.field_2605.method_223().size());
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      double var2 = MathHelper.lerp((double)var1.method_880(), field_4219.player.prevY, field_4219.player.getY());
      Vec3d var4 = field_4219.gameRenderer.getCamera().getPos();
      Box var5 = new Box(Class_0382.method_425());

      for (Class_0681 var7 : Hub.field_2605.method_223()) {
         if (RotationManager.method_4(var7.method_172())
            && (this.field_4329.getValue() || !var7.method_676())
            && var7.method_406().isWithinDistance(var4, (double)(this.field_4319.getValue() != null ? this.field_4319.getValue().intValue() : 0))
            && (!field_4219.player.getBoundingBox().intersects(var7.method_172()) || !this.field_4320.getValue() || this.field_4321.getValue())) {
            Color var8 = this.field_4324.getValue();
            Color var9 = this.field_4325.getValue();
            if (var7.method_676()) {
               var8 = this.field_4330.getValue();
               var9 = this.field_4331.getValue();
            } else if (var7.method_675() == Class_0156.UNSAFE) {
               var8 = this.field_4327.getValue();
               var9 = this.field_4328.getValue();
            }

            double var10 = var4.distanceTo(var7.method_406().toCenterPos());
            boolean var12 = var7.method_172().intersects(var5) && this.field_4320.getValue();
            if (this.field_4321.getValue() && (var10 >= (double)(this.field_4322.getValue() != null ? this.field_4322.getValue().floatValue() : 0.0f) || var12)) {
               float var13 = Float.intBitsToFloat(1065353216)
                  - (float)MathHelper.clamp(
                     (var10 - (double)(this.field_4322.getValue() != null ? this.field_4322.getValue().floatValue() : 0.0f))
                        / (double)((float)(this.field_4319.getValue() != null ? this.field_4319.getValue().intValue() : 0) - this.field_4322.getValue()),
                     0.0,
                     Double.longBitsToDouble(4607182418800017408L)
                  );
               if (var12) {
                  var13 = (float)(var2 - Math.floor(var2));
               }

               var8 = Class_1081.method_9(var8, (int)(var13 * (float)var8.getAlpha()));
               var9 = Class_1081.method_9(var9, (int)(var13 * (float)var9.getAlpha()));
            }

            switch ((Class_0786)this.field_4316.getValue()) {
               case SOLID:
                  Class_0612.method_5(
                     var1.method_10(), var7.method_172().withMaxY((double)((float)var7.method_406().getY() + this.field_4318.getValue())), var8
                  );
                  break;
               case GRADIENT:
                  Class_0612.method_7(var1.method_10(), var7.method_172().withMaxY((double)(var7.method_406().getY() + 1)), var8);
            }

            Class_0612.method_2(
               var1.method_10(),
               var7.method_172().withMaxY((double)((float)var7.method_406().getY() + this.field_4318.getValue())),
               var9,
               this.field_4317.getValue()
            );
         }
      }
   }
}
