package me.mioclient.enum_;

import me.mioclient.setting.Setting;

public enum SettingMode {
   MIN {
      @Override
      public boolean method_2(Setting<?> var1) {
         return var1.getValue().equals(var1.method_100());
      }
   },
   MAX {
      @Override
      public boolean method_2(Setting<?> var1) {
         return var1.getValue().equals(var1.method_101());
      }
   };

   public boolean method_2(Setting<?> var1) {
      return false;
   }
}
