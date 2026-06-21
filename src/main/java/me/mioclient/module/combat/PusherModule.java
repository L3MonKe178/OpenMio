package me.mioclient.module.combat;

import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0819;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1149;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1245;
import me.mioclient.internal.Class_1261;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0531;
import me.mioclient.record.Class_0566;
import me.mioclient.record.Class_0828;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Type;
import nick.Settings;

public class PusherModule extends Module {
   public Setting<Float> field_3001;
   public Setting<Integer> field_3002;
   public Setting<Boolean> field_3003;
   public Setting<Boolean> field_3004;
   public Setting<Boolean> field_3005;
   public Setting<Boolean> field_3006;
   public Setting<Boolean> field_3007;
   public Setting<Boolean> field_3008;
   public Setting<Boolean> field_3009;
   public Setting<Boolean> field_3010;
   public Setting<Boolean> field_3011;
   public Setting<Boolean> field_3012;
   public Setting<Color> field_3013;
   public Setting<Color> field_3014;
   public Setting<Float> field_3015;
   public Setting<Boolean> field_3016;
   public Setting<Float> field_3017;
   public final Class_0242 field_3018;
   public PlayerEntity field_2290;
   public boolean field_3019;
   public BlockPos field_3020;
   public boolean field_3021;
   public final Class_1149 field_3022;
   public final Class_0819 field_3023;

   public PusherModule() {
      super("Pusher", "Pushes your enemies out of their holes.", Category.COMBAT);
      Settings.initialize(this);
      this.field_3018 = new Class_0242();
      this.field_3022 = new Class_1149();
      this.field_3023 = new Class_0819(this);
      Hub.field_2616.method_2(new Class_0828(this, this.field_3013, this.field_3014, this.field_3015, this.field_3017, () -> true, this.field_3016, 450));
   }

   @Override
   public void onEnable() {
      this.field_3021 = false;
   }

   @Override
   public String getInfo() {
      return this.field_2290 != null ? this.field_2290.getGameProfile().getName() : super.getInfo();
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      if (field_4219.isInSingleplayer()) {
         if (!this.field_3021) {
            Hub.field_2619
               .method_2(() -> Class_1245.method_2(Text.literal("Pusher doesn't work in singleplayer."), Class_1245.method_38(-2), Priority.HIGH), 1);
            this.field_3021 = true;
         }
      } else if (!field_4219.player.isUsingItem() || this.field_3006.getValue()) {
         this.field_2290 = this.field_3023.method_691();
         if (this.field_2290 != null) {
            if (this.field_3007.getValue()) {
               boolean var2 = BlockPos.stream(this.field_2290.getBoundingBox())
                  .anyMatch(var0 -> field_4219.world.getBlockState(var0).isOf(Blocks.PISTON_HEAD) || field_4219.world.getBlockState(var0).isOf(Blocks.PISTON));
               if (var2) {
                  this.disable();
                  return;
               }
            }

            if (this.field_3018.method_9((long)this.field_3002.getValue().intValue())) {
               this.field_3019 = false;
               this.field_3018.reset();
               this.method_882();
               this.method_883();
            }
         }
      }
   }

   public void method_882() {
      int var1 = Class_0136.method_5(Items.REDSTONE_BLOCK);
      boolean var2 = false;
      if (var1 == -1) {
         var1 = Class_0136.method_5(Items.REDSTONE_TORCH);
         var2 = true;
      }

      int var3 = field_4219.player.getInventory().selectedSlot;
      if (var1 != -1) {
         Class_0566 var4 = null;

         for (Direction var6 : Type.HORIZONTAL) {
            BlockPos var7 = this.field_2290.getBlockPos().up().offset(var6);
            if (Class_1225.method_33(var7) instanceof PistonBlock && !this.method_2(this.field_2290, var7, var6)) {
               var4 = this.method_2(var2, var7);
            }
         }

         if (var4 != null) {
            this.field_3019 = true;
            if (this.field_3004.getValue()) {
               Hub.field_2598.method_2(Class_0485.method_2(var4.method_406().toCenterPos(), var4.method_20()), 500);
            }

            Class_0136.method_16(var1);
            Class_1035.method_2(var4.method_406(), var4.method_20(), false, Hand.MAIN_HAND);
            Class_1261.method_9(Hand.MAIN_HAND);
            Class_0136.method_16(var3);
            if (this.field_3012.getValue()) {
               Hub.field_2616.method_2(this, var4.method_406());
            }
         }
      }
   }

