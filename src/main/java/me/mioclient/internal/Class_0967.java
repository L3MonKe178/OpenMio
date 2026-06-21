package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.MioAPI;
import me.mioclient.module.Category;
import me.mioclient.module.Module;

public class Class_0967 extends Class_0746 implements MioAPI {
   public Category field_37;

   public Class_0967(Category var1) {
      super(FontRenderer.method_3(var1.getName()));
      this.field_37 = var1;

      for (Module var3 : Hub.field_2599.method_2(var1)) {
         Class_0050 var4 = new Class_0050(var3, this, 0);
         this.field_2378.add(var4);
      }

      this.method_142();
   }

   @Override
   public boolean method_2(Class_0937 var1) {
      net.minecraft.client.gui.widget.TextFieldWidget search =
            Hub.method_755() == null ? null : Hub.method_755().method_452();
      String query = search == null ? "" : search.getText();
      if (var1 instanceof Class_0050 var2 && !query.isEmpty()) {
         for (String var6 : var2.method_65().getAliases()) {
            if (var6.toLowerCase().contains(query.toLowerCase())) {
               return true;
            }
         }

         return false;
      }

      return true;
   }
}
