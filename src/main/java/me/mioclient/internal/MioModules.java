package me.mioclient.internal;

import me.mioclient.module.Module;

public final class MioModules {
   public static void init(Class_0965 manager) {
      String[] classes = new String[] {
         "me.mioclient.module.abstract_.AbstractModule_10",
         "me.mioclient.module.abstract_.AbstractModule_1",
         "me.mioclient.module.abstract_.AbstractModule_11",
         "me.mioclient.module.abstract_.AbstractModule_12",
         "me.mioclient.module.abstract_.AbstractModule_13",
         "me.mioclient.module.abstract_.AbstractModule_15",
         "me.mioclient.module.abstract_.AbstractModule_16",
         "me.mioclient.module.abstract_.AbstractModule_17",
         "me.mioclient.module.abstract_.AbstractModule_2",
         "me.mioclient.module.abstract_.AbstractModule_20",
         "me.mioclient.module.abstract_.AbstractModule_14",
         "me.mioclient.module.abstract_.AbstractModule_23",
         "me.mioclient.module.abstract_.AbstractModule_19",
         "me.mioclient.module.abstract_.AbstractModule_18",
         "me.mioclient.module.abstract_.AbstractModule_24",
         "me.mioclient.module.abstract_.AbstractModule_27",
         "me.mioclient.module.abstract_.AbstractModule_21",
         "me.mioclient.module.abstract_.AbstractModule_22",
         "me.mioclient.module.abstract_.AbstractModule_25",
         "me.mioclient.module.abstract_.AbstractModule_30",
         "me.mioclient.module.abstract_.AbstractModule_28",
         "me.mioclient.module.abstract_.AbstractModule_3",
         "me.mioclient.module.abstract_.AbstractModule_29",
         "me.mioclient.module.abstract_.AbstractModule_34",
         "me.mioclient.module.abstract_.AbstractModule_33",
         "me.mioclient.module.abstract_.AbstractModule_38",
         "me.mioclient.module.abstract_.AbstractModule_31",
         "me.mioclient.module.abstract_.AbstractModule_7",
         "me.mioclient.module.abstract_.AbstractModule_35",
         "me.mioclient.module.abstract_.AbstractModule_37",
         "me.mioclient.module.abstract_.AbstractModule_40",
         "me.mioclient.module.abstract_.AbstractModule_6",
         "me.mioclient.module.abstract_.AbstractModule_4",
         "me.mioclient.module.abstract_.AbstractModule_36",
         "me.mioclient.module.abstract_.AbstractModule_42",
         "me.mioclient.module.abstract_.AbstractModule_5",
         "me.mioclient.module.abstract_.AbstractModule_9",
         "me.mioclient.module.abstract_.AbstractModule_39",
         "me.mioclient.module.abstract_.AbstractModule_8",
         "me.mioclient.module.client.DiscordModule",
         "me.mioclient.module.client.FontsModule",
         "me.mioclient.module.client.HUDModule",
         "me.mioclient.module.client.IRCModule",
         "me.mioclient.module.client.NotificationsModule",
         "me.mioclient.module.client.UIModule",
         "me.mioclient.module.combat.AimAssistModule",
         "me.mioclient.module.combat.AntiBotModule",
         "me.mioclient.module.combat.AntiPhaseModule",
         "me.mioclient.module.combat.ArrowsModule",
         "me.mioclient.module.combat.AuraModule",
         "me.mioclient.module.combat.AutoArmorModule",
         "me.mioclient.module.combat.AutoBowReleaseModule",
         "me.mioclient.module.combat.AutoClickerModule",
         "me.mioclient.module.combat.AutoCrystalModule",
         "me.mioclient.module.combat.AutoExpModule",
         "me.mioclient.module.combat.AutoLogModule",
         "me.mioclient.module.combat.BowAimModule",
         "me.mioclient.module.combat.CombatmineModule",
         "me.mioclient.module.combat.CriticalsModule",
         "me.mioclient.module.combat.KeyPearlModule",
         "me.mioclient.module.combat.LegacyCrystalModule",
         "me.mioclient.module.combat.MainhandModule",
         "me.mioclient.module.combat.OffhandModule",
         "me.mioclient.module.combat.PusherModule",
         "me.mioclient.module.combat.SelfFillModule",
         "me.mioclient.module.combat.SelfWebModule",
         "me.mioclient.module.combat.TriggerModule",
         "me.mioclient.module.exploit.AntiHungerModule",
         "me.mioclient.module.exploit.AntiLevitationModule",
         "me.mioclient.module.exploit.AntiVanishModule",
         "me.mioclient.module.exploit.FakeVanillaModule",
         "me.mioclient.module.exploit.FastLatencyModule",
         "me.mioclient.module.exploit.FastProjectileModule",
         "me.mioclient.module.exploit.GrimDisablerModule",
         "me.mioclient.module.exploit.HitboxDesyncModule",
         "me.mioclient.module.exploit.IllegalDisconnectModule",
         "me.mioclient.module.exploit.MultiTaskModule",
         "me.mioclient.module.exploit.NewChunksModule",
         "me.mioclient.module.exploit.NoMineAnimationModule",
         "me.mioclient.module.exploit.PhaseModule",
         "me.mioclient.module.exploit.PingSpoofModule",
         "me.mioclient.module.exploit.PortalGodModeModule",
         "me.mioclient.module.exploit.ReachModule",
         "me.mioclient.module.exploit.RocketModule",
         "me.mioclient.module.exploit.TimerModule",
         "me.mioclient.module.exploit.XCarryModule",
         "me.mioclient.module.misc.AnnouncerModule",
         "me.mioclient.module.misc.AntiAFKModule",
         "me.mioclient.module.misc.AntiAimModule",
         "me.mioclient.module.misc.AntiCyrillicModule",
         "me.mioclient.module.misc.AntiQuitModule",
         "me.mioclient.module.misc.AntiSpamModule",
         "me.mioclient.module.misc.AutoReconnectModule",
         "me.mioclient.module.misc.AutoRespawnModule",
         "me.mioclient.module.misc.AutoSignModule",
         "me.mioclient.module.misc.BetterChatModule",
         "me.mioclient.module.misc.ChatFilterModule",
         "me.mioclient.module.misc.ChestSearchBarModule",
         "me.mioclient.module.misc.CoordLoggerModule",
         "me.mioclient.module.misc.CustomDeathTextModule",
         "me.mioclient.module.misc.DiscordNotifsModule",
         "me.mioclient.module.misc.ExtraScreenshotModule",
         "me.mioclient.module.misc.ExtraTabModule",
         "me.mioclient.module.misc.HeavenModule",
         "me.mioclient.module.misc.KillEffectsModule",
         "me.mioclient.module.misc.MiddleClickModule",
         "me.mioclient.module.misc.NoNarratorModule",
         "me.mioclient.module.misc.NoPacketKickModule",
         "me.mioclient.module.misc.NoRotateModule",
         "me.mioclient.module.misc.PMSoundModule",
         "me.mioclient.module.misc.SkinFlickerModule",
         "me.mioclient.module.misc.SoundBlockerModule",
         "me.mioclient.module.misc.SpammerModule",
         "me.mioclient.module.misc.StashFinderModule",
         "me.mioclient.module.misc.SwingModule",
         "me.mioclient.module.misc.UnfocusedFPSModule",
         "me.mioclient.module.misc.VisualRangeModule",
         "me.mioclient.module.movement.AntiVoidModule",
         "me.mioclient.module.movement.AutoWalkModule",
         "me.mioclient.module.movement.ElytraFlyModule",
         "me.mioclient.module.movement.EntityControlModule",
         "me.mioclient.module.movement.FakeLagModule",
         "me.mioclient.module.movement.FastFallModule",
         "me.mioclient.module.movement.FastLadderModule",
         "me.mioclient.module.movement.FastSwimModule",
         "me.mioclient.module.movement.FastWebModule",
         "me.mioclient.module.movement.FireworksModule",
         "me.mioclient.module.movement.FlightModule",
         "me.mioclient.module.movement.HighJumpModule",
         "me.mioclient.module.movement.HoleSnapModule",
         "me.mioclient.module.movement.JesusModule",
         "me.mioclient.module.movement.LongJumpModule",
         "me.mioclient.module.movement.NoFallModule",
         "me.mioclient.module.movement.NoJumpDelayModule",
         "me.mioclient.module.movement.NoSlowModule",
         "me.mioclient.module.movement.SafeWalkModule",
         "me.mioclient.module.movement.SpeedModule",
         "me.mioclient.module.movement.SpiralModule",
         "me.mioclient.module.movement.SprintModule",
         "me.mioclient.module.movement.StepModule",
         "me.mioclient.module.movement.VelocityModule",
         "me.mioclient.module.movement.WarpModule",
         "me.mioclient.module.player.AutoBreedModule",
         "me.mioclient.module.player.AutoCraftModule",
         "me.mioclient.module.player.AutoEatModule",
         "me.mioclient.module.player.AutoFarmModule",
         "me.mioclient.module.player.AutoFishModule",
         "me.mioclient.module.player.AutoMountModule",
         "me.mioclient.module.player.AutoNameTagModule",
         "me.mioclient.module.player.AutoTameModule",
         "me.mioclient.module.player.AutoToolModule",
         "me.mioclient.module.player.AutoUpgradeModule",
         "me.mioclient.module.player.BlockMixerModule",
         "me.mioclient.module.player.ChestStealerModule",
         "me.mioclient.module.player.FastPlaceModule",
         "me.mioclient.module.player.FreecamModule",
         "me.mioclient.module.player.InventoryCleanerModule",
         "me.mioclient.module.player.InventoryTweaksModule",
         "me.mioclient.module.player.ItemSaverModule",
         "me.mioclient.module.player.NameProtectModule",
         "me.mioclient.module.player.NoInteractModule",
         "me.mioclient.module.player.NukerModule",
         "me.mioclient.module.player.ReplenishModule",
         "me.mioclient.module.player.RotationLockModule",
         "me.mioclient.module.player.ScaffoldModule",
         "me.mioclient.module.player.SpeedMineModule",
         "me.mioclient.module.render.AmbienceModule",
         "me.mioclient.module.render.AnimationsModule",
         "me.mioclient.module.render.BlurModule",
         "me.mioclient.module.render.BordersModule",
         "me.mioclient.module.render.BreakHighlightModule",
         "me.mioclient.module.render.ChamsModule",
         "me.mioclient.module.render.CrosshairModule",
         "me.mioclient.module.render.ESPModule",
         "me.mioclient.module.render.FreeLookModule",
         "me.mioclient.module.render.GlintModule",
         "me.mioclient.module.render.HighlightModule",
         "me.mioclient.module.render.HitmarkerModule",
         "me.mioclient.module.render.HoleESPModule",
         "me.mioclient.module.render.LogoutSpotsModule",
         "me.mioclient.module.render.MarkersModule",
         "me.mioclient.module.render.NameTagsModule",
         "me.mioclient.module.render.NoBobModule",
         "me.mioclient.module.render.NoRenderModule",
         "me.mioclient.module.render.ParticlesModule",
         "me.mioclient.module.render.PhaseESPModule",
         "me.mioclient.module.render.SearchModule",
         "me.mioclient.module.render.ShaderModule",
         "me.mioclient.module.render.SkeletonModule",
         "me.mioclient.module.render.SkyColorModule",
         "me.mioclient.module.render.TracersModule",
         "me.mioclient.module.render.TrailsModule",
         "me.mioclient.module.render.TrajectoriesModule",
         "me.mioclient.module.render.TunnelsModule",
         "me.mioclient.module.render.ViewClipModule",
         "me.mioclient.module.render.ViewModelModule",
         "me.mioclient.module.render.VoidESPModule",
         "me.mioclient.module.render.WaypointsModule",
         "me.mioclient.module.render.XrayModule",
         "me.mioclient.module.render.ZoomModule",
      };
      sun.misc.Unsafe unsafe = null;
      try {
         java.lang.reflect.Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         unsafe = (sun.misc.Unsafe) f.get(null);
      } catch (Throwable t) {
         System.err.println("[Mio] Cannot get Unsafe: " + t);
      }
      int ok = 0, shells = 0, failed = 0;
      for (String name : classes) {
         try {
            Class<?> klass = Class.forName(name);
            Module m;
            try {
               java.lang.reflect.Constructor<?> ctor = klass.getDeclaredConstructor();
               ctor.setAccessible(true);
               m = (Module) ctor.newInstance();
               ok++;
            } catch (Throwable ctorErr) {
               if (unsafe == null) throw ctorErr;
               m = (Module) unsafe.allocateInstance(klass);
               for (java.lang.reflect.Field sf : klass.getDeclaredFields()) {
                  if (java.lang.reflect.Modifier.isStatic(sf.getModifiers()) && sf.getType().equals(klass)) {
                     unsafe.putObject(klass, unsafe.staticFieldOffset(sf), m);
                  }
               }
               shells++;
            }
            // populate any still-null Setting<?> fields with sensible defaults so downstream getValue() doesn't NPE
            populateSettings(m, unsafe);
            populateCategory(m, klass, unsafe);
            populateName(m, klass, unsafe);
            manager.method_29(m);
         } catch (Throwable t) {
            failed++;
            System.err.println("[Mio] Failed to load module: " + name + " - " + t);
         }
      }
      System.err.println("[Mio] Modules: " + ok + " full, " + shells + " shell, " + failed + " failed");
      applyDefaultKeybinds(manager);
      applyUIModuleDefaults(manager, unsafe);
   }

