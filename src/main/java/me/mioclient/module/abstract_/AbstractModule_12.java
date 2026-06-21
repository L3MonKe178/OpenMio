package me.mioclient.module.abstract_;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.Hub;
import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0149;
import me.mioclient.internal.Constants;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.Class_1323;
import me.mioclient.module.client.FontsModule;
import me.mioclient.module.client.HUDModule;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class AbstractModule_12 extends AbstractModule_26 {
   public static HUDModule hud = Hub.field_2595.method_78(HUDModule.class);
   public final Setting<Boolean> field_1999 = this.add(new BooleanSetting("Durability", true));
   public final Setting<Boolean> field_2000 = this.add(new BooleanSetting("Percentage", true));
   public final Setting<Boolean> field_2001 = this.add(new BooleanSetting("BarColor", true));

   public AbstractModule_12() {
      super("Armor");
      this.method_2(new Class_0149(this) {
         @Override
         public boolean method_176() {
            return false;
         }
      });
   }

   @Subscribe(
      method_800 = 1
   )
   public void method_2(Event_26 var1) {
      if (hud.isToggled() || this.method_22()) {
         int var2 = var1.method_881().getScaledWindowWidth();
         int var3 = var1.method_881().getScaledWindowHeight();
         byte var4 = 15;

         for (int var5 = 3; var5 >= 0; var5--) {
            ItemStack var6 = (ItemStack)field_4219.player.getInventory().armor.get(var5);
            if (!var6.isEmpty()) {
               int var7 = this.method_643();
               this.method_2(var1.method_881(), var6, var2 / 2 + var4, var3 - var7);
               if (var6.isDamageable() && this.field_1999.getValue()) {
                  String var8 = String.format("%d%s", Class_1323.method_5(var6), "%");
                  float var9 = FontsModule.field_364.isToggled() ? Float.intBitsToFloat(1060320051) : Float.intBitsToFloat(1059481190);
                  if (!this.field_2000.getValue()) {
                     var8 = var8.substring(0, var8.length() - 1);
                     var9 = Float.intBitsToFloat(1065353216);
                  }

                  float var10 = (float)(var3 - var7) - Float.intBitsToFloat(1081291571) - (float)(this.field_2000.getValue() ? 0 : 2);
                  FontRenderer.field_3143
                     .method_9(
                        var1.method_881(),
                        var8,
                        (float)var2 / Float.intBitsToFloat(1073741824)
                           + (float)var4
                           + Float.intBitsToFloat(1091567616)
                           - FontRenderer.field_3143.method_221(var8) * Float.intBitsToFloat(1056964608) * var9
                           - var9,
                        var10,
                        var9,
                        this.field_2001.getValue() ? new Color(var6.getItemBarColor(), false) : this.method_9(var10)
                     );
               }

               var4 += 18;
            }
         }
      }
   }

   @Override
   public void method_2(DrawContext var1) {
      super.method_2(var1);
   }

   @Override
   public float[] method_31() {
      return new float[]{0.0F, 0.0F};
   }

   public void method_2(DrawContext var1, ItemStack var2, int var3, int var4) {
      var1.drawItem(var2, var3, var4);
      var1.drawItemInSlot(field_4219.textRenderer, var2, var3, var4);
      RenderSystem.enableBlend();
   }

   public int method_643() {
      int var1;
      if ((field_4219.player.isSubmergedInWater() || field_4219.player.getAir() != field_4219.player.getMaxAir())
         && field_4219.player.getAir() > 0
         && !field_4219.player.isCreative()) {
         var1 = 65;
      } else if (field_4219.player.hasVehicle() && field_4219.player.getVehicle() instanceof LivingEntity var2) {
         var1 = (int)(
            (double)Constants.field_684
               + Math.ceil((double)((var2.getMaxHealth() - Float.intBitsToFloat(1065353216)) / Float.intBitsToFloat(1101004800)))
                  * Double.longBitsToDouble(4621819117588971520L)
         );
      } else {
         var1 = 55 - (field_4219.player.isCreative() ? 17 : 0);
      }

      return var1;
   }
}
