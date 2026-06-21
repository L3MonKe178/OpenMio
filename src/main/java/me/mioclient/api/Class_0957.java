package me.mioclient.api;

import java.util.Collections;
import me.mioclient.enum_.Class_0694;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public interface Class_0957 {
   Class_0957 field_2968 = (var0, var1) -> Class_0694.NONE;
   Class_0957 field_2969 = (var0, var1) -> var1.getBlock().getBlastResistance() < 600.0F ? Class_0694.IGNORE : Class_0694.NONE;

   Class_0694 test(BlockPos var1, BlockState var2);

   static Class_0957 method_107(BlockPos var0) {
      return method_2(Collections.singleton(var0));
   }

   static Class_0957 method_2(Iterable<BlockPos> var0) {
      return (var1, var2) -> {
         for (BlockPos var4 : var0) {
            if (var1.equals(var4)) {
               return Class_0694.IGNORE;
            }
         }

         return Class_0694.NONE;
      };
   }

   static Class_0957 method_374(BlockPos var0) {
      return method_9(Collections.singleton(var0));
   }

   static Class_0957 method_9(Iterable<BlockPos> var0) {
      return (var1, var2) -> {
         for (BlockPos var4 : var0) {
            if (var1.equals(var4)) {
               return Class_0694.SOLID;
            }
         }

         return Class_0694.NONE;
      };
   }

   static Class_0957 method_9(Class_0957... var0) {
      return (var1, var2) -> {
         for (Class_0957 var6 : var0) {
            Class_0694 var7 = var6.test(var1, var2);
            if (var7 != Class_0694.NONE) {
               return var7;
            }
         }

         return Class_0694.NONE;
      };
   }
}
