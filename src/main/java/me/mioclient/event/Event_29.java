package me.mioclient.event;

import me.mioclient.internal.Class_0605;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public class Event_29 extends Class_0605 {
   public final RegistryEntry<StatusEffect> field_3956;

   public Event_29(RegistryEntry<StatusEffect> var1) {
      super();
      this.field_3956 = var1;
   }

   public RegistryEntry<StatusEffect> method_1026() {
      return this.field_3956;
   }
}
