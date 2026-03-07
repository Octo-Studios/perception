package it.hurts.octostudios.perception.common.modules.trail.config;

import it.hurts.octostudios.perception.common.Perception;
import it.hurts.octostudios.perception.common.modules.base.config.ModuleConfig;
import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.shatterbyte.shatterlib.module.config.type.annotation.Comment;
import it.hurts.shatterbyte.shatterlib.util.ShatterColor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrailConfig extends ModuleConfig {
    @Comment("""
            List of entity ID's that should have trail effect.
            
            [size] - Trail thickness;
            [maxPoints] - Maximum trail length, measured in points that are placed every N ticks, where N is the value of updateFrequency;
            [minSpeed] - Minimum object movement speed to place trail points;
            [updateFrequency] - Frequency of trail point placement;
            [fadeInColor] - Starting color of the trail in ARGB format;
            [fadeOutColor] - Ending color of the trail in ARGB format;
            [positionOffset] - Position offset of the trail relative to the object in XYZ;
            [backwardShift] - Position offset of the trail in the opposite direction from the player's standing point;
            [motionShift] - Position offset of the trail in the opposite direction from the object's movement.
            """)
    private LinkedHashMap<String, TrailConfigData> entityTrails = new LinkedHashMap<>() {{
        put("minecraft:arrow", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0x00000000))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:spectral_arrow", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(10)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFFFFD505))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:trident", TrailConfigData.builder()
                .size(0.05F)
                .maxPoints(10)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF00FFEA))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:experience_orb", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF66FF00))
                .fadeOutColor(new ShatterColor(0x80D9FF00))
                .positionOffset(new Vector3f(0F, 0.175F, 0F))
                .backwardShift(0.1F)
                .motionShift(0.1F)
                .build());
        put("minecraft:firework_rocket", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(15)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0x80FFFFFF))
                .fadeOutColor(new ShatterColor(0xFFFF0000))
                .positionOffset(new Vector3f(0F, 0.25F, 0F))
                .backwardShift(0F)
                .motionShift(1F)
                .build());
        put("minecraft:potion", TrailConfigData.builder()
                .size(0.085F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0x8AD1E2F4))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.15F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:fireball", TrailConfigData.builder()
                .size(0.35F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFFEBAA18))
                .fadeOutColor(new ShatterColor(0x80800000))
                .positionOffset(new Vector3f(0F, 0.35F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:small_fireball", TrailConfigData.builder()
                .size(0.2F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFFEBAA18))
                .fadeOutColor(new ShatterColor(0x80800000))
                .positionOffset(new Vector3f(0F, 0.15F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:dragon_fireball", TrailConfigData.builder()
                .size(0.45F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFFD974F7))
                .fadeOutColor(new ShatterColor(0x80FF00D0))
                .positionOffset(new Vector3f(0F, 0.35F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:wither_skull", TrailConfigData.builder()
                .size(0.1F)
                .maxPoints(5)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF576868))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.125F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:wind_charge", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(10)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFFE8ECFC))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.05F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:breeze_wind_charge", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(10)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFFE8ECFC))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.05F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:eye_of_ender", TrailConfigData.builder()
                .size(0.075F)
                .maxPoints(10)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF6321A6))
                .fadeOutColor(new ShatterColor(0x80FF00D0))
                .positionOffset(new Vector3f(0F, 0.125F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:vex", TrailConfigData.builder()
                .size(0.15F)
                .maxPoints(20)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF515F6D))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.4F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:allay", TrailConfigData.builder()
                .size(0.15F)
                .maxPoints(20)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF63F8FC))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.3F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
        put("minecraft:phantom", TrailConfigData.builder()
                .size(0.15F)
                .maxPoints(30)
                .minSpeed(0.001F)
                .updateFrequency(1)
                .fadeInColor(new ShatterColor(0xFF38007D))
                .fadeOutColor(new ShatterColor(0x80000000))
                .positionOffset(new Vector3f(0F, 0.25F, 0F))
                .backwardShift(0F)
                .motionShift(0F)
                .build());
    }};

//    @Comment("List of particle ID's that should have trail effect.")
//    private Map<String, TrailConfigData> particleTrails = new HashMap<>();

    @Override
    public String getName() {
        return Perception.MODID + "/modules/trail";
    }
}