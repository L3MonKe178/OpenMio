package me.mioclient.internal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import me.mioclient.Hub;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.screen.Screen;

public class Class_0918 extends Class_1117 {
   public final Setting<Class_0211> field_2897;
   public final Screen field_2898;

   public Class_0918(Setting<Class_0211> var1, Screen var2) {
      super();
      this.field_2897 = var1;
      this.field_2898 = var2;
      this.method_831();
   }

   @Override
   public void method_257() {
      field_4219.setScreen(this.field_2898);
   }

   @Override
   public Screen method_830() {
      return this.field_2898 instanceof Class_1117 var1 ? var1.method_830() : this.field_2898;
   }

   public void filesDragged(List<Path> paths) {
      for (Path var3 : paths) {
         if (var3.toFile().getName().endsWith(".ogg")) {
            Path var4 = Class_1328.field_4288.resolve("custom");
            var4.toFile().mkdirs();

            try {
               Files.copy(var3, var4.resolve(var3.getFileName()));
            } catch (IOException var6) {
               var6.printStackTrace();
            }
         }
      }

      Hub.field_2606.method_534();
      this.method_831();
   }

   public void method_831() {
      this.method_999().clear();
      synchronized (Hub.field_2606.method_533()) {
         AtomicInteger var2 = new AtomicInteger(0);
         Hub.field_2606.method_533().stream().map(var0 -> var0.method_243()).filter(var0 -> !var0.isEmpty()).distinct().forEach(var2x -> {
            Class_0746 var3 = new Class_0746(Class_1016.method_3(var2x));
            this.method_999().add(var3);
            var3.setX(var3.getX() + (var3.method_216() + 3) * var2.getAndIncrement());
         });

         for (Class_0211 var4 : Hub.field_2606.method_533()) {
            for (Class_0746 var6 : this.method_999()) {
               if (var6.getName().equalsIgnoreCase(var4.method_243())) {
                  Supplier var7 = () -> this.field_2897.getValue().equals(var4)
                        ? new Class_1303().method_2(var4.getName()).method_9("> \u0001")
                        : var4.getName();
                  var6.method_714().add(new Class_0458(this, var6, var4));
                  break;
               }
            }
         }
      }
   }
}
