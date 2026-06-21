package me.mioclient.clickgui;

import me.mioclient.Hub;
import me.mioclient.module.Category;
import me.mioclient.module.Module;

/** Class_0967 — one panel per Category, filled with that Category's modules. */
public class CategoryPanel extends Panel {
   public final Category category;

   public CategoryPanel(ClickGuiScreen screen, Category category) {
      super(screen, prettyName(category.getName()));
      this.category = category;
      for (Module m : Hub.field_2599.method_2(category)) {
         this.buttons.add(new ModuleButton(this, m));
      }
   }

   /** FontRenderer.method_3 — title-case the category name. */
   private static String prettyName(String n) {
      if (n == null || n.isEmpty()) return "";
      return Character.toUpperCase(n.charAt(0)) + n.substring(1).toLowerCase();
   }
}
