package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.nio.ByteBuffer;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.FloatType;
import me.mioclient.enum_.LinesType;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4fStack;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

public class Class_0385 implements MioAPI {
   public boolean field_1232 = false;
   public double field_1233 = Double.longBitsToDouble(4607182418800017408L);
   public final LinesType field_1234;
   public final int field_1235;
   public final int field_1236;
   public final int field_1237;
   public final int field_1238;
   public ByteBuffer field_1239;
   public long field_1240;
   public long field_1241;
   public ByteBuffer field_1242;
   public long field_1243;
   public int field_1244;
   public int field_1245;
   public boolean field_1246;
   public boolean field_1247;
   public double field_1248;
   public double field_1249;
   public boolean field_1250;

   public Class_0385(LinesType var1, FloatType... var2) {
      super();
      int var3 = 0;

      for (FloatType var7 : var2) {
         var3 += var7.field_4462;
      }

      this.field_1234 = var1;
      this.field_1235 = var3 * var1.field_3888;
      this.field_1239 = BufferUtils.createByteBuffer(this.field_1235 * 256 * 4);
      this.field_1240 = MemoryUtil.memAddress0(this.field_1239);
      this.field_1242 = BufferUtils.createByteBuffer(var1.field_3888 * 512 * 4);
      this.field_1243 = MemoryUtil.memAddress0(this.field_1242);
      this.field_1236 = GlStateManager._glGenVertexArrays();
      Class_1088.method_209(this.field_1236);
      this.field_1237 = GlStateManager._glGenBuffers();
      Class_1088.method_263(this.field_1237);
      this.field_1238 = GlStateManager._glGenBuffers();
      Class_1088.method_221(this.field_1238);
      int var8 = 0;

      for (int var9 = 0; var9 < var2.length; var9++) {
         FloatType var10 = var2[var9];
         GlStateManager._enableVertexAttribArray(var9);
         GlStateManager._vertexAttribPointer(var9, var10.field_501, var10.method_1192(), var10.field_4463, var3, (long)var8);
         var8 += var10.field_4462;
      }

      Class_1088.method_209(0);
      Class_1088.method_263(0);
      Class_1088.method_221(0);
   }

   public void method_429() {
      GlStateManager._glDeleteBuffers(this.field_1238);
      GlStateManager._glDeleteBuffers(this.field_1237);
      GlStateManager._glDeleteVertexArrays(this.field_1236);
   }

   public void method_430() {
      if (this.field_1246) {
         throw new IllegalStateException("Mesh.end() called while already building.");
      } else {
         this.field_1241 = this.field_1240;
         this.field_1244 = 0;
         this.field_1245 = 0;
         this.field_1246 = true;
         Vec3d var1 = field_4219.gameRenderer.getCamera().getPos();
         this.field_1248 = var1.x;
         this.field_1249 = var1.z;
      }
   }

   public Class_0385 method_5(double var1, double var3, double var5) {
      long var7 = this.field_1241;
      MemoryUtil.memPutFloat(var7, (float)(var1 - this.field_1248));
      MemoryUtil.memPutFloat(var7 + 4L, (float)var3);
      MemoryUtil.memPutFloat(var7 + 8L, (float)(var5 - this.field_1249));
      this.field_1241 += 12L;
      return this;
   }

   public Class_0385 method_9(double var1, double var3) {
      long var5 = this.field_1241;
      MemoryUtil.memPutFloat(var5, (float)var1);
      MemoryUtil.memPutFloat(var5 + 4L, (float)var3);
      this.field_1241 += 8L;
      return this;
   }

   public Class_0385 method_38(Color var1) {
      long var2 = this.field_1241;
      MemoryUtil.memPutByte(var2, (byte)var1.getRed());
      MemoryUtil.memPutByte(var2 + 1L, (byte)var1.getGreen());
      MemoryUtil.memPutByte(var2 + 2L, (byte)var1.getBlue());
      MemoryUtil.memPutByte(var2 + 3L, (byte)((int)((float)var1.getAlpha() * (float)this.field_1233)));
      this.field_1241 += 4L;
      return this;
   }

   public int method_431() {
      return this.field_1244++;
   }

   public void method_78(int var1, int var2) {
      long var3 = this.field_1243 + (long)this.field_1245 * 4L;
      MemoryUtil.memPutInt(var3, var1);
      MemoryUtil.memPutInt(var3 + 4L, var2);
      this.field_1245 += 2;
      this.method_432();
   }

