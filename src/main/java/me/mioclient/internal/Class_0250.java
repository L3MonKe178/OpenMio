package me.mioclient.internal;

import net.minecraft.entity.passive.AnimalEntity;

public final class Class_0250 {
   public final AnimalEntity field_712;
   public int field_713;

   public Class_0250(AnimalEntity var1) {
      super();
      this.field_712 = var1;
      this.field_713 = 0;
   }

   public void method_273() {
      this.field_713++;
   }

   public AnimalEntity method_274() {
      return this.field_712;
   }

   public int method_275() {
      return this.field_713;
   }
}
