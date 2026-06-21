package me.mioclient.enum_;

import java.awt.Color;

public enum Priority {
   HIGH {
      @Override
      public Color method_152() {
         return new Color(225, 10, 10);
      }
   },
   MID {
      @Override
      public Color method_152() {
         return new Color(225, 170, 10);
      }
   },
   LOW {
      @Override
      public Color method_152() {
         return new Color(225, 225, 225);
      }
   };

   public Color method_152() {
      return null;
   }
}
