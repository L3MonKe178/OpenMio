package me.mioclient.module.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import me.mioclient.Hub;
import me.mioclient.api.Class_0468;
import me.mioclient.api.Class_0705;
import me.mioclient.api.Class_0822;
import me.mioclient.enum_.Class_0247;
import me.mioclient.enum_.Class_0285;
import me.mioclient.enum_.Class_0446;
import me.mioclient.enum_.Class_0685;
import me.mioclient.enum_.Class_0692;
import me.mioclient.enum_.Class_1029;
import me.mioclient.enum_.Class_1054;
import me.mioclient.enum_.Class_1140;
import me.mioclient.enum_.Class_1163;
import me.mioclient.enum_.Class_1172;
import me.mioclient.enum_.Class_1384;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_11;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_3;
import me.mioclient.event.Event_34;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0127;
import me.mioclient.internal.Class_0136;
import me.mioclient.internal.Class_0242;
import me.mioclient.internal.Class_0245;
import me.mioclient.internal.Class_0358;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0719;
import me.mioclient.internal.Class_0794;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1035;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.Class_1261;
import me.mioclient.internal.Class_1323;
import me.mioclient.mixin.ducks.DuckEntityStatusS2CPacket;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.abstract_.AbstractModule_39;
import me.mioclient.module.misc.KillEffectsModule;
import me.mioclient.module.player.SpeedMineModule;
import me.mioclient.record.Class_0398;
import me.mioclient.record.Class_0704;
import me.mioclient.record.Class_0828;
import me.mioclient.record.Class_1275;
import me.mioclient.setting.Setting;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import nick.Settings;

public class AutoCrystalModule extends Module {
   public static final ExecutorService field_4048 = Executors.newVirtualThreadPerTaskExecutor();
   public static SpeedMineModule speedmine = Hub.field_2595.method_78(SpeedMineModule.class);
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static AbstractModule_39 field_4049 = Hub.field_2595.method_78(AbstractModule_39.class);
   public Setting<Boolean> field_4050;
   public Setting<Integer> field_4051;
   public Setting<Float> field_4052;
   public Setting<Float> field_4053;
   public Setting<Float> field_4054;
   public Setting<Float> field_4055;
   public Setting<Float> field_4056;
   public Setting<Class_1384> field_4057;
   public Setting<Boolean> field_4058;
   public Setting<Boolean> field_4059;
   public Setting<Boolean> field_4060;
   public Setting<Boolean> field_4061;
   public Setting<Boolean> field_4062;
   public Setting<Boolean> field_4063;
   public Setting<Boolean> field_4064;
   public Setting<Boolean> field_4065;
   public Setting<Boolean> field_4066;
   public Setting<Integer> field_4067;
   public Setting<Integer> field_4068;
   public Setting<Float> field_4069;
   public Setting<Float> field_4070;
   public Setting<Float> field_4071;
   public Setting<Float> field_4072;
   public Setting<Boolean> field_4073;
   public Setting<Boolean> field_4074;
   public Setting<Boolean> field_4075;
   public Setting<Boolean> field_4076;
   public Setting<Class_0446> field_4077;
   public Setting<Boolean> field_4078;
   public Setting<Integer> field_4079;
   public Setting<Class_0685> field_4080;
   public Setting<Boolean> field_4081;
   public Setting<Boolean> field_4082;
   public Setting<Boolean> field_4083;
   public Setting<Boolean> field_4084;
   public Setting<Float> field_4085;
   public Setting<Integer> field_4086;
   public Setting<Boolean> field_4087;
   public Setting<Boolean> field_4088;
   public Setting<Integer> field_4089;
   public Setting<Boolean> field_4090;
   public Setting<Boolean> field_4091;
   public Setting<Color> field_4092;
   public Setting<Color> field_4093;
   public Setting<Float> field_4094;
   public Setting<Boolean> field_4095;
   public Setting<Boolean> field_4096;
   public Setting<Float> field_4097;
   public Setting<Boolean> field_4098;
   public Setting<Boolean> field_4099;
   public Setting<Boolean> field_4100;
   public Setting<Float> field_4101;
   public Setting<Boolean> field_4102;
   public Setting<Float> field_4103;
   public Setting<Float> field_4104;
   public Setting<Boolean> field_4105;
   public Setting<Boolean> field_4106;
   public Setting<Boolean> field_4107;
   public Setting<Boolean> field_4108;
   public Setting<Boolean> field_4109;
   public Setting<Boolean> field_4110;
   public Setting<Float> field_4111;
   public Setting<Boolean> field_4112;
   public Setting<Class_0285> field_4113;
   public Setting<Class_1140> field_4114;
   public Setting<Boolean> field_4115;
   public Setting<Boolean> field_4116;
   public Setting<Float> field_4117;
   public Setting<Float> field_4118;
   public Setting<Class_1172> field_4119;
   public Setting<Boolean> field_4120;
   public final Class_0242 field_4121;
   public final AtomicReference<Class_1275> field_4122;
   public final AtomicReference<Vec3d> field_4123;
   public final AtomicBoolean field_4124;
   public final Class_0242 field_4125;
   public final Class_0242 field_4126;
   public final Class_0242 field_4127;
   public final Class_0242 field_4128;
   public final Class_0242 field_4129;
   public final Class_0242 field_4130;
   public final Class_0242 field_4131;
   public final Class_0242 field_4132;
   public final LinkedList<Long> field_4133;
   public final List<Integer> field_4134;
   public final Mutable field_4135;
   public CompletableFuture<Class_1275> field_3789;
   public volatile BlockPos field_3020;
   public volatile BlockPos field_4136;
   public volatile BlockPos field_4137;
   public volatile Direction field_4138;
   public volatile boolean field_4139;
   public volatile boolean field_4140;
   public volatile boolean field_4141;
   public volatile float field_4142;
   public int field_4143;
   public int field_4144;
   public int field_4145;
   public int field_1071;
   public BlockPos field_4146;
   public boolean skip;
   public float[] field_4147;
   public Class_0704 field_4148;
   public PlayerEntity field_2290;
   public PlayerEntity field_4149;

