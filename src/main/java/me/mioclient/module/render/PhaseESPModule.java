package me.mioclient.module.render;

import java.awt.Color;
import me.mioclient.enum_.Class_0803;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1081;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0890;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import nick.Settings;

public class PhaseESPModule extends Module {
   public Setting<Color> field_3496;
   public Setting<Color> field_3497;
   public Setting<Color> field_3498;
   public Setting<Boolean> field_3499;
   public Setting<Float> field_3500;

   public PhaseESPModule() {
      super("PhaseESP", "Highlights safe blocks to phase into.", Category.RENDER);
      Settings.initialize(this);
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      Box var2 = field_4219.player
         .getBoundingBox()
         .withMaxY(field_4219.player.getBoundingBox().minY)
         .expand(Double.longBitsToDouble(4576918229304087675L), 0.0, Double.longBitsToDouble(4576918229304087675L));
      if (!Class_1035.method_16(field_4219.player.getBlockPos()) && !field_4219.player.isInSwimmingPose()) {
         BlockPos.stream(var2)
            .filter(this::method_1011)
            .map(var1x -> new Class_0890(var1x, this.method_473(var1x)))
            .forEach(
               var2x -> {
                  VoxelShape var3 = field_4219.world.getBlockState(var2x.field_2816).getOutlineShape(field_4219.world, var2x.field_2816);
                  if (var3.isEmpty()) {
                     var3 = VoxelShapes.cuboid(
                        0.0,
                        0.0,
                        0.0,
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4607182418800017408L)
                     );
                  }

                  Box var4 = var3.getBoundingBox().offset(var2x.field_2816);
                  Class_0612.method_5(var1.method_10(), var4.withMaxY((double)var2x.field_2816.getY()), var2x.field_2817.method_2(this));
                  if (this.field_3499.getValue()) {
                     Class_0612.method_2(
                        var1.method_10(),
                        var4.withMaxY((double)var2x.field_2816.getY()),
                        Class_1081.method_2(var2x.field_2817.method_2(this), this.field_3500.getValue()),
                        Float.intBitsToFloat(1065353216)
                     );
                  }
               }
            );
      }
   }

   public Class_0803 method_473(BlockPos var1) {
      boolean var2 = field_4219.world.getBlockState(var1).getBlock().getBlastResistance() >= Float.intBitsToFloat(1142292480);
      if (!Class_1035.method_16(var1.down())
         || !var2
         || field_4219.world.getBlockState(var1.down()).getBlock().getBlastResistance() < Float.intBitsToFloat(1142292480)) {
         return Class_0803.UNSAFE;
      } else {
         return !this.method_287(var1) && !this.method_287(var1.down()) ? Class_0803.SAFE : Class_0803.SEMI;
      }
   }

   public boolean method_287(BlockPos var1) {
      return Class_1035.method_30(var1) != Blocks.BEDROCK;
   }

   public boolean method_1011(BlockPos var1) {
      BlockState var2 = field_4219.world.getBlockState(var1);
      return var2.getBlock().getBlastResistance() >= Float.intBitsToFloat(1142292480) && Class_1035.method_16(var1) && !var2.isReplaceable();
   }
}
