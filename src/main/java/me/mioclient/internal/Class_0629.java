/*
 * Decompiled with CFR 0.152.
 */
package me.mioclient.internal;

import java.lang.invoke.LambdaMetafactory;
import java.util.function.Consumer;
import me.mioclient.Hub;
import me.mioclient.api.Class_0597;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0016;
import me.mioclient.internal.Class_0482;
import me.mioclient.internal.RotationManager;
import me.mioclient.internal.Class_0878;
import me.mioclient.mixin.ducks.DuckAnimalModel;
import me.mioclient.mixin.ducks.DuckLivingEntityRenderer;
import me.mioclient.mixin.ducks.DuckVillagerResemblingModel;
import me.mioclient.module.render.AnimationsModule;
import me.mioclient.record.Class_0661;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public class Class_0629
implements MioAPI,
Class_0597<LivingEntity> {
    public static AnimationsModule animations = Hub.field_2595.method_78(AnimationsModule.class);
    public boolean field_2003;
    public boolean field_2004 = false;
    public boolean field_2005 = false;

    @Override
    public void method_2(LivingEntity livingEntity, float f, MatrixStack matrixStack) {
        VillagerResemblingModel villagerResemblingModel;
        Direction direction;
        float f2;
        Entity entity;
        Class_0878 class_0878;
        this.field_2003 = Class_0629.field_4219.player == livingEntity;
        this.field_2004 = livingEntity instanceof Class_0878;
        if (this.field_2004) {
            f = 1.0f;
        }
        if (livingEntity instanceof Class_0878) {
            class_0878 = (Class_0878)livingEntity;
            this.field_2005 = class_0878.method_806();
        } else {
            this.field_2005 = false;
        }
        Object renderer = field_4219.getEntityRenderDispatcher().getRenderer((Entity)livingEntity);
        if (!(renderer instanceof LivingEntityRenderer)) {
            return;
        }
        DuckLivingEntityRenderer duckLivingEntityRenderer = (DuckLivingEntityRenderer)renderer;
        EntityModel entityModel = ((LivingEntityRenderer)renderer).getModel();
        matrixStack.push();
        entityModel.handSwingProgress = livingEntity.getHandSwingProgress(f);
        entityModel.riding = livingEntity.hasVehicle();
        entityModel.child = livingEntity.isBaby();
        float f3 = MathHelper.lerpAngleDegrees((float)f, (float)livingEntity.prevBodyYaw, (float)livingEntity.bodyYaw);
        float f4 = MathHelper.lerpAngleDegrees((float)f, (float)livingEntity.prevHeadYaw, (float)livingEntity.headYaw);
        if (this.field_2003) {
            f3 = MathHelper.lerpAngleDegrees((float)f, (float)Class_0629.rotations().method_520(), (float)Class_0629.rotations().method_517());
            f4 = MathHelper.lerpAngleDegrees((float)f, (float)Class_0629.rotations().method_521(), (float)Class_0629.rotations().method_522());
        }
        float f5 = f4 - f3;
        if (livingEntity.hasVehicle() && (entity = livingEntity.getVehicle()) instanceof LivingEntity) {
            LivingEntity livingEntity2 = (LivingEntity)entity;
            f3 = this.field_2003 ? MathHelper.lerpAngleDegrees((float)f, (float)Class_0629.rotations().method_520(), (float)Class_0629.rotations().method_517()) : MathHelper.lerpAngleDegrees((float)f, (float)livingEntity2.prevBodyYaw, (float)livingEntity2.bodyYaw);
            f5 = f4 - f3;
            f2 = MathHelper.clamp((float)MathHelper.wrapDegrees((float)f5), (float)-85.0f, (float)85.0f);
            f3 = f4 - f2;
            if (f2 * f2 > 2500.0f) {
                f3 += f2 * 0.2f;
            }
            f5 = f4 - f3;
        }
        float f6 = MathHelper.lerp((float)f, (float)livingEntity.prevPitch, (float)livingEntity.getPitch());
        if (this.field_2003) {
            f6 = MathHelper.lerp((float)f, (float)Class_0629.rotations().method_519(), (float)Class_0629.rotations().method_516());
        }
        if (LivingEntityRenderer.shouldFlipUpsideDown((LivingEntity)livingEntity)) {
            f6 *= -1.0f;
            f5 *= -1.0f;
        }
        if (livingEntity.isInPose(EntityPose.SLEEPING) && (direction = livingEntity.getSleepingDirection()) != null) {
            float f7 = livingEntity.getEyeHeight(EntityPose.STANDING) - 0.1f;
            matrixStack.translate((float)(-direction.getOffsetX()) * f7, 0.0f, (float)(-direction.getOffsetZ()) * f7);
        }
        if (entityModel instanceof BipedEntityModel) {
            PlayerEntity playerEntity;
            BipedEntityModel biped = (BipedEntityModel)entityModel;
            biped.sneaking = livingEntity instanceof PlayerEntity && (playerEntity = (PlayerEntity)livingEntity) != Class_0629.field_4219.player && animations.method_130() ? true : livingEntity.isSneaking();
        }
        f2 = duckLivingEntityRenderer.mio$getAnimationProgress(livingEntity, f);
        float f8 = livingEntity.getScale();
        duckLivingEntityRenderer.mio$setupTransforms(livingEntity, matrixStack, f2, f3, f, f8);
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        duckLivingEntityRenderer.mio$scale(livingEntity, matrixStack, f);
        matrixStack.translate(0.0f, -1.501f, 0.0f);
        float f9 = 0.0f;
        float f10 = 0.0f;
        if (!livingEntity.hasVehicle() && livingEntity.isAlive()) {
            if (livingEntity instanceof Class_0878) {
                f9 = Math.min(livingEntity.limbAnimator.getSpeed(), 1.0f);
                f10 = livingEntity.limbAnimator.getPos();
            } else {
                f9 = Math.min(livingEntity.limbAnimator.getSpeed(f), 1.0f);
                f10 = livingEntity.limbAnimator.getPos(f);
            }
            if (livingEntity.isBaby()) {
                f10 *= 3.0f;
            }
        }
        entityModel.animateModel((Entity)livingEntity, f10, f9, f);
        entityModel.setAngles((Entity)livingEntity, f10, f9, f2, f5, f6);
        Class_0661 class_0661 = new Class_0661(matrixStack, Class_0016.BOTH);
        if (entityModel instanceof VillagerResemblingModel) {
            villagerResemblingModel = (VillagerResemblingModel)entityModel;
            ((DuckVillagerResemblingModel)(Object)villagerResemblingModel).mio$getHatRim().visible = false;
        }
        if (entityModel instanceof AnimalModel) {
            AnimalModel animalModel = (AnimalModel)entityModel;
            this.method_2(class_0661, animalModel);
        } else if (entityModel instanceof SinglePartEntityModel) {
            SinglePartEntityModel singlePartEntityModel = (SinglePartEntityModel)entityModel;
            Class_0482.method_2(class_0661, singlePartEntityModel.getPart());
        } else if (entityModel instanceof CompositeEntityModel) {
            CompositeEntityModel compositeEntityModel = (CompositeEntityModel)entityModel;
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
        } else {
            this.method_2((Object)class_0661, (Object)entityModel);
        }
        matrixStack.pop();
    }

    public void method_2(Class_0661 class_0661, EntityModel<?> entityModel) {
    }

    public void method_2(Class_0661 class_0661, AnimalModel<?> animalModel) {
        DuckAnimalModel duckAnimalModel = (DuckAnimalModel)animalModel;
        if (animalModel instanceof BipedEntityModel) {
            BipedEntityModel bipedEntityModel = (BipedEntityModel)animalModel;
            this.method_2(bipedEntityModel);
        }
        if (animalModel.child) {
            class_0661.method_667().push();
            if (duckAnimalModel.mio$isHeadScaled()) {
                float f = 1.5f / duckAnimalModel.mio$getInvertedChildHeadScale();
                class_0661.method_667().scale(f, f, f);
            }
            class_0661.method_667().translate(0.0f, duckAnimalModel.mio$getChildHeadYOffset() / 16.0f, duckAnimalModel.mio$getChildHeadZOffset() / 16.0f);
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
            class_0661.method_667().pop();
            class_0661.method_667().push();
            float f = 1.0f / duckAnimalModel.mio$getInvertedChildBodyScale();
            class_0661.method_667().scale(f, f, f);
            class_0661.method_667().translate(0.0f, duckAnimalModel.mio$getChildBodyYOffset() / 16.0f, 0.0f);
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
            class_0661.method_667().pop();
        } else {
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
        /* removed indy */ if (false) throw new UnsupportedOperationException("indy decompile failed - method removed by deobfuscator");
        }
    }

    public void method_2(BipedEntityModel<?> bipedEntityModel) {
        boolean bl;
        bipedEntityModel.hat.visible = bl = Class_0482.method_505() && !this.field_2004;
        if (bipedEntityModel instanceof PlayerEntityModel) {
            PlayerEntityModel playerEntityModel = (PlayerEntityModel)bipedEntityModel;
            playerEntityModel.leftPants.visible = bl;
            playerEntityModel.rightPants.visible = bl;
            playerEntityModel.leftSleeve.visible = bl;
            playerEntityModel.rightSleeve.visible = bl;
            playerEntityModel.jacket.visible = bl;
            if (this.field_2005) {
                playerEntityModel.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
                playerEntityModel.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
            }
        }
    }

    public <T> T method_2(T t, T t2) {
        if (this.field_2003) {
            return t2;
        }
        return t;
    }

    public static RotationManager rotations() {
        return Hub.field_2598;
    }
}

