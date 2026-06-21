package me.mioclient.module.movement;

import me.mioclient.enum_.Class_0445;
import me.mioclient.enum_.Class_0739;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0464;
import me.mioclient.internal.FontRenderer;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import nick.Settings;

public class FlightModule extends Module {
   public Setting<Class_0739> field_3487;
   public Setting<Float> field_3488;
   public Setting<Boolean> field_3489;
   public Setting<Float> field_3490;
   public Setting<Float> field_3491;
   public Setting<Class_0445> field_3492;
   public Setting<Boolean> field_3493;
   public Setting<Float> field_3494;
   public Setting<Float> field_3495;
   public long field_303;

   public FlightModule() {
      super("Flight", "Allows you to fly using magic.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_3488.method_214();
      this.field_3490.method_214();
      this.field_3487.method_9(() -> {
         if (this.isToggled()) {
            field_4219.player.getAbilities().flying = false;
         }
      });
   }

   @Override
   public String getInfo() {
      try {
         return FontRenderer.method_3(this.field_3487.getValue());
      } catch (Exception var2) {
         return null;
      }
   }

   @Override
   public void onEnable() {
      this.field_303 = System.currentTimeMillis();
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         if (this.field_3487.getValue() == Class_0739.VANILLA) {
            field_4219.player.getAbilities().flying = false;
         }
      }
   }

   @Subscribe
   public void method_2(Event_19 var1) {
      if (this.field_3487 == null || this.field_3487.getValue() == null) return;
      if (this.field_3487.getValue() != null) this.field_3487.getValue().method_2(this, var1);
      if (this.field_3492 == null || this.field_3492.getValue() == null) return;
      if (this.field_3492.getValue() != null) this.field_3492.getValue().method_2(var1);
   }

   @Subscribe
   public void method_2(Event_9 var1) {
      if (!Class_0464.method_363()) {
         this.field_303 = System.currentTimeMillis();
      }

      if (this.field_3487 == null || this.field_3487.getValue() == null) return;
      if (this.field_3487.getValue() != null) this.field_3487.getValue().method_2(this, var1);
      if (this.field_3492 == null || this.field_3492.getValue() == null) return;
      if (this.field_3492.getValue() != null) this.field_3492.getValue().method_2(var1);
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_303 = System.currentTimeMillis();
      }
   }

   public double method_874() {
      return this.field_3493.getValue()
         ? Class_0464.method_2(
            (double)(this.field_3488.getValue() != null ? this.field_3488.getValue().floatValue() : 0.0f),
            (double)(this.field_3494.getValue() != null ? this.field_3494.getValue().floatValue() : 0.0f),
            (double)(this.field_3495.getValue() != null ? this.field_3495.getValue().floatValue() : 0.0f),
            this.field_303
         )
         : (double)(this.field_3488.getValue() != null ? this.field_3488.getValue().floatValue() : 0.0f);
   }
}
