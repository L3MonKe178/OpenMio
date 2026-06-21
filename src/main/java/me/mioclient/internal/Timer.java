package me.mioclient.internal;

import java.util.concurrent.TimeUnit;

public class Timer {
   public long field_677 = -1L;

   public Timer() {
      super();
   }

   public boolean method_2(double var1, TimeUnit var3) {
      return this.method_9(method_5(var1, var3));
   }

   public boolean method_9(long var1) {
      return System.currentTimeMillis() - this.field_677 >= var1;
   }

   public boolean method_5(long var1) {
      boolean var3 = this.method_9(var1);
      if (var3) {
         this.reset();
      }

      return var3;
   }

   public boolean method_2(long var1, TimeUnit var3) {
      return this.method_5(method_5((double)var1, var3));
   }

   public long method_271() {
      return System.currentTimeMillis() - this.field_677;
   }

   public void reset() {
      this.field_677 = System.currentTimeMillis();
   }

   public void setTime(long var1) {
      this.field_677 = var1;
   }

   public void method_9(double var1, TimeUnit var3) {
      this.field_677 = method_5(var1, var3);
   }

   public static long method_5(double var0, TimeUnit var2) {
      return switch (var2) {
         case NANOSECONDS -> (long)(var0 * Double.longBitsToDouble(4562254508917369340L) * Double.longBitsToDouble(4562254508917369340L));
         case MICROSECONDS -> (long)(var0 * Double.longBitsToDouble(4562254508917369340L));
         case MILLISECONDS -> (long)var0;
         case SECONDS -> (long)(var0 * Double.longBitsToDouble(4652007308841189376L));
         case MINUTES -> (long)(var0 * Double.longBitsToDouble(4652007308841189376L) * Double.longBitsToDouble(4633641066610819072L));
         case HOURS -> (long)(
         var0 * Double.longBitsToDouble(4652007308841189376L) * Double.longBitsToDouble(4633641066610819072L) * Double.longBitsToDouble(4633641066610819072L)
      );
         case DAYS -> (long)(
         var0
            * Double.longBitsToDouble(4652007308841189376L)
            * Double.longBitsToDouble(4633641066610819072L)
            * Double.longBitsToDouble(4633641066610819072L)
            * Double.longBitsToDouble(4627448617123184640L)
      );
      };
   }
}
