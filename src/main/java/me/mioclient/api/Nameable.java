package me.mioclient.api;

import net.minecraft.text.Text;

public interface Nameable {
   String getName();

   default Nameable method_14(String var1) {
      return () -> var1;
   }

   default Text method_15() {
      return Text.literal(this.getName());
   }

   static boolean method_16(Object var0) {
      return var0 instanceof Nameable;
   }
}
