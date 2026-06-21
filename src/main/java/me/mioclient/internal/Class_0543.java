package me.mioclient.internal;

import java.util.LinkedList;

public final class Class_0543<E> extends LinkedList<E> {
   public final int field_1746;

   @Override
   public boolean add(E var1) {
      if (this.size() >= this.field_1746) {
         this.removeFirst();
      }

      return this.contains(var1) ? false : super.add((E)var1);
   }

   public Class_0543(int var1) {
      super();
      this.field_1746 = var1;
   }
}
