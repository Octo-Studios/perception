package it.hurts.octostudios.perception.common.mixin.trails.particle;

import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.ITrailConfigProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Particle.class)
public class ParticleMixin implements ITrailConfigProvider {
    @Shadow
    protected double x;
    @Shadow
    protected double y;
    @Shadow
    protected double z;

    @Shadow
    protected double xo;
    @Shadow
    protected double yo;
    @Shadow
    protected double zo;

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
        var offset = getTrailConfigData().getPositionOffset();

        return new Vec3(x + offset.x(), y + offset.y(), z + offset.z());
    }

    @Override
    public boolean isTrailGrowing() {
//        return new Vec3(x, y, z).distanceTo(new Vec3(xo, yo, zo)) >= getTrailConfigData().getMinSpeed();
        return true;
    }

    @Override
    public boolean isTrailAlive() {
        var particle = (Particle) (Object) this;

        return particle.isAlive();
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