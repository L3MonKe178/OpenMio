package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum StrafeType implements Class_0013 {
   STRAFE {
      @Override
      public String getName() {
         return "Strafe";
      }
   },
   STRAFE_STRICT {
      @Override
      public String getName() {
         return "StrafeStrict";
      }
   },
   VANILLA {
      @Override
      public String getName() {
         return "Vanilla";
      }
   },
   ON_GROUND {
      @Override
      public String getName() {
         return "OnGround";
      }
   },
   GRIM {
      @Override
      public String getName() {
         return "Grim";
      }
   },
   NONE {
      @Override
      public String getName() {
         return "None";
      }
   };
}
