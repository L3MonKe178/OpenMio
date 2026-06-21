package com.jagrosh.discordipc.entities.pipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.User;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Pipe {
   public static final Logger LOGGER = LoggerFactory.getLogger(Pipe.class);
   public static final int VERSION = 1;
   public static final String[] unixPaths = new String[]{"XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP"};
   public static final String[] unixFolderPaths = new String[]{"/snap.discord", "/app/com.discordapp.Discord"};
   public final IPCClient ipcClient;
   public final HashMap<String, Callback> callbacks;
   public PipeStatus status = PipeStatus.CONNECTING;
   public IPCListener listener;
   public DiscordBuild build;
   public User currentUser;

   public Pipe(IPCClient var1, HashMap<String, Callback> var2) {
      super();
      this.ipcClient = var1;
      this.callbacks = var2;
   }

   public static Pipe openPipe(IPCClient var0, long var1, HashMap<String, Callback> var3, DiscordBuild... var4) throws com.jagrosh.discordipc.exceptions.NoDiscordClientException {
      if (var4 == null || var4.length == 0) {
         var4 = new DiscordBuild[]{DiscordBuild.ANY};
      }

      Pipe var5 = null;
      Pipe[] var6 = new Pipe[DiscordBuild.values().length];

      for (int var7 = 0; var7 < 10; var7++) {
         String var8 = getPipeLocation(var7);
         if (var0.isDebugMode()) {
            var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Searching for IPC Pipe: \"%s\"", var8));
         }

         try {
            File var9 = new File(var8);
            if (var9.exists()) {
               if (var0.isDebugMode()) {
                  var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Found valid file, attempting connection to IPC: \"%s\"", var8));
               }

               var5 = createPipe(var0, var3, var9);
               if (var5 != null) {
                  JsonObject var10 = new JsonObject();
                  var10.addProperty("v", 1);
                  var10.addProperty("client_id", Long.toString(var1));
                  var5.send(Packet.OpCode.HANDSHAKE, var10);
                  Packet var11 = var5.read();
                  JsonObject var12 = var11.getJson();
                  JsonObject var13 = var12.getAsJsonObject("data");
                  JsonObject var14 = var13.getAsJsonObject("user");
                  var5.build = DiscordBuild.from(var13.getAsJsonObject("config").get("api_endpoint").getAsString());
                  var5.currentUser = new User(
                     var14.getAsJsonPrimitive("username").getAsString(),
                     var14.has("global_name") && var14.get("global_name").isJsonPrimitive() ? var14.getAsJsonPrimitive("global_name").getAsString() : null,
                     var14.has("discriminator") && var14.get("discriminator").isJsonPrimitive() ? var14.getAsJsonPrimitive("discriminator").getAsString() : "0",
                     Long.parseLong(var14.getAsJsonPrimitive("id").getAsString()),
                     var14.has("avatar") && var14.get("avatar").isJsonPrimitive() ? var14.getAsJsonPrimitive("avatar").getAsString() : null
                  );
                  if (var0.isDebugMode()) {
                     var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Found a valid client (%s) with packet: %s", var5.build.name(), var11));
                     var0.getCurrentLogger(LOGGER)
                        .info(String.format("[DEBUG] Found a valid user (%s) with id: %s", var5.currentUser.getName(), var5.currentUser.getId()));
                  }

                  if (var5.build == var4[0] || DiscordBuild.ANY == var4[0]) {
                     if (var0.isDebugMode()) {
                        var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Found preferred client: %s", var5.build.name()));
                     }
                     break;
                  }

                  var6[var5.build.ordinal()] = var5;
                  var6[DiscordBuild.ANY.ordinal()] = var5;
                  var5.build = null;
                  var5 = null;
               }
            } else if (var0.isDebugMode()) {
               var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Unable to locate IPC Pipe: \"%s\"", var8));
            }
         } catch (Exception var16) {
            var5 = null;
         }
      }

      if (var5 == null) {
         for (int var17 = 1; var17 < var4.length; var17++) {
            DiscordBuild var19 = var4[var17];
            if (var0.isDebugMode()) {
               var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Looking for client build: %s", var19.name()));
            }

            if (var6[var19.ordinal()] != null) {
               var5 = var6[var19.ordinal()];
               var6[var19.ordinal()] = null;
               if (var19 == DiscordBuild.ANY) {
                  for (int var20 = 0; var20 < var6.length; var20++) {
                     if (var6[var20] == var5) {
                        var5.build = DiscordBuild.values()[var20];
                        var6[var20] = null;
                     }
                  }
               } else {
                  var5.build = var19;
               }

               if (var0.isDebugMode()) {
                  var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Found preferred client: %s", var5.build.name()));
               }
               break;
            }
         }

         if (var5 == null) {
            throw new NoDiscordClientException();
         }
      }

      for (int var18 = 0; var18 < var6.length; var18++) {
         if (var18 != DiscordBuild.ANY.ordinal() && var6[var18] != null) {
            try {
               var6[var18].close();
            } catch (Exception var15) {
               if (var0.isDebugMode()) {
                  var0.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Failed to close an open IPC pipe: %s", var15));
               }
            }
         }
      }

      var5.status = PipeStatus.CONNECTED;
      return var5;
   }

   public static Pipe createPipe(IPCClient var0, HashMap<String, Callback> var1, File var2) {
      String var3 = System.getProperty("os.name").toLowerCase();
      if (var3.contains("win")) {
         return new WindowsPipe(var0, var1, var2);
      } else if (!var3.contains("linux") && !var3.contains("mac")) {
         throw new RuntimeException("Unsupported OS: " + var3);
      } else {
         try {
            return (Pipe)(var3.contains("mac") ? new MacPipe(var0, var1, var2) : new UnixPipe(var0, var1, var2));
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }
      }
   }

   public static String generateNonce() {
      return UUID.randomUUID().toString();
   }

   public static String getPipeLocation(int var0) {
      String var1 = null;
      String var2 = "discord-ipc-" + var0;
      if (System.getProperty("os.name").contains("Win")) {
         return "\\\\?\\pipe\\" + var2;
      } else {
         for (String var6 : unixPaths) {
            var1 = System.getenv(var6);
            if (var1 != null) {
               break;
            }
         }

         if (var1 == null) {
            var1 = "/tmp";
         }

         for (String var12 : unixFolderPaths) {
            String var7 = var1 + var12;
            File var8 = new File(var7);
            if (var8.exists() && var8.isDirectory() && var8.list().length > 0) {
               var1 = var7;
               break;
            }
         }

         return var1 + "/" + var2;
      }
   }

   public void send(Packet.OpCode var1, JsonObject var2, Callback var3) {
      try {
         String var4 = generateNonce();
         var2.addProperty("nonce", var4);
         Packet var5 = new Packet(var1, var2, this.ipcClient.getEncoding());
         if (var3 != null && !var3.isEmpty()) {
            this.callbacks.put(var4, var3);
         }

         this.write(var5.toBytes());
         if (this.ipcClient.isDebugMode()) {
            this.ipcClient.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Sent packet: %s", var5.toDecodedString()));
         }

         if (this.listener != null) {
            this.listener.onPacketSent(this.ipcClient, var5);
         }
      } catch (Exception var6) {
         this.ipcClient.getCurrentLogger(LOGGER).error("Encountered an IOException while sending a packet and disconnected!");
         this.status = PipeStatus.DISCONNECTED;
      }
   }

   public void send(Packet.OpCode var1, JsonObject var2) {
      this.send(var1, var2, null);
   }

   public Packet receive(Packet.OpCode var1, byte[] var2) {
      JsonObject var3 = new JsonParser().parse(new String(var2)).getAsJsonObject();
      Packet var4 = new Packet(var1, var3, this.ipcClient.getEncoding());
      if (this.ipcClient.isDebugMode()) {
         this.ipcClient.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Received packet: %s", var4));
      }

      if (this.listener != null) {
         this.listener.onPacketReceived(this.ipcClient, var4);
      }

      return var4;
   }

   public abstract Packet read();

   public abstract void write(byte[] var1);

   public abstract void registerApp(String var1, String var2);

   public abstract void registerSteamGame(String var1, String var2);

   public PipeStatus getStatus() {
      return this.status;
   }

   public void setStatus(PipeStatus var1) {
      this.status = var1;
   }

   public void setListener(IPCListener var1) {
      this.listener = var1;
   }

   public abstract void close();

   public DiscordBuild getDiscordBuild() {
      return this.build;
   }

   public User getCurrentUser() {
      return this.currentUser;
   }
}
