package me.mioclient.event;

import me.mioclient.internal.Class_0605;

public final class Event_39 extends Class_0605 {
   public float field_1760;
   public float field_1761;
   public boolean field_1703;

   public Event_39(float var1, float var2, boolean var3) {
      super();
      this.field_1760 = var1;
      this.field_1761 = var2;
      this.field_1703 = var3;
   }

   public float method_500() {
      return this.field_1760;
   }

   public void setYaw(float var1) {
      this.field_1760 = var1;
   }

   public float method_501() {
      return this.field_1761;
   }

   public void setPitch(float var1) {
      this.field_1761 = var1;
   }

   public boolean method_1025() {
      return this.field_1703;
   }
}
