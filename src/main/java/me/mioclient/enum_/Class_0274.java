package me.mioclient.enum_;

import java.util.function.Supplier;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.record.Class_0920;
import net.minecraft.util.math.MathHelper;

public enum Class_0274 implements Class_1309, Class_0013 {
   TOP_LEFT("top_left", () -> Class_0920.method_9(0.0F, 0.0F, 10.0F, 10.0F)),
   TOP_RIGHT("top_right", () -> Class_0920.method_9((float)(field_4219.getWindow().getScaledWidth() - 10), 0.0F, 10.0F, 10.0F)),
   BOTTOM_LEFT("bottom_left", () -> Class_0920.method_9(0.0F, (float)(field_4219.getWindow().getScaledHeight() - 10), 10.0F, 10.0F)),
   BOTTOM_RIGHT(
      "bottom_right",
      () -> Class_0920.method_9((float)(field_4219.getWindow().getScaledWidth() - 10), (float)(field_4219.getWindow().getScaledHeight() - 10), 10.0F, 10.0F)
   ),
   TOP_CENTER("top_center", () -> Class_0920.method_9((float)field_4219.getWindow().getScaledWidth() / 2.0F - 10.0F, 0.0F, 10.0F, 10.0F)),
   NONE("none", () -> Class_0920.method_9(0.0F, 0.0F, (float)field_4219.getWindow().getScaledWidth(), (float)field_4219.getWindow().getScaledHeight()));

   public final String field_868;
   public final Supplier<Class_0920> field_869;

    Class_0274(String var3, Supplier<Class_0920> var4) {
      this.field_868 = var3;
      this.field_869 = var4;
   }

   @Override
   public String getName() {
      return this.field_868;
   }

   public boolean method_5(double var1, double var3) {
      return this.field_869
         .get()
         .method_5(
            MathHelper.clamp(var1, 0.0, (double)field_4219.getWindow().getScaledWidth()),
            MathHelper.clamp(var3, 0.0, (double)field_4219.getWindow().getScaledHeight())
         );
   }

   public Class_0920 method_198() {
      return this.field_869.get();
   }
}
