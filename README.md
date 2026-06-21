# OpenMio

Open-source reconstruction of the Mio Client (Minecraft 1.21.1 Fabric utility mod) from its obfuscated jar — deobfuscated, decompiled, remapped to Yarn, and patched into a buildable Fabric Loom project.

## Build

```sh
export JAVA_HOME=$(/usr/libexec/java_home -v 21)   # Java 21
./gradlew runClient                                # launch with the mod loaded
./gradlew build                                    # build/libs/mioclient-1.0.jar
```

> Java 26 is **not** supported — Mixin 0.8.7's bundled ASM can't read class file major version 70 (`ClassNotFoundException: java.lang.System` during transform).

## Stack

- Minecraft 1.21.1 · Yarn `1.21.1+build.3` · Fabric Loom 1.7
- Mixin 0.8.7 + MixinExtras 0.4.0
- Original `mio-loader.jar` runtime dependency (see [Runtime](#runtime))

## Layout

```
src/main/java/me/mioclient/
├── internal/        reconstructed core (modules, settings, GUI plumbing)
├── module/          200+ feature modules (combat / movement / render / …)
├── mixin/           Minecraft mixins
├── clickgui/        ClickGUI rewrite
└── deobf/           preserved obfuscated-name shims
libs/mio-loader-deobf.jar    compileOnly — original loader, used at compile time
```

## What works

- 200 modules registered, 36 commands, full ClickGUI with proper setting names
- Module bootstrap shells around constructors that depend on the loader's reflective `Settings.initialize` — those are no-ops for renamed classes, so `MioModules` fills the gaps via `Unsafe`
- Setting names sourced from a plaintext clone of `nick.SettingNames` (`MioSettingNames`) because at runtime the loader's keys are still encrypted

## Runtime

The original `mio-loader.jar` ships the encrypted strings and a few helper classes the runtime touches. Drop it in `run/mods/` before launching:

```sh
mkdir -p run/mods && cp ../mio-loader.jar run/mods/
```

## Reconstruction pipeline

1. Deobfuscate `mio.jar` → `mio-deobf.jar`
2. tiny-remapper `intermediary → named (yarn)`
3. Vineflower 1.10.1 decompile (CFR fallback for a handful of methods)
4. `fix-*.py` post-processors — record/enum cleanups, super() ordering, illegal identifiers
5. Hand-fix remaining cast / sealed-class / external-dep errors

## License

Reconstruction for research and education. Original Mio Client copyright belongs to its author.
