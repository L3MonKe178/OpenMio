package com.jagrosh.discordipc.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;
import net.lenni0451.reflect.Methods;

public class WinRegistry {
   public static final int HKEY_CURRENT_USER = -2147483647;
   public static final int HKEY_LOCAL_MACHINE = -2147483646;
   public static final int REG_SUCCESS = 0;
   public static final int KEY_ALL_ACCESS = 983103;
   public static final int KEY_READ = 131097;
   public static final Preferences userRoot = Preferences.userRoot();
   public static final Preferences systemRoot = Preferences.systemRoot();
   public static final Class<? extends Preferences> userClass = (Class<? extends Preferences>)userRoot.getClass();
   public static final Method regOpenKey;
   public static final Method regCloseKey;
   public static final Method regQueryValueEx;
   public static final Method regEnumValue;
   public static final Method regQueryInfoKey;
   public static final Method regEnumKeyEx;
   public static final Method regCreateKeyEx;
   public static final Method regSetValueEx;
   public static final Method regDeleteKey;
   public static final Method regDeleteValue;
   public static final float javaSpec;

   public WinRegistry() {
      super();
   }

   public static String readString(int var0, String var1, String var2) {
      if (var0 == -2147483646) {
         return readString(systemRoot, var0, var1, var2);
      } else if (var0 == -2147483647) {
         return readString(userRoot, var0, var1, var2);
      } else {
         throw new IllegalArgumentException("hkey=" + var0);
      }
   }

   public static String readString(long var0, String var2, String var3) {
      if (var0 == -2147483646L) {
         return readString(systemRoot, var0, var2, var3);
      } else if (var0 == -2147483647L) {
         return readString(userRoot, var0, var2, var3);
      } else {
         throw new IllegalArgumentException("hkey=" + var0);
      }
   }

   public static Map<String, String> readStringValues(int var0, String var1) {
      if (var0 == -2147483646) {
         return readStringValues(systemRoot, var0, var1);
      } else if (var0 == -2147483647) {
         return readStringValues(userRoot, var0, var1);
      } else {
         throw new IllegalArgumentException("hkey=" + var0);
      }
   }

   public static List<String> readStringSubKeys(int var0, String var1) {
      if (var0 == -2147483646) {
         return readStringSubKeys(systemRoot, var0, var1);
      } else if (var0 == -2147483647) {
         return readStringSubKeys(userRoot, var0, var1);
      } else {
         throw new IllegalArgumentException("hkey=" + var0);
      }
   }

   public static void createKey(int var0, String var1) {
      int[] var2;
      if (var0 == -2147483646) {
         var2 = createKey(systemRoot, var0, var1);
         Methods.invoke(systemRoot, regCloseKey, new Object[]{var2[0]});
      } else {
         if (var0 != -2147483647) {
            throw new IllegalArgumentException("hkey=" + var0);
         }

         var2 = createKey(userRoot, var0, var1);
         Methods.invoke(userRoot, regCloseKey, new Object[]{var2[0]});
      }

      if (var2[1] != 0) {
         throw new IllegalArgumentException("rc=" + var2[1] + "  key=" + var1);
      }
   }

   public static void createKey(long var0, String var2) {
      long[] var3;
      if (var0 == -2147483646L) {
         var3 = createKey(systemRoot, var0, var2);
         Methods.invoke(systemRoot, regCloseKey, new Object[]{var3[0]});
      } else {
         if (var0 != -2147483647L) {
            throw new IllegalArgumentException("hkey=" + var0);
         }

         var3 = createKey(userRoot, var0, var2);
         Methods.invoke(userRoot, regCloseKey, new Object[]{var3[0]});
      }

      if (var3[1] != 0L) {
         throw new IllegalArgumentException("rc=" + var3[1] + "  key=" + var2);
      }
   }

   public static void writeStringValue(int var0, String var1, String var2, String var3) {
      if (var0 == -2147483646) {
         writeStringValue(systemRoot, var0, var1, var2, var3);
      } else {
         if (var0 != -2147483647) {
            throw new IllegalArgumentException("hkey=" + var0);
         }

         writeStringValue(userRoot, var0, var1, var2, var3);
      }
   }

