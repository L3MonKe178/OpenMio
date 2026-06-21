#!/usr/bin/env python3
"""按 javac 错误消息插入 brigadier 类型见证。

读 /tmp/errors.txt 找
   对于 then(RequiredArgumentBuilder<Object,T>), ...
   对于 then(LiteralArgumentBuilder<Object>), ...
   不兼容的类型: RequiredArgumentBuilder<Object,T> 无法转换为 ...
然后在 java 源文件错误行附近找 `RequiredArgumentBuilder.argument(` 或 `LiteralArgumentBuilder.literal(`
插入 `<CommandSource>` / `<CommandSource, T>` 见证。

dry-run；--apply 实写。
"""

import os, re, sys

ROOT = os.path.dirname(os.path.abspath(__file__))
APPLY = "--apply" in sys.argv
ERR_FILE = "/tmp/errors.txt"

REQ_PAT = re.compile(
    r"(/Users/.+?\.java):(\d+):.*RequiredArgumentBuilder<Object,([A-Za-z_][A-Za-z0-9_]*)>"
)
LIT_PAT = re.compile(r"(/Users/.+?\.java):(\d+):.*LiteralArgumentBuilder<Object>")


def patch_required(path, line_no, T):
    with open(path) as fh:
        lines = fh.readlines()
    # The error is on `.then(...)` line. The actual `RequiredArgumentBuilder.argument(`
    # call is in or after that line; scan a 5-line window.
    for off in range(0, 8):
        idx = line_no - 1 + off
        if idx >= len(lines):
            break
        line = lines[idx]
        if "RequiredArgumentBuilder.argument(" in line and "<CommandSource" not in line:
            new = line.replace(
                "RequiredArgumentBuilder.argument(",
                f"RequiredArgumentBuilder.<CommandSource, {T}>argument(", 1
            )
            lines[idx] = new
            with open(path, "w") as fh: fh.writelines(lines)
            return idx + 1, line.rstrip()
        # walk backward too — error line may point at outer .then(
        if off < 4:
            idx2 = line_no - 1 - off
            if idx2 >= 0 and "RequiredArgumentBuilder.argument(" in lines[idx2] and "<CommandSource" not in lines[idx2]:
                new = lines[idx2].replace(
                    "RequiredArgumentBuilder.argument(",
                    f"RequiredArgumentBuilder.<CommandSource, {T}>argument(", 1
                )
                lines[idx2] = new
                with open(path, "w") as fh: fh.writelines(lines)
                return idx2 + 1, lines[idx2].rstrip()
    return None, None


def patch_literal(path, line_no):
    with open(path) as fh:
        lines = fh.readlines()
    for off in range(-4, 8):
        idx = line_no - 1 + off
        if 0 <= idx < len(lines) and "LiteralArgumentBuilder.literal(" in lines[idx] and "<CommandSource" not in lines[idx]:
            lines[idx] = lines[idx].replace(
                "LiteralArgumentBuilder.literal(",
                "LiteralArgumentBuilder.<CommandSource>literal(", 1
            )
            with open(path, "w") as fh: fh.writelines(lines)
            return idx + 1, lines[idx].rstrip()
    return None, None


def main():
    if not os.path.exists(ERR_FILE):
        print("缺 /tmp/errors.txt — 先 ./gradlew compileJava 收集")
        return
    text = open(ERR_FILE).read()

    fixed_req = 0
    fixed_lit = 0
    skipped = []

    for m in REQ_PAT.finditer(text):
        path, ln, T = m.group(1), int(m.group(2)), m.group(3)
        if not APPLY:
            print(f"would patch {os.path.basename(path)}:{ln} → <CommandSource, {T}>")
            fixed_req += 1
        else:
            new_ln, src_line = patch_required(path, ln, T)
            if new_ln:
                print(f"  fix {os.path.basename(path)}:{new_ln}  +<CommandSource, {T}>")
                fixed_req += 1
            else:
                skipped.append((path, ln, T))

    for m in LIT_PAT.finditer(text):
        path, ln = m.group(1), int(m.group(2))
        if not APPLY:
            print(f"would patch {os.path.basename(path)}:{ln} → <CommandSource>literal")
            fixed_lit += 1
        else:
            new_ln, src_line = patch_literal(path, ln)
            if new_ln:
                print(f"  fix {os.path.basename(path)}:{new_ln}  +<CommandSource>literal")
                fixed_lit += 1
            else:
                skipped.append((path, ln, "literal"))

    print(f"\n总: {fixed_req} required, {fixed_lit} literal")
    if skipped:
        print("跳过 (找不到目标行):")
        for s in skipped: print(" ", s)
    if not APPLY:
        print("dry-run, --apply 实写")


if __name__ == "__main__":
    main()
