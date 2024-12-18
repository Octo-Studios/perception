package it.hurts.octostudios.perception.common.mixin;

import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class LevelMixin {
    @Inject(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;ZLnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/Explosion;", at = @At("TAIL"))
    private void onExplode(Entity pSource, DamageSource pDamageSource, ExplosionDamageCalculator pDamageCalculator, double pX, double pY, double pZ, float pRadius, boolean pFire, Level.ExplosionInteraction pExplosionInteraction, boolean pSpawnParticles, ParticleOptions pSmallExplosionParticles, ParticleOptions pLargeExplosionParticles, Holder<SoundEvent> pExplosionSound, CallbackInfoReturnable<Explosion> cir) {
        ShakeManager.add(Shake.builder(new Vec3(pX, pY, pZ))
                .radius(7F + pRadius * 3F)
                .amplitude(0.5F + (pRadius * 0.15F))
                .speed(7F)
                .duration(10)
                .build());
    }
}