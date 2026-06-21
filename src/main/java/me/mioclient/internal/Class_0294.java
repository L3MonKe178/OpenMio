package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.tree.CommandNode;
import java.util.HashSet;
import java.util.List;
import me.mioclient.event.Event_4;
import me.mioclient.event.Subscribe;
import net.minecraft.command.CommandSource;
import net.minecraft.network.packet.c2s.play.RequestCommandCompletionsC2SPacket;
import net.minecraft.network.packet.s2c.play.CommandSuggestionsS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;

public final class Class_0294 extends Command {
   public final Timer stopwatch = new Timer();
   public boolean field_947;

   public Class_0294() {
      super("plugins");
      field_4220.method_14(this);
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.executes(var1x -> {
         field_4219.player.networkHandler.sendPacket(new RequestCommandCompletionsC2SPacket(0, "/"));
         ChatUtil.method_2(Text.literal("Fetching plugins..."), ChatUtil.method_38(-3));
         this.stopwatch.reset();
         this.field_947 = true;
         return 1;
      });
   }

   @Subscribe
   public void method_5(Event_4 var1) {
      if (this.field_947) {
         if (this.stopwatch.method_9(3000L)) {
            this.field_947 = false;
            List var2 = field_4219.player.networkHandler.getCommandDispatcher().getRoot().getChildren().stream().map(CommandNode::getName).toList();
            if (var2.isEmpty()) {
               ChatUtil.method_2(Text.literal("Couldn't get plugins"), ChatUtil.method_38(-3));
            } else {
               ChatUtil.method_2(this.method_5(var2), ChatUtil.method_38(-3));
            }
         }

         if (var1.method_127() instanceof CommandSuggestionsS2CPacket var4) {
            this.field_947 = false;
            ChatUtil.method_2(this.method_5(var4.getSuggestions().getList().stream().<String>map(Suggestion::getText).toList()), ChatUtil.method_38(-3));
         }
      }
   }

   public Text method_5(Iterable<String> var1) {
      HashSet<String> var2 = new HashSet<>();

      for (String var4 : var1) {
         String[] var5 = var4.split(":");
         if (var5.length > 1 && !var5[0].isEmpty()) {
            var2.add(var5[0]);
         }
      }

      Text var6 = Texts.join(var2, var0 -> Text.literal(var0).styled(var0x -> ChatUtil.method_2(var0x, () -> Class_1081.method_959().hashCode())));
      return Text.empty().append("Found plugins [").append(Text.literal(String.valueOf(var2.size())).formatted(Formatting.GRAY)).append("]: ").append(var6);
   }
}
