package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_1334;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.Box;
import nick.Settings;

public class LongJumpModule extends Module {
   public Setting<Float> field_1566;
   public Setting<Boolean> field_1567;
   public Setting<Boolean> field_1568;
   public Setting<Boolean> field_1569;
   public double field_1570;
   public double field_1419;
   public int field_1571;
   public boolean field_1572;

   public LongJumpModule() {
      super("LongJump", "Makes you jump fast 'n far.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_1571 = 4;
   }

   @Subscribe
   public void method_9(Event_9 var1) {
      if (!this.method_526() && !this.method_525() && !field_4219.player.isFallFlying() && !field_4219.player.isSpectator()) {
         double var6 = var1.method_395();
         if (this.field_1568.getValue() && Hub.field_2602.method_984().method_9(250L)) {
            Hub.field_2596.method_2(this, Float.intBitsToFloat(1066098124));
         } else if (this.field_1568.getValue()) {
            Hub.field_2596.method_38(this);
         }

         if (!Hub.field_2602.method_984().method_9(250L) && this.field_1569.getValue()) {
            this.method_68();
         }

         double var8 = (double)(Float.intBitsToFloat(1073741824) * this.field_1566.getValue());
         if (this.field_1571 == 1 && Class_0464.method_363()) {
            this.field_1570 = Double.longBitsToDouble(4608758678669597082L) * Class_0464.method_2(false, Double.longBitsToDouble(4598847156609680094L) * var8)
               - Double.longBitsToDouble(4576918229304087675L);
         } else if (this.field_1571 == 2 && Class_0464.method_363()) {
            var6 = Double.longBitsToDouble(4601237667291888353L) + Class_0464.method_495();
            this.field_1570 = this.field_1570
               * (this.field_1572 ? Double.longBitsToDouble(4610260629145325142L) : Double.longBitsToDouble(4608961340652828754L));
         } else if (this.field_1571 == 3) {
            this.field_1570 = this.field_1419
               - Double.longBitsToDouble(4604119971289628672L)
                  * (this.field_1419 - Class_0464.method_2(true, Double.longBitsToDouble(4598847156609680094L) * var8));
            this.field_1572 = !this.field_1572;
         } else {
            if ((
                  field_4219.player.verticalCollision
                     || !field_4219.world.isSpaceEmpty(field_4219.player.getBoundingBox().offset(0.0, field_4219.player.getVelocity().y, 0.0))
               )
               && this.field_1571 > 0) {
               this.field_1571 = Class_0464.method_363() ? 1 : 0;
            }

            this.field_1570 = this.field_1419 - this.field_1419 / Double.longBitsToDouble(4639798331726364672L);
         }

         this.field_1570 = Math.max(this.field_1570, Class_0464.method_2(false, Double.longBitsToDouble(4598847156609680094L) * var8));
         double var2;
         double var4;
         if (!Class_0464.method_363()) {
            var2 = 0.0;
            var4 = 0.0;
         } else {
            double[] var10 = Class_0464.method_2(field_4219.player.getYaw(RenderUtil.method_776()), field_4219.player.input, this.field_1570);
            var2 = var10[0];
            var4 = var10[1];
         }

         Class_1334.method_2(var1.method_1066(), var2, var6, var4);
         if (Class_0464.method_363()) {
            this.field_1571++;
         }
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_2(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (!Class_0464.method_363()) {
            this.field_1571 = 4;
         }

         double var2 = field_4219.player.getX() - field_4219.player.prevX;
         double var4 = field_4219.player.getZ() - field_4219.player.prevZ;
         this.field_1419 = Math.sqrt(var2 * var2 + var4 * var4);
      }
   }

   public boolean method_525() {
      return Class_0382.method_29(field_4219.player) && !this.field_1567.getValue();
   }

   public boolean method_526() {
      Box var1 = field_4219.player.getBoundingBox();
      Box var2 = new Box(
            (double)field_4219.player.getBlockPos().getX(),
            var1.minY,
            (double)field_4219.player.getBlockPos().getZ(),
            (double)field_4219.player.getBlockPos().getX() + Double.longBitsToDouble(4607182418800017408L),
            var1.maxY,
            (double)field_4219.player.getBlockPos().getZ() + Double.longBitsToDouble(4607182418800017408L)
         )
         .contract(Double.longBitsToDouble(4502148214488346440L));
      return field_4219.world.canCollide(field_4219.player, var2);
   }
}
