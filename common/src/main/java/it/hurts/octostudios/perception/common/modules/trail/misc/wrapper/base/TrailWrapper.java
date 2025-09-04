package it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.base;

import it.hurts.octostudios.octolib.module.particle.trail.EntityTrailProvider;
import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class TrailWrapper<T extends Entity> extends EntityTrailProvider<T> {
    @Getter
    @Setter
    private TrailConfigData data;

    public TrailWrapper(T entity, TrailConfigData data) {
        super(entity);

        this.data = data;
    }

    @Override
    public int getTrailMaxLength() {
        return data.getMaxPoints();
    }

    @Override
    public int getTrailUpdateFrequency() {
        return data.getUpdateFrequency();
    }

    @Override
    public double getTrailScale() {
        return data.getSize();
    }

    @Override
    public Vec3 getTrailPosition(float partialTicks) {
        var offset = data.getPositionOffset();

        var entityPosition = (entity.tickCount > 1 ? entity.getPosition(partialTicks) : entity.position()).add(entity.getDeltaMovement().normalize().scale(-data.getMotionShift())).add(offset.x(), offset.y(), offset.z());

        var player = entity.level().getNearestPlayer(entity, getTrailRenderDistance());

        if (player == null)
            return entityPosition;

        var playerPosition = player.getEyePosition(partialTicks);

        entityPosition = entityPosition.add(entityPosition.subtract(playerPosition).normalize().scale(data.getBackwardShift()));

        return entityPosition;
    }

    @Override
    public boolean isTrailGrowing() {
        return entity.getKnownMovement().length() >= data.getMinSpeed();
    }

    @Override
    public boolean isTrailAlive() {
        return entity.isAlive();
    }

    @Override
    public int getTrailFadeInColor() {
        return (int) Long.parseLong(data.getFadeInColor().replace("#", ""), 16);
    }

    @Override
    public int getTrailFadeOutColor() {
        return (int) Long.parseLong(data.getFadeOutColor().replace("#", ""), 16);
    }
}
