package it.hurts.octostudios.perception.common.mixin.trails.entity;

import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.ITrailConfigProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class EntityMixin implements ITrailConfigProvider {
    @Unique
    private TrailConfigData perception$trailData = new TrailConfigData();

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

        var data = getTrailConfigData();
        var offset = data.getPositionOffset();

        var entityPosition = (entity.tickCount > 1 ? entity.getPosition(partialTicks) : entity.position()).add(entity.getDeltaMovement().normalize().scale(-data.getMotionShift())).add(offset.x(), offset.y(), offset.z());

        var player = entity.getCommandSenderWorld().getNearestPlayer(entity, getTrailRenderDistance());

        if (player == null)
            return entityPosition;

        var playerPosition = player.getEyePosition(partialTicks);

        entityPosition = entityPosition.add(entityPosition.subtract(playerPosition).normalize().scale(data.getBackwardShift()));

        return entityPosition;
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