package me.mioclient.internal;

import com.google.gson.JsonElement;
import java.io.IOException;
import me.mioclient.Hub;
import me.mioclient.api.Class_0937;
import me.mioclient.api.Class_0988;
import me.mioclient.enum_.Class_0430;
import me.mioclient.enum_.Class_0836;
import me.mioclient.record.Class_0371;
import me.mioclient.setting.CustomSetting;
import me.mioclient.setting.Setting;
import me.mioclient.setting.StringSetting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0516 extends Class_0746 {
   public final Setting<String> field_1632 = new StringSetting("Save", "");
   public final Setting<Class_0836> field_1633 = new CustomSetting<>("Category", Class_0836.MODULES);
   public final Class_0311 field_1634;
   public Class_0430 field_1635 = Class_0430.NEW;
   public Class_0371 field_1636;
   public boolean field_80;

   public Class_0516(Class_0311 var1) {
      super("Preset");
      this.field_1634 = var1;
      this.field_1632.method_9(() -> {
         try {
            this.method_549().method_494(this.field_1632.getValue());
            var1.method_367().method_56();
         } catch (Exception var3) {
            var3.printStackTrace();
         }

         this.field_1632.method_78("");
      });
      this.field_1633.method_9(() -> {
         var1.method_9(this.field_1633.getValue());
         var1.method_367().method_56();
      });
   }

   @Override
   public void init() {
      this.method_56();
   }

   @Override
   public void method_2(DrawContext var1, MatrixStack var2, double var3, double var5) {
      if (this.field_80) {
         this.method_56();
         this.field_80 = false;
      }

      super.method_2(var1, var2, var3, var5);
   }

   public void method_56() {
      this.field_2378.clear();
      switch (this.field_1635) {
         case NEW:
            this.method_29(new Class_0414(this, Class_0988.field_3056, this.field_1633));
            this.method_29(new Class_0872(this, Class_0988.field_3056, this.field_1632));
            this.method_29(new Class_1266(this, "Refresh", () -> {
               this.method_549().method_56();
               this.field_1634.method_367().method_56();
            }));
            this.method_29(new Class_1266(this, "Restore", () -> {
               JsonElement var0 = Hub.field_2597.method_1174().method_358();
               if (var0 != null) {
                  Class_0836.ALL.fromJson(var0);
               }
            }));
            break;
         case EDIT:
            this.method_29(new Class_0906(this, new TextBuilder().method_2(this.field_1636.getName()).method_9("Preset \u0001")));
            this.method_29(new Class_1266(this, "Load", () -> {
               Hub.field_2597.method_1174().method_357();
               this.method_549().method_465(this.field_1636.getName());
            }));
            this.method_29(new Class_1266(this, "Save", () -> {
               try {
                  this.method_549().method_494(this.field_1636.getName());
                  this.field_1634.method_367().method_56();
               } catch (Exception var2) {
               }
            }));
            this.method_29(new Class_1266(this, "Delete", () -> {
               this.method_549().method_493(this.field_1636.getName());
               this.method_2(Class_0430.NEW);
            }));
            this.method_29(new Class_1266(this, "Back", () -> this.method_2(Class_0430.NEW)));
      }

      this.field_1634.method_367().method_56();
   }

   public void method_29(Class_0937 var1) {
      this.field_2378.add(var1);
   }

   public Class_0852 method_549() {
      return Hub.field_2597.method_2(this.field_1634.method_369());
   }

   public void method_9(Class_0371 var1) {
      this.field_1636 = var1;
      this.method_2(Class_0430.EDIT);
   }

   public Class_0371 method_550() {
      return this.field_1635 != Class_0430.EDIT ? null : this.field_1636;
   }

   public void method_2(Class_0430 var1) {
      this.field_1635 = var1;
      this.field_80 = true;
   }
}
