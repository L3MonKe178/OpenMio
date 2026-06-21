package me.mioclient.module.abstract_;

import me.mioclient.Hub;
import me.mioclient.enum_.SettingMode;
import me.mioclient.internal.Class_0719;
import me.mioclient.module.Category;
import me.mioclient.setting.CustomSetting3;
import me.mioclient.setting.Setting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class AbstractModule_31 extends AbstractModule_8 {
   public Object field_351;

   public AbstractModule_31() {
      super("FishTrap", "Traps your enemies in fish (crawling) position.", Category.COMBAT);
      this.method_139();
      this.method_5(false);
      this.unregister(this.field_2283);
      this.unregister(this.field_2284);
      this.unregister(this.field_2285);
      this.unregister(this.field_2286);
      this.field_2284.method_78(true);
      this.field_2285.method_78(false);
      this.field_2286.method_78(false);
   }

   @Override
   public Vec3d method_14(PlayerEntity var1) {
      if (!this.method_140().method_105()) {
         Box var2 = Hub.field_2612.method_2(var1, this.method_140().getValue());
         return Class_0719.method_2(var2);
      } else {
         return super.method_14(var1);
      }
   }

   @Override
   public boolean method_36(PlayerEntity var1) {
      return var1.getBoundingBox().getLengthY() > Double.longBitsToDouble(4607182418800017408L) ? false : super.method_36(var1);
   }

   public void method_139() {
      Setting var1 = this.add(new CustomSetting3("Extrapolation", 0, 0, 6));
      var1.method_2("None", SettingMode.MIN);
      this.field_351 = var1;
   }

   public Setting<Integer> method_140() {
      return (Setting<Integer>)this.field_351;
   }
}
