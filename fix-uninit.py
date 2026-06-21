#!/usr/bin/env python3
"""Drop `final` from any field referenced by 'may not have been initialized'."""
import re, sys, subprocess, os

ROOT = os.path.dirname(os.path.abspath(__file__))
os.chdir(ROOT)

JAVA_HOME = "/Users/mac/Library/Java/JavaVirtualMachines/temurin-22.0.2/Contents/Home"
env = os.environ.copy(); env["JAVA_HOME"] = JAVA_HOME

def collect():
    p = subprocess.run(["./gradlew", "compileJava", "--no-daemon"], env=env, capture_output=True, text=True)
    return p.stdout + p.stderr

def parse_uninit(out):
    out = out.replace("\r", "")
    pat = re.compile(r"(/Users/.+?\.java):\d+:.*可能尚未初始化变量([A-Za-z_][A-Za-z0-9_]*)")
    by_file = {}
    for m in pat.finditer(out):
        by_file.setdefault(m.group(1), set()).add(m.group(2))
    return by_file

def patch(path, fields):
    with open(path) as fh: t = fh.read()
    orig = t
    for fld in fields:
        t = re.sub(r"(public\s+)final(\s+[^;]*?\b" + re.escape(fld) + r"\b)", r"\1\2", t)
    if t != orig:
        with open(path, "w") as fh: fh.write(t)
        return True
    return False

for round in range(20):
    out = collect()
    by_file = parse_uninit(out)
    if not by_file:
        print(f"round {round}: no uninit errors left")
        break
    print(f"round {round}: {sum(len(v) for v in by_file.values())} fields in {len(by_file)} files")
    changed = 0
    for f, flds in by_file.items():
        if patch(f, flds):
            changed += 1
    if changed == 0:
        print("no patches applied — bailing")
        break
print("done")
