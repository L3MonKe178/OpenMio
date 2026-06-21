#!/usr/bin/env python3
"""Brigadier 类型见证补丁。

模式：
  LiteralArgumentBuilder.literal("...")               → LiteralArgumentBuilder.<CommandSource>literal("...")
  RequiredArgumentBuilder.argument("...", IntegerArgumentType.integer())  → ...<CommandSource, Integer>argument(...)

ArgumentType 推断表（方法名 → 泛型实参类型）：
  IntegerArgumentType.integer  → Integer
  FloatArgumentType.floatArg   → Float
  DoubleArgumentType.doubleArg → Double
  LongArgumentType.longArg     → Long
  StringArgumentType.string|word|greedyString → String
  BoolArgumentType.bool        → Boolean

dry-run 模式：默认；要真正写回加 --apply
"""

import os, re, sys

ROOT = os.path.dirname(os.path.abspath(__file__))
SRC = os.path.join(ROOT, "src", "main", "java")
APPLY = "--apply" in sys.argv

# .argument 的第二个参数 → 第二个泛型 T
ARG_TYPE_MAP = {
    "IntegerArgumentType.integer": "Integer",
    "FloatArgumentType.floatArg":   "Float",
    "DoubleArgumentType.doubleArg": "Double",
    "LongArgumentType.longArg":     "Long",
    "StringArgumentType.string":    "String",
    "StringArgumentType.word":      "String",
    "StringArgumentType.greedyString": "String",
    "BoolArgumentType.bool":        "Boolean",
}

LITERAL_RE = re.compile(r"\bLiteralArgumentBuilder\.literal\(")
# argument call where ArgumentType can have args:
# RequiredArgumentBuilder.argument("name", IntegerArgumentType.integer(1, 60))
# Match: name, ClassName.methodName, (anything balanced inside)
ARG_RE = re.compile(
    r"\bRequiredArgumentBuilder\.argument\(\s*([^,]+?)\s*,\s*([A-Za-z_][A-Za-z0-9_]*\.[A-Za-z_][A-Za-z0-9_]*)\s*(\([^)]*\))\s*\)"
)
# raw-cast patterns: `(LiteralArgumentBuilder)` and `(RequiredArgumentBuilder)`
# Loosen to <CommandSource> / <CommandSource,?> so .then() lines up.
RAW_LIT_CAST = re.compile(r"\(\s*LiteralArgumentBuilder\s*\)")
RAW_REQ_CAST = re.compile(r"\(\s*RequiredArgumentBuilder\s*\)")


def patch(text):
    changes = []
    new = text

    def lit_repl(m):
        changes.append(("literal", m.group(0)))
        return "LiteralArgumentBuilder.<CommandSource>literal("

    new = LITERAL_RE.sub(lit_repl, new)

    def arg_repl(m):
        name_arg = m.group(1)
        atype = m.group(2)
        call = m.group(3)  # parenthesised actuals incl. parens
        T = ARG_TYPE_MAP.get(atype)
        if T is None:
            return m.group(0)
        changes.append(("argument-" + T, m.group(0)))
        return f"RequiredArgumentBuilder.<CommandSource, {T}>argument({name_arg}, {atype}{call})"

    new = ARG_RE.sub(arg_repl, new)

    def lit_cast_repl(m):
        changes.append(("raw-lit-cast", m.group(0)))
        return "(LiteralArgumentBuilder<CommandSource>)"
    new = RAW_LIT_CAST.sub(lit_cast_repl, new)

    def req_cast_repl(m):
        changes.append(("raw-req-cast", m.group(0)))
        return "(RequiredArgumentBuilder<CommandSource, ?>)"
    new = RAW_REQ_CAST.sub(req_cast_repl, new)

    return new, changes


def main():
    total_files = 0
    total_changes = 0
    for dirpath, _, files in os.walk(SRC):
        for f in files:
            if not f.endswith(".java"):
                continue
            path = os.path.join(dirpath, f)
            with open(path) as fh:
                text = fh.read()
            # Skip files that don't import brigadier
            if "com.mojang.brigadier.builder" not in text:
                continue
            new, changes = patch(text)
            if not changes:
                continue
            # need CommandSource symbol — verify import
            if "com.mojang.brigadier" not in text:
                continue
            rel = os.path.relpath(path, ROOT)
            print(f"{rel}: {len(changes)} 处")
            for kind, snippet in changes[:3]:
                print(f"  - {kind}: {snippet[:80]}")
            if APPLY:
                with open(path, "w") as fh:
                    fh.write(new)
            total_files += 1
            total_changes += len(changes)
    print(f"\n总计: {total_files} 文件, {total_changes} 处")
    print("dry-run, 加 --apply 实写" if not APPLY else "已应用")


if __name__ == "__main__":
    main()
