package me.mioclient.internal;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_1309;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.module.abstract_.AbstractModule_26;
import me.mioclient.module.client.HUDModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class Class_0406 extends Class_1117 implements Class_1309 {
   public Class_1015<?> field_1306 = null;
   public static final int[] field_1307 = new int[]{265, 265, 264, 264, 263, 262, 263, 262, 66, 65};
   public int current = 0;
   public boolean field_1308;
   public boolean field_1309;
   public TextFieldWidget field_1310;
   public final Class_0242 field_1311 = new Class_0242();
   public String field_1312;
   public List<Class_0050> field_1313 = new ArrayList<>();

   public Class_0406() {
      super();
      this.field_1308 = Class_1328.field_4289.toFile().exists();
      int var1 = 10;

      for (Category var5 : Category.values()) {
         if (var5 != Category.HUD) {
            Class_0967 var6 = new Class_0967(var5);
            var6.setX(var1);
            this.field_3462.add(var6);
            var1 += var6.method_216() + 3;
         }
      }

      if (this.field_1308) {
         Class_0185 var7 = new Class_0185();
         var7.setX(var1);
         var7.method_80(false);
         this.field_3462.add(var7);
      }
   }

   @Override
   public void init() {
      super.init();
      this.field_1312 = "";
      this.field_1310 = new TextFieldWidget(field_4219.textRenderer, 7, -100, 100, 9, Text.literal("Search"));
      this.field_1310.setVisible(true);
      this.field_1310.setEditable(true);
      this.field_1310.setMaxLength(64);
      this.field_1310
         .setChangedListener(
            var1 -> {
               if (!var1.isEmpty()) {
                  for (Module var3 : Hub.field_2599.getRegistry()) {
                     if (!(var3 instanceof HUDModule)
                        && !(var3 instanceof AbstractModule_26)
                        && var3.getName().toLowerCase(Locale.ROOT).startsWith(var1.toLowerCase(Locale.ROOT))) {
                        this.field_1312 = var3.getName().substring(this.field_1310.getCursor());
                        return;
                     }
                  }
               }

               this.field_1312 = "";
            }
         );
      this.addDrawableChild(this.field_1310);
   }

   @Override
   public void method_2(DrawContext var1, int var2, int var3, float var4) {
      if (this.field_1313.size() >= 3) {
         this.field_1313.getFirst().field_111 = false;
      }

      this.field_1313.removeIf(Class_0050::isClosed);
      if (this.field_1310.isFocused()) {
         Class_1016 var10000 = Class_1016.field_3143;
         String var10001 = this.field_1310.getText();
         String var10002 = this.field_1312;
         float var5 = var10000.method_221(new Class_1303().method_2((Object)var10002).method_2((Object)var10001).method_9("\u0001\u0001_"));
         var10000 = Class_1016.field_3143;
         var10002 = this.field_1310.getText();
         String var10003 = String.valueOf(Formatting.GRAY);
         String var10004 = this.field_1312;
         var10000.method_9(
            var1,
            new Class_1303()
               .method_2(this.method_453())
               .method_2(String.valueOf(Formatting.WHITE))
               .method_2((Object)var10004)
               .method_2((Object)var10003)
               .method_2((Object)var10002)
               .method_9("\u0001\u0001\u0001\u0001\u0001"),
            (float)field_4219.getWindow().getScaledWidth() / Float.intBitsToFloat(1073741824) - var5 / Float.intBitsToFloat(1073741824),
            (float)field_4219.getWindow().getScaledHeight() / Float.intBitsToFloat(1073741824)
               - (float)Class_1016.field_3143.method_66() / Float.intBitsToFloat(1073741824),
            Color.white
         );
      } else if (Hub.field_2609.method_802() != 8) {
         String var6 = "Ctrl + F to activate search.";
         Class_1016.field_3143
            .method_9(
               var1,
               var6,
               (float)field_4219.getWindow().getScaledWidth() - Class_1016.field_3143.method_221(var6),
               (float)(field_4219.getWindow().getScaledHeight() - Class_1016.field_3143.method_66()),
               Class_1081.method_2(Color.GRAY, Color.WHITE, Double.longBitsToDouble(4656510908468559872L), Double.longBitsToDouble(4643000109586448384L))
            );
      }
   }

   @Override
   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      super.keyPressed(keyCode, scanCode, modifiers);
      if (!this.field_1308 && field_1307[this.current] == keyCode) {
         this.current++;
         if (this.current == field_1307.length) {
            try {
               Class_1328.field_4289.toFile().createNewFile();
            } catch (IOException var9) {
            }

            this.field_1308 = true;
            this.current = 0;
            Class_0185 var4 = new Class_0185();
            var4.method_80(true);
            var4.setX(field_4219.getWindow().getScaledWidth() / 2 - var4.method_216() / 2);
            var4.setY(field_4219.getWindow().getScaledHeight() / 2 - var4.method_66() / 2);
            this.field_3462.add(var4);
         }
      } else {
         this.current = 0;
      }

      switch (keyCode) {
         case 70:
            if (GLFW.glfwGetKey(field_4219.getWindow().getHandle(), 341) == 1) {
               if (this.field_1310.isFocused()) {
                  this.reset();
               } else {
                  this.setInitialFocus(this.field_1310);
               }
            }
            break;
         case 257:
            label53:
            for (Class_0746 var5 : this.field_3462) {
               for (Class_0937 var7 : var5.method_714()) {
                  if (var7 instanceof Class_0050 var8) {
                     String var11 = var8.method_65().getName();
                     String var12 = this.field_1310.getText();
                     String var13 = this.field_1312;
                     if (var11.equalsIgnoreCase(new Class_1303().method_2((Object)var13).method_2((Object)var12).method_9("\u0001\u0001"))) {
                        var8.field_111 = true;
                        var8.method_70();
                        break label53;
                     }
                  }
               }
            }

            this.reset();
            break;
         case 258:
            if (this.field_1310.isFocused()) {
               TextFieldWidget var10000 = this.field_1310;
               String var10001 = this.field_1310.getText();
               String var10002 = this.field_1312;
               var10000.setText(new Class_1303().method_2((Object)var10002).method_2((Object)var10001).method_9("\u0001\u0001"));
            }
            break;
         case 259:
         case 261:
            if (this.field_1310.isFocused() && !this.field_1310.getText().isEmpty()) {
               this.field_1310.setText(this.field_1310.getText().substring(0, this.field_1310.getText().length() - 1));
            }
      }

      return true;
   }

   public void filesDragged(List<Path> paths) {
      Class_0227.method_2(this, paths);
   }

   @Override
   public void reset() {
      super.reset();
      this.setFocused(null);
      this.field_1310.setFocused(false);
      this.field_1310.setText("");
      this.field_1310.setSuggestion("");
   }

   @Override
   public void method_257() {
      super.method_257();
      field_4221.submit(() -> Hub.field_2597.method_357());
   }

   public TextFieldWidget method_452() {
      return this.field_1310;
   }

   public String method_453() {
      if (this.field_1311.method_9(500L)) {
         this.field_1309 = !this.field_1309;
         this.field_1311.reset();
      }

      return this.field_1309 ? "_" : "";
   }

   public Class_1015<?> method_454() {
      return this.field_1306;
   }

   public void method_2(Class_1015<?> var1) {
      this.field_1306 = var1;
   }
}
