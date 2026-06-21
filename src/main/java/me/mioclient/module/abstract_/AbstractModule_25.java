package me.mioclient.module.abstract_;

import me.mioclient.enum_.Class_0274;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Class_0485;
import me.mioclient.internal.Class_0723;
import me.mioclient.internal.Class_1303;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public class AbstractModule_25 extends AbstractModule_26 {
   public AbstractModule_25() {
      super("Direction", "facing");
      this.method_2(new Class_0723(this, new Class_0121(() -> Text.literal(this.method_254()), () -> true)));
      this.method_14().method_2(Class_0274.BOTTOM_LEFT);
   }

   public String method_254() {
      String var1 = new Class_1303()
         .method_2((int)MathHelper.wrapDegrees(field_4219.gameRenderer.getCamera().getYaw()))
         .method_2(String.valueOf(Formatting.WHITE))
         .method_9("\u0001 (\u0001, ");

      return switch (Class_0485.method_221(field_4219.gameRenderer.getCamera().getYaw())) {
         case 0 -> new Class_1303().method_2((Object)var1).method_9("South\u0001+Z)");
         case 1 -> new Class_1303().method_2((Object)var1).method_9("SouthWest\u0001-X +Z)");
         case 2 -> new Class_1303().method_2((Object)var1).method_9("West\u0001-X)");
         case 3 -> new Class_1303().method_2((Object)var1).method_9("NorthWest\u0001-X -Z)");
         case 4 -> new Class_1303().method_2((Object)var1).method_9("North\u0001-Z)");
         case 5 -> new Class_1303().method_2((Object)var1).method_9("NorthEast\u0001+X -Z)");
         case 6 -> new Class_1303().method_2((Object)var1).method_9("East\u0001+X)");
         case 7 -> new Class_1303().method_2((Object)var1).method_9("SouthEast\u0001+X +Z)");
         default -> "Waiting...";
      };
   }
}
