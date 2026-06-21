package me.mioclient.module.render;

import java.awt.Color;
import java.util.Calendar;
import me.mioclient.api.Class_0718;
import me.mioclient.enum_.Class_0574;
import me.mioclient.enum_.Class_1296;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import nick.Settings;

public class AmbienceModule extends Module {
   public Setting<Class_0574> field_207;
   public Setting<Color> field_208;
   public Setting<Integer> field_209;
   public Setting<Boolean> field_210;
   public Setting<Boolean> field_211;
   public Setting<Float> field_212;
   public Setting<Boolean> field_213;
   public Setting<Class_1296> field_214;
   public Setting<Boolean> field_215;
   public Setting<Float> field_216;
   public double field_217;

   public AmbienceModule() {
      super("Ambience", "Changes several ambient things.", Category.RENDER);
      Settings.initialize(this);
      this.field_209.method_9(() -> {
         if (this.field_207.getValue() == Class_0574.SKY && field_4219.world != null) {
            field_4219.worldRenderer.reload();
         }
      });
      this.field_207
         .method_9(
            () -> {
               if (!this.method_535()) {
                  if (field_4219.player.hasStatusEffect(StatusEffects.NIGHT_VISION)
                     && field_4219.player.getStatusEffect(StatusEffects.NIGHT_VISION).getAmplifier() == 68) {
                     field_4219.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
                  }

                  if (this.field_207.getValue() != Class_0574.GAMMA) {
                     ((Class_0718)(Object)field_4219.options.getGamma()).forceSetValue(this.field_217);
                  }
               }
            }
         );
      this.setDrawn(false);
   }

   @Override
   public void onEnable() {
      this.field_217 = (Double)field_4219.options.getGamma().getValue();
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         field_4219.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
      }

      ((Class_0718)(Object)field_4219.options.getGamma()).forceSetValue(this.field_217);
   }

   @Override
   public void onToggle() {
      if (field_4219.worldRenderer != null) {
         if (this.field_207.getValue() == Class_0574.SKY || this.field_207.getValue() == Class_0574.SCREEN) {
            field_4219.worldRenderer.reload();
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.method_535()) {
         if (this.field_210.getValue()) {
            long var2 = this.method_94();
            field_4219.world.setTime(var2);
            field_4219.world.setTimeOfDay(var2);
         }

         if (this.field_207.getValue() == Class_0574.GAMMA) {
            ((Class_0718)(Object)field_4219.options.getGamma()).forceSetValue(Double.longBitsToDouble(4652007308841189376L));
         } else if (this.field_207.getValue() == Class_0574.POTION && !field_4219.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            field_4219.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 68));
         }
      }
   }

   public long method_94() {
      if (this.field_211.getValue()) {
         Calendar var1 = Calendar.getInstance();
         return (long)(
            ((float)var1.get(11) + (float)var1.get(12) / Float.intBitsToFloat(1114636288)) * Float.intBitsToFloat(1148846080)
               + Float.intBitsToFloat(1183621120)
         );
      } else {
         return (long)(this.field_212.getValue() * Float.intBitsToFloat(1148846080) + Float.intBitsToFloat(1183621120));
      }
   }

   public boolean method_95() {
      return this.isToggled() && this.field_213.getValue() && this.field_215.getValue();
   }
}
