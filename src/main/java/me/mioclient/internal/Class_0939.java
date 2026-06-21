package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.api.Class_0013;

public class Class_0939<E extends Class_0013> extends Class_1049<E, List<E>> {
   public Class_0939() {
      super(new ArrayList<>());
   }

   public <T extends E> T method_2(Class<T> var1) {
      return (T)this.method_9(var1x -> var1x.getClass().equals(var1));
   }

   public boolean method_2(E var1) {
      if (var1 == null) {
         return false;
      } else {
         for (Class_0013 var3 : this.field_3243) {
            if (var3 != null && var3.getName() != null && var3.getName().equals(var1.getName())) {
               return false;
            }
         }

         this.field_3243.add((E)var1);
         return true;
      }
   }

   public boolean method_9(E var1) {
      if (var1 == null) {
         return false;
      } else {
         this.field_3243.remove(var1);
         return true;
      }
   }

   @Override public boolean register(E var1)   { return method_2(var1); }
   @Override public boolean unregister(E var1) { return method_9(var1); }
}
