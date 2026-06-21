/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.internal;

import java.lang.invoke.LambdaMetafactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Class_0127 {
    public static final Lock field_379 = new ReentrantLock();

    public static void method_7(Runnable runnable) {
        Class_0127.method_2(field_379, runnable);
    }

    public static Runnable method_29(Runnable runnable) {
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
        return null;
    }

    public static void method_2(Lock lock, Runnable runnable) {
        try {
            lock.lock();
            runnable.run();
        }
        finally {
            lock.unlock();
        }
    }

    public static Runnable method_9(Lock lock, Runnable runnable) {
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
        return null;
    }
}