   private static void applyUIModuleDefaults(Class_0965 manager, sun.misc.Unsafe unsafe) {
      try {
         me.mioclient.module.client.UIModule ui = manager.method_78(me.mioclient.module.client.UIModule.class);
         if (ui == null) return;
         // Sized fields (padding, panel width, etc.) need real numeric defaults so the click GUI
         // has nonzero geometry. Set each via the typed setter so dependent recalculation runs.
         if (ui.field_2851 != null) ui.field_2851.method_78(Integer.valueOf(2));   // scroll padding
         if (ui.field_2852 != null) ui.field_2852.method_78(Integer.valueOf(0));   // panel width extra (original = 0)
         if (ui.field_2853 != null) ui.field_2853.method_78(Integer.valueOf(2));   // text x padding
         if (ui.field_2849 != null) ui.field_2849.method_78(Integer.valueOf(400)); // tooltip delay ms
         if (ui.field_2854 != null) ui.field_2854.method_78(Float.valueOf(1.0f));
         if (ui.field_2855 != null) ui.field_2855.method_78(Float.valueOf(1.0f));
         if (ui.field_2863 != null) ui.field_2863.method_78(Float.valueOf(1.0f));
         if (ui.field_2867 != null) ui.field_2867.method_78(Float.valueOf(1.0f));
         if (ui.field_2870 != null) ui.field_2870.method_78(Float.valueOf(1.0f));
         if (ui.field_2873 != null) ui.field_2873.method_78(Float.valueOf(1.0f));
         // Match the mio reference: saturated purple header, translucent lavender body,
         // transparent-OFF / darker-ON row backgrounds, light text. Game stays visible behind.
         java.awt.Color textOff = new java.awt.Color(235, 230, 245, 255);
         java.awt.Color textOn  = new java.awt.Color(255, 255, 255, 255);
         java.awt.Color header  = new java.awt.Color(112,  88, 152, 230);
         java.awt.Color body    = new java.awt.Color(200, 176, 232, 110);
         java.awt.Color rowOff  = new java.awt.Color(  0,   0,   0,   0);
         java.awt.Color rowOn   = new java.awt.Color( 96,  64, 144, 180);
         java.awt.Color scrnBg  = new java.awt.Color(  0,   0,   0,   0);
         java.awt.Color scrnFg  = new java.awt.Color(  0,   0,   0,  40);
         java.awt.Color border  = new java.awt.Color( 64,  48,  96, 220);
         java.awt.Color shadow  = new java.awt.Color(  0,   0,   0, 100);
         if (ui.field_2862 != null) ui.field_2862.method_78(shadow);
         if (ui.field_2876 != null) ui.field_2876.method_78(textOff);
         if (ui.field_2877 != null) ui.field_2877.method_78(textOn);
         if (ui.field_2879 != null) ui.field_2879.method_78(header);
         if (ui.field_2880 != null) ui.field_2880.method_78(body);
         if (ui.field_2881 != null) ui.field_2881.method_78(rowOff);
         if (ui.field_2882 != null) ui.field_2882.method_78(rowOn);
         if (ui.field_2883 != null) ui.field_2883.method_78(scrnBg);
         if (ui.field_2884 != null) ui.field_2884.method_78(scrnFg);
         if (ui.field_2858 != null) ui.field_2858.method_78(Boolean.TRUE);  // Line
         if (ui.field_2859 != null) ui.field_2859.method_78(Boolean.TRUE);  // Binds — show [KEY] suffix
         if (ui.field_2860 != null) ui.field_2860.method_78(Boolean.TRUE);  // Gear — show + expand indicator
         if (ui.field_2848 != null) ui.field_2848.method_78(Boolean.TRUE);  // Descriptions — tooltip on hover
         if (ui.field_2857 != null) ui.field_2857.method_78(Boolean.TRUE);  // Elements
         if (ui.field_2878 != null) ui.field_2878.method_78(Boolean.TRUE);  // Colors
         if (ui.field_2864 != null) ui.field_2864.method_78(Boolean.FALSE); // Sounds — silence by default
         if (ui.field_2861 != null) ui.field_2861.method_78(Boolean.FALSE); // WindowShadow
         if (ui.field_2856 != null) ui.field_2856.method_78(Boolean.FALSE); // Snow
         if (ui.field_2846 != null) ui.field_2846.method_78(";");
         System.err.println("[Mio] UIModule defaults applied");
      } catch (Throwable t) {
         System.err.println("[Mio] applyUIModuleDefaults: " + t);
         t.printStackTrace(System.err);
      }
   }

