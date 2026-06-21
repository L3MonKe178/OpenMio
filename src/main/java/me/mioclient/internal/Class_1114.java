package me.mioclient.internal;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_22;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import me.mioclient.module.exploit.FastLatencyModule;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public final class Class_1114 implements Class_1309 {
   public static final FastLatencyModule field_3440 = Hub.field_2595.method_78(FastLatencyModule.class);
   public static final Pattern field_3441 = Pattern.compile("\\d+ ping");
   public final ArrayDeque<Float> field_3442 = new ArrayDeque<>(20);
   public final Class_0242 field_3443 = new Class_0242();
   public final Class_0242 field_3444 = new Class_0242();
   public float field_3445;
   public float field_3446;
   public long field_3447;
   public long field_677;
   public int field_3448;
   public boolean field_3449;
   public boolean field_3450;
   public ServerInfo field_3451;
   public int field_3452;
   public boolean field_3453;

   public Class_1114() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      this.field_3447 = System.currentTimeMillis();
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket var2) {
         this.field_3448 = var2.getTeleportId();
         if (this.field_3444.method_9(50L)) {
            this.field_3443.reset();
         }

         this.field_3449 = field_4219.player.isSprinting();
      }

      if (var1.method_127() instanceof WorldTimeUpdateS2CPacket) {
         if (this.field_677 != 0L) {
            if (this.field_3442.size() > 20) {
               this.field_3442.poll();
            }

            this.field_3445 = Math.max(
               0.0F,
               Math.min(
                  Float.intBitsToFloat(1101004800),
                  Float.intBitsToFloat(1101004800) * (Float.intBitsToFloat(1148846080) / (float)(System.currentTimeMillis() - this.field_677))
               )
            );
            this.field_3442.add(this.field_3445);
            float var9 = 0.0F;

            for (Float var4 : this.field_3442) {
               var9 += Math.max(0.0F, Math.min(Float.intBitsToFloat(1101004800), var4));
            }

            if (this.field_3442.size() > 0) {
               var9 /= (float)this.field_3442.size();
            }

            this.field_3446 = var9;
         }

         this.field_677 = System.currentTimeMillis();
      }

      if (var1.method_127() instanceof BundleS2CPacket var10) {
         for (Packet var17 : var10.getPackets()) {
            field_4220.method_36(new Event_4(var17));
         }
      }

      if (var1.method_127() instanceof PlayerListHeaderS2CPacket var11) {
         ServerInfo var16 = field_4219.player.networkHandler.getServerInfo();
         if (var16 == null) {
            return;
         }

         String var18 = var11.footer().getString();
         Matcher var5 = field_3441.matcher(var18);
         if (var5.find()) {
            String var6 = var5.group();

            try {
               var6 = var6.substring(0, var6.length() - 5);
               this.field_3452 = Integer.parseInt(var6);
            } catch (Throwable var8) {
            }
         }
      }
   }

   @Subscribe(
      method_800 = 200
   )
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof ClientCommandC2SPacket var2) {
         if (var2.getMode() == Mode.START_SPRINTING) {
            if (this.field_3449) {
               var1.method_463();
            }

            this.field_3449 = true;
         }

         if (var2.getMode() == Mode.STOP_SPRINTING) {
            if (!this.field_3449) {
               var1.method_463();
            }

            this.field_3449 = false;
         }

         if (var2.getMode() == Mode.PRESS_SHIFT_KEY) {
            this.field_3450 = true;
         }

         if (var2.getMode() == Mode.RELEASE_SHIFT_KEY) {
            this.field_3450 = false;
         }
      }

      if (var1.method_127() instanceof TeleportConfirmC2SPacket) {
         this.field_3444.reset();
      }
   }

   @Subscribe
   public void method_9(Event_22 var1) {
      this.field_3453 = false;
      this.field_3452 = 0;
   }

   public int method_983() {
      if (field_4219.player == null) {
         return 0;
      } else if (field_3440.isToggled()) {
         return field_3440.method_983();
      } else {
         PlayerListEntry var1 = field_4219.player.networkHandler.getPlayerListEntry(field_4219.player.getGameProfile().getId());
         int var2 = var1 == null ? 0 : var1.getLatency();
         return var2 == 0 ? this.field_3452 : var2;
      }
   }

   public Class_0242 method_984() {
      return this.field_3443;
   }

   public Class_0242 method_985() {
      return this.field_3444;
   }

   public int method_986() {
      return this.field_3448;
   }

   public long method_987() {
      return this.field_3447;
   }

   public float method_988() {
      return this.field_3445;
   }

   public float method_989() {
      return this.field_3446;
   }

   public float method_990() {
      return this.field_3446 / Float.intBitsToFloat(1101004800);
   }

   public ServerInfo method_991() {
      return this.field_3451;
   }

   public void method_2(ServerInfo var1) {
      this.field_3451 = var1;
   }

   public boolean method_992() {
      return this.field_3449;
   }

   public boolean method_993() {
      return this.field_3450;
   }
}