   public AutoCrystalModule() {
      super("AutoCrystal", "Kills people with end crystals (if you're good).", Category.COMBAT, "crystalaura", "ca", "ac");
      Settings.initialize(this);
      this.field_4121 = new Class_0242();
      this.field_4122 = new AtomicReference<>();
      this.field_4123 = new AtomicReference<>();
      this.field_4124 = new AtomicBoolean();
      this.field_4125 = new Class_0242();
      this.field_4126 = new Class_0242();
      this.field_4127 = new Class_0242();
      this.field_4128 = new Class_0242();
      this.field_4129 = new Class_0242();
      this.field_4130 = new Class_0242();
      this.field_4131 = new Class_0242();
      this.field_4132 = new Class_0242();
      this.field_4133 = new LinkedList<>();
      this.field_4134 = Collections.synchronizedList(new ArrayList<>());
      this.field_4135 = new Mutable();
      this.field_4143 = -1;
      Hub.field_2616.method_2(new Class_0828(this, this.field_4092, this.field_4093, this.field_4094, this.field_4097, this.field_4096, this.field_4095, 1000));
      this.field_4051.method_31("PlaceDelay");
      this.field_4052.method_31("PlaceRange");
      this.field_4053.method_31("PlaceWallRange");
      this.field_4054.method_31("MinPlaceDamage");
      this.field_4055.method_31("MaxSelfPlace");
      this.field_4067.method_31("HitDelay");
      this.field_4068.method_31("HitFactor");
      this.field_4069.method_31("BreakRange");
      this.field_4070.method_31("BreakWallRange");
      this.field_4071.method_31("MinBreakDamage");
      this.field_4072.method_31("MaxSelfHit");
      this.field_4082.method_31("PlaceRotate");
      this.field_4083.method_31("BreakRotate");
      this.field_4085.method_31("FacePlaceHealth");
      this.field_4086.method_31("FacePlaceArmor");
      this.field_4088.method_31("ExtraTicks");
      this.field_4111.method_31("InvincTimeout");
   }

   @Override
   public void onEnable() {
      this.field_4143 = -1;
      this.field_4149 = this.field_2290;
      this.field_2290 = null;
      this.field_4138 = Direction.DOWN;
      this.field_3020 = null;
      this.field_4122.set(null);
      if (this.field_3789 != null) {
         try {
            this.field_3789.complete(null);
         } catch (CancellationException var2) {
         }
      }

      this.field_4128.reset();
      this.field_4132.setTime(-1L);
      this.field_1071 = -1;
   }

