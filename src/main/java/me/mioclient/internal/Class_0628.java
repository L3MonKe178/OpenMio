package me.mioclient.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_11;
import me.mioclient.event.Event_20;
import me.mioclient.event.Event_49;
import me.mioclient.event.Subscribe;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public final class Class_0628 implements MioAPI {
   public final Map<Integer, Class_0308> field_2002 = Collections.synchronizedMap(new HashMap<>());

   public Class_0628() {
      super();
      field_4220.method_14(this);
   }

   @Subscribe
   public void method_2(Event_11 var1) {
      if (!this.method_535()) {
         for (AbstractClientPlayerEntity var3 : field_4219.world.getPlayers()) {
            this.field_2002.putIfAbsent(var3.getId(), new Class_0308(var3));
            this.field_2002.get(var3.getId()).method_142();
         }
      }
   }

   @Subscribe
   public void method_2(Event_49 var1) {
      synchronized (this.field_2002) {
         this.field_2002.remove(var1.getId());
      }
   }

   @Subscribe
   public void method_2(Event_20 var1) {
      this.field_2002.clear();
   }

   public synchronized Box method_2(PlayerEntity var1, int var2) {
      if (!this.field_2002.containsKey(var1.getId())) {
         return var1.getBoundingBox();
      } else {
         List var3 = this.field_2002.get(var1.getId()).method_359();
         return var3.isEmpty() ? var1.getBoundingBox() : (Box)var3.get(Math.min(var2, var3.size() - 1));
      }
   }
}
