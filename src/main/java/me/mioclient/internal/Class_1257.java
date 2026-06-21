package me.mioclient.internal;

import java.net.SocketAddress;
import me.mioclient.api.MioAPI;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public final class Class_1257 {
   public transient long spawnTime = System.currentTimeMillis();
   public final String field_3950;
   public final String field_3951;
   public final int field_3952;
   public final int field_3953;
   public final int field_3954;
   public transient BlockPos field_3955;

   public Class_1257(String var1, String var2, int var3, int var4, int var5) {
      super();
      this.field_3950 = var1;
      this.field_3951 = var2;
      this.field_3952 = var3;
      this.field_3953 = var4;
      this.field_3954 = var5;
   }

   public Class_1257(String var1, String var2, BlockPos var3) {
      super();
      this.field_3950 = var1;
      this.field_3951 = var2;
      this.field_3952 = var3.getX();
      this.field_3953 = var3.getY();
      this.field_3954 = var3.getZ();
   }

   public String method_1095() {
      return this.field_3950;
   }

   public String method_1096() {
      return Formatting.strip(this.field_3950);
   }

   public String method_106() {
      return this.field_3951;
   }

   public int getX() {
      return this.field_3952;
   }

   public int getY() {
      return this.field_3953;
   }

   public int method_1097() {
      return this.field_3954;
   }

   public BlockPos method_111() {
      if (this.field_3955 == null) {
         this.field_3955 = new BlockPos(this.field_3952, this.field_3953, this.field_3954);
      }

      return this.field_3955;
   }

   public long getSpawnTime() {
      return this.spawnTime;
   }

   public void reset() {
      this.spawnTime = System.currentTimeMillis();
   }

   public boolean method_1098() {
      String var1 = null;

      try {
         SocketAddress var2 = MioAPI.field_4219.getNetworkHandler().getConnection().getAddress();
         var1 = var2.toString();
         int var3 = var1.indexOf(47);
         if (var3 > 0) {
            var1 = var1.substring(0, var3);
         }

         while (var1.endsWith(".")) {
            var1 = var1.substring(0, var1.length() - 1);
         }
      } catch (Exception var4) {
      }

      if (var1 == null || var1.isEmpty()) {
         var1 = "singleplayer";
      }

      return var1.equals(this.field_3951);
   }
}
