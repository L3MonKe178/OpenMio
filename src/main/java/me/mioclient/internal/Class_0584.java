package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Objects;
import me.mioclient.api.Class_1309;
import me.mioclient.record.Class_0362;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class Class_0584 implements Class_1309 {
   public final Class_0585 progress = new Class_0585(Float.intBitsToFloat(1073741824));
   public final ItemStack field_1846;
   public final Class_0362 field_1847;
   public final Class_0362 field_1848;

   public Class_0584(ItemStack var1, Class_0362 var2, Class_0362 var3) {
      super();
      this.field_1846 = var1.copy();
      this.field_1847 = var3;
      this.field_1848 = var2;
      this.progress.method_36(false);
   }

   public void method_5(DrawContext var1) {
      this.progress.method_3(true);
      float var2 = this.progress.method_45();
      int var3 = (int)((float)this.field_1848.method_401() + (float)(this.field_1847.method_401() - this.field_1848.method_401()) * var2);
      int var4 = (int)((float)this.field_1848.method_402() + (float)(this.field_1847.method_402() - this.field_1848.method_402()) * var2);
      var1.getMatrices().push();
      if ((double)var2 > Double.longBitsToDouble(4605380978949069210L)) {
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216),
            Float.intBitsToFloat(1065353216),
            Float.intBitsToFloat(1065353216),
            Float.intBitsToFloat(1065353216) - (var2 - Float.intBitsToFloat(1061997773)) / Float.intBitsToFloat(1045220557)
         );
      }

      var1.drawItem(this.field_1846, var3, var4, 0, 100);
      var1.drawItemInSlot(field_4219.textRenderer, this.field_1846, var3, var4);
      if ((double)var2 > Double.longBitsToDouble(4605380978949069210L)) {
         RenderSystem.setShaderColor(
            Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1065353216)
         );
      }

      var1.getMatrices().pop();
   }

   public ItemStack method_602() {
      return this.field_1846;
   }

   public Class_0362 method_603() {
      return this.field_1847;
   }

   public boolean method_604() {
      return this.progress.method_605() == Float.intBitsToFloat(1073741824);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class_0584 var2 = (Class_0584)var1;
         return Objects.equals(this.field_1846.getItem(), var2.field_1846.getItem()) && Objects.equals(this.field_1847, var2.field_1847);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field_1846.getItem(), this.field_1847);
   }
}
