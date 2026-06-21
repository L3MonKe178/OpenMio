/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.internal;

import com.google.common.collect.ImmutableList;
import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import me.mioclient.internal.Class_0482;
import me.mioclient.internal.Class_0629;
import me.mioclient.record.Class_0661;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0475
extends Class_0629 {
    private static ModelPart getLlamaPart(LlamaEntityModel model, String name) {
        try {
            Field f = LlamaEntityModel.class.getDeclaredField(name);
            f.setAccessible(true);
            return (ModelPart) f.get(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void method_2(Class_0661 class_0661, EntityModel<?> entityModel) {
        MatrixStack matrixStack = class_0661.method_667();
        if (entityModel instanceof LlamaEntityModel) {
            LlamaEntityModel llamaEntityModel = (LlamaEntityModel)entityModel;
            if (llamaEntityModel.child) {
                matrixStack.push();
                matrixStack.scale(0.71428573f, 0.64935064f, 0.7936508f);
                matrixStack.translate(0.0f, 1.3125f, 0.22f);
                Class_0482.method_2(class_0661, getLlamaPart(llamaEntityModel, "head"));
                matrixStack.pop();
                matrixStack.push();
                matrixStack.scale(0.625f, 0.45454544f, 0.45454544f);
                matrixStack.translate(0.0f, 2.0625f, 0.0f);
                Class_0482.method_2(class_0661, getLlamaPart(llamaEntityModel, "body"));
                matrixStack.pop();
                matrixStack.push();
                matrixStack.scale(0.45454544f, 0.41322312f, 0.45454544f);
                matrixStack.translate(0.0f, 2.0625f, 0.0f);
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
                matrixStack.pop();
            } else {
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
            }
        }
    }
}

