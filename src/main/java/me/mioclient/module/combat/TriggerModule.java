package me.mioclient.module.combat;

import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0930;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import nick.Settings;

public class TriggerModule extends Module {
   public Setting<Float> field_3057;
   public Setting<Boolean> field_3058;
   public Setting<Double> field_3059;
   public Setting<Integer> field_3060;
   public Setting<Boolean> field_3061;
   public Setting<Boolean> field_3062;
   public Setting<Boolean> field_3063;
   public Setting<Boolean> field_3064;
   public Setting<Boolean> field_3065;
   public Setting<Boolean> field_3066;
   public final Class_0242 field_3067;

   public TriggerModule() {
      super("Trigger", "Attacks entities under your crosshair.", Category.COMBAT);
      Settings.initialize(this);
      this.field_3067 = new Class_0242();
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (!this.method_535()) {
            if (field_4219.player.getMainHandStack().getItem() instanceof AxeItem
               || field_4219.player.getMainHandStack().getItem() instanceof SwordItem
               || !this.field_3058.getValue()) {
               if (field_4219.crosshairTarget instanceof EntityHitResult) {
                  Entity var2 = ((EntityHitResult)field_4219.crosshairTarget).getEntity();
                  boolean var3 = var2 instanceof PlayerEntity && !Hub.field_2603.method_1009(var2.getName().getString()) && this.field_3062.getValue();
                  if (var3 && this.field_3063.getValue() && !Class_0382.method_29((LivingEntity)((PlayerEntity)var2))) {
                     var3 = false;
                  }

                  if (var2.isAlive()
                     && (
                        var3
                           || var2 instanceof PassiveEntity && this.field_3064.getValue()
                           || var2 instanceof Monster && this.field_3065.getValue()
                           || var2 instanceof EndCrystalEntity && this.field_3066.getValue()
                     )
                     && field_4219.player.getAttackCooldownProgress(Float.intBitsToFloat(1056964608)) >= Float.intBitsToFloat(1065353216)
                     && this.field_3067.method_2(this.field_3059.getValue() * Math.random(), TimeUnit.SECONDS)
                     && field_4219.player.distanceTo(var2) <= this.field_3057.getValue()) {
                     this.method_230(Class_0930.method_17(this.field_3060.getValue()));
                     this.field_3067.reset();
                  }
               }
            }
         }
      }
   }

   public void method_230(boolean var1) {
      if (var1) {
         field_4219.player.swingHand(Hand.MAIN_HAND);
         field_4219.player.resetLastAttackedTicks();
      } else {
         ((DuckMinecraftClient)field_4219).attack();
      }
   }
}
