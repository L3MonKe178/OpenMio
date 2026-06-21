#!/usr/bin/env python3
"""
Post-process Vineflower/CFR output so the source tree compiles under fabric-loom.

Fixes applied per file:
  1. Replace Vineflower's broken Record header `(public )?record X()` (empty
     header) with `public final class X` so the manually-listed final fields
     stop being a syntax error.
  2. Remove `public` modifier on enum constructors (Java forbids it).
  3. Ensure every top-level class / enum / interface declaration is `public`
     (Vineflower marks many as package-private though their code is referenced
     across packages).
  4. Replace illegal numeric / Unicode field names with valid identifiers.
  5. Stub out lines containing CFR's `LambdaMetafactory.metafactory(null, null,
     null, ()V, …)` pseudo-syntax — those came from indy sites whose private
     impl method was eliminated by our ForwarderInliner.
"""

import os, re, sys

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "src", "main", "java")

ILLEGAL_NUMERIC = re.compile(r"(\b(?:public\s+|private\s+|protected\s+|static\s+|final\s+)+(?:\w+(?:<[^>]+>)?)\s+)(\d+)(\s*=)")

def fix_file(path: str) -> bool:
    with open(path, "r", encoding="utf-8") as fh:
        before = fh.read()
    text = before

    # (1) record X() / public record X<T>() -> public final class X[<T>]
    text = re.sub(
        r"^(?:public\s+)?record\s+([A-Za-z_][A-Za-z0-9_]*)(\<[^>]+\>)?\(\)",
        r"public final class \1\2",
        text,
        flags=re.MULTILINE,
    )

    # Identify the top-level type declaration so we can fix its modifier and
    # the enum constructor name.
    decl = re.search(
        r"^(public\s+)?(sealed\s+)?(final\s+)?(abstract\s+)?(enum|class|interface)\s+([A-Za-z_][A-Za-z0-9_]*)",
        text,
        re.MULTILINE,
    )
    if decl:
        is_public, sealed_mod, final_mod, abs_mod, kind, name = (
            decl.group(1) or "",
            decl.group(2) or "",
            decl.group(3) or "",
            decl.group(4) or "",
            decl.group(5),
            decl.group(6),
        )

        # (2) enum constructor must not be public — applies to every enum in
        # the file, including the nested ones (jagrosh IPCClient.Event etc.).
        for enum_name in re.findall(r"\benum\s+([A-Za-z_][A-Za-z0-9_]*)", text):
            text = re.sub(
                r"(\n\s+)public(\s+%s\s*\()" % re.escape(enum_name),
                r"\1\2",
                text,
            )

        # (3) public top-level type
        if not is_public:
            text = re.sub(
                r"^(sealed |final |abstract )*(enum|class|interface)\s+%s\b" % re.escape(name),
                lambda m: "public " + (m.group(1) or "") + m.group(2) + " " + name,
                text,
                count=1,
                flags=re.MULTILINE,
            )

    # (4) numeric / illegal identifier in field name -> prefix with f
    def fix_numeric(match: re.Match) -> str:
        return f"{match.group(1)}f{match.group(2)}{match.group(3)}"
    text = ILLEGAL_NUMERIC.sub(fix_numeric, text)

    # (5) Stub CFR pseudo-lambda lines.
    text = re.sub(
        r"^.*LambdaMetafactory\.metafactory\(null,\s*null,\s*null,.*$",
        '        throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");',
        text,
        flags=re.MULTILINE,
    )

    # (6) Constructor pattern emitted by Vineflower where the obfuscator
    # reordered the super() call after the field init. Java requires super()
    # to come first. Detect a run of `this.field = var;` then `super();` and
    # swap them.
    text = re.sub(
        r"((?:\n[ \t]+this\.[A-Za-z0-9_]+\s*=\s*[^;]+;)+)(\n[ \t]+super\(\)\s*;)",
        lambda m: m.group(2) + m.group(1),
        text,
    )

    if text != before:
        with open(path, "w", encoding="utf-8") as fh:
            fh.write(text)
        return True
    return False


def main():
    changed = 0
    visited = 0
    for dirpath, _dirs, files in os.walk(ROOT):
        for f in files:
            if not f.endswith(".java"):
                continue
            visited += 1
            if fix_file(os.path.join(dirpath, f)):
                changed += 1
    print(f"{changed}/{visited} files patched")


if __name__ == "__main__":
    main()
