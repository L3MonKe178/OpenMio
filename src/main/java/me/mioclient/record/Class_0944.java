package me.mioclient.record;

import net.minecraft.block.Block;

public final class Class_0944 {
   public final long field_2948;
   public final Block field_2949;

   public Class_0944(long var1, Block var3) {
      super();
      this.field_2948 = var1;
      this.field_2949 = var3;
   }

   public static Class_0944 method_31(Block var0) {
      return new Class_0944(System.currentTimeMillis(), var0);
   }

   public long method_417() {
      return this.field_2948;
   }

   public Block method_862() {
      return this.field_2949;
   }
}
