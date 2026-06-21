package com.jagrosh.discordipc.entities;

import com.jagrosh.discordipc.impl.DataConsumer;

public class Callback {
   public final DataConsumer<Packet> success;
   public final DataConsumer<String> failure;

   public Callback() {
      this(null, null);
   }

   public Callback(DataConsumer<Packet> var1) {
      this(var1, null);
   }

   public Callback(DataConsumer<Packet> var1, DataConsumer<String> var2) {
      super();
      this.success = var1;
      this.failure = var2;
   }

   public boolean isEmpty() {
      return this.success == null && this.failure == null;
   }

   public void succeed(Packet var1) {
      if (this.success != null) {
         this.success.accept(var1);
      }
   }

   public void fail(String var1) {
      if (this.failure != null) {
         this.failure.accept(var1);
      }
   }
}