   private static void applyDefaultKeybinds(Class_0965 manager) {
      try {
         me.mioclient.module.client.UIModule ui = manager.method_78(me.mioclient.module.client.UIModule.class);
         if (ui != null && (ui.getKeybind() == null || ui.getKeybind().method_38() < 0)) {
            ui.setKeybind(new me.mioclient.record.Class_0702(org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT, me.mioclient.enum_.Class_0046.TOGGLE, false));
            System.err.println("[Mio] UIModule keybind set to RIGHT_SHIFT (344)");
         }
      } catch (Throwable t) {
         System.err.println("[Mio] applyDefaultKeybinds: " + t);
      }
   }

   private static void populateName(Module m, Class<?> klass, sun.misc.Unsafe unsafe) {
      if (unsafe == null) return;
      try {
         if (m.getName() != null && !m.getName().isEmpty()) return;
         // Prefer the real super("RealName", ...) string baked into the constructor bytecode.
         String real = CtorNameSniffer.sniff(klass);
         String simple;
         if (real != null && !real.isEmpty()) {
            simple = real;
         } else {
            simple = klass.getSimpleName();
            if (simple.endsWith("Module")) simple = simple.substring(0, simple.length() - 6);
         }
         java.lang.reflect.Field f = me.mioclient.deobf.Named.class.getDeclaredField("field_327");
         unsafe.putObject(m, unsafe.objectFieldOffset(f), simple);
         // Named.field_2189 (description) is normally initialized to "" by the instance field
         // initializer in the source. Unsafe.allocateInstance skips initializers, so for shell
         // modules it stays null. Anyone calling getDescription().split(...) NPEs (the chat
         // help command does this). Write "" via Unsafe to match the source default.
         try {
            java.lang.reflect.Field df = me.mioclient.deobf.Named.class.getDeclaredField("field_2189");
            if (df.get(m) == null) unsafe.putObject(m, unsafe.objectFieldOffset(df), "");
         } catch (Throwable ignoreDesc) {}
         java.lang.reflect.Field af = Module.class.getDeclaredField("field_39");
         if (af.get(m) == null) {
            af.setAccessible(true);
            af.set(m, new String[]{ simple });
         }
      } catch (Throwable ignore) {}
   }

