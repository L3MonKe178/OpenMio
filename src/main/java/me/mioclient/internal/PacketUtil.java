package me.mioclient.internal;

import io.netty.buffer.Unpooled;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.mixin.ducks.DuckClientWorld;
import net.minecraft.client.network.PendingUpdateManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.Full;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.LookAndOnGround;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.PositionAndOnGround;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PacketUtil implements MioAPI {
   public PacketUtil() {
      super();
   }

   public static void method_2(Hand var0, BlockHitResult var1) {
      method_2(new PlayerInteractBlockC2SPacket(var0, var1, method_1101()));
   }

   public static void method_2(Hand var0) {
      method_2(var0, field_4219.player.getYaw(), field_4219.player.getPitch());
   }

   public static void method_2(Hand var0, float var1, float var2) {
      method_2(new PlayerInteractItemC2SPacket(var0, method_1101(), var1, var2));
   }

   public static void method_9(Hand var0) {
      method_2(new HandSwingC2SPacket(var0));
   }

   public static void method_2(float var0, float var1, boolean var2) {
      method_2(new LookAndOnGround(var0, var1, var2));
   }

   public static void method_2(float[] var0, boolean var1) {
      if (var0 != null && var0.length >= 2) {
         method_2(var0[0], var0[1], var1);
      }
   }

   public static void method_2(double var0, double var2, double var4, boolean var6) {
      method_2(new PositionAndOnGround(var0, var2, var4, var6));
   }

   public static void method_2(double var0, double var2, double var4, float var6, float var7, boolean var8) {
      method_2(new Full(var0, var2, var4, var6, var7, var8));
   }

   public static void method_36(int var0) {
      Entity var1 = field_4219.world.getEntityById(var0);
      if (var1 != null) {
         PlayerInteractEntityC2SPacket var2 = PlayerInteractEntityC2SPacket.attack(var1, field_4219.player.isSneaking());
         method_2(var2);
      }
   }

   public static void method_2(Action var0, BlockPos var1, Direction var2) {
      method_2(new PlayerActionC2SPacket(var0, var1, var2, method_1101()));
   }

   public static void method_2(Entity var0, Mode var1) {
      method_2(var0, var1, 0);
   }

   public static void method_2(Entity var0, Mode var1, int var2) {
      method_2(new ClientCommandC2SPacket(var0, var1, var2));
   }

   public static void method_1099() {
      method_2(field_4219.player, Mode.START_FALL_FLYING, 0);
   }

   public static void method_1100() {
      method_2(new PlayerActionC2SPacket(Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
   }

   public static void method_14(int var0) {
      method_9(var0, false);
   }

   public static void method_9(int var0, boolean var1) {
      if (var1 || Hub.field_2611.method_843() != var0) {
         method_2(new UpdateSelectedSlotC2SPacket(var0));
      }
   }

   public static void method_2(Packet<?> var0) {
      field_4219.player.networkHandler.sendPacket(var0);
   }

   public static int method_1101() {
      PendingUpdateManager var0 = ((DuckClientWorld)field_4219.world).getPendingUpdateManager().incrementSequence();
      if (var0 != null) {
         var0.close();
      }

      return var0.getSequence();
   }

   public static void method_9(Packet<?> var0) {
      if (var0 != null) {
         ClientConnection var1 = field_4219.player.networkHandler.getConnection();
         if (var1.isOpen()) {
            if (var1.channel.eventLoop().inEventLoop()) {
               var1.channel.writeAndFlush(var0);
            } else {
               var1.channel.eventLoop().execute(() -> var1.channel.writeAndFlush(var0));
            }
         }
      }
   }
}
