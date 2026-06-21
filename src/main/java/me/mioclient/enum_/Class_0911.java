package me.mioclient.enum_;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.mioclient.api.MioAPI;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIo;

public enum Class_0911 {
   FUTURE("Future") {
      @Override
      public List<String> method_201() throws java.io.IOException {
         String var1 = System.getProperty("user.home");
         if (!var1.endsWith(File.separator)) {
            var1 = var1 + File.separator;
         }

         var1 = var1 + "Future%sfriends.json".formatted(File.separator);
         File var2 = new File(var1);
         if (var2.exists() && var2.isFile()) {
            FileInputStream var3 = new FileInputStream(var2);
            byte[] var4 = var3.readAllBytes();
            var3.close();
            if (var4.length == 0) {
               return Collections.emptyList();
            } else {
               ArrayList var5 = new ArrayList();
               JsonArray var6 = (JsonArray)MioAPI.field_4218.fromJson(new String(var4, StandardCharsets.UTF_8), JsonArray.class);

               for (int var7 = 0; var7 < var6.size(); var7++) {
                  JsonObject var8 = var6.get(var7).getAsJsonObject();
                  if (var8.has("friend-label")) {
                     String var9 = var8.get("friend-label").getAsString();
                     if (!var5.contains(var9)) {
                        var5.add(var9);
                     }
                  }
               }

               return var5;
            }
         } else {
            throw new FileNotFoundException();
         }
      }
   },
   RUSHERHACK("RusherHack") {
      @Override
      public List<String> method_201() throws java.io.IOException {
         File var1 = new File("rusherhack" + File.separator + "config" + File.separator + "relations.json");
         if (var1.exists() && var1.isFile()) {
            FileInputStream var2 = new FileInputStream(var1);
            byte[] var3 = var2.readAllBytes();
            var2.close();
            if (var3.length == 0) {
               return Collections.emptyList();
            } else {
               ArrayList var4 = new ArrayList();
               JsonArray var5 = (JsonArray)MioAPI.field_4218.fromJson(new String(var3, StandardCharsets.UTF_8), JsonArray.class);

               for (int var6 = 0; var6 < var5.size(); var6++) {
                  JsonObject var7 = var5.get(var6).getAsJsonObject();
                  if (var7.has("username") && var7.has("state")) {
                     String var8 = var7.get("username").getAsString();
                     String var9 = var7.get("state").getAsString();
                     if ("FRIEND".equalsIgnoreCase(var9) && !var4.contains(var8)) {
                        var4.add(var8);
                     }
                  }
               }

               return var4;
            }
         } else {
            throw new FileNotFoundException();
         }
      }
   },
   METEOR("Meteor") {
      @Override
      public List<String> method_201() throws java.io.IOException {
         File var1 = new File("meteor-client" + File.separator + "friends.nbt");
         if (var1.exists() && var1.isFile() && var1.length() != 0L) {
            ArrayList var2 = new ArrayList();
            NbtCompound var3 = NbtIo.read(var1.toPath());

            for (NbtElement var5 : var3.getList("friends", 10)) {
               NbtCompound var6 = (NbtCompound)var5;
               if (var6.contains("name")) {
                  String var7 = var6.getString("name");
                  if (!var2.contains(var7)) {
                     var2.add(var7);
                  }
               }
            }

            return var2;
         } else {
            throw new FileNotFoundException();
         }
      }
   };

   public final String field_2890;

    Class_0911(String var3) {
      this.field_2890 = var3;
   }

   public String method_827() {
      return this.field_2890;
   }

   public List<String> method_201() throws java.io.IOException {
      return Collections.emptyList();
   }
}
