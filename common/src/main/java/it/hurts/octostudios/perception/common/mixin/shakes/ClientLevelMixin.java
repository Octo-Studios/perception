package it.hurts.octostudios.perception.common.mixin.shakes;

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
    public void onPlaySound(double x, double y, double z, SoundEvent soundEvent, SoundSource source, float volume, float pitch, boolean distanceDelay, long seed, CallbackInfo ci) {
        var id = soundEvent.getLocation().toString();

        var data = ConfigRegistry.SHAKE_CONFIG.getSoundShakes().get(id);

        if (data == null)
            return;

        var duration = data.getDuration();
        var fadeOutTime = data.getFadeOutTime();

        ShakeManager.add(Shake.builder(new Vec3(x, y, z))
                .rangeMultiplier(soundEvent.getRange(volume) * data.getRangeMultiplier())
                .fadeOutTime(fadeOutTime == -1 ? duration : fadeOutTime)
                .fadeInTime((int) (data.getFadeInTime() / pitch))
                .amplitude(data.getRotationAmplitude(), data.getOffsetAmplitude(), data.getFovAmplitude())
                .speed(data.getRotationSpeed() * pitch, data.getOffsetSpeed() * pitch, data.getFovSpeed() * pitch)
                .duration((int) (duration / pitch))
                .build());
    }
}
