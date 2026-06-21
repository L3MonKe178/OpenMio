package me.mioclient.module.abstract_;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0847;
import me.mioclient.enum_.SettingMode;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0621;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.PacketUtil;
import me.mioclient.internal.TextBuilder;
import me.mioclient.internal.Class_1334;
import me.mioclient.mixin.ducks.DuckAbstractBlock;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.combat.AutoArmorModule;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import nick.Settings;

public final class AbstractModule_6 extends Module {
   public static final AutoArmorModule field_3895 = Hub.field_2595.method_78(AutoArmorModule.class);
   public static final ElytraFlyModule field_3896 = Hub.field_2595.method_78(ElytraFlyModule.class);
   public final Class_0621 field_3897 = new Class_0621(3);
   public Setting<Boolean> field_3898;
   public Setting<Boolean> field_3899;
   public Setting<Integer> field_3900;
   public Setting<Integer> field_3901;
   public final Timer field_3902;
   public float field_1760;
   public BlockPos field_3534;
   public boolean field_3903;
   public boolean field_3904;
   public boolean field_3905;
   public int field_3906;
   public Class_0847 field_3907;

   public AbstractModule_6() {
      super(
         "ObstaclePasser",
         new TextBuilder()
            .method_2(String.valueOf(Formatting.YELLOW))
            .method_9("Passes obstacles in your way. \n\u0001Requires either ElytraFly or Speed enabled."),
         Category.MOVEMENT
      );
      Settings.initialize(this);
      this.field_3902 = new Timer();
      this.field_3900.method_2("~", SettingMode.MIN);
      this.field_3898.method_9(() -> this.field_3907 = null);
   }

   @Override
   public void onEnable() {
      if (this.method_535()) {
         this.method_68();
      } else {
         this.field_3903 = false;
         this.field_3907 = null;
         this.field_3906 = 0;
         this.field_1760 = (float)Math.round(field_4219.player.getYaw());
         this.field_3534 = field_4219.player.getBlockPos();
         this.field_3897.method_507();
      }
   }

   @Override
   public void onDisable() {
      Hub.field_2630.method_262();
      if (this.field_3903 && field_3895.isToggled()) {
         field_3895.field_2927.method_78(true);
      }

      this.field_3904 = false;
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!Hub.field_2630.method_261()) {
         field_4219.player.setYaw(this.field_1760);
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_9(Event_17 var1) {
      if (this.method_319()) {
         this.field_3902.reset();
      }

      this.method_1086();
      if (this.field_3907 == null) {
         this.field_3907 = Class_0847.method_294(this.field_1760);
      }

      if (this.field_3906 > 0) {
         this.field_3906--;
      }

      this.field_3897.method_142();
      this.method_1084();
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_3897.method_640();
      }
   }

   @Subscribe
   public void method_29(Event_16 var1) {
      if (!this.field_3899.getValue() || !this.field_3902.method_9(750L)) {
         if (!this.method_1088()) {
            if (this.method_1087() || !field_4219.player.isFallFlying() && field_3896.isToggled()) {
               var1.method_276().jumping = true;
            }

            var1.method_276().pressingForward = true;
            var1.method_276().movementForward = var1.method_278() ? var1.method_277() : Float.intBitsToFloat(1065353216);
         }
      }
   }

   public void method_1084() {
      if (Hub.field_2630.method_261()) {
         BlockPos var1 = Hub.field_2630.method_265();
         if (var1 != null && Class_1334.method_5(field_4219.player.getPos(), var1.toBottomCenterPos()) <= Float.intBitsToFloat(1065353216)) {
            Hub.field_2630.method_262();
            this.field_3906 = 10;
            if (field_3896.isToggled()) {
               this.field_3905 = true;
            }
         }
      }

      if (this.method_1085() && !Hub.field_2630.method_261()) {
         this.field_3897.method_507();
         int var8 = this.field_3900.getValue() == -1 ? this.field_3534.getY() : this.field_3900.getValue();
         if (this.field_3907 != null && this.field_3898.getValue()) {
            BlockPos var9 = null;

            for (int var10 = 5; var10 < this.field_3901.getValue(); var10++) {
               var9 = this.field_3907.field_2740.apply(var10).withY(var8);
               if (((DuckAbstractBlock)Class_1225.method_33(var9.down())).isCollidable()) {
                  break;
               }
            }

            Hub.field_2630.method_154(var9);
         } else {
            Vec3d var2 = this.field_3534.toBottomCenterPos();
            Vec3d var3 = this.method_80(this.field_1760);
            float var4 = Class_1334.method_5(var2, field_4219.player.getPos());
            BlockPos var5 = BlockPos.ofFloored(var2.add(var3.multiply((double)var4)));
            BlockPos var6 = null;

            for (int var7 = 5; var7 < this.field_3901.getValue(); var7++) {
               var6 = var5.add(BlockPos.ofFloored(var3.multiply((double)var7)));
               if (((DuckAbstractBlock)Class_1225.method_33(var6.down())).isCollidable()) {
                  break;
               }
            }

            Hub.field_2630.method_154(var6.withY(var8));
         }
      }
   }

   public boolean method_1085() {
      return this.field_3906 > 0
         ? false
         : field_4219.player.horizontalCollision
            || this.field_3897.method_641()
            || !Class_0382.method_428() && Hub.field_2615.method_1161() > 3 && field_4219.player.isOnGround() && field_3896.isToggled();
   }

   public void method_1086() {
      if (Hub.field_2630.method_261()) {
         if (!this.field_3903) {
            this.field_3903 = true;
            PacketUtil.method_2(field_4219.player, Mode.PRESS_SHIFT_KEY, 0);
            PacketUtil.method_2(field_4219.player, Mode.RELEASE_SHIFT_KEY, 0);
         }
      } else if (this.field_3903) {
         this.field_3903 = false;
      }
   }

   public boolean method_558() {
      return field_4219.player.getInventory().getArmorStack(EquipmentSlot.CHEST.getEntitySlotId()).isOf(Items.ELYTRA);
   }

   public boolean method_319() {
      ClientChunkManager var1 = field_4219.world.getChunkManager();
      return ChunkPos.stream(field_4219.player.getChunkPos(), 2).allMatch(var1x -> var1.isChunkLoaded(var1x.x, var1x.z)) || !this.field_3899.getValue();
   }

   public boolean method_1087() {
      return this.field_3905 && field_3896.isToggled();
   }

   public boolean method_1088() {
      return Hub.field_2630.method_261();
   }

   public boolean method_639() {
      return !this.isToggled() ? false : this.method_1088();
   }

   public Vec3d method_80(float var1) {
      return field_4219.player.getRotationVector(0.0F, var1);
   }
}
