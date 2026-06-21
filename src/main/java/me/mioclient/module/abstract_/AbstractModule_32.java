package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_1054;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.PreType;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_30;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_51;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0127;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.ChatUtil;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.combat.OffhandModule;
import me.mioclient.module.movement.NoSlowModule;
import me.mioclient.module.player.SpeedMineModule;
import me.mioclient.record.Class_0258;
import me.mioclient.record.Class_0828;
import me.mioclient.record.Class_1050;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public abstract class AbstractModule_32 extends Module {
   public static final AbstractModule_28 field_4245 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static final AbstractModule_2 field_4246 = Hub.field_2595.method_78(AbstractModule_2.class);
   public static final NoSlowModule noslow = Hub.field_2595.method_78(NoSlowModule.class);
   public final Setting<Integer> field_4247 = this.add(new CustomSetting3<>("Delay", 50, 0, 250).method_22("ms"));
   public final Setting<Integer> field_4248 = this.add(new CustomSetting3<>("BPT", 8, 1, 8));
   public final Setting<Boolean> field_4249 = this.add(new BooleanSetting("AirPlace", false));
   public final Setting<Boolean> field_4250 = this.add(new BooleanSetting("StrictDirection", false));
   public final Setting<Boolean> field_4251 = this.add(new BooleanSetting("Raytrace", false));
   public final Setting<Boolean> field_4252 = this.add(new BooleanSetting("Rotate", false));
   public final Setting<Boolean> field_4253 = this.add(new BooleanSetting("Attack", true));
   public final Setting<Boolean> field_4254 = this.add(new BooleanSetting("AutoDisable", false).method_220());
   public final Setting<Boolean> field_4255 = this.add(new BooleanSetting("Jump", true).method_2(this.field_4254));
   public final Setting<Boolean> field_4256 = this.add(new BooleanSetting("Chorus", false).method_2(this.field_4254));
   public final Setting<Boolean> field_4257 = this.add(new BooleanSetting("Pearl", false).method_2(this.field_4254));
   public final Setting<Boolean> field_4258 = this.add(new BooleanSetting("RubberBand", false).method_2(this.field_4254));
   public final Setting<Boolean> field_4259 = this.add(new BooleanSetting("Render", false).method_460());
   public final Setting<Color> field_4260 = this.add(new ColorSetting("Fill", new Color(189, 153, 255, 8), var1x -> this.field_4259.method_194()));
   public final Setting<Color> field_4261 = this.add(new ColorSetting("Outline", new Color(189, 153, 255, 91), var1x -> this.field_4259.method_194()));
   public final Setting<Float> field_4262 = this.add(
      new CustomSetting3<>("LineWidth", Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1036831949), Float.intBitsToFloat(1077936128))
         .method_2(this.field_4259)
   );
   public final Setting<Boolean> field_4263 = this.add(new BooleanSetting("Fade", true).method_2(this.field_4259));
   public final Setting<Float> field_4264 = this.add(
      new CustomSetting3<>("FadeTime", Float.intBitsToFloat(1065353216), Float.intBitsToFloat(1036831949), Float.intBitsToFloat(1065353216))
         .method_22("s")
         .method_2(this.field_4259, this.field_4263)
   );
   public final Timer field_3756 = new Timer();
   public final Timer field_4265 = new Timer();
   public boolean field_4266;
   public Vec3d field_4267;
   public Direction field_4138;
   public Entity field_4239;
   public int field_4268;
   public int field_4269;
   public double field_609;
   public boolean field_4270;
   public boolean field_4271;
   public boolean field_4272;
   public boolean field_4273;
   public boolean field_4274;

   public AbstractModule_32(String var1, String var2, Category var3, String... var4) {
      super(var1, var2, var3, var4);
      Hub.field_2616.method_2(new Class_0828(this, this.field_4260, this.field_4261, this.field_4262, this.field_4264, () -> true, this.field_4263, 450));
   }

   public AbstractModule_32(String var1, Category var2, String... var3) {
      this(var1, "", var2, var3);
   }

   @Override
   public void onEnable() {
      if (!this.method_535()) {
         this.field_609 = field_4219.player.getY();
      }

      this.field_4268 = 0;
      this.field_4269 = 0;
      this.field_4274 = false;
   }

   @Override
   public <T> Setting<T> add(Setting<T> var1) {
      if (!this.getRegistry().contains(this.field_4264)) {
         return super.add(var1);
      } else {
         this.getRegistry().add(this.getRegistry().indexOf(this.field_4253), var1);
         return var1;
      }
   }

   public abstract List<BlockPos> method_17();

   public boolean method_2(BlockPos var1) {
      return true;
   }

   public void method_2(BlockPos var1, boolean var2) {
   }

   public boolean method_37() {
      return true;
   }

   public void method_33() {
      this.field_4266 = false;
      if (this.field_4274) {
         this.field_4274 = false;
      } else if (!OffhandModule.method_639() && !SpeedMineModule.method_1124()) {
         if (!field_4219.player.isSpectator() && field_4219.player.isAlive() && !field_4219.player.isRiding() && !field_4219.player.isSleeping()) {
            if (!this.method_107()) {
               if (!this.field_4271 || !noslow.method_573()) {
                  if (this.field_3756.method_9((long)(this.field_4247.getValue() != null ? this.field_4247.getValue().intValue() : 0)) && this.method_37()) {
                     List var1 = this.method_17();
                     int var2 = this.method_34();
                     synchronized (var1) {
                        this.method_2(var2, var1);
                        this.method_2(var1);
                        if (!var1.isEmpty()) {
                           Class_0127.method_7(() -> this.method_9(var1));
                        }
                     }

                     this.field_4268 = 0;
                  }
               }
            }
         }
      }
   }

   public void method_2(List<BlockPos> var1) {
      if (this.field_4248.getValue() > 1) {
         var1.sort(Comparator.comparing(var1x -> Class_1035.method_9(var1x, this.field_4250.getValue()) != null ? -1 : 1));
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_2(Event_7 var1) {
      this.field_4267 = null;
      this.field_4239 = null;
      this.method_33();
   }

   @Subscribe
   public void method_2(Event_51 var1) {
      if (var1.method_213() == PreType.POST && (double)var1.method_575() > Constants.field_688 && this.field_4255.getValue()) {
         this.disable();
      }
   }

   @Subscribe
   public void method_2(Event_30 var1) {
      if (var1.method_602().getItem() == Items.CHORUS_FRUIT && !field_4219.player.isSneaking() && !field_4246.isToggled()) {
         if (this.field_4256.getValue()) {
            this.disable();
         } else {
            this.field_4274 = true;
         }
      }
   }

   @Subscribe
   public void method_2(Event_4 var1) {
      if (this.field_4253.getValue() && var1.method_127() instanceof EntitySpawnS2CPacket var2 && var2.getEntityType() == EntityType.END_CRYSTAL) {
         if (OffhandModule.method_639()) {
            return;
         }

         BlockPos var9 = BlockPos.ofFloored(var2.getX(), var2.getY(), var2.getZ());
         List var4 = this.method_17();
         this.method_2(this.method_34(), var4);

         for (BlockPos var6 : (Iterable<BlockPos>)(Iterable<?>)(var4)) {
            if (var6.isWithinDistance(var9, Double.longBitsToDouble(4611686018427387904L))) {
               if (!Hub.field_2623.method_5(var2.getEntityId(), true)) {
                  return;
               }

               PacketUtil.method_9(Hand.MAIN_HAND);
               break;
            }
         }

         Class_0127.method_7(() -> this.method_9(var4));
      }

      if (var1.method_127() instanceof PlayerPositionLookS2CPacket var7) {
         this.field_609 = var7.getY();
         if (field_4219.player.getPos().squaredDistanceTo(var7.getX(), var7.getY(), var7.getZ()) >= Double.longBitsToDouble(4607182418800017408L)
            && this.field_4258.getValue()) {
            this.disable();
            return;
         }
      }

      if (var1.method_127() instanceof PlayerRespawnS2CPacket && this.field_4270) {
         this.disable();
      }

      if (var1.method_127() instanceof OpenScreenS2CPacket var8 && !this.field_3756.method_9(100L)) {
         PacketUtil.method_2(new CloseHandledScreenC2SPacket(var8.getSyncId()));
         var1.method_463();
      }
   }

   public void method_9(List<BlockPos> var1) {
      if (!var1.isEmpty()) {
         for (BlockPos var3 : var1) {
            if (this.field_4253.getValue() && this.field_4265.method_9((long)Hub.field_2623.method_878())) {
               this.field_4239 = Class_1035.method_2(var3, Hub.field_2623.method_523());
               if (this.field_4239 != null) {
                  if (this.field_4252.getValue()) {
                     float[] var4 = RotationManager.method_14(this.field_4239);
                     Hub.field_2598.method_2(RotationManager.method_2(var4, field_4245.method_1051()), this.method_52() + 1);
                  }

                  this.field_4265.reset();
                  break;
               }
            }
         }

         int var10 = this.method_34();
         int var11 = field_4219.player.getInventory().selectedSlot;
         Class_0258 var12 = null;

         for (BlockPos var6 : var1) {
            if (this.field_4269 >= 30) {
               this.field_4269 = 0;
               ChatUtil.method_2(Text.literal(this.getName()).append(" is out of blocks!"), ChatUtil.method_38(-2), Priority.HIGH);
               this.field_4268 = 1337;
               this.disable();
               break;
            }

            if (var10 == -1 || field_4219.player.getInventory().getStack(var10).isEmpty()) {
               this.field_4269++;
               break;
            }

            if (this.field_4268 >= this.field_4248.getValue()) {
               break;
            }

            Class_1050 var7 = this.method_9(var6);
            if (var7.method_114()) {
               this.field_4269 = 0;
            }

            if (var7.method_114() && var6 != var1.getLast() && this.method_35()) {
               Class_0258 var8 = var7.method_209();
               if (var12 == null || !var12.method_4(var6)) {
                  if (var6 == var1.getFirst()) {
                     Class_0258 var9 = new Class_0258(new float[]{field_4219.player.getYaw(), field_4219.player.getPitch()});
                     if (var9.method_4(var6)) {
                        continue;
                     }
                  }

                  PacketUtil.method_2(
                     field_4219.player.getX(),
                     field_4219.player.getY(),
                     field_4219.player.getZ(),
                     var8.method_221()[0],
                     var8.method_221()[1],
                     field_4219.player.isOnGround()
                  );
                  var12 = var8;
               }
            }
         }

         if (!this.field_4273) {
            this.field_4273 = true;
            Hub.field_2619.method_2(() -> this.field_4273 = false, 1);
         }

         if (this.field_4272 && this.method_374()) {
            PacketUtil.method_1100();
         }

         PlayerUtil.method_16(var11);
         this.field_4272 = false;
      }
   }

   public Class_1050 method_9(BlockPos var1) {
      if (OffhandModule.method_639()) {
         return Class_1050.field_3246;
      } else {
         this.field_4138 = Class_1035.method_2(var1, this.field_4250.getValue(), this.field_4251.getValue());
         if (this.field_4138 == null && !this.field_4249.getValue()) {
            return Class_1050.field_3246;
         } else {
            float[] var2 = RotationManager.method_2(var1.toCenterPos(), this.field_4138);
            boolean var3 = this.field_4251.getValue();
            if (this.field_4138 != null) {
               var3 &= !Class_1225.method_2(Class_1172.field_3634.method_2(var1.offset(this.field_4138), this.field_4138.getOpposite()));
            } else {
               var3 &= !Class_1225.method_2(var1);
            }

            if (!this.method_2(var1)) {
               return Class_1050.field_3246;
            } else {
               boolean var4 = true;
               if (!this.field_4272) {
                  PlayerUtil.method_16(this.method_34());
                  if (this.method_374()) {
                     PacketUtil.method_1100();
                  }

                  this.field_4272 = true;
               }

               if (!var3 && this.method_5(var1)) {
                  if (RotationManager.method_513() && field_4245.method_1052()) {
                     var2[0] = (float)((double)var2[0] + Math.random() * Double.longBitsToDouble(4576918229175238656L));
                     var2[1] = (float)((double)var2[1] + Math.random() * Double.longBitsToDouble(4576918229175238656L));
                  }

                  this.field_3756.reset();
                  if (this.field_4259.getValue()) {
                     Hub.field_2616.method_2(this, var1);
                  }

                  this.field_4267 = var1.toCenterPos();
                  if (this.field_4138 != null) {
                     this.field_4267 = this.field_4267.offset(this.field_4138, Constants.field_688);
                  }

                  if (this.field_4252.getValue()) {
                     Hub.field_2598.method_2(RotationManager.method_2(var2, field_4245.method_1051()), this.method_52());
                  }

                  this.field_4268++;
                  var4 = false;
               }

               this.method_2(var1, var4);
               return new Class_1050(!var4, new Class_0258(var2));
            }
         }
      }
   }

   public boolean method_5(BlockPos var1) {
      return Class_1035.method_2(var1, this.field_4138, this.field_4249.getValue(), this.method_374() ? Hand.OFF_HAND : Hand.MAIN_HAND);
   }

   public int method_34() {
      return PlayerUtil.method_5(Items.OBSIDIAN) == -1
         ? PlayerUtil.method_7(
            (Predicate<ItemStack>)(var0 -> {
               if (var0.getItem() instanceof BlockItem var1
                  && var1.getBlock() != Blocks.ANVIL
                  && var1.getBlock().getBlastResistance() >= Float.intBitsToFloat(1142292480)) {
                  return true;
               }

               return false;
            })
         )
         : PlayerUtil.method_5(Items.OBSIDIAN);
   }

   public void method_7(BlockPos var1) {
      if (!field_4219.world.getBlockState(var1).isReplaceable() && field_4219.world.getBlockState(var1).getHardness(field_4219.world, var1) == 0.0F) {
         field_4219.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var1, Direction.DOWN));
         field_4219.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var1, Direction.DOWN));
      }
   }

   public boolean method_35() {
      return field_4245.field_3744.getValue() && field_4245.field_3745.getValue() == Class_1054.SILENT
         ? false
         : this.field_4252.getValue()
            && this.field_4248.getValue() > 1
            && field_4219.player.isOnGround()
            && Hub.field_2615.method_1161() > 1
            && !this.field_4273;
   }

   public boolean method_107() {
      double var1 = field_4219.player.getY() - this.field_609;
      if (field_4219.player.isOnGround()) {
         this.field_609 = field_4219.player.getY();
      }

      if ((
            !(field_4219.player.getY() > this.field_609 + Double.longBitsToDouble(4591870180066957722L))
               || !(var1 >= Double.longBitsToDouble(4600877379321698714L))
               || !this.field_4255.getValue()
         )
         && !field_4219.player.isSleeping()) {
         return false;
      } else {
         this.method_38(false);
         return true;
      }
   }

   public void method_2(int var1, List<BlockPos> var2) {
      ArrayList var3 = new ArrayList();
      Block var4 = null;
      if (var1 != -1 && field_4219.player.getInventory().getStack(var1).getItem() instanceof BlockItem var5) {
         var4 = var5.getBlock();
      }

      for (BlockPos var11 : var2) {
         double var7 = field_4219.player.getEyePos().squaredDistanceTo((double)var11.getX(), (double)var11.getY(), (double)var11.getZ());
         if (!Class_1035.method_2(var11, var4, true, this.field_4253.getValue()) || !(var7 <= Double.longBitsToDouble(4630263366890291200L))) {
            var3.add(var11);
         } else if (Class_1035.method_78(var11) == null) {
            this.field_4266 = true;
         }
      }

      for (BlockPos var12 : (Iterable<BlockPos>)(Iterable<?>)(var3)) {
         var2.remove(var12);
      }
   }

   public boolean method_374() {
      return field_4245.method_1052() && RotationManager.method_513() && this.field_4249.getValue() && this.field_4266;
   }

   public BlockPos method_425() {
      return Class_0382.method_425();
   }

   public int method_52() {
      return 20;
   }

   public void method_5(boolean var1) {
      this.field_4270 = var1;
   }

   public void method_185() {
      this.field_4271 = true;
   }
}
