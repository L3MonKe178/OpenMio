package me.mioclient.setting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public abstract class CustomSetting2<T> extends Setting<Set<T>> {
   public T field_602;

   @SafeVarargs
   public CustomSetting2(String var1, T... var2) {
      super(var1, new ObjectOpenHashSet());
      ((Set)this.method_99()).addAll(List.of(var2));
      ((Set)this.getValue()).addAll(List.of(var2));
   }

   @SafeVarargs
   public CustomSetting2(String var1, Predicate<Set<T>> var2, T... var3) {
      super(var1, new ObjectOpenHashSet(), var2);
      ((Set)this.method_99()).addAll(List.of(var3));
      ((Set)this.getValue()).addAll(List.of(var3));
   }

   public abstract T method_38(String var1);

   public abstract String method_9(T var1);

   public abstract Collection<T> method_230();

   public Set<String> method_251() {
      ObjectOpenHashSet var1 = new ObjectOpenHashSet();

      for (Object var3 : (Set)this.getValue()) {
         var1.add(this.method_9((T)var3));
      }

      return var1;
   }

   @Override
   public void method_78(String var1) {
      Object var2 = this.method_38(var1);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.method_29((T)var2)) {
            this.method_7((T)var2);
         } else {
            this.method_5((T)var2);
         }
      }
   }

   public Collection<String> method_186() {
      HashSet var1 = new HashSet();

      for (Object var3 : this.method_230()) {
         var1.add(this.method_9((T)var3));
      }

      return var1;
   }

   public void method_5(T var1) {
      if (var1 != null) {
         ((Set)this.getValue()).add(var1);
         this.field_602 = (T)var1;
         if (this.field_3124 != null) {
            this.field_3124.run();
         }
      }
   }

   public void method_39(String var1) {
      if (var1 != null) {
         this.method_5(this.method_38(var1));
      }
   }

   public void method_7(T var1) {
      if (var1 != null) {
         ((Set)this.getValue()).remove(var1);
         this.field_602 = (T)var1;
         if (this.field_3124 != null) {
            this.field_3124.run();
         }
      }
   }

   public void method_30(String var1) {
      if (var1 != null) {
         this.method_7(this.method_38(var1));
      }
   }

   public boolean method_29(T var1) {
      return var1 == null ? false : ((Set)this.getValue()).contains(var1);
   }

   public boolean method_16(String var1) {
      return var1 == null ? false : this.method_29(this.method_38(var1));
   }

   public T method_98() {
      return this.field_602;
   }

   @Override
   public JsonElement toJson() {
      JsonArray var1 = new JsonArray();

      for (Object var3 : (Set)this.getValue()) {
         var1.add(this.method_9((T)var3));
      }

      return var1;
   }
}
