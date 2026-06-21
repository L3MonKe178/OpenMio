package me.mioclient.module.misc;

import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0242;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import net.minecraft.client.gui.screen.DeathScreen;

public class AutoRespawnModule extends Module {
   public final Class_0242 field_2393 = new Class_0242();

   public AutoRespawnModule() {
      super("AutoRespawn", "Respawns automatically.", Category.MISC);
      this.setDrawn(false);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (field_4219.currentScreen instanceof DeathScreen && this.field_2393.method_9(150L)) {
         field_4219.player.requestRespawn();
         this.field_2393.reset();
      }
   }
}
