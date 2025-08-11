package it.hurts.octostudios.perception.common.modules.trail.misc.wrapper;

import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.base.TrailWrapper;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.projectile.Arrow;

public class ExperienceOrbTrailWrapper extends TrailWrapper<ExperienceOrb> {
    public ExperienceOrbTrailWrapper(ExperienceOrb entity, TrailConfigData data) {
        super(entity, data);
    }

    @Override
    public double getTrailScale() {
        var base = super.getTrailScale();

        return base + (base * entity.getIcon() * 0.015F);
    }
}