package me.mioclient.module.player;

import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Mount;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.util.Hand;
import nick.Settings;

public final class AutoMountModule extends Module {
   public Setting<Double> field_1193;
   public Setting<Float> field_1194;
   public Setting<Boolean> field_1195;
   public final Class_0242 field_1196;

   public AutoMountModule() {
      super("AutoMount", "Mounts rideable entities automatically.", Category.PLAYER);
      Settings.initialize(this);
      this.field_1196 = new Class_0242();
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      if (!field_4219.player.hasVehicle() && this.field_1196.method_2((double)this.field_1194.getValue().floatValue(), TimeUnit.SECONDS)) {
         for (Entity var3 : field_4219.world.getEntities()) {
            if (!((double)field_4219.player.distanceTo(var3) > this.field_1193.getValue())) {
               if (var3 instanceof LivingEntity) {
                  LivingEntity var4 = (LivingEntity)var3;
                  if (var4.isBaby()) {
                     continue;
                  }
               }

               if (var3 instanceof Saddleable) {
                  Saddleable var5 = (Saddleable)var3;
                  if (!var5.isSaddled() && !(var3 instanceof AbstractHorseEntity)) {
                     continue;
                  }
               }

               if (var3 instanceof Mount || var3 instanceof VehicleEntity) {
                  Class_1035.method_2(var3, Hand.MAIN_HAND);
                  if (this.field_1195.getValue()) {
                     Hub.field_2598.method_2(Class_0485.method_78(var3.getBoundingBox().getCenter()), 5);
                  }

                  this.field_1196.reset();
                  break;
               }
            }
         }
      }
   }
}
