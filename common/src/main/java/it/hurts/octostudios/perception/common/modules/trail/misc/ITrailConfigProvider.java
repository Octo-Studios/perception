package it.hurts.octostudios.perception.common.modules.trail.misc;

import it.hurts.octostudios.octolib.modules.particles.trail.TrailProvider;
import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;

public interface ITrailConfigProvider extends TrailProvider {
    TrailConfigData getTrailConfigData();

    void setTrailConfigData(TrailConfigData data);
}