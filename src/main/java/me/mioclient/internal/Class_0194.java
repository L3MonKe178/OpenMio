/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.internal;

import me.mioclient.api.Class_0013;
import me.mioclient.internal.Class_0145;
import me.mioclient.internal.Class_0414;
import me.mioclient.internal.Class_1016;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0194
extends Class_0145 {
    public final Class_0414 field_546;
    public final String field_547;
    public final int field_548;

    public Class_0194(Class_0414 class_0414, String string, int n, int n2) {
        super(class_0414.method_167(), n);
        this.field_547 = string;
        this.field_548 = n2;
        this.field_546 = class_0414;
    }

    @Override
    public void method_2(DrawContext drawContext, MatrixStack matrixStack, double d, double d2) {
        super.method_2(drawContext, matrixStack, d, d2);
        Class_1016.field_3143.method_9(drawContext, this.field_547, (float)this.method_167().getX() + (float)this.method_167().method_216() / Float.intBitsToFloat(0x40000000) - Class_1016.field_3143.method_221(this.field_547) / Float.intBitsToFloat(0x40000000), (float)(this.field_546.getY() + this.field_419) + this.method_850() - Float.intBitsToFloat(1065353216) - this.method_169(), this.method_852().field_2876.getValue());
    }

    @Override
    public void method_2(double d, double d2, int n) {
        if (this.field_546.method_911() || !this.method_5(d, d2) || !this.field_546.method_194()) {
            return;
        }
        super.method_2(d, d2, n);
        if (this.field_546.method_909().method_194() && n == 0) {
            for (Enum enum_ : (Enum[])((Enum)this.field_546.method_910().getValue()).getDeclaringClass().getEnumConstants()) {
                Class_0013 class_0013;
                if (!(enum_ instanceof Class_0013) || !(class_0013 = (Class_0013)((Object)enum_)).getName().equalsIgnoreCase(this.field_547)) continue;
                this.field_546.method_910().method_78(enum_);
                this.field_546.method_27(this.field_548);
            }
        }
    }

    @Override
    public boolean method_5(double d, double d2) {
        return d > (double)this.field_546.getX() && d < (double)(this.field_546.getX() + this.method_167().method_216()) && d2 > (double)(this.field_546.getY() + this.field_419) && d2 < (double)(this.field_546.getY() + this.method_851() + this.field_419);
    }

    @Override
    public int method_66() {
        if (this.field_546.method_911()) {
            return 0;
        }
        return super.method_66();
    }

    public String method_226() {
        return this.field_547;
    }
}

