package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.List;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0470;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0532;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.PacketUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0828;
import me.mioclient.record.Class_1183;
import me.mioclient.setting.Setting;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BedItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import nick.Settings;

public final class AbstractModule_13 extends Module {
   public static final AbstractModule_28 field_3564 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Boolean> field_3565;
   public Setting<Integer> field_3566;
   public Setting<Float> field_3567;
   public Setting<Boolean> field_3568;
   public Setting<Boolean> field_3569;
   public Setting<Boolean> field_3570;
   public Setting<Boolean> field_3571;
   public Setting<Boolean> field_3572;
   public Setting<Integer> field_3573;
   public Setting<Float> field_3574;
   public Setting<Boolean> field_3575;
   public Setting<Boolean> field_3576;
   public Setting<Boolean> field_3577;
   public Setting<Integer> field_3578;
   public Setting<Boolean> field_3579;
   public Setting<Integer> field_3580;
   public Setting<Boolean> field_3581;
   public Setting<Color> field_3582;
   public Setting<Color> field_3583;
   public Setting<Boolean> field_3584;
   public Setting<Float> field_3585;
   public Setting<Boolean> field_3586;
   public Setting<Float> field_3587;
   public Setting<Float> field_3588;
   public Setting<Boolean> field_3589;
   public final Class_0532 field_3590;
   public final Class_0470 field_3591;
   public final Timer field_3592;
   public final Timer field_3593;
   public PlayerEntity field_2290;
   public Class_1183 field_3594;
   public BlockPos field_3595;

   public AbstractModule_13() {
      super(
         "BedAura",
         new TextBuilder().method_2(String.valueOf(Formatting.RED)).method_9("Blows your enemies up using beds. \n\u0001Doesn't work in the overworld."),
         Category.COMBAT
      );
      Settings.initialize(this);
      this.field_3590 = new Class_0532(this);
      this.field_3591 = new Class_0470(this);
      this.field_3592 = new Timer();
      this.field_3593 = new Timer();
      Hub.field_2616
         .method_2(
            new Class_0828(this, this.field_3582, this.field_3583, () -> Float.intBitsToFloat(1065353216), this.field_3585, () -> false, this.field_3584, 1000)
         );
      this.field_3567.method_31("PlaceRange");
      this.field_3574.method_31("BreakRange");
      this.field_3566.method_31("PlaceDelay");
      this.field_3573.method_31("BreakDelay");
   }

   @Override
   public String getInfo() {
      return this.field_2290 == null ? null : this.field_2290.getGameProfile().getName();
   }

   @Subscribe
   public void method_9(Event_7 var1) {
      this.field_2290 = this.method_691();
      if (this.field_2290 == null) {
         this.field_3594 = null;
      } else {
         this.field_3594 = this.field_3590.method_22(this.field_2290);
         this.field_3595 = this.field_3591.method_31(this.field_2290);
      }

      if (this.field_3594 != null) {
         this.method_1024();
         if (this.field_3572.getValue() && this.method_2(this.field_3593, this.field_3573)) {
            this.method_289(this.field_3595);
         }

         if (this.field_3565.getValue() && this.method_2(this.field_3592, this.field_3566)) {
            this.method_2(this.field_3594);
         }
      }
   }

   public PlayerEntity method_691() {
      PlayerEntity var1 = null;
      float var2 = Float.intBitsToFloat(1203982208);

      for (PlayerEntity var4 : field_4219.world.getPlayers()) {
         if (var4 != field_4219.player && !Hub.field_2603.method_30(var4) && (double)var2 > field_4219.player.getEyePos().distanceTo(var4.getPos())) {
            var1 = var4;
            var2 = (float)field_4219.player.getEyePos().distanceTo(var4.getPos());
         }
      }

      return var1;
   }

   public void method_2(Class_1183 var1) {
      int var2 = PlayerUtil.method_7((Predicate<ItemStack>)(var0 -> var0.getItem() instanceof BedItem));
      int var3 = field_4219.player.getInventory().selectedSlot;
      if (var2 != -1) {
         PlayerUtil.method_78(var2);
         this.method_9(var1);
         PlayerUtil.method_78(var3);
      }
   }

