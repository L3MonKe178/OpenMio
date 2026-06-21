package me.mioclient.module.player;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import me.mioclient.Hub;
import me.mioclient.api.Class_1226;
import me.mioclient.enum_.Class_0174;
import me.mioclient.enum_.Class_0400;
import me.mioclient.enum_.Class_0403;
import me.mioclient.enum_.Class_0456;
import me.mioclient.enum_.Class_0692;
import me.mioclient.enum_.Class_0710;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.Class_1186;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_34;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_58;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Constants;
import me.mioclient.internal.Class_0277;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0612;
import me.mioclient.internal.RenderUtil;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1116;
import me.mioclient.internal.Class_1138;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_10;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.combat.AutoCrystalModule;
import me.mioclient.module.combat.AutoExpModule;
import me.mioclient.module.combat.CombatmineModule;
import me.mioclient.module.movement.NoSlowModule;
import me.mioclient.runnable.Class_1282;
import me.mioclient.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import nick.Settings;
import org.jetbrains.annotations.Nullable;

public class SpeedMineModule extends Module {
   public static final AutoExpModule field_3981 = Hub.field_2595.method_78(AutoExpModule.class);
   public static AutoCrystalModule field_219 = Hub.field_2595.method_78(AutoCrystalModule.class);
   public static final CombatmineModule field_3982 = Hub.field_2595.method_78(CombatmineModule.class);
   public static final AbstractModule_10 field_3983 = Hub.field_2595.method_78(AbstractModule_10.class);
   public static final AbstractModule_28 field_3984 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static final NoSlowModule field_3985 = Hub.field_2595.method_78(NoSlowModule.class);
   public static ItemSaverModule field_405 = Hub.field_2595.method_78(ItemSaverModule.class);
   public static boolean field_3986;
   public Setting<Class_0710> field_3987;
   public Setting<Float> field_3988;
   public Setting<Float> field_3989;
   public Setting<Boolean> field_3990;
   public Setting<Boolean> field_3991;
   public Setting<Boolean> field_3992;
   public Setting<Boolean> field_3993;
   public Setting<Class_0692> field_3994;
   public Setting<Boolean> field_3995;
   public Setting<Float> field_3996;
   public Setting<Boolean> field_3997;
   public Setting<Boolean> field_3998;
   public Setting<Boolean> field_3999;
   public Setting<Class_0403> field_4000;
   public Setting<Boolean> field_4001;
   public Setting<Class_0400> field_4002;
   public Setting<Boolean> field_4003;
   public Setting<Boolean> field_4004;
   public Setting<Boolean> field_4005;
   public Setting<Float> field_4006;
   public Setting<Boolean> field_4007;
   public Setting<Float> field_4008;
   public Setting<Class_1186> field_4009;
   public Setting<Boolean> field_4010;
   public Setting<Boolean> field_4011;
   public Setting<Class_0174> field_4012;
   public Setting<Color> field_4013;
   public Setting<Color> field_4014;
   public Setting<Set<Block>> field_4015;
   public final ArrayDeque<Boolean> field_4016;
   public final Timer field_4017;
   public final Timer field_4018;
   public final Timer field_4019;
   public final Timer field_4020;
   public static final AtomicBoolean field_4021 = new AtomicBoolean();
   public final AtomicBoolean field_4022;
   public final Class_1116 field_4023;
   public final Class_1138 field_4024;
   @Nullable
   public Class_1282 field_4025;
   public float field_877;
   public float field_4026;
   public int field_4027;
   public int field_1980;
   public Class_0277 field_4028;
   public BlockPos field_3164;
   public BlockPos field_4029;
   public volatile boolean field_4030;

   public SpeedMineModule() {
      super("SpeedMine", "Mines blocks silently.", Category.PLAYER);
      Settings.initialize(this);
      this.field_4016 = new ArrayDeque<>();
      this.field_4017 = new Timer();
      this.field_4018 = new Timer();
      this.field_4019 = new Timer();
      this.field_4020 = new Timer();
      this.field_4022 = new AtomicBoolean();
      this.field_4023 = new Class_1116(this);
      this.field_4024 = new Class_1138();
      this.field_4027 = -1;
      this.field_4028 = null;
      this.field_4030 = false;
      this.field_3993.method_31("RebreakPage");
      this.field_3999.method_31("SwapPage");
   }

