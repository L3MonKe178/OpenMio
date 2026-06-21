package me.mioclient.enum_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.internal.Constants;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public enum Class_1172 implements MioAPI, Nameable {
   field_3631("Fast", new Vec3d(Constants.field_688, Constants.field_688, Constants.field_688)),
   field_3632("X2", new Vec3d(Constants.field_688, 0.05, Constants.field_688), new Vec3d(Constants.field_688, 1.0, Constants.field_688)),
   field_3633("X4", new Vec3d(0.05, 0.05, 0.05), new Vec3d(0.05, 0.05, 0.95), new Vec3d(0.95, 0.05, 0.05), new Vec3d(0.95, 0.05, 0.95)),
   field_3634(
      "X8",
      new Vec3d(0.05, 0.05, 0.05),
      new Vec3d(0.05, 0.05, 0.95),
      new Vec3d(0.05, 0.95, 0.05),
      new Vec3d(0.05, 0.95, 0.95),
      new Vec3d(0.95, 0.05, 0.05),
      new Vec3d(0.95, 0.05, 0.95),
      new Vec3d(0.95, 0.95, 0.05),
      new Vec3d(0.95, 0.95, 0.95)
   );

   public final List<Vec3d> field_3635 = new ArrayList<>();
   public final String field_3636;

    Class_1172(String var3, Vec3d... var4) {
      this.field_3636 = var3;
      this.field_3635.addAll(Arrays.asList(var4));
   }

   public List<Vec3d> method_574() {
      return this.field_3635;
   }

   public List<Vec3d> method_38(BlockPos var1) {
      return this.method_574().stream().map(var2 -> {
         var2 = var2.add(Vec3d.of(var1));
         return this == field_3633 && field_4219.player.getEyeY() > (double)(var1.getY() + 1) ? var2.add(0.0, 0.9, 0.0) : var2;
      }).collect(Collectors.toList());
   }

   public List<Vec3d> method_2(BlockPos var1, Direction var2) {
      ArrayList var3 = new ArrayList();

      for (Vec3d var5 : this.field_3635) {
         if (!this.method_2(var2.getOffsetX(), (int)var5.x) && !this.method_2(var2.getOffsetY(), (int)var5.y) && !this.method_2(var2.getOffsetZ(), (int)var5.z)
            )
          {
            var3.add(var5.add(Vec3d.of(var1)));
         }
      }

      return var3;
   }

   public boolean method_2(int var1, int var2) {
      return (var1 | ~var2) == 1;
   }

   @Override
   public String getName() {
      return this.field_3636;
   }
}
