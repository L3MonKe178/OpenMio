package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.function.Predicate;
import me.mioclient.internal.PlayerUtil;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.FontRenderer;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AbstractModule_15 extends AbstractModule_26 {
   public final Setting<Boolean> field_1654 = this.add(new BooleanSetting("White", false));

   public AbstractModule_15() {
      super("Totems");
      this.method_2(new Class_0149(this));
   }

   @Override
   public void method_2(DrawContext var1) {
      ItemStack var2 = new ItemStack(Items.TOTEM_OF_UNDYING, PlayerUtil.method_29((Predicate<ItemStack>)(var0 -> var0.isOf(Items.TOTEM_OF_UNDYING))));
      if (var2.getCount() != 0) {
         var1.drawItem(var2, 0, 0);
         if (var2.getCount() > 1) {
            String var3 = String.valueOf(var2.getCount());
            var1.draw();
            var1.getMatrices().push();
            var1.getMatrices().translate(0.0F, 0.0F, Float.intBitsToFloat(1140457472));
            FontRenderer.field_3143
               .method_9(
                  var1,
                  var3,
                  Float.intBitsToFloat(1099431936) - FontRenderer.field_3143.method_221(var3),
                  Float.intBitsToFloat(1091567616),
                  this.field_1654.getValue() ? Color.white : this.method_9(Float.intBitsToFloat(1091567616))
               );
            var1.getMatrices().pop();
         }
      }
   }

   @Override
   public float[] method_31() {
      return new float[]{Float.intBitsToFloat(1098907648), Float.intBitsToFloat(1098907648)};
   }
}
