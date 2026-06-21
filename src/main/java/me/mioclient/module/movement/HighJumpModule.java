package me.mioclient.module.movement;

import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0464;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.entity.attribute.EntityAttributes;
import nick.Settings;

public class HighJumpModule extends Module {
   public static final float field_878 = Float.intBitsToFloat(1054280253);
   public Setting<Float> field_879;
   public Setting<Boolean> field_880;

   public HighJumpModule() {
      super("HighJump", "Makes you jump higher.", Category.MOVEMENT);
      Settings.initialize(this);
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         this.reset();
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (!this.field_880.getValue() && Class_0464.method_363()) {
         this.reset();
      } else {
         this.method_84(this.field_879.getValue());
      }
   }

   public void reset() {
      this.method_84(Float.intBitsToFloat(1054280253));
   }

   public void method_84(float var1) {
      field_4219.player.getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH).setBaseValue((double)var1);
   }
}
