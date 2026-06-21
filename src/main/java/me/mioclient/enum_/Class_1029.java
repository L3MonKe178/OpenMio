package me.mioclient.enum_;

import me.mioclient.Hub;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0396;
import me.mioclient.module.combat.AutoCrystalModule;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public enum Class_1029 implements Class_1309, Class_0013 {
   PLACE("Place"),
   HIT("Hit");

   public final String field_3183;
   public static AutoCrystalModule field_3184 = Hub.field_2595.method_78(AutoCrystalModule.class);

    Class_1029(String var3) {
      this.field_3183 = var3;
   }

   public double method_34(PlayerEntity var1) {
      if (field_3184.field_4141) {
         return 0.1;
      } else if (!field_3184.field_4084.getValue()
         || !(Class_0396.method_2((Entity)var1) <= field_3184.field_4085.getValue())
            && !(Class_0136.method_2(var1) <= (float)field_3184.field_4086.getValue().intValue())) {
         if (field_4219.world.getBlockState(field_4219.player.getBlockPos()).isOf(Blocks.NETHER_PORTAL)) {
            return 0.6;
         } else {
            double var2 = (double)field_3184.field_4054.getValue().floatValue();
            double var4 = (double)field_3184.field_4071.getValue().floatValue();
            if (field_3184.field_4054.method_468()) {
               var2 = (double)Class_0396.method_2((Entity)var1);
            }

            if (field_3184.method_1141()) {
               var2 = Math.min(var2, 5.0);
               var4 = 3.0;
            }

            return Math.min(this == HIT ? var4 : var2, (double)Class_0396.method_2((Entity)var1));
         }
      } else {
         return 0.6;
      }
   }

   public double method_924() {
      return field_3184.method_1140() ? 999.0 : (double)(this == HIT ? field_3184.field_4072.getValue() : field_3184.field_4055.getValue()).floatValue();
   }

   public boolean method_2(PlayerEntity var1, double var2, double var4, Class_0247 var6) {
      boolean var7 = field_4219.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING) || field_4219.player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING);
      boolean var8 = var6.equals(Class_0247.KILL);
      if (field_3184.field_4106.getValue()) {
         return false;
      } else {
         if (field_3184.method_1140()) {
            var2 = 0.0;
         }

         if (!(var2 >= (double)Class_0396.method_76()) || !field_3184.field_4073.getValue() || var8 && var7) {
            if (var8) {
               return false;
            } else {
               return this == PLACE && var2 > 0.0 && var4 / var2 < (double)field_3184.field_4056.getValue().floatValue()
                  ? true
                  : !(var4 >= (double)Class_0396.method_2((Entity)var1)) && (!(var4 >= this.method_34(var1)) || !(var2 <= this.method_924()));
            }
         } else {
            return true;
         }
      }
   }

   @Override
   public String getName() {
      return this.field_3183;
   }
}
