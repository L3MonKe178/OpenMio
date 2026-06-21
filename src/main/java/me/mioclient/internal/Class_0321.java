package me.mioclient.internal;

import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.util.math.Direction.Axis;

public class Class_0321 extends Class_0716 {
   public long field_303;
   public float field_1086;
   public final Timer field_1087 = new Timer();

   public Class_0321(ElytraFlyModule var1) {
      super(var1);
   }

   @Override
   public void onEnable() {
      this.field_1086 = 0.0F;
      this.field_303 = System.currentTimeMillis();
   }

   @Override
   public void method_2(Event_1 var1) {
   }

   @Override
   public void method_2(Event_9 var1) {
      if (this.field_2275.method_1178()) {
         if (!Class_0464.method_363()) {
            this.field_303 = System.currentTimeMillis();
         }

         if (this.field_2275.field_4374.getValue()) {
            this.field_1086 = (float)Class_0464.method_2(
               (double)this.field_2275.field_4364.getValue().floatValue(),
               (double)this.field_2275.field_4376.getValue().floatValue(),
               (double)this.field_2275.field_4377.getValue().floatValue(),
               this.field_303
            );
         } else {
            this.field_1086 = this.field_2275.field_4364.getValue();
         }

         double[] var2 = Class_0464.method_2(var1, (double)this.field_1086);
         float var3 = -this.field_2275.field_4369.getValue();
         field_4219.player.setVelocity(var2[0], (double)var3, var2[1]);
         var1.setY((double)var3);
         if (field_4219.player.input.sneaking) {
            var1.setY(Double.longBitsToDouble(-4620693217682128896L));
            field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, Double.longBitsToDouble(-4620693217682128896L)));
         }

         PacketUtil.method_1099();
         if (this.field_2275.field_4372.getValue() && var1.method_380() == 0.0 && var1.method_396() == 0.0 && this.field_1087.method_9(2500L)) {
            var1.method_7(
               Math.sin(Math.toRadians((double)(field_4219.player.age % Constants.field_686))) * Double.longBitsToDouble(4584304132692975288L),
               Math.cos(Math.toRadians((double)(field_4219.player.age % Constants.field_686))) * Double.longBitsToDouble(4584304132692975288L)
            );
         } else if (Class_0464.method_363()) {
            this.field_1087.reset();
         }
      }
   }

   @Override
   public void method_5(Event_4 var1) {
   }

   @Override
   public void method_2(Event_10 var1) {
   }

   @Override
   public void method_9(Event_19 var1) {
   }

   @Override
   public void method_2(Event_17 var1) {
   }

   @Override
   public void method_9(Event_39 var1) {
   }
}
