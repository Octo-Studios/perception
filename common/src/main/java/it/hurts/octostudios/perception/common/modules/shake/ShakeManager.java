package it.hurts.octostudios.perception.common.modules.shake;

import it.hurts.octostudios.perception.common.init.ConfigRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShakeManager {
    public static final Map<UUID, Shake> SHAKES = new HashMap<>();

    public static void add(Shake shake) {
        if (ConfigRegistry.PERCEPTION_CONFIG.isEnabledShakesModule())
            SHAKES.put(shake.getUuid(), shake);
    }
}