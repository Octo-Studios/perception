package it.hurts.octostudios.perception.common.modules.shake.config;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import it.hurts.octostudios.octolib.modules.config.impl.OctoConfig;
import it.hurts.octostudios.perception.common.modules.shake.config.data.ShakeConfigData;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ShakeConfig implements OctoConfig {
    @Prop(comment = "List of sound effects that trigger the screen shaking effect.")
    private Map<String, ShakeConfigData> soundShakes = new HashMap<>() {{
        put("minecraft:entity.ender_dragon.ambient", new ShakeConfigData(-1, 0.05F, 10, 60, 0, -1));
        put("minecraft:entity.ender_dragon.growl", new ShakeConfigData(-1, 0.05F, 10, 60, 0, -1));
        put("minecraft:block.sculk_shrieker.shriek", new ShakeConfigData(-1, 0.05F, 10, 80, 0, -1));
        put("minecraft:entity.warden.dig", new ShakeConfigData(-1, 0.1F, 15F, 80, 40, 20));
        put("minecraft:entity.warden.emerge", new ShakeConfigData(-1, 0.25F, 10F, 120, 0, -1));
        put("minecraft:entity.warden.sonic_boom", new ShakeConfigData(-1, 0.4F, 7F, 50, 0, -1));
        put("minecraft:entity.warden.roar", new ShakeConfigData(-1, 0.35F, 15F, 60, 10, -1));
    }};
}