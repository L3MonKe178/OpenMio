package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.mioclient.module.exploit.IllegalDisconnectModule;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0138 extends Command {
   public Class_0138() {
      super("disconnect");
      this.method_9("dc");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(LiteralArgumentBuilder.<CommandSource>literal("illegal").executes(var1x -> {
         if (!this.method_535()) {
            IllegalDisconnectModule.method_203();
         }

         return 1;
      }))).executes(var1x -> {
         if (!this.method_535()) {
            field_4219.player.networkHandler.getConnection().disconnect(Text.of("Disconnected"));
         }

         return 1;
      });
   }
}
