package it.hurts.octostudios.perception.common.mixin.chromatic_aberration;

import it.hurts.octostudios.perception.common.init.ConfigRegistry;
import it.hurts.shatterbyte.shatterlib.module.chromatic_aberration.ChromaticAberration;
import it.hurts.shatterbyte.shatterlib.module.chromatic_aberration.ChromaticAberrationManager;
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
        if (!ConfigRegistry.PERCEPTION_CONFIG.isEnabledChromaticAberrationModule())
            return;

        var id = soundEvent.location().toString();

        var data = ConfigRegistry.CHROMATIC_ABERRATION_CONFIG.getSoundAberrations().get(id);

        if (data == null)
            return;

        var duration = data.getDuration();
        var fadeOutTime = data.getFadeOutTime();

        var aberration = ChromaticAberration.builder(new Vec3(x, y, z))
                .radius(soundEvent.getRange(volume) * data.getRangeMultiplier())
                .fadeOutTime(fadeOutTime == -1 ? duration : fadeOutTime)
                .fadeInTime((int) (data.getFadeInTime() / pitch))
                .duration((int) (duration / pitch))
                .strength(data.getStrength())
                .build();

        ChromaticAberrationManager.add((ClientLevel) (Object) this, aberration);
    }
}
