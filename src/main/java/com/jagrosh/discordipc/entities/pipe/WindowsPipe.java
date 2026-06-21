package com.jagrosh.discordipc.entities.pipe;

import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Callback;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.impl.WinRegistry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsPipe extends Pipe {
   public static final Logger LOGGER = LoggerFactory.getLogger(WindowsPipe.class);
   public static final Float javaSpec = Float.parseFloat(System.getProperty("java.specification.version"));
   public final int targetKey = -2147483647;
   public final long targetLongKey = -2147483647L;
   public RandomAccessFile file;

   public WindowsPipe(IPCClient var1, HashMap<String, Callback> var2, File var3) {
      super(var1, var2);

      try {
         this.file = new RandomAccessFile(var3, "rw");
      } catch (FileNotFoundException var5) {
         this.file = null;
      }
   }

   @Override
   public void write(byte[] var1) {
      try {
         this.file.write(var1);
      } catch (IOException e) { throw new RuntimeException(e); }
   }

   @Override
   public Packet read() {
      try {
         while ((this.status == PipeStatus.CONNECTED || this.status == PipeStatus.CLOSING) && this.file.length() == 0L) {
            try {
               Thread.sleep(50L);
            } catch (InterruptedException var4) {
            }
         }

         if (this.status == PipeStatus.DISCONNECTED) {
            throw new IOException("Disconnected!");
         } else if (this.status == PipeStatus.CLOSED) {
            return new Packet(Packet.OpCode.CLOSE, null, this.ipcClient.getEncoding());
         } else {
            Packet.OpCode var1 = Packet.OpCode.values()[Integer.reverseBytes(this.file.readInt())];
            int var2 = Integer.reverseBytes(this.file.readInt());
            byte[] var3 = new byte[var2];
            this.file.readFully(var3);
            return this.receive(var1, var3);
         }
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
         this.file.close();
      } catch (IOException e) { throw new RuntimeException(e); }
   }

   @Override
   public void registerApp(String var1, String var2) {
      String var3 = System.getProperty("java.home");
      File var4 = new File(var3.split(";")[0] + "/bin/java.exe");
      File var5 = new File(var3.split(";")[0] + "/bin/javaw.exe");
      String var6 = var4.exists() ? var4.getAbsolutePath() : (var5.exists() ? var5.getAbsolutePath() : null);
      if (var6 == null) {
         throw new RuntimeException("Unable to find java path");
      } else {
         String var7;
         if (var2 != null) {
            var7 = var2;
         } else {
            var7 = var6;
         }

         String var8 = "discord-" + var1;
         String var9 = "URL:Run game " + var1 + " protocol";
         String var10 = "Software\\Classes\\" + var8;
         String var11 = var10 + "\\DefaultIcon";
         String var12 = var10 + "\\DefaultIcon";

         try {
            if (javaSpec >= 11.0F) {
               WinRegistry.createKey(-2147483647L, var10);
               WinRegistry.writeStringValue(-2147483647L, var10, "", var9);
               WinRegistry.writeStringValue(-2147483647L, var10, "URL Protocol", "\u0000");
               WinRegistry.createKey(-2147483647L, var11);
               WinRegistry.writeStringValue(-2147483647L, var11, "", var6);
               WinRegistry.createKey(-2147483647L, var12);
               WinRegistry.writeStringValue(-2147483647L, var12, "", var7);
            } else {
               WinRegistry.createKey(-2147483647, var10);
               WinRegistry.writeStringValue(-2147483647, var10, "", var9);
               WinRegistry.writeStringValue(-2147483647, var10, "URL Protocol", "\u0000");
               WinRegistry.createKey(-2147483647, var11);
               WinRegistry.writeStringValue(-2147483647, var11, "", var6);
               WinRegistry.createKey(-2147483647, var12);
               WinRegistry.writeStringValue(-2147483647, var12, "", var7);
            }
         } catch (Error | Exception var14) {
            throw new RuntimeException("Unable to modify Discord registry keys", var14);
         }
      }
   }

   @Override
   public void registerSteamGame(String var1, String var2) {
      try {
         String var3;
         if (javaSpec >= 11.0F) {
            var3 = WinRegistry.readString(-2147483647L, "Software\\\\Valve\\\\Steam", "SteamExe");
         } else {
            var3 = WinRegistry.readString(-2147483647, "Software\\\\Valve\\\\Steam", "SteamExe");
         }

         if (var3 == null) {
            throw new RuntimeException("Steam exe path not found");
         } else {
            var3 = var3.replaceAll("/", "\\");
            String var4 = "\"" + var3 + "\" steam://rungameid/" + var2;
            this.registerApp(var1, var4);
         }
      } catch (Exception var5) {
         throw new RuntimeException("Unable to register Steam game", var5);
      }
   }
}
