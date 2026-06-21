package me.mioclient.internal;

import java.io.DataOutput;
import org.jetbrains.annotations.NotNull;

public class Class_0177 implements DataOutput {
   public int field_501;

   public Class_0177() {
      super();
   }

   public int method_212() {
      return this.field_501;
   }

   @Override
   public void write(int var1) {
      this.field_501++;
   }

   @Override
   public void write(byte[] var1) {
      this.field_501 += var1.length;
   }

   @Override
   public void write(@NotNull byte[] var1, int var2, int var3) {
      this.field_501 += var3;
   }

   @Override
   public void writeBoolean(boolean var1) {
      this.field_501++;
   }

   @Override
   public void writeByte(int var1) {
      this.field_501++;
   }

   @Override
   public void writeShort(int var1) {
      this.field_501 += 2;
   }

   @Override
   public void writeChar(int var1) {
      this.field_501 += 2;
   }

   @Override
   public void writeInt(int var1) {
      this.field_501 += 4;
   }

   @Override
   public void writeLong(long var1) {
      this.field_501 += 8;
   }

   @Override
   public void writeFloat(float var1) {
      this.field_501 += 4;
   }

   @Override
   public void writeDouble(double var1) {
      this.field_501 += 8;
   }

   @Override
   public void writeBytes(String var1) {
      this.field_501 = this.field_501 + var1.length();
   }

   @Override
   public void writeChars(String var1) {
      this.field_501 = this.field_501 + var1.getBytes().length;
   }

   @Override
   public void writeUTF(@NotNull String var1) {
      this.field_501 = this.field_501 + var1.getBytes().length;
   }
}
