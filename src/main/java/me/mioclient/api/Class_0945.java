package me.mioclient.api;

import java.util.Collection;

public interface Class_0945<E, T extends Collection<E>> {
   T getRegistry();

   boolean register(E var1);

   boolean unregister(E var1);
}
