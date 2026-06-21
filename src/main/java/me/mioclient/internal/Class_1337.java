package me.mioclient.internal;

import me.mioclient.event.Event_1;
import me.mioclient.event.Subscribe;

public class Class_1337 extends Class_0508 {
   public Class_1337() {
      super();
   }

   @Subscribe(
      method_800 = -200
   )
   public void method_9(Event_1 var1) {
      if (this.method_537() != Float.intBitsToFloat(1065353216)) {
         for (int var2 = 1; (float)var2 <= this.method_537() - Float.intBitsToFloat(1065353216); var2++) {
            Class_0654.method_633();
         }
      }
   }
}
