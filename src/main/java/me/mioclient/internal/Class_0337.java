package me.mioclient.internal;

import java.io.ByteArrayInputStream;
import me.mioclient.api.MioAPI;
import me.mioclient.mixin.ducks.DuckAbstractSoundInstance;
import net.minecraft.util.math.Vec3d;

public class Class_0337 implements MioAPI {
   public static final Class_0337 field_1127 = new Class_0337(null) {
      @Override
      public void method_230(float var1) {
      }

      @Override
      public void method_2(Vec3d var1, float var2) {
      }
   };
   public final byte[] field_1128;

   public Class_0337(byte[] var1) {
      super();
      this.field_1128 = var1;
   }

   public void method_387() {
      this.method_230(Float.intBitsToFloat(1065353216));
   }

   public void method_30(Vec3d var1) {
      this.method_2(var1, Float.intBitsToFloat(1065353216));
   }

   public void method_230(float var1) {
      if (field_4219.getSoundManager() != null) {
         try {
            field_4219.execute(() -> {
               Class_0423 var2 = new Class_0423(new ByteArrayInputStream(this.method_388()));
               ((DuckAbstractSoundInstance)var2).setVolume(var1);
               field_4219.getSoundManager().play(var2);
            });
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }
   }

   public void method_2(Vec3d var1, float var2) {
      if (field_4219.world != null) {
         try {
            field_4219.execute(() -> {
               Class_0423 var3 = new Class_0423(var1, new ByteArrayInputStream(this.method_388()));
               ((DuckAbstractSoundInstance)var3).setVolume(var2);
               field_4219.getSoundManager().play(var3);
            });
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }
   }

   public byte[] method_388() {
      return this.field_1128;
   }
}
