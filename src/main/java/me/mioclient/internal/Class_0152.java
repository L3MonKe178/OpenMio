package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_0333;
import me.mioclient.api.Class_1322;
import me.mioclient.module.Module;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0152 extends Class_0618 {
   public static boolean field_442 = false;
   public static final String field_443 = "ㅤ";

   public Class_0152() {
      super("panic");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.executes(var1x -> {
         this.method_2(() -> field_4219.setScreen(new ConfirmScreen(var1xx -> {
               if (var1xx) {
                  this.method_183();
               }

               field_4219.setScreen(null);
            }, Text.literal("Unload the client."), Text.literal("Continue?"))));
         return 1;
      });
   }

   public void method_183() {
      Hub.field_2597.method_357();
      field_442 = true;
      String var1 = Class_1032.method_927();
      Class_1032.method_270("ㅤ");

      for (Module var3 : (List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()) {
         var3.modifyKeybind(var0 -> var0.method_9(-1));
         if (var3.isToggled()) {
            var3.method_68();
         }
      }

      field_4219.inGameHud.getChatHud().getMessageHistory().removeIf(var1x -> var1x.startsWith(var1));
      ((Class_1322)field_4219.inGameHud.getChatHud()).getVisible().removeIf(var0 -> {
         Class_0333 var1x = (Class_0333)(Object)var0;
         return var1x.getSignature() != null && var1x.getSignature().toByteBuffer().getInt() < 0;
      });
      field_4219.inGameHud.getChatHud().getMessageHistory().removeIf(var1x -> var1x.startsWith(var1));
   }
}
