package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.mioclient.enum_.Priority;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Class_0557 extends Class_1344 {
   public Class_0557() {
      super("playtime");
      this.method_9("pt");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(
         RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.greedyString())
            .executes(
               var1x -> {
                  String var2 = (String)var1x.getArgument("name", String.class);
                  Class_1245.method_2(Text.of("Fetching data..."), Class_1245.method_22(this));
                  this.method_29("playtime", var2)
                     .whenComplete(
                        (var2x, var3) -> {
                           Object var4 = null;
                           if (var3 != null) {
                              var4 = var3.getMessage();
                           } else {
                              try {
                                 if (var2x.has("playtimeSeconds")) {
                                    if (var2x.get("playtimeSeconds").isJsonNull()) {
                                       throw new RuntimeException("No data for player.");
                                    }

                                    int var5 = var2x.get("playtimeSeconds").getAsInt();
                                    StringBuilder var6 = new StringBuilder(var2);
                                    var6.append(" has played for ");
                                    var6.append(Class_1344.method_105(var5));
                                    var6.append(".");
                                    Class_1245.method_2(Text.of(var6.toString()), Class_1245.method_22(this));
                                    return;
                                 }

                                 var4 = "Response data doesn't have a required field: playtimeSeconds.";
                              } catch (Exception var7) {
                                 var4 = var7.getMessage();
                              }
                           }

                           if (var4 != null) {
                              Class_1245.method_2(
                                 Text.of(new Class_1303().method_2(var4).method_2(String.valueOf(Formatting.RED)).method_9("\u0001\u0001")),
                                 Class_1245.method_22(this),
                                 Priority.MID
                              );
                           }
                        }
                     );
                  return 1;
               }
            )
      );
   }
}
