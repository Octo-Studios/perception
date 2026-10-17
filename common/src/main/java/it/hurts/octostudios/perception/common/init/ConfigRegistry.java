package it.hurts.octostudios.perception.common.init;

import it.hurts.octostudios.perception.common.Perception;
import it.hurts.octostudios.perception.common.config.PerceptionConfigData;
import it.hurts.octostudios.perception.common.modules.chromatic_aberration.config.ChromaticAberrationConfig;
import it.hurts.octostudios.perception.common.modules.shake.config.ShakeConfig;
import it.hurts.octostudios.perception.common.modules.trail.config.TrailConfig;
import it.hurts.shatterbyte.shatterlib.module.config.ConfigManager;
import it.hurts.shatterbyte.shatterlib.platform.ShatterLibServices;

public class ConfigRegistry {
    public static PerceptionConfigData PERCEPTION_CONFIG = new PerceptionConfigData();

    public static ShakeConfig SHAKE_CONFIG = new ShakeConfig();
    public static TrailConfig TRAIL_CONFIG = new TrailConfig();
    public static ChromaticAberrationConfig CHROMATIC_ABERRATION_CONFIG = new ChromaticAberrationConfig();

    public static void registerCommon() {
        ConfigManager.register(Perception.MODID, PERCEPTION_CONFIG);

        ConfigManager.register(Perception.MODID, SHAKE_CONFIG);
        ConfigManager.register(Perception.MODID, TRAIL_CONFIG);
        ConfigManager.register(Perception.MODID, CHROMATIC_ABERRATION_CONFIG);

        SHAKE_CONFIG.load(ShatterLibServices.platform().getConfigDirectory(), true);
        TRAIL_CONFIG.load(ShatterLibServices.platform().getConfigDirectory(), true);
        CHROMATIC_ABERRATION_CONFIG.load(ShatterLibServices.platform().getConfigDirectory(), true);
    }
}