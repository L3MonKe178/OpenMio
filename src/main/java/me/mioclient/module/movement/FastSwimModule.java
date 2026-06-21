package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.player.SpeedMineModule;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Direction.Axis;
import nick.Settings;

public class FastSwimModule extends Module {
   public static SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   public Setting<Boolean> field_4435;
   public Setting<Float> field_4436;
   public Setting<Boolean> field_4437;
   public Setting<Float> field_4438;
   public Setting<Boolean> field_4439;
   public Setting<Float> field_4440;
   public Setting<Float> field_4441;
   public Setting<Boolean> field_4442;
   public Setting<Float> field_4443;
   public Setting<Float> field_4444;
   public long field_303;
   public boolean field_4445;

   public FastSwimModule() {
      super("FastSwim", "Makes you move faster while in liquids.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_4436.method_31("WaterSpeed");
      this.field_4438.method_31("LavaSpeed");
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if ((field_4219.player.isInLava() && this.field_4437.getValue() || Class_0382.method_7(field_4219.player) && this.field_4435.getValue())
         && !field_4219.player.isFallFlying()
         && field_4219.player.isOnGround()) {
         field_4219.player.horizontalSpeed = 0.0F;
      }
   }

   @Subscribe
   public void method_9(Event_9 var1) {
      boolean var2 = speedmine.isToggled()
         && (double)speedmine.method_1119() + speedmine.method_1114()
            >= (double)speedmine.field_3988.getValue().floatValue() - Double.longBitsToDouble(4591870180066957722L)
         && speedmine.method_1118() != null
         && Class_1225.method_3(speedmine.method_1118());
      if ((field_4219.player.isInLava() && this.field_4437.getValue() || Class_0382.method_7(field_4219.player) && this.field_4435.getValue())
         && !field_4219.player.isFallFlying()
         && Hub.field_2602.method_984().method_9(500L)
         && !var2) {
         if (!Class_0464.method_363()) {
            this.field_303 = System.currentTimeMillis();
         }

         float var3 = this.field_4439.getValue() ? Float.intBitsToFloat(1039516303) * this.field_4440.getValue() : 0.0F;
         float var4 = Float.intBitsToFloat(1039516303) * this.field_4443.getValue();
         double var5 = (double)(Float.intBitsToFloat(1039516303) * this.field_4438.getValue());
         boolean var7 = this.field_4435.getValue() && Class_0382.method_7(field_4219.player);
         if (var7) {
            var5 = (double)(Float.intBitsToFloat(1039516303) * this.field_4436.getValue());
         }

         if (this.field_4442.getValue() && (double)var4 < var5) {
            float var8 = MathHelper.clamp(
               (float)(System.currentTimeMillis() - this.field_303) / (this.field_4444.getValue() * Float.intBitsToFloat(1148846080)),
               0.0F,
               Float.intBitsToFloat(1065353216)
            );
            double var9 = (double)var4 + (var5 - (double)var4) * (double)var8;
            var5 = Math.min(var9, var5);
         }

         if (field_4219.player.input.jumping && !this.field_4445) {
            var1.setY((double)var3);
            field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, (double)var3));
         } else if (field_4219.player.input.sneaking) {
            var1.setY((double)(-var3));
            field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, (double)(-var3)));
            var5 /= Double.longBitsToDouble(4611686018427387904L);
         } else {
            var1.setY(0.0);
            field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, 0.0));
            if (!field_4219.player.verticalCollision && this.field_4441.getValue() != 0.0F) {
               field_4219.player.setVelocity(field_4219.player.getVelocity().add(0.0, (double)(-this.field_4441.getValue()), 0.0));
               var1.setY(field_4219.player.getVelocity().y);
            }
         }

         double[] var11 = Class_0464.method_2(var1, var5);
         field_4219.player.setVelocity(var11[0], field_4219.player.getVelocity().y, var11[1]);
      } else {
         this.field_303 = System.currentTimeMillis();
      }
   }
}
