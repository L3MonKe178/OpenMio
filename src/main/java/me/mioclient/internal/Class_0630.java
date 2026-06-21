package me.mioclient.internal;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.List;
import java.util.function.BiConsumer;
import me.mioclient.Hub;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_21;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.ColorSetting;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.command.CommandSource;

public class Class_0630 extends Class_0618 {
   public Class_0630() {
      super("global");
      this.method_9("globalsync", "syncall");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      var1.then(LiteralArgumentBuilder.<CommandSource>literal("color").then(RequiredArgumentBuilder.<CommandSource, Boolean>argument("value", BoolArgumentType.bool()).executes(var1x -> {
         this.method_2((BiConsumer<Module, Setting<?>>)((var1xx, var2) -> {
            if (!(var1xx instanceof AbstractModule_21) && !(var1xx instanceof UIModule)) {
               if (var2 instanceof ColorSetting var3) {
                  var3.method_29((Boolean)var1x.getArgument("value", Boolean.class));
               }
            }
         }));
         return 1;
      })));
      var1.then(
         LiteralArgumentBuilder.<CommandSource>literal("linewidth")
            .then(
               RequiredArgumentBuilder.<CommandSource, Float>argument("value", FloatArgumentType.floatArg(Float.intBitsToFloat(1036831949), Float.intBitsToFloat(1084227584)))
                  .executes(var1x -> {
                     float var2 = (Float)var1x.getArgument("value", Float.class);
                     this.method_2((BiConsumer<Module, Setting<?>>)((var1xx, var2x) -> {
                        if (var2x.getValue() instanceof Float && var2x.getName().equalsIgnoreCase("LineWidth")) {
                           ((CustomSetting3)var2x).method_2(var2);
                        }
                     }));
                     return 1;
                  })
            )
      );
   }

   public void method_2(BiConsumer<Module, Setting<?>> var1) {
      for (Module var3 : (List<Module>)Hub.field_2599.getRegistry()) {
         for (Setting<?> var5 : var3.getRegistry()) {
            var1.accept(var3, var5);
         }
      }
   }
}
