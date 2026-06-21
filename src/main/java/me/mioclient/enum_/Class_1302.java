package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0136;

public enum Class_1302 implements Class_1309, Class_0013 {
   NONE("None"),
   NORMAL("Normal") {
      @Override
      public void method_9(int var1, Runnable var2) {
         Class_0136.method_16(var1);
         var2.run();
      }
   },
   SILENT("Silent") {
      @Override
      public void method_9(int var1, Runnable var2) {
         int var3 = field_4219.player.getInventory().selectedSlot;
         Class_0136.method_16(var1);
         var2.run();
         Class_0136.method_16(var3);
      }
   };

   public final String field_4210;

    Class_1302(String var3) {
      this.field_4210 = var3;
   }

   @Override
   public String getName() {
      return this.field_4210;
   }

   public void method_9(int var1, Runnable var2) {
      if (field_4219.player.getInventory().selectedSlot == var1) {
         var2.run();
      }
   }
}
