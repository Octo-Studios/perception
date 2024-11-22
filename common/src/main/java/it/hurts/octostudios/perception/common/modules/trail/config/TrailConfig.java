package it.hurts.octostudios.perception.common.modules.trail.config;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import it.hurts.octostudios.octolib.modules.config.impl.OctoConfig;
import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import lombok.Data;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

@Data
public class TrailConfig implements OctoConfig {
    @Prop(comment = "List of entity ID's that should have trail effect.")
    private Map<String, TrailConfigData> entityTrails = new HashMap<>() {{
        put("minecraft:arrow", new TrailConfigData(0.075F, 5, 0.001F, 1, "#00000000", "#80000000", new Vector3f(0F, 0F, 0F)));
        put("minecraft:spectral_arrow", new TrailConfigData(0.075F, 10, 0.001F, 1, "#FFFFD505", "#80000000", new Vector3f(0F, 0F, 0F)));
        put("minecraft:trident", new TrailConfigData(0.05F, 10, 0.001F, 1, "#FF00FFEA", "#80000000", new Vector3f(0F, 0F, 0F)));
        put("minecraft:experience_orb", new TrailConfigData(0.075F, 5, 0.001F, 1, "#FF66FF00", "#80D9FF00", new Vector3f(0F, 0.175F, 0F)));
        put("minecraft:item", new TrailConfigData(0.1F, 5, 0.001F, 1, "#00000000", "#32000000", new Vector3f(0F, 0.35F, 0F)));
        put("minecraft:firework_rocket", new TrailConfigData(0.075F, 15, 0.001F, 1, "#80FFFFFF", "#FFFF0000", new Vector3f(0F, 0.25F, 0F)));
        put("minecraft:potion", new TrailConfigData(0.085F, 10, 0.001F, 1, "#8AD1E2F4", "#80000000", new Vector3f(0F, 0.15F, 0F)));
        put("minecraft:fireball", new TrailConfigData(0.35F, 5, 0.001F, 1, "#FFEBAA18", "#80800000", new Vector3f(0F, 0.35F, 0F)));
        put("minecraft:small_fireball", new TrailConfigData(0.2F, 5, 0.001F, 1, "#FFEBAA18", "#80800000", new Vector3f(0F, 0.15F, 0F)));
        put("minecraft:dragon_fireball", new TrailConfigData(0.45F, 5, 0.001F, 1, "#FFD974F7", "#80FF00D0", new Vector3f(0F, 0.35F, 0F)));
        put("minecraft:wither_skull", new TrailConfigData(0.1F, 5, 0.001F, 1, "#FF576868", "#80000000", new Vector3f(0F, 0.125F, 0F)));
        put("minecraft:wind_charge", new TrailConfigData(0.075F, 10, 0.001F, 1, "#FFE8ECFC", "#80000000", new Vector3f(0F, 0.05F, 0F)));
        put("minecraft:breeze_wind_charge", new TrailConfigData(0.075F, 10, 0.001F, 1, "#FFE8ECFC", "#80000000", new Vector3f(0F, 0.05F, 0F)));
        put("minecraft:eye_of_ender", new TrailConfigData(0.075F, 10, 0.001F, 1, "#FF6321A6", "#80FF00D0", new Vector3f(0F, 0.125F, 0F)));
        put("minecraft:vex", new TrailConfigData(0.15F, 20, 0.001F, 1, "#FF515F6D", "#80000000", new Vector3f(0F, 0.4F, 0F)));
        put("minecraft:allay", new TrailConfigData(0.15F, 20, 0.001F, 1, "#FF63F8FC", "#80000000", new Vector3f(0F, 0.3F, 0F)));
    }};

    @Prop(comment = "List of particle ID's that should have trail effect.")
    private Map<String, TrailConfigData> particleTrails = new HashMap<>() {{
        put("minecraft:cherry_leaves", new TrailConfigData(0.025F, 3, 0.001F, 1, "#FFDE69FF", "#FFDE69FF", new Vector3f(0F, 0F, 0F)));
    }};
}