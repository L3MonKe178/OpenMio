package me.mioclient.module.abstract_;

import me.mioclient.enum_.Class_0602;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Class_0723;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.Setting;
import me.mioclient.setting.StringSetting;

public class AbstractModule_40 extends AbstractModule_26 {
   public Setting<Class_0602> field_3762 = this.add(new CustomSetting<>("Mode", Class_0602.NAME));
   public final Setting<String> field_3763 = this.add(new StringSetting("Text", "Welcome to Mio.", var1 -> this.field_3762.getValue() == Class_0602.CUSTOM));

   public AbstractModule_40() {
      super("Welcomer");
      this.method_2(new Class_0723(this, new Class_0121(() -> (this.field_3762.getValue() != null ? this.field_3762.getValue().method_2(this) : null), () -> true)));
   }
}
