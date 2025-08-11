package it.hurts.octostudios.perception.common.mixin.trails.particle;

import net.minecraft.client.particle.ParticleEngine;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ParticleEngine.class)
public class ParticleEngineMixin {
//    @Inject(method = "createParticle", at = @At("RETURN"))
//    public void createParticle(ParticleOptions options, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, CallbackInfoReturnable<Particle> cir) {
//        if (!ConfigRegistry.PERCEPTION_CONFIG.isEnabledTrailsModule())
//            return;
//
//        var id = BuiltInRegistries.PARTICLE_TYPE.getKey(options.getType());
//
//        if (id == null || !(cir.getReturnValue() instanceof ITrailConfigProvider provider))
//            return;
//
//        var trail = ConfigRegistry.TRAIL_CONFIG.getParticleTrails().getOrDefault(id.toString(), null);
//
//        if (trail != null) {
//            provider.setTrailConfigData(trail);
//
//            OctoRenderManager.registerProvider(provider);
//        }
//    }
}