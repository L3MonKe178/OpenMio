package me.mioclient.enum_;

import java.util.function.Function;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Class_0396;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public enum Class_1045 implements MioAPI {
   DISTANCE(var0 -> var0.squaredDistanceTo(field_4219.player)),
   HEALTH(var0 -> (double)Class_0396.method_2((Entity)var0));

   public final Function<LivingEntity, Double> field_3230;

    Class_1045(Function<LivingEntity, Double> var3) {
      this.field_3230 = var3;
   }

   public Function<LivingEntity, Double> method_939() {
      return this.field_3230;
   }
}
