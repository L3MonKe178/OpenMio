package me.mioclient.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;

public final class Class_0924 {
   public final HashMap<String, String> field_2903 = new HashMap<>();

   public Class_0924() {
      super();
      this.field_2903.put("textures/overlay.png", "assets/mio/textures/overlay.png");
      this.field_2903.put("textures/skin_protect.png", "assets/mio/textures/skin_protect.png");
      this.field_2903.put("textures/shine.png", "assets/mio/textures/shine.png");
   }

   public void method_840() {
      this.field_2903.forEach(this::method_30);
   }

   public void method_30(String var1, String var2) {
      Path var3 = Class_1328.field_4281.resolve(var1);
      if (!var3.toFile().exists()) {
         try {
            try (InputStream var4 = Class_0550.class.getClassLoader().getResourceAsStream(var2)) {
               if (var4 != null) {
                  Class_1222.method_2(var3, var4.readAllBytes());
                  return;
               }
            }
         } catch (IOException var9) {
            var9.printStackTrace();
         }
      }
   }
}
