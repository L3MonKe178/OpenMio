package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.enum_.StrafeType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_37;
import me.mioclient.event.Event_38;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_51;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.Class_0806;
import me.mioclient.internal.Class_0963;
import me.mioclient.internal.Class_0992;
import me.mioclient.internal.Class_1177;
import me.mioclient.internal.Class_1197;
import me.mioclient.internal.Class_1295;
import me.mioclient.internal.Class_1323;
import me.mioclient.internal.Class_1376;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_6;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class SpeedModule extends Module {
   public static AbstractModule_6 field_1637 = Hub.field_2595.method_78(AbstractModule_6.class);
   public Setting<StrafeType> field_2191;
   public Setting<Float> field_2192;
   public Setting<Boolean> field_2193;
   public Setting<Boolean> field_2194;
   public Setting<Boolean> field_2195;
   public Setting<Boolean> field_2196;
   public boolean field_2197;
   public final Timer field_2198;
   public final Class_1295<StrafeType, Class_0806> field_2199;

   public SpeedModule() {
      super("Speed", "Makes you move faster.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_2198 = new Timer();
      this.field_2199 = new Class_1295<>(this.field_2191);
      this.field_2199.method_2(StrafeType.STRAFE, new Class_0992(this));
      this.field_2199.method_2(StrafeType.STRAFE_STRICT, new Class_1376(this));
      this.field_2199.method_2(StrafeType.VANILLA, new Class_1177(this));
      this.field_2199.method_2(StrafeType.ON_GROUND, new Class_0963(this));
      this.field_2199.method_2(StrafeType.GRIM, new Class_1197(this));
      this.field_2199.method_2(StrafeType.NONE, new Class_0806(this) {
         @Override
         public void method_9(Event_9 var1) {
         }
      });
   }

   @Override
   public String getInfo() {
      return (this.field_2191.getValue() != null ? this.field_2191.getValue().getName() : "");
   }

   @Override
   public void onEnable() {
      this.field_2199.method_1156().onEnable();
   }

   @Subscribe
   public void method_2(Event_38 var1) {
      if (this.field_2191.getValue() == StrafeType.STRAFE_STRICT && !this.method_404()) {
         field_4219.player.setSprinting(true);
      }
   }

   @Subscribe
   public void method_9(Event_39 var1) {
      if (!var1.method_1025()) {
         this.field_2199.method_1156().method_738();
      }
   }

   @Subscribe
   public void method_9(Event_9 var1) {
      if (Hub.field_2602.method_985().method_9(10L) && !this.field_2197) {
         this.field_2199.method_1156().method_9(var1);
      }
   }

   @Subscribe
   public void method_2(Event_51 var1) {
      if (var1.method_213() == PreType.POST && field_4219.player.isOnGround() && (double)var1.method_575() > Double.longBitsToDouble(4603579539098121011L)) {
         this.field_2197 = true;
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_2(Event_19 var1) {
      this.field_2197 = false;
      if (Hub.field_2602.method_985().method_9(10L)) {
         this.field_2199.method_1156().method_2(var1);
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.reset();
      }
   }

   @Subscribe
   public void method_2(Event_37 var1) {
      Vec3d var2 = new Vec3d(var1.method_380(), var1.method_395(), var1.method_396());
      double var3 = (double)Class_1323.method_2(
         var2, field_4219.player, field_4219.player.getBoundingBox(), Double.longBitsToDouble(4618441417868443648L), true, null, null
      );
      if (Class_0464.method_363()
         && Hub.field_2602.method_984().method_9(500L)
         && this.field_2193.getValue()
         && !(Math.sqrt(field_4219.player.squaredDistanceTo(var2)) > Double.longBitsToDouble(4618441417868443648L))
         && !(var3 < Double.longBitsToDouble(4616189618054758400L))) {
         this.field_2198.reset();
      }
   }

   public void reset() {
      this.field_2199.method_1156().field_1419 = 0.0;
      this.field_2199.method_1156().field_1570 = 0.0;
      this.field_2199.method_1156().field_1571 = 4;
      if (this.field_2191.getValue() == StrafeType.ON_GROUND) {
         this.field_2199.method_1156().field_1571 = 2;
      }
   }

   public boolean method_525() {
      return Class_0382.method_29(field_4219.player) && !this.field_2194.getValue();
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
      return field_4219.world.canCollide(field_4219.player, var2) && this.field_2196.getValue();
   }

   public boolean method_678() {
      return field_1637 == null ? false : field_1637.method_639();
   }

   public boolean method_404() {
      return this.method_535()
         ? true
         : this.method_526() || this.method_525() || Class_0382.method_427() || field_4219.player.isFallFlying() || field_4219.player.isSpectator();
   }
}