   @Override
   public void onEnable() {
      this.reset();
      this.field_4029 = null;
      field_4021.set(false);
   }

   @Override
   public String getInfo() {
      return !Class_1225.method_3(this.field_3164)
         ? null
         : "%.2f".formatted(MathHelper.clamp(this.method_1119() / this.field_3988.getValue(), 0.0F, Float.intBitsToFloat(1065353216)));
   }

   @Subscribe
   public void method_2(Event_58 var1) {
      if (!this.method_686(var1.method_111())) {
         this.field_4029 = null;
         this.reset();
      } else if (!field_4219.player.isSpectator() && !field_4219.player.isCreative()) {
         var1.method_463();
         if (!this.field_4017.method_9(100L) && !field_3986) {
            if (var1.method_111().equals(this.field_3164)) {
               this.field_4017.reset();
            }
         } else if (Class_1225.method_3(var1.method_111()) || field_3986) {
            if (!this.field_3990.getValue() || this.field_4028 == null || !var1.method_111().equals(this.field_4028.method_111())) {
               double var2 = field_4219.player.getEyePos().squaredDistanceTo(var1.method_111().toCenterPos());
               if (!(var2 > (double)(this.field_3989.getValue() * this.field_3989.getValue()))) {
                  this.field_4017.reset();
                  boolean var4 = false;
                  if (this.field_3164 != null && !this.field_3164.equals(var1.method_111())) {
                     this.reset();
                     var4 = true;
                  }

                  if (!var4 && this.field_3164 != null && this.field_3164.equals(var1.method_111())) {
                     if (this.field_3997.getValue() && this.field_4019.method_9(100L)) {
                        this.field_4029 = null;
                        this.reset();
                     }
                  } else {
                     if (this.field_4003.getValue() && this.field_4000.getValue() == Class_0403.SILENT && this.field_4004.getValue()) {
                        if (this.field_4030) {
                           this.field_4030 = false;
                           this.reset();
                           return;
                        }

                        if (field_3981.isToggled() && field_3981.field_3419.getValue() == Class_0456.SILENT && !field_3981.field_1702) {
                           this.reset();
                           return;
                        }
                     }

                     if (!field_3986) {
                        field_3982.method_251(true);
                     }

                     this.field_3164 = this.field_4029 = var1.method_111();
                     Direction var5 = this.method_664(this.field_3164);
                     if (this.field_3992.getValue()) {
                        field_4219.player.swingHand(Hand.MAIN_HAND);
                     }

                     int var6 = this.method_456(this.field_3164);
                     int var7 = field_4219.player.getInventory().selectedSlot;
                     boolean var8 = var6 != -1;
                     if (this.field_4000.getValue() == Class_0403.NORMAL) {
                        var6 = PlayerUtil.method_29(this.field_3164, true);
                        if (var6 != var7 && this.field_4001.getValue()) {
                           this.field_1980 = var7;
                        }
                     }

                     if (var6 != -1) {
                        if (this.field_4003.getValue() && this.field_4000.getValue() == Class_0403.SILENT) {
                           PlayerUtil.method_39(var6);
                        } else if (var7 != var6) {
                           PlayerUtil.method_16(var6);
                        }
                     }

                     if (this.field_3990.getValue()) {
                        PacketUtil.method_2(Action.START_DESTROY_BLOCK, this.field_3164, var5);
                        PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var5);
                        if (this.field_4028 == null) {
                           this.field_4028 = new Class_0277(this.field_3164);
                        }
                     } else {
                        PacketUtil.method_2(Action.START_DESTROY_BLOCK, this.field_3164, var5);
                        if (var8) {
                           PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var5);
                        }
                     }

                     if (var8 && var6 != -1) {
                        if (this.field_4000.getValue() == Class_0403.SILENT) {
                           if (this.field_4003.getValue()) {
                              PlayerUtil.method_39(var6);
                           } else if (var7 != var6) {
                              PlayerUtil.method_16(var7);
                           }
                        }

                        Hub.field_2622.method_99(this.field_3164);
                        this.field_4017.setTime(-1L);
                     }

                     field_4219.player.swingHand(Hand.MAIN_HAND);
                     this.field_4019.reset();
                  }
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.reset();
      this.field_4029 = null;
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_9(Event_34 var1) {
      if (this.field_4003.getValue() && this.field_4000.getValue() == Class_0403.SILENT && this.field_4004.getValue()) {
         this.field_4022.set(true);
         this.field_877 = this.field_4026 = 0.0F;
         Hub.field_2619.method_2(() -> {
            this.field_4022.set(false);
            BlockPos var1x = this.field_3164;
            if (var1x != null) {
               if (this.field_3992.getValue()) {
                  PacketUtil.method_9(Hand.MAIN_HAND);
               }

               if (Class_1225.method_3(var1x)) {
                  field_3986 = true;
                  this.reset();
                  this.method_2(new Event_58(var1x, this.method_664(var1x)));
                  field_3986 = false;
               } else if (this.field_3994.getValue() == Class_0692.FAST) {
                  PacketUtil.method_2(Action.START_DESTROY_BLOCK, var1x, this.method_664(var1x));
                  this.field_4016.clear();
               }
            }
         }, 1);
      }
   }

   @Subscribe
   public void method_4(Event_4 var1) {
      this.field_4023.method_7(var1);
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (RotationManager.method_513()
         && this.field_3988.getValue() < Float.intBitsToFloat(1065353216)
         && !field_3984.method_1052()
         && var1.method_127() instanceof PlayerActionC2SPacket var2
         && var2.getAction() == Action.STOP_DESTROY_BLOCK) {
         PacketUtil.method_2(Action.ABORT_DESTROY_BLOCK, var2.getPos().add(0, 500, 0), var2.getDirection());
      }
   }

   @Subscribe(
      method_800 = 100
   )
   public void method_5(Event_7 var1) {
      if (!field_4021.get()) {
         if (this.field_4000.getValue() != Class_0403.NONE || ItemSaverModule.method_101(field_4219.player.getMainHandStack()) || !field_405.isToggled()) {
            boolean var2 = Class_0396.method_76() > Float.intBitsToFloat(1082130432) || !field_4219.player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING);
            if (this.field_4028 != null
               && !this.field_4028.method_111().equals(this.field_3164)
               && this.field_4028.method_200() >= Float.intBitsToFloat(1065353216)
               && !field_3985.method_573()
               && var2) {
               field_4021.set(true);
               int var9 = field_4219.player.getInventory().selectedSlot;
               int var10 = PlayerUtil.method_29(this.field_4028.method_111(), !this.field_4003.getValue());
               if (this.field_4003.getValue()) {
                  PlayerUtil.method_39(var10);
               } else {
                  PacketUtil.method_9(var10, true);
               }

               this.method_574(this.field_4028.method_111());
               this.field_4020.reset();
               field_3982.field_3712.method_113(this.field_4028.method_111());
               this.field_4028 = null;
               Hub.field_2619.method_2(() -> {
                  if (this.field_4003.getValue()) {
                     PlayerUtil.method_39(var10);
                  } else {
                     PacketUtil.method_9(var9, true);
                     field_4219.player.getInventory().selectedSlot = var9;
                  }

                  field_4021.set(false);
               }, 2);
            } else if (this.field_1980 != -1
               && !Class_1225.method_3(this.field_3164)
               && this.field_4000.getValue() == Class_0403.NORMAL
               && this.field_4001.getValue()) {
               PlayerUtil.method_16(this.field_1980);
               this.field_1980 = -1;
            } else if (!this.method_1113()) {
               if (!field_4219.player.isSpectator() && !field_4219.player.isCreative() && Class_1225.method_3(this.field_3164)) {
                  if (this.field_4006.getValue() == Float.intBitsToFloat(1065353216)
                     || this.method_1119() / this.field_3988.getValue() >= this.field_4006.getValue()
                        && (
                           this.field_4000.getValue() != Class_0403.NONE
                              || !this.field_3998.getValue()
                              || this.field_4027 == field_4219.player.getInventory().selectedSlot
                        )) {
                     this.method_574(this.field_3164);
                  }

                  if (!(this.method_1119() < this.field_3988.getValue())) {
                     if ((
                           this.field_3994.getValue() != Class_0692.INSTANT
                              || this.field_4018.method_2((double)(this.field_3996.getValue() != null ? this.field_3996.getValue().floatValue() : 0.0f), TimeUnit.SECONDS)
                        )
                        && !field_3985.method_573()) {
                        if (!this.field_3995.getValue() || this.method_1117()) {
                           int var3 = field_4219.player.getInventory().selectedSlot;
                           BlockState var4 = field_4219.world.getBlockState(this.field_3164);
                           this.field_4025 = new Class_1282(
                              this, field_4219.player.getMainHandStack().getItem(), this.field_4027, field_4219.player.getInventory().selectedSlot
                           );
                           Direction var5 = this.method_664(this.field_3164);
                           if (this.field_4000.getValue() != Class_0403.NONE
                              && this.field_4027 != -1
                              && field_4219.player.getInventory().selectedSlot != this.field_4027) {
                              if (this.field_4000.getValue() == Class_0403.SILENT && this.field_4003.getValue()) {
                                 PlayerUtil.method_39(this.field_4027);
                              } else {
                                 this.field_1980 = field_4219.player.getInventory().selectedSlot;
                                 PlayerUtil.method_16(this.field_4027);
                              }
                           } else if (var3 != this.field_4027 && this.field_3994.getValue() == Class_0692.INSTANT && this.field_3998.getValue()) {
                              return;
                           }

                           this.field_4018.reset();
                           if (Class_1225.method_3(this.field_3164)) {
                              this.field_4020.reset();
                           }

                           if (this.field_3992.getValue()) {
                              PacketUtil.method_9(Hand.MAIN_HAND);
                           }

                           if (!RotationManager.method_513() && this.field_3994.getValue() != Class_0692.INSTANT) {
                              PacketUtil.method_2(field_4219.player.getX(), field_4219.player.getY(), field_4219.player.getZ(), field_4219.player.isOnGround());
                           }

                           BlockPos var6 = this.field_3164;
                           this.field_4023.method_994(this.field_3164);
                           PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var5);
                           if (field_3984.method_1052() && this.field_3994.getValue() == Class_0692.NONE) {
                              PacketUtil.method_2(Action.ABORT_DESTROY_BLOCK, this.field_3164, var5);
                           }

                           Hub.field_2622.method_100(this.field_3164);
                           if (this.field_3994.getValue() == Class_0692.FAST) {
                              PacketUtil.method_2(Action.ABORT_DESTROY_BLOCK, this.field_3164, var5);
                              PacketUtil.method_2(Action.STOP_DESTROY_BLOCK, this.field_3164, var5);
                              PacketUtil.method_2(Action.START_DESTROY_BLOCK, this.field_3164, var5);
                              this.field_3164 = null;
                           }

                           if (this.field_4000.getValue() == Class_0403.SILENT && this.field_4027 != -1) {
                              int var7 = this.field_4027;
                              Runnable var8 = () -> {
                                 if (this.field_4003.getValue() && this.field_4000.getValue() == Class_0403.SILENT) {
                                    PlayerUtil.method_39(var7);
                                 } else if (var3 != this.field_4027 || this.field_4002.getValue() == Class_0400.TICK) {
                                    PlayerUtil.method_16(var3);
                                 }

                                 field_219.field_4124.set(false);
                              };
                              if (this.field_4002.getValue() == Class_0400.TICK) {
                                 field_4021.set(true);
                                 Hub.field_2619.method_2(() -> {
                                    field_4021.set(false);
                                    var8.run();
                                 }, 2);
                              } else {
                                 var8.run();
                              }
                           }

                           if (this.field_4000.getValue() == Class_0403.SILENT && this.field_4003.getValue()) {
                              Hub.field_2619.method_2(this.field_4025, 2);
                           }

                           if (this.field_3994.getValue() != Class_0692.INSTANT
                              || var4 != null && var4.getBlock() instanceof ShulkerBoxBlock && Hub.field_2622.method_101(this.field_3164)) {
                              this.reset();
                           }

                           if (this.field_4005.getValue() && RotationManager.method_514()) {
                              Hub.field_2598.method_2(RotationManager.method_2(RotationManager.method_78(var6.toCenterPos()), Hub.field_2598.method_509()), 150);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      float var2 = this.field_3991.getValue() ? Hub.field_2602.method_990() : Float.intBitsToFloat(1065353216);
      if (this.field_4028 != null) {
         BlockState var3 = field_4219.world.getBlockState(this.field_4028.method_111());
         int var4 = PlayerUtil.method_29(this.field_4028.method_111(), !this.field_4003.getValue());
         double var5 = Class_1225.method_2(field_4219.player.getInventory().getStack(var4), var3, field_4219.player.isOnGround()) * (double)var2;
         this.field_4028.method_310((float)var5);
         if (field_4219.world.isAir(this.field_4028.method_111())) {
            this.field_4028 = null;
         }
      }

      if (this.field_4029 != null
         && field_4219.world.getBlockState(this.field_4029).getBlock() instanceof ShulkerBoxBlock
         && Hub.field_2622.method_101(this.field_4029)) {
         this.field_4029 = null;
      }

      if (!this.field_4022.get()) {
         this.field_4026 = this.field_877;
         if (this.field_3164 == null) {
            if (this.field_4029 != null
               && !field_4219.player.isCreative()
               && Class_1225.method_3(this.field_4029)
               && this.field_3994.getValue() == Class_0692.FAST) {
               this.field_3164 = this.field_4029;
            }
         } else {
            this.field_4027 = PlayerUtil.method_29(this.field_3164, !this.field_4003.getValue());
            if (this.field_3994.getValue() == Class_0692.INSTANT
               && this.field_3164 != null
               && field_4219.world.getBlockState(this.field_3164).getBlock() == Blocks.AIR) {
               this.field_4018.reset();
            }

            if (!Class_1225.method_3(this.field_3164)) {
               if (this.field_3994.getValue() == Class_0692.FAST) {
                  this.field_4016.add(field_4219.player.isOnGround());
                  if (this.field_4016.size() > 200) {
                     this.field_4016.removeLast();
                  }
               } else if (this.field_3994.getValue() != Class_0692.INSTANT) {
                  this.reset();
               }
            } else {
               BlockState var7 = field_4219.world.getBlockState(this.field_3164);
               ItemStack var8 = field_4219.player.getInventory().getStack(this.method_1115());
               if (this.method_1115() != field_4219.player.getInventory().selectedSlot && this.field_4002.getValue() == Class_0400.SLOW) {
                  var8 = new ItemStack(var8.getItem());
               }

               boolean var9 = false;
               if (this.field_3994.getValue() == Class_0692.FAST) {
                  while (!this.field_4016.isEmpty()) {
                     boolean var6 = this.field_4016.poll();
                     this.field_877 = this.field_877 + (float)(Class_1225.method_2(var8, var7, var6) * (double)var2);
                     var9 = true;
                  }

                  this.field_4026 = this.field_877;
               }

               if (var9 && var7.isOf(Blocks.OBSIDIAN)) {
                  this.field_877 = MathHelper.clamp(this.field_877, 0.0F, Float.intBitsToFloat(1065185444));
                  this.field_4026 = this.field_877;
               } else {
                  this.field_877 = this.field_877 + (float)(Class_1225.method_2(var8, var7, field_4219.player.isOnGround()) * (double)var2);
               }
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() == PreType.PRE && this.field_3164 != null) {
         if (!(field_4219.player.getEyePos().distanceTo(this.field_3164.toCenterPos()) <= (double)(this.field_3989.getValue() != null ? this.field_3989.getValue().floatValue() : 0.0f))) {
            this.method_1116();
         }
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_4007.getValue()) {
         if (this.field_3990.getValue() && this.field_4028 != null && !this.field_4028.method_111().equals(this.field_3164)) {
            this.field_4028.method_2(this, var1);
         }

         if (this.field_3164 != null) {
            VoxelShape var2 = field_4219.world.getBlockState(this.field_3164).getOutlineShape(field_4219.world, this.field_3164);
            boolean var3 = this.field_3994.getValue() == Class_0692.INSTANT;
            if (!var2.isEmpty() && Class_1225.method_3(this.field_3164)) {
               this.field_4024.method_1016();
            } else {
               if (!var3) {
                  return;
               }

               if (!this.field_4010.getValue() && !this.field_4011.getValue()) {
                  return;
               }
            }

            float var4 = MathHelper.clamp(
               MathHelper.lerp(RenderUtil.method_776(), this.field_4026, this.method_1119()) / this.field_3988.getValue(),
               0.0F,
               Float.intBitsToFloat(1065353216)
            );
            if (this.field_4012 == null || this.field_4012.getValue() == null) return;
            Color[] var5 = (this.field_4012.getValue() != null ? this.field_4012.getValue().method_2(this, var4) : null);
            Box var6 = this.field_4009
               .getValue()
               .method_2(
                  this,
                  var2.isEmpty()
                     ? new Box(
                        0.0,
                        0.0,
                        0.0,
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4607182418800017408L)
                     )
                     : var2.getBoundingBox(),
                  var4
               )
               .offset(this.field_3164);
            if (field_4219.world.getBlockState(this.field_3164).isAir()) {
               var6 = new Box(this.field_3164);
            }

            if (this.field_4011.getValue() && !this.field_4010.getValue()) {
               this.field_4024.method_29(var6);
               this.field_4024.method_17(this.field_4008.getValue());
               this.field_4024.method_2(var1.method_10(), var5[0], var5[1], var3 ? Float.intBitsToFloat(1142292480) : Float.intBitsToFloat(1065353216), true);
            } else {
               Class_0612.method_5(var1.method_10(), var6, var5[0]);
               Class_0612.method_2(var1.method_10(), var6, var5[1], this.field_4008.getValue());
            }
         }
      }
   }

   public boolean method_1113() {
      if (this.field_3994.getValue() != Class_0692.INSTANT) {
         return false;
      } else if (this.field_4000.getValue() == Class_0403.NONE) {
         return false;
      } else {
         this.field_4023.method_142();
         boolean var1 = this.field_4023.method_995();
         BlockPos var2 = this.field_3164;
         if (var1) {
            this.reset();
            this.field_4022.set(true);
            Hub.field_2619.method_2(() -> {
               this.field_4022.set(false);
               field_4219.interactionManager.attackBlock(var2, Direction.UP);
            }, 1);
         }

         return var1;
      }
   }

   public void method_574(BlockPos var1) {
      if (this.field_4005.getValue() && !RotationManager.method_514()) {
         Vec3d var2 = Class_1225.method_2(var1, Class_1172.field_3634);
         if (var2 == null) {
            var2 = var1.toCenterPos();
         }

         float[] var3 = RotationManager.method_2(RotationManager.method_78(var2), Hub.field_2598.method_509());
         Hub.field_2598.method_2(var3, 150, true);
      }
   }

   public double method_1114() {
      if (this.field_3164 == null) {
         return 0.0;
      } else {
         BlockState var1 = field_4219.world.getBlockState(this.field_3164);
         float var2 = this.field_3991.getValue() ? Hub.field_2602.method_990() : Float.intBitsToFloat(1065353216);
         return Class_1225.method_2(field_4219.player.getInventory().getStack(this.method_1115()), var1, field_4219.player.isOnGround()) * (double)var2;
      }
   }

   public int method_1115() {
      return this.field_4027 < 0 ? field_4219.player.getInventory().selectedSlot : this.field_4027;
   }

   public void method_1116() {
      this.field_4029 = null;
      this.reset();
   }

   public void reset() {
      if (this.field_3164 != null && !this.method_535()) {
         PacketUtil.method_2(Action.ABORT_DESTROY_BLOCK, this.field_3164, this.method_664(this.field_3164));
         PacketUtil.method_9(Hand.MAIN_HAND);
         ((Class_1226)field_4219.interactionManager).setBreakingBlock(false);
         ((Class_1226)field_4219.interactionManager).setBreakingProgress(0.0F);
      }

      this.field_3164 = null;
      this.field_877 = 0.0F;
      this.field_4027 = -1;
      this.field_4016.clear();
      this.field_4019.reset();
      this.field_4023.reset();
   }

   public boolean method_1117() {
      if (!field_4219.world.getBlockState(this.method_1120()).isOf(Blocks.OBSIDIAN)) {
         return true;
      } else {
         Box var1 = new Box(this.method_1120());
         boolean var2 = false;

         for (Entity var4 : field_4219.world.getEntities()) {
            if (var4 instanceof PlayerEntity && var4 != field_4219.player && !var2) {
               if (var4.getBoundingBox()
                  .stretch(0.0, Double.longBitsToDouble(4607182418800017408L), 0.0)
                  .expand(Double.longBitsToDouble(4607182418800017408L), 0.0, Double.longBitsToDouble(4607182418800017408L))
                  .intersects(var1)) {
                  var2 = true;
               }
            } else if (var4 instanceof EndCrystalEntity && var4.getBoundingBox().expand(0.0, Constants.field_689, 0.0).intersects(var1)) {
               return true;
            }
         }

         return !var2 || this.field_4020.method_9((long)(Float.intBitsToFloat(1148846080) / Hub.field_2602.method_990()));
      }
   }

   public int method_456(BlockPos var1) {
      if (field_3983.isToggled() && field_3983.field_2757.getValue()) {
         return -1;
      } else {
         int var2 = PlayerUtil.method_29(this.field_3164, !this.field_4003.getValue());
         int var3 = field_4219.player.getInventory().selectedSlot;
         double var4 = Class_1225.method_2(
            field_4219.player.getInventory().getStack(var2), field_4219.world.getBlockState(var1), field_4219.player.isOnGround()
         );
         if (this.field_4000.getValue() == Class_0403.NONE && var2 != var3) {
            return -1;
         } else {
            return var4 > (double)(this.field_3988.getValue() != null ? this.field_3988.getValue().floatValue() : 0.0f) ? var2 : -1;
         }
      }
   }

   public BlockPos method_1118() {
      if (this.isToggled()) {
         return this.field_3164;
      } else {
         return field_4219.interactionManager.isBreakingBlock()
               && (double)((Class_1226)field_4219.interactionManager).getBreakingProgress() >= Double.longBitsToDouble(4606281698874543309L)
            ? ((Class_1226)field_4219.interactionManager).getCurrentBreakingBlock()
            : null;
      }
   }

   public float method_1119() {
      return MathHelper.clamp(this.field_877, 0.0F, Float.intBitsToFloat(1065353216));
   }

   public BlockPos method_1120() {
      return this.field_3164;
   }

   public BlockPos method_1121() {
      return switch ((Class_0692)this.field_3994.getValue()) {
         case NONE -> null;
         case FAST -> this.field_4029;
         case INSTANT -> this.field_3164;
      };
   }

   public Direction method_664(BlockPos var1) {
      List var2 = Class_1035.method_39(var1);
      return !var2.isEmpty() ? (Direction)var2.get(0) : Direction.UP;
   }

   public boolean method_686(BlockPos var1) {
      if (this.field_3987 == null || this.field_3987.getValue() == null) return false;
      return (this.field_3987.getValue() != null ? this.field_3987.getValue().method_2(field_4219.world.getBlockState(var1).getBlock(), this.field_4015) : false);
   }

   public float method_760() {
      return this.field_4008.getValue();
   }

   public Class_1282 method_1122() {
      return this.field_4025;
   }

   public boolean method_4(long var1) {
      return this.field_4020.method_9(var1);
   }

   public Class_0277 method_1123() {
      return this.field_4028;
   }

   public static boolean method_1124() {
      return field_4021.get();
   }
}
