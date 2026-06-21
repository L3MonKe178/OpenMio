package me.mioclient.module.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0376;
import me.mioclient.enum_.Class_0626;
import me.mioclient.enum_.Class_1213;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.record.Class_0455;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nick.Settings;

public class NukerModule extends Module {
   public static final AbstractModule_28 field_3146 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static final ItemSaverModule field_3147 = Hub.field_2595.method_78(ItemSaverModule.class);
   public static final SpeedMineModule field_3148 = Hub.field_2595.method_78(SpeedMineModule.class);
   public Setting<Class_0626> field_3149;
   public Setting<Class_1213> field_3150;
   public Setting<Class_0376> field_3151;
   public Setting<Integer> field_3152;
   public Setting<Float> field_3153;
   public Setting<Float> field_3154;
   public Setting<Boolean> field_3155;
   public Setting<Boolean> field_3156;
   public Setting<Boolean> field_3157;
   public Setting<Boolean> field_3158;
   public Setting<Boolean> field_3159;
   public Setting<Set<Block>> field_3160;
   public final List<Class_0455> field_3161;
   public final Timer field_3162;
   public final Timer field_3163;
   public BlockPos field_3164;
   public int field_3165;

   public NukerModule() {
      super("Nuker", "Breaks blocks nearby.", Category.PLAYER);
      Settings.initialize(this);
      this.field_3161 = new ArrayList<>();
      this.field_3162 = new Timer();
      this.field_3163 = new Timer();
      this.field_3164 = null;
      this.field_3159.method_334();
   }

   @Override
   public String getInfo() {
      return FontRenderer.method_3(this.field_3149.getValue());
   }

   @Override
   public void onDisable() {
      field_4219.options.attackKey.setPressed(false);
   }

   @Subscribe
   public void method_38(Event_7 var1) {
      if (!this.method_535()) {
         this.field_3161.removeIf(var0 -> System.currentTimeMillis() - var0.field_1456 > 1000L);
         this.field_3164 = null;
         this.field_3165 = 0;
         if (this.field_3163.method_9((long)(this.field_3152.getValue() != null ? this.field_3152.getValue().intValue() : 0))) {
            if (!field_3148.isToggled() || field_3148.method_4((long)(this.field_3152.getValue() != null ? this.field_3152.getValue().intValue() : 0))) {
               boolean var2 = this.field_3157.getValue() || field_3148.field_3990.getValue();
               List<BlockPos> var3 = (this.field_3150.getValue() != null ? this.field_3150.getValue().method_2(this) : java.util.Collections.emptyList());
               var3.sort(Comparator.comparing(var1x -> (this.field_3151.getValue() != null ? this.field_3151.getValue().field_1223 : null).apply(var1x)));

               for (BlockPos var5 : var3) {
                  if (this.method_920()) {
                     break;
                  }

                  if ((!this.field_3158.getValue() || !((double)var5.getY() < field_4219.player.getY())) && this.method_483(var5)) {
                     this.method_610(var5);
                     this.field_3163.reset();
                     if (!var2 && this.field_3164 != null || field_3148.isToggled() && field_3148.field_3990.getValue() && this.field_3165 >= 2) {
                        break;
                     }
                  }
               }
            }
         }
      }
   }

   public void method_610(BlockPos var1) {
      if (Class_1225.method_3(var1)) {
         Direction var2 = Direction.UP;
         if (this.field_3155.getValue()) {
            List var3 = Class_1035.method_39(var1);
            if (var3.isEmpty()) {
               return;
            }

            var2 = (Direction)var3.get(0);
         }

         if (this.field_3156.getValue()) {
            Hub.field_2598.method_2(RotationManager.method_2(var1.toCenterPos(), var2), 5);
         }

         this.field_3165++;
         if (!field_3148.isToggled()) {
            int var10 = -1;
            double var4 = Double.longBitsToDouble(-4616189618054758400L);
            BlockState var6 = field_4219.world.getBlockState(var1);

            for (int var7 = 0; var7 < 9; var7++) {
               double var8 = (double)field_4219.player.getInventory().getStack(var7).getMiningSpeedMultiplier(var6);
               if ((!field_3147.isToggled() || ItemSaverModule.method_101(field_4219.player.getInventory().getStack(var7))) && var8 > var4) {
                  var4 = var8;
                  var10 = var7;
               }
            }

            this.field_3164 = var1;
            this.field_3162.reset();
            if (this.field_3157.getValue()) {
               if (field_3146.method_1052()) {
                  PlayerUtil.method_16(var10);
                  PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var2);
                  PacketUtil.method_2(Action.START_DESTROY_BLOCK, this.field_3164, var2);
                  PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var2);
                  PacketUtil.method_2(Action.ABORT_DESTROY_BLOCK, this.field_3164.add(0, 500, 0), var2);
               } else {
                  PacketUtil.method_2(Action.START_DESTROY_BLOCK, this.field_3164, var2);
                  PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var2);
               }

               PacketUtil.method_9(Hand.MAIN_HAND);
               this.field_3161.add(new Class_0455(this.field_3164, System.currentTimeMillis()));
            } else {
               PlayerUtil.method_16(var10);
               field_4219.interactionManager.updateBlockBreakingProgress(var1, var2);
               field_4219.player.swingHand(Hand.MAIN_HAND);
            }
         } else {
            SpeedMineModule.field_3986 = true;
            field_4219.interactionManager.attackBlock(var1, var2);
            SpeedMineModule.field_3986 = false;
            this.field_3164 = var1;
            this.field_3162.reset();
         }
      }
   }

   public boolean method_483(BlockPos var1) {
      if (this.field_3161.stream().anyMatch(var1x -> var1x.method_406().equals(var1))) {
         return false;
      } else {
         double var2 = Math.sqrt(var1.getSquaredDistance(field_4219.player.getEyePos()));
         if (!Class_1225.method_2(var1) && var2 > (double)(this.field_3154.getValue() != null ? this.field_3154.getValue().floatValue() : 0.0f)) {
            return false;
         } else if (this.field_3149.getValue() == Class_0626.SHULKERS && Hub.field_2622.method_101(var1)) {
            return false;
         } else {
            return this.field_3159.getValue() && Hub.field_2630.method_264() && !Hub.field_2630.method_263(var1)
               ? false
               : (this.field_3149.getValue() != null ? this.field_3149.getValue().method_2(var1, Class_1035.method_30(var1), this) : false);
         }
      }
   }

   public boolean method_920() {
      if (!field_3148.isToggled()) {
         return false;
      } else {
         BlockPos var1 = field_3148.method_1120();
         if (var1 == null) {
            var1 = field_3148.method_1121();
         }

         BlockPos var2 = null;
         if (field_3148.method_1123() != null) {
            var2 = field_3148.method_1123().method_111();
         }

         return Class_1225.method_3(var1) && (var2 == null || !var2.equals(var1));
      }
   }

   public BlockHitResult method_921() {
      if (field_4219.crosshairTarget instanceof BlockHitResult var1 && var1.getType() == Type.BLOCK) {
         return var1;
      }

      return null;
   }
}
