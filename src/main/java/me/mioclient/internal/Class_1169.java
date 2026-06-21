package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;
import me.mioclient.api.Class_0098;

public class Class_1169 {
   public static List<Class_0098> field_3626 = new ArrayList<>();

   public Class_1169() {
      super();
   }

   public static Class_0098 method_9(short var0) {
      for (Class_0098 var2 : field_3626) {
         if (var2.method_27() == var0) {
            return var2;
         }
      }

      return null;
   }

   static {
      field_3626.add(new Class_0188());
      field_3626.add(new Class_1228());
      field_3626.add(new Class_0167());
      field_3626.add(new Class_0085());
      field_3626.add(new Class_1065());
      field_3626.add(new Class_1335());
      field_3626.add(new Class_0903());
      field_3626.add(new Class_1325());
      field_3626.add(new Class_0515());
      field_3626.add(new Class_0921());
      field_3626.add(new Class_0022());
      field_3626.add(new Class_0741());
      field_3626.add(new Class_0476());

      for (Class_0098 var1 : field_3626) {
         for (Class_0098 var3 : field_3626) {
            if (var1 != var3 && var1.method_27() == var3.method_27()) {
               throw new RuntimeException("Duplicate packet ID (%s/%s)".formatted(var1.getClass().getName(), var3.getClass().getName()));
            }
         }
      }
   }
}
