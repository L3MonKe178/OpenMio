package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public class Class_0299 extends Class_0618 {
   public Class_0299() {
      super("defaults");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.executes(var1x -> {
         ConfirmScreen var2 = new ConfirmScreen(var0 -> {
            if (var0) {
               for (Module var2x : (List<Module>)Hub.field_2599.getRegistry()) {
                  for (Setting var4 : var2x.getRegistry()) {
                     var4.reset();
                  }

                  var2x.method_38(false);
               }
            }

            field_4219.setScreen(null);
         }, Text.of("Reset to defaults."), Text.of("Continue?"));
         this.method_2(() -> field_4219.setScreen(var2));
         return 1;
      });
   }
}
