package it.hurts.octostudios.perception.common.init;

import it.hurts.octostudios.octolib.modules.config.ConfigManager;
import it.hurts.octostudios.octolib.modules.config.impl.OctoConfig;
import it.hurts.octostudios.perception.common.Perception;
import it.hurts.octostudios.perception.common.modules.shake.config.ShakeConfig;
import it.hurts.octostudios.perception.common.modules.trail.config.TrailConfig;

public class ConfigRegistry {
    public static ShakeConfig SHAKE_CONFIG = new ShakeConfig();
    public static TrailConfig TRAIL_CONFIG = new TrailConfig();

    public static void registerCommon() {
        registerModule("shake", SHAKE_CONFIG);
        registerModule("trail", TRAIL_CONFIG);
    }

    private static void registerModule(String path, OctoConfig config) {
        ConfigManager.registerConfig(Perception.MODID + "-v1" + "/modules/" + path, config);
    }
}