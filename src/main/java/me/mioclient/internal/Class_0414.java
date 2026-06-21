/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.internal;

import java.util.ArrayList;
import me.mioclient.api.Class_0988;
import me.mioclient.internal.Class_0031;
import me.mioclient.internal.Class_0194;
import me.mioclient.internal.Class_0345;
import me.mioclient.internal.Class_0585;
import me.mioclient.internal.Class_0745;
import me.mioclient.internal.Class_0746;
import me.mioclient.internal.Class_0841;
import me.mioclient.internal.Class_1015;
import me.mioclient.internal.FontRenderer;
import me.mioclient.internal.TextBuilder;
import me.mioclient.module.client.UIModule;
import me.mioclient.setting.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0414
extends Class_1015<Enum<?>> {
    public final ArrayList<Class_0194> field_1318 = new ArrayList();
    public final Class_0031 field_1319 = new Class_0031(() -> Float.valueOf(Float.intBitsToFloat(0x40000000) * this.method_852().field_2854.getValue().floatValue()), true);
    public final Class_0585 field_1320 = new Class_0585(() -> Float.valueOf(Float.intBitsToFloat(0x40000000) * UIModule.field_2843.field_2854.getValue().floatValue()), true);
    public boolean field_111 = false;
    public int field_419;
    public int field_1321;
    public int field_1322;
    public int field_1323;
    public final int field_1324;

    public Class_0414(Class_0746 class_0746, Class_0988 class_0988, Setting<?> setting) {
        super(class_0746, class_0988, (Setting<Enum<?>>)(Setting)setting);
        int n = this.method_851();
        int n2 = 0;
        for (Enum enum_ : (Enum[])((Enum)this.field_3138.method_99()).getDeclaringClass().getEnumConstants()) {
            if (Class_0345.method_29(enum_)) continue;
            this.field_1318.add(new Class_0194(this, Class_0841.method_5(enum_), n, n2));
            n += this.method_851();
            ++n2;
        }
        this.field_1324 = n2;
    }

    @Override
    public void method_26(int n) {
        this.field_419 = n;
    }

    @Override
    public void method_2(double d, double d2, int n) {
        if (this.method_911()) {
            return;
        }
        super.method_2(d, d2, n);
        this.field_1318.forEach(class_0194 -> class_0194.method_2(d, d2, n));
        if (this.method_5(d, d2)) {
            if (n == 0) {
                this.field_3138.method_78(Class_0841.method_9((Enum)this.field_3138.getValue()));
                this.field_1323 = (this.field_1323 + 1) % this.field_1324;
            }
            if (n == 1) {
                int n2 = 0;
                for (int i = 0; i < ((Enum[])((Enum)this.field_3138.getValue()).getDeclaringClass().getEnumConstants()).length; ++i) {
                    Enum enum_ = ((Enum[])((Enum)this.field_3138.getValue()).getDeclaringClass().getEnumConstants())[i];
                    if (enum_ == this.field_3138.getValue()) {
                        this.field_1323 = n2;
                        break;
                    }
                    if (Class_0345.method_29(enum_)) continue;
                    ++n2;
                }
                this.field_111 = !this.field_111;
                this.method_167().method_142();
            }
        }
    }

    @Override
    public void method_4(double d, double d2) {
        if (this.method_911()) {
            return;
        }
        this.field_1322 = this.method_167().getY() + this.field_419;
        this.field_1321 = this.method_167().getX();
    }

    @Override
    public void method_2(DrawContext drawContext, MatrixStack matrixStack, double d, double d2) {
        if (this.method_911()) {
            this.field_1320.reset();
            return;
        }
        super.method_2(drawContext, matrixStack, d, d2);
        this.field_1320.method_3(true);
        if (this.field_111) {
            this.field_1319.method_3(this.field_1323 * this.method_851());
            float f = this.field_1319.method_45();
            Class_0745.method_4(matrixStack, this.field_418.getX() + this.method_170() + 1, (float)(this.field_418.getY() + this.field_419) + f + (float)this.method_851() - Float.intBitsToFloat(0x3F000000), this.field_418.getX() + this.field_418.method_216() - 2, (float)(this.field_418.getY() + this.field_419 + this.method_851() * 2) + f - Float.intBitsToFloat(0x3F000000), this.method_852().field_2879.getValue());
        } else {
            this.field_1319.method_3(-this.method_851());
        }
        int n = this.field_111 ? 1 : 0;
        float f = Math.max((float)this.field_418.method_216() * this.field_1320.method_45() - Float.intBitsToFloat(1065353216), Float.intBitsToFloat(0x40000000));
        Class_0745.method_4(matrixStack, this.field_418.getX() + this.method_170() + n, (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(0x3F000000) + (float)n, (float)this.field_418.getX() + f - (float)n, (float)(this.field_418.getY() + this.field_419 + this.method_851()) - Float.intBitsToFloat(0x3F000000), this.method_852().field_2879.getValue());
        if (this.field_111) {
            this.field_1318.forEach(class_0194 -> class_0194.method_2(drawContext, matrixStack, d, d2));
            if (UIModule.field_2843.field_2858.getValue().booleanValue()) {
                Class_0745.method_29(matrixStack, this.field_418.getX() + this.method_170(), (float)(this.field_418.getY() + this.field_419) + Float.intBitsToFloat(0x3F000000), this.field_418.getX() + this.field_418.method_216() - 2, (float)(this.field_418.getY() + this.field_419 + this.method_66()) - Float.intBitsToFloat(1069547520), this.method_852().field_2879.getValue());
            }
        }
        String string = new TextBuilder().method_2((Object)Class_0841.method_5((Enum)this.field_3138.getValue())).method_2((Object)this.field_3138.getName()).method_9("\u0001: \u0001");
        this.method_2(matrixStack, string, () -> FontRenderer.field_3143.method_9(drawContext, string, (float)(this.field_418.getX() + 4), (float)this.field_418.getY() + this.method_850() - this.method_169() + (float)this.field_419, this.method_852().field_2876.getValue()));
        int n2 = this.method_851();
        for (Class_0194 class_01942 : this.field_1318) {
            class_01942.method_26(n2);
            n2 += this.method_851();
        }
    }

    @Override
    public boolean method_5(double d, double d2) {
        return d > (double)this.field_1321 && d < (double)(this.field_1321 + this.method_167().method_216()) && d2 > (double)this.field_1322 && d2 < (double)(this.field_1322 + 11);
    }

    @Override
    public int method_66() {
        if (this.method_911()) {
            this.field_1319.method_36(-this.method_851());
            return 0;
        }
        if (this.field_111) {
            return this.method_851() + this.field_1324 * this.method_851() + 1;
        }
        return super.method_66();
    }

    @Override
    public void init() {
        this.method_27(((Enum)this.field_3138.getValue()).ordinal());
    }

    public int getY() {
        return this.field_1322;
    }

    public int getX() {
        return this.field_1321;
    }

    public boolean method_194() {
        return this.field_111;
    }

    public int method_36() {
        return this.field_1323;
    }

    public void method_27(int n) {
        this.field_1323 = n;
    }
}

