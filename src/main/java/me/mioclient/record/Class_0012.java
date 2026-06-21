package me.mioclient.record;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import me.mioclient.api.MioAPI;

public final class Class_0012 implements BooleanConsumer, MioAPI {
   public final Runnable field_20;

   public Class_0012(Runnable var1) {
      super();
      this.field_20 = var1;
   }

   public void accept(boolean var1) {
      field_4219.setScreen(null);
      if (var1) {
         this.field_20.run();
      }
   }

   public Runnable method_13() {
      return this.field_20;
   }
}
