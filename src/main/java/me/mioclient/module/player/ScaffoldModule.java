package me.mioclient.module.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0147;
import me.mioclient.enum_.Class_0710;
import me.mioclient.enum_.Class_1302;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_7;
import me.mioclient.event.Event_8;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0512;
import me.mioclient.internal.Class_0533;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_0981;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.PacketUtil;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.record.Class_0407;
import me.mioclient.record.Class_0773;
import me.mioclient.record.Class_0828;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import nick.Settings;

public class ScaffoldModule extends Module {
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public Setting<Class_0710> field_145;
   public Setting<Set<Block>> field_146;
   public Setting<Class_1302> field_147;
   public Setting<Integer> field_148;
   public Setting<Integer> field_149;
   public Setting<Boolean> field_150;
   public Setting<Boolean> field_151;
   public Setting<Boolean> field_152;
   public Setting<Boolean> field_153;
   public Setting<Boolean> field_154;
   public Setting<Boolean> field_155;
   public Setting<Boolean> field_156;
   public Setting<Boolean> field_157;
   public Setting<Boolean> field_158;
   public Setting<Float> field_159;
   public Setting<Color> field_160;
   public Setting<Color> field_161;
   public Setting<Boolean> field_162;
   public Setting<Float> field_163;
   public final Timer field_164;
   public final Timer field_165;
   public final Timer field_166;
   public final Timer field_167;
   public final Timer field_168;
   public Class_0407 field_169;
   public float[] field_170;
   public int field_171;
   public int field_172;
   public double field_173;
   public float field_174;

   public ScaffoldModule() {
      super("Scaffold", "Speedbridges for you automatically.", Category.PLAYER);
      Settings.initialize(this);
      this.field_164 = new Timer();
      this.field_165 = new Timer();
      this.field_166 = new Timer();
      this.field_167 = new Timer();
      this.field_168 = new Timer();
      this.field_172 = -1;
      this.field_174 = Float.intBitsToFloat(-1082130432);
      Hub.field_2616.method_2(new Class_0828(this, this.field_160, this.field_161, this.field_159, this.field_163, () -> false, this.field_162, 450));
   }

   @Override
   public void onToggle() {
      this.field_164.reset();
      this.field_165.reset();
      this.field_169 = null;
      this.field_170 = null;
      this.field_171 = 0;
      this.field_172 = -1;
   }

