package me.mioclient.module.combat;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.enum_.Side3;
import me.mioclient.event.Event_12;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Timer;
import me.mioclient.internal.Class_0930;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.exploit.MultiTaskModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import nick.Settings;

public class AutoClickerModule extends Module {
   public static final MultiTaskModule multitask = Hub.field_2595.method_78(MultiTaskModule.class);
   public Setting<Side3> field_1443;
   public Setting<Integer> field_1444;
   public Setting<Float> field_1445;
   public Setting<Boolean> field_1446;
   public final Timer field_1447;
   public float field_1448;
   public final List<Long> field_1449;

   public AutoClickerModule() {
      super("AutoClicker", "Spams attack as you hold down the attack button.", Category.COMBAT);
      Settings.initialize(this);
      this.field_1447 = new Timer();
      this.field_1448 = Float.intBitsToFloat(1065353216);
      this.field_1449 = new ArrayList<>();
   }

   @Override
   public String getInfo() {
      return String.valueOf(this.field_1449.size());
   }

   @Subscribe
   public void method_2(Event_12 var1) {
      this.field_1449.removeIf(var0 -> System.currentTimeMillis() - var0 >= 1000L);
      long var2 = 1000L / (long)(this.field_1444.getValue() != null ? this.field_1444.getValue().intValue() : 0);
      if (!this.field_1445.method_105()) {
         var2 = (long)((float)var2 * this.field_1448);
      }

      if (this.field_1447.method_9(var2)) {
         this.method_489();
         this.field_1449.add(System.currentTimeMillis());
         this.field_1447.reset();
         this.field_1448 = Class_0930.method_2(Float.intBitsToFloat(1065353216), this.field_1445.getValue());
      }
   }

   public boolean method_185(Entity var1) {
      if (this.isToggled() && this.field_1446.getValue() && var1 instanceof PlayerEntity var2 && Hub.field_2603.method_30(var2)) {
         return true;
      }

      return false;
   }

   public void method_489() {
      if (!multitask.isToggled() || !field_4219.player.isUsingItem() && field_4219.currentScreen == null) {
         if (this.field_1443 == null || this.field_1443.getValue() == null) return;
         KeyBinding.onKeyPressed(((me.mioclient.mixin.ducks.DuckKeyBinding)(Object)this.field_1443.getValue().method_688()).getKey());
      } else {
         if (this.field_1443 == null || this.field_1443.getValue() == null) return;
      if (this.field_1443.getValue() != null) this.field_1443.getValue().method_687();
      }
   }
}
