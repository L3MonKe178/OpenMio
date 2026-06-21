package me.mioclient.internal;

import me.mioclient.api.Class_1153;
import me.mioclient.module.abstract_.AbstractModule_42;
import me.mioclient.record.Class_0681;

public class Class_0351 implements Class_1153 {
   public final AbstractModule_42 field_1150;

   public Class_0351(AbstractModule_42 var1) {
      super();
      this.field_1150 = var1;
   }

   @Override
   public boolean method_2(Class_0681 var1) {
      return (
            var1.method_172().getLengthX() == Double.longBitsToDouble(4607182418800017408L)
                  && var1.method_172().getLengthZ() == Double.longBitsToDouble(4607182418800017408L)
               || this.field_1150.field_767.getValue()
         )
         && field_4219.player.getEyePos().distanceTo(var1.method_406().toCenterPos()) <= (double)this.field_1150.field_770.getValue().floatValue()
         && Math.abs(field_4219.player.getEyePos().y - var1.method_406().toCenterPos().y) <= (double)this.field_1150.field_771.getValue().floatValue();
   }
}
