package me.mioclient.runnable;

import java.util.ArrayList;
import me.mioclient.internal.Class_0191;
import me.mioclient.record.Class_0681;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;

public class Class_1235 implements Runnable {
   public static final Mutable field_3882 = new Mutable();
   public final Class_0191 field_3883;

   public Class_1235(Class_0191 var1) {
      super();
      this.field_3883 = var1;
   }

   @Override
   public void run() {
      while (true) {
         try {
            this.method_1080();
            Thread.sleep(50L);
         } catch (Throwable var2) {
         }
      }
   }

   public void method_1080() {
      if (MinecraftClient.getInstance() != null && MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
         ArrayList<Class_0681> var1 = new ArrayList<>();
         BlockPos var2 = MinecraftClient.getInstance().player.getBlockPos();

         for (int var3 = -16; var3 < 16; var3++) {
            for (int var4 = -16; var4 < 16; var4++) {
               for (int var5 = -16; var5 < 16; var5++) {
                  field_3882.set(var2.getX() + var3, var2.getY() + var4, var2.getZ() + var5);
                  Class_0681 var6 = Class_0191.method_9(field_3882, null);
                  if (var6 != null && !var1.stream().anyMatch(var1x -> var6.method_172().intersects(var1x.method_172()))) {
                     var1.add(var6);
                  }
               }
            }
         }

         this.field_3883.method_39(var1);
      }
   }
}
