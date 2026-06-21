package com.jagrosh.discordipc.entities.pipe;

import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.Packet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.newsclub.net.unix.AFInputStream;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnixPipe extends Pipe {
   public static final Logger LOGGER = LoggerFactory.getLogger(UnixPipe.class);
   public final AFUNIXSocket socket;

   public UnixPipe(IPCClient var1, HashMap<String, Callback> var2, File var3) {
      super(var1, var2);
      try {
         this.socket = AFUNIXSocket.newInstance();
         this.socket.connect(AFUNIXSocketAddress.of(var3));
      } catch (IOException e) { throw new RuntimeException(e); }
   }

   @Override
   public Packet read() {
      try {
         AFInputStream var1 = this.socket.getInputStream();

         while ((this.status == PipeStatus.CONNECTED || this.status == PipeStatus.CLOSING) && var1.available() == 0) {
            try {
               Thread.sleep(50L);
            } catch (InterruptedException var7) {
            }
         }

         if (this.status == PipeStatus.DISCONNECTED) {
            throw new IOException("Disconnected!");
         } else if (this.status == PipeStatus.CLOSED) {
            return new Packet(Packet.OpCode.CLOSE, null, this.ipcClient.getEncoding());
         } else {
            byte[] var2 = new byte[8];
            int var3 = var1.read(var2);
            ByteBuffer var4 = ByteBuffer.wrap(var2);
            if (this.ipcClient.isDebugMode() && this.ipcClient.isVerboseLogging()) {
               this.ipcClient.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Read Byte Data: %s with result %s", new String(var2), var3));
            }

            Packet.OpCode var5 = Packet.OpCode.values()[Integer.reverseBytes(var4.getInt())];
            var2 = new byte[Integer.reverseBytes(var4.getInt())];
            int var6 = var1.read(var2);
            if (this.ipcClient.isDebugMode() && this.ipcClient.isVerboseLogging()) {
               this.ipcClient.getCurrentLogger(LOGGER).info(String.format("[DEBUG] Read Reversed Byte Data: %s with result %s", new String(var2), var6));
            }

            return this.receive(var5, var2);
         }
      } catch (IOException e) { throw new RuntimeException(e); }
   }

   @Override
   public void write(byte[] var1) {
      try {
         this.socket.getOutputStream().write(var1);
      } catch (IOException e) { throw new RuntimeException(e); }
   }

   @Override
   public void close() {
      if (this.ipcClient.isDebugMode()) {
         this.ipcClient.getCurrentLogger(LOGGER).info("[DEBUG] Closing IPC pipe...");
      }

      this.status = PipeStatus.CLOSING;
      this.send(Packet.OpCode.CLOSE, new JsonObject());
      this.status = PipeStatus.CLOSED;
      try {
         this.socket.close();
      } catch (IOException e) { throw new RuntimeException(e); }
   }

   public boolean mkdir(String var1) {
      File var2 = new File(var1);
      return var2.exists() && var2.isDirectory() || var2.mkdir();
   }

   @Override
   public void registerApp(String var1, String var2) {
      String var3 = System.getenv("HOME");
      if (var3 == null) {
         throw new RuntimeException("Unable to find user HOME directory");
      } else {
         if (var2 == null) {
            try {
               var2 = Files.readSymbolicLink(Paths.get("/proc/self/exe")).toString();
            } catch (Exception var14) {
               throw new RuntimeException("Unable to get current exe path from /proc/self/exe", var14);
            }
         }

         String var4 = "[Desktop Entry]\nName=Game "
            + var1
            + "\nExec="
            + var2
            + " %%u\nType=Application\nNoDisplay=true\nCategories=Discord;Games;\nMimeType=x-scheme-handler/discord-"
            + var1
            + ";\n";
         String var5 = "/discord-" + var1 + ".desktop";
         String var6 = var3 + "/.local";
         if (this.mkdir(var6)) {
            this.ipcClient.getCurrentLogger(LOGGER).warn("[DEBUG] Failed to create directory '" + var6 + "', may already exist");
         }

         var6 = var6 + "/share";
         if (this.mkdir(var6)) {
            this.ipcClient.getCurrentLogger(LOGGER).warn("[DEBUG] Failed to create directory '" + var6 + "', may already exist");
         }

         var6 = var6 + "/applications";
         if (this.mkdir(var6)) {
            this.ipcClient.getCurrentLogger(LOGGER).warn("[DEBUG] Failed to create directory '" + var6 + "', may already exist");
         }

         var6 = var6 + var5;

         try {
            FileWriter var7 = new FileWriter(var6);

            try {
               var7.write(var4);
            } catch (Throwable var12) {
               try {
                  var7.close();
               } catch (Throwable var10) {
                  var12.addSuppressed(var10);
               }

               throw var12;
            }

            var7.close();
         } catch (Exception var13) {
            throw new RuntimeException("Failed to write desktop info into '" + var6 + "'");
         }

         String var18 = "xdg-mime default discord-" + var1 + ".desktop x-scheme-handler/discord-" + var1;

         try {
            ProcessBuilder var8 = new ProcessBuilder(var18.split(" "));
            var8.environment();
            int var9 = var8.start().waitFor();
            if (var9 < 0) {
               throw new Exception("xdg-mime returned " + var9);
            }
         } catch (Exception var11) {
            throw new RuntimeException("Failed to register mime handler", var11);
         }
      }
   }

   @Override
   public void registerSteamGame(String var1, String var2) {
      this.registerApp(var1, "xdg-open steam://rungameid/" + var2);
   }
}
