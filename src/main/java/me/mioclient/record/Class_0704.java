package me.mioclient.record;

import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;

public final class Class_0704 {
   public final int field_2235;
   public final EntityType<?> field_2236;

   public Class_0704(int var1, EntityType<?> var2) {
      super();
      this.field_2235 = var1;
      this.field_2236 = var2;
   }

   public static Class_0704 method_9(EntitySpawnS2CPacket var0) {
      return new Class_0704(var0.getEntityId(), var0.getEntityType());
   }

   public int method_684() {
      return this.field_2235;
   }

   public EntityType<?> method_685() {
      return this.field_2236;
   }
}
