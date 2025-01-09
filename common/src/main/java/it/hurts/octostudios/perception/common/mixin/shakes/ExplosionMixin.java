package it.hurts.octostudios.perception.common.mixin.shakes;

import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Final
    @Shadow
    private double x;
    @Final
    @Shadow
    private double y;
    @Final
    @Shadow
    private double z;

    @Inject(method = "finalizeExplosion", at = @At("HEAD"))
    public void onFinalizeExplosion(boolean spawnParticles, CallbackInfo ci) {
        var explosion = (Explosion) (Object) this;

        var radius = explosion.radius;

        ShakeManager.add(Shake.builder(new Vec3(x, y, z))
                .amplitude(radius * 0.1F, radius * 0.1F, radius * 0.01F)
                .radius(7F + radius * 2F)
                .duration(15)
                .speed(5F)
                .build());
    }
}