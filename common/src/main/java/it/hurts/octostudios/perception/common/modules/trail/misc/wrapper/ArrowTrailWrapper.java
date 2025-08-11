package it.hurts.octostudios.perception.common.modules.trail.misc.wrapper;

import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.base.TrailWrapper;
import net.minecraft.world.entity.projectile.Arrow;

public class ArrowTrailWrapper extends TrailWrapper<Arrow> {
    public ArrowTrailWrapper(Arrow entity, TrailConfigData data) {
        super(entity, data);
    }

    @Override
    public int getTrailFadeInColor() {
        return entity.getColor();
    }
}