   private static void populateCategory(Module m, Class<?> klass, sun.misc.Unsafe unsafe) {
      if (unsafe == null) return;
      try {
         if (m.getCategory() != null) return;
         String pkg = klass.getName();
         me.mioclient.module.Category cat = null;
         if (pkg.contains(".combat.")) cat = me.mioclient.module.Category.COMBAT;
         else if (pkg.contains(".misc.")) cat = me.mioclient.module.Category.MISC;
         else if (pkg.contains(".render.")) cat = me.mioclient.module.Category.RENDER;
         else if (pkg.contains(".movement.")) cat = me.mioclient.module.Category.MOVEMENT;
         else if (pkg.contains(".player.")) cat = me.mioclient.module.Category.PLAYER;
         else if (pkg.contains(".exploit.")) cat = me.mioclient.module.Category.EXPLOIT;
         else if (pkg.contains(".client.")) cat = me.mioclient.module.Category.CLIENT;
         else if (pkg.contains(".abstract_.")) cat = me.mioclient.module.Category.MISC; // abstract HUD/widget modules
         if (cat == null) return;
         Class<?> mc = Module.class;
         java.lang.reflect.Field f = mc.getDeclaredField("field_37");
         unsafe.putObject(m, unsafe.objectFieldOffset(f), cat);
      } catch (Throwable ignore) {}
   }

