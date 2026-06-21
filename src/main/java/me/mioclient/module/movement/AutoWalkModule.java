package me.mioclient.module.movement;

import me.mioclient.Hub;
import me.mioclient.enum_.Priority;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_16;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0211;
import me.mioclient.internal.Class_1245;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.text.Text;
import nick.Settings;

public class AutoWalkModule extends Module {
   public Setting<Boolean> field_260;
   public Setting<Boolean> field_261;
   public Setting<Class_0211> field_262;
   public Setting<Float> field_263;
   public static boolean field_264;
   public boolean field_265;

   public AutoWalkModule() {
      super("AutoWalk", "Lets your forward key rest.", Category.MOVEMENT);
      Settings.initialize(this);
      this.field_265 = true;
   }

   @Override
   public void onDisable() {
      field_264 = true;
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (Hub.field_2614.method_874() == 0.0) {
         if (!this.field_265 && this.field_261.getValue()) {
            Class_1245.method_2(Text.literal("You are stuck!"), Class_1245.method_2(this), Priority.HIGH);
            Hub.field_2606.method_2(this.field_262.getValue()).method_230(this.field_263.getValue());
         }

         this.field_265 = true;
      } else {
         this.field_265 = false;
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_5(Event_16 var1) {
      var1.method_276().movementForward = var1.method_278() ? var1.method_277() : Float.intBitsToFloat(1065353216);
      var1.method_276().pressingForward = true;
      if (this.field_260.getValue()) {
         var1.method_276().jumping = true;
      }
   }

   @Subscribe(
      method_800 = -100
   )
   public static void method_7(Event_16 var0) {
      if (field_264) {
         var0.method_276().movementForward = 0.0F;
         var0.method_276().pressingForward = false;
         var0.method_276().jumping = false;
         field_264 = false;
      }
   }

   static {
      field_4220.method_5(AutoWalkModule.class);
   }
}
