package me.mioclient.internal;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_1176;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_15;
import me.mioclient.event.Event_22;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket.Entry;

public final class Class_0160 implements MioAPI {
   public final Map<String, Integer> field_472 = new ConcurrentHashMap<>();
   public final Map<String, Long> field_473 = new ConcurrentHashMap<>();

   public Class_0160() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (!this.method_535()) {
         if (var1.method_127() instanceof EntityStatusS2CPacket var2 && var2.getStatus() == 35 && var2.getEntity(field_4219.world) instanceof PlayerEntity var8
            )
          {
            String var13 = var8.getGameProfile().getName();
            this.field_472.compute(var13, (var0, var1x) -> var1x == null ? 1 : var1x + 1);
            field_4220.method_36(new Event_15(var8, this.field_472.get(var13), Class_1176.TOTEM_POP));
         }

         if (var1.method_127() instanceof PlayerListS2CPacket var6) {
            for (Entry var14 : var6.getPlayerAdditionEntries()) {
               this.field_473.remove(var14.profile().getName());
            }
         }

         if (var1.method_127() instanceof PlayerRemoveS2CPacket var7) {
            for (UUID var15 : var7.profileIds()) {
               PlayerListEntry var5 = field_4219.player.networkHandler.getPlayerListEntry(var15);
               if (var5 != null) {
                  this.field_473.put(var5.getProfile().getName(), System.currentTimeMillis());
               }
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      for (PlayerEntity var3 : field_4219.world.getPlayers()) {
         if (var3 != null && !(Class_0396.method_2((net.minecraft.entity.Entity)var3) > 0.0F)) {
            String var4 = var3.getName().getString();
            if (this.field_472.containsKey(var4)) {
               int var5 = this.field_472.get(var4);
               this.field_472.remove(var4);
               field_4220.method_36(new Event_15(var3, var5, Class_1176.DEATH));
            }
         }
      }

      String var6 = null;

      for (java.util.Map.Entry var8 : this.field_473.entrySet()) {
         if ((Long)var8.getValue() + 120000L < System.currentTimeMillis()) {
            var6 = (String)var8.getKey();
            this.field_472.remove(var8.getKey());
         }
      }

      if (var6 != null) {
         this.field_473.remove(var6);
      }
   }

   @Subscribe
   public void method_9(Event_22 var1) {
      if (Hub.field_2602.method_991() == null || var1.method_106().equals(Hub.field_2602.method_991().address)) {
         this.field_472.clear();
      }
   }

   public int method_39(PlayerEntity var1) {
      return this.method_194(var1.getGameProfile().getName());
   }

   public int method_194(String var1) {
      return this.field_472.getOrDefault(var1, 0);
   }
}
