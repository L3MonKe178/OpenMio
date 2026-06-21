package me.mioclient.event;

import me.mioclient.enum_.PreType;
import me.mioclient.internal.Class_0605;

public final class Event_19 extends Class_0605 {
   public final PreType field_2304;
   public final double field_2305;
   public final double field_2306;
   public final double field_2307;
   public final float field_2308;
   public final float field_2309;
   public final boolean field_2310;
   public double field_608;
   public double field_609;
   public double field_2311;
   public float field_2312;
   public float field_2313;
   public boolean field_2314;
   public boolean field_2315;

   public Event_19() {
      this(PreType.PRE, 0.0, 0.0, 0.0, 0.0F, 0.0F, false);
   }

   public Event_19(PreType var1, Event_19 var2) {
      this(var1, var2.field_608, var2.field_609, var2.field_2311, var2.field_2312, var2.field_2313, var2.field_2314);
   }

   public Event_19(PreType var1, double var2, double var4, double var6, float var8, float var9, boolean var10) {
      super();
      this.field_2304 = var1;
      this.field_608 = var2;
      this.field_609 = var4;
      this.field_2311 = var6;
      this.field_2312 = var8;
      this.field_2313 = var9;
      this.field_2314 = var10;
      this.field_2305 = var2;
      this.field_2306 = var4;
      this.field_2307 = var6;
      this.field_2308 = var8;
      this.field_2309 = var9;
      this.field_2310 = var10;
   }

   public double method_698() {
      return this.field_2305;
   }

   public double method_699() {
      return this.field_2306;
   }

   public double method_700() {
      return this.field_2307;
   }

   public float method_701() {
      return this.field_2308;
   }

   public float method_702() {
      return this.field_2309;
   }

   public boolean method_703() {
      return this.field_2310;
   }

   public float method_704() {
      return this.field_2312;
   }

   public float method_705() {
      return this.field_2313;
   }

   public boolean method_706() {
      return this.field_2315;
   }

   public double method_380() {
      return this.field_608;
   }

   public void setX(double var1) {
      this.field_2315 = true;
      this.field_608 = var1;
   }

   public double method_395() {
      return this.field_609;
   }

   public void setY(double var1) {
      this.field_2315 = true;
      this.field_609 = var1;
   }

   public double method_396() {
      return this.field_2311;
   }

   public void setZ(double var1) {
      this.field_2315 = true;
      this.field_2311 = var1;
   }

   public float method_500() {
      return this.field_2312;
   }

   public void setYaw(float var1) {
      this.field_2315 = true;
      this.field_2312 = var1;
   }

   public float method_501() {
      return this.field_2313;
   }

   public void setPitch(float var1) {
      this.field_2315 = true;
      this.field_2313 = var1;
   }

   public void method_5(float[] var1) {
      if (var1 != null && var1.length == 2) {
         this.setYaw(var1[0]);
         this.setPitch(var1[1]);
      }
   }

   public boolean method_707() {
      return this.field_2314;
   }

   public void setOnGround(boolean var1) {
      this.field_2315 = true;
      this.field_2314 = var1;
   }

   public PreType method_213() {
      return this.field_2304;
   }
}
