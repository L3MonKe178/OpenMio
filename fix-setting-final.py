#!/usr/bin/env python3
"""扫所有 Java，对每个调用 Settings.initialize 的类：
   把 `public final Setting<...> field_NNN;` 改成 `public Setting<...> field_NNN;`

dry-run；--apply 实写。
"""
import os, re, sys

ROOT = os.path.dirname(os.path.abspath(__file__))
SRC = os.path.join(ROOT, "src", "main", "java")
APPLY = "--apply" in sys.argv

# matches `public final Setting<...> field_NAME;`
# Setting<...> may have nested generics like Setting<Set<class_1792>>.
# We match `Setting<` + balanced brackets + `> field_NNN`.
FIELD_RE = re.compile(
    r"^(\s*)public\s+final(\s+Setting\s*<)",
    re.MULTILINE
)


def patch(text):
    if "Settings.initialize" not in text:
        return text, 0
    new, n = FIELD_RE.subn(r"\1public\2", text)
    return new, n


def main():
    total_files = 0
    total_drops = 0
    for dirpath, _, files in os.walk(SRC):
        for f in files:
            if not f.endswith(".java"):
                continue
            path = os.path.join(dirpath, f)
            text = open(path).read()
            new, n = patch(text)
            if n == 0:
                continue
            rel = os.path.relpath(path, ROOT)
            print(f"{rel}: 去掉 {n} 处 final")
            if APPLY:
                open(path, "w").write(new)
            total_files += 1
            total_drops += n
    print(f"\n总: {total_files} 文件, {total_drops} 处 final")
    if not APPLY:
        print("dry-run, --apply 实写")


if __name__ == "__main__":
    main()
