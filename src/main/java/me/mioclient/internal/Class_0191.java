package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0156;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.record.Class_0681;
import me.mioclient.runnable.Class_1235;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.RailBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public final class Class_0191 implements MioAPI {
   public List<Class_0681> field_536 = new ArrayList<>();
   public boolean field_537;
   public Future<?> field_538;

   public Class_0191() {
      super();
      field_4220.method_14(this);
      this.field_538 = field_4221.submit(new Class_1235(this));
   }

   public boolean method_221(BlockPos var1) {
      return this.field_536.stream().anyMatch(var1x -> var1x.method_172().intersects(new Box(var1)));
   }

   public static Class_0681 method_9(BlockPos var0, Direction var1) {
      if (!method_222(var0) && method_222(var0.down()) && !method_222(var0.up())) {
         if (field_4219.world.getBlockState(var0.down()).isOf(Blocks.END_PORTAL)) {
            return null;
         } else {
            boolean var2 = method_222(var0.up(2));
            Class_0156 var3 = Class_0156.SAFE;
            Direction var4 = null;

            for (Direction var8 : Direction.values()) {
               if (var1 != var8.getOpposite() && var8 != Direction.UP) {
                  BlockPos var9 = var0.offset(var8);
                  Block var10 = field_4219.world.getBlockState(var9).getBlock();
                  if (!method_222(var9)) {
                     if (var4 != null || var1 != null) {
                        return null;
                     }

                     Class_0681 var11 = method_9(var0.offset(var8), var8);
                     if (var11 == null) {
                        return null;
                     }

                     if (!var11.method_676()) {
                        var2 = false;
                     }

                     if (var3 == Class_0156.SAFE) {
                        var3 = var11.method_675();
                     }

                     var4 = var8;
                  } else {
                     if (var10 == Blocks.RESPAWN_ANCHOR) {
                        return null;
                     }

                     if (var10.getBlastResistance() < Float.intBitsToFloat(1142292480) && var10.getBlastResistance() >= 0.0F || var10.getHardness() == 0.0F) {
                        return null;
                     }

                     if (var10.getBlastResistance() >= Float.intBitsToFloat(1142292480) && var10.getHardness() >= 0.0F) {
                        var3 = Class_0156.UNSAFE;
                     }
                  }
               }
            }

            return new Class_0681(
               var3,
               var0.toImmutable(),
               var4 == null ? new Box(var0) : new Box(var0).stretch((double)var4.getVector().getX(), 0.0, (double)var4.getVector().getZ()),
               var2
            );
         }
      } else {
         return null;
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (this.field_538.isDone() || this.field_538.isCancelled()) {
         this.field_538 = field_4221.submit(new Class_1235(this));
      }

      this.field_537 = this.method_221(Class_0382.method_425());
   }

   public static boolean method_222(BlockPos var0) {
      BlockState var1 = field_4219.world.getBlockState(var0);
      return !var1.isAir()
         && !var1.isOf(Blocks.FIRE)
         && !var1.isOf(Blocks.SOUL_FIRE)
         && !(var1.getBlock() instanceof ButtonBlock)
         && !(var1.getBlock() instanceof TorchBlock)
         && !(var1.getBlock() instanceof RailBlock)
         && !var1.isOf(Blocks.LIGHT);
   }

   public List<Class_0681> method_223() {
      return this.field_536;
   }

   public void method_39(List<Class_0681> var1) {
      this.field_536 = var1;
   }

   public boolean method_224() {
      return this.field_537;
   }
}
