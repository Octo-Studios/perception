package it.hurts.octostudios.perception.common.mixin.shakes;

import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow
    protected abstract void move(float forwards, float up, float right);

    @Shadow
    public abstract float getCameraEntityPartialTicks(DeltaTracker deltaTracker);

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;alignWithEntity(F)V", shift = At.Shift.AFTER))
    public void onUpdate(DeltaTracker deltaTracker, CallbackInfo ci) {
        var player = Minecraft.getInstance().player;

        if (player == null)
            return;

        var partialTicks = getCameraEntityPartialTicks(deltaTracker);

        var shakeRotation = new Vector3f();
        var shakeOffset = new Vector3f();

        for (var effect : ShakeManager.SHAKES.values()) {
            shakeRotation.add(effect.getShakeRotation(player, partialTicks));
            shakeOffset.add(effect.getShakeOffset(player, partialTicks));
        }

        var camera = (Camera) (Object) this;

        var inverseRotation = new Quaternionf(camera.rotation()).conjugate();

        if (shakeRotation.lengthSquared() > 0) {
            shakeRotation.rotate(inverseRotation);

            float rotationFactor = 10F;

            float rotationX = shakeRotation.x() * rotationFactor;
            float rotationY = shakeRotation.y() * rotationFactor;
            float rotationZ = shakeRotation.z() * rotationFactor;

            camera.rotation().mul(new Quaternionf()
                    .rotateX(-rotationX * (float) (Math.PI / 180F))
                    .rotateY(-rotationY * (float) (Math.PI / 180F))
                    .rotateZ(-rotationZ * (float) (Math.PI / 180F)));
        }

        if (shakeOffset.lengthSquared() > 0) {
            shakeOffset.rotate(inverseRotation);

            move(-shakeOffset.z(), shakeOffset.y(), shakeOffset.x());
        }
    }
}