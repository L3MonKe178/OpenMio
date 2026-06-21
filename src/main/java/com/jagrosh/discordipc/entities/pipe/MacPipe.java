package com.jagrosh.discordipc.entities.pipe;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class MacPipe extends UnixPipe {
   public MacPipe(IPCClient var1, HashMap<String, Callback> var2, File var3) {
      super(var1, var2, var3);
   }

   public void registerCommand(String var1, String var2) {
      String var3 = System.getenv("HOME");
      if (var3 == null) {
         throw new RuntimeException("Unable to find user HOME directory");
      } else {
         String var4 = var3 + "/Library/Application Support/discord";
         if (!this.mkdir(var4)) {
            throw new RuntimeException("Failed to create directory '" + var4 + "'");
         } else {
            var4 = var4 + "/games";
            if (!this.mkdir(var4)) {
               throw new RuntimeException("Failed to create directory '" + var4 + "'");
            } else {
               var4 = var4 + "/" + var1 + ".json";

               try {
                  FileWriter var5 = new FileWriter(var4);

                  try {
                     var5.write("{\"command\": \"" + var2 + "\"}");
                  } catch (Throwable var9) {
                     try {
                        var5.close();
                     } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                     }

                     throw var9;
                  }

                  var5.close();
               } catch (Exception var10) {
                  throw new RuntimeException("Failed to write fame info into '" + var4 + "'");
               }
            }
         }
      }
   }

   public void registerUrl(String var1) {
      throw new UnsupportedOperationException("MacOS URL registration is not supported at this time.");
   }

   @Override
   public void registerApp(String var1, String var2) {
      try {
         if (var2 != null) {
            this.registerCommand(var1, var2);
         } else {
            this.registerUrl(var1);
         }
      } catch (Exception var4) {
         throw new RuntimeException("Failed to register " + (var2 == null ? "url" : "command"), var4);
      }
   }

   @Override
   public void registerSteamGame(String var1, String var2) {
      this.registerApp(var1, "steam://rungameid/" + var2);
   }
}
