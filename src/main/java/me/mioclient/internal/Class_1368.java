package me.mioclient.internal;

import com.google.gson.annotations.SerializedName;
import me.mioclient.api.Nameable;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.text.HoverEvent.Action;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;

public class Class_1368 implements Nameable {
   @SerializedName("x")
   public final double field_4446;
   @SerializedName("y")
   public final double field_4447;
   @SerializedName("z")
   public final double field_4448;
   @SerializedName("name")
   public final String field_4449;
   @SerializedName("dimension")
   public final String field_4450;
   @SerializedName("server")
   public final String field_4451;
   @SerializedName("toggled")
   public boolean field_41;
   public transient Vec3d field_1675;

   public Class_1368(String var1, double var2, double var4, double var6, String var8, String var9) {
      super();
      this.field_4446 = this.method_39(var2);
      this.field_4447 = this.method_39(var4);
      this.field_4448 = this.method_39(var6);
      this.field_4450 = var8;
      this.field_4451 = var9;
      this.field_4449 = var1;
      this.field_1675 = new Vec3d(var2, var4, var6);
      this.field_41 = true;
   }

   public Class_1368(String var1, Vec3d var2, String var3, String var4) {
      this(var1, var2.x, var2.y, var2.z, var3, var4);
   }

   public double method_380() {
      return this.field_4446;
   }

   public double method_395() {
      return this.field_4447;
   }

   public double method_396() {
      return this.field_4448;
   }

   public String method_600() {
      return this.field_4450;
   }

   public String method_106() {
      return this.field_4451;
   }

   public double method_17(Vec3d var1) {
      return var1.distanceTo(this.field_1675);
   }

   public double method_39(double var1) {
      return Class_0930.method_2(var1, 1);
   }

   public boolean isToggled() {
      return this.field_41;
   }

   public void method_38(boolean var1) {
      this.field_41 = var1;
   }

   public Vec3d method_733() {
      if (this.field_1675 == null) {
         this.field_1675 = new Vec3d(this.field_4446, this.field_4447, this.field_4448);
      }

      return this.field_1675;
   }

   public boolean method_16(String var1, String var2) {
      return this.getName().equals(var1) && this.method_106().equalsIgnoreCase(var2);
   }

   @Override
   public String getName() {
      return this.field_4449;
   }

   @Override
   public Text method_15() {
      return Text.literal(this.field_4449)
         .styled(
            var1 -> var1.withHoverEvent(
                  new HoverEvent(
                     Action.SHOW_TEXT,
                     Text.literal(
                           String.format(
                              "%s's info:\nx: %.1f\ny: %.1f\nz: %.1f\ndimension: %s\nserver: %s",
                              this.field_4449,
                              this.field_4446,
                              this.field_4447,
                              this.field_4448,
                              this.field_4450,
                              this.field_4451
                           )
                        )
                        .styled(var0 -> var0.withFormatting(Formatting.GRAY))
                  )
               )
         );
   }
}
