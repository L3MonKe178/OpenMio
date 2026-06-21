package me.mioclient.module.misc;

import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction.Axis;

public class HeavenModule extends Module {
   public HeavenModule() {
      super("Heaven", "Brings you to heaven after you die.", Category.MISC);
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      if (field_4219.currentScreen instanceof DeathScreen) {
         if (field_4219.player.verticalCollision) {
            field_4219.player.setBoundingBox(new Box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
         }

         field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, Double.longBitsToDouble(4613937818241073152L)));
      }
   }
}
