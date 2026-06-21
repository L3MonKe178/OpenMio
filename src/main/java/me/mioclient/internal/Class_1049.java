package me.mioclient.internal;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import me.mioclient.api.Class_0945;

public abstract class Class_1049<E, T extends Collection<E>> implements Class_0945<E, T> {
   public final T field_3243;

   public Class_1049(T var1) {
      super();
      this.field_3243 = (T)var1;
   }

   public Optional<E> method_2(Predicate<E> var1) {
      for (E var3 : this.field_3243) {
         if (var1.test(var3)) {
            return Optional.of(var3);
         }
      }

      return Optional.empty();
   }

   public E method_9(Predicate<E> var1) {
      for (E var3 : this.field_3243) {
         if (var1.test(var3)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public T getRegistry() {
      return this.field_3243;
   }
}
