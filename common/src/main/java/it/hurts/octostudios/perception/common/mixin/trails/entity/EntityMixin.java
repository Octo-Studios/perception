package it.hurts.octostudios.perception.common.mixin.trails.entity;

import it.hurts.octostudios.octolib.modules.particles.OctoRenderManager;
import it.hurts.octostudios.perception.common.init.ConfigRegistry;
import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.ITrailConfigProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements ITrailConfigProvider {
    @Unique
    private TrailConfigData perception$trailData = new TrailConfigData();

    @Inject(at = @At("RETURN"), method = "<init>")
    public void init(EntityType<? extends Entity> entityType, Level level, CallbackInfo ci) {
        if (!level.isClientSide)
            return;

        var entity = (Entity) (Object) this;
        var trail = ConfigRegistry.TRAIL_CONFIG.getEntityTrails().getOrDefault(EntityType.getKey(entity.getType()).toString(), null);

        if (trail != null) {
            setTrailConfigData(trail);

            OctoRenderManager.registerProvider(this);
        }
    }

    @Override
    public TrailConfigData getTrailConfigData() {
        return this.perception$trailData;
    }

    @Override
    public void setTrailConfigData(TrailConfigData data) {
        this.perception$trailData = data;
    }

    @Override
    public int getTrailMaxLength() {
        return getTrailConfigData().getMaxPoints();
    }

    @Override
    public int getTrailUpdateFrequency() {
        return getTrailConfigData().getUpdateFrequency();
    }

    @Override
    public double getTrailScale() {
        return getTrailConfigData().getSize();
    }

    @Override
    public Vec3 getTrailPosition(float partialTicks) {
        var entity = (Entity) (Object) this;
        var offset = getTrailConfigData().getPositionOffset();

        return (entity.tickCount > 1 ? entity.getPosition(partialTicks) : entity.position()).add(offset.x(), offset.y(), offset.z());
    }

    @Override
    public boolean isTrailGrowing() {
        var entity = (Entity) (Object) this;

        return entity.getKnownMovement().length() >= getTrailConfigData().getMinSpeed();
    }

    @Override
    public boolean isTrailAlive() {
        var entity = (Entity) (Object) this;

        return entity.isAlive();
    }

    @Override
    public int getTrailFadeInColor() {
        return (int) Long.parseLong(getTrailConfigData().getFadeInColor().replace("#", ""), 16);
    }

    @Override
    public int getTrailFadeOutColor() {
        return (int) Long.parseLong(getTrailConfigData().getFadeOutColor().replace("#", ""), 16);
    }
}