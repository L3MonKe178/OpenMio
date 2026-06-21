package me.mioclient.enum_;

import me.mioclient.Hub;
import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1309;
import me.mioclient.module.abstract_.AbstractModule_40;
import net.minecraft.text.Text;

public enum Class_0602 implements Class_0013 {
   NAME("Name") {
      @Override
      public Text method_2(AbstractModule_40 var1) {
         return Text.literal("Hello " + Class_1309.field_4219.player.getName().getString() + " :')");
      }
   },
   UID("UID") {
      @Override
      public Text method_2(AbstractModule_40 var1) {
         return Text.literal("Hello uid" + Hub.field_2609.method_802() + " :')");
      }
   },
   CUSTOM("Custom") {
      @Override
      public Text method_2(AbstractModule_40 var1) {
         return Text.literal(var1.field_3763.getValue());
      }
   };

   public final String field_1874;

    Class_0602(String var3) {
      this.field_1874 = var3;
   }

   @Override
   public String getName() {
      return this.field_1874;
   }

   public Text method_2(AbstractModule_40 var1) {
      return null;
   }
}
