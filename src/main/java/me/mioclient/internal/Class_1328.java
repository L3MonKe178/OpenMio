package me.mioclient.internal;

import com.google.gson.JsonParser;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0836;
import net.fabricmc.loader.api.FabricLoader;

public final class Class_1328 implements Class_1309 {
   public static final Path field_4281 = FabricLoader.getInstance().getGameDir().resolve("mio-fabric");
   public static final Path field_4282 = field_4281.resolve("presets");
   public static final Path field_4283 = field_4281.resolve("spammer");
   public static final Path field_4284 = field_4281.resolve("autoez");
   public static final Path field_4285 = field_4281.resolve("textures");
   public static final Path field_4286 = field_4281.resolve("maps");
   public static final Path field_4287 = field_4281.resolve("chunks");
   public static final Path field_4288 = field_4281.resolve("sounds");
   public static final Path field_4289 = Path.of(System.getProperty("user.home"), "_____a");
   public static final List<Path> field_4290 = new ArrayList<>();
   public final List<Class_1146> field_4291 = List.of(
      Hub.field_2621,
      Hub.field_2599,
      Hub.field_2603,
      Hub.field_2604,
      Hub.field_2607,
      Hub.field_2618,
      Hub.field_2620,
      Hub.field_2610,
      Hub.field_2625,
      Hub.field_2626,
      Hub.field_2627,
      Hub.field_2628,
      Hub.field_2600
   );
   public final Map<Class_0836, Class_0852> field_4292 = new HashMap<>();
   public final Class_0924 field_4293 = new Class_0924();
   public final Class_0307 field_4294 = new Class_0307();

   public Class_1328() {
      super();
   }

   public void method_1173() {
      field_4290.stream().filter(var0 -> !var0.toFile().exists()).forEachOrdered(var0 -> var0.toFile().mkdir());
      this.field_4293.method_840();
   }

   public void method_840() {
      field_4290.addAll(Arrays.asList(field_4281, field_4282, field_4283, field_4284, field_4285, field_4286, field_4288));
      field_4290.add(field_4287);

      for (Class_0836 var4 : Class_0836.values()) {
         field_4290.add(var4.method_774());
      }

      this.method_1173();

      try {
         Class_0950.run();
      } catch (Throwable var7) {
      }

      for (Class_1146 var10 : this.field_4291) {
         try {
            Path var12 = field_4281.resolve(var10.getConfigName());
            if (var12.toFile().exists()) {
               var10.fromJson(JsonParser.parseString(Class_1222.method_9(var12)).getAsJsonObject());
            }
         } catch (Throwable var6) {
            var6.printStackTrace();
         }
      }

      for (Class_0836 var14 : Class_0836.values()) {
         Class_0852 var5 = new Class_0852(var14);
         this.field_4292.put(var14, var5);
         var5.method_56();
      }
   }

   public void method_357() {
      if (!Class_0152.field_442) {
         for (Class_1146 var2 : this.field_4291) {
            try {
               Path var3 = field_4281.resolve(var2.getConfigName());
               Class_1222.method_2(var3, field_4218.toJson(var2.toJson().getAsJsonObject()));
            } catch (Throwable var4) {
               var4.printStackTrace();
            }
         }
      }
   }

   public Class_0852 method_2(Class_0836 var1) {
      return this.field_4292.get(var1);
   }

   public Class_0307 method_1174() {
      return this.field_4294;
   }
}
