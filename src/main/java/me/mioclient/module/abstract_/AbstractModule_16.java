package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0388;
import me.mioclient.module.Category;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import nick.Settings;

public class AbstractModule_16 extends AbstractModule_32 {
   public Setting<Class_0388> field_1331;
   public Setting<Boolean> field_1332;
   public Setting<Float> field_1333;

   public AbstractModule_16() {
      super("Blocker", "Blocks certain offsets to prevent crystal damage.", Category.COMBAT);
      Settings.initialize(this);
      this.field_4255.method_39(false);
   }

   @Override
   public String getInfo() {
      return (this.field_1331.getValue() != null ? this.field_1331.getValue().getName() : "");
   }

   @Override
   public List<BlockPos> method_17() {
      ArrayList var1 = new ArrayList();

      if (this.field_1331 == null || this.field_1331.getValue() == null) return null;
      for (Vec3i var5 : (this.field_1331.getValue() != null ? this.field_1331.getValue().method_440() : new Vec3i[0])) {
         BlockPos var6 = BlockPos.ofFloored(field_4219.player.getPos()).add(var5);
         if (Hub.field_2629.method_251(var6) >= this.field_1333.getValue()) {
            var1.add(var6);
         }
      }

      ArrayList var10 = new ArrayList();

      for (BlockPos var12 : (Iterable<BlockPos>)(Iterable<?>)(var1)) {
         for (Vec3i var8 : Class_0388.field_1258.method_440()) {
            if (var12.getY() != (int)field_4219.player.getPos().getY()) {
               break;
            }

            BlockPos var9 = BlockPos.ofFloored(field_4219.player.getPos());
            if (this.field_1332.getValue()) {
               if (!var12.add(var8).equals(var9)) {
                  var10.add(var12.add(var8));
               }
            } else if (var12.subtract(var9).equals(var8)) {
               var10.add(var12.add(var8));
               break;
            }
         }

         if ((double)var12.getY() == field_4219.player.getY() + Double.longBitsToDouble(4613937818241073152L)) {
            var10.add(var12.down());
         } else {
            var10.add(var12.up());
         }
      }

      return var10;
   }
}
