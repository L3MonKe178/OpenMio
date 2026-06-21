/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.internal;

import com.google.common.collect.ImmutableList;
import java.lang.invoke.LambdaMetafactory;
import java.util.function.Consumer;
import me.mioclient.internal.Class_0629;
import me.mioclient.record.Class_0661;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class Class_1361
extends Class_0629 {
    @Override
    public void method_2(Class_0661 class_0661, EntityModel<?> entityModel) {
        MatrixStack matrixStack = class_0661.method_667();
        if (entityModel instanceof RabbitEntityModel) {
            RabbitEntityModel rabbitEntityModel = (RabbitEntityModel)entityModel;
            if (rabbitEntityModel.child) {
                matrixStack.push();
                matrixStack.scale(0.56666666f, 0.56666666f, 0.56666666f);
                matrixStack.translate(0.0f, 1.375f, 0.125f);
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
                matrixStack.pop();
                matrixStack.push();
                matrixStack.scale(0.4f, 0.4f, 0.4f);
                matrixStack.translate(0.0f, 2.25f, 0.0f);
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
                matrixStack.pop();
            } else {
                matrixStack.push();
                matrixStack.scale(0.6f, 0.6f, 0.6f);
                matrixStack.translate(0.0f, 1.0f, 0.0f);
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
                matrixStack.pop();
            }
        }
    }
}

