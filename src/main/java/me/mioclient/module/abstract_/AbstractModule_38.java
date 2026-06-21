package me.mioclient.module.abstract_;

import me.mioclient.internal.Class_1303;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import net.minecraft.util.Formatting;

public class AbstractModule_38 extends Module {
   public AbstractModule_38() {
      super(
         "NoHitDelay",
         new Class_1303().method_2(String.valueOf(Formatting.RED)).method_9("Removes the weapon attack delay. \n\u0001Only works on pre 1.9 servers."),
         Category.COMBAT
      );
   }
}
