package me.mioclient.record;

import me.mioclient.Hub;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0200;
import me.mioclient.internal.Class_0396;
import me.mioclient.internal.Class_0878;
import me.mioclient.internal.Class_1225;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public final class Class_0375 {
   public final long field_1210;
   public final Box field_1211;
   public final Class_0200 field_1212;
   public final int field_1213;
   public final int field_1214;
   public final Vec3d field_1215;
   public final Class_0878 field_1216;
   public final String field_1217;

   public Class_0375(long var1, Box var3, Class_0200 var4, int var5, int var6, Vec3d var7, Class_0878 var8, String var9) {
      super();
      this.field_1210 = var1;
      this.field_1211 = var3;
      this.field_1212 = var4;
      this.field_1213 = var5;
      this.field_1214 = var6;
      this.field_1215 = var7;
      this.field_1216 = var8;
      this.field_1217 = var9;
   }

   public static Class_0375 method_2(PlayerEntity var0, String var1) {
      Class_0878 var2 = new Class_0878(Class_1309.field_4219.world);
      var2.method_78(var0);
      var2.hurtTime = var2.deathTime = 0;
      long var3 = System.currentTimeMillis();
      Box var5 = var0.getBoundingBox();
      Class_0200 var6 = Class_1225.method_1071();
      int var7 = Math.round(Class_0396.method_2((net.minecraft.entity.Entity)var0));
      int var8 = Hub.field_2613.method_39(var0);
      Vec3d var9 = var0.getPos();
      return new Class_0375(var3, var5, var6, var7, var8, var9, var2, var1);
   }

   public long method_417() {
      return this.field_1210;
   }

   public Box method_172() {
      return this.field_1211;
   }

   public Class_0200 method_418() {
      return this.field_1212;
   }

   public int method_419() {
      return this.field_1213;
   }

   public int method_420() {
      return this.field_1214;
   }

   public Vec3d method_421() {
      return this.field_1215;
   }

   public Class_0878 method_422() {
      return this.field_1216;
   }

   public String method_423() {
      return this.field_1217;
   }
}
