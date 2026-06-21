package me.mioclient.internal;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0229;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Class_0505 implements MioAPI {
   public static final Logger field_1585 = LogManager.getLogger("Mio Sound System");
   public static final String field_1586 = "https://mioclient.me/assets/killstreaks.zip";
   public final Map<Class_0211, Class_0337> field_1587 = Collections.synchronizedMap(new HashMap<>());
   public ObjectArrayList<Class_0337> field_1588 = new ObjectArrayList();
   public ObjectArrayList<Class_0337> field_1589 = new ObjectArrayList();
   public ObjectArrayList<Class_0337> field_1590 = new ObjectArrayList();
   public ObjectArrayList<Class_0337> field_1591 = new ObjectArrayList();
   public ObjectArrayList<Class_0337> field_1592 = new ObjectArrayList();
   public ObjectArrayList<Class_0337> field_1593 = new ObjectArrayList();
   public ObjectArrayList<Class_0337> field_1594 = new ObjectArrayList();

   public Class_0505() {
      super();

      try {
         Path var1 = Class_1328.field_4288.resolve("killstreaks");
         File var2 = var1.toFile();
         if (!var2.exists() || !var2.isDirectory()) {
            field_1585.info("Downloading killstreak sounds");
            if (var2.exists() && !var2.delete()) {
               throw new RuntimeException("Failed to delete invalid killstreaks file");
            }

            if (!var2.mkdirs()) {
               throw new RuntimeException("Failed to create the killstreaks folder");
            }

            HttpRequest var3 = Class_0130.method_92("https://mioclient.me/assets/killstreaks.zip")
               .header("User-Agent", "MioClient/2.0")
               .timeout(Duration.ofSeconds(5L))
               .build();
            HttpResponse var4 = Class_0130.method_2(var3, BodyHandlers.ofByteArray());
            if (var4.statusCode() != 200) {
               throw new RuntimeException("Got invalid response code while downloading killstreak sounds (%d)".formatted(var4.statusCode()));
            }

            byte[] var5 = (byte[])var4.body();
            ZipInputStream var6 = new ZipInputStream(new ByteArrayInputStream(var5));

            ZipEntry var7;
            while ((var7 = var6.getNextEntry()) != null) {
               if (var7.isDirectory()) {
                  var1.resolve(var7.getName()).toFile().mkdirs();
               } else {
                  Class_1222.method_2(var1.resolve(var7.getName()), var6.readAllBytes());
               }
            }

            field_1585.info("Finished downloading killstreak sounds");
         }
      } catch (Exception var8) {
         field_1585.error("Failed to download killstreak sounds");
         var8.printStackTrace();
      }
   }

   public ObjectArrayList<Class_0337> method_208(String var1) throws java.io.IOException {
      ObjectArrayList var2 = new ObjectArrayList();
      File var3 = Class_1328.field_4288.resolve("killstreaks").resolve(var1).toFile();
      if (!var3.exists()) {
         field_1585.warn("Folder %s doesn't exist, ignoring".formatted(var3.getName()));

         try {
            var3.mkdir();
         } catch (Exception var8) {
         }

         return var2;
      } else if (!var3.isDirectory()) {
         throw new IllegalArgumentException("mio-fabric/killstreaks/%s isn't a folder".formatted(var3.getName()));
      } else {
         for (File var7 : Objects.requireNonNull(var3.listFiles())) {
            if (!var7.isDirectory()) {
               if (!var7.getName().endsWith(".ogg")) {
                  field_1585.warn("Ignoring mio-fabric/killstreaks/%s/%s. Only .ogg sounds are supported".formatted(var1, var7.getName()));
               } else {
                  var2.add(new Class_0337(Class_1222.method_2(var7.toPath())));
               }
            }
         }

         if (var2.isEmpty()) {
            field_1585.warn(new TextBuilder().method_2((Object)var1).method_9("No sounds were registered for \u0001"));
         }

         return var2;
      }
   }

   public Class_0337 method_6(int var1) {
      if (var1 < 2) {
         return null;
      } else {
         return switch (var1) {
            case 2 -> (Class_0337)this.field_1588.get(ThreadLocalRandom.current().nextInt(this.field_1588.size()));
            case 3 -> (Class_0337)this.field_1589.get(ThreadLocalRandom.current().nextInt(this.field_1589.size()));
            case 4 -> (Class_0337)this.field_1590.get(ThreadLocalRandom.current().nextInt(this.field_1590.size()));
            case 5 -> (Class_0337)this.field_1591.get(ThreadLocalRandom.current().nextInt(this.field_1591.size()));
            case 6 -> (Class_0337)this.field_1592.get(ThreadLocalRandom.current().nextInt(this.field_1592.size()));
            case 7 -> (Class_0337)this.field_1593.get(ThreadLocalRandom.current().nextInt(this.field_1593.size()));
            case 8 -> (Class_0337)this.field_1594.get(ThreadLocalRandom.current().nextInt(this.field_1594.size()));
            default -> var1 % 4 != 0 ? null : (Class_0337)this.field_1594.get(ThreadLocalRandom.current().nextInt(this.field_1594.size()));
         };
      }
   }

   public Set<Class_0211> method_533() {
      return this.field_1587.keySet();
   }

   public Class_0337 method_2(Class_0211 var1) {
      return this.field_1587
         .entrySet()
         .stream()
         .filter(
            var1x -> var1x.getKey() != null
                  && var1x.getKey().getName() != null
                  && var1x.getKey().method_243() != null
                  && var1x.getKey().getName().equals(var1.getName())
                  && var1x.getKey().method_243().equals(var1.method_243())
         )
         .map(Entry::getValue)
         .findAny()
         .orElse(Class_0337.field_1127);
   }

   public void method_2(Class_0211 var1, float var2) {
      this.method_2(var1).method_230(var2);
   }

   public void method_2(Class_0211 var1, Vec3d var2, float var3) {
      this.method_2(var1).method_2(var2, var3);
   }

   public void method_534() {
      this.method_536();
      this.field_1587.clear();

      for (Class_0229 var4 : Class_0229.values()) {
         try {
            String var5 = new TextBuilder().method_2(var4.method_258().getName()).method_9("/assets/mio/sounds/\u0001.ogg");
            InputStream var6 = this.getClass().getResourceAsStream(var5);
            if (var6 == null) {
               throw new FileNotFoundException(var5);
            }

            byte[] var7 = var6.readAllBytes();
            this.field_1587.put(var4.method_258(), new Class_0337(var7));
         } catch (Exception var8) {
         }
      }

      for (File var12 : Class_1328.field_4288.toFile().listFiles()) {
         if (var12.isDirectory() && !var12.getName().equalsIgnoreCase("killstreaks")) {
            this.method_535(var12.getName());
         }
      }
   }

   public void method_535(String var1) {
      Path var2 = Class_1328.field_4288.resolve(var1);

      for (File var6 : var2.toFile().listFiles()) {
         if (!var6.isDirectory() && var6.getName().endsWith(".ogg")) {
            try {
               byte[] var7 = Class_1222.method_2(var6.toPath());
               String var8 = var6.getName().replace(".ogg", "");
               this.field_1587.put(new Class_0211(var1, var8), new Class_0337(var7));
            } catch (Exception var9) {
               if (var9 instanceof InvalidIdentifierException) {
                  field_1585.warn("Failed to load sound: %s".formatted(var9.getMessage()));
               } else {
                  field_1585.warn("Failed to load sound %s: %s".formatted(var6.getName(), var9.toString()));
               }
            }
         }
      }
   }

   public void method_536() {
      try {
         this.field_1588 = this.method_208("doublekill_2");
         this.field_1589 = this.method_208("triplekill_3");
         this.field_1590 = this.method_208("dominating_4");
         this.field_1591 = this.method_208("megakill_5");
         this.field_1592 = this.method_208("unstoppable_6");
         this.field_1593 = this.method_208("wickedsick_7");
         this.field_1594 = this.method_208("monsterkill_8up");
      } catch (Exception var2) {
         field_1585.warn("Failed to load killstreak sounds");
      }
   }
}
