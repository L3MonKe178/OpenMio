package me.mioclient.event;

import java.awt.Color;
import me.mioclient.internal.Class_0605;
import net.minecraft.entity.Entity;

public class Event_14 extends Class_0605 {
   public static final Event_14 field_3221 = new Event_14();
   public Entity field_581;
   public int field_3222;
   public float field_3223;
   public float field_3224;
   public float field_3225;
   public float field_3226;

   public Event_14() {
      super();
   }

   public static Event_14 method_2(Entity var0, int var1, float var2, float var3, float var4, float var5) {
      field_3221.field_3222 = var1;
      field_3221.field_581 = var0;
      field_3221.field_3223 = var2;
      field_3221.field_3224 = var3;
      field_3221.field_3225 = var4;
      field_3221.field_3226 = var5;
      field_3221.method_616();
      return field_3221;
   }

   public Entity method_11() {
      return this.field_581;
   }

   public void method_36(Entity var1) {
      this.field_581 = var1;
   }

   public float method_934() {
      return this.field_3223;
   }

   public void method_35(float var1) {
      this.field_3223 = var1;
   }

   public float method_935() {
      return this.field_3224;
   }

   public void method_107(float var1) {
      this.field_3224 = var1;
   }

   public float method_936() {
      return this.field_3225;
   }

   public void method_374(float var1) {
      this.field_3225 = var1;
   }

   public float method_937() {
      return this.field_3226;
   }

   public void method_425(float var1) {
      this.field_3226 = var1;
   }

   public int method_938() {
      return this.field_3222;
   }

   public void method_294(int var1) {
      this.field_3222 = var1;
   }

   public void method_78(Color var1) {
      this.method_35((float)var1.getRed() / 255.0F);
      this.method_107((float)var1.getGreen() / 255.0F);
      this.method_374((float)var1.getBlue() / 255.0F);
      this.method_425((float)var1.getAlpha() / 255.0F);
   }
}
