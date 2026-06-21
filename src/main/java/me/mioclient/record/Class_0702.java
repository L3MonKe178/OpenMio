package me.mioclient.record;

import me.mioclient.enum_.Class_0046;
import me.mioclient.internal.Class_0018;

public final class Class_0702 {
   public final int field_2226;
   public final Class_0046 field_2227;
   public final boolean field_2228;
   public static final Class_0702 field_2229 = new Class_0702(-1, Class_0046.TOGGLE, false);

   public Class_0702(int var1, Class_0046 var2, boolean var3) {
      super();
      this.field_2226 = var1;
      this.field_2227 = var2;
      this.field_2228 = var3;
   }

   public Class_0702 method_9(int var1) {
      return new Class_0702(var1, this.field_2227, this.field_2228);
   }

   public Class_0702 method_2(Class_0046 var1) {
      return new Class_0702(this.field_2226, var1, this.field_2228);
   }

   public Class_0702 method_9(boolean var1) {
      return new Class_0702(this.field_2226, this.field_2227, var1);
   }

   public boolean method_29() {
      return this.field_2226 < 0;
   }

   public String method_4() {
      return Class_0018.method_2(this);
   }

   public int method_38() {
      return this.field_2226;
   }

   public Class_0046 method_78() {
      return this.field_2227;
   }

   public boolean method_39() {
      return this.field_2228;
   }
}
