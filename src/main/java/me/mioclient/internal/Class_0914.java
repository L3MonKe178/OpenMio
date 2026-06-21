package me.mioclient.internal;

import me.mioclient.mixin.ducks.DuckWorldRenderer;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.WorldRenderer;

public abstract class Class_0914 extends Class_0726 {
   public Framebuffer field_2895;

   public Class_0914() {
      super();
   }

   @Override
   public void method_696() {
      WorldRenderer var1 = field_4219.worldRenderer;
      DuckWorldRenderer var2 = (DuckWorldRenderer)var1;
      this.field_2895 = var1.getEntityOutlinesFramebuffer();
      var2.setEntityOutlinesFramebuffer(this.field_2302);
   }

   @Override
   public void method_697() {
      if (this.field_2895 != null) {
         WorldRenderer var1 = field_4219.worldRenderer;
         DuckWorldRenderer var2 = (DuckWorldRenderer)var1;
         var2.setEntityOutlinesFramebuffer(this.field_2895);
         this.field_2895 = null;
      }
   }

   public void method_434() {
      this.method_39(() -> this.field_2301.draw());
   }
}