   public static void writeStringValue(long var0, String var2, String var3, String var4) {
      if (var0 == -2147483646L) {
         writeStringValue(systemRoot, var0, var2, var3, var4);
      } else {
         if (var0 != -2147483647L) {
            throw new IllegalArgumentException("hkey=" + var0);
         }

         writeStringValue(userRoot, var0, var2, var3, var4);
      }
   }

   public static void deleteKey(int var0, String var1) {
      int var2 = -1;
      if (var0 == -2147483646) {
         var2 = deleteKey(systemRoot, var0, var1);
      } else if (var0 == -2147483647) {
         var2 = deleteKey(userRoot, var0, var1);
      }

      if (var2 != 0) {
         throw new IllegalArgumentException("rc=" + var2 + "  key=" + var1);
      }
   }

   public static void deleteValue(int var0, String var1, String var2) {
      int var3 = -1;
      if (var0 == -2147483646) {
         var3 = deleteValue(systemRoot, var0, var1, var2);
      } else if (var0 == -2147483647) {
         var3 = deleteValue(userRoot, var0, var1, var2);
      }

      if (var3 != 0) {
         throw new IllegalArgumentException("rc=" + var3 + "  key=" + var1 + "  value=" + var2);
      }
   }

   public static int deleteValue(Preferences var0, int var1, String var2, String var3) {
      int[] var4 = (int[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var2), 983103});
      if (var4[1] != 0) {
         return var4[1];
      } else {
         int var5 = (Integer)Methods.invoke(var0, regDeleteValue, new Object[]{var4[0], toCstr(var3)});
         Methods.invoke(var0, regCloseKey, new Object[]{var4[0]});
         return var5;
      }
   }

   public static int deleteKey(Preferences var0, int var1, String var2) {
      return (Integer)Methods.invoke(var0, regDeleteKey, new Object[]{var1, toCstr(var2)});
   }

   public static String readString(Preferences var0, int var1, String var2, String var3) {
      int[] var4 = (int[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var2), 131097});
      if (var4[1] != 0) {
         return null;
      } else {
         byte[] var5 = (byte[])Methods.invoke(var0, regQueryValueEx, new Object[]{var4[0], toCstr(var3)});
         Methods.invoke(var0, regCloseKey, new Object[]{var4[0]});
         return var5 != null ? new String(var5).trim() : null;
      }
   }

   public static String readString(Preferences var0, long var1, String var3, String var4) {
      long[] var5 = (long[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var3), 131097});
      if (var5[1] != 0L) {
         return null;
      } else {
         byte[] var6 = (byte[])Methods.invoke(var0, regQueryValueEx, new Object[]{var5[0], toCstr(var4)});
         Methods.invoke(var0, regCloseKey, new Object[]{var5[0]});
         return var6 != null ? new String(var6).trim() : null;
      }
   }

   public static Map<String, String> readStringValues(Preferences var0, int var1, String var2) {
      HashMap var3 = new HashMap();
      int[] var4 = (int[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var2), 131097});
      if (var4[1] != 0) {
         return null;
      } else {
         int[] var5 = (int[])Methods.invoke(var0, regQueryInfoKey, new Object[]{var4[0]});
         int var6 = var5[0];
         int var7 = var5[3];

         for (int var8 = 0; var8 < var6; var8++) {
            byte[] var9 = (byte[])Methods.invoke(var0, regEnumValue, new Object[]{var4[0], var8, var7 + 1});
            String var10 = readString(var1, var2, new String(var9));
            var3.put(new String(var9).trim(), var10);
         }

         Methods.invoke(var0, regCloseKey, new Object[]{var4[0]});
         return var3;
      }
   }

   public static List<String> readStringSubKeys(Preferences var0, int var1, String var2) {
      ArrayList var3 = new ArrayList();
      int[] var4 = (int[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var2), 131097});
      if (var4[1] != 0) {
         return null;
      } else {
         int[] var5 = (int[])Methods.invoke(var0, regQueryInfoKey, new Object[]{var4[0]});
         int var6 = var5[0];
         int var7 = var5[3];

         for (int var8 = 0; var8 < var6; var8++) {
            byte[] var9 = (byte[])Methods.invoke(var0, regEnumKeyEx, new Object[]{var4[0], var8, var7 + 1});
            var3.add(new String(var9).trim());
         }

         Methods.invoke(var0, regCloseKey, new Object[]{var4[0]});
         return var3;
      }
   }

   public static int[] createKey(Preferences var0, int var1, String var2) {
      return (int[])Methods.invoke(var0, regCreateKeyEx, new Object[]{var1, toCstr(var2)});
   }

   public static long[] createKey(Preferences var0, long var1, String var3) {
      return (long[])Methods.invoke(var0, regCreateKeyEx, new Object[]{var1, toCstr(var3)});
   }

   public static void writeStringValue(Preferences var0, int var1, String var2, String var3, String var4) {
      int[] var5 = (int[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var2), 983103});
      Methods.invoke(var0, regSetValueEx, new Object[]{var5[0], toCstr(var3), toCstr(var4)});
      Methods.invoke(var0, regCloseKey, new Object[]{var5[0]});
   }

   public static void writeStringValue(Preferences var0, long var1, String var3, String var4, String var5) {
      long[] var6 = (long[])Methods.invoke(var0, regOpenKey, new Object[]{var1, toCstr(var3), 983103});
      Methods.invoke(var0, regSetValueEx, new Object[]{var6[0], toCstr(var4), toCstr(var5)});
      Methods.invoke(var0, regCloseKey, new Object[]{var6[0]});
   }

   public static byte[] toCstr(String var0) {
      byte[] var1 = new byte[var0.length() + 1];

      for (int var2 = 0; var2 < var0.length(); var2++) {
         var1[var2] = (byte)var0.charAt(var2);
      }

      var1[var0.length()] = 0;
      return var1;
   }

   static {
      try {
         javaSpec = Float.parseFloat(System.getProperty("java.specification.version"));
         regOpenKey = Methods.getDeclaredMethod(
            userClass, "WindowsRegOpenKey", new Class[]{javaSpec >= 11.0F ? long.class : int.class, byte[].class, int.class}
         );
         regCloseKey = Methods.getDeclaredMethod(userClass, "WindowsRegCloseKey", new Class[]{javaSpec >= 11.0F ? long.class : int.class});
         regQueryValueEx = Methods.getDeclaredMethod(userClass, "WindowsRegQueryValueEx", new Class[]{javaSpec >= 11.0F ? long.class : int.class, byte[].class});
         regEnumValue = Methods.getDeclaredMethod(
            userClass, "WindowsRegEnumValue", new Class[]{javaSpec >= 11.0F ? long.class : int.class, int.class, int.class}
         );
         regQueryInfoKey = Methods.getDeclaredMethod(userClass, "WindowsRegQueryInfoKey1", new Class[]{javaSpec >= 11.0F ? long.class : int.class});
         regEnumKeyEx = Methods.getDeclaredMethod(
            userClass, "WindowsRegEnumKeyEx", new Class[]{javaSpec >= 11.0F ? long.class : int.class, int.class, int.class}
         );
         regCreateKeyEx = Methods.getDeclaredMethod(userClass, "WindowsRegCreateKeyEx", new Class[]{javaSpec >= 11.0F ? long.class : int.class, byte[].class});
         regSetValueEx = Methods.getDeclaredMethod(
            userClass, "WindowsRegSetValueEx", new Class[]{javaSpec >= 11.0F ? long.class : int.class, byte[].class, byte[].class}
         );
         regDeleteValue = Methods.getDeclaredMethod(userClass, "WindowsRegDeleteValue", new Class[]{javaSpec >= 11.0F ? long.class : int.class, byte[].class});
         regDeleteKey = Methods.getDeclaredMethod(userClass, "WindowsRegDeleteKey", new Class[]{javaSpec >= 11.0F ? long.class : int.class, byte[].class});
      } catch (Exception var1) {
         throw new RuntimeException(var1);
      }
   }
}
