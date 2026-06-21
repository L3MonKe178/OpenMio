package me.mioclient.internal;

import java.util.ArrayDeque;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_20;
import me.mioclient.event.Subscribe;

public class Class_0972 implements MioAPI {
   public final ArrayDeque<Double> field_2988 = new ArrayDeque<>();
   public double field_1570;
   public double field_2989;
   public Timer field_2990 = new Timer();

   public Class_0972() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_1570 = 0.0;
      this.field_2989 = 0.0;
      this.field_2988.clear();
      this.field_2990.reset();
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      double var2 = this.method_876();
      if (!(var2 > 0.0) && field_4219.player.age % 4 != 0) {
         this.field_2988.pollFirst();
      } else {
         this.field_2988.add(var2 * (double)Hub.field_2596.method_537());
      }

      while (!this.field_2988.isEmpty() && this.field_2988.size() > 9) {
         this.field_2988.poll();
      }

      double var4 = 0.0;

      for (double var7 : this.field_2988) {
         var4 += var7;
      }

      this.field_1570 = this.field_2988.size() == 0 ? 0.0 : var4 / (double)this.field_2988.size();
      if (this.field_1570 < Double.longBitsToDouble(4626604192193052672L)) {
         this.field_2989 = this.field_1570;
      } else if (this.field_2990.method_9(50L)) {
         this.field_2989 = this.field_1570;
         this.field_2990.reset();
      }
   }

   public double method_874() {
      return this.field_1570;
   }

   public double method_875() {
      return this.field_2989;
   }

   public double method_876() {
      double var1 = field_4219.player.hasVehicle()
         ? field_4219.player.getVehicle().getX() - field_4219.player.getVehicle().prevX
         : field_4219.player.getX() - field_4219.player.prevX;
      double var3 = field_4219.player.hasVehicle()
         ? field_4219.player.getVehicle().getZ() - field_4219.player.getVehicle().prevZ
         : field_4219.player.getZ() - field_4219.player.prevZ;
      return Math.hypot(var1, var3) * Double.longBitsToDouble(4626322717216342016L) * Double.longBitsToDouble(4615288898129284301L);
   }
}
