package it.hurts.octostudios.perception.common.mixin.trails.particle;

import it.hurts.octostudios.octolib.modules.particles.OctoRenderManager;
import it.hurts.octostudios.perception.common.init.ConfigRegistry;
import it.hurts.octostudios.perception.common.modules.trail.misc.ITrailConfigProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParticleEngine.class)
public class ParticleEngineMixin {
    @Inject(method = "createParticle", at = @At("RETURN"))
    public void createParticle(ParticleOptions options, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, CallbackInfoReturnable<Particle> cir) {
        var particle = cir.getReturnValue();

        if (!(particle instanceof ITrailConfigProvider provider))
            return;

        var trail = ConfigRegistry.TRAIL_CONFIG.getParticleTrails().getOrDefault(BuiltInRegistries.PARTICLE_TYPE.getKey(options.getType()).toString(), null);

        if (trail != null) {
            provider.setTrailConfigData(trail);

            OctoRenderManager.registerProvider(provider);
        }
    }
}