   public void method_5(int var1, int var2, int var3, int var4) {
      long var5 = this.field_1243 + (long)this.field_1245 * 4L;
      MemoryUtil.memPutInt(var5, var1);
      MemoryUtil.memPutInt(var5 + 4L, var2);
      MemoryUtil.memPutInt(var5 + 8L, var3);
      MemoryUtil.memPutInt(var5 + 12L, var3);
      MemoryUtil.memPutInt(var5 + 16L, var4);
      MemoryUtil.memPutInt(var5 + 20L, var1);
      this.field_1245 += 6;
      this.method_432();
   }

   public void method_432() {
      if ((this.field_1244 + 1) * this.field_1235 >= this.field_1239.capacity()) {
         int var1 = this.method_437();
         int var2 = this.field_1239.capacity() * 2;
         if (var2 % this.field_1235 != 0) {
            var2 += var2 % this.field_1235;
         }

         ByteBuffer var3 = BufferUtils.createByteBuffer(var2);
         MemoryUtil.memCopy(MemoryUtil.memAddress0(this.field_1239), MemoryUtil.memAddress0(var3), (long)var1);
         this.field_1239 = var3;
         this.field_1240 = MemoryUtil.memAddress0(this.field_1239);
         this.field_1241 = this.field_1240 + (long)var1;
      }

      if (this.field_1245 * 4 >= this.field_1242.capacity()) {
         int var4 = this.field_1242.capacity() * 2;
         if (var4 % this.field_1234.field_3888 != 0) {
            var4 += var4 % (this.field_1234.field_3888 * 4);
         }

         ByteBuffer var5 = BufferUtils.createByteBuffer(var4);
         MemoryUtil.memCopy(MemoryUtil.memAddress0(this.field_1242), MemoryUtil.memAddress0(var5), (long)this.field_1245 * 4L);
         this.field_1242 = var5;
         this.field_1243 = MemoryUtil.memAddress0(this.field_1242);
      }
   }

   public void method_433() {
      if (!this.field_1246) {
         throw new IllegalStateException("Mesh.end() called while not building.");
      } else {
         if (this.field_1245 > 0) {
            Class_1088.method_263(this.field_1237);
            GlStateManager._glBufferData(34962, this.field_1239.limit(this.method_437()), 35048);
            Class_1088.method_263(0);
            Class_1088.method_221(this.field_1238);
            GlStateManager._glBufferData(34963, this.field_1242.limit(this.field_1245 * 4), 35048);
            Class_1088.method_221(0);
         }

         this.field_1246 = false;
      }
   }

   public void method_5(MatrixStack var1) {
      Class_1088.method_964();
      if (this.field_1232) {
         GlStateManager._enableDepthTest();
      } else {
         GlStateManager._disableDepthTest();
      }

      Class_1088.method_966();
      GlStateManager._disableCull();
      Class_1088.method_967();
      if (this.field_1247) {
         Matrix4fStack var2 = RenderSystem.getModelViewStack();
         var2.pushMatrix();
         if (var1 != null) {
            var2.mul(var1.peek().getPositionMatrix());
         }

         Vec3d var3 = field_4219.gameRenderer.getCamera().getPos();
         var2.translate(0.0F, (float)(-var3.y), 0.0F);
      }

      this.field_1250 = true;
   }

   public void method_7(MatrixStack var1) {
      if (this.field_1246) {
         this.method_433();
      }

      if (this.field_1245 > 0) {
         boolean var2 = this.field_1250;
         if (!var2) {
            this.method_5(var1);
         }

         this.method_436();
         Class_1119.field_3472.method_1005();
         Class_1088.method_209(this.field_1236);
         Class_1088.method_2(this.field_1234.method_1081(), this.field_1245, 5125);
         Class_1088.method_209(0);
         if (!var2) {
            this.method_434();
         }
      }
   }

   public void method_434() {
      if (this.field_1247) {
         RenderSystem.getModelViewStack().popMatrix();
      }

      Class_1088.method_965();
      this.field_1250 = false;
   }

   public boolean method_435() {
      return this.field_1246;
   }

   public void method_436() {
   }

   public int method_437() {
      return (int)(this.field_1241 - this.field_1240);
   }
}