   @Override
   public void onEnable() {
      if (!this.method_535()) {
         this.field_173 = field_4219.player.getY();
      }
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      if (this.field_174 > 0.0F) {
         this.method_84(this.field_174);
         this.field_174 = Float.intBitsToFloat(-1082130432);
      }

      if (!field_4219.player.isRiding()) {
         if (this.field_167.method_9(50L) || !field_4219.player.isOnGround() || !field_144.field_3744.getValue()) {
            if (!Class_0464.method_363() || field_4219.player.horizontalCollision) {
               this.field_173 = field_4219.player.getY();
            }

            this.method_79();
            this.field_169 = this.method_81();
            boolean var2 = this.field_172 == -1 || field_4219.player.getInventory().getStack(this.field_172).isEmpty();
            if (this.field_147.getValue() == Class_1302.NONE && this.method_80(field_4219.player.getMainHandStack()) == 0) {
               var2 = true;
            }

            if (var2) {
               this.field_169 = null;
            } else {
               this.method_2(this.field_169);
               if (!RotationManager.method_514() && this.field_169 != null) {
                  this.method_78(this.method_2(Class_0147.PRE));
               }

               if (this.field_170 != null) {
                  Hub.field_2598.method_2(this.field_170, 5);
               }

               if (this.field_165.method_9(500L) || RotationManager.method_513()) {
                  this.field_170 = null;
               }

               this.method_77();
               if (this.field_169 != null && this.field_166.method_9((long)(this.field_148.getValue() != null ? this.field_148.getValue().intValue() : 0))) {
                  if (!this.method_5(this.field_169.method_406(), this.field_172)) {
                     return;
                  }

                  this.field_147
                     .getValue()
                     .method_9(
                        this.field_172,
                        () -> {
                           if (this.field_158.getValue()) {
                              Hub.field_2616.method_2(this, this.field_169.method_406());
                           }

                           Class_0773 var1x = Class_0773.method_2(this.field_169, true);
                           Vec3d var2x = var1x.method_564();
                           if (var1x.method_457() == Direction.DOWN) {
                              var2x = var2x.withAxis(Axis.Y, (double)this.field_169.method_406().getY());
                           } else {
                              var2x = var2x.add(
                                 0.0,
                                 var1x.method_721().isEmpty()
                                    ? Constants.field_688
                                    : var1x.method_721().getBoundingBox().maxY - Double.longBitsToDouble(4576918229304087675L),
                                 0.0
                              );
                           }

                           boolean var3 = Hub.field_2602.method_993();
                           PacketUtil.method_2(field_4219.player, Mode.PRESS_SHIFT_KEY, 0);
                           Class_1035.method_2(this.field_169.method_406(), var2x, var1x.method_457(), this.field_152.getValue(), Hand.MAIN_HAND);
                           if (!var3) {
                              PacketUtil.method_2(field_4219.player, Mode.RELEASE_SHIFT_KEY, 0);
                           }

                           if (RotationManager.method_514()) {
                              this.method_78(this.method_2(Class_0147.POST.method_2(var1x, var2x)));
                           }

                           this.field_166.reset();
                           this.field_165.reset();
                           this.field_164.reset();
                        }
                     );
               }

               if (this.field_164.method_9(150L)) {
                  this.field_164.reset();
                  this.field_169 = null;
               }
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_8 var1) {
      if (this.field_151.getValue() && !field_4219.player.isRiding()) {
         var1.method_463();
      }
   }

   @Subscribe
   public void method_2(Event_9 var1) {
      if (this.field_151.getValue() && RotationManager.method_513() && field_4219.player.isOnGround() && !field_4219.player.isRiding()) {
         Box var2 = field_4219.player.getBoundingBox().offset(var1.method_380(), 0.0, var1.method_396());
         boolean var3 = field_4219.player.isSneaking();
         if (field_4219.world.isSpaceEmpty(var2.stretch(0.0, Double.longBitsToDouble(-4616189618054758400L), 0.0))) {
            if (!var3) {
               field_4219.player.setSneaking(true);
            }

            var1.method_7(0.0, 0.0);
         } else if (!var3) {
            field_4219.player.setSneaking(false);
         }
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (!var1.method_464()) {
         if (var1.method_127() instanceof ClickSlotC2SPacket) {
            this.field_167.reset();
         }
      }
   }

   public void method_77() {
      int var1 = (int)Math.floor(field_4219.player.getY());
      if (this.field_171 != var1 || RotationManager.method_513()) {
         this.field_171 = var1;
         if (this.field_150.getValue() && field_4219.player.input.jumping && field_4219.player.forwardSpeed == 0.0F && field_4219.player.sidewaysSpeed == 0.0F) {
            if (RotationManager.method_513()) {
               if (field_4219.player.isOnGround()) {
                  this.field_174 = this.method_83();
                  this.method_84(Float.intBitsToFloat(1052539785));
               }
            } else if (this.field_169 != null) {
               field_4219.player.setVelocity(0.0, Double.longBitsToDouble(4601237667055665185L), 0.0);
            }
         }
      }
   }

   public void method_2(Class_0407 var1) {
      if (var1 != null) {
         if (this.field_157.getValue() && this.field_168.method_9((long)Hub.field_2623.method_878())) {
            Entity var2 = Class_1035.method_2(var1.method_406(), Hub.field_2623.method_523());
            if (var2 != null) {
               float[] var3 = RotationManager.method_14(var2);
               this.method_78(var3);
               this.field_168.reset();
            }
         }
      }
   }

   public void method_78(float[] var1) {
      if (this.field_155.getValue()) {
         if (RotationManager.method_514()) {
            Hub.field_2598.method_9(var1, 10);
            PacketUtil.method_2(field_4219.player.getX(), field_4219.player.getY(), field_4219.player.getZ(), var1[0], var1[1], field_4219.player.isOnGround());
         } else {
            this.field_170 = var1;
         }
      }
   }

   public void method_79() {
      if (this.method_80(field_4219.player.getMainHandStack()) > 0) {
         this.field_172 = field_4219.player.getInventory().selectedSlot;
      } else {
         int var1 = field_4219.player.getInventory().selectedSlot;
         int var2 = this.method_80(field_4219.player.getInventory().getStack(var1));

         for (int var3 = 0; var3 < 9; var3++) {
            int var4 = this.method_80(field_4219.player.getInventory().getStack(var3));
            if (var4 > var2) {
               var1 = var3;
               var2 = var4;
            }
         }

         if (var2 == 0) {
            this.field_172 = -1;
         } else {
            this.field_172 = var1;
         }
      }
   }

   public int method_80(ItemStack var1) {
      if (var1.getItem() instanceof BlockItem var2
         && (this.field_145.getValue() != null ? this.field_145.getValue().method_2(var2.getBlock(), this.field_146.getValue()) : false)
         && ((DuckAbstractBlock)var2.getBlock()).isCollidable()
         && this.method_2(var2)) {
         return Class_0719.method_2(var2.getBlock().getDefaultState().getCollisionShape(field_4219.world, BlockPos.ORIGIN)) ? 2 : 1;
      }

      return 0;
   }

   public boolean method_2(BlockItem var1) {
      int var2 = Class_0981.method_886();
      return !(var1.getBlock() instanceof FallingBlock) || var2 <= 1;
   }

   public float[] method_2(Class_0147 var1) {
      if (var1 == Class_0147.PRE) {
         Class_0773 var2 = Class_0773.method_2(this.field_169, false);
         float[] var3 = RotationManager.method_78(
            var2.method_564()
               .add(
                  0.0,
                  var2.method_721().isEmpty() ? Constants.field_688 : var2.method_721().getBoundingBox().maxY - Double.longBitsToDouble(4576918229304087675L),
                  0.0
               )
         );
         if (Class_0464.method_363()) {
            double[] var4 = Class_0464.method_2(field_4219.player.getYaw(), field_4219.player.input, Double.longBitsToDouble(4607182418800017408L));
            float var5 = (float)(Math.toDegrees(Math.atan2(var4[1], var4[0])) - Double.longBitsToDouble(4643457506423603200L));
            if (MathHelper.angleBetween(var5, var3[0]) < Float.intBitsToFloat(1114636288)) {
               var3[0] = var5;
            }
         }

         this.field_165.reset();
         return var3;
      } else {
         return RotationManager.method_78(
            var1.field_427
               .add(
                  0.0,
                  var1.field_426.method_721().isEmpty()
                     ? Constants.field_688
                     : var1.field_426.method_721().getBoundingBox().maxY - Double.longBitsToDouble(4576918229304087675L),
                  0.0
               )
         );
      }
   }

   public Class_0407 method_81() {
      BlockPos var1 = null;
      Vec3d var2 = Class_0719.method_2(field_4219.player.getBoundingBox());
      if (this.field_154.getValue()) {
         var2 = var2.withAxis(Axis.Y, this.field_173);
      }

      boolean var3 = field_4219.player.hasVehicle() ? field_4219.player.getVehicle().groundCollision : field_4219.player.groundCollision;
      if (field_4219.player.hasVehicle()) {
         var2 = Class_0719.method_2(field_4219.player.getVehicle().getBoundingBox());
      }

      double var4 = this.method_82((float)var2.getY());

      for (int var6 = 0; var6 < this.field_149.getValue() + 1 && (Class_0464.method_363() && field_4219.player.isOnGround() || var6 <= 0); var6++) {
         var1 = BlockPos.ofFloored(var2.withAxis(Axis.Y, var4)).offset(Class_0382.method_426(), var6);
         if (!var3 && !this.field_154.getValue() && !field_4219.world.getBlockState(var1).isReplaceable()) {
            var1 = var1.up();
         }

         if (this.method_5(var1, this.field_172)) {
            break;
         }

         var1 = null;
      }

      if (var1 == null) {
         return null;
      } else {
         Direction var12 = Class_1035.method_9(var1, this.field_153.getValue());
         if (var12 != null) {
            return new Class_0407(var1, var12);
         } else {
            for (int var7 = 1; var7 < 3; var7++) {
               for (Direction var11 : Direction.values()) {
                  if (var11 != Direction.UP) {
                     var12 = Class_1035.method_9(var1.offset(var11, var7), this.field_153.getValue());
                     if (var12 != null) {
                        return new Class_0407(var1.offset(var11), var12);
                     }
                  }
               }
            }

            return null;
         }
      }
   }

   public double method_82(float var1) {
      Box var2 = field_4219.player.getBoundingBox();
      boolean var3 = field_4219.player.isOnGround();
      if (field_4219.player.hasVehicle()) {
         var2 = field_4219.player.getVehicle().getBoundingBox();
         var3 = field_4219.player.getVehicle().isOnGround();
      }

      double var4 = var2.minX;
      double var6 = var2.minZ;
      double var8 = var2.maxX;
      double var10 = var2.maxZ;
      ArrayList var12 = new ArrayList();
      var12.add(Class_0719.method_2(var2).withAxis(Axis.Y, (double)var1));
      if (var3) {
         var12.addAll(
            List.of(
               new Vec3d(var4, (double)var1, var6),
               new Vec3d(var8, (double)var1, var6),
               new Vec3d(var4, (double)var1, var10),
               new Vec3d(var8, (double)var1, var10)
            )
         );
      }

      double var13 = Double.longBitsToDouble(-4571373524106608640L);

      for (Vec3d var16 : (Iterable<Vec3d>)(Iterable<?>)(var12)) {
         Class_0512 var17 = new Class_0533(var16, var16.add(0.0, Double.longBitsToDouble(-4616189618054758400L), 0.0))
            .method_22((Entity)(field_4219.player.hasVehicle() ? field_4219.player.getVehicle() : field_4219.player))
            .method_565();
         BlockHitResult var18 = Class_0981.method_2(var17);
         double var19 = var18.getType() == Type.MISS
            ? var16.getY() - Double.longBitsToDouble(4607182418800017408L)
            : var18.getPos().getY() - Double.longBitsToDouble(4576918229175238656L);
         var13 = Math.max(var13, var19);
      }

      return var13;
   }

   public boolean method_5(BlockPos var1, int var2) {
      ItemStack var3 = field_4219.player.getInventory().getStack(var2 == -1 ? field_4219.player.getInventory().selectedSlot : var2);
      return var3.getItem() instanceof BlockItem var4
         ? Class_1035.method_2(var1, var4.getBlock(), true, this.field_157.getValue())
         : Class_1035.method_9(var1, true, this.field_157.getValue());
   }

   public float method_83() {
      return (float)field_4219.player.getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH).getBaseValue();
   }

   public void method_84(float var1) {
      field_4219.player.getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH).setBaseValue((double)var1);
   }
}
