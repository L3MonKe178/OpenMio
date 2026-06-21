package me.mioclient.runnable;

import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0136;
import me.mioclient.module.player.SpeedMineModule;
import net.minecraft.item.Item;

public class Class_1282 implements Runnable, Class_1309 {
   public final SpeedMineModule field_4152;
   public final long field_4153;
   public final Item field_4154;
   public final int field_4155;
   public final int field_4156;

   public Class_1282(SpeedMineModule var1, Item var2, int var3, int var4) {
      super();
      this.field_4154 = var2;
      this.field_4155 = var3;
      this.field_4156 = var4;
      this.field_4153 = System.currentTimeMillis();
      this.field_4152 = var1;
   }

   @Override
   public void run() {
      if (this.field_4152.method_1122() == null || this.field_4152.method_1122().equals(this)) {
         if (!field_4219.player.getInventory().getStack(this.field_4156).isOf(this.field_4154)) {
            Class_0136.method_39(this.field_4155);
         }
      }
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof Class_1282 var2 ? var2.field_4153 == this.field_4153 : false;
   }
}
