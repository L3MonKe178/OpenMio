package me.mioclient.internal;

import java.util.ArrayDeque;
import java.util.Queue;
import me.mioclient.Hub;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.abstract_.AbstractModule_6;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CommonPongC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.screen.slot.SlotActionType;

public class Class_0517 extends Class_0716 {
   public static final AbstractModule_6 field_1637 = Hub.field_2595.method_78(AbstractModule_6.class);
   public final Queue<Packet<?>> field_1638 = new ArrayDeque<>();
   public int field_1639 = 0;
   public boolean field_1640;

   public Class_0517(ElytraFlyModule var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      field_4219.options.jumpKey.setPressed(false);
      field_4219.player.setSprinting(false);
      this.field_1639 = 0;
      this.method_159();
   }

   @Override
   public void method_2(Event_1 var1) {
      if (!this.field_2275.method_1180() && !this.method_557()) {
         if (field_4219.player.isFallFlying()) {
            field_4219.player.horizontalSpeed = 0.0F;
         }

         if (Hub.field_2615.method_1162() >= 40) {
            this.method_159();
         }

         if (!Class_0382.method_29(field_4219.player) && !Class_0382.method_5(field_4219.player)) {
            if (this.field_2275.field_4384.getValue()) {
               this.field_1639--;
               if (this.field_1639 == 0) {
                  this.method_159();
               }
            } else if (this.field_1639 != 0) {
               this.field_1639 = 0;
               this.method_159();
            }
         } else {
            this.method_159();
         }
      }
   }

   @Override
   public void method_2(Event_9 var1) {
   }

   @Override
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof DeathMessageS2CPacket || var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.method_159();
      }
   }

   @Override
   public void method_2(Event_10 var1) {
      if (!this.method_557()) {
         if (var1.method_127() instanceof DuckPlayerMoveC2SPacket var2
            && this.method_558()
            && this.field_2275.field_4382.getValue()
            && this.field_2275.field_4381.getValue()
            && Class_0382.method_428()
            && !this.field_2275.method_1180()) {
            var2.setPitch(this.field_2275.field_4383.getValue());
         }

         if (this.field_2275.field_4384.getValue() && field_4219.player.isFallFlying() && this.method_558() && !this.field_2275.method_1180()) {
            if (!(var1.method_127() instanceof ClientCommandC2SPacket) && !(var1.method_127() instanceof CommonPongC2SPacket)) {
               this.field_1638.add(var1.method_127());
               var1.method_463();
            }
         }
      }
   }

   @Override
   public void method_9(Event_19 var1) {
      if (!this.field_2275.method_1180() && this.method_558() && !this.method_557()) {
         if (this.field_2275.field_4381.getValue() && this.field_2275.field_4382.getValue()) {
            var1.setPitch(this.field_2275.field_4383.getValue());
         }
      }
   }

   @Override
   public void method_2(Event_17 var1) {
      this.field_1640 = this.field_2275.method_1183();
      if (field_4219.player.isFallFlying() && Hub.field_2615.method_1161() > 5) {
         field_4219.player.stopFallFlying();
      } else {
         boolean var2 = this.method_558();
         if (!Class_0382.method_29(field_4219.player)
            && !Class_0382.method_5(field_4219.player)
            && !this.field_2275.method_1180()
            && !this.method_557()
            && var2) {
            if (!Class_0382.method_428() && Hub.field_2615.method_1161() < 3 && !this.field_1640) {
               field_4219.player.startFallFlying();
               Class_1261.method_1099();
            }

            if (this.field_2275.field_4385.getValue() && !this.field_1640) {
               field_4219.interactionManager.clickSlot(0, 6, 0, SlotActionType.PICKUP, field_4219.player);
               field_4219.interactionManager.clickSlot(0, 6, 0, SlotActionType.PICKUP, field_4219.player);
               Class_1261.method_1099();
            }

            if (!field_4219.player.horizontalCollision) {
               if (Class_0464.method_363()) {
                  field_4219.player.setSprinting(true);
                  if (field_4219.player.isFallFlying()) {
                     field_4219.player.setSneaking(false);
                  }
               }

               if (field_4219.player.isOnGround() && this.field_2275.field_4384.getValue()) {
                  this.field_1639 = 3;
               }
            }

            if (this.field_2275.field_4381.getValue() && !this.field_2275.field_4382.getValue()) {
               field_4219.player.setPitch(this.field_2275.field_4383.getValue());
            }
         }
      }
   }

   @Override
   public void method_9(Event_39 var1) {
      if (this.method_558() && !this.method_557()) {
         if (this.field_2275.field_4381.getValue() && !this.field_2275.method_1180()) {
            var1.setPitch(this.field_2275.field_4383.getValue());
            var1.method_463();
         }
      }
   }

   @Override
   public void method_5(Event_16 var1) {
      if (!this.method_557()) {
         if (this.method_558() && Class_0382.method_428() && !this.field_2275.method_1180()) {
            var1.method_276().jumping = true;
            var1.method_276().pressingForward = true;
            var1.method_276().movementForward = var1.method_278() ? var1.method_277() : Float.intBitsToFloat(1065353216);
            field_4219.player.setSprinting(true);
         }
      }
   }

   public void method_159() {
      if (this.method_535()) {
         this.field_1638.clear();
      } else {
         while (!this.field_1638.isEmpty()) {
            Packet var1 = this.field_1638.poll();
            if (var1 == null) {
               break;
            }

            Class_1261.method_9(var1);
         }
      }
   }

   public boolean method_557() {
      return field_1637 == null ? false : field_1637.method_639();
   }

   public boolean method_558() {
      return this.field_2275.method_558() || this.field_1640;
   }
}
