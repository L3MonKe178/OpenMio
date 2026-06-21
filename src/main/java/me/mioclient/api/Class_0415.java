package me.mioclient.api;

import me.mioclient.enum_.Class_0304;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface Class_0415 {
   long mio$getLastEatingTime();

   Class_0304 mio$getRole();

   void mio$setRole(Class_0304 var1);

   boolean mio$isNextToWall();

   default float method_2(ItemStack var1, LivingEntity var2) {
      return (float)(System.currentTimeMillis() - this.mio$getLastEatingTime()) / ((float)var1.getMaxUseTime(var2) * 50.0F + 50.0F);
   }
}
