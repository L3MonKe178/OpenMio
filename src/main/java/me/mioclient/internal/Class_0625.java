package me.mioclient.internal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_7;
import me.mioclient.event.Subscribe;

public class Class_0625 extends Class_1049<Class_0140, List<Class_0140>> implements MioAPI {
   public final Queue<Class_0140> field_1992 = new ArrayDeque<>();

   public Class_0625() {
      super(Collections.synchronizedList(new ArrayList<>()));
      field_4220.method_14(this);
   }

   @Subscribe(
      method_800 = -100
   )
   public void method_9(Event_7 var1) {
      synchronized ((List)this.field_3243) {
         while (!this.field_1992.isEmpty()) {
            this.field_3243.add(this.field_1992.poll());
         }

         this.field_3243.removeIf(Class_0140::method_166);
      }
   }

   public void method_2(Runnable var1, int var2) {
      Class_0140 var3 = new Class_0140(var1, var2);
      if (field_4219.isOnThread()) {
         this.method_2(var3);
      } else {
         field_4219.executeSync(() -> this.method_2(var3));
      }
   }

   public boolean method_2(Class_0140 var1) {
      synchronized ((List)this.field_3243) {
         return this.field_1992.add(var1);
      }
   }

   public boolean method_9(Class_0140 var1) {
      throw new UnsupportedOperationException();
   }

   @Override public boolean register(Class_0140 var1)   { return method_2(var1); }
   @Override public boolean unregister(Class_0140 var1) {
      return ((java.util.List<?>)this.getRegistry()).remove(var1);
   }
}
