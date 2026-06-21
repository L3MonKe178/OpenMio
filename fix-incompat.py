#!/usr/bin/env python3
"""
Add `(Type)` casts where the Java compiler reports
  "不兼容的类型: Object无法转换为<Type>"
or its raw-collection variants. Iterates with the compiler until the count
stops dropping or hits zero.

Handles patterns:
  1. for-each loop:   for (Type x : raw)  -> for (Type x : (Iterable<Type>) raw)
  2. assignment:      Type x = expr;       -> Type x = (Type) expr;
  3. method arg:      foo(expr)            -> foo((Type) expr)  (fallback)
"""

import os, re, subprocess, sys

ROOT = os.path.dirname(os.path.abspath(__file__))
os.chdir(ROOT)

JAVA_HOME = "/Users/mac/Library/Java/JavaVirtualMachines/temurin-22.0.2/Contents/Home"
env = os.environ.copy(); env["JAVA_HOME"] = JAVA_HOME

ERR_PAT = re.compile(
    r"(/Users/.+?\.java):(\d+):\s*错误:\s*不兼容的类型:\s*Object无法转换为([A-Za-z_][A-Za-z0-9_<>?,.\s]*)"
)


def collect():
    p = subprocess.run(["./gradlew", "compileJava", "--no-daemon"], env=env, capture_output=True, text=True)
    return p.stdout + p.stderr


def parse(out):
    errs = []
    for m in ERR_PAT.finditer(out.replace("\r", "")):
        file, line, t = m.group(1), int(m.group(2)), m.group(3).strip()
        # strip trailing decoration that might leak in
        t = t.split("\n")[0].strip()
        errs.append((file, line, t))
    return errs


def patch_line(path: str, line_no: int, type_name: str) -> bool:
    with open(path) as fh: lines = fh.readlines()
    if line_no < 1 or line_no > len(lines):
        return False
    idx = line_no - 1
    line = lines[idx]
    orig = line

    # Pattern 1: for-each loop
    m = re.match(r"^(\s*)for\s*\(([^:]*?)\s+:\s+(.*?)\)\s*\{?\s*$", line)
    if m:
        indent, decl, expr = m.groups()
        # don't double-cast
        if "(Iterable<" not in expr and "(Iterator<" not in expr:
            new_expr = f"(Iterable<{type_name}>)(Iterable<?>)({expr})"
            # preserve trailing `{` if any
            tail = "{" if line.rstrip().endswith("{") else ""
            line = f"{indent}for ({decl} : {new_expr}) {tail}\n"
            if line.rstrip()[-1] != "{":
                # add brace pre-existing newline
                pass

    # Pattern 2: standalone assignment / declaration that ended on this line
    if line == orig:
        m = re.match(r"^(\s*)([A-Za-z_][A-Za-z0-9_<>?,.\s\[\]]*\s+\w+)\s*=\s*(.+);\s*$", line)
        if m:
            indent, lhs, rhs = m.groups()
            # only add cast if rhs doesn't already start with cast to that type
            if f"({type_name})" not in rhs:
                line = f"{indent}{lhs} = ({type_name}) ({rhs});\n"

    # Pattern 3: return statement
    if line == orig:
        m = re.match(r"^(\s*)return\s+(.+);\s*$", line)
        if m:
            indent, rhs = m.groups()
            if f"({type_name})" not in rhs:
                line = f"{indent}return ({type_name})({rhs});\n"

    if line == orig:
        return False
    lines[idx] = line
    with open(path, "w") as fh:
        fh.writelines(lines)
    return True


for round in range(8):
    out = collect()
    errs = parse(out)
    if not errs:
        print(f"round {round}: no incompat errors")
        break
    print(f"round {round}: {len(errs)} incompat errors")
    by_file = {}
    for f, l, t in errs:
        by_file.setdefault(f, []).append((l, t))
    changed = 0
    for f, items in by_file.items():
        # patch deepest-line first so line numbers stay valid
        items.sort(reverse=True)
        for l, t in items:
            if patch_line(f, l, t):
                changed += 1
    print(f"round {round}: patched {changed}")
    if changed == 0:
        break
print("done")
