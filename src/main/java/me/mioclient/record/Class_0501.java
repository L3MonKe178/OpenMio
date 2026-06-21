package me.mioclient.record;

import java.util.Objects;
import me.mioclient.internal.Class_0447;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class Class_0501 {
   public final int field_1578;
   public final String field_1579;

   public Class_0501(int var1, String var2) {
      super();
      this.field_1578 = var1;
      this.field_1579 = var2;
   }

   public boolean method_16(ItemStack var1) {
      return Item.getRawId(var1.getItem()) == this.field_1578 && Objects.equals(Class_0447.method_31(var1), this.field_1579);
   }

   public int method_530() {
      return this.field_1578;
   }

   public String method_531() {
      return this.field_1579;
   }
}
