package me.mioclient.mixin;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_64;
import me.mioclient.internal.Class_1334;
import me.mioclient.module.movement.EntityControlModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BoatEntity.class})
public abstract class MixinBoat extends Entity implements Class_1309 {
   private static final EntityControlModule entitycontrol = Hub.field_2595.method_78(EntityControlModule.class);
   @Unique
   private Event_64 mio$event;

   public MixinBoat(EntityType<?> var1, World var2) {
      super(var1, var2);
   }

   @Inject(
      method = {"clampPassengerYaw"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void clampPassengerYawHook(Entity var1, CallbackInfo var2) {
      if (entitycontrol.method_107(this)) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"updatePaddles"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void updatePaddles(CallbackInfo var1) {
      if (entitycontrol.method_107(this)) {
         var1.cancel();
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/entity/vehicle/BoatEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V",
         shift = Shift.BEFORE
      )}
   )
   private void tickPre(CallbackInfo var1) {
      if (Event_64.method_33(this)) {
         this.mio$event = new Event_64(this.getVelocity());
         field_4220.method_36(this.mio$event);
         Class_1334.method_2(this.getVelocity(), this.mio$event.method_380(), this.mio$event.method_395(), this.mio$event.method_396());
      }
   }
}
