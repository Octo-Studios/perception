package it.hurts.octostudios.perception.common.init;

import it.hurts.octostudios.octolib.modules.config.ConfigManager;
import it.hurts.octostudios.octolib.modules.config.impl.OctoConfig;
import it.hurts.octostudios.perception.common.Perception;
import it.hurts.octostudios.perception.common.modules.shake.config.ShakeConfig;

public class ConfigRegistry {
    public static ShakeConfig SHAKE_CONFIG = new ShakeConfig();

    public static void registerCommon() {
        registerModule("shake", SHAKE_CONFIG);
    }

    private static void registerModule(String path, OctoConfig config) {
        ConfigManager.registerConfig(Perception.MODID + "/modules/" + path, config);
    }
}