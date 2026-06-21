package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0499;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.Category;
import me.mioclient.record.Class_0014;
import me.mioclient.setting.Setting;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos.Mutable;
import nick.Settings;

public class AbstractModule_30 extends AbstractModule_32 {
   public static final Mutable field_347 = new Mutable();
   public Setting<Float> field_348;
   public Setting<Boolean> field_349;
   public boolean field_350;

   public AbstractModule_30() {
      super("AnvilAura", "Drops anvils on your enemies.", Category.COMBAT, "autoanvil");
      Settings.initialize(this);
      this.unregister(this.field_4256);
   }

   @Override
   public List<BlockPos> method_17() {
      ArrayList var1 = new ArrayList();
      this.field_350 = false;
      Class_0014 var2 = this.method_137();
      if (var2 == null) {
         return var1;
      } else {
         if (var2.method_19() == Class_0499.CAGE) {
            for (int var3 = 0; var3 < 3; var3++) {
               var1.add(Class_0382.method_9(var2.method_18()).offset(var2.method_20()).up(var3));
            }
         } else {
            var1.add(Class_0382.method_9(var2.method_18()).up(2));
            this.field_350 = true;
         }

         return var1;
      }
   }

   @Override
   public int method_34() {
      return this.field_350 ? Class_0136.method_5(Items.ANVIL) : super.method_34();
   }

   public Class_0014 method_137() {
      double var1 = Double.longBitsToDouble(9218868437227405311L);
      Class_0014 var3 = null;

      for (AbstractClientPlayerEntity var5 : field_4219.world.getPlayers()) {
         if (var5.isAlive() && field_4219.player != var5 && !Hub.field_2603.method_30(var5) && (!this.field_349.getValue() || Class_0382.method_29(var5))) {
            BlockPos var6 = Class_0382.method_9(var5).up(2);
            double var7 = field_4219.player.getEyePos().distanceTo(var5.getPos());
            if (!(var7 > (double)this.field_348.getValue().floatValue())
               && field_4219.world.getBlockState(var6.down()).isReplaceable()
               && Class_1035.method_7(var6, false)) {
               Direction var9 = Class_1035.method_9(var6, this.field_4250.getValue());
               Class_0014 var10;
               if (var9 != null) {
                  var10 = new Class_0014(var5, Class_0499.ANVIL, null);
               } else {
                  Direction var11 = this.method_138(var6);
                  if (var11 == null) {
                     continue;
                  }

                  var10 = new Class_0014(var5, Class_0499.CAGE, var11);
               }

               if (var7 < var1) {
                  var3 = var10;
                  var1 = var7;
               }
            }
         }
      }

      return var3;
   }

   public Direction method_138(BlockPos var1) {
      List var2 = Class_1035.method_39(var1);
      int var3 = 1337;
      Direction var4 = null;

      for (Direction var8 : Direction.values()) {
         if (var2.contains(var8.getOpposite()) || !this.field_4250.getValue()) {
            int var9 = 0;

            for (int var10 = 0; var10 < 3; var10++) {
               field_347.set(var1.getX() + var8.getOffsetX(), var1.getY() - var10, var1.getZ() + var8.getOffsetZ());
               if (!field_4219.world.getBlockState(field_347).isReplaceable()) {
                  var9 = var10;
                  break;
               }

               var9 = -1;
            }

            if (var9 != -1 && var9 < var3) {
               var3 = var9;
               var4 = var8;
            }
         }
      }

      return var4;
   }
}