   public void method_883() {
      if (!this.field_3019) {
         int var1 = Class_0136.method_2(Items.PISTON, Items.STICKY_PISTON);
         int var2 = field_4219.player.getInventory().selectedSlot;
         if (var1 != -1) {
            Class_0531 var3 = null;

            for (Direction var5 : Type.HORIZONTAL) {
               BlockPos var6 = this.field_2290.getBlockPos().up().offset(var5);
               if (!this.method_2(this.field_2290, var6, var5)) {
                  if (this.method_632(var6) == var5.getOpposite()) {
                     var3 = null;
                     break;
                  }

                  if (Class_1035.method_7(var6, true)) {
                     Class_0531 var7 = this.method_78(var6, var5.getOpposite());
                     if (var7 != null) {
                        var3 = var7;
                     }
                  }
               }
            }

            if (var3 != null) {
               this.field_3020 = var3.method_406();
               Hub.field_2598.method_2(Class_0485.method_78(var3.method_564()), 500);
               Class_0136.method_16(var1);
               Class_1035.method_2(var3.method_406(), var3.method_20(), false, Hand.MAIN_HAND);
               Class_1261.method_9(Hand.MAIN_HAND);
               Class_0136.method_16(var2);
               if (this.field_3012.getValue()) {
                  Hub.field_2616.method_2(this, var3.method_406());
               }
            }
         }
      }
   }

   public Class_0566 method_2(boolean var1, BlockPos var2) {
      Class_0566 var3 = null;

      for (Direction var7 : Direction.values()) {
         BlockPos var8 = var2.offset(var7);
         if (field_4219.world.getBlockState(var8).isOf(Blocks.REDSTONE_BLOCK)) {
            return null;
         }

         if (Class_1035.method_7(var8, true)) {
            Direction var9 = this.method_2(var1, var8, var7.getOpposite());
            if (var9 != null) {
               var3 = new Class_0566(var9, var8);
            }
         }
      }

      return var3;
   }

   public Direction method_2(boolean var1, BlockPos var2, Direction var3) {
      if (!var1) {
         return Class_1035.method_9(var2, this.field_3003.getValue());
      } else {
         for (Direction var7 : Direction.values()) {
            BlockPos var8 = var2.offset(var7);
            if (var3 != var7 && Hub.field_2622.method_105(var8) && (!this.field_3003.getValue() || Class_1035.method_39(var8).contains(var7.getOpposite()))) {
               return var7;
            }
         }

         return null;
      }
   }

   public Class_0531 method_78(BlockPos var1, Direction var2) {
      for (Direction var6 : Direction.values()) {
         BlockPos var7 = var1.offset(var6);
         if (Hub.field_2622.method_105(var7)
            && (!this.field_3003.getValue() || Class_1035.method_39(var7).contains(var6.getOpposite()))
            && (!this.field_3005.getValue() || Class_1225.method_2(Class_1172.field_3634.method_38(var7)))) {
            if (!this.field_3004.getValue()) {
               Vec3d var11 = field_4219.player.getEyePos().offset(var2.getOpposite(), Double.longBitsToDouble(4607182418800017408L));
               return new Class_0531(var6, var1, var11);
            }

            for (Vec3d var9 : Class_1172.field_3634.method_2(var7, var6.getOpposite())) {
               Direction var10 = this.field_3022.method_4(Class_0485.method_78(var9));
               if (var10 == var2.getOpposite()) {
                  return new Class_0531(var6, var1, var9);
               }
            }
         }
      }

      return null;
   }

   public boolean method_2(LivingEntity var1, BlockPos var2, Direction var3) {
      if (field_4219.player.getEyePos().distanceTo(var2.toCenterPos()) > (double)this.method_884()) {
         return true;
      } else {
         BlockPos var4 = var2.offset(var3.getOpposite(), 2);
         int var5 = 0;

         for (BlockPos var7 : Class_0382.method_5(var1)) {
            if (Hub.field_2622.method_105(var7)) {
               var5++;
            }
         }

         boolean var8 = field_4219.world.getBlockState(var4.down()).isReplaceable();
         return var5 > 2 && var8 ? true : !field_4219.world.getBlockState(var4).isReplaceable();
      }
   }

   public Direction method_632(BlockPos var1) {
      BlockState var2 = field_4219.world.getBlockState(var1);
      return var2.getBlock() instanceof PistonBlock ? (Direction)var2.get(FacingBlock.FACING) : null;
   }

   public float method_884() {
      return this.field_3001.getValue();
   }
}
