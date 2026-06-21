package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import me.mioclient.Hub;
import me.mioclient.deobf.Named;
import me.mioclient.enum_.Class_0836;
import me.mioclient.enum_.Priority;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.record.Class_0371;
import me.mioclient.record.Class_0702;
import net.minecraft.command.CommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;

public final class Class_0355 extends Class_0618 {
   public Class_0355() {
      super("preset");
      this.method_9("config", "cfg");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(
               RequiredArgumentBuilder.<CommandSource, Class_0836>argument(
                                    "category", new Class_0798()
                                 )
                                 .then(
                                    LiteralArgumentBuilder.<CommandSource>literal("save")
                                       .then(
                                          RequiredArgumentBuilder.<CommandSource, String>argument("preset", StringArgumentType.string())
                                             .executes(
                                                var0 -> {
                                                   String var1x = (String)var0.getArgument("preset", String.class);
                                                   Class_0852 var2 = Class_0798.getManager(var0, "category");

                                                   try {
                                                      var2.method_494(var1x);
                                                   } catch (IOException var4) {
                                                      var4.printStackTrace();
                                                   }

                                                   Class_1245.method_2(
                                                      Text.literal(new Class_1303().method_2((Object)var1x).method_9("Preset \u0001 has been saved.")),
                                                      Class_1245.method_38(-1)
                                                   );
                                                   return 1;
                                                }
                                             )
                                       )
                                 ))
                              .then(
                                 LiteralArgumentBuilder.<CommandSource>literal("delete")
                                    .then(
                                       RequiredArgumentBuilder.<CommandSource, String>argument("preset", new Class_0897())
                                          .executes(
                                             var0 -> {
                                                Class_0371 var1x = Class_0897.getPreset(var0, "category", "preset");
                                                Class_0852 var2 = Class_0798.getManager(var0, "category");
                                                var2.method_493(var1x.method_228());
                                                Class_1245.method_2(
                                                   Text.literal(new Class_1303().method_2(var1x.method_228()).method_9("Preset \u0001 has been deleted.")),
                                                   Class_1245.method_38(-1)
                                                );
                                                return 1;
                                             }
                                          )
                                    )
                              ))
                           .then(
                              LiteralArgumentBuilder.<CommandSource>literal("rename")
                                 .then(
                                    RequiredArgumentBuilder.<CommandSource, String>argument("preset", new Class_0897())
                                       .then(
                                          RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string())
                                             .executes(
                                                var0 -> {
                                                   Class_0371 var1x = Class_0897.getPreset(var0, "category", "preset");
                                                   Class_0852 var2 = Class_0798.getManager(var0, "category");
                                                   var2.method_39(var1x.method_228(), (String)var0.getArgument("name", String.class));
                                                   Class_1245.method_2(
                                                      Text.literal(
                                                         new Class_1303()
                                                            .method_2((String)var0.getArgument("name", String.class))
                                                            .method_2(var1x.method_228())
                                                            .method_9("The name of \u0001 has been set to \u0001.")
                                                      ),
                                                      Class_1245.method_38(-1)
                                                   );
                                                   return 1;
                                                }
                                             )
                                       )
                                 )
                           ))
                        .then(
                           LiteralArgumentBuilder.<CommandSource>literal("load")
                              .then(
                                 RequiredArgumentBuilder.<CommandSource, String>argument("preset", new Class_0897())
                                    .executes(
                                       var1x -> {
                                          Hub.field_2597.method_1174().method_357();
                                          Class_0836 var2 = (Class_0836)var1x.getArgument("category", Class_0836.class);
                                          Class_0371 var3 = Class_0897.getPreset(var1x, "category", "preset");
                                          Class_0852 var4 = Class_0798.getManager(var1x, "category");
                                          var4.method_56();
                                          var4.method_465(var3.method_228());
                                          Class_1245.method_2(
                                             Text.literal(new Class_1303().method_2(var3.method_228()).method_9("Preset \u0001 has been loaded.")),
                                             Class_1245.method_38(-1)
                                          );
                                          if (var2 == Class_0836.ALL) {
                                             this.method_2(this::method_393);
                                          }

                                          return 1;
                                       }
                                    )
                              )
                        )
                     .then(
                        LiteralArgumentBuilder.<CommandSource>literal("specific")
                           .then(
                              (RequiredArgumentBuilder.<CommandSource, String>argument("preset", new Class_0897())
                                    .then(
                                       RequiredArgumentBuilder.<CommandSource, Category>argument("module_category", new Class_0699<Category>(Category.class, "Category"))
                                          .executes(
                                             var1x -> {
                                                Hub.field_2597.method_1174().method_357();
                                                Class_0371 var2 = Class_0897.getPreset(var1x, "category", "preset");
                                                Class_0852 var3 = Class_0798.getManager(var1x, "category");
                                                Category var4 = (Category)var1x.getArgument("module_category", Category.class);
                                                var3.method_56();
                                                this.method_2(var2, var1xx -> var1xx.getCategory() == var4);
                                                Class_1245.method_2(
                                                   Text.literal(
                                                      new Class_1303()
                                                         .method_2(var4.getName())
                                                         .method_2(var2.method_228())
                                                         .method_9("Preset \u0001 for \u0001 has been loaded.")
                                                   ),
                                                   Class_1245.method_38(-1)
                                                );
                                                return 1;
                                             }
                                          )
                                    ))
                                 .then(
                                    RequiredArgumentBuilder.argument("modules", new Class_0232(new Class_0484()))
                                       .executes(
                                          var1x -> {
                                             Hub.field_2597.method_1174().method_357();
                                             Class_0371 var2 = Class_0897.getPreset(var1x, "category", "preset");
                                             Class_0852 var3 = Class_0798.getManager(var1x, "category");
                                             Set<Class<?>> var4 = ((List<?>)var1x.getArgument("modules", List.class))
                                                .stream()
                                                .map(Object::getClass)
                                                .collect(Collectors.toSet());
                                             var3.method_56();
                                             Predicate var5 = var1xx -> var4.contains(var1xx.getClass());
                                             this.method_2(var2, var5);
                                             Class_1245.method_2(
                                                Text.literal(
                                                   new Class_1303()
                                                      .method_2(this.method_4(var5))
                                                      .method_2(var2.method_228())
                                                      .method_9("Preset \u0001 for \u0001 has been loaded.")
                                                ),
                                                Class_1245.method_38(-1)
                                             );
                                             return 1;
                                          }
                                       )
                                 )
                           )
                     )
                  .then(
                     LiteralArgumentBuilder.<CommandSource>literal("list")
                        .executes(
                           var0 -> {
                              Class_0852 var1x = Class_0798.getManager(var0, "category");
                              Class_1245.method_2(
                                 Text.literal("Preset list: ").append(Texts.join(var1x.getRegistry(), var0x -> Text.literal(var0x.method_228()))).append("."),
                                 Class_1245.method_38(-1)
                              );
                              return 1;
                           }
                        )
                  )
            .then(
               LiteralArgumentBuilder.<CommandSource>literal("export")
                  .then(
                     RequiredArgumentBuilder.<CommandSource, Module>argument("module", new Class_0484())
                        .executes(
                           var0 -> {
                              Module var1x = (Module)var0.getArgument("module", Module.class);
                              JsonObject var2 = var1x.toJson().getAsJsonObject();
                              var2.remove("bind");
                              var2.remove("toggled");
                              var2.remove("key");
                              field_4219.keyboard.setClipboard(Base64.getEncoder().encodeToString(var2.toString().getBytes(StandardCharsets.UTF_8)));
                              Class_1245.method_2(
                                 Text.literal("Successfully copied ")
                                    .append(Text.literal(var1x.getName()).formatted(Formatting.GRAY))
                                    .append(" config to your clipboard"),
                                 Class_1245.method_38(-1)
                              );
                              return 1;
                           }
                        )
                  )
            )
         .then(
            LiteralArgumentBuilder.<CommandSource>literal("import")
               .then(
                  RequiredArgumentBuilder.<CommandSource, Module>argument("module", new Class_0484())
                     .executes(
                        var0 -> {
                           Module var1x = (Module)var0.getArgument("module", Module.class);

                           try {
                              String var2 = new String(Base64.getDecoder().decode(field_4219.keyboard.getClipboard().trim().replace("\n", "")));
                              var1x.fromJson(JsonParser.parseString(var2));
                              Class_1245.method_2(
                                 Text.literal("Successfully loaded ")
                                    .append(Text.literal(var1x.getName()).formatted(Formatting.GRAY))
                                    .append(" config from your clipboard"),
                                 Class_1245.method_38(-1)
                              );
                           } catch (Exception var4) {
                              MutableText var3 = Text.literal("Failed to load ")
                                 .append(Text.literal(var1x.getName()).formatted(Formatting.GRAY))
                                 .append(" config");
                              if (var4.getStackTrace()[0].toString().contains("Base64")) {
                                 var3 = var3.append(" due to invalid input");
                              } else {
                                 var3 = var3.append(" due to an unknown reason");
                              }

                              Class_1245.method_2(var3, Class_1245.method_38(-1), Priority.MID);
                              var4.printStackTrace();
                           }

                           return 1;
                        }
                     )
               )
         );
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("restore").executes(var0 -> {
         JsonElement var1x = Hub.field_2597.method_1174().method_358();
         if (var1x == null) {
            return 1;
         } else {
            Class_0836.ALL.fromJson(var1x);
            Class_1245.method_2(Text.literal("Restored previous preset."), Class_1245.method_38(-1));
            return 1;
         }
      }));
      var1.executes(var1x -> {
         this.method_2(() -> field_4219.setScreen(new Class_0311()));
         return 1;
      });
   }

   public void method_2(Class_0371 var1, Predicate<Module> var2) {
      for (Module var4 : (List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()) {
         if (!(var4 instanceof AbstractModule_26) && var2.test(var4)) {
            try {
               var4.fromJson(var1.method_415().getAsJsonObject().get(var4.getConfigName()));
            } catch (Exception var6) {
            }
         }
      }
   }

   public String method_4(Predicate<Module> var1) {
      return ((List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()).stream().filter(var1).map(Named::getName).collect(Collectors.joining(", "));
   }

   public void method_393() {
      MutableText var1 = Text.empty();
      var1.append("Preset binds: ");

      for (Module var3 : (List<me.mioclient.module.Module>)Hub.field_2599.getRegistry()) {
         Class_0702 var4 = var3.getKeybind();
         if (!var4.method_29()) {
            var1.append("\n");
            var1.append(var3.getName());
            var1.append(" - ");
            var1.append(var4.method_4());
            var1.append(" ");
            var1.append(Class_1245.method_185(var4.method_78().getName()));
         }
      }

      Class_1245.method_2(var1, -2);
   }
}
