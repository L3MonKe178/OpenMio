package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import net.minecraft.world.biome.Biome.Precipitation;

public enum Class_1296 implements Nameable {
   CLEAR("Clear", Precipitation.NONE),
   SNOW("Snow", Precipitation.SNOW),
   RAIN("Rain", Precipitation.RAIN),
   DUSTY("Dusty", Precipitation.NONE);

   public final String field_4190;
   public final Precipitation field_4191;

    Class_1296(String var3, Precipitation var4) {
      this.field_4190 = var3;
      this.field_4191 = var4;
   }

   @Override
   public String getName() {
      return this.field_4190;
   }

   public Precipitation method_1157() {
      return this.field_4191;
   }
}
