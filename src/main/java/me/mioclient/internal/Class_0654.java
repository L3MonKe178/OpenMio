package me.mioclient.internal;

import me.mioclient.api.Class_1194;
import me.mioclient.api.Class_1309;
import me.mioclient.mixin.ducks.DuckLivingEntity;

public class Class_0654 implements Class_1309 {
   public static boolean field_2106;

   public Class_0654() {
      super();
   }

   public static void method_633() {
      field_2106 = true;
      int var0 = field_4219.player.getItemUseTimeLeft();
      float var1 = field_4219.player.distanceTraveled;
      float var2 = field_4219.player.limbAnimator.getSpeed();
      float var3 = field_4219.player.horizontalSpeed;
      float var4 = field_4219.player.prevHorizontalSpeed;
      float var5 = field_4219.player.renderYaw;
      float var6 = field_4219.player.lastRenderYaw;
      float var7 = field_4219.player.renderPitch;
      float var8 = field_4219.player.lastRenderPitch;
      int var9 = ((DuckLivingEntity)field_4219.player).mio$getLastAttackedTicks();
      int var10 = field_4219.player.handSwingTicks;
      float var11 = field_4219.player.lastHandSwingProgress;
      float var12 = field_4219.player.handSwingProgress;
      ((Class_1194)field_4219.player).superTick();
      ((Class_1194)field_4219.player).resetEvent();
      ((DuckLivingEntity)field_4219.player).mio$setLastAttackedTicks(var9);
      ((DuckLivingEntity)field_4219.player).setItemUseTimeLeft(var0);
      field_4219.player.distanceTraveled = var1;
      field_4219.player.limbAnimator.setSpeed(var2);
      field_4219.player.horizontalSpeed = var3;
      field_4219.player.prevHorizontalSpeed = var4;
      field_4219.player.renderYaw = var5;
      field_4219.player.lastRenderYaw = var6;
      field_4219.player.renderPitch = var7;
      field_4219.player.lastRenderPitch = var8;
      field_4219.player.handSwingTicks = var10;
      field_4219.player.lastHandSwingProgress = var11;
      field_4219.player.handSwingProgress = var12;
      ((Class_1194)field_4219.player).sendMovementPacketsWrapper();
      ((Class_1194)field_4219.player).resetRotations();
      field_2106 = false;
   }

   public static boolean method_663() {
      return field_2106;
   }
}