   private static void populateSettings(Module m, sun.misc.Unsafe unsafe) {
      Class<?> klass = m.getClass();
      while (klass != null && klass != Object.class) {
         for (java.lang.reflect.Field f : klass.getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) continue;
            try {
               f.setAccessible(true);
               Object cur = f.get(m);
               if (cur != null) continue;
               Object val = null;
               if (me.mioclient.setting.Setting.class.isAssignableFrom(f.getType())) {
                  val = makeSetting(f);
               } else {
                  // Pick a default impl for common collection / Map / Set / Queue / Deque interfaces so
                  // shell modules don't keep null List/Map fields that NPE the first time a callback fires.
                  Class<?> ftype = f.getType();
                  if (ftype.isInterface()) {
                     if (ftype == java.util.List.class || ftype == java.util.Collection.class || ftype == Iterable.class) val = new java.util.ArrayList<>();
                     else if (ftype == java.util.Queue.class || ftype == java.util.Deque.class) val = new java.util.ArrayDeque<>();
                     else if (ftype == java.util.Set.class) val = new java.util.LinkedHashSet<>();
                     else if (ftype == java.util.Map.class) val = new java.util.LinkedHashMap<>();
                     else if (ftype == java.util.NavigableSet.class || ftype == java.util.SortedSet.class) val = new java.util.TreeSet<>();
                     else if (ftype == java.util.NavigableMap.class || ftype == java.util.SortedMap.class) val = new java.util.TreeMap<>();
                  }
                  if (val != null) {
                     // fall through to assignment below
                  } else {
                  String tn = ftype.getName();
                  boolean mio = tn.startsWith("me.mioclient.");
                  boolean atomicOrCollection =
                     tn.startsWith("java.util.concurrent.atomic.")
                     || tn.equals("java.util.ArrayList")
                     || tn.equals("java.util.LinkedList")
                     || tn.equals("java.util.HashMap")
                     || tn.equals("java.util.LinkedHashMap")
                     || tn.equals("java.util.HashSet")
                     || tn.equals("java.util.LinkedHashSet")
                     || tn.startsWith("it.unimi.dsi.fastutil.");
                  boolean concrete = !ftype.isInterface() && !java.lang.reflect.Modifier.isAbstract(ftype.getModifiers());
                  if (concrete && (mio || atomicOrCollection)) {
                     try {
                        java.lang.reflect.Constructor<?> c = f.getType().getDeclaredConstructor();
                        c.setAccessible(true);
                        val = c.newInstance();
                     } catch (Throwable noNoArg) {
                        if (mio) {
                           // try a ctor that takes the owning module type (Class_0104(ESPModule), …)
                           for (java.lang.reflect.Constructor<?> c : f.getType().getDeclaredConstructors()) {
                              Class<?>[] params = c.getParameterTypes();
                              if (params.length == 1 && params[0].isInstance(m)) {
                                 try {
                                    c.setAccessible(true);
                                    val = c.newInstance(m);
                                    break;
                                 } catch (Throwable ignore) {}
                              }
                           }
                        }
                     }
                  }
                  }
               }
               if (val == null) continue;
               // bypass final field restriction via Unsafe
               if (java.lang.reflect.Modifier.isFinal(f.getModifiers()) && unsafe != null) {
                  unsafe.putObject(m, unsafe.objectFieldOffset(f), val);
               } else {
                  f.set(m, val);
               }
               // Settings also need to be in the module's registry — otherwise the
               // ClickGUI iterates an empty list and the expand panel shows nothing.
               if (val instanceof me.mioclient.setting.Setting<?> s) {
                  java.util.List<me.mioclient.setting.Setting<?>> reg = m.getRegistry();
                  if (reg != null && !reg.contains(s)) reg.add(s);
               }
            } catch (Throwable t) {
               // ignore: leave null
            }
         }
         klass = klass.getSuperclass();
      }
      // Original ctors register settings in declaration order; superclass fields end up first.
      // Match that order so the click-GUI shows things grouped sensibly.
      try {
         java.util.List<me.mioclient.setting.Setting<?>> reg = m.getRegistry();
         if (reg != null && reg.size() > 1) {
            java.util.List<Class<?>> chain = new java.util.ArrayList<>();
            for (Class<?> c = m.getClass(); c != null && c != Object.class; c = c.getSuperclass()) chain.add(0, c);
            java.util.Map<me.mioclient.setting.Setting<?>, Integer> order = new java.util.IdentityHashMap<>();
            int idx = 0;
            for (Class<?> c : chain) {
               for (java.lang.reflect.Field f : c.getDeclaredFields()) {
                  if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) continue;
                  if (!me.mioclient.setting.Setting.class.isAssignableFrom(f.getType())) continue;
                  try {
                     f.setAccessible(true);
                     Object v = f.get(m);
                     if (v instanceof me.mioclient.setting.Setting<?> s) order.put(s, idx++);
                  } catch (Throwable ignore) {}
               }
            }
            reg.sort(java.util.Comparator.comparingInt(s -> order.getOrDefault(s, Integer.MAX_VALUE)));
         }
      } catch (Throwable ignore) {}
   }

   private static volatile java.util.Map<String, String> SETTING_NAME_LOOKUP;

   private static java.util.Map<String, String> settingNameLookup() {
      java.util.Map<String, String> snap = SETTING_NAME_LOOKUP;
      if (snap != null) return snap;
      snap = new java.util.HashMap<>();
      try {
         // nick.SettingNames at runtime keeps obfuscated keys (string-encryption that resolves
         // through the loader's clinit). MioSettingNames is a plaintext clone of the deobf source
         // so the keys read as "me.mioclient.deobf.Class_XXXX.field_YYYY" — which is what we need
         // to slice the field-only suffix off.
         java.util.Map<String, String> raw = MioSettingNames.asMap();
         for (var e : raw.entrySet()) {
            String key = e.getKey();
            int dot = key.lastIndexOf('.');
            String fname = dot < 0 ? key : key.substring(dot + 1);
            // last writer wins is fine — field names are globally unique in the mapping
            snap.put(fname, e.getValue());
         }
         System.err.println("[Mio] Loaded " + snap.size() + " setting-name mappings from MioSettingNames");
      } catch (Throwable t) {
         System.err.println("[Mio] settingNameLookup failed (will use raw field names): " + t);
      }
      SETTING_NAME_LOOKUP = snap;
      return snap;
   }

   private static String prettyFieldName(String raw) {
      String real = settingNameLookup().get(raw);
      return real == null ? raw : real;
   }

   private static me.mioclient.setting.Setting<?> makeSetting(java.lang.reflect.Field f) {
      String name = prettyFieldName(f.getName());
      Class<?> t = extractGenericArg(f);
      if (t == Boolean.class) return new me.mioclient.setting.BooleanSetting(name, false);
      if (t == String.class) return new me.mioclient.setting.StringSetting(name, "");
      if (t == java.awt.Color.class) return new me.mioclient.setting.ColorSetting(name, defaultColorFor(name));
      // default to 1 (not 0) — many number settings are scales/multipliers and a 0 default
      // collapses click-GUI scale to zero (matrix.scale(0,0,1)) → nothing renders
      if (t == Integer.class) return new me.mioclient.setting.CustomSetting3<>(name, 1, 0, 100);
      if (t == Double.class) return new me.mioclient.setting.CustomSetting3<>(name, 1.0, 0.0, 10.0);
      if (t == Float.class) return new me.mioclient.setting.CustomSetting3<>(name, 1.0f, 0.0f, 10.0f);
      if (t == Long.class) return new me.mioclient.setting.CustomSetting3<>(name, 1L, 0L, 100L);
      if (t == me.mioclient.internal.Class_0211.class) return new me.mioclient.setting.Class0211Setting(name, new me.mioclient.internal.Class_0211(""));
      if (t != null && t.isEnum()) {
         return makeEnumSetting(name, t);
      }
      // Collection-typed settings: Setting<Set<X>> / Setting<List<X>> etc.
      // Hand back an empty collection so the field is non-null and registered in the GUI.
      if (t != null && java.util.Collection.class.isAssignableFrom(t)) {
         java.util.Collection<Object> empty;
         if      (java.util.Set.class.isAssignableFrom(t))  empty = new java.util.LinkedHashSet<>();
         else if (java.util.Deque.class.isAssignableFrom(t) || java.util.Queue.class.isAssignableFrom(t)) empty = new java.util.ArrayDeque<>();
         else                                                empty = new java.util.ArrayList<>();
         @SuppressWarnings({"unchecked","rawtypes"})
         me.mioclient.setting.Setting<?> s =
               new me.mioclient.setting.CollectionSetting(name, (java.util.Collection) empty);
         return s;
      }
      return null;
   }

   private static java.awt.Color defaultColorFor(String name) {
      // give the click GUI sensible default colors so it shows up against the white-tinted screen background
      String lower = name.toLowerCase();
      if (lower.contains("text") || lower.contains("name") || lower.contains("title") || lower.contains("font")) {
         return new java.awt.Color(0xFFFFFF);
      }
      if (lower.contains("accent") || lower.contains("highlight") || lower.contains("active") || lower.contains("enabled")) {
         return new java.awt.Color(0x6CA0DC);
      }
      if (lower.contains("border") || lower.contains("outline")) {
         return new java.awt.Color(0x202020);
      }
      // default: dark grey panel/body background
      return new java.awt.Color(0x202020);
   }

   @SuppressWarnings({"unchecked","rawtypes"})
   private static <E extends Enum<E>> me.mioclient.setting.Setting<?> makeEnumSetting(String name, Class<?> t) {
      E first = (E) t.getEnumConstants()[0];
      return new me.mioclient.setting.CustomSetting(name, first);
   }

   private static Class<?> extractGenericArg(java.lang.reflect.Field f) {
      java.lang.reflect.Type g = f.getGenericType();
      if (g instanceof java.lang.reflect.ParameterizedType pt) {
         java.lang.reflect.Type[] args = pt.getActualTypeArguments();
         if (args.length > 0) {
            java.lang.reflect.Type a = args[0];
            if (a instanceof Class<?> c) return c;
            // Nested generic like Setting<Set<Block>> — drop down to the raw container type.
            if (a instanceof java.lang.reflect.ParameterizedType inner && inner.getRawType() instanceof Class<?> raw) return raw;
         }
      }
      return null;
   }

   private MioModules() { super(); }
}
