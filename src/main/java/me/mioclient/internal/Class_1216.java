package me.mioclient.internal;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Class_1216 extends DataOutputStream {
   public final ByteArrayOutputStream field_3806;

   public Class_1216(ByteArrayOutputStream var1) {
      super(var1);
      this.field_3806 = var1;
   }

   public byte[] method_1009() {
      return this.field_3806.toByteArray();
   }
}
