package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.mioclient.Hub;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_26;
import net.minecraft.command.CommandSource;

public final class Class_0712 extends Command {
   public final Set<Module> field_2264 = new HashSet<>();

   public Class_0712() {
      super("alloff");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.executes(var1x -> {
         this.field_2264.clear();

         for (Module var3 : (List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()) {
            if (!(var3 instanceof AbstractModule_26) && var3.isToggled()) {
               var3.method_68();
               this.field_2264.add(var3);
            }
         }

         return 1;
      });
      var1.then(Command.method_2("restore", "backup").executes(var1x -> {
         for (Module var3 : (List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()) {
            if (!(var3 instanceof AbstractModule_26)) {
               var3.method_38(this.field_2264.contains(var3));
            }
         }

         return 1;
      }));
   }
}
