package it.hurts.octostudios.perception.common.modules.chromatic_aberration.config;

import it.hurts.octostudios.octolib.module.config.annotation.Prop;
import it.hurts.octostudios.perception.common.modules.base.config.ModuleConfig;
import it.hurts.octostudios.perception.common.modules.chromatic_aberration.config.data.ChromaticAberrationConfigData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChromaticAberrationConfig extends ModuleConfig {
    @Prop(comment = """
            List of sound effects that trigger the chromatic aberration effect.
            
            [rangeMultiplier] - Multiplier of the standard sound playback range, which will be used as the radius for the chromatic aberration effect;
            [duration] - The duration of the chromatic aberration in ticks;
            [fadeInTime] - The time in ticks for interpolating the screen shaking from the minimum to the maximum value. Applied at the start of the shaking effect;
            [fadeOutTime] - The time in ticks for interpolating the screen shaking from the maximum to the minimum value. Applied at the end of the shaking effect. A value of -1 sets the interpolation duration equal to the effect's total duration.
            [strength] - the Strength of the chromatic aberration.
            """)
    private Map<String, ChromaticAberrationConfigData> soundShakes = new HashMap<>() {{
        put("minecraft:entity.ender_dragon.growl", ChromaticAberrationConfigData.builder()
                .duration(60)
                .fadeInTime(5)
                .strength(0.025F)
                .build());
        put("minecraft:entity.ender_dragon.ambient", ChromaticAberrationConfigData.builder()
                .duration(60)
                .fadeInTime(5)
                .strength(0.025F)
                .build());
        put("minecraft:block.sculk_shrieker.shriek", ChromaticAberrationConfigData.builder()
                .duration(90)
                .fadeInTime(5)
                .strength(0.015F)
                .build());
        put("minecraft:entity.warden.heartbeat", ChromaticAberrationConfigData.builder()
                .duration(5)
                .fadeInTime(3)
                .strength(0.05F)
                .build());
        put("minecraft:entity.generic.explode", ChromaticAberrationConfigData.builder()
                .duration(10)
                .fadeInTime(2)
                .strength(0.1F)
                .build());
        put("minecraft:entity.dragon_fireball.explode", ChromaticAberrationConfigData.builder()
                .duration(10)
                .fadeInTime(2)
                .strength(0.1F)
                .build());
        put("minecraft:entity.warden.sonic_boom", ChromaticAberrationConfigData.builder()
                .duration(60)
                .fadeInTime(10)
                .fadeOutTime(20)
                .strength(0.05F)
                .build());
        put("minecraft:entity.warden.roar", ChromaticAberrationConfigData.builder()
                .duration(80)
                .fadeInTime(20)
                .strength(0.05F)
                .build());
        put("minecraft:block.end_portal.spawn", ChromaticAberrationConfigData.builder()
                .duration(100)
                .strength(0.05F)
                .build());
        put("minecraft:entity.wither.spawn", ChromaticAberrationConfigData.builder()
                .duration(40)
                .strength(0.05F)
                .build());
        put("minecraft:entity.ender_dragon.death", ChromaticAberrationConfigData.builder()
                .duration(260)
                .fadeInTime(10)
                .fadeInTime(20)
                .strength(0.035F)
                .build());
        put("minecraft:entity.elder_guardian.curse", ChromaticAberrationConfigData.builder()
                .duration(60)
                .strength(0.025F)
                .build());
    }};
}