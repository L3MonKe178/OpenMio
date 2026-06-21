package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Event_42 extends Event {
   public final Screen field_3088;
   public DrawContext field_2999;
   public float field_465;
   public float field_466;
   public Runnable field_3089 = null;

   public Event_42(Screen var1) {
      super();
      this.field_3088 = var1;
   }

   public Screen method_562() {
      return this.field_3088;
   }

   public MatrixStack method_10() {
      return this.field_2999.getMatrices();
   }

   public float method_59() {
      return this.field_465;
   }

   public float method_60() {
      return this.field_466;
   }

   public DrawContext method_881() {
      return this.field_2999;
   }

   public void method_9(DrawContext var1) {
      this.field_2999 = var1;
   }

   public void setX(float var1) {
      this.field_465 = var1;
   }

   public void setY(float var1) {
      this.field_466 = var1;
   }

   public void method_31(Runnable var1) {
      this.field_3089 = var1;
   }

   public void method_273() {
      if (this.field_3089 != null) {
         this.field_3089.run();
      }
   }
}
