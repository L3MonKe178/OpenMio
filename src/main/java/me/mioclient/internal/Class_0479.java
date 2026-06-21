package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.module.client.UIModule;
import net.minecraft.command.CommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public final class Class_0479 extends Command {
   public Class_0479() {
      super("help");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      if (!new File("mio-fabric").exists()) {
         Hub.field_2619
            .method_2(
               () -> {
                  ChatUtil.method_2(Text.literal("Welcome to Mio!"), ChatUtil.method_38(-1001));
                  ChatUtil.method_2(
                     Text.literal("The UI bind is ").append(this.method_5(UIModule.field_2843.getKeybind().method_4())), ChatUtil.method_38(-1002)
                  );
                  ChatUtil.method_2(Text.literal("The command prefix is ").append(this.method_5(CommandManager.method_927())), ChatUtil.method_38(-1003));
               },
               0
            );
      }

      var1.executes(var0 -> {
         ArrayList var1x = new ArrayList();
         ((List<Command>)Hub.field_2600.getRegistry()).forEach(var1xx -> var1x.add(var1xx.getName()));
         MutableText var2 = Text.empty();
         var2.append("Commands (%d): ".formatted(var1x.size()));
         var2.append(Text.of(new TextBuilder().method_2(String.join(", ", var1x)).method_9("\u0001.")));
         ChatUtil.method_2(var2, ChatUtil.method_38(-1));
         return 1;
      });
   }
}
