package me.mioclient.module.movement;

import me.mioclient.module.Category;
import me.mioclient.module.Module;

public class NoJumpDelayModule extends Module {
   public NoJumpDelayModule() {
      super("NoJumpDelay", "Removes the jumping delay when sprint-jumping.", Category.MOVEMENT);
   }
}
