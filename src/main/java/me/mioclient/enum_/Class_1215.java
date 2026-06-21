package me.mioclient.enum_;

import java.util.Comparator;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0485;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public enum Class_1215 implements Class_0013 {
   DISTANCE("Distance", Comparator.comparing(var0 -> var0.distanceTo(Class_1309.field_4219.player))),
   CROSSHAIR("Crosshair", Comparator.comparing(var0 -> MathHelper.angleBetween(Class_1309.field_4219.player.getYaw(), Class_0485.method_14(var0)[0]))),
   HEALTH("Health", Comparator.comparing(Class_0396::method_2));

   public final String field_3803;
   public final Comparator<Entity> field_3804;

    Class_1215(String var3, Comparator<Entity> var4) {
      this.field_3803 = var3;
      this.field_3804 = var4;
   }

   @Override
   public String getName() {
      return this.field_3803;
   }

   public Comparator<Entity> method_55() {
      return this.field_3804;
   }
}
