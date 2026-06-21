package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0323;
import me.mioclient.enum_.Class_0845;
import me.mioclient.enum_.Class_1229;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_40;
import me.mioclient.event.Event_55;
import me.mioclient.event.Event_7;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0094;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0321;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0517;
import me.mioclient.internal.Class_0613;
import me.mioclient.internal.Class_0716;
import me.mioclient.internal.Class_0933;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.PacketUtil;
import me.mioclient.internal.Class_1295;
import me.mioclient.mixin.ducks.DuckMinecraftClient;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.abstract_.AbstractModule_6;
import me.mioclient.module.misc.MiddleClickModule;
import me.mioclient.record.Class_0983;
import me.mioclient.setting.Setting;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public class ElytraFlyModule extends Module {
   public static final MiddleClickModule field_4345 = Hub.field_2595.method_78(MiddleClickModule.class);
   public static final AbstractModule_6 field_4346 = Hub.field_2595.method_78(AbstractModule_6.class);
   public static final NoSlowModule field_4347 = Hub.field_2595.method_78(NoSlowModule.class);
   public static final AbstractModule_28 field_4348 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static boolean field_885 = false;
   public  Setting<Class_1229> field_4349;
   public  Setting<Class_0323> field_4350;
   public  Setting<Boolean> field_4351;
   public  Setting<Boolean> field_4352;
   public  Setting<Boolean> field_4353;
   public  Setting<Float> field_4354;
   public  Setting<Integer> field_4355;
   public  Setting<Float> field_4356;
   public  Setting<Boolean> field_4357;
   public  Setting<Boolean> field_4358;
   public  Setting<Float> field_4359;
   public  Setting<Boolean> field_4360;
   public  Setting<Integer> field_4361;
   public  Setting<Integer> field_4362;
   public  Setting<Float> field_4363;
   public  Setting<Float> field_4364;
   public  Setting<Class_0845> field_4365;
   public  Setting<Float> field_4366;
   public  Setting<Float> field_4367;
   public  Setting<Boolean> field_4368;
   public  Setting<Float> field_4369;
   public  Setting<Boolean> field_4370;
   public  Setting<Float> field_4371;
   public  Setting<Boolean> field_4372;
   public  Setting<Boolean> field_4373;
   public  Setting<Boolean> field_4374;
   public  Setting<Float> field_4375;
   public Setting<Float> field_4376;
   public Setting<Float> field_4377;
   public Setting<Float> field_4378;
   public  Setting<Float> field_4379;
   public Setting<Integer> field_4380;
   public Setting<Boolean> field_4381;
   public Setting<Boolean> field_4382;
   public  Setting<Float> field_4383;
   public Setting<Boolean> field_4384;
   public  Setting<Boolean> field_4385;
   public Setting<Boolean> field_4386;
   public  Setting<Boolean> field_4387;
   public final Class_1295<Class_1229, Class_0716> field_4388;
   public final Timer field_4389;
   public final Timer field_4390;
   public final Timer field_4391;
   public final Timer field_4392;
   public final Timer field_4393;
   public boolean field_4394;
   public boolean field_4395;
   public boolean field_4396;

   public ElytraFlyModule() {
      super("ElytraFly", "Turns you into a block game boeing.", Category.MOVEMENT, "elytraflight");
      Settings.initialize(this);
      this.field_4388 = new Class_1295<>(this.field_4349);
      this.field_4389 = new Timer();
      this.field_4390 = new Timer();
      this.field_4391 = new Timer();
      this.field_4392 = new Timer();
      this.field_4393 = new Timer();
      this.field_4359.method_31("LavaSpeed");
      this.field_4373.method_31("VerticalPacket");
      this.field_4379.method_31("StrictSpeed");
      this.field_4383.method_31("BouncePitch");
      this.field_4354.method_2("GrimV3", SettingMode.MAX);
      this.field_4356.method_2("Unlimited", SettingMode.MAX);
      this.field_4355.method_5(var1 -> !this.field_4354.method_105());
      this.field_4385.method_5(var1 -> !this.field_4387.getValue());
      this.field_4388.method_2(Class_1229.BOOST, new Class_0613(this));
      this.field_4388.method_2(Class_1229.CONTROL, new Class_0933(this));
      this.field_4388.method_2(Class_1229.PACKET, new Class_0321(this));
      this.field_4388.method_2(Class_1229.STRICT, new Class_0094(this));
      this.field_4388.method_2(Class_1229.BOUNCE, new Class_0517(this));
   }

   @Override
   public void onEnable() {
      this.field_4392.setTime(-1L);
      this.field_4394 = true;
      if (!this.method_535()) {
         this.field_4388.method_1156().onEnable();
      }
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         this.field_4388.method_1156().onDisable();
      }

      if (!this.method_535() && this.field_4387.getValue() && this.field_4387.method_176() && !field_4219.player.isSneaking()) {
         PacketUtil.method_2(field_4219.player, Mode.PRESS_SHIFT_KEY, 0);
         PacketUtil.method_2(field_4219.player, Mode.RELEASE_SHIFT_KEY, 0);
      }

      this.field_4389.reset();
   }

   @Override
   public String getInfo() {
      try {
         return FontRenderer.method_3((this.field_4349.getValue() != null ? this.field_4349.getValue().getName() : ""));
      } catch (Exception var2) {
         return null;
      }
   }

   @Subscribe
   public void method_78(Event_7 var1) {
      if (Class_0382.method_428() && this.field_4388.method_1156() instanceof Class_0613 var2 && this.field_4360.getValue()) {
         Class_0983 var5 = Hub.field_2598.method_510();
         float var4 = var5 != null ? var5.method_888()[0] : var1.method_500();
         Hub.field_2598.method_2(new float[]{var4, var2.method_501()}, 999, true);
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!field_4219.player.isFallFlying()) {
         this.field_4394 = true;
      }

      this.field_4388.method_1156().method_2(var1);
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerActionC2SPacket var2 && var2.getAction() == Action.RELEASE_USE_ITEM) {
         this.field_4391.reset();
      }

      this.field_4388.method_1156().method_2(var1);
   }

   @Subscribe
   public void method_9(Event_55 var1) {
      if (!this.field_4396) {
         if (field_4219.player.getStackInHand(var1.method_12()).isOf(Items.FIREWORK_ROCKET)) {
            this.field_4395 = true;
            var1.method_463();
         }
      }
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      int var2 = PlayerUtil.method_5(Items.ELYTRA);
      boolean var3 = var2 == -1;
      if (var2 == -1) {
         var2 = PlayerUtil.method_9(Items.ELYTRA);
      }

      boolean var4 = this.method_1183();
      if (this.field_4391.method_9(200L)) {
         if (!field_4348.method_1052() || !var3) {
            if (field_4346 == null || !field_4346.method_639()) {
               if (var4) {
                  if (Class_0382.method_4(field_4219.player)) {
                     field_4219.interactionManager.clickSlot(0, 6, 0, SlotActionType.PICKUP, field_4219.player);
                     field_4219.interactionManager.clickSlot(0, 6, 0, SlotActionType.PICKUP, field_4219.player);
                  } else if (var2 != -1) {
                     int var5 = var2;
                     if (var3) {
                        var2 = 0;
                        field_4219.interactionManager.clickSlot(0, var5, 0, SlotActionType.SWAP, field_4219.player);
                     }

                     field_4219.interactionManager.clickSlot(0, 6, var2, SlotActionType.SWAP, field_4219.player);
                     this.method_1177();
                     if (this.field_4395 && this.field_4389.method_5(150L)) {
                        this.field_4396 = true;
                        if (field_4345.isToggled()) {
                           field_4345.method_298();
                        } else {
                           ((DuckMinecraftClient)field_4219).interact();
                        }

                        this.field_4396 = false;
                        this.field_4395 = false;
                        this.field_4391.reset();
                     }

                     field_4219.interactionManager.clickSlot(0, 6, var2, SlotActionType.SWAP, field_4219.player);
                     if (var3) {
                        field_4219.interactionManager.clickSlot(0, var5, 0, SlotActionType.SWAP, field_4219.player);
                     }
                  }
               }

               this.field_4388.method_1156().method_2(var1);
            }
         }
      }
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof EntitySpawnS2CPacket var2 && var2.getEntityType() == EntityType.FIREWORK_ROCKET) {
         Vec3d var4 = new Vec3d(var2.getX(), var2.getY(), var2.getZ());
         if (var4.squaredDistanceTo(field_4219.player.getPos()) <= Double.longBitsToDouble(4630263366890291200L)) {
            this.field_4391.reset();
         }
      }

      this.field_4388.method_1156().method_5(var1);
   }

   @Subscribe
   public void method_9(Event_9 var1) {
      this.field_4388.method_1156().method_2(var1);
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      this.field_4388.method_1156().method_9(var1);
   }

   @Subscribe
   public void method_9(Event_39 var1) {
      this.field_4388.method_1156().method_9(var1);
   }

   @Subscribe
   public void method_5(Event_16 var1) {
      this.field_4388.method_1156().method_5(var1);
   }

   @Subscribe
   public void method_2(Event_40 var1) {
      if (this.method_1183()
         && var1.method_863() != null
         && var1.method_863().getId() != null
         && var1.method_863().getId().toString().contains("item.armor.equip")) {
         var1.method_463();
      }
   }

   public void method_1177() {
      if (!this.field_4392.method_9(500L) || this.field_4387.method_176()) {
         PacketUtil.method_1099();
         field_4219.player.startFallFlying();
      }
   }

   public void method_1099() {
      if (this.method_558() && !field_4219.player.isFallFlying() && !field_4219.player.isOnGround() && this.field_4350.getValue() != Class_0323.NONE) {
         if (this.field_4350.getValue() == Class_0323.STRICT) {
            Hub.field_2596.method_2(this, Float.intBitsToFloat(1036831949));
         }

         long var1 = 100L;
         if (this.field_4349.getValue() == Class_1229.STRICT) {
            var1 = (long)(this.field_4380.getValue() != null ? this.field_4380.getValue().intValue() : 0);
         }

         if (this.field_4390.method_9(var1)) {
            field_4219.player.startFallFlying();
            PacketUtil.method_1099();
            this.field_4390.reset();
         }
      } else {
         Hub.field_2596.method_38(this);
      }
   }

   public boolean method_558() {
      return Class_0382.method_4(field_4219.player);
   }

   public boolean method_1178() {
      if (this.method_535() || field_4219.player.input == null) {
         return false;
      } else {
         return this.field_4373.getValue() && field_4219.player.input.jumping
            ? false
            : this.isToggled() && !field_4219.player.isOnGround() && this.field_4349.getValue() == Class_1229.PACKET && this.method_558();
      }
   }

   public boolean method_1179() {
      if (this.field_4387.getValue()) {
         return false;
      } else if (Hub.field_2630.method_261()) {
         return false;
      } else if (field_4346 != null && field_4346.method_639()) {
         return false;
      } else if (this.method_41()) {
         field_4219.player.startFallFlying();
         return true;
      } else {
         return false;
      }
   }

   public boolean method_41() {
      if (Hub.field_2630.method_261()) {
         return false;
      } else {
         return field_4346 != null && field_4346.method_639()
            ? false
            : !field_4219.player.getAbilities().flying
               && !field_4219.player.hasVehicle()
               && !field_4219.player.isClimbing()
               && this.method_558()
               && !field_4219.player.isTouchingWater()
               && !field_4219.player.hasStatusEffect(StatusEffects.LEVITATION)
               && field_4219.player.input.jumping;
      }
   }

   public boolean method_1180() {
      return field_4348.method_1052() ? false : field_4219.player.isUsingItem() && (!field_4347.isToggled() || !field_4347.field_1687.getValue());
   }

   public boolean method_1181() {
      return this.method_1182() && field_4219.player.getPitch() > 0.0F;
   }

   public boolean method_1182() {
      return this.method_535()
         ? false
         : (field_4219.player.input.jumping || field_4219.player.input.sneaking)
            && this.field_4365.getValue() == Class_0845.MANUAL
            && field_4219.player.isFallFlying()
            && this.method_558();
   }

   public boolean method_428() {
      if (field_885) {
         return field_4219.player.isFallFlying();
      } else {
         field_885 = true;
         boolean var1 = field_4219.player.isFallFlying();
         field_885 = false;
         return var1;
      }
   }

   public boolean method_1183() {
      if (Hub.field_2630.method_261()) {
         return false;
      } else if (this.method_535()) {
         return false;
      } else if (field_885) {
         return false;
      } else {
         int var1 = PlayerUtil.method_5(Items.ELYTRA);
         if (var1 == -1) {
            var1 = PlayerUtil.method_9(Items.ELYTRA);
         }

         boolean var2 = var1 != -1;
         if (var2) {
            this.field_4393.reset();
         }

         var2 |= !this.field_4393.method_9(150L);
         if (Class_0382.method_4(field_4219.player)) {
            var2 = true;
         }

         if (!var2) {
            return false;
         } else {
            boolean var3 = this.field_4387.getValue() && this.field_4387.method_176();
            return this.isToggled() && var3;
         }
      }
   }
}
