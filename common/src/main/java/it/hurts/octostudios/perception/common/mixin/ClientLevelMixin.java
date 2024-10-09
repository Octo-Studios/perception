package it.hurts.octostudios.perception.common.mixin;

import it.hurts.octostudios.perception.common.init.ConfigRegistry;
import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Inject(method = "playSound", at = @At("HEAD"))
    public void onPlaySound(double pX, double pY, double pZ, SoundEvent pSoundEvent, SoundSource pSource, float pVolume, float pPitch, boolean pDistanceDelay, long pSeed, CallbackInfo ci) {
        var id = pSoundEvent.getLocation().toString();

        var data = ConfigRegistry.SHAKE_CONFIG.getSoundShakes().get(id);

        if (data == null)
            return;

        var radius = data.getRadius();
        var duration = data.getDuration();
        var fadeOutTime = data.getFadeOutTime();

        ShakeManager.add(Shake.builder(new Vec3(pX, pY, pZ))
                .radius(radius == -1 ? pSoundEvent.getRange(pVolume) : radius)
                .fadeOutTime(fadeOutTime == -1 ? duration : fadeOutTime)
                .fadeInTime(data.getFadeInTime())
                .amplitude(data.getAmplitude())
                .speed(data.getSpeed())
                .duration(duration)
                .build());
    }
}
