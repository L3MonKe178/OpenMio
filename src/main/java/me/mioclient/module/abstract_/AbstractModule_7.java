package me.mioclient.module.abstract_;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0274;
import me.mioclient.internal.Class_0121;
import me.mioclient.internal.Class_0723;
import me.mioclient.internal.TextBuilder;
import me.mioclient.setting.BooleanSetting;
import me.mioclient.setting.Setting;
import me.mioclient.setting.StringSetting;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AbstractModule_7 extends AbstractModule_26 {
   public final Setting<Boolean> field_99 = this.add(new BooleanSetting("VersionColor", true));
   public final Setting<String> field_100 = this.add(new StringSetting("Text", "Mio"));

   public AbstractModule_7() {
      super("Watermark");
      Class_0121 var1 = new Class_0121(
         () -> {
            String var1x = this.method_61();
            return Text.literal(
               new TextBuilder()
                  .method_2((Object)var1x)
                  .method_2(String.valueOf(this.field_99.getValue() ? Formatting.WHITE : ""))
                  .method_2((this.field_100.getValue() != null ? this.field_100.getValue().trim() : ""))
                  .method_9("\u0001\u0001\u0001")
            );
         },
         () -> true
      );
      this.method_2(new Class_0723(this, var1));
      this.method_14().method_2(Class_0274.TOP_LEFT);
      this.method_38(true);
   }

   public String method_61() {
      return Hub.field_2609.method_802() == 62 ? " v2.1.7+" : " v2.1.7";
   }
}
