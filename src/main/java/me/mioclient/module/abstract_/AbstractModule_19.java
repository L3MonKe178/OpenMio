package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import me.mioclient.Hub;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_1225;
import me.mioclient.module.Category;
import me.mioclient.module.player.SpeedMineModule;
import me.mioclient.setting.Setting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import nick.Settings;

public class AbstractModule_19 extends AbstractModule_32 {
   public static final SpeedMineModule field_3651 = Hub.field_2595.method_78(SpeedMineModule.class);
   public Setting<Float> field_3652;
   public Setting<Boolean> field_3653;
   public Setting<Boolean> field_3654;
   public Setting<Boolean> field_3655;
   public Setting<Boolean> field_3656;
   public Setting<Boolean> field_3657;
   public Setting<Integer> field_3658;
   public Setting<Boolean> field_3659;
   public Setting<Boolean> field_3660;
   public Setting<Boolean> field_3661;
   public PlayerEntity field_2290;

   public AbstractModule_19() {
      super("WebAura", "Traps your enemies with cobwebs.", Category.COMBAT, "autoweb");
      Settings.initialize(this);
      this.field_4255.method_78(false);
      this.field_4253.method_78(false);
      this.unregister(this.field_4254);
      this.unregister(this.field_4255);
      this.unregister(this.field_4256);
      this.unregister(this.field_4257);
      this.unregister(this.field_4253);
   }

   @Override
   public boolean method_107() {
      return false;
   }

   @Override
   public String getInfo() {
      return this.field_2290 != null ? this.field_2290.getGameProfile().getName() : null;
   }

   @Override
   public List<BlockPos> method_17() {
      this.field_2290 = this.method_691();
      if (this.field_2290 != null && (this.field_3653.getValue() || !field_4219.player.isUsingItem())) {
         ArrayList<BlockPos> var1 = new ArrayList<>();
         Box var2 = field_4219.player.getBoundingBox();
         if (this.field_3657.getValue()) {
            var2 = Hub.field_2612.method_2(this.field_2290, Class_0719.method_2(this.field_2290, this.field_3658));
         }

         BlockPos var3 = BlockPos.ofFloored(Class_0719.method_2(var2));
         if (this.field_3661.getValue() && var2.minY - (double)var3.getY() <= Class_0245.field_688) {
            var1.add(var3.down());
            var1.add(var3);
         }

         if (this.field_3660.getValue()) {
            var1.add(var3.up());
         }

         var1.removeIf(
            var1x -> !field_4219.world.getBlockState(var1x).isReplaceable()
                  || !field_4219.world.getFluidState(var1x).isEmpty()
                  || !this.method_8(var1x)
                  || !Class_1225.method_2(var1x) && this.field_4251.getValue()
         );
         return var1;
      } else {
         return Collections.emptyList();
      }
   }

   @Override
   public int method_34() {
      return Class_0136.method_5(Items.COBWEB);
   }

   @Override
   public void method_2(BlockPos var1, boolean var2) {
      if (var1.equals(field_3651.method_1121())) {
         field_3651.method_1116();
      }
   }

   public boolean method_8(BlockPos var1) {
      if (!this.field_3656.getValue()) {
         return true;
      } else {
         Box var2 = new Box(var1);
         boolean var3 = field_4219.player.getBoundingBox().intersects(var2);
         if (this.field_3657.getValue()) {
            for (int var4 = this.field_3658.getValue(); var4 >= 0; var4--) {
               if (Hub.field_2612.method_2(field_4219.player, Class_0719.method_2(field_4219.player, this.field_3658)).intersects(var2)) {
                  var3 = true;
                  break;
               }
            }
         }

         return !var3;
      }
   }

   public PlayerEntity method_691() {
      return field_4219.world
         .getPlayers()
         .stream()
         .filter(
            var1 -> {
               boolean var2 = !this.field_3655.getValue() || var1.isInSwimmingPose();
               return var2
                  && var1 != field_4219.player
                  && !Objects.equals(var1.getName().getString(), field_4219.player.getGameProfile().getName())
                  && !Hub.field_2603.method_1009(var1.getName().getString())
                  && var1.isAlive()
                  && !var1.isDead()
                  && (!this.field_3654.getValue() || Class_0382.method_29((LivingEntity)var1))
                  && var1.distanceTo(field_4219.player) <= this.field_3652.getValue();
            }
         )
         .min(Comparator.comparing(var0 -> var0.squaredDistanceTo(field_4219.player)))
         .orElse(null);
   }
}
