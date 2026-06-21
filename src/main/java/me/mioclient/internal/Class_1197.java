package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_9;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.movement.SpeedModule;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity.AnimationStage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;

public class Class_1197 extends Class_0806 {
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Box field_1887;

   public Class_1197(SpeedModule var1) {
      super(var1);
   }

   @Override
   public void method_9(Event_9 var1) {
      if (this.field_1887 != null) {
         if (Class_0464.method_363()) {
            int var2 = 0;

            for (BlockEntity var4 : Class_1225.method_1070()) {
               if (var4 instanceof ShulkerBoxBlockEntity) {
                  ShulkerBoxBlockEntity var5 = (ShulkerBoxBlockEntity)var4;
                  if (this.field_1887
                        .expand(Double.longBitsToDouble(4602678819172646912L))
                        .intersects(var5.getBoundingBox(Blocks.SHULKER_BOX.getDefaultState()).offset(var4.getPos()))
                     && var5.getAnimationStage() != AnimationStage.CLOSED) {
                     var2 += field_144.method_1052() ? 0 : 4;
                  }
               }
            }

            float var6 = field_144.method_1052() ? 0.0F : Float.intBitsToFloat(1065353216);

            for (Entity var10 : field_4219.world.getEntities()) {
               if (!(var10 instanceof Class_0922)
                  && var10 != field_4219.player
                  && var10.isPushable()
                  && var10.getBoundingBox().intersects(this.field_1887.expand((double)var6))) {
                  if (var10 instanceof BoatEntity) {
                     var2 += 4;
                  }

                  var2++;
               }
            }

            if (var2 != 0) {
               Vec3d var8 = var1.method_1066().normalize();
               var8 = var8.withAxis(Axis.Y, 0.0);
               var1.method_4(var1.method_1066().add(var8.multiply(Double.longBitsToDouble(4590429028186199163L) * (double)var2)));
            }
         }
      }
   }

   @Override
   public void method_2(Event_19 var1) {
      if (var1.method_213() == PreType.POST) {
         this.field_1887 = field_4219.player.getBoundingBox();
      }
   }
}
