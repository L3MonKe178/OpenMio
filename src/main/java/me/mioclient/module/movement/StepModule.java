package me.mioclient.module.movement;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0581;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_51;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_1016;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.PositionAndOnGround;
import nick.Settings;

public class StepModule extends Module {
   public static final HoleSnapModule field_2119 = Hub.field_2595.method_78(HoleSnapModule.class);
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Class_0581> field_2120;
   public Setting<Float> field_2121;
   public Setting<Boolean> field_2122;
   public Setting<Boolean> field_2123;
   public Setting<Float> field_2124;
   public boolean field_2125;
   public int field_2126;

   public StepModule() {
      super("Step", "Allows you to step up blocks without jumping.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_2126 = 0;
   }

   @Override
   public void onDisable() {
      this.field_2125 = false;
      this.field_2126 = 0;
      if (!this.method_535()) {
         Class_0396.method_9(field_4219.player, Float.intBitsToFloat(1058642330));
      }
   }

   @Override
   public String getInfo() {
      return Class_1016.method_3(this.field_2120.getValue());
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_2(Event_17 var1) {
      if (this.field_2126 > 0 && Hub.field_2605.method_221(Class_0382.method_425()) && this.field_2122.getValue()) {
         this.disable();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_2125 = false;
   }

   @Subscribe
   public void method_2(Event_51 var1) {
      if (!field_2119.method_405()) {
         if (field_4219.player.isOnGround() && !(field_4219.player.fallDistance > 0.0F) && field_4219.player.verticalCollision) {
            if (var1.method_213() == PreType.PRE) {
               var1.method_34(this.field_2121.getValue());
            }

            if (var1.method_213() == PreType.POST) {
               if ((double)var1.method_575() <= Double.longBitsToDouble(4603579539098121011L)) {
                  Hub.field_2596.method_38(this);
                  return;
               }

               if (this.field_2120.getValue() == Class_0581.VANILLA) {
                  this.field_2126++;
                  return;
               }

               ArrayList var2 = new ArrayList();
               if (this.field_2123.getValue() && !Class_0382.method_5(field_4219.player)) {
                  Hub.field_2596.method_2(this, this.field_2124.getValue());
                  this.field_2125 = true;
               }

               this.field_2126++;
               if (var1.method_575() <= Float.intBitsToFloat(1065353216)) {
                  var2.addAll(
                     List.of(
                        Double.longBitsToDouble(4601237667291888353L) * (double)var1.method_575(),
                        Double.longBitsToDouble(4604930618986332160L) * (double)var1.method_575()
                     )
                  );
               } else if ((double)var1.method_575() <= Double.longBitsToDouble(4609434218613702656L)) {
                  var2.addAll(
                     List.of(
                        Double.longBitsToDouble(4601237667291888353L),
                        Double.longBitsToDouble(4604930618986332160L),
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4607902994740396687L),
                        Double.longBitsToDouble(4608218246714312622L),
                        Double.longBitsToDouble(4608083138725491507L)
                     )
                  );
               } else if (var1.method_575() <= this.field_2121.getValue()) {
                  var2.addAll(
                     List.of(
                        Double.longBitsToDouble(4601237667291888353L),
                        Double.longBitsToDouble(4605200834963974390L),
                        Double.longBitsToDouble(4603849755075763241L),
                        Double.longBitsToDouble(4602768891165194322L),
                        Double.longBitsToDouble(4606281698874543309L),
                        Double.longBitsToDouble(4608128174721765212L),
                        Double.longBitsToDouble(4609209038632334131L),
                        Double.longBitsToDouble(4609118966639786721L)
                     )
                  );
               }

               if (!var2.isEmpty()) {
                  for (Double var4 : (Iterable<Double>)(Iterable<?>)(var2)) {
                     field_4219.player
                        .networkHandler
                        .sendPacket(
                           new PositionAndOnGround(
                              field_4219.player.getX(), field_4219.player.getY() + var4, field_4219.player.getZ(), field_4219.player.isOnGround()
                           )
                        );
                  }
               }
            }
         } else {
            Hub.field_2596.method_38(this);
         }
      }
   }

   public boolean method_666() {
      return this.field_2125;
   }
}
