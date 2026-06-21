package me.mioclient.module.combat;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0034;
import me.mioclient.enum_.Class_0448;
import me.mioclient.event.Event_10;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0030;
import me.mioclient.internal.Class_0144;
import me.mioclient.internal.Class_0382;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.PacketUtil;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.PositionAndOnGround;
import nick.Settings;

public class CriticalsModule extends Module {
   public static final AuraModule field_3405 = Hub.field_2595.method_78(AuraModule.class);
   public Setting<Class_0448> field_3406;
   public Setting<Boolean> field_3407;

   public CriticalsModule() {
      super("Criticals", "Turns your hits into critical ones.", Category.COMBAT);
      Settings.initialize(this);
      this.field_3407.method_5(var1 -> this.field_3406.getValue() != Class_0448.GRIMV3);
   }

   @Override
   public String getInfo() {
      return (this.field_3406.getValue() != null ? this.field_3406.getValue().getName() : "");
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerInteractEntityC2SPacket var2 && this.method_7(var2)) {
         Entity var4 = Class_0144.method_2(var2);
         if (var4 == null) {
            return;
         }

         if (!var1.method_464()) {
            this.method_300();
         }

         field_4219.execute(() -> field_4219.player.addCritParticles(var4));
      }
   }

   public void method_300() {
      float[] var1 = new float[]{field_4219.player.getYaw(), field_4219.player.getPitch()};
      if (field_3405 != null && field_3405.field_582 != null && field_3405.isToggled()) {
         var1 = RotationManager.method_2(RotationManager.method_14(field_3405.field_582), Hub.field_2598 != null ? Hub.field_2598.method_509() : 0F);
      }

      switch ((Class_0448)this.field_3406.getValue()) {
         case NCP:
            if (Class_0382.method_5(field_4219.player)) {
               this.method_9(Double.longBitsToDouble(4589168748072235207L), 0.0, Double.longBitsToDouble(4578359381184846234L), 0.0);
            } else {
               this.method_9(Double.longBitsToDouble(4592590756007337001L), Double.longBitsToDouble(4592590853854343945L));
            }
            break;
         case GRIM:
            PacketUtil.method_2(
               field_4219.player.getX(),
               field_4219.player.getY() - Double.longBitsToDouble(4517329193108106637L),
               field_4219.player.getZ(),
               var1[0],
               var1[1],
               false
            );
            break;
         case GRIMV3:
            float var2 = Math.clamp(
               var1[1], Float.intBitsToFloat(-1028390912) + AbstractModule_28.field_3741, Float.intBitsToFloat(1119092736) - AbstractModule_28.field_3741
            );
            PacketUtil.method_2(
               field_4219.player.getX(),
               field_4219.player.getY() + Double.longBitsToDouble(4589175226049939217L),
               field_4219.player.getZ(),
               var1[0],
               var2 + AbstractModule_28.field_3741,
               false
            );
            PacketUtil.method_2(
               field_4219.player.getX(),
               field_4219.player.getY() + Double.longBitsToDouble(4586718062093245874L),
               field_4219.player.getZ(),
               var1[0],
               var2 - AbstractModule_28.field_3741,
               false
            );
            break;
         default:
            this.method_9(Double.longBitsToDouble(4589168748072235207L), 0.0);
      }
   }

   public boolean method_976() {
      if ((this.field_3407.getValue() || this.field_3406.getValue() == Class_0448.GRIMV3) && Class_0464.method_363()) {
         return false;
      } else if (this.field_3406.getValue() == Class_0448.GRIMV3) {
         return field_4219.player.isOnGround() && Class_0030.method_43();
      } else {
         return this.field_3406.getValue() == Class_0448.GRIM && !field_4219.player.isOnGround()
            ? true
            : field_4219.player.isOnGround() && field_4219.player.verticalCollision && !field_4219.player.isInLava() && !field_4219.player.isTouchingWater();
      }
   }

   public void method_9(double... var1) {
      for (double var5 : var1) {
         PacketUtil.method_2(new PositionAndOnGround(field_4219.player.getX(), field_4219.player.getY() + var5, field_4219.player.getZ(), false));
      }
   }

   public boolean method_7(PlayerInteractEntityC2SPacket var1) {
      if (Class_0144.method_9(var1) == Class_0034.ATTACK && this.method_976()) {
         Entity var2 = Class_0144.method_2(var1);
         return (field_3405 == null || !field_3405.isToggled() || !field_3405.field_1073 || this.field_3406.getValue() == Class_0448.GRIM)
            && var2 != null
            && var2.isAlive()
            && !(var2 instanceof EndCrystalEntity);
      } else {
         return false;
      }
   }
}
