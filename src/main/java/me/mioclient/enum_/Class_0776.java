package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponent.StatusEffectEntry;
import net.minecraft.entity.effect.StatusEffects;

public enum Class_0776 implements Class_0013 {
   HUNGER("Hunger") {
      @Override
      public double method_2(FoodComponent var1) {
         return (double)var1.nutrition();
      }
   },
   SATURATION("Saturation") {
      @Override
      public double method_2(FoodComponent var1) {
         return (double)var1.saturation();
      }
   },
   HEALTH("Health") {
      @Override
      public double method_2(FoodComponent var1) {
         for (StatusEffectEntry var3 : var1.effects()) {
            if (var3.effect().getEffectType() == StatusEffects.REGENERATION) {
               return (double)var3.effect().getAmplifier();
            }
         }

         return 0.0;
      }
   };

   public final String field_2437;

    Class_0776(String var3) {
      this.field_2437 = var3;
   }

   @Override
   public String getName() {
      return this.field_2437;
   }

   public abstract double method_2(FoodComponent var1);
}
