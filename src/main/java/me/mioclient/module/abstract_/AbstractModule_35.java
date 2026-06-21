package me.mioclient.module.abstract_;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import me.mioclient.enum_.Class_0274;
import me.mioclient.enum_.Class_0710;
import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Class_0199;
import me.mioclient.internal.Class_0723;
import me.mioclient.internal.Class_1162;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.Setting;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;

public class AbstractModule_35 extends AbstractModule_26 {
   public final Setting<Boolean> field_4296 = this.add(new BooleanSetting("Vanilla", false));
   public final Setting<Class_0710> field_4297 = this.add(new CustomSetting<>("Selection", Class_0710.ANY));
   public final Setting<Set<StatusEffect>> field_4298 = this.add(new Class_0199<>("WhiteList", Registries.STATUS_EFFECT));
   public final List<Class_1162> field_4299 = new ArrayList<>();

   public AbstractModule_35() {
      super("Effects", "potions");
      Class_0723 var1 = new Class_0723(this, this.field_4299) {
         @Override
         public Color method_2(float var1, Class_0121 var2) {
            StatusEffect var3 = (StatusEffect)((Class_1162)var2).method_1026().value();
            return AbstractModule_35.this.field_4296.getValue() ? new Color(var3.getColor(), false) : super.method_2(var1, var2);
         }
      };
      this.method_2(var1);
      this.method_14().method_2(Class_0274.BOTTOM_RIGHT);
   }

   @Override
   public void onEnable() {
      this.field_4299.clear();
      Registries.STATUS_EFFECT
         .streamEntries()
         .forEach(
            var1 -> this.field_4299
                  .add(
                     new Class_1162(
                        var1, () -> field_4219.player.hasStatusEffect(var1) && (this.field_4297.getValue() != null ? this.field_4297.getValue().method_2((StatusEffect)var1.value(), this.field_4298) : false)
                     )
                  )
         );
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      this.field_4299.sort(Comparator.comparing(var0 -> ((StatusEffect)var0.method_1026().value()).getName().getString()));
   }
}
