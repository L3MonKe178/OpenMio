package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_1041;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.client.input.Input;

public class Class_0926 extends Class_0716 implements Class_1041 {
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);

   public Class_0926(ElytraFlyModule var1) {
      super(var1);
   }

   @Override
   public boolean method_295() {
      return this.method_535() ? false : !Class_0382.method_428() || field_4219.player.isOnGround();
   }

   @Override
   public void method_2(Event_17 var1) {
   }

   @Override
   public void method_2(Event_10 var1) {
      if (this.method_295()) {
         field_144.method_1053().method_9(var1);
      }
   }

   @Override
   public void method_2(Event_9 var1) {
      boolean var2 = !Hub.field_2602.method_984().method_9(300L);
      if (!Class_0382.method_428() && !var2) {
         float var3 = this.field_2275.field_4364.getValue();
         Input var4 = field_4219.player.input;
         if (var4.movementForward == 0.0F && var4.movementSideways == 0.0F) {
            var4.movementForward = Float.intBitsToFloat(1065353216);
         }

         double[] var5 = Class_0464.method_2(field_4219.player.getYaw(RenderUtil.method_776()), var4, (double)var3);
         var1.method_7(var5[0], var5[1]);
         this.field_2275.field_4392.reset();
      }
   }

   @Override
   public void method_5(Event_16 var1) {
      if (!field_4219.player.isOnGround()) {
         var1.method_276().jumping = field_4219.player.age % 3 == 0;
      }
   }

   @Override
   public void method_9(Event_39 var1) {
   }

   @Override
   public void method_9(Event_19 var1) {
   }

   @Override
   public void method_2(Event_1 var1) {
   }

   @Override
   public void method_5(Event_4 var1) {
   }
}
