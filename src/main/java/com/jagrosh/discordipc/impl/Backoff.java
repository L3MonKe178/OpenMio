package com.jagrosh.discordipc.impl;

import java.util.Random;

public class Backoff {
   public final long minAmount;
   public final long maxAmount;
   public long current;
   public int fails;
   public final Random randGenerator;

   public Backoff(long var1, long var3) {
      super();
      this.minAmount = var1;
      this.maxAmount = var3;
      this.current = var1;
      this.fails = 0;
      this.randGenerator = new Random();
   }

   public void reset() {
      this.fails = 0;
      this.current = this.minAmount;
   }

   public long nextDelay() {
      this.fails++;
      double var1 = (double)this.current * 2.0 * this.rand01();
      this.current = Math.min(this.current + (long)var1, this.maxAmount);
      return this.current;
   }

   public double rand01() {
      return this.randGenerator.nextDouble();
   }
}