   @Override
   public void onDisable() {
      this.method_1137();
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (var1.method_213() == PreType.PRE) {
         this.field_4139 = false;
         if (this.field_4141) {
            this.field_4141 = false;
            return;
         }

         this.field_4141 = false;
         PlayerEntity var2 = this.field_2290 == null && !this.method_78(this.field_4149) ? this.field_4149 : this.field_2290;
         if (var2 == null) {
            double var3 = Double.longBitsToDouble(9218868437227405311L);

            for (PlayerEntity var6 : field_4219.world.getPlayers()) {
               double var7 = (double)field_4219.player.distanceTo(var6);
               if (!this.method_78(var6) && !(var7 > var3)) {
                  var3 = var7;
                  var2 = var6;
               }
            }

            if (var2 == null) {
               return;
            }

            this.field_2290 = var2;
         }

         for (Entity var4 : field_4219.world.getEntities()) {
            if (var4 instanceof ItemEntity) {
               ItemEntity var10 = (ItemEntity)var4;
               if (var10.getStack().getItem() == Items.OBSIDIAN && var10.getStack().getCount() >= 8 && var4.isAlive()) {
                  Box var11 = var2.getBoundingBox();
                  if (var4.getBoundingBox().intersects(var11.withMinY(var11.getCenter().getY()).expand(Class_0245.field_688, 0.0, Class_0245.field_688))) {
                     this.field_4141 = true;
                     break;
                  }
               }
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_11 var1) {
      if (!this.method_535()) {
         if (!this.method_1144()) {
            if (this.field_3789 != null && this.field_3789.isDone()) {
               try {
                  Class_1275 var2 = this.field_3789.get();
                  if (var2 == null) {
                     this.field_4122.set(null);
                     this.field_4142 = 0.0F;
                  } else {
                     this.field_4122.set(var2);
                     this.field_4142 = (float)var2.method_445();
                     this.field_2290 = var2.method_444();
                  }
               } catch (ExecutionException | InterruptedException var9) {
                  var9.printStackTrace();
               }
            }

            if (this.field_3789 == null || this.field_3789.isDone() || this.field_3789.isCancelled()) {
               this.method_1134();
               this.field_3789 = CompletableFuture.supplyAsync(this::method_1128, field_4048).exceptionally(var1x -> this.method_2(var1x, "PlaceThread"));
            }

            synchronized (this.field_4134) {
               for (int var4 : this.field_4134) {
                  if (field_4219.world.getEntityById(var4) instanceof EndCrystalEntity var6) {
                     ((Class_0468)var6).setMioAttacked(true);
                  }
               }

               this.field_4134.removeIf(var0 -> field_4219.world.getEntityById(var0) != null);
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_7 var1) {
      this.field_4140 = false;
      this.field_4136 = null;
      Entity var2 = field_4219.world.getEntityById(this.field_4143);
      if (var2 == null || !var2.isAlive()) {
         this.field_4144 = 0;
      }

      if (field_4219.player.age % 4 == 0) {
         long var3 = System.currentTimeMillis();

         while (!this.field_4133.isEmpty() && this.field_4133.get(0) != null && var3 - this.field_4133.get(0) > 1000L) {
            try {
               this.field_4133.remove();
            } catch (NullPointerException var6) {
            }
         }
      }

      KillEffectsModule var7 = Hub.field_2599.method_78(KillEffectsModule.class);
      if (var7 != null && var7.isToggled() && !this.method_78(this.field_2290)) {
         var7.method_185(this.field_2290);
      }

      if (!this.method_639()) {
         this.method_1130();
         if (this.field_4126.method_9((long)this.field_4051.getValue().intValue())) {
            Class_0127.method_7(this::method_1129);
         }

         Vec3d var4 = this.field_4123.get();
         if (this.field_4081.getValue()) {
            if (var4 != null && (!Class_0485.method_514() || this.method_1131())) {
               Hub.field_2598.method_2(Class_0485.method_2(Class_0485.method_78(var4), Hub.field_2598.method_509()), 100, true);
               if (this.field_4125.method_9(500L)) {
                  this.field_4122.lazySet(null);
                  this.field_4123.lazySet(null);
               }
            } else if (Class_0485.method_514() && !this.method_1131() && this.field_4147 != null) {
               Class_1261.method_2(
                  field_4219.player.getX(),
                  field_4219.player.getY(),
                  field_4219.player.getZ(),
                  this.field_4147[0],
                  this.field_4147[1],
                  field_4219.player.isOnGround()
               );
               this.field_4147 = null;
            }
         }
      }
   }

   @Subscribe
   public void method_9(Event_3 var1) {
      if (this.field_4090.getValue() && this.field_4088.getValue() && this.field_2290 != null) {
         for (int var2 = 1; var2 < Class_0719.method_2(this.field_2290, this.field_4089); var2++) {
            Box var3 = Hub.field_2612.method_2(this.field_2290, var2 - 1);
            Box var4 = Hub.field_2612.method_2(this.field_2290, var2);
            Class_0838.field_2672.method_2(var1.method_10(), Class_0719.method_2(var3), Class_0719.method_2(var4), this.field_4093.getValue());
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof EntitySpawnS2CPacket var2) {
         this.field_4148 = Class_0704.method_9(var2);
         if (!this.method_1132()) {
            try {
               if (this.method_639()) {
                  return;
               }

               this.method_2(var2);
            } catch (Throwable var4) {
            }
         }
      }

      if (var1.method_127() instanceof EntityStatusS2CPacket var5
         && var5.getStatus() == 3
         && this.field_2290 != null
         && ((DuckEntityStatusS2CPacket)var5).getId() == this.field_2290.getId()) {
         ((Class_0705)this.field_2290).setServerSideDead(true);
      }

      if (var1.method_127() instanceof DeathMessageS2CPacket) {
         this.field_4106.method_78(false);
      }
   }

   @Subscribe
   public void method_2(Event_34 var1) {
      if (this.field_4145 > 0) {
         this.field_4145--;
      } else {
         this.field_4129.reset();
         this.field_4128.reset();
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_4106.method_78(false);
   }

   @Override
   public String getInfo() {
      return this.field_2290 != null && !this.method_78(this.field_2290)
         ? "%s, %.1f, %d".formatted(this.field_2290.getGameProfile().getName(), this.field_4142, this.field_4133.size())
         : super.getInfo();
   }

   public void method_2(EntitySpawnS2CPacket var1) {
      if (var1.getEntityType() == EntityType.END_CRYSTAL && this.field_2290 != null) {
         Vec3d var2 = new Vec3d(var1.getX(), var1.getY(), var1.getZ());
         if (this.field_3020 != null && var2.squaredDistanceTo(this.field_3020.toCenterPos()) <= Double.longBitsToDouble(4621256167635550208L)) {
            this.field_4133.add(System.currentTimeMillis());
         }

         if (this.field_4077.getValue() != Class_0446.NONE && this.field_4066.getValue() && !this.method_107(this.field_2290)) {
            if (this.method_1141() && this.method_1142()) {
               return;
            }

            boolean var3 = Class_1225.method_9(var2);
            if (field_4219.player.getEyePos().distanceTo(var2) > (double)(var3 ? this.field_4069.getValue() : this.field_4070.getValue()).floatValue()) {
               return;
            }

            double var4 = (double)Class_1323.method_2(var2, this.field_2290);
            double var6 = (double)Class_1323.method_2(var2, field_4219.player);
            Class_0247 var8 = Class_0247.method_2(this.field_2290, var4);
            if (this.field_4074.getValue() && var4 <= var6 && !this.field_4106.getValue() && !var8.field_696) {
               return;
            }

            if (var4 < Class_1029.HIT.method_34(this.field_2290) || Class_1029.HIT.method_2(this.field_2290, var6, var4, var8)) {
               return;
            }

            BlockPos var9 = BlockPos.ofFloored(var2);
            if (this.field_4077.getValue() == Class_0446.STRICT && this.field_4146.equals(var9)) {
               return;
            }

            if (this.field_4081.getValue() && this.field_4083.getValue()) {
               this.method_22(var2);
            }

            if (Hub.field_2623.method_5(var1.getEntityId(), true)) {
               this.field_4136 = var9;
               this.field_4146 = var9;
               this.field_4143 = var1.getEntityId();
               this.field_4144 = 0;
               this.field_4132.reset();
            }

            boolean var10 = field_4219.player.isHolding(Items.END_CRYSTAL) || Class_0396.method_76() >= Float.intBitsToFloat(1090519040);
            if (!field_144.method_1052()) {
               var10 = true;
            }

            if (this.field_4057.getValue().method_1214() && !this.field_4139 && var10) {
               this.field_4130.reset();
               Class_0127.method_7(this::method_1129);
               if (this.field_4057.getValue() == Class_1384.STRICT) {
                  this.field_4139 = true;
               }
            }

            this.method_176(var1.getEntityId());
         }
      }
   }

   public Class_1275 method_1128() {
      Class_1275 var1 = this.field_4122.get();
      Class_0398 var2 = null;
      Class_1275 var3 = null;
      Class_0794 var4 = new Class_0794()
         .method_17(this.field_4058.getValue())
         .method_35(this.field_4062.getValue())
         .method_107(this.field_4120.getValue() && !this.method_1141())
         .method_374(this.field_4064.getValue() && this.method_1136())
         .method_425(this.method_1141() && speedmine.field_3994.getValue() != Class_0692.INSTANT)
         .method_101(this.field_4088.getValue() ? this.field_4089.getValue() : 0)
         .method_33(this.field_4069.getValue());
      if (var1 != null && var4.method_185(var1.method_406())) {
         var2 = this.method_4(var1.method_406(), Class_1225.method_36(var1.method_406()));
      }

      for (double var5 = -Math.floor((double)this.field_4052.getValue().floatValue());
         var5 < Math.ceil((double)this.field_4052.getValue().floatValue());
         var5 += Double.longBitsToDouble(4607182418800017408L)
      ) {
         for (double var7 = -Math.floor((double)this.field_4052.getValue().floatValue());
            var7 < Math.ceil((double)this.field_4052.getValue().floatValue());
            var7 += Double.longBitsToDouble(4607182418800017408L)
         ) {
            for (double var9 = -Math.floor((double)this.field_4052.getValue().floatValue());
               var9 < Math.ceil((double)this.field_4052.getValue().floatValue());
               var9 += Double.longBitsToDouble(4607182418800017408L)
            ) {
               this.field_4135.set(field_4219.player.getX() + var5, field_4219.player.getEyeY() + var7, field_4219.player.getZ() + var9);
               boolean var11 = Class_1225.method_36(this.field_4135);
               if (var3 == null || var3.method_446() == Class_1163.OBSIDIAN || var11) {
                  double var12 = field_4219.player.getEyePos().distanceTo(this.field_4135.toCenterPos());
                  if (!(var12 > (double)this.field_4052.getValue().floatValue()) && var4.method_185(this.field_4135)) {
                     List var14 = this.field_4119.getValue().method_38(this.field_4135);
                     boolean var15 = Class_1225.method_2(var14);
                     if ((var15 || !(var12 > (double)this.field_4053.getValue().floatValue()) || this.field_4063.getValue())
                        && (!this.field_4059.getValue() || !Class_1035.method_39(this.field_4135).isEmpty())) {
                        double var16 = (double)Class_1323.method_2(
                           Vec3d.ofCenter(this.field_4135, Double.longBitsToDouble(4607182418800017408L)),
                           field_4219.player,
                           this.method_35(field_4219.player),
                           Double.longBitsToDouble(4618441417868443648L),
                           true,
                           null,
                           this.field_4135
                        );
                        Class_0398 var18 = this.method_4(this.field_4135, var11);
                        if (var18 != null
                           && !Class_1029.PLACE
                              .method_2(var18.method_444(), var16, var18.method_445(), Class_0247.method_2(var18.method_444(), var18.method_445()))) {
                           Class_1163 var19 = Class_1163.NONE;
                           if (var2 == null || var1.method_406().equals(this.field_4135) || !((double)Math.round(var2.method_445()) >= var18.method_445())) {
                              if (!var15 && var12 > (double)this.field_4053.getValue().floatValue()) {
                                 var19 = Class_1163.RAYTRACE;
                              }

                              if (this.field_4064.getValue() && !var11) {
                                 if (this.method_35(var18.method_444()).union(var18.method_444().getBoundingBox()).intersects(new Box(this.field_4135))
                                    || !Class_1035.method_7(this.field_4135, false)
                                    || !this.field_4065.getValue() && Class_1035.method_9(this.field_4135, this.field_4059.getValue()) == null) {
                                    continue;
                                 }

                                 var19 = Class_1163.OBSIDIAN;
                              }

                              var3 = new Class_1275(this.field_4135.toImmutable(), var18.method_444(), var18.method_445(), var19).method_2(var3);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   public void method_1129() {
      if (!this.method_1141()) {
         this.field_4124.set(false);
      }

      if (this.field_4050.getValue() && !this.method_1132() && !this.field_4124.get()) {
         boolean var1 = field_4219.player.getOffHandStack().getItem() == Items.END_CRYSTAL;
         boolean var2 = field_4219.player.isHolding(Items.END_CRYSTAL);
         if (!this.field_4087.getValue() && !this.field_4141
            || !(this.field_4142 < this.field_4054.getValue())
            || !(this.field_4142 < Float.intBitsToFloat(1084227584))
            || this.field_4126.method_9(this.method_1138())) {
            Class_1275 var3 = this.field_4122.get();
            if (var3 == null) {
               this.method_1137();
            } else {
               BlockPos var4 = var3.method_406();
               if (var4 != null && this.field_2290 != null) {
                  double var5 = Class_1029.PLACE.method_34(this.field_2290);
                  double var7 = (double)Class_1323.method_2(
                     Vec3d.ofCenter(var4, Double.longBitsToDouble(4607182418800017408L)),
                     this.field_2290,
                     this.method_35(this.field_2290),
                     Double.longBitsToDouble(4618441417868443648L),
                     true,
                     this.method_1141() ? speedmine.method_1118() : null,
                     var4
                  );
                  if (this.field_4088.getValue() && this.field_4089.getValue() > 0 && var7 > Double.longBitsToDouble(4611686018427387904L) && var7 < var5) {
                     Box var9 = Class_0719.method_4(this.field_2290);
                     var7 = Math.max(var7, this.method_2(var4, this.field_2290, var9, true));
                  }

                  if (var7 < var5) {
                     this.method_1137();
                  } else if (!Class_1225.method_36(var4)) {
                     if (var3.method_446() == Class_1163.OBSIDIAN && this.field_4064.getValue()) {
                        this.method_214(var4);
                     }
                  } else {
                     int var14 = -1;
                     int var10 = Class_0136.method_5(Items.END_CRYSTAL);
                     if (!this.field_4061.getValue()
                        || Class_1035.method_2(var4, this.field_4058.getValue(), true, true, true, this.field_4062.getValue(), this.field_4120.getValue())) {
                        if (field_4219.world.getBlockState(var4.up()).getBlock() == Blocks.FIRE) {
                           field_4219.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var4.up(), Direction.DOWN));
                           field_4219.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var4.up(), Direction.DOWN));
                        } else {
                           boolean var11 = false;
                           boolean var12 = this.method_514() && this.field_4114.getValue() == Class_1140.ALTERNATIVE;
                           boolean var13 = false;
                           if (!var2 && this.method_1141() && speedmine.field_4004.getValue() && speedmine.field_4003.getValue()) {
                              var12 = true;
                              var13 = true;
                           }

                           if (!var12 || this.skip && field_4219.player.currentScreenHandler.getCursorStack().isEmpty()) {
                              if (var12 && var10 == -1) {
                                 var10 = Class_0136.method_9(Items.END_CRYSTAL);
                              }

                              if (this.field_4113.getValue() != Class_0285.NONE && !var2 && !this.method_1133() && var10 != -1) {
                                 var14 = field_4219.player.getInventory().selectedSlot;
                                 if (var12) {
                                    Class_0136.method_39(var10);
                                    var11 = true;
                                    this.field_4124.set(var13);
                                 } else if (this.method_514() && this.field_4114.getValue() == Class_1140.PICK) {
                                    field_4219.interactionManager.pickFromInventory(var10);
                                 } else {
                                    if (this.field_4113.getValue() == Class_0285.SILENT) {
                                       speedmine.field_4030 = true;
                                       this.method_1143();
                                    }

                                    if (this.method_1135()) {
                                       this.field_1071 = var14;
                                    }

                                    Class_0136.method_16(var10);
                                 }
                              }

                              if (var10 != -1 && this.field_4113.getValue() != Class_0285.NONE || var2) {
                                 this.method_2(var4, var1, this.field_4138);
                                 if (this.method_514() || var11) {
                                    if (var14 != -1) {
                                       if (var11) {
                                          Class_0136.method_39(var10);
                                       } else if (this.field_4113.getValue() == Class_0285.SILENT) {
                                          if (this.method_514() && this.field_4114.getValue() == Class_1140.PICK) {
                                             field_4219.interactionManager.pickFromInventory(var10);
                                          } else {
                                             this.method_1143();
                                             Class_0136.method_16(var14);
                                          }
                                       }
                                    }

                                    if (this.field_4116.getValue() || var13 || this.skip) {
                                       this.skip = !this.skip;
                                    }
                                 }
                              }
                           } else {
                              this.skip = true;
                           }
                        }
                     }
                  }
               } else {
                  this.method_1137();
               }
            }
         }
      }
   }

   public void method_2(BlockPos var1, boolean var2, Direction var3) {
      if (var1 != null && var3 != null) {
         if (var1.equals(this.field_4136) || this.field_4136 == null || this.field_4057.getValue().method_1214()) {
            List var4 = Class_1035.method_39(var1);
            Direction var5 = var3;
            if (!var4.isEmpty()) {
               var5 = (Direction)var4.get(0);
            }

            Vec3d var6 = var1.toCenterPos().offset(var5, Class_0245.field_688);
            double var7 = Double.longBitsToDouble(9218868437227405311L);
            double[] var9 = new double[]{Double.longBitsToDouble(4587366580439587226L), Double.longBitsToDouble(4606732058837280358L)};

            for (double var13 : var9) {
               for (double var18 : new double[]{
                  Double.longBitsToDouble(4587366580439587226L), Double.longBitsToDouble(4606732058837280358L), Double.longBitsToDouble(4607092346807469998L)
               }) {
                  for (double var23 : var9) {
                     Vec3d var25 = Vec3d.of(var1).add(var13, var18, var23);
                     BlockHitResult var26 = field_4219.world
                        .raycast(new RaycastContext(field_4219.player.getEyePos(), var25, ShapeType.COLLIDER, FluidHandling.NONE, field_4219.player));
                     double var27 = var26.getPos().distanceTo(field_4219.player.getEyePos());
                     if ((var26.getType() == Type.MISS || var26.getBlockPos().equals(var1))
                        && var25.distanceTo(field_4219.player.getEyePos()) <= (double)this.field_4052.getValue().floatValue()
                        && var27 < var7
                        && (var4.contains(var26.getSide()) || !this.field_4059.getValue())) {
                        var6 = var26.getPos();
                        var5 = var26.getSide();
                        var7 = var27;
                     }
                  }
               }
            }

            if (this.field_4082.getValue() && this.field_4081.getValue() && !this.field_4140) {
               this.method_22(var6);
            }

            Hand var29 = var2 ? Hand.OFF_HAND : Hand.MAIN_HAND;
            Class_1261.method_2(var29, new BlockHitResult(var1.toCenterPos().offset(var5, Class_0245.field_688), var5, var1, false));
            field_4219.player.swingHand(var29);
            this.field_3020 = var1;
            if (this.field_4091.getValue()) {
               Hub.field_2616.method_2(this, var1);
            }

            this.field_4126.reset();
         }
      }
   }

   public void method_214(BlockPos var1) {
      if (this.method_1136()) {
         int var2 = Class_0136.method_5(Items.END_CRYSTAL);
         if (this.field_4113.getValue() == Class_0285.SILENT && this.field_4114.getValue() == Class_1140.ALTERNATIVE) {
            var2 = Class_0136.method_9(Items.END_CRYSTAL);
         }

         if (field_4219.player.getOffHandStack().isOf(Items.END_CRYSTAL) || var2 != -1) {
            int var3 = field_4219.player.getInventory().selectedSlot;
            int var4 = Class_0136.method_5(Items.OBSIDIAN);
            Direction var5 = Class_1035.method_9(var1, this.field_4059.getValue());
            boolean var6 = this.method_514() && this.field_4114.getValue() == Class_1140.ALTERNATIVE
               || this.field_4114.getValue() == Class_1140.PICK && this.method_1141() && speedmine.method_1118() != null;
            boolean var7 = this.field_4065.getValue() && var5 == null && field_144.method_1052();
            Hand var8 = Hand.MAIN_HAND;
            if (var6 && var4 == -1) {
               var4 = Class_0136.method_9(Items.OBSIDIAN);
            }

            if (var4 != -1 && (var5 != null || this.field_4065.getValue()) && Class_1035.method_7(var1, false)) {
               if (var4 != var3) {
                  if (var6) {
                     Class_0136.method_39(var4);
                  } else if (this.method_514() && this.field_4114.getValue() == Class_1140.PICK) {
                     field_4219.interactionManager.pickFromInventory(var4);
                  } else {
                     speedmine.field_4030 = true;
                     Class_0136.method_16(var4);
                  }
               }

               if (var7) {
                  Class_1261.method_1100();
                  var8 = Hand.OFF_HAND;
               }

               Class_1035.method_2(var1, var5, this.field_4065.getValue(), var8);
               this.field_4132.reset();
               if (var7) {
                  Class_1261.method_1100();
               }

               if (this.field_4082.getValue() && this.field_4081.getValue()) {
                  Vec3d var9 = var1.toCenterPos();
                  if (var5 != null) {
                     var9 = var9.offset(var5, Double.longBitsToDouble(4602678819172646912L));
                  }

                  this.method_22(var9);
               }

               this.field_4137 = var1;
               if (var3 != -1 && var4 != var3) {
                  if (var6) {
                     Class_0136.method_39(var4);
                  } else if (this.method_514() && this.field_4114.getValue() == Class_1140.PICK) {
                     field_4219.interactionManager.pickFromInventory(var4);
                  } else {
                     Class_0136.method_16(var3);
                  }
               }
            }
         }
      }
   }

   public void method_1130() {
      if (this.field_4066.getValue()
         && this.field_4127.method_9((long)this.field_4067.getValue().intValue())
         && !this.method_1132()
         && !this.method_107(this.field_2290)) {
         if (this.field_4130.method_9((long)Hub.field_2602.method_983())
            || this.field_4077.getValue() == Class_0446.NONE
            || !this.method_1142()
            || this.method_1141()) {
            SpeedMineModule var1 = Hub.field_2599.method_78(SpeedMineModule.class);
            Entity var2 = null;
            double var3 = Double.longBitsToDouble(-4569777033223077888L);
            boolean var5 = this.method_1141();
            if (!Class_1225.method_3(speedmine.method_1118())) {
               var5 = false;
            }

            BlockPos var6 = var1.method_1118();
            if (!Class_1225.method_3(var6)) {
               var6 = null;
            }

            for (Entity var8 : field_4219.world.getEntities()) {
               if (var8 instanceof EndCrystalEntity && this.method_2((EndCrystalEntity)var8)) {
                  double var9;
                  if (this.skip) {
                     var9 = (double)Class_1323.method_2(var8.getPos(), this.field_2290, this.method_35(this.field_2290));
                  } else {
                     var9 = this.method_2(BlockPos.ofFloored(var8.getPos()).down(), this.field_2290, this.method_35(this.field_2290), false);
                  }

                  double var11 = (double)Class_1323.method_2(var8.getPos(), field_4219.player, field_4219.player.getBoundingBox());
                  Class_0247 var13 = Class_0247.method_2(this.field_2290, var9);
                  if ((!this.field_4074.getValue() || !(var9 <= var11) || this.field_4106.getValue() || var13.field_696)
                     && !Class_1029.HIT.method_2(this.field_2290, var11, var9, var13)) {
                     if (this.field_3020 != null
                        && var8.getBoundingBox().intersects(new Box(this.field_3020.up()).stretch(0.0, Double.longBitsToDouble(4607182418800017408L), 0.0))
                        && var2 == null
                        && (!var5 || var6 == null)) {
                        var2 = var8;
                     }

                     if (!(var9 < Class_1029.HIT.method_34(this.field_2290)) && var9 > var3) {
                        var3 = var9;
                        var2 = var8;
                     }
                  }
               }
            }

            if (var2 != null) {
               this.method_2((EndCrystalEntity)var2, field_4219.player.getOffHandStack().getItem() == Items.END_CRYSTAL);
            }
         }
      }
   }

   public void method_2(EndCrystalEntity var1, boolean var2) {
      if (var1.getId() == this.field_4143 && this.field_4131.method_9(500L) && var1.isAlive() && !var1.isRemoved()) {
         this.field_4144++;
      } else {
         this.field_4144 = 0;
      }

      this.field_4143 = var1.getId();
      if (this.field_4144 >= 5) {
         this.field_4131.reset();
      }

      if (!this.method_1131() || !this.field_4075.getValue()) {
         if (Hub.field_2623.method_80(var1.getId())) {
            ((Class_0468)var1).setMioAttacked(true);
            this.method_176(var1.getId());
            this.field_4136 = var1.getBlockPos().down();
            if (this.field_4083.getValue() && this.field_4081.getValue()) {
               this.method_22(Class_0719.method_9(var1.getBoundingBox()));
               this.field_4140 = true;
            }

            this.field_4127.reset();
            this.field_4132.reset();
         }
      }
   }

   public boolean method_2(EndCrystalEntity var1) {
      if (var1.age < this.field_4079.getValue() && !this.method_1141()) {
         return false;
      } else if (!var1.isRemoved() && this.field_2290 != null) {
         boolean var2 = field_4219.player.canSee(var1);
         return field_4219.player.getEyePos().distanceTo(var1.getPos())
            <= (double)(var2 ? this.field_4069.getValue() : this.field_4070.getValue()).floatValue();
      } else {
         return false;
      }
   }

   public void method_22(Vec3d var1) {
      if (var1 != null) {
         if (Class_0485.method_513() && field_144.field_3745.getValue() == Class_1054.SILENT && !this.method_7(100L)) {
            float[] var2 = Class_0485.method_78(var1);
            Hub.field_2598.method_29(var2);
            this.field_4147 = var2;
         }

         this.field_4125.reset();
         this.field_4123.set(var1);
      }
   }

   public void method_176(int var1) {
      if (!this.field_4068.method_105()) {
         for (int var2 = 1; var2 < this.field_4068.getValue(); var2++) {
            int var3 = var1 + var2;
            if (field_144.field_3744.getValue()) {
               var3--;
            }

            for (Entity var5 : field_4219.world.getEntities()) {
               if (var5 instanceof LivingEntity && var5.isRemoved() || !var5.isAlive()) {
                  return;
               }
            }

            if (this.field_4148 != null && this.field_4148.method_685() != EntityType.END_CRYSTAL && this.field_4148.method_684() == var3) {
               break;
            }

            Class_1261.method_36(var3);
            Class_1261.method_9(Hand.MAIN_HAND);
            this.field_4134.add(var3);
         }
      }
   }

   public boolean method_1131() {
      return this.method_7(500L);
   }

   public boolean method_7(long var1) {
      return !Hub.field_2602.method_984().method_9(200L) ? true : !this.field_4131.method_9((long)((float)var1 / Hub.field_2602.method_990()));
   }

   public boolean method_639() {
      if (this.method_535()) {
         return true;
      } else if (Class_1035.method_932()) {
         return true;
      } else if (OffhandModule.method_639() || SpeedMineModule.method_1124()) {
         return true;
      } else if (this.method_78(this.field_2290)) {
         return true;
      } else if (field_4219.player.isSleeping()) {
         return true;
      } else if (field_4219.player.isUsingItem() && this.field_4100.getValue()) {
         return true;
      } else {
         boolean var1 = field_4219.interactionManager.isBreakingBlock() || Class_1225.method_3(speedmine.method_1120()) && speedmine.isToggled();
         if (var1 && this.field_4099.getValue()) {
            return true;
         } else if (this.field_4106.getValue()) {
            return false;
         } else {
            return field_4049.isToggled()
                  && field_4049.field_3792.get() != null
                  && field_4049.field_3793 != null
                  && this.field_4122.get() != null
                  && field_4049.field_3793.method_445() > this.field_4122.get().method_445()
               ? true
               : Class_0396.method_76() < this.field_4101.getValue() && !this.field_4106.getValue();
         }
      }
   }

   public boolean method_1132() {
      return !this.field_4129.method_9((long)(Float.intBitsToFloat(1112014848) * this.field_4118.getValue()));
   }

   public boolean method_1133() {
      return this.field_4113.getValue() == Class_0285.NORMAL
         && !this.field_4128.method_9((long)(Float.intBitsToFloat(1112014848) * this.field_4117.getValue()));
   }

   public boolean method_514() {
      return this.field_4113.getValue() == Class_0285.SILENT;
   }

   public Box method_35(PlayerEntity var1) {
      return this.field_4088.getValue() ? Hub.field_2612.method_2(var1, Class_0719.method_2(var1, this.field_4089)) : Class_0719.method_4(var1);
   }

   public double method_2(BlockPos var1, LivingEntity var2, Box var3, boolean var4) {
      return (double)Class_1323.method_2(
         Vec3d.ofCenter(var1, Double.longBitsToDouble(4607182418800017408L)),
         var2,
         var3,
         Double.longBitsToDouble(4618441417868443648L),
         true,
         this.method_1141() ? speedmine.method_1118() : null,
         var4 ? var1.toImmutable() : null
      );
   }

   public Class_0398 method_4(BlockPos var1, boolean var2) {
      Class_0398 var3 = null;

      for (AbstractClientPlayerEntity var5 : (Iterable<AbstractClientPlayerEntity>)(Iterable<?>)(new ArrayList(field_4219.world.getPlayers()))) {
         if (!this.method_78(var5) && (!((double)var1.getY() > var5.getY() + Double.longBitsToDouble(4609434218613702656L)) || var2)) {
            Box var6 = this.method_35(var5);
            double var7 = Class_1029.PLACE.method_34(var5);
            double var9 = this.method_2(var1, var5, var6, true);
            Class_0247 var11 = Class_0247.method_2(var5, var9);
            if ((!this.field_4107.getValue() || Class_0382.method_29(var5))
               && (
                  !(Class_0719.method_2(var6).distanceTo(Vec3d.ofBottomCenter(var1)) > (double)this.field_4104.getValue().floatValue())
                     || var11.field_696
                     || this.field_4141
               )) {
               if (Hub.field_2603.method_30(var5) && var5 != field_4219.player) {
                  if (this.field_4105.getValue() && var9 > (double)Class_0396.method_2((net.minecraft.entity.Entity)var5)) {
                     return null;
                  }
               } else if ((!(var7 > var9) && !(var9 < Class_0245.field_688) || var11.field_696) && (var3 == null || var9 > var3.method_445())) {
                  var3 = new Class_0398(var5, var9, Class_1163.NONE, var11);
               }
            }
         }
      }

      return var3;
   }

   public void method_1134() {
      if (this.method_78(this.field_2290)) {
         this.field_2290 = null;
      }
   }

   public boolean method_78(LivingEntity var1) {
      if (var1 == null) {
         return true;
      } else if (this.field_4106.getValue()) {
         return var1 != field_4219.player;
      } else {
         return Hub.field_2603.method_30((PlayerEntity)var1)
            ? true
            : !var1.isAlive()
               || var1.isDead()
               || !(var1.distanceTo(field_4219.player) <= this.field_4103.getValue())
               || ((Class_0705)var1).isServerSideDead()
               || field_4219.player == var1
               || var1.isSpectator()
               || ((PlayerEntity)var1).getAbilities().creativeMode;
      }
   }

   public boolean method_1135() {
      return this.field_4113.getValue() == Class_0285.NORMAL && this.field_4115.getValue();
   }

   public boolean method_1136() {
      if (this.field_4137 != null && this.field_2290 != null) {
         if (!Class_1035.method_2(this.field_4137, this.field_4058.getValue(), true, false, true, this.field_4062.getValue(), this.field_4120.getValue())) {
            return true;
         }

         List var1 = this.field_4119.getValue().method_38(this.field_4137);
         boolean var2 = Class_1225.method_2(var1);
         double var3 = field_4219.player.getEyePos().distanceTo(this.field_4137.toCenterPos());
         if (var3 > (double)this.field_4052.getValue().floatValue()) {
            return true;
         }

         if (!var2 && var3 > (double)this.field_4053.getValue().floatValue() && !this.field_4063.getValue()) {
            return true;
         }

         double var5 = (double)Class_1323.method_2(
            Vec3d.ofCenter(this.field_4137, Double.longBitsToDouble(4607182418800017408L)),
            this.field_2290,
            this.method_35(this.field_2290),
            Double.longBitsToDouble(4618441417868443648L),
            true,
            null,
            null
         );
         if (var5 >= Class_1029.PLACE.method_34(this.field_2290)) {
            return false;
         }
      }

      return this.field_4132.method_9(this.method_1138());
   }

   public void method_1137() {
      if (!this.method_535() && this.method_1135() && this.field_1071 != -1) {
         Class_0136.method_16(this.field_1071);
         this.field_1071 = -1;
      }
   }

   public long method_1138() {
      return (long)(Float.intBitsToFloat(1140457472) / Hub.field_2602.method_990());
   }

   public float method_1139() {
      return !this.field_4109.getValue() ? Float.intBitsToFloat(-1082130432) : this.field_4111.getValue();
   }

   public boolean method_107(PlayerEntity var1) {
      if (this.method_1140()) {
         return false;
      } else {
         PlayerListEntry var2 = field_4219.player.networkHandler.getPlayerListEntry(var1.getGameProfile().getId());
         return var2 == null
            ? false
            : (float)(System.currentTimeMillis() - ((Class_0822)var2).mio$getJoinTime()) <= this.method_1139() * Float.intBitsToFloat(1148846080);
      }
   }

   public boolean method_1140() {
      return !this.field_4110.getValue() ? false : (float)field_4219.player.age / Float.intBitsToFloat(1101004800) < this.method_1139();
   }

   public boolean method_1141() {
      float var1 = Float.intBitsToFloat(1075838976);
      boolean var2 = (double)speedmine.method_1119() + speedmine.method_1114() * (double)var1 >= (double)speedmine.field_3988.getValue().floatValue()
         && speedmine.method_1118() != null
         && Class_1225.method_3(speedmine.method_1118());
      return this.field_4060.getValue() && (var2 || !speedmine.method_4(100L));
   }

   public boolean method_1142() {
      return this.field_4078.getValue();
   }

   public void method_1143() {
      this.field_4145++;
   }

   public Class_1275 method_2(Throwable var1, String var2) {
      return null;
   }

   public boolean method_1144() {
      boolean var1 = field_4219.player.isHolding(var0 -> Class_0358.method_29(var0.getItem()));
      if (!var1 && this.field_4121.method_9(50L)) {
         return false;
      } else {
         if (var1) {
            this.field_4121.reset();
         }

         if (this.field_3789 != null && !this.field_3789.isDone()) {
            this.method_463();
         }

         return true;
      }
   }

   public void method_463() {
      CompletableFuture var1 = this.field_3789;
      if (var1 != null && !var1.isDone() && !var1.isCancelled()) {
         var1.complete(null);
      }
   }
}
