package me.mioclient.exception;

public class Class_0805 extends RuntimeException {
   public Class_0805(Class<?> var1) {
      super("No registered lambda listener for '" + var1.getName() + "'.");
   }
}
