package me.mioclient.internal;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
import me.mioclient.api.Class_1309;
import me.mioclient.deobf.Named;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.record.Class_0828;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public final class Class_0178 extends Class_1049<Class_0828, List<Class_0828>> implements Class_1309 {
   public final Map<Class_0828, Set<Class_1138>> field_502 = new HashMap<>();

   public Class_0178() {
      super(new ArrayList<>());
      Class_1309.field_4220.method_14(this);
   }

   public void method_2(Named var1, BlockPos var2) {
      if (var2 != null) {
         this.method_2(var1, new Box(var2));
      }
   }

   public void method_2(Named var1, Box var2) {
      if (var2 != null) {
         Class_0828 var3 = this.method_9(var1x -> var1x.method_764().equals(var1));
         if (var3 == null) {
            throw new NoSuchElementException();
         } else {
            synchronized (this.field_502) {
               Set var5 = this.field_502.get(var3);
               if (var3.method_762()) {
                  for (Class_1138 var7 : (Iterable<Class_1138>)(Iterable<?>)(var5)) {
                     if (var2.equals(var7.method_1017())) {
                        var7.method_1016();
                        return;
                     }
                  }
               } else {
                  var5.clear();
               }

               Class_1138 var10 = new Class_1138();
               var10.method_29(var2);
               var10.method_1016();
               var5.add(var10);
            }
         }
      }
   }

   @Subscribe
   public void method_2(Event_1 var1) {
      synchronized (this.field_502) {
         for (Entry var4 : this.field_502.entrySet()) {
            ((Set<Class_1138>)var4.getValue()).removeIf(var1x -> var1x.method_37(((Class_0828)var4.getKey()).method_761()) == 0.0F);
         }
      }
   }

   @Subscribe
   public void method_5(Event_3 var1) {
      synchronized (this.field_502) {
         for (Entry var4 : this.field_502.entrySet()) {
            Class_0828 var5 = (Class_0828)var4.getKey();

            for (Class_1138 var7 : (Iterable<Class_1138>)(Iterable<?>)((Set)var4.getValue())) {
               var7.method_17(var5.method_760());
               var7.method_2(var1.method_10(), var5.method_758(), var5.method_759(), var5.method_761(), var5.method_763());
            }
         }
      }
   }

   public boolean method_2(Class_0828 var1) {
      this.field_502.put(var1, new ObjectLinkedOpenHashSet());
      return ((List)this.getRegistry()).add(var1);
   }

   public boolean method_9(Class_0828 var1) {
      throw new UnsupportedOperationException();
   }

   @Override public boolean register(Class_0828 var1)   { return method_2(var1); }
   @Override public boolean unregister(Class_0828 var1) {
      this.field_502.remove(var1);
      return ((java.util.List<?>)this.getRegistry()).remove(var1);
   }
}
