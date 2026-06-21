package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.record.Class_0371;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0040 extends Class_0746 {
   public final Class_0311 field_79;
   public boolean field_80;

   public Class_0040(Class_0311 var1) {
      super("List");
      this.field_79 = var1;
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.field_80) {
         for (Class_0937 var8 : this.field_2378) {
            var8.method_4(var3, var5);
         }

         this.method_142();
         this.field_80 = false;
      }

      super.method_2(var1, var2, var3, var5);
   }

   public void method_56() {
      this.field_80 = true;
      this.field_2378.clear();

      for (Class_0371 var2 : Hub.field_2597.method_2(this.field_79.method_369()).getRegistry()) {
         this.field_2378.add(new Class_1062(this.field_79, this, var2));
      }
   }
}
