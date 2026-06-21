package me.mioclient.module.abstract_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_50;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_1035;
import me.mioclient.module.Category;
import me.mioclient.setting.Setting;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import nick.Settings;

public class AbstractModule_17 extends AbstractModule_32 {
   public Setting<Boolean> field_2100;
   public Setting<Boolean> field_2101;
   public Setting<Boolean> field_2102;
   public Setting<Boolean> field_2103;
   public final Timer field_2104;
   public boolean field_2105;

   public AbstractModule_17() {
      super("FeetPlace", "Surrounds your feet with obby.", Category.COMBAT, "surround");
      Settings.initialize(this);
      this.field_2104 = new Timer();
      this.method_5(true);
   }

   @Override
   public void onEnable() {
      if (!this.method_535()) {
         this.method_662();
      }

      super.onEnable();
      this.field_2104.reset();
   }

   @Override
   public List<BlockPos> method_17() {
      List var1 = this.method_2(new ArrayList<>(), this.method_425(), 0);
      var1.sort(this.method_661());
      return var1;
   }

   @Override
   public int method_52() {
      return 999;
   }

   @Override
   public int method_34() {
      return this.field_2103.getValue() ? PlayerUtil.method_5(Items.OBSIDIAN) : super.method_34();
   }

   @Override
   public void method_33() {
      if (!field_4219.player.hasVehicle()) {
         this.field_2105 = !Hub.field_2602.method_985().method_9(100L);
         if (this.field_2105) {
            this.field_609 = field_4219.player.getY();
         }

         try {
            super.method_33();
         } catch (Throwable var2) {
         }
      }
   }

   @Override
   public void method_2(List<BlockPos> var1) {
   }

   @Override
   public boolean method_107() {
      return this.field_2105 ? false : super.method_107();
   }

   @Subscribe
   public void method_9(Event_50 var1) {
      if (this.field_2100.getValue()) {
         this.method_33();
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket var2
         && field_4219.player.getPos().squaredDistanceTo(new Vec3d(var2.getX(), var2.getY(), var2.getZ())) > Double.longBitsToDouble(4621256167635550208L)) {
         this.field_609 = var2.getY();
         Hub.field_2602.method_985().reset();
      }
   }

   public List<BlockPos> method_2(List<Integer> var1, BlockPos var2, int var3) {
      int var4 = this.method_34();
      BlockState var5 = Blocks.OBSIDIAN.getDefaultState();
      if (var4 != -1 && field_4219.player.getInventory().getStack(var4).getItem() instanceof BlockItem var6) {
         var5 = var6.getBlock().getDefaultState();
      }

      ArrayList var15 = new ArrayList();
      var1.add(var2.hashCode());

      for (Vec3d var9 : this.method_468(var3)) {
         BlockPos var10 = var2.add(BlockPos.ofFloored(var9));
         if (var9.y != Double.longBitsToDouble(-4616189618054758400L)
            || (!Hub.field_2605.method_221(var10) || field_4219.player.isOnGround())
               && this.field_2102.getValue()
               && !field_4219.player.isInSwimmingPose()
               && !var10.equals(Hub.field_2622.method_344())) {
            VoxelShape var11 = var5.getCollisionShape(field_4219.world, BlockPos.ORIGIN);
            Box var12 = var11.isEmpty() ? new Box(var10) : Class_0719.method_2(var11.getBoundingBox(), var10);
            this.method_7(var10);
            if (!field_4219.world.getBlockState(var10).isReplaceable()) {
               boolean var13 = Class_0719.method_2(field_4219.player.getBoundingBox(), new Box(var10));
               if (!var13) {
                  continue;
               }
            }

            if (!field_4219.world
                  .getEntitiesByClass(LivingEntity.class, var12, var1x -> var1x == field_4219.player || Class_0719.method_2(var12, var1x.getBoundingBox()))
                  .isEmpty()
               && !var1.contains(var10.hashCode())
               && var9.y == (double)var3) {
               var15.addAll(this.method_2(var1, var10, var3));
            } else if (field_4219.world.getBlockState(var10).isReplaceable()) {
               Direction var17 = Class_1035.method_9(var10, this.field_4250.getValue());
               if (var17 == null) {
                  BlockPos var14 = Class_1035.method_2(var10, 2, true, this.field_4250.getValue());
                  if (var14 != null) {
                     var15.add(var14);
                  }
               }

               var15.add(var10);
            }
         }
      }

      return var15;
   }

   public List<Vec3d> method_468(int var1) {
      return Arrays.asList(
         new Vec3d(Double.longBitsToDouble(-4616189618054758400L), (double)var1, 0.0),
         new Vec3d(Double.longBitsToDouble(4607182418800017408L), (double)var1, 0.0),
         new Vec3d(0.0, (double)var1, Double.longBitsToDouble(4607182418800017408L)),
         new Vec3d(0.0, (double)var1, Double.longBitsToDouble(-4616189618054758400L)),
         new Vec3d(0.0, (double)(var1 - 1), 0.0)
      );
   }

   public Comparator<BlockPos> method_661() {
      return Hub.field_2614.method_876() <= Double.longBitsToDouble(4621819117588971520L)
         ? Comparator.comparing(var1 -> this.method_2(field_4219.player.getYaw(), var1))
         : Comparator.comparing(var1 -> this.method_9(Class_0464.method_496(), var1));
   }

   public float method_2(float var1, BlockPos var2) {
      return var2.getY() == Class_0382.method_425().getY() && this.method_38(field_4219.player)
         ? (float)Class_0382.method_425().getSquaredDistance((double)var2.getX(), (double)var2.getY(), (double)var2.getZ())
         : this.method_9(var1, var2);
   }

   public float method_9(float var1, BlockPos var2) {
      return MathHelper.angleBetween(var1, RotationManager.method_78(var2.toCenterPos())[0]) + (float)var2.getY() * Float.intBitsToFloat(981668463);
   }

   public void method_662() {
      if (!this.method_535()
         && this.field_2101.getValue()
         && field_4219.player.isOnGround()
         && !Hub.field_2605.method_224()
         && Class_0382.method_5((LivingEntity)field_4219.player).size() > 4) {
         Vec3d var1 = new Vec3d(
            Math.floor(field_4219.player.getX()) + Double.longBitsToDouble(4602678819172646912L),
            field_4219.player.getY(),
            Math.floor(field_4219.player.getZ()) + Double.longBitsToDouble(4602678819172646912L)
         );
         field_4219.player
            .setVelocity(
               (var1.x - field_4219.player.getX()) * Double.longBitsToDouble(4602678819172646912L),
               field_4219.player.getVelocity().getY(),
               (var1.z - field_4219.player.getZ()) * Double.longBitsToDouble(4602678819172646912L)
            );
         field_4219.player.setPosition(var1);
      }
   }

   public boolean method_38(LivingEntity var1) {
      for (BlockPos var3 : Class_0382.method_7(var1)) {
         VoxelShape var4 = field_4219.world.getBlockState(var3).getCollisionShape(field_4219.world, BlockPos.ORIGIN);
         if (!var4.isEmpty()) {
            Box var5 = var4.getBoundingBox();
            if (!Class_0719.method_7(var5)) {
               return true;
            }
         }
      }

      return false;
   }
}
