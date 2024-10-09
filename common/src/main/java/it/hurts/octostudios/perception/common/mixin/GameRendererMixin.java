package it.hurts.octostudios.perception.common.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        Iterator<Shake> iterator = ShakeManager.SHAKES.values().iterator();

        while (iterator.hasNext()) {
            Shake effect = iterator.next();

            effect.update();

            if (effect.isFinished())
                iterator.remove();
        }
    }

    @Inject(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", shift = At.Shift.BEFORE))
    private void onRenderLevel(DeltaTracker pDeltaTracker, CallbackInfo ci, @Local(ordinal = 0) float partialTicks, @Local(ordinal = 0) PoseStack poseStack) {
        var MC = Minecraft.getInstance();
        var player = MC.player;

        if (player == null)
            return;

        var shakeOffset = new Vector3f();

        for (var effect : ShakeManager.SHAKES.values())
            shakeOffset.add(effect.getShakeOffset(player, partialTicks));

        if (shakeOffset.lengthSquared() > 0) {
            shakeOffset.rotate(new Quaternionf(MC.gameRenderer.getMainCamera().rotation()).conjugate());

            poseStack.translate(shakeOffset.x(), shakeOffset.y(), shakeOffset.z() * 0.25F);

            float rotationFactor = 5.0f;

            float rotationX = -shakeOffset.y() * rotationFactor;
            float rotationY = shakeOffset.x() * rotationFactor;
            float rotationZ = shakeOffset.z() * rotationFactor;

            poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
            poseStack.mulPose(Axis.YP.rotationDegrees(rotationY));
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotationZ));
        }
    }
}