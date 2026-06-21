package me.mioclient.internal;

import me.mioclient.api.Class_0410;

public final class Class_1135 {
   public static final String field_3514 = "dev.tr7zw.entityculling.versionless.EntityCullingVersionlessBase";
   public static final Class_0410 field_3515;
   public static boolean field_3516;
   public static boolean field_3517;

   public Class_1135() {
      super();
   }

   public static void method_1013() {
      if (!field_3516 && field_3515 != null) {
         field_3516 = true;
         field_3517 = field_3515.method_458();
         field_3515.method_39(false);
      }
   }

   public static void method_1014() {
      if (field_3516 && field_3515 != null) {
         field_3516 = false;
         field_3515.method_39(field_3517);
      }
   }

   static {
      Class_1060 var0 = null;

      try {
         Class.forName("dev.tr7zw.entityculling.versionless.EntityCullingVersionlessBase");
         var0 = new Class_1060();
      } catch (ClassNotFoundException var2) {
      }

      field_3515 = var0;
   }
}
