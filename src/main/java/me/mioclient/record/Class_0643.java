package me.mioclient.record;

import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.player.PlayerEntity;

public final class Class_0643 {
   public final PlayerEntity field_2058;
   public final long field_2059;
   public final AtomicBoolean field_2060;

   public Class_0643(PlayerEntity var1, long var2, AtomicBoolean var4) {
      super();
      this.field_2058 = var1;
      this.field_2059 = var2;
      this.field_2060 = var4;
   }

   public boolean method_653() {
      return System.currentTimeMillis() - this.field_2059 >= 300L || this.field_2060.get();
   }

   public PlayerEntity method_654() {
      return this.field_2058;
   }

   public long method_655() {
      return this.field_2059;
   }

   public AtomicBoolean method_656() {
      return this.field_2060;
   }
}
