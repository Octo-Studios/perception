package it.hurts.octostudios.perception.common.mixin.trails.entity;

import it.hurts.octostudios.octolib.modules.particles.OctoRenderManager;
import it.hurts.octostudios.perception.common.init.ConfigRegistry;
import it.hurts.octostudios.perception.common.modules.trail.misc.ITrailConfigProvider;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Inject(method = "addEntity", at = @At("TAIL"))
    public void addEntity(int i, Entity entity, CallbackInfo ci) {
        if (!ConfigRegistry.PERCEPTION_CONFIG.isEnabledTrailsModule() || !entity.getCommandSenderWorld().isClientSide()
                || !(entity instanceof ITrailConfigProvider provider))
            return;

        var trail = ConfigRegistry.TRAIL_CONFIG.getEntityTrails().getOrDefault(EntityType.getKey(entity.getType()).toString(), null);

        if (trail == null)
            return;

        provider.setTrailConfigData(trail);

        OctoRenderManager.registerProvider(provider);
    }
}