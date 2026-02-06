package it.hurts.octostudios.perception.common.init;

import it.hurts.octostudios.perception.common.Perception;
import it.hurts.octostudios.perception.common.config.PerceptionConfigData;
import it.hurts.octostudios.perception.common.modules.shake.config.ShakeConfig;
import it.hurts.octostudios.perception.common.modules.trail.config.TrailConfig;
import it.hurts.shatterbyte.shatterlib.module.config.ConfigManager;
import it.hurts.shatterbyte.shatterlib.module.config.ShatterConfig;

public class ConfigRegistry {
    public static PerceptionConfigData PERCEPTION_CONFIG = new PerceptionConfigData();

    public static ShakeConfig SHAKE_CONFIG = new ShakeConfig();
    public static TrailConfig TRAIL_CONFIG = new TrailConfig();

    public static void registerCommon() {
        ConfigManager.register(Perception.MODID, PERCEPTION_CONFIG);

        if (!PERCEPTION_CONFIG.isEnabledExtendedConfigs())
            return;

        registerModule("shake", SHAKE_CONFIG);
        registerModule("trail", TRAIL_CONFIG);
    }

    private static void registerModule(String path, ShatterConfig config) {
        ConfigManager.register(Perception.MODID + "/modules/" + path, config);
    }
}