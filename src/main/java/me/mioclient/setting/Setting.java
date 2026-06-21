package me.mioclient.setting;

import java.util.Objects;
import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.api.Class_1146;
import me.mioclient.api.Class_1339;
import me.mioclient.deobf.Named;
import me.mioclient.enum_.SettingMode;
import me.mioclient.internal.Class_0841;

public abstract class Setting<T> extends Named implements Class_1339<T>, Class_1146 {
   public T field_3117;
   public boolean field_111;
   public boolean field_3118;
   public boolean field_3119;
   public boolean field_3120;
   public Predicate<T> field_3121;
   public String field_3122 = this.getName();
   public String field_3123 = "";
   public Runnable field_3124;
   public T field_3125;
   public T field_3126;
   public T field_3127;
   public T field_3128;
   public T field_3129;
   public boolean field_3130;
   public boolean field_3131;
   public SettingMode field_3132;

   public Setting(String var1, T var2, T var3, T var4, Predicate<T> var5) {
      super(var1);
      this.field_3117 = (T)var2;
      this.field_3127 = (T)var3;
      this.field_3128 = (T)var4;
      this.field_3121 = var5;
      this.field_3125 = (T)var2;
      this.field_3126 = (T)var2;
      this.field_3129 = (T)var2;
      this.field_3130 = true;
   }

   public Setting(String var1, T var2, Predicate<T> var3) {
      super(var1);
      this.field_3117 = (T)var2;
      this.field_3121 = var3;
      this.field_3125 = (T)var2;
      this.field_3126 = (T)var2;
      this.field_3129 = (T)var2;
   }

   public Setting(String var1, T var2, T var3, T var4) {
      super(var1);
      this.field_3117 = (T)var2;
      this.field_3127 = (T)var3;
      this.field_3128 = (T)var4;
      this.field_3125 = (T)var2;
      this.field_3126 = (T)var2;
      this.field_3129 = (T)var2;
      this.field_3130 = true;
   }

   public Setting(String var1, T var2) {
      super(var1);
      this.field_3117 = (T)var2;
      this.field_3125 = (T)var2;
      this.field_3126 = (T)var2;
      this.field_3129 = (T)var2;
   }

   @Override
   public T getValue() {
      return this.field_3125;
   }

   public T method_340() {
      return this.field_3126;
   }

   public void method_4(T var1) {
      this.field_3126 = (T)var1;
   }

   public T method_99() {
      return this.field_3117;
   }

   public T method_100() {
      return this.field_3127;
   }

   public T method_101() {
      return this.field_3128;
   }

   public boolean method_105() {
      return this.method_100().equals(this.getValue());
   }

   public boolean method_84() {
      return this.method_101().equals(this.getValue());
   }

   @Override
   public String getConfigName() {
      return this.field_3122;
   }

   public void method_31(String var1) {
      this.field_3122 = var1;
   }

   public String method_294() {
      return Class_0841.method_5((Enum<?>)this.field_3126);
   }

   public String method_157() {
      return this.field_3123;
   }

   public <T> String method_38(T var1) {
      return var1.getClass().getSimpleName();
   }

   public void reset() {
      this.method_78(this.field_3117);
   }

   public abstract void method_78(String var1);

   public void method_78(T var1) {
      this.method_4((T)var1);
      if (this.field_3130) {
         if (((Number)this.field_3127).floatValue() > ((Number)var1).floatValue()) {
            this.method_4(this.field_3127);
         }

         if (((Number)this.field_3128).floatValue() < ((Number)var1).floatValue()) {
            this.method_4(this.field_3128);
         }
      }

      this.field_3125 = this.field_3126;
      if (this.field_3125 != this.field_3129) {
         this.method_6();
      }

      this.field_3129 = this.field_3125;
   }

   public void method_39(T var1) {
      this.field_3117 = (T)var1;
   }

   public T method_80() {
      return this.field_3129;
   }

   public Setting<T> method_460() {
      this.field_3118 = true;
      return this;
   }

   public Setting<T> method_220() {
      this.field_3120 = true;
      this.method_460();
      return this;
   }

   public final Setting<T> method_2(String var1, SettingMode var2) {
      this.field_3123 = var1;
      this.field_3132 = var2;
      return this;
   }

   public void method_9(Runnable var1) {
      this.field_3124 = var1;
   }

   public void method_6() {
      if (this.field_3124 != null) {
         try {
            this.field_3124.run();
         } catch (Throwable var2) {
         }
      }
   }

   public Setting<T> method_8() {
      this.field_3119 = true;
      return this;
   }

   @SafeVarargs
   public final Setting<T> method_2(Setting<Boolean>... var1) {
      this.method_5(var1x -> {
         for (Setting var5 : var1) {
            boolean var6 = var5.field_3120 || var5.field_3118;
            if (var6 ? !var5.method_194() : !(Boolean)var5.getValue()) {
               return false;
            }
         }

         return true;
      });
      return this;
   }

   public void method_5(Predicate<T> var1) {
      if (this.field_3121 == null) {
         this.field_3121 = var1;
      } else {
         this.field_3121 = this.field_3121.and(var1);
      }
   }

   public void method_334() {
      this.method_5(var0 -> Hub.field_2630.method_264());
   }

   public boolean method_82() {
      return this.field_3125 instanceof Number;
   }

   public boolean method_310() {
      return this.field_3125 instanceof Enum;
   }

   public boolean method_270() {
      return this.field_3125 instanceof String;
   }

   public boolean method_468() {
      return this.method_82() && !this.field_3123.isEmpty() && this.field_3132.method_2(this);
   }

   public boolean method_176() {
      return this.field_3121 == null ? true : this.field_3121.test(this.getValue());
   }

   public boolean method_194() {
      return this.field_111 && this.field_3118;
   }

   public boolean method_138() {
      return this.field_3130;
   }

   public void method_214() {
      this.field_3130 = false;
   }

   public boolean method_158() {
      return this.field_3120;
   }

   public void method_7(boolean var1) {
      this.field_3131 = var1;
   }

   public boolean method_113() {
      return this.field_3131;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null || this.getClass() != var1.getClass()) {
         return false;
      } else if (!super.equals(var1)) {
         return false;
      } else {
         Setting var2 = (Setting)var1;
         return Objects.equals(this.field_3122, var2.field_3122);
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), this.field_3122);
   }
}
