package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.deobf.Named;
import me.mioclient.enum_.Class_1072;
import me.mioclient.record.Class_0702;

public abstract class Class_0260 extends Named {
   public final List<String> field_789 = new ArrayList<>();
   public final Class_1072 field_790;
   public final Class_0702 field_40;

   public Class_0260(String var1, Class_1072 var2, Class_0702 var3) {
      super(var1);
      this.field_790 = var2;
      this.field_40 = var3;
   }

   public abstract void run();

   public Class_1072 method_2() {
      return this.field_790;
   }

   public Class_0702 getKeybind() {
      return this.field_40;
   }

   public List<String> method_9() {
      return this.field_789;
   }

   public void method_7(String var1) {
      for (String var5 : var1.split(";")) {
         CommandManager.method_7(var5.trim());
      }
   }
}
