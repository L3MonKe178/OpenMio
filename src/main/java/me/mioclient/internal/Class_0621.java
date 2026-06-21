package me.mioclient.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Class_0621 {
   public final List<Long> field_1986 = Collections.synchronizedList(new ArrayList<>());
   public final int field_713;
   public final int field_1987;

   public Class_0621(int var1) {
      this(1000, var1);
   }

   public Class_0621(int var1, int var2) {
      super();
      this.field_713 = var1;
      this.field_1987 = var2;
   }

   public void method_640() {
      this.field_1986.add(System.currentTimeMillis());
   }

   public boolean method_641() {
      return this.field_1986.size() > this.field_1987;
   }

   public void method_142() {
      this.field_1986.removeIf(var1 -> System.currentTimeMillis() > var1 + (long)this.field_713);
   }

   public void method_507() {
      this.field_1986.clear();
   }
}
