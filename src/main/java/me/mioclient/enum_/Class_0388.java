package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import net.minecraft.util.math.Vec3i;

public enum Class_0388 implements Nameable {
   field_1257(
      "Full",
      new Vec3i(1, 0, 0),
      new Vec3i(0, 0, 1),
      new Vec3i(-1, 0, 0),
      new Vec3i(0, 0, -1),
      new Vec3i(1, 1, 0),
      new Vec3i(0, 1, 1),
      new Vec3i(-1, 1, 0),
      new Vec3i(0, 1, -1),
      new Vec3i(0, 2, 0),
      new Vec3i(0, 3, 0)
   ),
   field_1258("Surround", new Vec3i(1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(-1, 0, 0), new Vec3i(0, 0, -1)),
   field_1259("Cev", new Vec3i(0, 2, 0), new Vec3i(0, 3, 0));

   public final String field_1260;
   public final Vec3i[] field_1261;

    Class_0388(String var3, Vec3i... var4) {
      this.field_1260 = var3;
      this.field_1261 = var4;
   }

   @Override
   public String getName() {
      return this.field_1260;
   }

   public Vec3i[] method_440() {
      return this.field_1261;
   }
}
