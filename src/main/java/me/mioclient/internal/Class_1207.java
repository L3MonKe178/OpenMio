package me.mioclient.internal;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0200;
import me.mioclient.module.render.WaypointsModule;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.command.CommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.math.Vec3d;

public final class Class_1207 extends Class_0618 {
   public Class_1207() {
      super("waypoints");
      this.method_9("wp");
   }

   @Override
   public void exec(LiteralArgumentBuilder<CommandSource> var1) {
      ((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)((LiteralArgumentBuilder<CommandSource>)var1.then(
                  LiteralArgumentBuilder.<CommandSource>literal("add")
                     .then(
                        ((RequiredArgumentBuilder<CommandSource, ?>)RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string())
                              .then(((RequiredArgumentBuilder<CommandSource, ?>)RequiredArgumentBuilder.<CommandSource, Vec3d>argument("pos", new Class_0019()).executes(var0 -> {
                                 String var1x = (String)var0.getArgument("name", String.class);
                                 Vec3d var2 = (Vec3d)var0.getArgument("pos", Vec3d.class);
                                 String var3 = Class_1225.method_1071().method_235().toLowerCase();
                                 Class_1368 var4 = new Class_1368(var1x, var2, var3, method_1054());
                                 Hub.field_2604.method_9(var4);
                                 Class_1245.method_2(Text.literal("Waypoint ").append(var4.method_15()).append(" has been created"), -1);
                                 return 1;
                              })).then(RequiredArgumentBuilder.<CommandSource, Class_0200>argument("dimension", new Class_0699<>(Class_0200.class, "dimension")).executes(var0 -> {
                                 String var1x = (String)var0.getArgument("name", String.class);
                                 Vec3d var2 = (Vec3d)var0.getArgument("pos", Vec3d.class);
                                 Class_0200 var3 = (Class_0200)var0.getArgument("dimension", Class_0200.class);
                                 Class_1368 var4 = new Class_1368(var1x, var2, var3.method_235().toLowerCase(), method_1054());
                                 Hub.field_2604.method_9(var4);
                                 Class_1245.method_2(Text.literal("Waypoint ").append(var4.method_15()).append(" has been created"), -1);
                                 return 1;
                              }))))
                           .executes(var0 -> {
                              String var1x = (String)var0.getArgument("name", String.class);
                              String var2 = Class_1225.method_1071().method_235().toLowerCase();
                              Vec3d var3 = field_4219.player.getPos();
                              Class_1368 var4 = new Class_1368(var1x, var3, var2, method_1054());
                              Hub.field_2604.method_9(var4);
                              Class_1245.method_2(Text.literal("Waypoint ").append(var4.method_15()).append(" has been created"), -1);
                              return 1;
                           })
                     )
               ))
               .then(
                  Class_0618.method_2("remove", "delete", "del")
                     .then(
                        RequiredArgumentBuilder.<CommandSource, String>argument("server", StringArgumentType.string())
                           .suggests(Class_1207::method_38)
                           .then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string()).suggests(Class_1207::method_78).executes(var0 -> {
                              String var1x = (String)var0.getArgument("name", String.class);
                              String var2 = (String)var0.getArgument("server", String.class);
                              ((List<Class_1368>)Hub.field_2604.getRegistry())
                                 .removeIf(var2x -> var2.equalsIgnoreCase(var2x.method_106()) && var1x.equalsIgnoreCase(var2x.getName()));
                              Class_1245.method_2(Text.literal("Waypoint ").append(var1x).append(" has been removed"), -1);
                              return 1;
                           }))
                     )
               ))
            .then(
               LiteralArgumentBuilder.<CommandSource>literal("rename")
                  .then(
                     RequiredArgumentBuilder.<CommandSource, String>argument("server", StringArgumentType.string())
                        .suggests(Class_1207::method_38)
                        .then(
                           RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string())
                              .suggests(Class_1207::method_78)
                              .then(RequiredArgumentBuilder.<CommandSource, String>argument("target", StringArgumentType.string()).executes(var0 -> {
                                 String var1x = (String)var0.getArgument("name", String.class);
                                 String var2 = (String)var0.getArgument("server", String.class);
                                 String var3 = (String)var0.getArgument("target", String.class);
                                 Predicate<Class_1368> var4 = var2x -> var2.equalsIgnoreCase(var2x.method_106()) && var1x.equalsIgnoreCase(var2x.getName());
                                 Class_1368 var5 = (Class_1368)Hub.field_2604.method_2(var4).orElse(null);
                                 if (var5 == null) {
                                    Class_1245.method_2(Text.of("Waypoint not found"), -1);
                                    return 1;
                                 } else {
                                    ((List<Class_1368>)Hub.field_2604.getRegistry()).removeIf(var4);
                                    ((List<Class_1368>)Hub.field_2604.getRegistry()).add(new Class_1368(var3, var5.method_733(), var5.method_600(), var5.method_106()));
                                    MutableText var6 = Text.literal("Waypoint ");
                                    var6 = var6.append(var1x);
                                    var6 = var6.append(" has been renamed to");
                                    var6 = var6.append(var3);
                                    Class_1245.method_2(var6, -1);
                                    return 1;
                                 }
                              }))
                        )
                  )
            ))
         .then(LiteralArgumentBuilder.<CommandSource>literal("list").executes(var0 -> {
            MutableText var1x = Text.empty();
            var1x.append("Waypoints list: ");
            var1x.append(Texts.join(((List<Class_1368>)Hub.field_2604.getRegistry()).stream().map(Class_1368::method_15).toList(), Text.literal(", ")));
            Class_1245.method_2(var1x, -1);
            return 1;
         }));
      var1.then(
         LiteralArgumentBuilder.<CommandSource>literal("toggle")
            .then(
               RequiredArgumentBuilder.<CommandSource, String>argument("server", StringArgumentType.string())
                  .suggests(Class_1207::method_38)
                  .then(RequiredArgumentBuilder.<CommandSource, String>argument("name", StringArgumentType.string()).suggests(Class_1207::method_78).executes(var0 -> {
                     String var1x = (String)var0.getArgument("name", String.class);
                     String var2 = (String)var0.getArgument("server", String.class);
                     Predicate<Class_1368> var3 = var2x -> var2.equalsIgnoreCase(var2x.method_106()) && var1x.equalsIgnoreCase(var2x.getName());
                     Class_1368 var4 = (Class_1368)Hub.field_2604.method_2(var3).orElse(null);
                     if (var4 == null) {
                        Class_1245.method_2(Text.of("Waypoint not found"), -1);
                        return 1;
                     } else {
                        var4.method_38(!var4.isToggled());
                        String var5 = "Made the waypoint %s %s.".formatted(var1x, var4.isToggled() ? "visible" : "invisible");
                        Class_1245.method_2(Text.literal(var5), -1);
                        return 1;
                     }
                  }))
            )
      );
      if (Hub.field_2630.method_264()) {
         this.method_2(new Class_0393(), var1);
         this.method_2(new Class_0412(), var1);
      }
   }

   public static CompletableFuture<Suggestions> method_38(CommandContext<CommandSource> var0, SuggestionsBuilder var1) {
      return CommandSource.suggestMatching(((List<Class_1368>)Hub.field_2604.getRegistry()).stream().map(Class_1207::method_2).distinct(), var1);
   }

   public static CompletableFuture<Suggestions> method_2(CommandContext<CommandSource> var0, SuggestionsBuilder var1, String var2) {
      Stream var3 = ((List<Class_1368>)Hub.field_2604.getRegistry()).stream().filter(var1x -> var1x.method_106().equalsIgnoreCase(var2)).map(Class_1368::getName);
      return CommandSource.suggestMatching(var3, var1);
   }

   public static CompletableFuture<Suggestions> method_78(CommandContext<CommandSource> var0, SuggestionsBuilder var1) {
      String var2 = (String)var0.getArgument("server", String.class);
      return method_2(var0, var1, var2);
   }

   public static String method_2(Class_1368 var0) {
      String var1 = var0.method_106().toLowerCase();
      return var1.contains(":") ? new Class_1303().method_2((Object)var1).method_9("\"\u0001\"") : var1;
   }

   public static String method_1054() {
      ServerInfo var0 = field_4219.player.networkHandler.getServerInfo();
      if (var0 == null) {
         return "singleplayer";
      } else {
         String[] var1 = var0.address.split(":");
         return var1.length == 2 && var1[1].equalsIgnoreCase("25565") ? var1[0] : var0.address;
      }
   }

   public static Vec3d method_107(CommandContext<CommandSource> var0) {
      String var1 = (String)var0.getArgument("name", String.class);
      Class_1368 var2 = Hub.field_2604.method_2(var1x -> var1x.method_16(var1, method_1054())).orElse(null);
      return var2 == null ? null : WaypointsModule.method_4(var2);
   }
}
