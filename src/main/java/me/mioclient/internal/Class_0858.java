package me.mioclient.internal;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0046;
import me.mioclient.enum_.Class_1072;
import me.mioclient.event.Event_18;
import me.mioclient.event.Subscribe;
import me.mioclient.record.Class_0702;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class Class_0858 extends Command {
   public boolean field_1229;
   public String field_2767;
   public Class_1072 field_2768;

   public Class_0858() {
      super("macro");
      field_4220.method_14(this);
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(this.method_9("delete", var0 -> var0.executes(var0x -> {
                  Class_0260 var1x = (Class_0260)var0x.getArgument("macro", Class_0260.class);
                  Hub.field_2607.method_9(var1x);
                  ChatUtil.method_2(Text.literal(FontRenderer.method_3(var1x.getName())).append(" has been deleted"), ChatUtil.method_38(-1));
                  return 1;
               })))).then(this.method_9("commands", this::method_2)))
            .then(
               LiteralArgumentBuilder.<CommandSource>literal("new")
                  .then(
                     RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.word())
                        .then(
                           RequiredArgumentBuilder.<CommandSource, Class_1072>argument("type", new Class_0699<>(Class_1072.class, "Macro"))
                                 .then(
                                    ((RequiredArgumentBuilder<CommandSource, ?>)RequiredArgumentBuilder.<CommandSource, Class_0702>argument("key", new Class_1189())
                                          .then(this.method_798().executes(var0 -> {
                                             String var1x = (String)var0.getArgument("name", String.class);
                                             String var2 = (String)var0.getArgument("command", String.class);
                                             Class_1072 var3 = (Class_1072)var0.getArgument("type", Class_1072.class);
                                             Class_0702 var4 = (Class_0702)var0.getArgument("key", Class_0702.class);
                                             Class_0260 var5 = var3.method_2(var1x, var4);
                                             var5.method_9().add(var2);
                                             Hub.field_2607.method_2(var5);
                                             ChatUtil.method_2(
                                                Text.literal("Macro ").append(var5.method_15()).append(" has been created"), ChatUtil.method_38(-1)
                                             );
                                             return 1;
                                          })))
                                       .executes(var0 -> {
                                          String var1x = (String)var0.getArgument("name", String.class);
                                          Class_1072 var2 = (Class_1072)var0.getArgument("type", Class_1072.class);
                                          Class_0702 var3 = (Class_0702)var0.getArgument("key", Class_0702.class);
                                          Class_0260 var4 = var2.method_2(var1x, var3);
                                          Hub.field_2607.method_2(var4);
                                          ChatUtil.method_2(
                                             Text.literal("Macro ").append(var4.method_15()).append(" has been created"), ChatUtil.method_38(-1)
                                          );
                                          return 1;
                                       })
                                 ))
                              .executes(var1x -> {
                                 this.field_2767 = (String)var1x.getArgument("name", String.class);
                                 this.field_2768 = (Class_1072)var1x.getArgument("type", Class_1072.class);
                                 this.field_1229 = true;
                                 ChatUtil.method_2(Text.literal("Press a key"), ChatUtil.method_38(-1));
                                 return 1;
                              })
                        )
                  )
            )
         .then(LiteralArgumentBuilder.<CommandSource>literal("list").executes(var0 -> {
            int var1x = 0;
            ChatUtil.method_2(Text.literal("Macro list:"), ChatUtil.method_38(-1));

            for (Class_0260 var3 : (List<Class_0260>)Hub.field_2607.getRegistry()) {
               String var4 = "%s (%s)".formatted(var3.getName(), var3.getKeybind().method_4());
               ChatUtil.method_2(Text.literal(var4), ChatUtil.method_38(var1x));
               var1x++;
            }

            return 0;
         }));
   }

   public void method_2(RequiredArgumentBuilder<CommandSource, Class_0260> var1) {
      ((RequiredArgumentBuilder<CommandSource, ?>)((RequiredArgumentBuilder<CommandSource, ?>)((RequiredArgumentBuilder<CommandSource, ?>)((RequiredArgumentBuilder<CommandSource, ?>)var1.then(
                     LiteralArgumentBuilder.<CommandSource>literal("add").then(this.method_798().executes(var0 -> {
                        Class_0260 var1x = (Class_0260)var0.getArgument("macro", Class_0260.class);
                        String var2 = (String)var0.getArgument("command", String.class);
                        if (var1x.method_2() == Class_1072.HOLD && var1x.method_9().size() >= 2) {
                           String var3 = new TextBuilder()
                              .method_2(String.valueOf(Formatting.RED))
                              .method_9("\u0001You've reached the command limit of Hold Macro");
                           ChatUtil.method_2(Text.literal(var3), ChatUtil.method_38(-1));
                           return 1;
                        } else {
                           var1x.method_9().add(var2);
                           return 1;
                        }
                     }))
                  ))
                  .then(
                     LiteralArgumentBuilder.<CommandSource>literal("remove")
                        .then(
                           RequiredArgumentBuilder.<CommandSource, Integer>argument("index", IntegerArgumentType.integer(1))
                              .executes(
                                 var1x -> {
                                    Class_0260 var2 = (Class_0260)var1x.getArgument("macro", Class_0260.class);
                                    int var3 = (Integer)var1x.getArgument("index", Integer.class) - 1;
                                    if (var3 < var2.method_9().size() && var3 >= 0 && !var2.method_9().isEmpty()) {
                                       var2.method_9().remove(var3);
                                       return 1;
                                    } else {
                                       String var4 = new TextBuilder()
                                          .method_2(this.method_2(var2))
                                          .method_2(String.valueOf(Formatting.WHITE))
                                          .method_2(String.valueOf(Formatting.RED))
                                          .method_9("\u0001Invalid index\u0001\n\u0001");
                                       ChatUtil.method_2(Text.literal(var4), ChatUtil.method_38(-1));
                                       return 1;
                                    }
                                 }
                              )
                        )
                  ))
               .then(LiteralArgumentBuilder.<CommandSource>literal("clear").executes(var0 -> {
                  Class_0260 var1x = (Class_0260)var0.getArgument("macro", Class_0260.class);
                  var1x.method_9().clear();
                  return 1;
               })))
            .then(
               LiteralArgumentBuilder.<CommandSource>literal("edit")
                  .then(
                     RequiredArgumentBuilder.<CommandSource, Integer>argument("index", IntegerArgumentType.integer(1))
                        .then(
                           this.method_798()
                              .executes(
                                 var1x -> {
                                    Class_0260 var2 = (Class_0260)var1x.getArgument("macro", Class_0260.class);
                                    String var3 = (String)var1x.getArgument("command", String.class);
                                    int var4 = (Integer)var1x.getArgument("index", Integer.class) - 1;
                                    if (var4 > var2.method_9().size()) {
                                       String var5 = new TextBuilder()
                                          .method_2(this.method_2(var2))
                                          .method_2(String.valueOf(Formatting.WHITE))
                                          .method_2(String.valueOf(Formatting.RED))
                                          .method_9("\u0001Invalid index\u0001\n\u0001");
                                       ChatUtil.method_2(Text.literal(var5), ChatUtil.method_38(-1));
                                       return 1;
                                    } else {
                                       var2.method_9().set(var4, var3);
                                       return 1;
                                    }
                                 }
                              )
                        )
                  )
            ))
         .executes(var1x -> {
            Class_0260 var2 = (Class_0260)var1x.getArgument("macro", Class_0260.class);
            ChatUtil.method_2(Text.literal(this.method_2(var2)), ChatUtil.method_38(-1));
            return 1;
         });
   }

   public LiteralArgumentBuilder<CommandSource> method_9(String var1, Consumer<RequiredArgumentBuilder<CommandSource, Class_0260>> var2) {
      RequiredArgumentBuilder var3 = RequiredArgumentBuilder.argument("macro", new Class_0874());
      var2.accept(var3);
      return (LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal(var1).then(var3);
   }

   public String method_2(Class_0260 var1) {
      StringBuffer var2 = new StringBuffer(new TextBuilder().method_2(var1.getName()).method_9("\u0001's command list: \n"));

      for (int var3 = 0; var3 < var1.method_9().size(); var3++) {
         var2.append(var3 + 1);
         var2.append(". ");
         var2.append(var1.method_9().get(var3));
         if (var3 != var1.method_9().size() - 1) {
            var2.append("\n");
         }
      }

      return var2.toString();
   }

   public RequiredArgumentBuilder<CommandSource, String> method_798() {
      return RequiredArgumentBuilder.<CommandSource, String>argument("command", StringArgumentType.greedyString()).suggests(this::method_5);
   }

   public CompletableFuture<Suggestions> method_5(CommandContext<CommandSource> var1, SuggestionsBuilder var2) {
      try {
         int var3 = var2.getStart();
         int var4 = 0;
         StringBuilder var5 = new StringBuilder();

         for (char var9 : var2.getRemaining().toCharArray()) {
            var4++;
            if (var9 != CommandManager.method_928() && (var4 != 1 || var9 != ' ')) {
               var5.append(var9);
            } else {
               var3 += var4;
               var4 = 0;
               var5.setLength(0);
            }
         }

         int var11 = var3;
         return CommandManager.field_3191.getCompletionSuggestions(CommandManager.field_3191.parse(var5.toString(), null), var4).thenApply(var1x -> {
            StringRange var2x = var1x.getRange();
            var2x = new StringRange(var2x.getStart() + var11, var2x.getEnd() + var11);
            List var3x = var1x.getList().stream().map(var1xx -> {
               StringRange var2xx = new StringRange(var1xx.getRange().getStart() + var11, var1xx.getRange().getEnd() + var11);
               return new Suggestion(var2xx, var1xx.getText(), var1xx.getTooltip());
            }).toList();
            return new Suggestions(var2x, var3x);
         });
      } catch (Exception var10) {
         return var2.buildFuture();
      }
   }

   @Subscribe
   public void method_2(Event_18 var1) {
      if (this.field_1229 && this.field_2768 != null && this.field_2767 != null) {
         Class_0260 var2 = this.field_2768.method_2(this.field_2767, new Class_0702(var1.method_614(), Class_0046.TOGGLE, var1.method_615()));
         Hub.field_2607.method_2(var2);
         ChatUtil.method_2(Text.literal("Macro ").append(var2.method_15()).append(" has been created"), ChatUtil.method_38(-1));
         this.field_1229 = false;
      }
   }
}
