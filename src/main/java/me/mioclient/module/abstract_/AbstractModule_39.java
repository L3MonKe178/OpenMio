package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1245;
import me.mioclient.internal.Class_1261;
import me.mioclient.internal.Class_1303;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.combat.OffhandModule;
import me.mioclient.record.Class_0828;
import me.mioclient.record.Class_0835;
import me.mioclient.record.Class_1073;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import nick.Settings;

public class AbstractModule_39 extends Module {
   public static final AbstractModule_28 field_3764 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static final OffhandModule field_3765 = Hub.field_2595.method_78(OffhandModule.class);
   public static final AutoCrystalModule field_3766 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public Setting<Integer> field_3767;
   public Setting<Float> field_3768;
   public Setting<Float> field_3769;
   public Setting<Float> field_3770;
   public Setting<Float> field_3771;
   public Setting<Float> field_3772;
   public Setting<Boolean> field_3773;
   public Setting<Boolean> field_3774;
   public Setting<Boolean> field_3775;
   public Setting<Boolean> field_3776;
   public Setting<Boolean> field_3777;
   public Setting<Boolean> field_3778;
   public Setting<Boolean> field_3779;
   public Setting<Boolean> field_3780;
   public Setting<Color> field_3781;
   public Setting<Color> field_3782;
   public Setting<Boolean> field_3783;
   public Setting<Float> field_3784;
   public Setting<Boolean> field_3785;
   public Setting<Boolean> field_3786;
   public Setting<Boolean> field_3787;
   public Setting<Float> field_3788;
   public CompletableFuture<Class_0835> field_3789;
   public final Mutable field_3790;
   public final Class_0242 field_3791;
   public final AtomicReference<Class_0835> field_3792;
   public Class_1073 field_3793;

   public AbstractModule_39() {
      super(
         "AnchorAura",
         new Class_1303().method_2(String.valueOf(Formatting.RED)).method_9("Explodes respawn anchors to kill players. \n\u0001Doesn't work in the nether."),
         Category.COMBAT
      );
      Settings.initialize(this);
      this.field_3790 = new Mutable();
      this.field_3791 = new Class_0242();
      this.field_3792 = new AtomicReference<>();
      Hub.field_2616
         .method_2(
            new Class_0828(this, this.field_3781, this.field_3782, () -> Float.intBitsToFloat(1065353216), this.field_3784, () -> false, this.field_3783, 450)
         );
   }

   @Override
   public void onEnable() {
      this.field_3793 = null;
      if (this.field_3789 != null) {
         try {
            this.field_3789.complete(null);
         } catch (CancellationException var2) {
         }
      }
   }

   @Override
   public void onToggle() {
      this.field_3792.set(null);
   }

