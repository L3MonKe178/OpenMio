package me.mioclient.module.misc;

import me.mioclient.module.Category;
import me.mioclient.module.Module;

public class NoNarratorModule extends Module {
   public NoNarratorModule() {
      super("NoNarrator", "Disables the narrator feature.", Category.MISC);
      this.setDrawn(false);
   }
}