   public void method_9(Class_1183 var1) {
      if (var1 != null) {
         if (Class_1035.method_7(var1.method_1037(), true)) {
            float[] var2;
            if (this.field_3569.getValue()) {
               var2 = RotationManager.method_78(var1.method_1041().getPos());
            } else {
               var2 = RotationManager.method_7(var1.method_1037().toCenterPos(), var1.method_1038().toCenterPos());
            }

            Hub.field_2598.method_2(var2, 500);
            BlockHitResult var3 = var1.method_1041();
            Hub.field_2615.method_3(() -> PacketUtil.method_2(Hand.MAIN_HAND, var3));
            field_4219.player.swingHand(Hand.MAIN_HAND);
            Box var4 = Box.enclosing(var1.method_1037(), var1.method_1038());
            var4 = var4.withMaxY(var4.minY + Double.longBitsToDouble(4603241769126068224L));
            var4 = var4.expand(Double.longBitsToDouble(4566758108763783168L));
            if (this.field_3581.getValue()) {
               Hub.field_2616.method_2(this, var4);
            }

            if (this.field_3576.getValue()) {
               Hub.field_2615.method_22(() -> {
                  Direction var2x = this.method_4(var1.method_1037(), Direction.UP);
                  BlockHitResult var3x = new BlockHitResult(var1.method_1037().toCenterPos(), var2x, var1.method_1037(), false);
                  PacketUtil.method_2(Hand.MAIN_HAND, var3x);
                  field_4219.player.swingHand(Hand.MAIN_HAND);
               });
            }

            this.field_3592.reset();
         }
      }
   }

   public void method_289(BlockPos var1) {
      if (var1 != null) {
         Direction var2 = this.method_4(var1, Direction.UP);
         BlockHitResult var3 = new BlockHitResult(var1.toCenterPos(), var2, var1, false);
         Hub.field_2615.method_22(() -> {
            field_4219.interactionManager.interactBlock(field_4219.player, Hand.MAIN_HAND, var3);
            field_4219.player.swingHand(Hand.MAIN_HAND);
         });

         for (Direction var5 : Type.HORIZONTAL) {
            BlockPos var6 = var1.offset(var5);
            if (field_4219.world.getBlockState(var6).getBlock() instanceof BedBlock) {
               Hub.field_2622.method_99(var6);
               break;
            }
         }

         Hub.field_2622.method_99(var1);
         this.field_3593.reset();
      }
   }

   public void method_1024() {
      if (this.field_3579.getValue()) {
         int var1 = this.field_3580.getValue() - 1;
         ItemStack var2 = field_4219.player.getInventory().getStack(var1);
         if (!(var2.getItem() instanceof BedItem)) {
            int var3 = PlayerUtil.method_2((Predicate<ItemStack>)(var0 -> var0.getItem() instanceof BedItem), true);
            if (var3 != -1) {
               PlayerUtil.method_9(var3, PlayerUtil.method_30(var1));
            }
         }
      }
   }

   public Direction method_4(BlockPos var1, Direction var2) {
      List var3 = Class_1035.method_39(var1);
      if (!var3.isEmpty()) {
         var3.getFirst();
      }

      return var2;
   }

   public boolean method_2(BlockPos var1, BedPart var2) {
      BlockState var3 = field_4219.world.getBlockState(var1);
      return var3.getBlock() instanceof BedBlock && var3.get(BedBlock.PART) == var2;
   }

   public boolean method_2(Timer var1, Setting<Integer> var2) {
      float var3 = this.field_3575.getValue() ? Hub.field_2602.method_990() : Float.intBitsToFloat(1065353216);
      return var1.method_9((long)((float)(50L * (long)((Integer)var2.getValue()).intValue()) * var3));
   }

   public Box method_16(LivingEntity var1) {
      if (var1 instanceof PlayerEntity var2 && this.field_3577.getValue()) {
         return Hub.field_2612.method_2(var2, this.field_3578.getValue());
      }

      return var1.getBoundingBox();
   }
}
