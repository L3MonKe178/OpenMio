package me.mioclient.internal;

import java.util.ArrayDeque;
import java.util.Queue;
import me.mioclient.Hub;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_1054;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;
import me.mioclient.mixin.ducks.DuckClientPlayerEntity;
import me.mioclient.mixin.ducks.DuckWorldRenderer;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.record.Class_0983;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class RotationManager implements MioAPI {
   public static AbstractModule_28 field_144 = Hub.field_2595.method_78(AbstractModule_28.class);
   public static boolean field_1538;
   public float field_1539;
   public float field_1540;
   public float field_1541;
   public float field_1542;
   public float field_1543;
   public float field_1544;
   public float field_1545;
   public float field_1546;
   public float[] field_1547;
   public int field_1548;
   public int field_1549;
   public final Queue<Packet<?>> field_800 = new ArrayDeque<>();
   public boolean field_1550;
   public boolean field_1551;
   public Class_0983 field_1552;
   public boolean field_1553;

   public RotationManager() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_1553 = false;
   }

   @Subscribe
   public void method_2(Event_17 var1) {
      if (method_513()) {
         this.method_507();
         this.method_508();
      }
   }

   @Subscribe
   public void method_2(Event_39 var1) {
      boolean var2 = field_144.field_3744.getValue() || this.field_1553;
      if (this.field_1552 != null && var2) {
         float[] var3 = this.field_1552.method_888();
         float var4 = method_7(var3);
         if (var4 != var3[0]
            && var4 != MathHelper.wrapDegrees(var3[0] - (float)Constants.field_684)
            && var4 != MathHelper.wrapDegrees(var3[0] + (float)Constants.field_684)) {
            field_4219.player.setSprinting(false);
         }

         var1.method_463();
         var1.setYaw(var4);
         var1.setPitch(var3[1]);
      }
   }

   public static float method_7(float[] var0) {
      if (field_4219.player.isFallFlying()) {
         return var0[0];
      } else {
         float var1 = (float)Constants.field_686;
         float var2 = var0[0];

         for (int var3 = 0; var3 < Constants.field_686; var3 += Constants.field_685) {
            float var4 = MathHelper.wrapDegrees(var0[0] + (float)var3);
            float var5 = MathHelper.angleBetween(var4, field_4219.player.getYaw());
            if (var5 <= var1) {
               var2 = var4;
               var1 = var5;
            }
         }

         return var2;
      }
   }

   @Subscribe
   public void method_9(Event_19 var1) {
      if (this.field_1549 > 0 && this.field_1547 != null) {
         this.method_29(this.field_1547);
         this.field_1549--;
      }

      if (var1.method_213() == PreType.POST) {
         if (!method_513()) {
            this.method_507();
         }
      } else {
         if (!method_513()) {
            this.method_508();
         }

         if (this.field_1552 != null) {
            var1.method_5(this.field_1552.method_888());
            this.field_1552 = null;
         }
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (this.field_1550 && !var1.method_464() && !this.field_1551) {
         if (var1.method_127() instanceof PlayerMoveC2SPacket) {
            var1.method_463();
            PacketUtil.method_9(var1.method_127());
            this.method_507();
         } else {
            this.field_800.add(var1.method_127());
            var1.method_463();
         }
      }
   }

   public boolean method_2(Mode var1) {
      return var1 == Mode.PRESS_SHIFT_KEY || var1 == Mode.RELEASE_SHIFT_KEY;
   }

   public void method_209(boolean var1) {
      this.field_1550 = var1;
   }

   public boolean method_506() {
      return this.field_1550;
   }

   public void method_507() {
      long var1 = 0L;

      while (!this.field_800.isEmpty()) {
         if (++var1 > 500L) {
            System.out.println("anticrash1337");
            break;
         }

         Packet var3 = this.field_800.poll();
         if (var3 != null) {
            this.field_1551 = true;
            PacketUtil.method_2(var3);
            this.field_1551 = false;
         }
      }

      this.field_800.clear();
   }

   public void method_508() {
      this.field_1550 = true;
      field_4220.method_36(new Event_7());
      this.field_1550 = false;
      if (this.field_1552 == null) {
         this.method_507();
      }
   }

   public float method_509() {
      return field_144.method_1051();
   }

   public Class_0983 method_510() {
      return this.field_1552;
   }

   public void method_2(Class_0983 var1) {
      if (this.field_1552 == null || this.field_1552.method_800() <= var1.method_800()) {
         this.field_1552 = var1;
      }
   }

   public void method_2(float[] var1, int var2) {
      this.method_2(var1, var2, false);
   }

   public void method_2(float[] var1, int var2, boolean var3) {
      if (!field_144.method_1052() || !(field_4219.currentScreen instanceof HandledScreen)) {
         if (!method_514() || var3) {
            this.method_2(new Class_0983(var1, var2));
         } else if (this.field_1550) {
            this.method_29(var1);
            PacketUtil.method_2(field_4219.player.getX(), field_4219.player.getY(), field_4219.player.getZ(), var1[0], var1[1], Hub.field_2615.method_707());
         }
      }
   }

   public void method_511() {
      this.field_1553 = true;
   }

   public void method_29(float[] var1) {
      this.method_9(var1, 0);
   }

   public void method_9(float[] var1, int var2) {
      this.field_1548 = field_4219.player.age;
      this.field_1542 = this.field_1539;
      this.field_1543 = this.field_1540;
      this.field_1544 = this.field_1541;
      this.field_1541 = this.method_4(var1[0], this.field_1544);
      this.field_1545 = this.field_1546;
      this.field_1546 = var1[0];
      this.field_1539 = var1[0];
      this.field_1540 = var1[1];
      this.field_1547 = var1;
      if (var2 > 0) {
         this.field_1549 = var2;
      }
   }

   public static float[] method_98(BlockPos var0) {
      return method_78(
         new Vec3d((double)var0.getX() + Constants.field_688, (double)var0.getY() + Constants.field_688, (double)var0.getZ() + Constants.field_688)
      );
   }

   public static float[] method_14(Entity var0) {
      return method_78(Class_0719.method_9(field_4219.player.getEyePos(), var0.getBoundingBox()));
   }

   public static float[] method_78(Vec3d var0) {
      return method_7(field_4219.player.getCameraPosVec(RenderUtil.method_776()), var0);
   }

   public static float[] method_2(Vec3d var0, Direction var1) {
      return method_7(field_4219.player.getCameraPosVec(RenderUtil.method_776()), var1 == null ? var0 : var0.offset(var1, Constants.field_688));
   }

   public static float[] method_7(Vec3d var0, Vec3d var1) {
      double var2 = var1.x - var0.x;
      double var4 = (var1.y - var0.y) * Double.longBitsToDouble(-4616189618054758400L);
      double var6 = var1.z - var0.z;
      double var8 = Math.sqrt(var2 * var2 + var6 * var6);
      return new float[]{
         (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(var6, var2)) - (double)Constants.field_685),
         (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(var4, var8)))
      };
   }

   public static float[] method_2(float[] var0, float var1) {
      if (var0 == null) {
         return null;
      } else {
         var1 = MathHelper.clamp(var1, Float.intBitsToFloat(1036831949), Float.intBitsToFloat(1065353216));
         if (var1 < Float.intBitsToFloat(1065353216)) {
            float var2 = ((DuckClientPlayerEntity)field_4219.player).lastYaw();
            float var3 = MathHelper.wrapDegrees(var0[0] - var2);
            if (Math.abs(var3) > Float.intBitsToFloat(1127481344) * var1) {
               var0[0] = var2 + var3 * (Float.intBitsToFloat(1127481344) * var1 / Math.abs(var3));
            }
         }

         return new float[]{var0[0], var0[1]};
      }
   }

   public static int method_221(float var0) {
      return MathHelper.floor((double)(var0 * Float.intBitsToFloat(1090519040) / (float)Constants.field_686) + Constants.field_688) & 7;
   }

   public static int method_512() {
      return method_222(field_4219.gameRenderer.getCamera().getYaw());
   }

   public static int method_222(float var0) {
      return MathHelper.floor((double)(var0 * Float.intBitsToFloat(1082130432) / (float)Constants.field_686) + Constants.field_688) & 3;
   }

   public static Direction method_220(int var0) {
      return switch (var0) {
         case 0 -> Direction.SOUTH;
         case 1 -> Direction.WEST;
         case 2 -> Direction.NORTH;
         case 3 -> Direction.EAST;
         default -> Direction.UP;
      };
   }

   public static boolean method_4(Box var0) {
      return ((DuckWorldRenderer)field_4219.worldRenderer).getFrustum().isVisible(var0);
   }

   public static boolean method_39(Vec3d var0) {
      return method_4(new Box(BlockPos.ofFloored(var0)));
   }

   public void method_29(float var1, float var2) {
      if (field_4219.player.age != this.field_1548) {
         this.method_29(new float[]{var1, var2});
      }
   }

   public static boolean method_513() {
      return field_144 == null ? false : field_144.field_3744.getValue();
   }

   public static boolean method_514() {
      return field_144 == null ? false : method_513() && field_144.field_3745.getValue() == Class_1054.SILENT;
   }

   public float method_515() {
      return this.field_1539;
   }

   public float method_516() {
      return this.field_1540;
   }

   public float method_517() {
      return this.field_1541;
   }

   public float method_518() {
      return this.field_1542;
   }

   public float method_519() {
      return this.field_1543;
   }

   public float method_520() {
      return this.field_1544;
   }

   public float method_521() {
      return this.field_1545;
   }

   public float method_522() {
      return this.field_1546;
   }

   public int method_523() {
      return this.field_1548;
   }

   public float method_4(float var1, float var2) {
      if (field_4219.player.hasVehicle()) {
         return var1;
      } else {
         float var3 = var2;
         double var5 = field_4219.player.getX() - field_4219.player.prevX;
         double var7 = field_4219.player.getZ() - field_4219.player.prevZ;
         if (var5 * var5 + var7 * var7 > Double.longBitsToDouble(4567911030457368576L)) {
            float var4 = (float)MathHelper.atan2(var7, var5) * Float.intBitsToFloat(1113927392) - (float)Constants.field_685;
            float var9 = MathHelper.abs(MathHelper.wrapDegrees(var1) - var4);
            if (Float.intBitsToFloat(1119748096) < var9 && var9 < Float.intBitsToFloat(1132756992)) {
               var3 = var4 - Float.intBitsToFloat(1127481344);
            } else {
               var3 = var4;
            }
         }

         if (field_4219.player.handSwingProgress > 0.0F) {
            var3 = var1;
         }

         var3 = var2 + MathHelper.wrapDegrees(var3 - var2) * Float.intBitsToFloat(1050253722);
         float var12 = MathHelper.wrapDegrees(var1 - var3);
         if (var12 < Float.intBitsToFloat(-1030356992)) {
            var12 = Float.intBitsToFloat(-1030356992);
         } else if (var12 >= Float.intBitsToFloat(1117126656)) {
            var12 = Float.intBitsToFloat(1117126656);
         }

         var3 = var1 - var12;
         if (var12 * var12 > Float.intBitsToFloat(1159479296)) {
            var3 += var12 * Float.intBitsToFloat(1045220557);
         }

         return var3;
      }
   }
}
