package me.mioclient.module.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0556;
import me.mioclient.enum_.Class_0760;
import me.mioclient.event.Event_26;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0031;
import me.mioclient.internal.Class_0158;
import me.mioclient.internal.Class_0617;
import me.mioclient.internal.Class_1117;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.exploit.AntiLevitationModule;
import me.mioclient.module.render.NoRenderModule;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;

public class HUDModule extends Module {
   public static AntiLevitationModule field_952 = Hub.field_2595.method_78(AntiLevitationModule.class);
   public static NoRenderModule norender = Hub.field_2595.method_78(NoRenderModule.class);
   public final Predicate<Boolean> field_953 = var0 -> field_4219.currentScreen instanceof Class_1117 var1x ? var1x.method_830() == null : true;
   public final Setting<Boolean> field_954 = this.add(new BooleanSetting("HudEditor", false, this.field_953));
   public final Setting<Class_0760> field_955 = this.add(new CustomSetting<>("Icons", Class_0760.MOVE));
   public final Setting<Boolean> field_956 = this.add(new BooleanSetting("SmoothWidth", false));
   public final Setting<Integer> field_957 = this.add(new CustomSetting3<>("SafeX", 1, 0, 20));
   public final Setting<Integer> field_958 = this.add(new CustomSetting3<>("SafeY", 1, 0, 10));
   public final Setting<Boolean> field_959 = this.add(new BooleanSetting("Colors", true).method_220());
   public final Setting<Color> field_960 = this.add(new ColorSetting("Color", new Color(189, 153, 255, 255)).method_8().method_2(this.field_959));
   public final Setting<Class_0556> field_961 = this.add(new CustomSetting<>("Fade", Class_0556.NONE, var1x -> this.field_959.method_194()));
   public final Setting<Integer> field_962 = this.add(
      new CustomSetting3<>(
         "FadeOffset",
         5,
         1,
         30,
         var1x -> (this.field_961.getValue() != Class_0556.NONE || ((ColorSetting)this.field_960).method_132()) && this.field_959.method_194()
      )
   );
   public final Setting<Color> field_963 = this.add(
      new ColorSetting("SecondColor", new Color(105, 86, 143, 255), var1x -> this.field_961.getValue() == Class_0556.PULSE && this.field_959.method_194())
         .method_8()
   );
   public final Setting<Integer> field_964 = this.add(
      new CustomSetting3<>("ColorAmount", 5, 2, 9, var1x -> this.field_961.getValue() == Class_0556.RAW && this.field_959.method_194())
   );
   public final Setting<Integer> field_965 = this.add(
      new CustomSetting3<>("FadeSpeed", 50, 20, 100, var1x -> this.field_961.getValue() == Class_0556.RAW && this.field_959.method_194())
   );
   public final Setting<Boolean> field_966 = this.add(
      new BooleanSetting("MixColors", true, var1x -> this.field_961.getValue() == Class_0556.RAW && this.field_959.method_194())
   );
   public final List<Setting<Color>> field_967 = new ArrayList<>(this.field_964.method_101());
   public final Class_0031 field_968 = new Class_0031(Float.intBitsToFloat(1073741824));
   public final Class_0031 field_969 = new Class_0031(Float.intBitsToFloat(1069547520));

   public HUDModule() {
      super("HUD", "Draws useful HUD elements on your screen.", Category.CLIENT);
      this.field_954.method_9(() -> {
         if (this.field_954.getValue()) {
            this.field_954.method_78(false);
            if (!this.method_535()) {
               field_4219.setScreen(Hub.method_756());
            }
         }
      });
      this.setDrawn(false);
      this.method_38(true);

      for (int var1 = 1; var1 <= this.field_964.method_101(); var1++) {
         int var2 = var1;
         Setting var3 = this.add(
            new ColorSetting(
                  new TextBuilder().method_2(var1).method_9("Color\u0001"),
                  Color.white,
                  var2x -> this.field_961.getValue() == Class_0556.RAW && this.field_959.method_194() && var2 <= this.field_964.getValue()
               )
               .method_8()
         );
         this.field_967.add(var3);
      }
   }

   @Subscribe
   public void method_2(Event_26 var1) {
      int var2 = 0;
      if (this.field_955.getValue() == Class_0760.MOVE && !(field_4219.currentScreen instanceof InventoryScreen)) {
         for (StatusEffectInstance var4 : field_4219.player.getStatusEffects()) {
            RegistryEntry var5 = var4.getEffectType();
            if ((!field_952.isToggled() || var5 != StatusEffects.SLOW_FALLING && var5 != StatusEffects.LEVITATION)
               && (!norender.isToggled() || !norender.field_719.getValue() || var5 != StatusEffects.DARKNESS)) {
               if (var2 == 0) {
                  var2++;
               }

               if (!((StatusEffect)var4.getEffectType().value()).isBeneficial()) {
                  var2 = 2;
               }
            }
         }
      }

      if (field_4219.currentScreen instanceof InventoryScreen) {
         var2 = 0;
      }

      this.field_968.method_3((float)(24 * var2 + 4));
      this.field_968.method_45();
      float var6 = this.field_969.method_45();
      float var7 = (float)field_4219.getWindow().getScaledWidth();
      float var8 = (float)field_4219.getWindow().getScaledHeight();
      if (var6 > Float.intBitsToFloat(1065353216) && !(field_4219.currentScreen instanceof ChatScreen)) {
         var1.method_881()
            .fill(
               2,
               (int)(var8 - Float.intBitsToFloat(1073741824) - var6),
               (int)(var7 - Float.intBitsToFloat(1073741824)),
               (int)(var8 - Float.intBitsToFloat(1073741824)),
               field_4219.options.getTextBackgroundColor(Integer.MIN_VALUE)
            );
      }

      Class_0158.field_460 = true;
      if (!(field_4219.currentScreen instanceof Class_0617)) {
         Hub.method_756().method_2(var1.method_881(), 0.0, 0.0);
      }

      Class_0158.field_460 = false;
   }

   public float method_338() {
      return this.field_969.field_55;
   }

   public float method_339() {
      return this.field_968.field_55 == Float.intBitsToFloat(1082130432) ? Float.intBitsToFloat(1065353216) : this.field_968.field_55;
   }

   public void method_340(float var1) {
      this.field_969.method_3(var1);
   }

   public int method_341() {
      return this.field_957.getValue();
   }

   public int method_342() {
      return this.field_958.getValue();
   }

   public void method_334(int var1) {
      this.field_957.method_78(var1);
   }

   public void method_82(int var1) {
      this.field_958.method_78(var1);
   }
}
