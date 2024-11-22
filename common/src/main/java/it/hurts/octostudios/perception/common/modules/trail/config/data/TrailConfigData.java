package it.hurts.octostudios.perception.common.modules.trail.config.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joml.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrailConfigData {
    private float size = 0.1F;
    private int maxPoints = 5;
    private float minSpeed = 0.001F;
    private int updateFrequency = 1;
    private String fadeInColor = "FFFFFFFF";
    private String fadeOutColor = "FFFFFFFF";
    private Vector3f positionOffset = new Vector3f(0F, 0F, 0F);
}