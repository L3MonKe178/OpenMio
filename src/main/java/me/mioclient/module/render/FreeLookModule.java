package me.mioclient.module.render;

import me.mioclient.Hub;
import me.mioclient.event.Event_17;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0838;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.player.FreecamModule;
import net.minecraft.client.option.Perspective;

public class FreeLookModule extends Module {
   public static FreecamModule freecam = Hub.field_2595.method_78(FreecamModule.class);
   public Perspective field_1352;

   public FreeLookModule() {
      super("FreeLook", "Allows you to rotate your 3rd person camera without rotating your player.", Category.RENDER);
   }

   @Override
   public void onEnable() {
      if (!freecam.isToggled()) {
         this.field_1352 = field_4219.options.getPerspective();
         field_4219.options.setPerspective(this.method_466());
      }
   }

   @Override
   public void onDisable() {
      if (this.field_1352 == Perspective.FIRST_PERSON) {
         field_4219.options.setPerspective(Perspective.FIRST_PERSON);
      }
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_2(Event_17 var1) {
      if (!freecam.isToggled()) {
         field_4219.options.setPerspective(this.method_466());
         field_4219.crosshairTarget = field_4219.player.raycast(field_4219.player.getBlockInteractionRange(), Class_0838.method_776(), false);
      }
   }

   public Perspective method_466() {
      if (this.field_1352 == Perspective.FIRST_PERSON) {
         return Perspective.THIRD_PERSON_BACK;
      } else {
         if (this.field_1352 == null) {
            this.field_1352 = Perspective.FIRST_PERSON;
         }

         return this.field_1352;
      }
   }
}
