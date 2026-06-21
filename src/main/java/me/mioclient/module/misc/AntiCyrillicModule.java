package me.mioclient.module.misc;

import java.util.HashMap;
import java.util.Map;
import me.mioclient.enum_.Class_0440;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_24;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_1016;
import me.mioclient.internal.Class_1303;
import me.mioclient.mixin.ducks.DuckCommandExecutionC2SPacket;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import nick.Settings;

public class AntiCyrillicModule extends Module {
   public Setting<Class_0440> field_1461;
   public static final Map<Character, String> field_1462 = new HashMap<Character, String>() {
      {
         this.put(Character.valueOf('Й'), "u'");
         this.put(Character.valueOf('й'), "u'");
         this.put(Character.valueOf('Ц'), "U,");
         this.put(Character.valueOf('ц'), "u,");
         this.put(Character.valueOf('У'), "y");
         this.put(Character.valueOf('у'), "y");
         this.put(Character.valueOf('К'), "K");
         this.put(Character.valueOf('к'), "K");
         this.put(Character.valueOf('Е'), "E");
         this.put(Character.valueOf('е'), "e");
         this.put(Character.valueOf('Н'), "H");
         this.put(Character.valueOf('н'), "H");
         this.put(Character.valueOf('Г'), "r");
         this.put(Character.valueOf('г'), "r");
         this.put(Character.valueOf('Ш'), "LLI");
         this.put(Character.valueOf('ш'), "LLI");
         this.put(Character.valueOf('Щ'), "LLI,");
         this.put(Character.valueOf('щ'), "LLI,");
         this.put(Character.valueOf('З'), "3");
         this.put(Character.valueOf('з'), "3");
         this.put(Character.valueOf('Х'), "X");
         this.put(Character.valueOf('х'), "X");
         this.put(Character.valueOf('Ъ'), "'b");
         this.put(Character.valueOf('ъ'), "'b");
         this.put(Character.valueOf('Ф'), "qp");
         this.put(Character.valueOf('ф'), "qp");
         this.put(Character.valueOf('Ы'), "bI");
         this.put(Character.valueOf('ы'), "bI");
         this.put(Character.valueOf('В'), "B");
         this.put(Character.valueOf('в'), "B");
         this.put(Character.valueOf('А'), "A");
         this.put(Character.valueOf('а'), "a");
         this.put(Character.valueOf('П'), "II");
         this.put(Character.valueOf('п'), "n");
         this.put(Character.valueOf('Р'), "P");
         this.put(Character.valueOf('р'), "p");
         this.put(Character.valueOf('О'), "O");
         this.put(Character.valueOf('о'), "o");
         this.put(Character.valueOf('Л'), "JI");
         this.put(Character.valueOf('л'), "JI");
         this.put(Character.valueOf('Д'), "D");
         this.put(Character.valueOf('д'), "D");
         this.put(Character.valueOf('Ж'), ")I(");
         this.put(Character.valueOf('ж'), ")I(");
         this.put(Character.valueOf('Э'), "3");
         this.put(Character.valueOf('э'), "3");
         this.put(Character.valueOf('Я'), "9I");
         this.put(Character.valueOf('я'), "9I");
         this.put(Character.valueOf('Ч'), "4");
         this.put(Character.valueOf('ч'), "4");
         this.put(Character.valueOf('С'), "C");
         this.put(Character.valueOf('с'), "c");
         this.put(Character.valueOf('М'), "M");
         this.put(Character.valueOf('м'), "M");
         this.put(Character.valueOf('И'), "U");
         this.put(Character.valueOf('и'), "u");
         this.put(Character.valueOf('Т'), "T");
         this.put(Character.valueOf('т'), "T");
         this.put(Character.valueOf('Ь'), "b");
         this.put(Character.valueOf('ь'), "b");
         this.put(Character.valueOf('Б'), "6");
         this.put(Character.valueOf('б'), "6");
         this.put(Character.valueOf('Ю'), "IO");
         this.put(Character.valueOf('ю'), "IO");
         this.put(Character.valueOf('Ё'), "E");
         this.put(Character.valueOf('ё'), "E");
      }
   };

   public AntiCyrillicModule() {
      super("AntiCyrillic", "Replaces cyrillic letters in your messages with latin equivalents.", Category.MISC);
      Settings.initialize(this);
   }

   @Override
   public String getInfo() {
      try {
         return Class_1016.method_3(this.field_1461.getValue());
      } catch (Exception var2) {
         return null;
      }
   }

   @Subscribe
   public void method_9(Event_24 var1) {
      if (this.field_1461.getValue() != Class_0440.COMMANDS) {
         var1.method_220(this.method_236(var1.method_219()));
      }
   }

   @Subscribe
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof CommandExecutionC2SPacket var2 && this.field_1461.getValue() != Class_0440.CHAT) {
         ((DuckCommandExecutionC2SPacket)(Object)var2).setCommand(this.method_236(var2.command()));
      }
   }

   public String method_236(String var1) {
      String var2 = var1;
      if (!var1.startsWith("/")) {
         var2 = this.method_492(var1);
      } else {
         String[] var3 = var1.split(" ");
         if (var3.length > 1) {
            StringBuilder var4 = new StringBuilder();

            for (int var5 = var3.length - 1; var5 >= 0; var5--) {
               if (var5 == 0) {
                  var4.insert(0, new Class_1303().method_2(var3[0]).method_9("\u0001 "));
               } else {
                  var4.insert(0, new Class_1303().method_2(this.method_492(var3[var5])).method_9("\u0001 "));
               }
            }

            var2 = var4.toString();
         }
      }

      if (var2.length() > 256) {
         var2 = var2.substring(0, 256);
      }

      return var2;
   }

   public String method_492(String var1) {
      String var2 = var1;
      int var3 = var1.length();

      for (int var4 = var3 - 1; var4 >= 0; var4--) {
         char var5 = var2.charAt(var4);
         if (field_1462.containsKey(var5)) {
            var2 = new Class_1303()
               .method_2(var2.substring(var4 + 1))
               .method_2(field_1462.get(var5))
               .method_2(var2.substring(0, var4))
               .method_9("\u0001\u0001\u0001");
         }
      }

      return var2;
   }
}
