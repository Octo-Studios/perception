package it.hurts.octostudios.perception.common.init;

import dev.architectury.platform.Platform;
import it.hurts.octostudios.perception.common.Perception;
import it.hurts.octostudios.perception.common.config.PerceptionConfigData;
import it.hurts.octostudios.perception.common.modules.shake.config.ShakeConfig;
import it.hurts.octostudios.perception.common.modules.trail.config.TrailConfig;
import it.hurts.shatterbyte.shatterlib.module.config.ConfigManager;

public class ConfigRegistry {
    public static PerceptionConfigData PERCEPTION_CONFIG = new PerceptionConfigData();

    public static ShakeConfig SHAKE_CONFIG = new ShakeConfig();
    public static TrailConfig TRAIL_CONFIG = new TrailConfig();

    public static void registerCommon() {
        ConfigManager.register(Perception.MODID, PERCEPTION_CONFIG);

        ConfigManager.register(Perception.MODID, SHAKE_CONFIG);
        ConfigManager.register(Perception.MODID, TRAIL_CONFIG);
        SHAKE_CONFIG.load(Platform.getConfigFolder(), true);
        TRAIL_CONFIG.load(Platform.getConfigFolder(), true);
    }
}