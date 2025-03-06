package it.hurts.octostudios.perception.common.mixin.shakes;

import com.mojang.blaze3d.vertex.PoseStack;
import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
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
        var player = Minecraft.getInstance().player;

        if (player == null)
            return;

        Iterator<Shake> iterator = ShakeManager.SHAKES.values().iterator();

        while (iterator.hasNext()) {
            Shake effect = iterator.next();

            effect.update(player);

            if (effect.isFinished())
                iterator.remove();
        }
    }

    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void onRenderLevel(float partialTicks, long l, PoseStack poseStack, CallbackInfo ci) {
        var MC = Minecraft.getInstance();
        var player = MC.player;

        if (player == null)
            return;

        var shakeRotation = new Vector3f();
        var shakeOffset = new Vector3f();

        for (var effect : ShakeManager.SHAKES.values()) {
            shakeRotation.add(effect.getShakeRotation(player, partialTicks));
            shakeOffset.add(effect.getShakeOffset(player, partialTicks));
        }

        if (shakeRotation.lengthSquared() > 0) {
            float rotationFactor = 10F;

            float rotationX = shakeRotation.x() * rotationFactor;
            float rotationY = shakeRotation.y() * rotationFactor;
            float rotationZ = shakeRotation.z() * rotationFactor;

            poseStack.mulPose(new Quaternionf()
                    .rotateX(-rotationX * (float) (Math.PI / 180F))
                    .rotateY(-rotationY * (float) (Math.PI / 180F))
                    .rotateZ(-rotationZ * (float) (Math.PI / 180F)));
        }

        if (shakeOffset.lengthSquared() > 0) {
            poseStack.translate(-shakeOffset.z(), shakeOffset.y(), shakeOffset.x());
        }
    }
}