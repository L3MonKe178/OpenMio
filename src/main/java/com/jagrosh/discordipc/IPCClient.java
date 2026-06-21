package com.jagrosh.discordipc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.User;
import com.jagrosh.discordipc.entities.pipe.Pipe;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import com.jagrosh.discordipc.impl.Backoff;
import java.io.Closeable;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IPCClient implements Closeable {
   public static final Logger LOGGER = LoggerFactory.getLogger(IPCClient.class);
   public final Backoff RECONNECT_TIME_MS = new Backoff(500L, 60000L);
   public final long clientId;
   public final boolean autoRegister;
   public final HashMap<String, Callback> callbacks = new HashMap<>();
   public final String applicationId;
   public final String optionalSteamId;
   public volatile Pipe pipe;
   public Logger forcedLogger = null;
   public IPCListener listener = null;
   public Thread readThread = null;
   public String encoding = "UTF-8";
   public long nextDelay = 0L;
   public boolean debugMode;
   public boolean verboseLogging;

   public IPCClient(long var1, boolean var3, boolean var4, boolean var5, String var6, String var7) {
      super();
      this.clientId = var1;
      this.debugMode = var3;
      this.verboseLogging = var4;
      this.applicationId = var6;
      this.autoRegister = var5;
      this.optionalSteamId = var7;
   }

   public IPCClient(long var1, boolean var3, boolean var4, boolean var5, String var6) {
      this(var1, var3, var4, var5, var6, null);
   }

   public IPCClient(long var1, boolean var3, boolean var4) {
      this(var1, var3, var4, false, null);
   }

   public IPCClient(long var1, boolean var3, boolean var4, String var5, String var6) {
      this(var1, var3, false, var4, var5, var6);
   }

   public IPCClient(long var1, boolean var3, boolean var4, String var5) {
      this(var1, var3, var4, var5, null);
   }

   public IPCClient(long var1, boolean var3) {
      this(var1, var3, false, null);
   }

   public IPCClient(long var1, boolean var3, String var4, String var5) {
      this(var1, false, var3, var4, var5);
   }

   public IPCClient(long var1, boolean var3, String var4) {
      this(var1, var3, var4, null);
   }

   public IPCClient(long var1) {
      this(var1, false, null);
   }

   public static int getPID() {
      String var0 = ManagementFactory.getRuntimeMXBean().getName();
      return Integer.parseInt(var0.substring(0, var0.indexOf(64)));
   }

   public Logger getCurrentLogger(Logger var1) {
      return this.forcedLogger != null ? this.forcedLogger : var1;
   }

   public void setForcedLogger(Logger var1) {
      this.forcedLogger = var1;
   }

   public void setListener(IPCListener var1) {
      this.listener = var1;
      if (this.pipe != null) {
         this.pipe.setListener(var1);
      }
   }

   public String getApplicationId() {
      return this.applicationId;
   }

   public String getOptionalSteamId() {
      return this.optionalSteamId;
   }

   public boolean isAutoRegister() {
      return this.autoRegister;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public void setEncoding(String var1) {
      this.encoding = var1;
   }

   public long getClientID() {
      return this.clientId;
   }

   public boolean isDebugMode() {
      return this.debugMode;
   }

   public void setDebugMode(boolean var1) {
      this.debugMode = var1;
   }

   public boolean isVerboseLogging() {
      return this.verboseLogging;
   }

   public void setVerboseLogging(boolean var1) {
      this.verboseLogging = var1;
   }

   public void connect(DiscordBuild... var1) {
      this.checkConnected(false);

      long var2;
      while ((var2 = this.nextDelay - System.currentTimeMillis()) > 0L) {
         if (this.debugMode) {
            this.getCurrentLogger(LOGGER).info("[DEBUG] Attempting connection in: " + var2 + "ms");
         }

         try { Thread.sleep(var2); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
      }

      this.callbacks.clear();
      this.pipe = null;

      try {
         this.pipe = Pipe.openPipe(this, this.clientId, this.callbacks, var1);
      } catch (Exception var5) {
         this.updateReconnectTime();
         throw new RuntimeException(var5);
      }

      if (this.isAutoRegister()) {
         try {
            if (this.optionalSteamId != null && !this.optionalSteamId.isEmpty()) {
               this.registerSteamGame(this.getApplicationId(), this.optionalSteamId);
            } else {
               this.registerApp(this.getApplicationId(), null);
            }
         } catch (Error | Exception var6) {
            if (this.debugMode) {
               var6.printStackTrace();
            } else {
               this.getCurrentLogger(LOGGER).error("Unable to register application, enable debug mode for trace...");
            }
         }
      }

      if (this.debugMode) {
         this.getCurrentLogger(LOGGER).info("[DEBUG] Client is now connected and ready!");
      }

      if (this.listener != null) {
         this.listener.onReady(this);
         this.pipe.setListener(this.listener);
      }

      this.startReading();
   }

   public void sendRichPresence(RichPresence var1) {
      this.sendRichPresence(var1, null);
   }

   public void sendRichPresence(RichPresence var1, Callback var2) {
      this.checkConnected(true);
      if (this.debugMode) {
         this.getCurrentLogger(LOGGER).info("[DEBUG] Sending RichPresence to discord: " + (var1 == null ? null : var1.toDecodedJson(this.encoding)));
      }

      JsonObject var3 = new JsonObject();
      JsonObject var4 = new JsonObject();
      var3.addProperty("cmd", "SET_ACTIVITY");
      var4.addProperty("pid", getPID());
      var4.add("activity", var1 == null ? new JsonObject() : var1.toJson());
      var3.add("args", var4);
      this.pipe.send(Packet.OpCode.FRAME, var3, var2);
   }

   public void registerSteamGame(String var1, String var2) {
      if (this.pipe != null) {
         this.pipe.registerSteamGame(var1, var2);
      }
   }

   public void registerApp(String var1, String var2) {
      if (this.pipe != null) {
         this.pipe.registerApp(var1, var2);
      }
   }

   public void subscribe(IPCClient.Event var1) {
      this.subscribe(var1, null);
   }

   public void subscribe(IPCClient.Event var1, Callback var2) {
      this.checkConnected(true);
      if (!var1.isSubscribable()) {
         throw new IllegalStateException("Cannot subscribe to " + var1 + " event!");
      } else {
         if (this.debugMode) {
            this.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Subscribing to Event: %s", var1.name()));
         }

         JsonObject var3 = new JsonObject();
         var3.addProperty("cmd", "SUBSCRIBE");
         var3.addProperty("evt", var1.name());
         this.pipe.send(Packet.OpCode.FRAME, var3, var2);
      }
   }

   public void respondToJoinRequest(User var1, IPCClient.ApprovalMode var2, Callback var3) {
      this.checkConnected(true);
      if (var1 != null) {
         if (this.debugMode) {
            this.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Sending response to %s as %s", var1.getName(), var2.name()));
         }

         JsonObject var4 = new JsonObject();
         var4.addProperty("cmd", var2 == IPCClient.ApprovalMode.ACCEPT ? "SEND_ACTIVITY_JOIN_INVITE" : "CLOSE_ACTIVITY_JOIN_REQUEST");
         JsonObject var5 = new JsonObject();
         var5.addProperty("user_id", var1.getId());
         var4.add("args", var5);
         this.pipe.send(Packet.OpCode.FRAME, var4, var3);
      }
   }

   public void respondToJoinRequest(User var1, IPCClient.ApprovalMode var2) {
      this.respondToJoinRequest(var1, var2, null);
   }

   public PipeStatus getStatus() {
      return this.pipe == null ? PipeStatus.UNINITIALIZED : this.pipe.getStatus();
   }

   @Override
   public void close() throws java.io.IOException {
      this.checkConnected(true);

      try {
         this.pipe.close();
      } catch (Exception var2) {
         if (this.debugMode) {
            this.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Failed to close pipe: %s", var2));
         }
      }
   }

   public DiscordBuild getDiscordBuild() {
      return this.pipe == null ? null : this.pipe.getDiscordBuild();
   }

   public User getCurrentUser() {
      return this.pipe == null ? null : this.pipe.getCurrentUser();
   }

   public void checkConnected(boolean var1) {
      if (var1 && this.getStatus() != PipeStatus.CONNECTED) {
         throw new IllegalStateException(String.format("IPCClient (ID: %d) is not connected!", this.clientId));
      } else if (!var1 && this.getStatus() == PipeStatus.CONNECTED) {
         throw new IllegalStateException(String.format("IPCClient (ID: %d) is already connected!", this.clientId));
      }
   }

   public void startReading() {
      this.readThread = new Thread(new Runnable() {
         @Override
         public void run() {
            IPCClient.this.readPipe(IPCClient.this);
         }
      }, "IPCClient-Reader");
      this.readThread.setDaemon(true);
      if (this.debugMode) {
         this.getCurrentLogger(LOGGER).info("[DEBUG] Starting IPCClient reading thread!");
      }

      this.readThread.start();
   }

   public void readPipe(IPCClient var1) {
      try {
         while (true) {
            Packet var2;
            if ((var2 = this.pipe.read()).getOp() != Packet.OpCode.CLOSE) {
               JsonObject var3 = var2.getJson();
               if (var3 != null) {
                  IPCClient.Event var4 = IPCClient.Event.of(
                     var3.has("evt") && !var3.get("evt").isJsonNull() ? var3.getAsJsonPrimitive("evt").getAsString() : null
                  );
                  String var5 = var3.has("nonce") && !var3.get("nonce").isJsonNull() ? var3.getAsJsonPrimitive("nonce").getAsString() : null;
                  switch (var4) {
                     case NULL:
                        if (var5 != null && this.callbacks.containsKey(var5)) {
                           this.callbacks.remove(var5).succeed(var2);
                        }
                        break;
                     case ERROR:
                        if (var5 != null && this.callbacks.containsKey(var5)) {
                           this.callbacks
                              .remove(var5)
                              .fail(
                                 var3.has("data") && var3.getAsJsonObject("data").has("message")
                                    ? var3.getAsJsonObject("data").getAsJsonObject("message").getAsString()
                                    : null
                              );
                        }
                        break;
                     case ACTIVITY_JOIN:
                        if (this.debugMode) {
                           this.getCurrentLogger(LOGGER).info("[DEBUG] Reading thread received a 'join' event.");
                        }
                        break;
                     case ACTIVITY_SPECTATE:
                        if (this.debugMode) {
                           this.getCurrentLogger(LOGGER).info("[DEBUG] Reading thread received a 'spectate' event.");
                        }
                        break;
                     case ACTIVITY_JOIN_REQUEST:
                        if (this.debugMode) {
                           this.getCurrentLogger(LOGGER).info("[DEBUG] Reading thread received a 'join request' event.");
                        }
                        break;
                     case UNKNOWN:
                        if (this.debugMode) {
                           this.getCurrentLogger(LOGGER)
                              .info("[DEBUG] Reading thread encountered an event with an unknown type: " + var3.getAsJsonPrimitive("evt").getAsString());
                        }
                  }

                  if (this.listener != null && var3.has("cmd") && var3.getAsJsonPrimitive("cmd").getAsString().equals("DISPATCH")) {
                     try {
                        JsonObject var6 = var3.getAsJsonObject("data");
                        switch (IPCClient.Event.of(var3.getAsJsonPrimitive("evt").getAsString())) {
                           case ACTIVITY_JOIN:
                              this.listener.onActivityJoin(var1, var6.getAsJsonPrimitive("secret").getAsString());
                              break;
                           case ACTIVITY_SPECTATE:
                              this.listener.onActivitySpectate(var1, var6.getAsJsonPrimitive("secret").getAsString());
                              break;
                           case ACTIVITY_JOIN_REQUEST:
                              JsonObject var7 = var6.getAsJsonObject("user");
                              User var8 = new User(
                                 var7.getAsJsonPrimitive("username").getAsString(),
                                 var7.has("global_name") && var7.get("global_name").isJsonPrimitive()
                                    ? var7.getAsJsonPrimitive("global_name").getAsString()
                                    : null,
                                 var7.has("discriminator") && var7.get("discriminator").isJsonPrimitive()
                                    ? var7.getAsJsonPrimitive("discriminator").getAsString()
                                    : "0",
                                 Long.parseLong(var7.getAsJsonPrimitive("id").getAsString()),
                                 var7.has("avatar") && var7.get("avatar").isJsonPrimitive() ? var7.getAsJsonPrimitive("avatar").getAsString() : null
                              );
                              this.listener.onActivityJoinRequest(var1, var6.has("secret") ? var6.getAsJsonObject("secret").getAsString() : null, var8);
                        }
                     } catch (Exception var9) {
                        this.getCurrentLogger(LOGGER).error(String.format("Exception when handling event: %s", var9));
                     }
                  }
               }
            } else {
               this.pipe.setStatus(PipeStatus.DISCONNECTED);
               if (this.listener != null) {
                  this.listener.onClose(var1, var2.getJson());
               }
               break;
            }
         }
      } catch (Exception var10) {
         this.getCurrentLogger(LOGGER).error(String.format("Reading thread encountered an Exception: %s", var10));
         this.pipe.setStatus(PipeStatus.DISCONNECTED);
         if (this.listener != null) {
            this.RECONNECT_TIME_MS.reset();
            this.updateReconnectTime();
            this.listener.onDisconnect(var1, var10);
         }
      }
   }

   public void updateReconnectTime() {
      this.nextDelay = System.currentTimeMillis() + this.RECONNECT_TIME_MS.nextDelay();
   }

   public static enum ApprovalMode {
      ACCEPT,
      DENY;
   }

   public static enum Event {
      NULL(false),
      READY(false),
      ERROR(false),
      ACTIVITY_JOIN(true),
      ACTIVITY_SPECTATE(true),
      ACTIVITY_JOIN_REQUEST(true),
      UNKNOWN(false);

      public final boolean subscribable;

       Event(boolean var3) {
         this.subscribable = var3;
      }

      public static IPCClient.Event of(String var0) {
         if (var0 == null) {
            return NULL;
         } else {
            for (IPCClient.Event var4 : values()) {
               if (var4 != UNKNOWN && var4.name().equalsIgnoreCase(var0)) {
                  return var4;
               }
            }

            return UNKNOWN;
         }
      }

      public boolean isSubscribable() {
         return this.subscribable;
      }
   }
}
