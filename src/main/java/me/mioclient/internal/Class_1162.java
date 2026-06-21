package me.mioclient.internal;

import java.util.function.Supplier;
import me.mioclient.api.MioAPI;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Class_1162 extends Class_0121 implements MioAPI {
   public final RegistryEntry<StatusEffect> field_3598;

   public Class_1162(RegistryEntry<StatusEffect> var1, Supplier<Boolean> var2) {
      super(
         () -> {
            StatusEffectInstance var1x = field_4219.player.getStatusEffect(var1);
            if (var1x == null) {
               return Text.empty();
            } else {
               String var2x = StatusEffectUtil.getDurationText(var1x, Float.intBitsToFloat(1065353216), field_4219.world.getTickManager().getTickRate())
                  .getString();
               if (var2x.startsWith("0")) {
                  var2x = var2x.substring(1);
               }

               String var3 = var1x.getAmplifier() == 0 ? "" : new TextBuilder().method_2(var1x.getAmplifier() + 1).method_9("\u0001 ");
               return Text.literal("%s %s%s%s".formatted(((StatusEffect)var1.value()).getName().getString(), var3, Formatting.WHITE, var2x));
            }
         },
         var2
      );
      this.field_3598 = var1;
   }

   public RegistryEntry<StatusEffect> method_1026() {
      return this.field_3598;
   }
}
