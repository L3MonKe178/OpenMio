package me.mioclient.module.abstract_;

import java.awt.Color;
import me.mioclient.internal.Class_0055;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Class_0838;
import me.mioclient.internal.Class_1081;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class AbstractModule_33 extends AbstractModule_26 {
   public final Setting<Color> field_2552 = this.add(new ColorSetting("Background", new Color(10, 10, 10, 50)));
   public final Setting<Color> field_2553 = this.add(new ColorSetting("Outline", new Color(10, 10, 10, 100)));
   public final Setting<Boolean> field_2554 = this.add(new BooleanSetting("HideEmpty", true));
   public final Class_0055 field_2555 = new Class_0055();

   public AbstractModule_33() {
      super("Inventory");
      Class_0149 var1 = new Class_0149(this);
      var1.method_2(this);
      this.method_2(var1);
   }

   @Override
   public void method_2(DrawContext var1) {
      this.field_2555.method_2(!this.isEmpty(), 200L);
      float var2 = this.field_2555.method_45();
      if (!this.field_2554.getValue()) {
         var2 = Float.intBitsToFloat(1065353216);
      }

      Color var3 = Class_1081.method_2(this.field_2552.getValue(), (float)this.field_2552.getValue().getAlpha() / Float.intBitsToFloat(1132396544) * var2);
      Color var4 = Class_1081.method_2(this.field_2553.getValue(), (float)this.field_2553.getValue().getAlpha() / Float.intBitsToFloat(1132396544) * var2);
      Class_0838.field_2672.method_9(var1.getMatrices(), 0.0F, 0.0F, Float.intBitsToFloat(1126301696), Float.intBitsToFloat(1113063424), var3);
      Class_0838.field_2672
         .method_2(
            var1.getMatrices(),
            Float.intBitsToFloat(-1082130432),
            Float.intBitsToFloat(-1082130432),
            Float.intBitsToFloat(1126301696),
            Float.intBitsToFloat(1113063424),
            var4
         );
      DefaultedList var5 = field_4219.player.getInventory().main;

      for (int var6 = 0; var6 < var5.size() - 9; var6++) {
         int var7 = var6 % 9 * 18;
         int var8 = var6 / 9 * 18;
         ItemStack var9 = (ItemStack)var5.get(var6 + 9);
         var1.drawItem(var9, var7, var8);
         var1.drawItemInSlot(field_4219.textRenderer, var9, var7, var8);
      }
   }

   public boolean isEmpty() {
      DefaultedList var1 = field_4219.player.getInventory().main;

      for (int var2 = 0; var2 < var1.size() - 9; var2++) {
         ItemStack var3 = (ItemStack)var1.get(var2 + 9);
         if (!var3.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public float[] method_31() {
      return new float[]{Float.intBitsToFloat(1126301696), Float.intBitsToFloat(1113063424)};
   }
}
