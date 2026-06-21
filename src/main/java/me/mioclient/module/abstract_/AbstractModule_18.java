package me.mioclient.module.abstract_;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0200;
import me.mioclient.enum_.Class_0274;
import me.mioclient.enum_.SettingMode;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Class_0723;
import me.mioclient.internal.Class_0930;
import me.mioclient.internal.Class_1225;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.player.FreecamModule;
import me.mioclient.record.Class_0362;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AbstractModule_18 extends AbstractModule_26 {
   public static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   public final Setting<Float> field_3276 = this.add(new CustomSetting3<>("SafeRange", 0.0F, 0.0F, Float.intBitsToFloat(1120403456)).method_22("k"));
   public final Setting<Boolean> field_3277 = this.add(new BooleanSetting("Nether", true));
   public final Setting<Boolean> field_3278 = this.add(new BooleanSetting("Fake", false));
   public Class_0362 field_3279;

   public AbstractModule_18() {
      super("Position", "coordinates");
      this.field_3276.method_2("None", SettingMode.MIN);
      this.field_3278
         .method_9(
            () -> Hub.field_2619
                  .method_2(
                     () -> {
                        boolean var1x = Class_0930.method_17(50);
                        boolean var2 = Class_0930.method_17(50);
                        int var3 = (int)(
                           -field_4219.player.getX() * Double.longBitsToDouble(4611686018427387904L)
                              + Math.random() * field_4219.player.getX() * Double.longBitsToDouble(4616189618054758400L)
                        );
                        int var4 = (int)(
                           -field_4219.player.getZ() * Double.longBitsToDouble(4611686018427387904L)
                              + Math.random() * field_4219.player.getZ() * Double.longBitsToDouble(4616189618054758400L)
                        );
                        var3 += 13;
                        var4 -= 37;
                        if (var1x) {
                           var3 = -var3;
                        }

                        if (var2) {
                           var4 = -var4;
                        }

                        this.field_3279 = new Class_0362(var3, var4);
                     },
                     0
                  )
         );
      Class_0121 var1 = new Class_0121(
         () -> {
            if (!this.method_535()
               && (
                  !(this.field_3276.getValue() > 0.0F)
                     || field_4219.player.getPos().isInRange(Vec3d.ZERO, (double)(this.field_3276.getValue() * Float.intBitsToFloat(1148846080)))
                     || field_4219.player.age >= 200
               )) {
               boolean var1x = field_4219.world.getRegistryKey().getValue().getPath().contains("nether");
               Vec3d var2 = field_4219.player.getPos();
               if (freecam.isToggled()) {
                  var2 = field_4219.gameRenderer.getCamera().getPos().subtract(0.0, (double)field_4219.player.getHeight(), 0.0);
               }

               if (this.field_3278.getValue() && this.field_3279 != null) {
                  if (this.field_3279.method_401() == 0 && this.field_3279.method_402() == 0) {
                     this.field_3278.method_6();
                  }

                  var2 = var2.add((double)this.field_3279.method_401(), 0.0, (double)this.field_3279.method_402());
               }

               BlockPos var3 = BlockPos.ofFloored(var2);
               BlockPos var4 = var1x
                  ? BlockPos.ofFloored(
                     var2.multiply(
                        Double.longBitsToDouble(4620693217682128896L),
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4620693217682128896L)
                     )
                  )
                  : BlockPos.ofFloored(
                     var2.multiply(
                        Double.longBitsToDouble(4593671619917905920L),
                        Double.longBitsToDouble(4607182418800017408L),
                        Double.longBitsToDouble(4593671619917905920L)
                     )
                  );
               String var5 = new TextBuilder().method_2(String.valueOf(Formatting.WHITE)).method_9("XYZ: \u0001");
               String var6 = new TextBuilder().method_2(var3.getZ()).method_2(var3.getY()).method_2(var3.getX()).method_9("\u0001, \u0001, \u0001.");
               String var7 = new TextBuilder().method_2(var4.getZ()).method_2(var4.getY()).method_2(var4.getX()).method_9(" (\u0001, \u0001, \u0001.)");
               if (this.field_3277.getValue() && Class_1225.method_1071() != Class_0200.THE_END) {
                  var6 = new TextBuilder().method_2((Object)var7).method_2((Object)var6).method_9("\u0001\u0001");
               }

               return Text.literal(new TextBuilder().method_2((Object)var6).method_2((Object)var5).method_9("\u0001\u0001"));
            } else {
               return Text.literal("XYZ: %sREDACTED".formatted(Formatting.WHITE));
            }
         },
         () -> true
      );
      this.method_2(new Class_0723(this, var1));
      this.method_14().method_2(Class_0274.BOTTOM_LEFT);
   }
}
