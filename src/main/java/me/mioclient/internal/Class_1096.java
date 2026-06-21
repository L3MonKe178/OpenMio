package me.mioclient.internal;

import java.util.Comparator;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.module.combat.CombatmineModule;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;

public final class Class_1096 implements Class_1309 {
   public final CombatmineModule field_3408;

   public Class_1096(CombatmineModule var1) {
      super();
      this.field_3408 = var1;
   }

   public PlayerEntity method_691() {
      return field_4219.world
         .getPlayers()
         .stream()
         .filter(
            var1 -> var1.isAlive()
                  && var1 != field_4219.player
                  && field_4219.player.distanceTo(var1) <= this.field_3408.method_884()
                  && !Hub.field_2603.method_30(var1)
         )
         .filter(var1 -> !this.field_3408.field_3696.getValue() || Class_0382.method_29((LivingEntity)var1))
         .min(Comparator.comparing(var0 -> MathHelper.angleBetween(field_4219.player.getYaw(), Class_0485.method_14(var0)[0])))
         .orElse(null);
   }

   public Box method_374(PlayerEntity var1) {
      return var1.getBoundingBox().expand(-Class_0719.field_2280, 0.0, -Class_0719.field_2280).withMaxY(var1.getY());
   }
}
