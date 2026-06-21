package me.mioclient.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.List;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0304;
import me.mioclient.event.Event_32;
import me.mioclient.event.Event_60;
import me.mioclient.event.Subscribe;
import me.mioclient.module.Module;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;
import nick.Commands;

public final class Class_1032 extends Class_0939<Class_0618> implements Class_1309, Class_1146 {
   public static UIModule clickgui = Hub.field_2595.method_78(UIModule.class);
   public static final CommandSource field_3190 = new ClientCommandSource(null, field_4219);
   public static final CommandDispatcher<CommandSource> field_3191 = new CommandDispatcher();
   public static String field_3192 = ";";

   public Class_1032() {
      super();
      field_4220.method_14(this);
      Commands.initialize(this);   // no-op in our renamed deobf — kept for symmetry
      MioCommands.init(this);      // replaces the above for our package layout
      this.method_929();
      this.method_931();
      this.method_930();
   }

   public static String method_927() {
      return field_3192;
   }

   public static void method_270(String var0) {
      if (!var0.isBlank()) {
         String var1 = var0.substring(0, 1);
         field_3192 = var1;
         clickgui.field_2846.method_78((String)var1);
      }
   }

   public static char method_928() {
      return field_3192.charAt(0);
   }

   public void method_929() {
      if (Hub.field_2630.method_264()) {
         this.method_2((Class_0618)(new Class_1321()));
      }
   }

   public void method_930() {
      for (Module var2 : (Iterable<Module>)(Iterable<?>)((List<me.mioclient.module.Module>)Hub.field_2599.getRegistry())) {
         if (!this.method_2(var1 -> var1.getName().equalsIgnoreCase(var2.getName())).isPresent()) {
            for (String var6 : var2.getAliases()) {
               Class_0691 var7 = new Class_0691(var6.toLowerCase());
               new Class_0023(var2).exec(var7);
               field_3191.register(var7);
            }
         }
      }
   }

   public static void method_7(String var0) {
      Event_32 var1 = new Event_32(var0);
      Class_1309.field_4220.method_36(var1);
      if (!var1.method_464()) {
         try {
            field_3191.execute(field_3191.parse(var0, field_3190));
         } catch (CommandSyntaxException var3) {
         }
      }
   }

   public void method_931() {
      this.method_2((Class_0618)(new Class_0881(Class_0304.FRIEND)));
      this.method_2((Class_0618)(new Class_0881(Class_0304.ENEMY)));
   }

   public boolean method_2(Class_0618 var1) {
      LiteralArgumentBuilder var2 = LiteralArgumentBuilder.<CommandSource>literal(var1.getName());
      var1.exec(var2);
      LiteralCommandNode var3 = field_3191.register(var2);

      for (String var7 : var1.getAliases()) {
         Class_0518 var8 = new Class_0518(var7);

         for (CommandNode var10 : (Iterable<CommandNode>)(Iterable<?>)(var3.getChildren())) {
            var8.then(var10);
         }

         var8.executes(var1x -> var3.getCommand().run(var1x));
         field_3191.register(var8);
      }

      return this.field_3243.add(var1);
   }

   @Subscribe
   public void method_2(Event_60 var1) {
      if (method_927().length() == 1 && method_927().charAt(0) == var1.method_1167()) {
         field_4219.setScreen(new ChatScreen(""));
      }
   }

   @Override
   public JsonElement toJson() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("prefix", field_3192);
      return var1;
   }

   @Override
   public void fromJson(JsonElement var1) {
      if (var1.getAsJsonObject().has("prefix")) {
         method_270(var1.getAsJsonObject().get("prefix").getAsString());
      }
   }

   @Override
   public String getConfigName() {
      return "commands.json";
   }
}
