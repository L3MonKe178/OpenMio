package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public final class Class_1303 {
   public static final char field_4212 = 1;
   public static final char field_4213 = '\u0002';
   public static final String field_4214 = String.valueOf(field_4212);
   public final List<String> field_4215 = new ArrayList<>();

   public Class_1303() {
      super();
   }

   public Class_1303 method_2(boolean var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(byte var1) {
      this.method_2(String.valueOf((int)var1));
      return this;
   }

   public Class_1303 method_2(char var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(short var1) {
      this.method_2(String.valueOf((int)var1));
      return this;
   }

   public Class_1303 method_2(int var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(long var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(double var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(float var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(boolean[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(byte[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(char[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(short[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(int[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(long[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(double[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(float[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(Object[] var1) {
      this.method_2(Arrays.toString(var1));
      return this;
   }

   public Class_1303 method_2(Object var1) {
      this.method_2(String.valueOf(var1));
      return this;
   }

   public Class_1303 method_2(String var1) {
      this.field_4215.add(0, var1);
      return this;
   }

   public String method_9(String var1) {
      return method_2(var1, this.field_4215);
   }

   public static String method_2(String var0, List<String> var1) {
      for (int var2 = 0; var0.indexOf(1) >= 0; var2++) {
         if (var2 == var1.size()) {
            return var0;
         }

         String var3 = (String)var1.get(var2);
         var0 = var0.replaceFirst(field_4214, Matcher.quoteReplacement(var3));
      }

      return var0;
   }
}