   @Override
   public String getInfo() {
      return this.field_3793 != null ? "%s, %.1f".formatted(this.field_3793.method_956().getName().getString(), this.field_3793.method_445()) : null;
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      if (field_4219.world.getDimension().respawnAnchorWorks()) {
         Class_1245.method_2(Text.of("Doesn't work in the nether!"), Class_1245.method_38(-1), Priority.HIGH);
         this.disable();
      } else {
         this.method_1061();
         if (!this.method_639()) {
            Class_0835 var2 = this.field_3792.get();
            if (var2 != null) {
               if (this.method_12() != null) {
                  BlockPos var3 = var2.method_406();
                  BlockState var4 = field_4219.world.getBlockState(var3);
                  int var5 = field_4219.player.getInventory().selectedSlot;
                  if (var4.isOf(Blocks.RESPAWN_ANCHOR)) {
                     Direction var6 = this.method_82(var3);
                     if (var6 == null || !this.field_3791.method_9((long)(this.field_3767.getValue() + Hub.field_2602.method_983()))) {
                        return;
                     }

                     if ((Integer)var4.get(RespawnAnchorBlock.CHARGES) == 0) {
                        this.method_2(var3, var6, var5);
                     }

                     if (this.field_3774.getValue()) {
                        Hub.field_2598.method_2(Class_0485.method_78(var3.toCenterPos()), 100);
                     }

                     this.method_2(var3, var6, Vec3d.ofCenter(var3), Hand.OFF_HAND);
                     if (this.field_3778.getValue()) {
                        field_4219.world.setBlockState(var3, Blocks.AIR.getDefaultState());
                     }
                  }

                  this.method_2(var3, var2.method_457(), this.method_12());
               }
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (this.field_3779.getValue() && this.field_3792.get() != null) {
         if (var1.method_127() instanceof BlockUpdateS2CPacket var2
            && this.field_3793 != null
            && var2.getState().isAir()
            && var2.getPos().equals(this.field_3792.get().method_406())) {
            this.method_2(this.field_3792.get().method_406(), this.field_3792.get().method_457(), this.method_12());
         }
      }
   }

   public void method_2(BlockPos var1, Direction var2, int var3) {
      Vec3d var4 = var1.toCenterPos().offset(var2, Class_0245.field_688);
      Hand var5 = Class_0136.method_7(Items.GLOWSTONE);
      if (this.field_3774.getValue()) {
         Hub.field_2598.method_2(Class_0485.method_78(var4), 100);
      }

      if (var5 != null) {
         this.method_2(var1, var2, var4, Hand.MAIN_HAND);
      } else {
         int var6 = Class_0136.method_5(Items.GLOWSTONE);
         if (var6 != -1) {
            Class_0136.method_16(var6);
            this.method_2(var1, var2, var4, Hand.MAIN_HAND);
            Class_0136.method_16(var3);
         }
      }

      this.field_3791.reset();
   }

   public void method_2(BlockPos var1, Direction var2, Hand var3) {
      if (!this.method_176(var1)) {
         int var4 = field_4219.player.getInventory().selectedSlot;
         int var5 = Class_0136.method_5(Items.RESPAWN_ANCHOR);
         boolean var6 = Class_1035.method_78(var1) == null && field_3764.method_1052() && this.field_3776.getValue();
         if (var5 != -1 || var3 != Hand.MAIN_HAND) {
            if (this.field_3774.getValue()) {
               Hub.field_2598.method_2(Class_0485.method_2(var1.toCenterPos(), var2), 100);
            }

            boolean var7 = !field_4219.player.getMainHandStack().isOf(Items.RESPAWN_ANCHOR);
            if (var7) {
               Class_0136.method_16(var5);
            }

            if (var6) {
               var3 = Class_0382.method_5(var3);
               Class_1261.method_1100();
            }

            if (Class_1035.method_9(var1, this.method_310(var1.offset(var2)), var2, this.field_3776.getValue(), var3) && this.field_3780.getValue()) {
               Hub.field_2616.method_2(this, var1);
            }

            if (var6) {
               Class_1261.method_1100();
            }

            if (var7) {
               Class_0136.method_16(var4);
            }
         }
      }
   }

   public void method_2(BlockPos var1, Direction var2, Vec3d var3, Hand var4) {
      Hub.field_2615.method_22(() -> {
         Class_1261.method_2(Hand.MAIN_HAND, new BlockHitResult(var3, var2, var1, false));
         Class_1261.method_9(var4);
      });
   }

   public void method_1061() {
      if (this.field_3792.get() != null && this.method_468(this.field_3792.get().method_406())) {
         this.field_3792.set(null);
      }

      if (this.field_3789 != null && this.field_3789.isDone() && this.field_3792.get() == null) {
         try {
            Class_0835 var1 = this.field_3789.get();
            if (var1 == null) {
               this.field_3792.set(null);
            } else {
               this.field_3792.set(var1);
               this.field_3793 = var1.method_773();
            }
         } catch (ExecutionException | InterruptedException var2) {
            var2.printStackTrace();
         }
      }

      if (this.field_3789 == null || this.field_3789.isDone() || this.field_3789.isCancelled()) {
         if (this.field_3793 != null && Class_1073.method_2(this.field_3793.method_956(), (double)this.field_3770.getValue().floatValue())) {
            this.field_3793 = null;
         }

         this.field_3789 = CompletableFuture.supplyAsync(this::method_1062, field_4221).exceptionally(var0 -> null);
      }
   }

   public Direction method_82(BlockPos var1) {
      List var2 = Class_1035.method_39(var1);
      Direction var3 = this.field_3775.getValue() ? null : Direction.DOWN;
      if (!var2.isEmpty()) {
         var3 = (Direction)var2.get(0);
      }

      return var3;
   }

   public Vec3d method_310(BlockPos var1) {
      List<Vec3d> var2 = Class_1172.field_3634.method_38(var1);
      return var2.stream()
         .filter(var1x -> Class_1225.method_9(var1x) || field_4219.player.getEyePos().distanceTo(var1x) <= (double)this.field_3769.getValue().floatValue())
         .min(Comparator.comparing(var0 -> field_4219.player.getEyePos().distanceTo(var0)))
         .orElse(var1.toCenterPos());
   }

   public Class_0835 method_1062() {
      Class_0835 var1 = null;

      for (double var2 = -Math.floor((double)this.field_3768.getValue().floatValue());
         var2 < Math.ceil((double)this.field_3768.getValue().floatValue());
         var2 += Double.longBitsToDouble(4607182418800017408L)
      ) {
         for (double var4 = -Math.floor((double)this.field_3768.getValue().floatValue());
            var4 < Math.ceil((double)this.field_3768.getValue().floatValue());
            var4 += Double.longBitsToDouble(4607182418800017408L)
         ) {
            for (double var6 = -Math.floor((double)this.field_3768.getValue().floatValue());
               var6 < Math.ceil((double)this.field_3768.getValue().floatValue());
               var6 += Double.longBitsToDouble(4607182418800017408L)
            ) {
               this.field_3790.set(field_4219.player.getX() + var2, field_4219.player.getEyeY() + var4, field_4219.player.getZ() + var6);
               if ((Class_1035.method_2(this.field_3790, Blocks.RESPAWN_ANCHOR, true, false) || this.method_176(this.field_3790))
                  && this.field_3790.getY() >= field_4219.world.getBottomY()
                  && this.field_3790.getY() <= field_4219.world.getTopY()) {
                  List var8 = Class_1172.field_3634.method_38(this.field_3790);
                  boolean var9 = Class_1225.method_2(var8);
                  double var10 = field_4219.player.getEyePos().distanceTo(this.field_3790.toCenterPos());
                  if ((var9 || !(var10 > (double)this.field_3769.getValue().floatValue())) && !(var10 > (double)this.field_3768.getValue().floatValue())) {
                     Direction var12 = Class_1035.method_2(this.field_3790, this.field_3775.getValue(), this.field_3769.getValue() <= 0.0F);
                     Class_1073 var13 = this.method_270(this.field_3790);
                     if (var12 == null && this.field_3776.getValue() || this.method_176(this.field_3790)) {
                        var12 = Direction.DOWN;
                     }

                     if (var12 == null && !field_4219.world.getFluidState(this.field_3790).isEmpty() && this.field_3777.getValue()) {
                        var12 = Direction.DOWN;
                     }

                     if (var12 != null && var13 != null && !this.method_2(this.field_3790, var13)) {
                        Class_0835 var14 = new Class_0835(this.field_3790.toImmutable(), var12, var13);
                        if (this.method_176(this.field_3790)) {
                           return var14;
                        }

                        var1 = var14.method_2(var1);
                     }
                  }
               }
            }
         }
      }

      return var1;
   }

   public Class_1073 method_270(BlockPos var1) {
      Class_1073 var2 = null;

      for (PlayerEntity var4 : field_4219.world.getPlayers()) {
         if (!Class_1073.method_2(var4, (double)this.field_3770.getValue().floatValue())) {
            double var5 = (double)Class_1323.method_2(
               var1.toCenterPos(), var4, var4.getBoundingBox(), Double.longBitsToDouble(4617315517961601024L), true, this.method_176(var1) ? var1 : null, null
            );
            if (!((double)this.field_3771.getValue().floatValue() > var5) && !(var5 < Class_0245.field_688) && (var2 == null || var5 > var2.method_445())) {
               var2 = new Class_1073(var4, var5);
            }
         }
      }

      return var2;
   }

   public Hand method_12() {
      boolean var1 = field_3765.isToggled() && field_3765.field_1962.getValue().method_98(false) == Items.RESPAWN_ANCHOR;
      Hand var2 = Class_0136.method_7(Items.RESPAWN_ANCHOR);
      if (var1 && var2 == null) {
         return null;
      } else {
         return var1 ? Hand.OFF_HAND : Hand.MAIN_HAND;
      }
   }

   public boolean method_2(BlockPos var1, Class_1073 var2) {
      if (var2 == null) {
         return true;
      } else {
         boolean var3 = this.method_176(var1);
         double var4 = (double)Class_1323.method_2(
            Vec3d.ofCenter(var1),
            field_4219.player,
            field_4219.player.getBoundingBox(),
            Double.longBitsToDouble(4617315517961601024L),
            true,
            this.method_176(var1) ? var1 : null,
            null
         );
         boolean var6 = var2.method_445() >= (double)Class_0396.method_2((net.minecraft.entity.Entity)var2.method_956());
         double var7 = (double)Class_1323.method_2(
            Vec3d.ofCenter(var1),
            var2.method_956(),
            var2.method_956().getBoundingBox(),
            Double.longBitsToDouble(4617315517961601024L),
            true,
            this.method_176(var1) ? var1 : null,
            null
         );
         boolean var9 = !var6 && var4 >= (double)this.field_3772.getValue().floatValue()
            || var4 >= (double)Class_0396.method_76() && this.field_3773.getValue();
         if (var3 && !var9) {
            return false;
         } else {
            return !((double)this.field_3771.getValue().floatValue() > var7) && !(var7 < Class_0245.field_688) ? var9 : true;
         }
      }
   }

   public boolean method_468(BlockPos var1) {
      List var2 = Class_1172.field_3634.method_38(var1);
      double var3 = (double)(!Class_1225.method_2(var2) ? this.field_3769.getValue() : this.field_3768.getValue()).floatValue();
      if (!this.method_2(var1, this.method_270(var1)) && !(field_4219.player.getEyePos().distanceTo(var1.toCenterPos()) > var3)) {
         return field_4219.world.getBlockState(var1).isAir()
               && (this.method_82(var1) == null || Class_1035.method_2(var1, this.field_3775.getValue(), this.field_3769.getValue() <= 0.0F) == null)
               && !this.field_3776.getValue()
            ? true
            : !Class_1035.method_2(var1, Blocks.RESPAWN_ANCHOR, true, false) && !this.method_176(var1);
      } else {
         return true;
      }
   }

   public boolean method_176(BlockPos var1) {
      return field_4219.world.getBlockState(var1).isOf(Blocks.RESPAWN_ANCHOR);
   }

   public boolean method_639() {
      if (this.method_535()) {
         return true;
      } else if (field_4219.player.isSleeping()) {
         return true;
      } else if (field_4219.player.isUsingItem() && this.field_3787.getValue()) {
         return true;
      } else if (field_4219.interactionManager.isBreakingBlock() && this.field_3786.getValue()) {
         return true;
      } else {
         return field_3766.isToggled()
               && field_3766.field_4122.get() != null
               && this.field_3793 != null
               && field_3766.field_4122.get().method_445() > this.field_3793.method_445()
            ? true
            : Class_0396.method_76() < this.field_3788.getValue();
      }
   }
}
