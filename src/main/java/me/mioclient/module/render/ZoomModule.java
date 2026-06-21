package me.mioclient.module.render;

import me.mioclient.api.Class_0718;
import me.mioclient.event.Event_65;
import me.mioclient.event.Subscribe;
import me.mioclient.internal.Class_0055;
import me.mioclient.internal.Class_0095;
import me.mioclient.module.Category;
import me.mioclient.module.Module;
import me.mioclient.setting.Setting;
import net.minecraft.util.math.MathHelper;
import nick.Settings;

public class ZoomModule extends Module {
   public Setting<Boolean> field_4305;
   public Setting<Boolean> field_4306;
   public Setting<Boolean> field_4307;
   public Setting<Integer> field_4308;
   public final Class_0095 field_4309;
   public int field_3076;
   public boolean field_4310;
   public boolean field_1538;
   public final Class_0055 field_4311;
   public final Class_0055 field_4312;

   public ZoomModule() {
      super("Zoom", "Zooms your camera in.", Category.RENDER);
      Settings.initialize(this);
      this.field_4309 = new Class_0095(this);
      this.field_4311 = new Class_0055();
      this.field_4312 = new Class_0055();
   }

   @Override
   public void onDisable() {
      if (!this.method_535()) {
         this.field_4311.method_2(0.0F, (long)this.method_15(100));
         this.field_4312.method_2(0.0F, (long)this.method_15(100));
         field_4219.options.smoothCameraEnabled = this.field_4310;
      }
   }

   @Override
   public void onEnable() {
      if (this.method_535()) {
         this.disable();
      } else {
         field_4220.method_14(this.field_4309);
         if (!this.field_1538) {
            this.field_3076 = (Integer)field_4219.options.getFov().getValue();
         }

         this.field_4311.method_2(Float.intBitsToFloat(1065353216), (long)this.method_15(200));
         this.field_4310 = field_4219.options.smoothCameraEnabled;
      }
   }

   @Subscribe
   public void method_2(Event_65 var1) {
      if (var1.method_618() != 0.0 && this.field_4307.getValue()) {
         int var2 = (int)((float)this.field_3076 - (float)Math.abs(75 / this.field_4308.getValue() - this.field_3076) * this.method_1176());
         int var3 = (int)MathHelper.clamp(
            (float)((double)this.field_4312.method_45() + var1.method_618() * Double.longBitsToDouble(4621819117588971520L)),
            (float)(var2 - this.field_3076),
            (float)(var2 - 10)
         );
         this.field_4312.method_2((float)var3, (long)this.method_15(100));
         var1.method_463();
      }
   }

   public void method_231(int var1) {
      ((Class_0718)(Object)field_4219.options.getFov()).forceSetValue(var1);
   }

   public int method_1175() {
      int var1 = (int)((float)this.field_3076 - (float)Math.abs(75 / this.field_4308.getValue() - this.field_3076) * this.method_1176());
      return this.field_4307.getValue()
         ? (int)MathHelper.clamp((float)var1 - this.field_4312.method_45(), Float.intBitsToFloat(1092616192), Float.intBitsToFloat(1125515264))
         : var1;
   }

   public float method_1176() {
      return (float)(
         Double.longBitsToDouble(4607182418800017408L)
            - Math.pow((double)(Float.intBitsToFloat(1065353216) - this.field_4311.method_45()), Double.longBitsToDouble(4616189618054758400L))
      );
   }

   public int method_15(int var1) {
      return !this.field_4305.getValue() ? 0 : var1;
   }
}
