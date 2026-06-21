package me.mioclient.internal;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import me.mioclient.api.Class_1309;
import me.mioclient.enum_.Class_0200;
import me.mioclient.enum_.Class_0334;
import me.mioclient.module.exploit.NewChunksModule;
import me.mioclient.record.Class_0853;
import net.minecraft.util.math.ChunkPos;

public class Class_0598 implements Class_1309 {
   public final Lock field_1866 = new ReentrantLock();
   public final NewChunksModule field_1867;
   public String field_1435;

   public Class_0598(NewChunksModule var1) {
      super();
      this.field_1867 = var1;
   }

   public void method_580(String var1) {
      field_4221.submit(Class_0127.method_9(this.field_1866, () -> this.method_468(var1)));
   }

   public void method_582(String var1) {
      field_4221.submit(Class_0127.method_9(this.field_1866, () -> this.method_176(var1)));
   }

   public void method_468(String var1) {
      if (!this.field_1867.method_971().isEmpty() && var1 != null) {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();

         try (DataOutputStream var3 = new DataOutputStream(var2)) {
            var3.writeInt(1);
            synchronized (this.field_1867.method_971()) {
               int var5 = 0;

               for (List var7 : this.field_1867.method_971().values()) {
                  var5 += var7.size();
               }

               var3.writeInt(var5);

               for (List var17 : this.field_1867.method_971().values()) {
                  for (Class_0853 var9 : (Iterable<Class_0853>)(Iterable<?>)(var17)) {
                     ChunkPos var10 = var9.method_794();
                     var3.writeInt(var10.x);
                     var3.writeInt(var10.z);
                     var3.writeByte(var9.method_418().ordinal());
                     var3.writeByte(var9.method_795().ordinal());
                  }
               }
            }

            Class_1222.method_2(
               Class_1328.field_4287.resolve(new Class_1303().method_2(Class_1016.method_222(var1)).method_9("\u0001.data")), var2.toByteArray()
            );
         } catch (Exception var15) {
            var15.printStackTrace();
         }

         if (!this.field_1867.isToggled()) {
            this.field_1867.method_971().clear();
         }
      }
   }

   public void method_176(String var1) {
      Path var2 = Class_1328.field_4287.resolve(new Class_1303().method_2(Class_1016.method_222(var1)).method_9("\u0001.data"));
      if (var2.toFile().exists()) {
         try (DataInputStream var3 = new DataInputStream(new FileInputStream(var2.toFile()))) {
            int var4 = var3.readInt();
            int var5 = var3.readInt();
            ArrayList var6 = new ArrayList();

            for (int var7 = 0; var7 < var5; var7++) {
               int var8 = var3.readInt();
               int var9 = var3.readInt();
               byte var10 = var3.readByte();
               byte var11 = var3.readByte();
               if (var10 < Class_0200.values().length && var11 < Class_0334.values().length) {
                  Class_0200 var12 = Class_0200.values()[var10];
                  Class_0334 var13 = Class_0334.values()[var11];
                  Class_0853 var14 = new Class_0853(new ChunkPos(var8, var9), var12, var13);
                  var6.add(var14);
               }
            }

            this.field_1867.method_971().clear();

            for (Class_0853 var19 : (Iterable<Class_0853>)(Iterable<?>)(var6)) {
               this.field_1867.method_2(var19);
            }
         } catch (Exception var17) {
         }
      }
   }

   public String method_609() {
      return this.field_1435;
   }

   public void method_27(String var1) {
      this.field_1435 = var1;
   }
}
