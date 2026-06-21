package me.mioclient.internal;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.awt.Color;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import me.mioclient.api.Class_0078;
import me.mioclient.deobf.Named;
import me.mioclient.enum_.Priority;
import me.mioclient.module.Module;
import me.mioclient.setting.Class0211Setting;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.CustomSetting2;
import me.mioclient.setting.Setting;
import net.minecraft.command.CommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.ClickEvent.Action;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

public final class Class_0023 extends Class_0618 {
   public final Module field_34;

   public Class_0023(Module var1) {
      super(var1.getName());
      this.field_34 = var1;
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(
                  RequiredArgumentBuilder.<CommandSource, Boolean>argument("state", BoolArgumentType.bool()).suggests((var0, var1x) -> var1x.buildFuture()).executes(var1x -> {
                     this.field_34.method_38((Boolean)var1x.getArgument("state", Boolean.class));
                     return 1;
                  })
               ))
               .then(
                  ((RequiredArgumentBuilder<CommandSource, ?>)((RequiredArgumentBuilder<CommandSource, ?>)((RequiredArgumentBuilder<CommandSource, ?>)RequiredArgumentBuilder.<CommandSource, String>argument(
                                 "setting", new Class_1024(this.field_34)
                              )
                              .then(
                                 Class_0618.method_2("clear")
                                    .suggests(this::method_29)
                                    .executes(
                                       var1x -> {
                                          Setting var2 = Class_1024.getOption(var1x, this.field_34, "setting");
                                          if (var2 instanceof CustomSetting2 var3) {
                                             ((Set)var3.getValue()).clear();
                                             Class_1245.method_2(
                                                Text.literal(this.field_34.getName())
                                                   .append(Text.literal(" ").append(var2.getName()).styled(var0 -> var0.withFormatting(Formatting.GRAY)))
                                                   .append(": Cleared"),
                                                Class_1245.method_2(this.field_34),
                                                Priority.LOW
                                             );
                                          }

                                          return 1;
                                       }
                                    )
                              ))
                           .then(
                              RequiredArgumentBuilder.<CommandSource, String>argument("value", new Class_1233(this.field_34, "setting"))
                                 .executes(
                                    var1x -> {
                                       Setting var2 = Class_1024.getOption(var1x, this.field_34, "setting");
                                       String var3 = (String)var1x.getArgument("value", String.class);

                                       try {
                                          Formatting var4 = null;
                                          if (var2 instanceof Class_0199 var5) {
                                             Class_0078 var6 = Class_0078.method_2(var5.method_231());
                                             Collection<String> var7 = var6.method_114(var3);
                                             if (!var7.isEmpty()) {
                                                if (var7.stream().anyMatch(var5::method_16)) {
                                                   var7.forEach(var5::method_30);
                                                   var4 = Formatting.RED;
                                                } else {
                                                   var7.forEach(var5::method_39);
                                                   var4 = Formatting.GREEN;
                                                }
                                             }
                                          }

                                          if (var4 == null) {
                                             var2.method_78(var3);
                                          }

                                          Object var9 = this.method_9(var2);
                                          if (var2 instanceof CustomSetting2 var10) {
                                             if (var4 == null) {
                                                var4 = var10.method_16(var3) ? Formatting.GREEN : Formatting.RED;
                                             }

                                             Formatting var11 = var4;
                                             var9 = Text.literal(var3).styled(var1xx -> var1xx.withFormatting(var11));
                                          }

                                          Class_1245.method_2(
                                             Text.literal(this.field_34.getName())
                                                .append(Text.literal(" ").append(var2.getName()).styled(var0 -> var0.withFormatting(Formatting.GRAY)))
                                                .append(": ")
                                                .append((Text)var9),
                                             Class_1245.method_2(this.field_34),
                                             Priority.LOW
                                          );
                                       } catch (Throwable var8) {
                                          Class_1245.method_2(Text.literal("Invalid value: ").append(var3), Class_1245.method_2(this.field_34), Priority.HIGH);
                                       }

                                       return 1;
                                    }
                                 )
                           ))
                        .then(LiteralArgumentBuilder.<CommandSource>literal("reset").executes(var1x -> {
                           Setting var2 = Class_1024.getOption(var1x, this.field_34, "setting");
                           var2.reset();
                           return 1;
                        })))
                     .executes(
                        var1x -> {
                           Setting var2 = Class_1024.getOption(var1x, this.field_34, "setting");
                           Class_1245.method_2(
                              Text.literal(this.field_34.getName())
                                 .append(Text.literal(" ").append(var2.getName()).styled(var0 -> var0.withFormatting(Formatting.GRAY)))
                                 .append(": ")
                                 .append(this.method_9(var2)),
                              Class_1245.method_2(this.field_34)
                           );
                           return 1;
                        }
                     )
               ))
            .then(
               ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("Enabled")
                     .then(RequiredArgumentBuilder.<CommandSource, Boolean>argument("state", BoolArgumentType.bool()).executes(var1x -> {
                        this.field_34.method_38((Boolean)var1x.getArgument("state", Boolean.class));
                        return 1;
                     })))
                  .executes(
                     var1x -> {
                        Class_1245.method_2(
                           Text.literal("Module ")
                              .append(Text.literal(this.field_34.getName()).styled(var0 -> var0.withFormatting(Formatting.GRAY)))
                              .append(" is ")
                              .append(
                                 Text.literal(this.field_34.isToggled() ? "enabled" : "disabled")
                                    .styled(
                                       var1xx -> this.field_34.isToggled() ? var1xx.withFormatting(Formatting.GREEN) : var1xx.withFormatting(Formatting.RED)
                                    )
                              ),
                           Class_1245.method_2(this.field_34)
                        );
                        return 1;
                     }
                  )
            ))
         .executes(
            var1x -> {
               MutableText var2 = Text.empty();
               MutableText var3 = this.method_28();
               var2.append(var3).append(" [%s - %s]".formatted(this.field_34.getKeybind().method_4(), this.field_34.getKeybind().method_78().getName()));

               for (Object var5 : this.field_34.getRegistry()) {
                  MutableText var6 = Text.literal(new Class_1303().method_2(((Named)var5).getName()).method_9("\n\u0001: "));
                  Objects.requireNonNull(var5);
                  switch (var5) {
                     case CustomSetting var9:
                        var6.append(Text.literal(Class_1016.method_3(((Enum)var9.getValue()).name())).formatted(Formatting.GRAY));
                        break;
                     case CustomSetting2 var10:
                        var6.append(Text.literal(String.join(", ", var10.method_251())).formatted(Formatting.GRAY));
                        break;
                     case ColorSetting var11:
                        String var12 = this.method_2(var11);
                        var6.append(Text.literal(var12).styled(var1xx -> var1xx.withColor(var11.method_465().hashCode())));
                        break;
                     default:
                        var6.append(Text.literal(((Setting)var5).getValue().toString()).formatted(Formatting.GRAY));
                  }

                  String var7 = new Class_1303()
                     .method_2(((Named)var5).getName())
                     .method_2(this.field_34.getName().toLowerCase())
                     .method_2(Class_1032.method_927())
                     .method_9("\u0001\u0001 \u0001 ");
                  var6.styled(var1xx -> var1xx.withClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, var7)));
                  var2.append(var6);
               }

               Class_1245.method_2(var2, Class_1245.method_38(-1));
               return 1;
            }
         );
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("reset").executes(var1x -> {
         for (Setting var3 : this.field_34.getRegistry()) {
            var3.reset();
         }

         return 1;
      }));
   }

   @NotNull
   public MutableText method_28() {
      MutableText var1 = Text.literal(this.field_34.getName());
      var1.styled(var1x -> Class_1245.method_2(var1x, () -> this.field_34.isToggled() ? Formatting.GREEN.getColorValue() : Formatting.RED.getColorValue()));
      var1.styled(
         var1x -> var1x.withHoverEvent(
               new HoverEvent(net.minecraft.text.HoverEvent.Action.SHOW_TEXT, Text.literal(this.field_34.getDescription().split("\n")[0]))
            )
      );
      var1.styled(
         var1x -> var1x.withClickEvent(
               new ClickEvent(
                  Action.RUN_COMMAND, new Class_1303().method_2(this.field_34.getName()).method_2(Class_1032.method_927()).method_9("\u0001toggle \u0001")
               )
            )
      );
      return var1;
   }

   public String method_2(ColorSetting var1) {
      Color var2 = var1.method_465();
      return var1.field_3119
         ? "rgb(%d, %d, %d)".formatted(var2.getRed(), var2.getGreen(), var2.getBlue())
         : "rgba(%d, %d, %d, %d)".formatted(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha());
   }

   public CompletableFuture<Suggestions> method_29(CommandContext<?> var1, SuggestionsBuilder var2) {
      try {
         Setting var3 = Class_1024.getOption(var1, this.field_34, "setting");
         if (var3 instanceof CustomSetting2) {
            return CommandSource.suggestMatching(new String[]{"clear"}, var2);
         }
      } catch (CommandSyntaxException var4) {
      }

      return Suggestions.empty();
   }

   public Text method_9(Setting<?> var1) {
      MutableText var2 = Text.literal(var1.getValue().toString());
      if (var1 instanceof ColorSetting var3) {
         var2 = Text.literal(Class_1081.method_2(var3.method_465(), true)).styled(var1x -> var1x.withColor(var1.getValue().hashCode()));
      } else if (var1 instanceof CustomSetting2 var4) {
         var2 = Texts.join(var4.method_251(), Text.literal(", ").styled(var0 -> var0.withFormatting(Formatting.GRAY)), Text::literal);
      } else if (var1 instanceof Class0211Setting var5) {
         var2 = Text.literal(((Class_0211)var5.getValue()).getName());
      }

      return var2;
   }
}
