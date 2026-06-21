package me.mioclient.internal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.module.Module;
import me.mioclient.record.Class_0702;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

public final class Class_0076 extends Class_0618 {
   public Class_0076() {
      super("bind");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)var1.then(
            LiteralArgumentBuilder.<CommandSource>literal("set")
               .then(
                  RequiredArgumentBuilder.<CommandSource, Module>argument("module", new Class_0484())
                     .then(
                        RequiredArgumentBuilder.<CommandSource, Class_0702>argument("key", new Class_1189())
                           .executes(
                              var1x -> {
                                 Module var2 = (Module)var1x.getArgument("module", Module.class);
                                 Class_0702 var3 = (Class_0702)var1x.getArgument("key", Class_0702.class);
                                 var2.modifyKeybind(var1xx -> var1xx.method_9(var3.method_38()).method_9(var3.method_39()));
                                 Class_1245.method_2(
                                    Text.literal("Bind for ")
                                       .append(this.method_5(var2.getName()))
                                       .append(" has been set to ")
                                       .append(this.method_5(Class_0018.method_2(var3.method_38(), var3.method_39()))),
                                    Class_1245.method_38(-1)
                                 );
                                 return 1;
                              }
                           )
                     )
               )
         ))
         .then(
            ((LiteralArgumentBuilder<CommandSource>)LiteralArgumentBuilder.<CommandSource>literal("clear")
                  .then(RequiredArgumentBuilder.<CommandSource, Module>argument("module", new Class_0484()).executes(var1x -> {
                     Module var2 = (Module)var1x.getArgument("module", Module.class);
                     var2.modifyKeybind(var0 -> var0.method_9(-1));
                     Class_1245.method_2(Text.literal("Bind for ").append(this.method_5(var2.getName())).append(" has been reset"), Class_1245.method_38(-1));
                     return 1;
                  })))
               .then(LiteralArgumentBuilder.<CommandSource>literal("all").executes(var0 -> {
                  for (Module var2 : (List<Module>)Hub.field_2599.getRegistry()) {
                     var2.modifyKeybind(var0x -> var0x.method_9(-1));
                  }

                  Class_1245.method_2(Text.literal("Bind for all modules has been reset"), Class_1245.method_38(-1));
                  return 1;
               }))
         );
   }
}
