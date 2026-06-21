package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0848 extends Command {
   public Class_0848() {
      super("coords");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(RequiredArgumentBuilder.<CommandSource, String>argument("name", new Class_0246()).executes(var1x -> {
         this.method_9((String)var1x.getArgument("name", String.class), this.method_793());
         return 1;
      }))).executes(var1x -> {
         try {
            field_4219.keyboard.setClipboard(this.method_793());
            ChatUtil.method_2(Text.literal("Your position has been copied to the clipboard."), ChatUtil.method_38(-1));
         } catch (Exception var3) {
         }

         return 1;
      });
   }

   public String method_793() {
      return field_4219.player == null
         ? ""
         : new TextBuilder()
            .method_2((int)Math.floor(Math.random() * Double.longBitsToDouble(4666723172467343360L)))
            .method_2(Class_1225.method_1071().method_235())
            .method_2((int)field_4219.player.getZ())
            .method_2((int)field_4219.player.getY())
            .method_2((int)field_4219.player.getX())
            .method_9("X: \u0001, Y: \u0001, Z: \u0001 in The \u0001 [\u0001]");
   }

   public void method_9(String var1, String var2) {
      String var3 = new TextBuilder().method_2((Object)var2).method_2((Object)var1).method_9("w \u0001 \u0001");
      field_4219.player.networkHandler.sendChatCommand(var3);
   }
}
