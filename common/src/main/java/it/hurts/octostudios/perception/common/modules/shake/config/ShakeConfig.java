package it.hurts.octostudios.perception.common.modules.shake.config;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import it.hurts.octostudios.octolib.modules.config.impl.OctoConfig;
import it.hurts.octostudios.perception.common.modules.shake.config.data.FallShakeConfigData;
import it.hurts.octostudios.perception.common.modules.shake.config.data.ShakeConfigData;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ShakeConfig implements OctoConfig {
    @Prop(comment = "Options for configuring the screen shake effect during player falls.")
    private FallShakeConfigData fallShakes = new FallShakeConfigData();

    @Prop(comment = """
            List of sound effects that trigger the screen shaking effect.
            
            [radius] - The radius in blocks within which the shaking effect spreads. A value of -1 sets the shaking radius equal to the sound's playback radius;
            [rotationAmplitude|offsetAmplitude|fovAmplitude] - The intensity of the screen shaking;
            [rotationSpeed|offsetSpeed|fovSpeed] - The speed of the screen shaking;
            [duration] - The duration of the screen shaking;
            [fadeInTime] - The time in ticks for interpolating the screen shaking from the minimum to the maximum value. Applied at the start of the shaking effect;
            [fadeOutTime] - The time in ticks for interpolating the screen shaking from the maximum to the minimum value. Applied at the end of the shaking effect. A value of -1 sets the interpolation duration equal to the effect's total duration.
            
            * Rotation - Tilt of the screen away from the source of the shake;
            * Offset - Offset of the position away from the source of the shake;
            * FOV (Field of View) - Screen zoom-out.
            """)
    private Map<String, ShakeConfigData> soundShakes = new HashMap<>() {{
        put("minecraft:entity.ender_dragon.ambient", ShakeConfigData.builder()
                .amplitude(0.05F)
                .speed(10F)
                .duration(60)
                .build());
        put("minecraft:entity.ender_dragon.growl", ShakeConfigData.builder()
                .amplitude(0.05F)
                .speed(10F)
                .duration(60)
                .build());
        put("minecraft:block.sculk_shrieker.shriek", ShakeConfigData.builder()
                .amplitude(0.025F)
                .speed(10F)
                .duration(80)
                .build());
        put("minecraft:entity.warden.dig", ShakeConfigData.builder()
                .amplitude(0.1F)
                .speed(15F)
                .duration(80)
                .fadeInTime(50)
                .fadeOutTime(20)
                .build());
        put("minecraft:entity.warden.emerge", ShakeConfigData.builder()
                .amplitude(0.15F)
                .speed(10F)
                .duration(120)
                .build());
        put("minecraft:entity.warden.sonic_boom", ShakeConfigData.builder()
                .amplitude(0.25F)
                .speed(7F)
                .duration(50)
                .build());
        put("minecraft:entity.warden.roar", ShakeConfigData.builder()
                .amplitude(0.15F)
                .speed(15F)
                .duration(60)
                .fadeInTime(10)
                .build());
        put("minecraft:entity.warden.heartbeat", ShakeConfigData.builder()
                .amplitude(0.05F, 0.075F, 0F)
                .speed(10F)
                .duration(5)
                .build());
        put("minecraft:block.anvil.place", ShakeConfigData.builder()
                .amplitude(0.1F)
                .speed(7.5F)
                .duration(5)
                .build());
        put("minecraft:block.anvil.land", ShakeConfigData.builder()
                .amplitude(0.075F)
                .speed(7.5F)
                .duration(5)
                .build());
        put("minecraft:block.end_portal.spawn", ShakeConfigData.builder()
                .amplitude(0F, 0.1F, 0.25F)
                .speed(10F)
                .duration(80)
                .build());
        put("minecraft:entity.wither.spawn", ShakeConfigData.builder()
                .amplitude(0.05F, 0.05F, 0.1F)
                .speed(10F)
                .duration(60)
                .build());
        put("minecraft:entity.ender_dragon.death", ShakeConfigData.builder()
                .amplitude(0.05F, 0.05F, 0.15F)
                .speed(10F)
                .duration(260)
                .build());
        put("minecraft:entity.elder_guardian.curse", ShakeConfigData.builder()
                .amplitude(0.15F, 0.05F, 0.2F)
                .speed(2F, 2F, 10F)
                .duration(30)
                .build());
        put("minecraft:entity.generic.explode", ShakeConfigData.builder()
                .amplitude(0.1F, 0.5F, 0.15F)
                .speed(5F, 3.5F, 10F)
                .duration(5)
                .build());
        put("minecraft:entity.dragon_fireball.explode", ShakeConfigData.builder()
                .amplitude(0.1F, 0.5F, 0.15F)
                .speed(5F, 3.5F, 10F)
                .duration(5)
                .build());
    }};
}